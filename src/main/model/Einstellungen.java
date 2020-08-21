package main.model;

/**
 * Modellklasse fuer die Einstellungen
 */
public class Einstellungen {
    private int zielWert = 200;
    private int anzahlSpieler = 4;
    private boolean spezialKarten = false;
    private int anzahlStartKarten = 7;


    /**
     * Gibt den ZielWert (Wert benoetigt um zu gewinnen) zurueck
     * @return Zielwert
     */
    public int getZielWert() {
        return zielWert;
    }

    /**
     * Setzt den ZielWert
     * @param zielWert neuer ZielWert
     */
    public void setZielWert(int zielWert) {
        this.zielWert = zielWert;
    }


    /**
     * Gibt die Anzahl Spieler zurueck
     * @return Anzahl Spieler
     */
    public int getAnzahlSpieler() {
        return anzahlSpieler;
    }

    /**
     * Setzt die Anzahl Spieler
     * @param anzahlSpieler
     */
    public void setAnzahlSpieler(int anzahlSpieler) {
        this.anzahlSpieler = anzahlSpieler;
    }

    /**
     * Gibt die Azahl an Startkarten zurueck
     * @return AnzahlStartkarten
     */
    public int getAnzahlStartKarten() {
        return anzahlStartKarten;
    }
}
