import express from 'express';
const router = express.Router();

router.get('/', function (req, res) {
    res.type('text/html');

    let response;
    if (!('firstName' in req.query)) {
        response = "<p>Error: Request-Parameter 'firstName' not set</p>";
        res.status(400);
    } else if (req.query.firstName == '') {
        response = "<p>Error: Request-Parameter 'firstName' is empty</p>";
        res.status(400);
    } else {
        response = `
        <!DOCTYPE html>
        <html>
            <head>
                <title>Beispiel: Welcome</title>
                <meta charset="utf8">
                <link href="webt.css" rel="stylesheet">
            </head>
            <body>
                <h1>Beispiel: Welcome Resultat</h1>
                <p>Hallo ${req.query.firstName}</p>
            </body>
        </html>`;
    }

    res.send(response);
});

export default router;