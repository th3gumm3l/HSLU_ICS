import express from 'express';
const router = express.Router();

router.get('/', function (req, res) {
   // json encode
   let person = { 
      name:  'Muster',
      vorname: 'Hans',
      adresse: 'Musterstr. 23' 
   };
   let personJSON = JSON.stringify(person);

   let output = 'Person: ' + personJSON;
   output += '\n\n';

   // json decode
   personJSON = `{
      "name":"Muster",
      "vorname":"Hans",
      "adresse":"Musterstr. 23"
   }`;

   person = JSON.parse(personJSON);

   if (person) {
      output += 'Name: ' + person['name'] + ' / Vorname: ' + person['vorname'] + ' / Adresse: ' + person['adresse'];
   } else {
      output += 'error...';
   }

   res.type('text/plain');
   res.send(output);
});

export default router;