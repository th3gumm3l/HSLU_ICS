import express from 'express';
const router = express.Router();

router.get('/', function (req, res) {
    res.type('text/plain');
    
    // perform validation
    let error = null;
    if (!('currency' in req.query)) {
        error = "Error: Parameter 'currency' not set";
    } else if (req.query.currency != 'CHF' && req.query.currency != 'EUR') {
        error = 'Error: Invalid value for parameter \'currency\'';
    }
    
    // report error
    if (error) {
        res.status(400); // bad request
        res.send(error);
        return;
    }
    
    // process and send response
    res.send('continue processing with currency ' + req.query.currency);
});

export default router;