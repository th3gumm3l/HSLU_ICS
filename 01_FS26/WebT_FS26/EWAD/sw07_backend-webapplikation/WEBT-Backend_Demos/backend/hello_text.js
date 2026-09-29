import express from 'express';
const router = express.Router();

router.get('/', function (req, res) {
    res.type('text/plain');
    
    let response;
    if (!('firstName' in req.query)) {
        response = "Error: Request-Parameter 'firstName' not set";
        res.status(400);
    } else if (req.query.firstName == '') {
        response = "Error: Request-Parameter 'firstName' is empty";
        res.status(400);
    } else {
        response = 'Hallo ' + req.query.firstName;
    }

    res.send(response);
});

export default router;