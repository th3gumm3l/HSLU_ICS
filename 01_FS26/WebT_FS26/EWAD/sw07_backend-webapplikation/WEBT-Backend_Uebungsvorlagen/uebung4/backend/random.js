import express from 'express';
const router = express.Router();

router.all('/', function (req, res) {
    res.type('application/json');

    let random = Math.round(Math.random() * 100);

    let color = random < 50 ? 'green' : 'red';

    res.send(JSON.stringify({ random: random, color: color }));
});

export default router;