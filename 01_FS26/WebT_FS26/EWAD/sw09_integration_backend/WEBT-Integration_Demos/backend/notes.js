// Demonstration code only. Use a database in real applications:
// - notes.json should not be accessible from outside.
// - loading and storing entire data set per request is very ineffficient.

import express from 'express';
import fs from 'node:fs';
const router = express.Router();

// loads notes data from disk
function loadData() {
    if (fs.existsSync('notes.json')) {
        const data = fs.readFileSync('notes.json');
        return JSON.parse(data);
    } else {
        return { 
            notes: [],
            nextId: 1 
        };
    }
}

// stores all notes to notes.json
function storeData(data) {
    fs.writeFileSync('notes.json', JSON.stringify(data));
}

// small validator helper object to aggregate and report errors
function createValidator() {
    return {
        errors: [],
        validate: function(condition, message) {
            if (!condition) {
                this.errors.push(message);
            }
        },
        hasErrors: function() {
            return this.errors.length > 0;
        },
        sendErrors: function(res) {
            res.status(400);
            res.send({errors: this.errors});
        }
    }
}

// helper function to convert the message body to an
// object or return null if json is incorrect
function parseJson(req) {
    try {
        return JSON.parse(req.body.toString('utf-8'));
    } catch (e) {
        return null;
    }
}

// returns the index of a note corresponding to a certain index
function getNoteIndex(id) {
    let index = 0;
    for(const note of data.notes) {
        if (note.id == id) {
            return index;
        }
        ++index;
    }
    return null;
}

// REST function to return a note (GET method)
router.get('/:id', function (req, res) {
    res.type('application/json');

    // validate
    const validator = createValidator();
    const index = getNoteIndex(req.params.id);
    validator.validate(index != null, 'note "' + req.params.id + '" does not exist');
    if (validator.hasErrors()) {
        validator.sendErrors(res);
        return;
    }
    
    // send
    res.send(JSON.stringify(data.notes[index]));
});

// REST function to return index of all notes (GET method)
router.get('/', function (req, res) {
    res.type('application/json');

    // send
    const result = [];
    for(const note of data.notes) {
        result.push(note.title);
    }
    res.send(JSON.stringify(result));
});

// REST function to submit a new note (POST method)
router.post('/', function (req, res) {
    res.type('application/json');

    // validate
    const validator = createValidator();
    const note = parseJson(req);
    validator.validate(note != null, 'JSON-Document does not have valid syntax');
    if (validator.hasErrors()) {
        validator.sendErrors(res);
        return;
    }

    // get and assign next id
    note.id = data.nextId++;
    data.notes.push(note);

    // store and send
    storeData(data);
    res.status(201);
    res.send(JSON.stringify(note));
});

// REST function to replace a note from on disk (PUT method)
router.put('/:id', function (req, res) {
    res.type('application/json');

    // validate
    const validator = createValidator();
    const index = getNoteIndex(req.params.id);
    validator.validate(index != null, 'note "' + req.params.id + '" does not exist');
    const newNote = parseJson(req);
    validator.validate(newNote != null, 'JSON-Document does not have valid syntax');
    if (validator.hasErrors()) {
        validator.sendErrors(res);
        return;
    }

    // replace note
    newNote.id = req.params.id;
    data.notes[index] = newNote;

    // store and send
    storeData(data);
    res.send(JSON.stringify(newNote));
});

// REST function to delete a note from disk (DELETE method)
router.delete('/:id', function (req, res) {
    res.type('application/json');

    // validate
    const validator = createValidator();
    const index = getNoteIndex(req.params.id);
    validator.validate(index != null, 'note "' + req.params.id + '" does not exist');
    if (validator.hasErrors()) {
        validator.sendErrors(res);
        return;
    }

    // delete from memory structure
    const note = data.notes[index];
    data.notes.splice(index, 1);

    // store and send reponse
    storeData(data);
    res.send(JSON.stringify(note));
});

// on startup: load notes into memory
const data = loadData();

export default router;