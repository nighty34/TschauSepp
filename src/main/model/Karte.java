package main.model;

/**
 * Katen Objekt. Setzt sich zusammen aus einer Farbe und einem Wert
 */
public class Karte {
    private Farbe farbe;
    private Werte werte;

    /**
     * Constructor fuer eine Karte
     * @param farbe Farbe der Karte
     * @param werte Wert der Karte
     */
    public Karte(Farbe farbe, Werte werte){
        this.farbe = farbe;
        this.werte = werte;
    }

    /**
     * Gibt die Farbe der Karte zurueck
     * @return Kartenfarbe
     */
    public Farbe getFarbe() {
        return farbe;
    }

    /**
     * Gibt den Wert der Karte zurueck
     * @return Wert der Karte
     */
    public Werte getWerte() {
        return werte;
    }
}
