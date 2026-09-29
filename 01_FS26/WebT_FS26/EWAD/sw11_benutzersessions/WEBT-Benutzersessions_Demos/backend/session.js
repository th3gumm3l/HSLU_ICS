import express from 'express';
const router = express.Router();

router.get('/id', function (req, res) {
    res.send(JSON.stringify({message : 'session id is "' + req.session.id + '"'}));
});

router.delete('/id', function (req, res) {
    const sessionId = req.session.id;
    req.session.destroy();
    res.send(JSON.stringify({message : 'session id "' + sessionId + '" closed'}));
});

router.post('/value', function (req, res) {
    req.session.lastOption = 3;
    res.send(JSON.stringify({message : 'session variable "lastOption" set new value "3"'}));
});

router.get('/value', function (req, res) {
    let message;
    if ('lastOption' in req.session) {
        message = 'session variable "lastOption"  contains "' + req.session.lastOption + '"';
    } else {
        message = 'session variable "lastOption" not found';
    }
    res.send(JSON.stringify({message : message}));
});

export default router;