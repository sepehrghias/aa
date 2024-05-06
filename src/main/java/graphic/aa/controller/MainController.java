package graphic.aa.controller;

import graphic.aa.model.DataBase;
import graphic.aa.model.Player;

import java.util.ArrayList;

public class MainController {
    public static void logout() {
        DataBase.setLoggedInPlayer(null);
    }

    public static String getImage() {
        return DataBase.getLoggedINPlayer().getUrl();
    }

    public static void sortForScoreboard () {
        DataBase.sortTopPlayer();
    }

    public static ArrayList<Player> getTopPlayer () {
        return DataBase.getPlayers();
    }
}
