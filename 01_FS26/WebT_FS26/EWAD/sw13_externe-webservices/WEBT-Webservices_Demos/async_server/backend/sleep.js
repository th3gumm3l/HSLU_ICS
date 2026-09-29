import express from 'express'
import { setTimeout } from 'node:timers/promises';
const router = express.Router();

let counter = 0;
router.get('/', async function(req, res) {
    res.type('text/plain')
    if ('duration' in req.query && parseInt(req.query.duration) >= 0) {
        const duration = parseInt(req.query.duration);
        counter++;
        await setTimeout(duration);
        res.send(`${counter}`);
    } else {
        res.status(400);
        res.send('parameter "duration" missing');
    }
});

export default router;