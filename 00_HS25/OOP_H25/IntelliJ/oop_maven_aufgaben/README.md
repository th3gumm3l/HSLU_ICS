# Java Projekt-Template fuer OOP/PLAB, AD, VSK, SWDA und DEVOPS

## Zweck
Dieses Projekt dient in den Modulen OOP/PLAB, AD, VSK, SWDA und DEVOPS als 
Vorlage fuer Java-Projekte. Es nutzt einen auf 
[Apache Maven](https://maven.apache.org/) basierenden (und somit IDE-unabhaengigen)
Build und definiert die dafuer notwendige, standardisierte Verzeichnisstruktur.

## Distribution / Download
Die jeweils aktuelle Version steht auf [SWITCHdrive](https://drive.switch.ch/) zur Verfuegung:
https://drive.switch.ch/index.php/s/nz0UdWDlSQ9ogDX?path=%2F15_maven_template

## Verwendung
Das Projekt kann einfach kopiert und umbenannt werden. In NetBeans, IntelliJ und
Visual Studio Code kann es **direkt** geoeffnet werden, in Eclipse  ist
ein **Import** des Projektes (als `Existing Maven Project`) notwendig.
Die beste (weil vollstaendige) Integration ist in NetBeans gegeben, in allen
anderen IDEs kann der Maven Build manuell ausgeloest werden. Nach dem Kopieren
in ein neues Verzeichnis (fuer ein neues Projekt) wird sehr empfohlen:
* den Namen des Verzeichnisses,
* den Namen des Projektes in der IDE, sowie
* die `ArtifactID` und `Name` (zwei Elemente im `pom.xml`)
zwecks Vereinfachung und Konsistenz **synchron** zu halten.
In NetBeans erreichen Sie das z.B. durch ein einfaches "Rename"-Refactoring.
Sie koennen es aber auch von Hand mit einem Texteditor (z.B. notepad) erledigen.

Hinweis: Sobald Sie eigene Klassen und Testfaelle ergaenzt haben, macht es Sinn
die fuer Demozwecke enthaltenen Demo-Klassen entfernen.

## Enthaltene Libraries (Dependencies)
* Simple Logging Facade (SLF4J) - https://www.slf4j.org/
* LogBack - https://logback.qos.ch/ (Default)
* JSpecify - https://jspecify.dev/

## Enthaltene Test-Libraries (Test Dependencies)
* AssertJ - https://assertj.github.io/doc/
* EqualsVerifier - https://jqno.nl/equalsverifier/
* Console Captor - https://github.com/Hakky54/console-captor
* JUnit 6 - https://junit.org/

## Integrierte Analysewerkzeuge (Code Qualitaet)
* Checkstyle - https://checkstyle.sourceforge.io/
* PMD - https://pmd.github.io/
* JaCoCo - https://www.eclemma.org/jacoco/
* Spotbugs - https://spotbugs.github.io/

## Weitere Integrationen (benoetigen ggf. Konfiguration/Account)
* [AsciiDoctor-Plugin](https://asciidoctor.org/) fuer [AsciiDoc](https://asciidoc.org/)
* Deployment in Package Repository (Maven Repo) von http://gitlab.com vorbereitet
* Dockerfile fuer Bau eines [Docker-Images](https://www.docker.com/)
* [Fabric/Docker-Plugin](https://dmp.fabric8.io/) fuer Build und Deploy auf [DockerHub](https://hub.docker.com/)
* [GitLab CI/CD](https://docs.gitlab.com/ee/ci/) (.gitlab-ci.yml) inkl. Coverage-Auswertung fuer Java.
* [JIB-Plugin](https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin) (Google) fuer Build und Deploy auf DockerHub (alternative).

Feedback und Fehlermeldungen willkommen: roland.gisler@hslu.ch
