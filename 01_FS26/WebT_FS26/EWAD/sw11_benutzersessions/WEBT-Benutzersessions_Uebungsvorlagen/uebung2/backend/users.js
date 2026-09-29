import express from 'express'
import { MongoClient } from 'mongodb'
import createUserAccounts from './lib/userAccounts.js';

const mongoClient = new MongoClient('mongodb://localhost');
const db = mongoClient.db('tasks');
const userAccounts = await createUserAccounts(db.collection('users'));
const router = express.Router();

// error codes
const RETVAL_SUCCESS = 0;
const RETVAL_VALIDATION_FAILED = 1;
const RETVAL_REGISTRAITON_FAILED = 2;
const RETVAL_AUTHENTICATION_FAILED = 3;
const RETVAL_INTERNAL_FAILURE = 4;

// handle register requests
// POST with JSON body in format: { "username": <username>, "password": <password> }
router.post('/register', async function(req, res) {
    res.type('application/json');
    try {
        const body = getAndValidateBody(req);
        const success = await userAccounts.register(body.username, body.password);
        if (!success) {
            throw createMessage(RETVAL_REGISTRAITON_FAILED, 'username is already registered');
        }
        res.send(createJsonMessage(RETVAL_SUCCESS, 'registration successful')); 
    } catch (error) {
        handleError(res, error);
    }
});

// handle login requests
// POST with JSON body in format: { "username": <username>, "password": <password> }
router.post('/login', async function(req, res) {
    res.type('application/json');
    try {
        const body = getAndValidateBody(req);
        const success = await userAccounts.authenticate(body.username, body.password);
        if (!success) {
            throw createMessage(RETVAL_AUTHENTICATION_FAILED, 'authentification failed'); 
        }
        req.session.user = body.username;
        res.send(createJsonMessage(RETVAL_SUCCESS, 'authentification successful')); 
    } catch (error) {
        handleError(res, error);
    }    
});

// handle logout requests
// POST without body
router.post('/logout', async function(req, res) {
    res.type('application/json');
    req.session.destroy();
    res.send(createJsonMessage(RETVAL_SUCCESS, 'logout successful')); 
});

// validate incomming request containing username and password
function getAndValidateBody(req) {
    const body = JSON.parse(req.body.toString('utf-8'));
    if (body == null) throw createMessage(RETVAL_VALIDATION_FAILED, 'Request body is empty');
    if (!('username' in body)) throw createMessage(RETVAL_VALIDATION_FAILED, 'property "username" is missing');
    if (body.username == '') throw createMessage(RETVAL_VALIDATION_FAILED, 'property "username" is missing');
    if (body.username.length < 0 || body.username.length > 50) throw createMessage(RETVAL_VALIDATION_FAILED, 'property "username" is empty or too long (>50)');
    if (!('password' in body)) throw createMessage(RETVAL_VALIDATION_FAILED, 'property "password" is missing');
    if (body.password == '') throw createMessage(RETVAL_VALIDATION_FAILED, ' property "password" is missing');
    if (body.password.length < 0 || body.password.length > 50) throw createMessage(RETVAL_VALIDATION_FAILED, 'property "password" is empty or too long (>50)');
    return body;
}

// handle errors during processing:
// - if an message is detected, report this known error to the client.
// - otherwise assume unknown error log and report internal error.
function handleError(res, error) {
    if ('id' in error && 'message' in error) {
        res.status(400);
        res.send(createJsonMessage(error.id, error.message));
    } else {            
        console.log(error);
        res.status(500);
        res.send(createJsonMessage(RETVAL_INTERNAL_FAILURE, 'error during processing of request'));
    }
}

// create a message and coverts it to JSON format
function createJsonMessage(id, message) {
    return JSON.stringify(createMessage(id, message))
}

// create a message with a classification id (to simplify error handling for client)
function createMessage(id, message) {
    return {
        message: message,
        id: id
    }
}

export default router;
