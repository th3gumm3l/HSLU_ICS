function diagrammZeichnen(stats) {
    const canvas = document.getElementById('rundenCanvas');
    const ctx = canvas.getContext('2d');
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    if (stats.length === 0) return;

    const padding = { top: 30, bottom: 50, left: 10, right: 10 };
    const zeichenBreite = canvas.width - padding.left - padding.right;
    const zeichenHoehe = canvas.height - padding.top - padding.bottom;
    const balkenBreite = Math.min(60, (zeichenBreite / stats.length) - 10);
    const abstand = (zeichenBreite - balkenBreite * stats.length) / (stats.length + 1);
    const maxWert = Math.max(...stats.map(s => s.durchschnitt));

    ctx.fillStyle = '#333';
    ctx.font = '12px Segoe UI';
    ctx.fillText('Ø Rundenzeit pro Fahrer (Min)', padding.left, 18);

    stats.forEach(function(eintrag, i) {
        const x = padding.left + abstand + i * (balkenBreite + abstand);
        const balkenHoehe = (eintrag.durchschnitt / maxWert) * zeichenHoehe;
        const y = padding.top + zeichenHoehe - balkenHoehe;

        ctx.fillStyle = '#1a1a2e';
        ctx.fillRect(x, y, balkenBreite, balkenHoehe);
        ctx.strokeStyle = '#e63946';
        ctx.strokeRect(x, y, balkenBreite, balkenHoehe);

        ctx.fillStyle = '#333';
        ctx.font = '11px Segoe UI';
        ctx.fillText(eintrag.durchschnitt, x + 2, y - 5);
        ctx.fillText(eintrag.fahrer.substring(0, 8), x, padding.top + zeichenHoehe + 16);
    });
}

async function diagrammAktualisieren() {
    try {
        const response = await fetch('/rundenzeit/stats', { method: 'GET', signal: AbortSignal.timeout(5000) });
        if (!response.ok) return;
        const data = JSON.parse(await response.text());
        diagrammZeichnen(data.stats);
    } catch (error) {
        console.error('Diagramm konnte nicht geladen werden:', error);
    }
}

diagrammAktualisieren();