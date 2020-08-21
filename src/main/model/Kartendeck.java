package main.model;

import java.util.*;

/**
 * Kuemmert sich um den Aufnahme und Ablage stapel. Generiert ebenfalls Karten und mischt diese.
 */
public class Kartendeck {
    private List<Karte> ablagestapel = new ArrayList<>();
    private List<Karte> aufnehmstapel = new ArrayList<>();

    public void karteAblegen(Karte karte){
        ablagestapel.add(karte);
    }

    /**
     * Gibt die oberste Karte des Aufnahmestapels aus und entfernt diese aus dem Stapel.
     * @return Die gezogene Karte
     */
    public Karte karteZiehen(){
        if(aufnehmstapel.size()>1){
            Karte karte = aufnehmstapel.get(1);
            aufnehmstapel.remove(karte);
            return karte;
        }else{
            mischen();
            return karteZiehen();
        }
    }

    /**
     * Setzt den Ablagestapel auf eine Karte zurück und durchmischt den Aufnahmestapel
     */
    private void mischen(){
        if(ablagestapel.size()>4){

            Karte top = ablagestapel.get(ablagestapel.size());
            ablagestapel.remove(ablagestapel.size());
            for (Karte karte:ablagestapel) {
                aufnehmstapel.add(karte);
            }
            ablagestapel = new ArrayList<>();
            ablagestapel.add(top);
        }
        if(ablagestapel.size()==0){
            ablagestapel.add(karteZiehen());
        }
        Collections.shuffle(aufnehmstapel);
    }

    /**
     * Erstellt Kartenobjekte anhand von Farbe und Werte-Enmum
     */
    public void erstellenKarten(){
        for(Farbe farbe : Farbe.values()){
            for(Werte werte : Werte.values()){
                aufnehmstapel.add(new Karte(farbe, werte));
            }
        }
        mischen();
    }

    /**
     * Erstellt eine ArrayList von Karten und gibt diese zurück
     * @param kartenAnzahl Anzahl an Karten fuer die ArrayList
     * @return ArrayList bestehend aus Karten
     */
    public ArrayList<Karte> kartenHandErstellen(int kartenAnzahl){
        ArrayList<Karte> kartenHand = new ArrayList<>();
        for(int i = 0; i<kartenAnzahl; i++){
            kartenHand.add(karteZiehen());
        }
        return kartenHand;
    }

    /**
     * Gibt die oberste Karte des AblageStapels zurücl
     * @return Referenz zur Karte
     */
    public Karte getObersteKarte(){
        return ablagestapel.get(ablagestapel.size()-1);
    }


    /**
     * Setzt alle Stapel zurück.
     */
    public void reset(){
        ablagestapel.clear();
        aufnehmstapel.clear();
        erstellenKarten();
    }



}
