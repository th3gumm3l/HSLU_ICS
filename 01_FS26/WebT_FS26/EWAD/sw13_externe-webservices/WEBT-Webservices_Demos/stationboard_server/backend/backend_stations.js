import express from 'express';
const router = express.Router();

async function getStationData(station) {        
    // create request content
    const url = 'https://transport.opendata.ch/v1/locations?query=' + station;
    const response = await fetch(url, {
        method: 'GET',
        signal: AbortSignal.timeout(10000)
    });
    if (response.ok) {
        return JSON.parse(await response.text());
    } else {
        throw Error('fetch failed with status: ' + response.status);
    }
}

function produceOutput(stations) {
    const result = [];
    for (const station of stations) {
        result.push(station.name);
    }
    return result;
}

router.get('/', async function(req, res) {
    if (!('station' in req.query) && req.query.station != '') {
        res.status(400);
        res.send(JSON.stringify({error :'Parameter "station" is required'}));
        return;
    }
 
    try {
        const stationData = await getStationData(req.query.station);
        const result = produceOutput(stationData.stations);
        res.send(JSON.stringify(result));
    } catch (error) {
        console.log(error);
        res.status(500);
        res.send(JSON.stringify({error : 'Internal error occured'}));            
    }
});

export default router;