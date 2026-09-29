#include <stdio.h>

// a) Funktion: verschiebt einen Grossbuchstaben um 3 Stellen im Alphabet.
//    Bei Überlauf (X, Y, Z) beginnt die Zählung wieder bei A.
char shift3(char c) {
    return 'A' + (c - 'A' + 3) % 26;
}

int main(void) {
    char buchstabe = 'A';
    char verschluesselt = shift3(buchstabe);
    printf("%c -> %c\n", buchstabe, verschluesselt); // A -> D

    // Test mit Überlauf
    buchstabe = 'X';
    printf("%c -> %c\n", buchstabe, shift3(buchstabe)); // X -> A
    buchstabe = 'Z';
    printf("%c -> %c\n", buchstabe, shift3(buchstabe)); // Z -> C

    return 0;
}

/*
b) Was wird noch benötigt, um die ganze Aufgabe zu lösen?

   - Eingabe vom Benutzer einlesen (z.B. mit scanf("%c", ...)) statt
     einer fest codierten Variable.
   - Variable Verschiebung: nicht nur +3, sondern beliebiger Wert
     als zweiter Funktionsparameter (int shift).
   - Behandlung von Klein- UND Grossbuchstaben (Bereich 'a'..'z'
     zusätzlich zu 'A'..'Z'); Nicht-Buchstaben unverändert lassen.
   - Verarbeitung ganzer Zeichenketten (Strings / char-Arrays) statt
     nur eines einzelnen Zeichens -> Schleife über alle Zeichen.
   - Eventuell eine Entschlüsselungs-Funktion (Verschiebung rückwärts).
   - Konzepte, die dafür noch nötig sind:
       * Schleifen (for / while) zum Durchlaufen eines Strings
       * Arrays bzw. Pointer auf char (char* / char[])
       * Bedingungen (if / else) zur Unterscheidung der Zeichenarten
       * Funktionen mit mehreren Parametern
*/