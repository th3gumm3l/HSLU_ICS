const touched = { fahrer: false, streckenbedingung: false, rundenzeit: false };

const gespeicherterFahrer = localStorage.getItem('letzterFahrer');
if (gespeicherterFahrer) {
    document.getElementById('fahrer').value = gespeicherterFahrer;
}

const streckenbedingungText = { '1': 'Trocken', '2': 'Feucht', '3': 'Nass' };

function tabelleZeileHinzufuegen(runde) {
    const tbody = document.getElementById('tabelleBody');
    const zeile = document.createElement('tr');
    zeile.setAttribute('data-id', runde.id);
    const tdFahrer = document.createElement('td');
    tdFahrer.innerText = runde.fahrer;
    const tdStrecke = document.createElement('td');
    tdStrecke.innerText = streckenbedingungText[runde.streckenbedingung];
    const tdZeit = document.createElement('td');
    tdZeit.innerText = runde.rundenzeit;
    const tdAktion = document.createElement('td');
    const btnLoeschen = document.createElement('button');
    btnLoeschen.innerText = 'Löschen';
    btnLoeschen.className = 'w3-button w3-red w3-small';
    btnLoeschen.onclick = function() { rundeLoeschen(zeile, runde.id); };
    tdAktion.appendChild(btnLoeschen);
    zeile.appendChild(tdFahrer);
    zeile.appendChild(tdStrecke);
    zeile.appendChild(tdZeit);
    zeile.appendChild(tdAktion);
    tbody.appendChild(zeile);
}

async function rundeLoeschen(zeile, id) {
    try {
        const response = await fetch('/rundenzeit/' + id, {
            method: 'DELETE',
            signal: AbortSignal.timeout(5000)
        });
        if (!response.ok) throw('HTTP Status: ' + response.status + ' ' + response.statusText);
        zeile.remove();
        await aktualisiereDurchschnittNachLoeschen();
        diagrammAktualisieren();
    } catch (error) {
        alert('Fehler beim Löschen: ' + error);
    }
}

async function aktualisiereDurchschnittNachLoeschen() {
    try {
        const response = await fetch('/rundenzeit', { method: 'GET', signal: AbortSignal.timeout(5000) });
        if (!response.ok) return;
        const data = JSON.parse(await response.text());
        if (data.runden.length > 0) {
            const summe = data.runden.reduce((s, r) => s + r.rundenzeit, 0);
            const durchschnitt = Math.round((summe / data.runden.length) * 100) / 100;
            document.getElementById('durchschnittAnzeige').innerText = 'Ø Durchschnitt aller Runden: ' + durchschnitt + ' Min';
        } else {
            document.getElementById('durchschnittAnzeige').innerText = '';
        }
    } catch (error) {
        console.error('Durchschnitt konnte nicht aktualisiert werden:', error);
    }
}

async function tabelleBeimLadenBefuellen() {
    try {
        const response = await fetch('/rundenzeit', { method: 'GET', signal: AbortSignal.timeout(5000) });
        if (!response.ok) return;
        const data = JSON.parse(await response.text());
        for (const runde of data.runden) {
            tabelleZeileHinzufuegen(runde);
        }
        if (data.runden.length > 0) {
            const summe = data.runden.reduce((s, r) => s + r.rundenzeit, 0);
            const durchschnitt = Math.round((summe / data.runden.length) * 100) / 100;
            document.getElementById('durchschnittAnzeige').innerText = 'Ø Durchschnitt aller Runden: ' + durchschnitt + ' Min';
        }
    } catch (error) {
        console.error('Tabelle konnte nicht geladen werden:', error);
    }
}

tabelleBeimLadenBefuellen();

function validateFahrer() {
    const error = document.getElementById('errorFahrer');
    error.innerText = (touched.fahrer && document.getElementById('fahrer').value.trim() === '')
        ? 'Fahrer darf nicht leer sein.' : '';
}

function validateStreckenbedingung() {
    const error = document.getElementById('errorStreckenbedingung');
    error.innerText = (touched.streckenbedingung && !['1', '2', '3'].includes(document.getElementById('streckenbedingung').value))
        ? 'Bitte eine Streckenbedingung wählen.' : '';
}

function validateRundenzeit() {
    const val = document.getElementById('rundenzeit').value;
    const error = document.getElementById('errorRundenzeit');
    error.innerText = (touched.rundenzeit && (val === '' || isNaN(val) || Number(val) <= 0))
        ? 'Rundenzeit muss eine positive Zahl sein.' : '';
}

function onInputFahrer() { touched.fahrer = true; validateFahrer(); }
function onInputStreckenbedingung() { touched.streckenbedingung = true; validateStreckenbedingung(); }
function onInputRundenzeit() { touched.rundenzeit = true; validateRundenzeit(); }

function clearValidationErrors() {
    document.getElementById('errorFahrer').innerText = '';
    document.getElementById('errorStreckenbedingung').innerText = '';
    document.getElementById('errorRundenzeit').innerText = '';
}

function validateAll() {
    touched.fahrer = true; touched.streckenbedingung = true; touched.rundenzeit = true;
    validateFahrer(); validateStreckenbedingung(); validateRundenzeit();
}

function hasValidationErrors() {
    return document.getElementById('errorFahrer').innerText !== ''
        || document.getElementById('errorStreckenbedingung').innerText !== ''
        || document.getElementById('errorRundenzeit').innerText !== '';
}

async function absenden() {
    clearValidationErrors();
    validateAll();
    if (hasValidationErrors()) return;

    const fahrer = document.getElementById('fahrer').value;
    const streckenbedingung = document.getElementById('streckenbedingung').value;
    const rundenzeit = document.getElementById('rundenzeit').value;

    try {
        const response = await fetch('/rundenzeit?fahrer=' + fahrer, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ streckenbedingung: streckenbedingung, rundenzeit: rundenzeit }),
            signal: AbortSignal.timeout(5000)
        });
        if (!response.ok) throw('HTTP Status: ' + response.status + ' ' + response.statusText);

        const result = JSON.parse(await response.text());

        localStorage.setItem('letzterFahrer', fahrer);
        diagrammAktualisieren();
        document.getElementById('durchschnittAnzeige').innerText = 'Ø Durchschnitt aller Runden: ' + result.durchschnitt + ' Min';
        tabelleZeileHinzufuegen(result);
    } catch (error) {
        alert('Fehler: ' + error);
    }
}