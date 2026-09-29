import express from 'express';
const router = express.Router();

router.get('/', function (req, res) {
    res.type('application/json');

    let response;
    if (!('firstName' in req.query)) {
        response = {
            error: "Error: Request-Parameter 'firstName' not set"
        };
        res.status(400);
    } else if (req.query.firstName == '') {
        response = {
            error: "Error: Request-Parameter 'firstName' is empty"
        };
        res.status(400);
    } else {
        response = {
            message: 'Hallo ' + req.query.firstName
        };
    }

    res.send(JSON.stringify(response));
});

export default router;