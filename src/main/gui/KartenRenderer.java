package main.gui;

import main.model.Karte;
import main.model.Spieler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

/**
 * Kartenrenderer fuer die SpielGUI
 * Stellt alle Karten in den Spielerhaenden dar
 */
public class KartenRenderer extends JPanel implements ListCellRenderer<Karte> {

    private boolean isHidden = false;
    private static final String pathHidden = "RueckSeite.png";
    private Spieler spieler;

    private JLabel label = new JLabel();

    /**
     * Contructor fuer den KartenRenderer
     * @param hidden Sind die Karten zu sehen
     * @param spieler Zugehoeriger Spieler
     */
    public KartenRenderer(boolean hidden, Spieler spieler){
        this.isHidden = hidden;
        this.spieler = spieler;
    }

    /**
     * Aendert die Anzeige zu entweder der Rueck bzw zur Vordersete der Karte
     * @param newStatus Neuer Status (ja = Rueckseite, nein = Front)
     */
    public void changeHidden(boolean newStatus){
        this.isHidden = newStatus;
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Karte> list,
                                                  Karte value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        if(!isHidden) {
            setSize(20, 20);
            try {
                InputStream is = Spieler.class.getClassLoader().getResourceAsStream(value.getFarbe().toString() + "_" + value.getWerte().toString() + ".png");

                BufferedImage img = ImageIO.read(is);
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH));
                label.setIcon(imageIcon);
            } catch (Exception ex) {
                label.setText(value.getFarbe().toString() + " " + value.getWerte().toString());
            }
        }else{
            try {
                InputStream is = Spieler.class.getClassLoader().getResourceAsStream(pathHidden);
                BufferedImage img = ImageIO.read(is);
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH));
                label.setIcon(imageIcon);

            } catch (Exception ex) {
                label.setText("Hidden");
            }
        }
        setLayout(new BorderLayout());


        add(label, BorderLayout.CENTER);

        setVisible(true);
        return this;
    }

    /**
     * Gibt den momentanen AnzeigeStatus zurueck
     * @return ist versteckt
     */
    public boolean getIsHidden(){
        return isHidden;
    }

    /**
     * Gibt den zugehoerigen Spieler zurueck
     * @return Spieler Referenz
     */
    public Spieler getSpieler(){
        return spieler;
    }
}
