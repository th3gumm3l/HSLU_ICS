package ch.hslu.exam.aufgabe4;

public class Demo {
    public static void main(String[] args) {
        RaumVerwaltung raumVerwaltung = new RaumVerwaltung();

        raumVerwaltung.bucheRaum(11);
        raumVerwaltung.bucheRaum(6);
        raumVerwaltung.bucheRaum(17);

        raumVerwaltung.getRaumListe();

        System.out.println("--------------------------");

        raumVerwaltung.raumFreigeben(603);

        raumVerwaltung.getRaumListe();
    }
}
