import express from 'express';
import fs from 'node:fs';
const router = express.Router();

// constants
const question = 'Wie finden Sie das Wetter Heute?';
const responses = [ 'spitze', 'sensationell', 'fantastisch', 'super' ];

// get vote count for option 'option' from votes.json
function getVotes(option) {
    if (fs.existsSync('votes.json')) {
        const data = fs.readFileSync('votes.json');
        const votes = JSON.parse(data);
        return votes['option' + option];
    } else {
        return 0;
    }
}

// stores new vote count for option 'option' in votes.json
function updateVotes(option, count) {
    let votes;
    if (fs.existsSync('votes.json')) {
        const data = fs.readFileSync('votes.json');
        votes = JSON.parse(data);
    } else {
        votes = { option0: 0, option1: 0, option2: 0, option3: 0 };
    }
    votes['option' + option] = count;
    fs.writeFileSync('votes.json', JSON.stringify(votes));
}

// queries number of votes for an option
router.post('/', function (req, res) {
    res.type('application/json');

    // check if parameter 'options' is set and is valid
    let error = null;
    if (!('option' in req.query)) {
        error = 'Parameter \'option\' not set';
    } else if (req.query.option < 0 || req.query.option >= responses.length) {
        error = 'Invalid value for parameter \'option\'';
    }

    // abort on error
    if (error) {
        res.status(400);
        res.send(JSON.stringify({ message: error }));
        return;
    }
    
    // update value
    const option = parseInt(req.query.option);
    updateVotes(option, getVotes(option) + 1);

    // return results as json document
    const result = {};
    for (let i = 0; i < responses.length; ++i) {
        result['option' + i] = getVotes(i);
    }
    res.send(JSON.stringify(result));
});

export default router;