import { WebSocketServer } from 'ws';

const server = new WebSocketServer({
    port: 8081
});

// Sammlung aller verbundenen Nutzer in form von offenen sockets
const clients = new Set();

server.on('connection', (socket) => {
    // Bei einer neuen Verbindung wird der entsprechende socket in den
    // 'clients' gespeichert.
    clients.add(socket);
    // Der WebSocket server merkt sich den Benutzernamen für jede Verbindung
    // im neu erzeugten Property 'username'. Dieses Property wird bis zum
    // Empfangen des tatsächlichen Benutzernamens auf "anonymous" gesetzt.
    socket.username = "anonymous";

    socket.on('message', (message) => {
        // Die empfangenen Nachrichten sind im JSON format und enthalten
        // entweder das Property 'username' um den Benutzernamen für diese
        // Verbindung zu setzen oder das Property 'message' um eine neue
        // Nachricht an alle aktiven Benutzer zu schicken.
        const msg = JSON.parse(message);

        if ('username' in msg) {
            socket.username = msg.username;
            clients.forEach((client) => {
                if (client.readyState === WebSocket.OPEN) {
                    client.send(`<i>${msg.username} has joined the chat</i>`);
                }
            });
        }
        else {
            const username = socket.username;
            // Die empfangene Nachricht wird an alle noch aktiven Benutzer
            // verschickt.
            clients.forEach((client) => {
                if (client.readyState === WebSocket.OPEN) {
                    client.send(`<b>&lt;${username}&gt;</b> ${msg.message}`);
                }
            });
        }
    });

    socket.on('close', () => {
        const username = socket.username;
        // Wenn die Socket-Verbindung eines Nutzers geschlossen wird, löschen
        // wir das entsprechende Socket aus der Liste und informieren alle noch
        // aktiven Benutzer.
        clients.delete(socket);
        clients.forEach((client) => {
            if (client.readyState === WebSocket.OPEN) {
                client.send(`<i>${username} has left the chat</>`);
            }
        });
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