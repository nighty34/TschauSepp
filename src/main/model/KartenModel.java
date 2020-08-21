package main.model;

import javax.swing.*;
import java.util.Vector;

/**
 * Modell fuer SpielerKarten.
 */
public class KartenModel extends DefaultListModel<Karte> {
    private Vector<Karte> karten;

    /**
     * Constructor
     */
    public KartenModel(){
        karten = new Vector<>();
    }

    /**
     * Gibt die Groesse der Collection zurueck
     * @return Grösse
     */
    @Override
    public int getSize(){
        return karten.size();
    }

    /**
     * Fuegt ein Object zu der Collection hinzu
     * @param karte Neues Objekt
     */
    @Override
    public void addElement(Karte karte){
        karten.add(karte);
        fireContentsChanged(this, 0, getSize());
    }

    /**
     * Entfernt ein Objekt und gibt gleichzeitig dieses zurueck
     * @param index Index des Objekts
     * @return Referent zu dem entfernten Objekt
     */
    @Override
    public Karte remove(int index){
        Karte karte = karten.remove(index);
        fireContentsChanged(this, 0, getSize());
        return karte;
    }

    /**
     * Entfernt ein Element
     * @param object Das zu entfernende Objekt
     * @return gibt an ob Aktion erfolgreich war
     */
    @Override
    public boolean removeElement(Object object){
        boolean happen = karten.removeElement(object);
        fireContentsChanged(this, 0, getSize());
        return happen;

    }

    /**
     * Givt Objekt an index zurueck
     * @param index Index des Objekts
     * @return Gewuenschtes Objekt
     */
    @Override
    public Karte getElementAt(int index) {
        return karten.get(index);
    }

    /**
     * ToString Funktion
     * @return Objekt als String
     */
    @Override
    public String toString() {
        fireContentsChanged(this, 0, getSize());
        return karten.toString();
    }

    /**
     * Macht die Collection leer.
     */
    @Override
    public void clear() {
        karten.clear();
        fireContentsChanged(this, 0, getSize());
    }
}
