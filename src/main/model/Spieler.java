package main.model;

import main.model.Karte;

import java.util.ArrayList;
import java.util.List;

public class Spieler {
    private List<Karte> karten;
    private int score;
    private boolean tschau = false;
    private boolean sepp = false;

    /**
     * Gibt den momentanen Score aus
     * @return Score
     */
    public int getScore() {
        return score;
    }

    /**
     * Gibt den SeppWert zurueck
     * @return Sepp
     */
    public boolean isSepp() {
        return sepp;
    }


    /**
     * Gibt TschauWert zuruek
     * @return Tschau
     */
    public boolean isTschau() {
        return tschau;
    }

    /**
     * Setzt den Score für den Spieler
     * @param score neuer Score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Fügt dem Spieler eine Karte auf die Hand
     * @param karte neue Karte
     */
    public void karteAufnehmen(Karte karte){
        karten.add(karte);
    }

    /**
     * Legt eine Karte anhand einens Indexes ab
     * @param index index der gewünschten Karte
     * @return Karten Objekt an angegebenen Index
     */
    public Karte karteAblegen(int index){
        return karten.get(index);
    }

    /**
     * Entfernt eine Karte aus dem Kartendeck
     * @param i index der Karte
     */
    public void removeKarte(int i){
        karten.remove(i);
    }

    /**
     * Aktiviert "Tschau" fuer eine Runde
     */
    public void tschauSagen(){
        tschau = true;
    }

    /**
     * Aktiviert "Sepp" fuer eine Runde
     */
    public void seppSagen(){
        sepp = true;
    }

    /**
     * Constructor fuer einen Spieler
     * @param kartenHand KartenHand fuer den Spielstart
     */
    public Spieler(ArrayList<Karte> kartenHand){
        this.karten = kartenHand;
    }


    /**
     * Gibt Karten auf der Hand zurück
     * @return Kartenhand
     */
    public List<Karte> getKarten() {
        return karten;
    }


    /**
     * Setter fuer Sepp
     * @param sepp neuer Wert
     */
    public void setSepp(boolean sepp) {
        this.sepp = sepp;
    }


    /**
     * Setter fuer Tschau
     * @param tschau neuer Wert
     */
    public void setTschau(boolean tschau) {
        this.tschau = tschau;
    }

    /**
     * Resetet die SpielerHand
     */
    public void reset(){
        karten.clear();
    }

    /**
     * Set die Kartenhand
     * @param karten neue KartenHand
     */
    public void setKarten(List<Karte> karten) {
        this.karten = karten;
    }
}
