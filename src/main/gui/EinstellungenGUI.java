package main.gui;

import main.model.Einstellungen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Das GUI fuer das abaendern der Spieleinstellungen
 */
public class EinstellungenGUI extends JFrame {

    /**
     * Exception welche im Falle einer invaliden Eingabe ausgegeben wird
     */
    public static class InvaliderWertExeption extends Exception{}

    private Einstellungen model;

    private JPanel wertPanel = new JPanel();
    private JPanel spielerPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();

    private JTextField zielPunkzahl = new JTextField("              ");
    private JTextField spielerAnzahl = new JTextField("              ");
    private JLabel zielText = new JLabel("Ziel Punkzahl");
    private JLabel spielerText = new JLabel("Spieler Anzahl");
    private JButton zurueckButton = new JButton("Zurück");

    /**
     * Zeichnet das Einsellungs GUI
     * @param model Die Referenz zum Einstellungs Objekt, welches mit dem GUI bearbeitet werden soll.
     */
    public EinstellungenGUI(Einstellungen model){
        super("Einstellungen");
        this.model = model;
        setUp();
        pack();
        setVisible(true);
    }


    private void setUp(){
        zurueckButton.addActionListener(new ZurueckListener());

        wertPanel.setLayout(new FlowLayout());
        wertPanel.add(zielText);
        wertPanel.add(zielPunkzahl);

        spielerPanel.setLayout(new FlowLayout());
        spielerPanel.add(spielerText);
        spielerPanel.add(spielerAnzahl);

        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(zurueckButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(wertPanel, BorderLayout.NORTH);
        getContentPane().add(spielerPanel,BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }


    /**
     * ButtonListener für den "Zurueck"-Button
     */
    public class ZurueckListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            Integer spielerInt = null;
            Integer scoreInt = null;
            String spieler = spielerAnzahl.getText().replaceAll("\\s","");
            String score = zielPunkzahl.getText().replaceAll("\\s", "");
            try{
                spielerInt = Integer.parseInt(spieler);
                if(spielerInt>4 || spielerInt<2){
                    throw new InvaliderWertExeption();
                }
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null,
                        "Ungltige Spieleranzahl eingegeben! Bitte gebe eine Zahl zwischen 2-4 ein",
                        "Invalider Wert",
                        JOptionPane.WARNING_MESSAGE
                );
            }
            try{
                scoreInt = Integer.parseInt(score);
                if(scoreInt<1){
                    throw new InvaliderWertExeption();
                }
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null,
                        "Ungltiger ZielWert eingegeben! Bitte gebe eine Zahl grösser als 0 ein!",
                        "Invalider Wert",
                        JOptionPane.WARNING_MESSAGE
                );
            }
            if(!(spielerInt == null) && !(scoreInt == null)){
                model.setAnzahlSpieler(spielerInt);
                model.setZielWert(scoreInt);
                dispose();
            }
        }
    }
}
