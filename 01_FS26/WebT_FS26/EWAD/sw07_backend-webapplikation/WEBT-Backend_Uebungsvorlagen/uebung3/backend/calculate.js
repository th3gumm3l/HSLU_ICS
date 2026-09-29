import express from 'express';
const router = express.Router();

function calculateResistance(r1, r2, wiring) {
    let result = 0;
    if (wiring === "serial") {
        result = r1 + r2;
    } else if (wiring === "parallel") {
        result = (r1 * r2) / (r1 + r2);
    }
    return result;
}

router.all('/', function (req, res) {
    res.type('application/json');

    let error = null;
    const { r1, r2, wiring } = req.query;

    if (r1 === undefined || r2 === undefined || wiring === undefined) {
        error = { error: "Parameters r1, r2 and wiring are required." };
    } else if (isNaN(parseFloat(r1)) || isNaN(parseFloat(r2))) {
        error = { error: "r1 and r2 must be valid numbers." };
    } else if (wiring !== "serial" && wiring !== "parallel") {
        error = { error: "wiring must be 'serial' or 'parallel'." };
    }

    if (error) {
        res.status(400);
        res.send(error);
        return;
    }

    let resistance = calculateResistance(parseFloat(r1), parseFloat(r2), wiring);
    let response = { resistance: resistance };

    res.send(JSON.stringify(response));
});

export default router;