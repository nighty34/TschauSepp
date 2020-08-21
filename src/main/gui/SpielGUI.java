package main.gui;

import com.sun.deploy.panel.JavaPanel;
import jdk.nashorn.internal.ir.BaseNode;
import main.controll.Spiel;
import main.model.Karte;
import main.model.Spieler;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class SpielGUI extends JFrame {

    private JPanel hauptPanel = new JPanel();
    private JPanel mittelPanel = new JPanel();
    private JPanel SpielerUntenPanel = new JPanel();
    private JPanel SpielerLinksPanel = new JPanel();
    private JPanel SpielerRechtsPanel = new JPanel();
    private JPanel SpielerObenPanel = new JPanel();

    private JScrollPane scrollPaneUnten;
    private JScrollPane scrollPaneLinks;
    private JScrollPane scrollPaneOben;
    private JScrollPane scrollPaneRechs;

    private JList<Karte> listUnten = new JList<>();
    private JList<Karte> listLinks = new JList<>();
    private JList<Karte> listOben = new JList<>();
    private JList<Karte> listRechts = new JList<>();

    private JCheckBox untenTschau = new JCheckBox();
    private JCheckBox linksTschau = new JCheckBox();
    private JCheckBox obenTschau = new JCheckBox();
    private JCheckBox rechtsTschau = new JCheckBox();

    private JCheckBox untenSepp = new JCheckBox();
    private JCheckBox linksSepp = new JCheckBox();
    private JCheckBox obenSepp = new JCheckBox();
    private JCheckBox rechtsSepp = new JCheckBox();

    private JPanel untenCheckPanel = new JPanel();
    private JPanel linksCheckPanel = new JPanel();
    private JPanel obenCheckPanel = new JPanel();
    private JPanel rechtsCheckPanel = new JPanel();

    private JPanel untenPanel = new JPanel();
    private JPanel linksPanel = new JPanel();
    private JPanel obenPanel = new JPanel();
    private JPanel rechtsPanel = new JPanel();

    private JPanel unten = new JPanel();
    private JPanel links = new JPanel();
    private JPanel rechts = new JPanel();
    private JPanel oben = new JPanel();

    private JPanel buttonFlowPanel = new JPanel();

    private JLabel ablageStapel = new JLabel();

    private JButton karteZiehen = new JButton("Karte Ziehen");

    private Spiel controller;

    private KartenRenderer rendUnten;
    private KartenRenderer rendLinks;
    private KartenRenderer rendOben;
    private KartenRenderer rendRechts;

    private DefaultListModel<Karte> kartenUnten = new DefaultListModel<>();
    private DefaultListModel<Karte> kartenLinks = new DefaultListModel<>();
    private DefaultListModel<Karte> kartenOben = new DefaultListModel<>();
    private DefaultListModel<Karte> kartenRechts = new DefaultListModel<>();

    /**
     * Constructor fuer die SpielGUI
     * @param controller Referenz zum controller
     */
    public SpielGUI(Spiel controller){
        this.controller = controller;
        setUp();
        for(int i = 0; i<controller.getEinstellungen().getAnzahlSpieler(); i++){
            erstelleKarten(controller.getSpieler(i), i);
        }
        pack();
        setVisible(true);
    }

    private void setUp(){
        untenPanel.setLayout(new BorderLayout());
        linksPanel.setLayout(new BorderLayout());
        obenPanel.setLayout(new BorderLayout());
        rechtsPanel.setLayout(new BorderLayout());

        untenCheckPanel.setLayout(new FlowLayout());
        linksCheckPanel.setLayout(new FlowLayout());
        obenCheckPanel.setLayout(new FlowLayout());
        rechtsCheckPanel.setLayout(new FlowLayout());

        untenCheckPanel.add(untenTschau);
        untenCheckPanel.add(new JLabel("Tschau"));

        untenPanel.add(untenCheckPanel, BorderLayout.NORTH);
        untenPanel.add(untenSepp, BorderLayout.CENTER);
        untenPanel.add(new JLabel("Sepp"), BorderLayout.EAST);

        linksCheckPanel.add(linksTschau);
        linksCheckPanel.add(new JLabel("Tschau"));

        linksPanel.add(linksCheckPanel, BorderLayout.NORTH);
        linksPanel.add(linksSepp, BorderLayout.CENTER);
        linksPanel.add(new JLabel("Sepp"), BorderLayout.EAST);

        rechtsCheckPanel.add(rechtsTschau);
        rechtsCheckPanel.add(new JLabel("Tschau"));

        rechtsPanel.add(rechtsCheckPanel, BorderLayout.NORTH);
        rechtsPanel.add(rechtsSepp, BorderLayout.CENTER);
        rechtsPanel.add(new JLabel("Sepp"), BorderLayout.EAST);


        obenCheckPanel.add(obenTschau);
        obenCheckPanel.add(new JLabel("Tschau"));

        obenPanel.add(obenCheckPanel, BorderLayout.NORTH);
        obenPanel.add(obenSepp, BorderLayout.CENTER);
        obenPanel.add(new JLabel("Sepp"), BorderLayout.EAST);

        karteZiehen.addActionListener(new KarteNehmenListener());

        listUnten.setModel(kartenUnten);
        listUnten.setVisibleRowCount(1);
        listUnten.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        scrollPaneUnten = new JScrollPane(listUnten);

        listLinks.setModel(kartenLinks);
        listLinks.setLayoutOrientation(JList.VERTICAL_WRAP);
        scrollPaneLinks = new JScrollPane(listLinks);

        listOben.setModel(kartenOben);
        listOben.setVisibleRowCount(1);
        listOben.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        scrollPaneOben = new JScrollPane(listOben);

        listRechts.setModel(kartenRechts);
        listRechts.setLayoutOrientation(JList.VERTICAL_WRAP);
        scrollPaneRechs = new JScrollPane(listRechts);

        unten.setLayout(new BoxLayout(untenPanel, BoxLayout.Y_AXIS));


        if(!(controller.getSpieler(0)==null)){
            listUnten.setCellRenderer(new KartenRenderer(false, controller.getSpieler(0)));
            listUnten.addListSelectionListener(new AusspielListener());
        }
        if(!(controller.getSpieler(1)==null)){
            listLinks.setCellRenderer(new KartenRenderer(true, controller.getSpieler(1)));
            listLinks.addListSelectionListener(new AusspielListener());
        }
        listOben.setCellRenderer(new KartenRenderer(true, controller.getSpieler(2)));
        if(!(controller.getSpieler(2)==null)){

            listOben.addListSelectionListener(new AusspielListener());

        }
        listRechts.setCellRenderer(new KartenRenderer(true, controller.getSpieler(3)));
        if(!(controller.getSpieler(3)==null)){
            listRechts.addListSelectionListener(new AusspielListener());
        }

        buttonFlowPanel.setLayout(new FlowLayout());
        buttonFlowPanel.add(karteZiehen);

        mittelPanel.setLayout(new BorderLayout());

        //ablageStapel.setText(controller.getAktiveKarte().getFarbe() + " " +  controller.getAktiveKarte().getWerte());
        RenderCard();
        mittelPanel.add(ablageStapel, BorderLayout.CENTER);
        mittelPanel.add(buttonFlowPanel, BorderLayout.WEST);

        SpielerUntenPanel.setLayout(new BorderLayout());
        SpielerLinksPanel.setLayout(new BorderLayout());
        SpielerObenPanel.setLayout(new BorderLayout());
        SpielerRechtsPanel.setLayout(new BorderLayout());

        SpielerUntenPanel.add(scrollPaneUnten, BorderLayout.CENTER);
        SpielerLinksPanel.add(scrollPaneLinks, BorderLayout.CENTER);
        SpielerObenPanel.add(scrollPaneOben, BorderLayout.CENTER);
        SpielerRechtsPanel.add(scrollPaneRechs, BorderLayout.CENTER);

        hauptPanel.setLayout(new BorderLayout());
        hauptPanel.add(mittelPanel, BorderLayout.CENTER);
        hauptPanel.add(SpielerUntenPanel, BorderLayout.SOUTH);
        hauptPanel.add(SpielerLinksPanel, BorderLayout.WEST);
        hauptPanel.add(SpielerRechtsPanel, BorderLayout.EAST);
        hauptPanel.add(SpielerObenPanel, BorderLayout.NORTH);

        SpielerUntenPanel.add(untenPanel, BorderLayout.WEST);
        SpielerLinksPanel.add(linksPanel, BorderLayout.WEST);
        SpielerObenPanel.add(obenPanel, BorderLayout.WEST);
        SpielerRechtsPanel.add(rechtsPanel, BorderLayout.WEST);

        rendUnten = (KartenRenderer) listUnten.getCellRenderer();
        rendLinks = (KartenRenderer) listLinks.getCellRenderer();
        rendRechts = (KartenRenderer) listRechts.getCellRenderer();
        rendOben = (KartenRenderer) listOben.getCellRenderer();


        getContentPane().add(hauptPanel);
    }


    private void nextRound(){
        for(int i = 0; i<controller.getEinstellungen().getAnzahlSpieler(); i++) {
            if(CardTest(controller.getSpieler(i))) {
                if(controller.TestGewinner(controller.getSpieler(i))){
                    finish(i);
                }
            }
        }
        RenderCard();
        //ablageStapel.setText(controller.getAktiveKarte().getFarbe() + " " +  controller.getAktiveKarte().getWerte());
        controller.iterateRotation();
        rendUnten.changeHidden(true);
        rendLinks.changeHidden(true);
        rendOben.changeHidden(true);
        rendRechts.changeHidden(true);

        switch (controller.getRotation()){
            case 0:
                rendUnten.changeHidden(false);
                break;
            case 1:
                rendLinks.changeHidden(false);
                break;
            case 2:
                rendOben.changeHidden(false);
                break;
            case 3:
                rendRechts.changeHidden(false);
                break;
        }

        kartenUnten.clear();
        kartenLinks.clear();
        kartenOben.clear();
        kartenRechts.clear();

        for(int i = 0; i<controller.getEinstellungen().getAnzahlSpieler(); i++){

            erstelleKarten(controller.getSpieler(i), i);
            controller.getSpieler(i).setTschau(false);
            controller.getSpieler(i).setSepp(false);
        }
    }


    private void erstelleKarten(Spieler spieler, int index){ //0 = unten, 1 = links, 2 = oben, 3 = rechts
        for (Karte karte:spieler.getKarten()) {

            switch (index){
                case 0:
                    kartenUnten.addElement(karte);
                    break;
                case 1:
                    kartenLinks.addElement(karte);
                    break;
                case 2:
                    kartenOben.addElement(karte);
                    break;
                case 3:
                    kartenRechts.addElement(karte);
                    break;
            }
        }
    }

    /**
     * Listener, welcher beim "Ausspieler einer Karte" aktiv wird.
     */
    public class AusspielListener implements ListSelectionListener{
        @Override
        public void valueChanged(ListSelectionEvent e){
            JList source = (JList)e.getSource();
            KartenRenderer renderer = (KartenRenderer) source.getCellRenderer();
            Spieler spieler = renderer.getSpieler();
            UpdateCheckBox(controller.getIndex(spieler));
            if(!renderer.getIsHidden() && source.getSelectedIndex()>=0) {
                if(spieler.isSepp()){
                    if(spieler.getKarten().size()>1){
                        spieler.karteAufnehmen(controller.getKartendeck().karteZiehen());
                        spieler.karteAufnehmen(controller.getKartendeck().karteZiehen());
                    }
                }
                if(spieler.getKarten().size()==2){
                    if(!spieler.isTschau()) {
                        spieler.karteAufnehmen(controller.getKartendeck().karteZiehen());
                    }
                }

                if(spieler.getKarten().size()==1){
                    if(!spieler.isSepp()){
                        spieler.karteAufnehmen(controller.getKartendeck().karteZiehen());
                        spieler.karteAufnehmen(controller.getKartendeck().karteZiehen());
                    }
                }

                if(spieler.isTschau()){
                    if(spieler.getKarten().size()>2){
                        spieler.karteAufnehmen(controller.getKartendeck().karteZiehen());
                    }
                }
                System.out.println(source.getSelectedIndex());
                if(controller.karteAusspielen(spieler.karteAblegen(source.getSelectedIndex()))){
                    spieler.removeKarte(source.getSelectedIndex());
                    nextRound();
                }
            }
        }
    }

    /**
     * ButtonListener fuer den "KarteZiehen-Button"
     */
    public class KarteNehmenListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (controller.getRotation()){
                case 0:
                    rendUnten.getSpieler().karteAufnehmen(controller.getKartendeck().karteZiehen());
                    break;
                case 1:
                    rendLinks.getSpieler().karteAufnehmen(controller.getKartendeck().karteZiehen());
                    break;
                case 2:
                    rendOben.getSpieler().karteAufnehmen(controller.getKartendeck().karteZiehen());
                    break;
                case 3:
                    rendRechts.getSpieler().karteAufnehmen(controller.getKartendeck().karteZiehen());
                    break;
            }

            nextRound();
        }
    }

    private boolean CardTest(Spieler spieler){
        if(spieler.getKarten().size()<1){
            int score = 0;
            for(int i = 0; i<controller.getEinstellungen().getAnzahlSpieler(); i++){
                score+= calcWert(controller.getSpieler(i));
            }
            spieler.setScore(spieler.getScore() + score);
            return true;
        }
        return false;
    }

    private int calcWert(Spieler spieler){
        int score = 0;
        for (Karte karte:spieler.getKarten()) {
            switch (karte.getWerte()){
                case Ass:
                    score+=11;
                    break;
                case Koenig:
                    score+=4;
                    break;
                case Dame:
                    score+=3;
                    break;
                case Bube:
                    score+=2;
                    break;
                case Zehn:
                    score+=10;
                    break;
                case Neun:
                    score+=9;
                    break;
                case Acht:
                    score+=8;
                    break;
                case Sieben:
                    score+=7;
                    break;
                case Sechs:
                    score+=6;
                    break;
            }
        }
        return score;
    }

    private void UpdateCheckBox(int spielerIndex) {
        switch (spielerIndex) {
            case 0:
                if (untenTschau.isSelected()) {
                    controller.getSpieler(spielerIndex).tschauSagen();
                }
                if (untenSepp.isSelected()) {
                    controller.getSpieler(spielerIndex).seppSagen();
                }
                break;
            case 1:
                if (linksTschau.isSelected()) {
                    controller.getSpieler(spielerIndex).tschauSagen();
                }
                if (linksSepp.isSelected()) {
                    controller.getSpieler(spielerIndex).seppSagen();
                }
                break;
            case 2:
                if (obenTschau.isSelected()) {
                    controller.getSpieler(spielerIndex).tschauSagen();
                }
                if (obenTschau.isSelected()) {
                    controller.getSpieler(spielerIndex).seppSagen();
                }
                break;
            case 3:
                if (rechtsTschau.isSelected()) {
                    controller.getSpieler(spielerIndex).tschauSagen();
                }
                if (rechtsTschau.isSelected()) {
                    controller.getSpieler(spielerIndex).seppSagen();
                }
                break;
        }
        untenTschau.setSelected(false);
        linksTschau.setSelected(false);
        obenTschau.setSelected(false);
        rechtsTschau.setSelected(false);

        untenSepp.setSelected(false);
        linksSepp.setSelected(false);
        obenSepp.setSelected(false);
        rechtsSepp.setSelected(false);
    }

    private void RenderCard(){
        Karte karte = controller.getAktiveKarte();
        try {
            InputStream is = Spieler.class.getClassLoader().getResourceAsStream(karte.getFarbe().toString() + "_" + karte.getWerte().toString() + ".png");
            BufferedImage img = ImageIO.read(is);
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH));
            ablageStapel.setIcon(imageIcon);
            ablageStapel.setText("");
        } catch (Exception ex) {
            ablageStapel.setText(karte.getFarbe().toString() + " " + karte.getWerte().toString());
        }
    }

    /**
     * Beendet das Spiel und gitb den Gewinner aus
     * @param spieler
     */
    private void finish(int spieler){
        JOptionPane.showMessageDialog(null,
                "Spieler " + spieler + " hat gewonnen!",
                "Gewinner",
                JOptionPane.WARNING_MESSAGE
        );
    }
}
