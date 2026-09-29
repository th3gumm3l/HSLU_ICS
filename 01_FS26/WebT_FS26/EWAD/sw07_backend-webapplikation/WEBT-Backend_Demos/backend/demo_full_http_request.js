import express from 'express';
const router = express.Router();

router.all('/', function (req, res) {
    const response = {
        method: req.method,
        query: req.query,
        body: req.body.toString('utf-8'),
    }
    res.type('application/json');
    res.send(JSON.stringify(response));
});

export default router;