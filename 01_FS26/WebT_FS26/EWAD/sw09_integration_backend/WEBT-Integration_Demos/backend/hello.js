import express from 'express';
const router = express.Router();

router.get('/', function(req, res) {
    let result;

    // parameter verification
    if (('name' in req.query) && req.query.name != '') {
        result = {
            message: 'Hello ' + req.query.name
        };        
    } else {
        // produce error message and set http response code to 400
        result = {
            error: 'Hello ' + 'Parameter <name> is not set or empty'
        };
        res.status(400);
    }
    
    // return result
    res.send(JSON.stringify(result));
});

export default router;
