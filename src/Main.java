import main.controll.Spiel;
import main.gui.MenuGUI;
import main.model.Einstellungen;
import main.model.Kartendeck;

public class Main {
    public static void main(String[] args) {
        Spiel spiel = new Spiel();
        Einstellungen einstellungen = new Einstellungen();
        Kartendeck kartendeck = new Kartendeck();

        spiel.setEinstellungen(einstellungen);
        spiel.setKartendeck(kartendeck);
        kartendeck.erstellenKarten();

        new MenuGUI(spiel);
    }
}
