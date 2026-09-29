import express from 'express'
const router = express.Router();

function callSleep() {
    return fetch('http://localhost/sleep?duration=3000', {
        method: 'GET',
        signal: AbortSignal.timeout(10000)
    });
}

async function evaluateResponse(response) {
    if (response.ok) {
        return await response.text();
    } else {
        throw Error('fetch failed with status: ' + response.status);
    }
}

router.get('/sync', async function(req, res) {
    res.type('text/plain');
    try {
        const response1 = callSleep();
        const value1 = await evaluateResponse(await response1);
        const response2 = callSleep();
        const value2 = await evaluateResponse(await response2);
        res.send(`value1 = ${value1} / value2 = ${value2}`);
    } catch (error) {
        console.log(error);
        res.status(500);
        res.send('error during sync request processing');
    }
});

router.get('/async', async function(req, res) {
    res.type('text/plain');
    try {
        const response1 = callSleep();
        const response2 = callSleep();
        const value1 = await evaluateResponse(await response1);
        const value2 = await evaluateResponse(await response2);
        res.send(`value1 = ${value1} / value2 = ${value2}`);
    } catch (error) {
        console.log(error);
        res.status(500);
        res.send('error during sync request processing');
    }
});

export default router;