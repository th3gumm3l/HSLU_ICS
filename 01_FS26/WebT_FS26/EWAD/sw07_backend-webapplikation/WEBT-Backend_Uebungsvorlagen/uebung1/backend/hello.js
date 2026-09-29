import express from 'express';
const router = express.Router();

router.get('/', function(req, res) {
    res.type('text/html');

    if ('name' in req.query) {
        let name = req.query.name;
        res.send(`Hallo ${name}!`);   // TODO 2: Template literal
    } else {
        res.status(400).send("Hallo unbekannte Person");  // TODO 1: Fehlerbehandlung
    }
});

export default router;