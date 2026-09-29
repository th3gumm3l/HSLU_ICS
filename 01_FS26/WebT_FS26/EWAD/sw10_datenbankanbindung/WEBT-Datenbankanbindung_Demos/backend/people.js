import express from 'express';
import { MongoClient }  from 'mongodb';

const router = express.Router();

// set database connection parameters
const client = new MongoClient('mongodb://localhost');
const db = client.db('people');
const birthdays = db.collection('birthdays');

// process put request
router.post('/', async function(req, res) {
    res.type('application/json');

    let error = null;
    if (!('name' in req.query)) {
        error = 'Parameter "name" is not present';
    } else if (req.query.name == null) {
        error = 'Parameter "name" is empty';
    } else if (!('day' in req.query)) {
        error = 'Parameter "day" is not present';
    } else if (req.query.day == null) {
        error = 'Parameter "day" is empty';
    }

    // abort on error
    if (error) {
        res.status(400);
        res.send(JSON.stringify({ message: error }));
        return;
    }

    let message = null;
    try {
        await birthdays.insertOne({
            name: req.query.name,
            day: new Date(req.query.day)
        });
        message = 'Entry successfully added';
    } catch (error) {
        message = 'Could not add entry successfully: ' + error;
        res.status(500);
    }
    res.send(JSON.stringify({message: message}));
});

// process get request
router.get('/', async function(req, res) {
    res.type('application/json');
    
    let message;
    let results = null;
    try {
        results = await birthdays.find().toArray();
        message = {
            people: []
        }
        for (const result of results) {
            message.people.push({
                name: result.name,
                day: result.day
            });
        }
    } catch (error) {
        message = 'Could not query database successfully: ' + error;
        res.status(500);
    }
    res.send(JSON.stringify({message: message}));
});

export default router;