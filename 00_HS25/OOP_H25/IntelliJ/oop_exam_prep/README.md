# Java Projekt für Examen (eMEP) - HS25

## Zweck
Dieses Projekt wird für elektronische Examen (eMEP) genutzt.
Es basiert auf Apache Maven und dem aus den Übungen bereits bekannten oop_maven_template.
Für die Bearbeitung wir NetBeans empfohlen (Projekt kann direkt geöffnet werden), sie können 
aber auch jede andere IDE, z.B. Eclipse JDT (importieren!), IntelliJ oder Visual Studio Code 
verwenden. Wichtig ist, dass Sie für die Abgabe unbedingt den Buildprozess über Maven starten
und danach prüfen, ob er fehlerfrei beendet und eine (jeweils neue) ZIP-Datei erzeugt wurde.

## Nutzung
Die einfachste Nutzung ist in der Shell gegeben. Führen Sie jeweils vor einer 
Abgabe ein `mvn clean package` aus. Beachten Sie dabei, dass der Build __fehlerfrei__ abgeschlossen
wird und mit einem __BUILD SUCCESS__ endet - ansonsten korrigieren Sie Ihr Projekt!
Nach dem erfolgreichen Build finden Sie im Verzeichnis ./target eine ZIP-Datei 
(z.B. oop_exam-HS25-xxx.zip), welche Sie direkt für die Abgabe im ILIAS verwenden.
In den jeweiligen IDEs starten Sie den Maven-Build typisch auf dem pom.xml (clean package).
Für NetBeans ist zur Vereinfachung eine Buildkonfig bereits vorkonfiguriert.

## Verwendung mit Docker
Sollten Sie Docker auf ihrem System installiert haben, können Sie über die im Projekt
enthaltenen Scripts devshell.cmd (für Windows) oder devshell.sh (für Mac/Linux) einen
Shell-Container starten, welcher die benötigten Java- und Maven-Binaries enthält. 
Somit können Sie das Projekt IDE-Unabhängig auf jeden Fall in der Shell bauen.

## Enhaltene Libraries (Dependencies)
* LogBack over SLF4J - https://logback.qos.ch/ over https://slf4j.org/
* JUnit 6 - https://junit.org/
* AssertJ - https://assertj.github.io/doc/
* EqualsVerifier - https://jqno.nl/equalsverifier/