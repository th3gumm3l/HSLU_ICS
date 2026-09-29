// initial station id (zurich)
let station = '8503000';

// refresh station board data
async function refresh() {
    if (station) {
        try {
            // create request content
            const url = 'https://transport.opendata.ch/v1/stationboard?id=' + station + '&limit=15';
            const response = await fetch(url, {
                method: 'GET',
                signal: AbortSignal.timeout(5000)
            });
            if (response.ok) {
                const result = JSON.parse(await response.text());
                displayStationboard(result);
            } else {
                throw Error('fetch failed with status: ' + response.status);
            }
        } catch (error) {
            alert('fetch failed: ' + error);
        }
    }
}

// process received stationboard from api
function displayStationboard(stationData) {
    const stationboard = document.querySelector('#stationboard tbody');
    let lines = '';
    let index = 0;

    // iterate over all stationboard results
    while (stationData.stationboard.length > index) {
        let current = stationData.stationboard[index];

        // add train and destination
        lines += createStationboardEntry(current);
        index++;
    }

    // add stationboard information to html
    stationboard.innerHTML = lines;
}

function createStationboardEntry(current) {
    // create new line
    let line = '<tr><td>';

    // add departure time
    const departure = new Date(current.stop.departure);
    if (current.stop.prognosis.departure) {
        const prognosis = new Date(current.stop.prognosis.departure);
        const delay = (prognosis.valueOf() - departure.valueOf()) / 60000;
        line += formatDate(departure) + ' <strong>+' + delay + ' min</strong>';
    } else {
        line += formatDate(departure);
    }

    line += '</td><td>' + current.category + current.number + '</td><td>' + current.to + '</td></tr>'
    return line;
}

// format times in a proper way
function formatDate(dateAsString) {
    return dateAsString.toLocaleTimeString();
}

// event if station was selected
window.addEventListener('stationSelected', async function (data) {
    station = data.detail.id;
    refresh();
});

// load initial stationboard after everything is loaded
window.addEventListener('load', async function () {
    refresh();
});
