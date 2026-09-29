import express from 'express';
import { MongoClient }  from 'mongodb';
import nodemailer from 'nodemailer';

const router = express.Router();

// set database connection parameters
const client = new MongoClient('mongodb://localhost');
const db = client.db('invite');
const votes = db.collection('votes');
await votes.createIndex( { 'email': 1 }, { unique: true } );

// constants
const responses = [ 'spitze', 'sensationell', 'fantastisch', 'super' ]; 

function setResponseAndStatus(res, data, status) {
    res.type('application/json');
    res.status(status);
    res.send(JSON.stringify(data));
}

function validateVoteRequest(data) {    
    if (!('token' in data)) return 'Property "token" not found';
    if (!('email' in data)) return 'Property "email" not found';

    if (!('option' in data)) return 'Property "option" not found';
    let option = Number(data.option);
    if (!Number.isInteger(option)) return 'Property "option" must be an integer value';
    if (option < 0 || option > responses.length) return 'Property "option" outside of range';
}

router.post('/vote', async function(req, res) {
    try {
        // validation
        const body = JSON.parse(req.body.toString('utf-8'));
        const error = validateVoteRequest(body);
        if (error != null) {
            setResponseAndStatus(res, { message: error }, 400);
            return;
        }

        // update vote and check authorization (email, token) at the same time
        const result = await votes.updateOne({ 
            email: { $eq: body.email },
            token: { $eq: body.token }
        }, {   
            $set: { vote: parseInt(body.option) }  
        });
    
        // return votes if successful otherwise failure message
        if (result.matchedCount == 1) {
            const allVotes = await votes.find({}, { projection: {email: 1 , vote: 1 }, sort: {email: 1 }}).toArray();
            setResponseAndStatus(res, allVotes, 200);            
        } else {
            setResponseAndStatus(res, { message: 'no entry with email found or token is incorrect' }, 400);
        }
    } catch (error) {
        // fatal error
        setResponseAndStatus(res, { message: 'server error while updating vote: ' + error}, 500);
        console.error(error);
    }
});

function validateInviteRequest(data) {
    if (data == null) return 'Input not in JSON-Format';
    if (!('email' in data)) return 'Property "email" not found';
}

function genHexString(len) {
    let output = '';
    for (let i = 0; i < len; ++i) {
        output += (Math.floor(Math.random() * 16)).toString(16);
    }
    return output;
}

async function sendMail(port, email, token) {
    const transporter = nodemailer.createTransport({
        port: 25,
        host: 'localhost',
        tls: {
            rejectUnauthorized: false
        },
    });
    const message = {
        from:    'noreply@domain.com',
        to:      email,
        subject: 'You have been invited to vote',
        text:    `Please vote using the following link: http://localhost:${port}/vote_frontend.html?email=${email}&token=${token}`
    };
    await transporter.sendMail(message);        
}

function logMail(port, email, token) {
    console.log(`Please vote using the following link: http://localhost:${port}/vote_frontend.html?email=${email}&token=${token}`);
}

router.post('/invite', async function (req, res) {
    // validate request
    try {
        const data = JSON.parse(req.body.toString('utf-8'));
        const error = validateInviteRequest(data);
        if (error != null) {
            setResponseAndStatus(res, { message: error }, 400);
            return;
        }

        // create token for later authentification check
        const token = genHexString(24);
        try {
            await votes.insertOne({
                email: data.email,
                token: token,
                vote: null
            });

            // success: send invitation email (absolute link must be adapted to actual domain)
            await sendMail(req.socket.localPort, data.email, token);
            logMail(req.socket.localPort, data.email, token);
            setResponseAndStatus(res, { message: data.email + ' added successfully'}, 200);
        } catch (error) {
           if (error.code !== 11000) throw error;
           setResponseAndStatus(res, { message: data.email + ' is already participant of this vote'}, 400);
        }

    } catch (error) {
        // fatal error
        setResponseAndStatus(res, { message: 'server error while invite participant "' + error }, 500);
        console.error(error);
    }
});

export default router;