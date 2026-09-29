import express from 'express';
const router = express.Router();

router.get('/', function (req, res) {
    res.type('application/json');
    
    const response = {};
    if ('firstName' in req.query && req.query.firstName != '') {
        response.message = 'Hallo ' + req.query.firstName;
    } else {
        response.error =  "Error: Request-Parameter 'firstName' not set or is empty";
        res.status(400);
    }

    res.send(JSON.stringify(response));
});

export default router;