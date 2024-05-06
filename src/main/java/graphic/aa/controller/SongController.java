package graphic.aa.controller;

import graphic.aa.model.DataBase;
import graphic.aa.view.GameMenu;

public class SongController {
    public static void setSong(int song) {
        DataBase.setSong(song);
    }

    public static int getSong() {
        return DataBase.getSong();
    }

    public static GameMenu getGame() {
        return DataBase.getGameMenu();
    }
}
