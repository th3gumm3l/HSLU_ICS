import express from 'express';
const router = express.Router();

router.get('/', function (req, res) {
    // verify that cookie lastOption is set
    let message;
    if ('lastOption' in req.cookies) {
        message = 'cookie "lastOption"  contains "' + req.cookies.lastOption + '"';  

        // verify that cookie lastOption is an integer in range 0 to 3
        const value = Number(req.cookies.lastOption)
        if (Number.isInteger(value) && value >= 0 && value <= 3) {
            message += ', which is a valid number in range 0 to 3';
        } else {
            message += ', which is not a valid number in range 0 to 3';
        }

    } else {
        message = 'cookie "lastOption" not found';
    }
    res.send(JSON.stringify({message : message}));
});

router.post('/', function (req, res) {
    res.cookie('lastOption', 3, {
        maxAge: 60 * 60 * 1000 // one hour specified as milliseconds
    });
    res.send(JSON.stringify({message : 'cookie "lastOption" set new value "3"'}));
});

router.delete('/', function (req, res) {
    res.cookie('lastOption', null, {
        maxAge: 0 // set expire time to "now"
    });
    res.send(JSON.stringify({message : 'set expire date of cookie "lastOption" to zero'}));
});

export default router;