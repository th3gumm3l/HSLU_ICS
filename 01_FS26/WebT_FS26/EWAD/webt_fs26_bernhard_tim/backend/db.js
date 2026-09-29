import { MongoClient } from 'mongodb';

// Verbindung definieren
const client = new MongoClient('mongodb://localhost:27017');

// Datenbank und Collection öffnen (werden automatisch erstellt falls nicht existent)
const db = client.db('rundenzeit_tracker');
const rundenCollection = db.collection('runden');

// Index auf 'fahrer' erstellen (schnelle Suche nach Fahrer)
await rundenCollection.createIndex({ fahrer: 1 });

export { rundenCollection };