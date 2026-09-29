import { WebSocketServer } from 'ws';

const wss = new WebSocketServer({ port: 8082 });

wss.on('connection', function(ws) {
  console.log('Client connected');

  ws.on('message', function(message) {
    console.log(`received: ${message}`);
    const string = message.toString();
    // TODO: Sende eine Push-Benachrichtigung zurück an den Client, wenn die Nachricht mit "Remind me later:" beginnt.
    // Die Benachrichtigung soll den Text enthalten, der nach "Remind me later:" kommt.
    // Die Benachrichtigung soll 10 Sekunden nach Empfang der Nachricht gesendet werden.
    // Ohne "Remind me later:"-Prefix wird die Antwort sofort gesendet.
    ws.send(`Echo to client: ${string}`);
  });

  ws.on('close', function() {
    console.log('Client disconnected');
  });
});

// EWAD prüft ob eine aus dem backend/-Ordner geladene Datei einen neuen REST-
// Endpunkt erzeugt und diesen exportiert. In dieser einfachen Implementation
// eines Chatservers ist dies nicht der Fall und EWAD produziert eine
// Fehlermeldung. Trotzdem funktioniert der Chatserver aber wie er soll.
//
// Mit einem zusätzlichen REST-API könnte der Chat-server aber nun noch um
// Funktionen erweitert werden um zum Beispiel eine Liste aller aktiven Benutzer
// zu erhalten, um Nutzer raus zu werfen oder sogar um eine ganze Benutzer-
// Verwaltung zu implementieren.
console.log("The chatserver doesn't install any express routes; the warning about the module not being loaded can be safely ignored")