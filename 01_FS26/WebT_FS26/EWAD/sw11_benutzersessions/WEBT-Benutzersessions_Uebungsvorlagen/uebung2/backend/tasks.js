import express from 'express';
import { MongoClient } from 'mongodb'

const router = express.Router();
const mongoClient = new MongoClient('mongodb://localhost');
const db = mongoClient.db('tasks');
const tasks = db.collection('tasks');

router.get('/', async function(req, res) {
    res.type('application/json');

    if (!isUserLoggedIn(req)) {
        res.status(401);
        res.send({ message: 'not logged in' });
        return;
    }

    try {
        const result = await tasks.find({
            user: getUser(req)
        }, { projection: { _id: 0, 'description': 1, 'due': 1}}).toArray();
        res.send(JSON.stringify(result));
    } catch (error) {
        console.log(error);
        res.status(500);
        res.send('internal server error while accesing database');
    }
});

router.post('/', async function(req, res) {
    res.type('application/json');

    let body = null;
    try {
        body = getAndValidateBody(req);
    } catch (error) {
        res.status(400);
        res.send({ message: `malformed request: ${error.message}` });
        return;
    }

    if (!isUserLoggedIn(req)) {
        res.status(401);
        res.send({ message: 'not logged in' });
        return;
    }

    // insert and return of data (insert operation adds _id property)
    try {
        const data = {
            description: body.description,
            due: new Date(body.due),
            user: getUser(req)
        };
        await tasks.insertOne(data);
        res.send(JSON.stringify(data));
    } catch (error) {
        console.log(error);
        res.status(500);
        res.send('internal server error while accesing database');
    }
});

function getAndValidateBody(req) {
    const body = JSON.parse(req.body.toString('utf-8'));    
    if (body == null) throw Error('body cannot be empty');
    if (!('description' in body)) throw Error('property "description" is missing');
    if (!('due' in body)) throw Error('property "due" is missing');
    if (body.description.length < 0 || body.description.length > 100) throw Error('property "description" has invalid length (0 < length < 100)');
    if (body.due != new Date(body.due).toISOString().slice(0, 10)) throw Error('property "due" has wrong date format');
    if (new Date(body.due) < Date.now()) throw Error('property "due" must be in the future');
    return body;
}

function isUserLoggedIn(req) {
    return 'user' in req.session;
}

function getUser(req) {
    return req.session.user;
}

export default router;
