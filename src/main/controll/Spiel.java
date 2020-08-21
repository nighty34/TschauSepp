package main.controll;

import main.model.Einstellungen;
import main.model.Karte;
import main.model.Kartendeck;
import main.model.Spieler;

import java.util.ArrayList;
import java.util.List;

/**
 * Der Controller fuer die SpielGUI. Regelt denn Spielverlauf
 */
public class Spiel {
    private List<Spieler> spieler = new ArrayList<>();
    private Kartendeck kartendeck;
    private Einstellungen einstellungen;
    private int rotation;

    /**
     * Gibt die aktuelle Rotation zurueck (0 = unten, 1 = links, 2 = oben, 3 = rechts)
     * @return aktuelle Rotation
     */
    public int getRotation() {
        return rotation;
    }

    public void iterateRotation() {
        rotation++;
        if(rotation>=einstellungen.getAnzahlSpieler()){
            rotation = 0;
        }
    }

    /**
     * Methode zum hinzufügen eines Spielers zur Spielrotation
     * @param spieler Neuer Spieler
     */
    public void addSpieler(Spieler spieler){
        this.spieler.add(spieler);
    }

    /**
     * Spieler-Getter für einen Spieler
     * @param index Index des Spielers
     * @return Referenz zum Spieler
     */
    public Spieler getSpieler(int index){
        try {
            return spieler.get(index);
        }catch (Exception ex){
            return null;
        }
    }

    /**
     * Entfernt einen Spieler
     * @param spieler Referenz zum zu entfernenden Spieler
     */
    public void removeSpieler(Spieler spieler){
        this.spieler.remove(spieler);
    }

    /**
     * Entfernt einen Spieler
     * @param index Index des zu entfernenden Spieler
     */
    public void removeSpieler(int index){
        spieler.remove(index);
    }

    /**
     * Kartendeck-Setter
     * @param deck Die neue Kartendeck Referenz
     */
    public void setKartendeck(Kartendeck deck){
        this.kartendeck = deck;
    }

    /**
     * Kartendeck-Getter
     * @return Die gespeicherte Kartendeck-Referenz
     */
    public Kartendeck getKartendeck(){
        return kartendeck;
    }

    /**
     * Einstellung-Getter
     * @return Die gespeicherte Einstellungs-Referenz
     */
    public Einstellungen getEinstellungen() {
        return einstellungen;
    }

    /**
     * Methode zum ausspielen einer Karte. Der Zug wird auf seine validität geprueft und gibt dementsprechend eine Antwort. Karte wird auf Farbe und Wert getestet.
     * @param karte die zum ausspielen gewünschte Karte.
     * @return Gibt einen Boolean je nachdem ob der Zug gültig ist zurück.
     */
    public boolean karteAusspielen(Karte karte){
        if(karte.getFarbe().equals(getAktiveKarte().getFarbe()) || karte.getWerte().equals(getAktiveKarte().getWerte())) {
            kartendeck.karteAblegen(karte);
            return true;
        }
        return  false;
    }

    /**
     * Einstellung-Setter
     * @param einstellungen Neue Referenz zum Einstellungsobjekt
     */
    public void setEinstellungen(Einstellungen einstellungen){
        this.einstellungen = einstellungen;
    }

    /**
     * Spielstart Funtkion. Wertet das Einstellungsobjekt aus und erstellt diesem entsprechend ein Spiel.
     */
    public void spielStarten(){
        for(int i = 0; i<einstellungen.getAnzahlSpieler(); i++){
            spieler.add(new Spieler(kartendeck.kartenHandErstellen(einstellungen.getAnzahlStartKarten())));
        }

        kartendeck.erstellenKarten();
    }

    /**
     * Gibt die oberste Karte auf dem Ablagestapel zurueck
     * @return Die oberste Karte auf dem Ablagestapel
     */
    public Karte getAktiveKarte() {
        return kartendeck.getObersteKarte();
    }


    /**
     * Testet ob ein Spieler gewonnen hat
     * @param spieler Zu testender Spieler
     */
    public boolean TestGewinner(Spieler spieler){
        if(spieler.getScore()>einstellungen.getZielWert()){
            return true;
        }
        kartendeck.reset();

        for (Spieler sp:this.spieler) {
            sp.reset();
            sp.setKarten(kartendeck.kartenHandErstellen(einstellungen.getAnzahlStartKarten()));
        }
        return false;
    }

    /**
     * Gibt den Index eines Spielers in Spieler zurueck
     * @param spieler Nachgefragtes Objekt
     * @return Spieler Index
     */
    public int getIndex(Spieler spieler){
        return this.spieler.indexOf(spieler);
    }
}
