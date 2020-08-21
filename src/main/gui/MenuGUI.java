package main.gui;

import main.controll.Spiel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Das GUI fuer das Hautpmenu. Von hier aus kann zu den Einstellungen und zum Spiel navigiert werden.
 */
public class MenuGUI extends JFrame{

    private Spiel model;

    private JButton spielenButton = new JButton("Spielen");
    private JButton einstellungButton = new JButton("Einstellungen");
    private JButton exitButton = new JButton("Beenden");

    private JPanel buttonPanel = new JPanel();
    private JPanel mainPanel = new JPanel();
    private JPanel spielPanel = new JPanel();
    private JPanel einstellungPanel = new JPanel();
    private JPanel exitPanel = new JPanel();

    /**
     * Zeichnet das Hauptmenu
     */
    public MenuGUI(Spiel model){
        this.model = model;
        setUp();
        pack();
        setVisible(true);
    }

    private void setUp(){
        spielPanel.setLayout(new FlowLayout());
        spielPanel.add(spielenButton);

        einstellungPanel.setLayout(new FlowLayout());
        einstellungPanel.add(einstellungButton);

        exitPanel.setLayout(new FlowLayout());
        exitPanel.add(exitButton);

        buttonPanel.setLayout(new BorderLayout());

        buttonPanel.add(spielPanel, BorderLayout.NORTH);
        buttonPanel.add(einstellungPanel, BorderLayout.CENTER);
        buttonPanel.add(exitPanel, BorderLayout.SOUTH);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        spielenButton.addActionListener(new SpielStartListener());
        einstellungButton.addActionListener(new EinstellungListener());

         exitButton.addActionListener(new BeeendenListener());

        getContentPane().add(mainPanel);
    }

    /**
     * Button Listener für den "SpielStart"-Button
     */
    public class SpielStartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            model.spielStarten();
            new SpielGUI(model);
        }
    }

    /**
     * Button Listener für den "Einstellungs"-Button. Ruft das EinstellungsGUI auf.
     */
    public class EinstellungListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            new EinstellungenGUI(model.getEinstellungen());
        }
    }

    /**
     * Button Listener für den "Beeenden"-Button. Beendet das Programm
     */
    public class BeeendenListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }
}
