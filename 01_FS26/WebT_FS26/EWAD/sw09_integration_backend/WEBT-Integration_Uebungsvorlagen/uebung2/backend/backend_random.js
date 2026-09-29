import express from 'express';
const router = express.Router();

router.get('/', function (req, res) {
    res.type('application/json');

    let random = Math.random() * 100;

    // determine color of number (green / red)
    let color = '';
    if (random < 50) {
        color = 'green';
    } else {
        color = 'red';
    }

    // output information as JSON
    let response = {
        random: random,
        color: color
    };
    res.send(JSON.stringify(response));
});

export default router;