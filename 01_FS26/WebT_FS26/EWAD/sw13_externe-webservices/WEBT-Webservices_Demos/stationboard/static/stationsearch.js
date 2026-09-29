async function loadStationsBy() {
    const station = document.getElementById('station');
    try {
        // create request content
        const url = 'https://transport.opendata.ch/v1/locations?query=' + station.value + '&type=station';
        const response = await fetch(url, {
            method: 'GET',
            signal: AbortSignal.timeout(5000)
        });            
        if (response.ok) {          
            const res = JSON.parse(await response.text());                
            autocomplete(station, res.stations);
        } else {
            throw Error('fetch failed with status: ' + response.status);
        }
    } catch (error) {
        alert('fetch failed: ' + error);
    }
}

// listen to input changes
window.addEventListener('load', function () {
    document.getElementById('station').addEventListener('keyup', loadStationsBy);
});
