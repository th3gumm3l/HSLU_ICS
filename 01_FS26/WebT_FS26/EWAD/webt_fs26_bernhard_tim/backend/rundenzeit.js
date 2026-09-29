import express from 'express';
import { rundenCollection } from './db.js';
import { ObjectId } from 'mongodb';

const router = express.Router();

router.post('/', async function (req, res) {
    res.type('application/json');
    try {
        const fahrer = req.query.fahrer;
        const body = JSON.parse(req.body.toString('utf-8'));
        const streckenbedingung = body.streckenbedingung;
        const rundenzeit = body.rundenzeit;

        if (!fahrer || fahrer.trim() == '') {
            res.status(400).send(JSON.stringify({ error: 'Fahrer fehlt' }));
            return;
        }
        if (streckenbedingung == null || !['1', '2', '3'].includes(String(streckenbedingung))) {
            res.status(400).send(JSON.stringify({ error: 'Ungültige Streckenbedingung' }));
            return;
        }
        if (rundenzeit == null || rundenzeit == '' || isNaN(rundenzeit) || Number(rundenzeit) <= 0) {
            res.status(400).send(JSON.stringify({ error: 'Ungültige Rundenzeit' }));
            return;
        }

        const insertResult = await rundenCollection.insertOne({
            fahrer: fahrer,
            streckenbedingung: streckenbedingung,
            rundenzeit: Number(rundenzeit)
        });

        const alleRunden = await rundenCollection.find({}).toArray();
        const summe = alleRunden.reduce((s, r) => s + r.rundenzeit, 0);
        const durchschnitt = Math.round((summe / alleRunden.length) * 100) / 100;

        res.send(JSON.stringify({
            id: insertResult.insertedId,
            fahrer: fahrer,
            streckenbedingung: streckenbedingung,
            rundenzeit: Number(rundenzeit),
            durchschnitt: durchschnitt
        }));
    } catch (error) {
        console.error(error);
        res.status(500).send(JSON.stringify({ error: 'Interner Serverfehler' }));
    }
});

router.get('/', async function (req, res) {
    res.type('application/json');
    try {
        const alleRunden = await rundenCollection.find({}).toArray();
        const runden = alleRunden.map(r => ({
            id: r._id,
            fahrer: r.fahrer,
            streckenbedingung: r.streckenbedingung,
            rundenzeit: r.rundenzeit
        }));
        res.send(JSON.stringify({ runden: runden }));
    } catch (error) {
        console.error(error);
        res.status(500).send(JSON.stringify({ error: 'Interner Serverfehler' }));
    }
});

router.get('/stats', async function (req, res) {
    res.type('application/json');
    try {
        const stats = await rundenCollection.aggregate([
            {
                $group: {
                    _id: '$fahrer',
                    durchschnitt: { $avg: '$rundenzeit' }
                }
            },
            {
                $project: {
                    _id: 0,
                    fahrer: '$_id',
                    durchschnitt: { $round: ['$durchschnitt', 2] }
                }
            }
        ]).toArray();
        res.send(JSON.stringify({ stats: stats }));
    } catch (error) {
        console.error(error);
        res.status(500).send(JSON.stringify({ error: 'Interner Serverfehler' }));
    }
});

router.delete('/:id', async function (req, res) {
    res.type('application/json');
    try {
        const id = req.params.id;
        if (!ObjectId.isValid(id)) {
            res.status(400).send(JSON.stringify({ error: 'Ungültige ID' }));
            return;
        }
        await rundenCollection.deleteOne({ _id: new ObjectId(id) });
        res.send(JSON.stringify({ message: 'Runde gelöscht' }));
    } catch (error) {
        console.error(error);
        res.status(500).send(JSON.stringify({ error: 'Interner Serverfehler' }));
    }
});

export default router;