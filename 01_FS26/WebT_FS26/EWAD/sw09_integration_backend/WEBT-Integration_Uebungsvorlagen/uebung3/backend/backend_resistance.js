import express from 'express';
const router = express.Router();

//  calculates total resistance corresponing to wiring
function calculateResistance(r1, r2, wiring) {
    let totalResistance;
    if (wiring == 'serial') {
        totalResistance = r1 + r2;
    } else if (wiring == 'parallel') {
        totalResistance = r1 * r2 / (r1 + r2);
    }
    return totalResistance;
}

// handle request
router.post('/', function (req, res) {
    res.type('application/json');

    let body;
    try {
        body = JSON.parse(req.body.toString('utf-8'));
    } catch (e) {
        res.status(400);
        res.send(JSON.stringify({ error: 'Invalid JSON document' }));
        return;
    }

    let error = null;

    if (!('r1' in body)) {
        error = { error: "Parameter 'r1' not set" };
    } else if (!('r2' in body)) {
        error = { error: "Parameter 'r2' not set" };
    } else if (!('wiring' in body)) {
        error = { error: "Parameter 'wiring' not set" };
    } else if (isNaN(body.r1) || parseFloat(body.r1) < 0) {
        error = { error: "Invalid value for parameter 'r1'" };
    } else if (isNaN(body.r2) || parseFloat(body.r2) < 0) {
        error = { error: "Invalid value for parameter 'r2'" };
    } else if (parseFloat(body.r1) == 0 && parseFloat(body.r2) == 0) {
        error = { error: "r1 and r2 can not both be 0" };
    } else if (body.wiring!='serial' && body.wiring!='parallel') {
        error = { error: "Invalid value for parameter 'wiring'" };
    }

    if (error) {
        res.status(400);
        res.send(error);
        return;
    }

    let resistance = calculateResistance(parseFloat(body.r1), parseFloat(body.r2), body.wiring);
    let response = { resistance: resistance };

    res.send(JSON.stringify(response));
});

export default router;