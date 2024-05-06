package graphic.aa.controller;

import graphic.aa.model.DataBase;
import graphic.aa.view.GameMenu;
import graphic.aa.view.HelloApplication;

public class GameController {
    public static String getShootKey() {
        if (DataBase.getLoggedINPlayer() != null)
            return DataBase.getLoggedINPlayer().getShootKey();
        return "Space";
    }

    public static void setGame(GameMenu gameMenu) {
        DataBase.setGameMenu(gameMenu);
    }

    public static void setScore(int score) {
        DataBase.getLoggedINPlayer().setScore(score);
        SettingController.writeInJson();
    }

    public static double getCircleMap(int index) {
        if (DataBase.getLoggedINPlayer() != null)
            return Math.toRadians(DataBase.getMap(DataBase.getLoggedINPlayer().getMap()).get(index));
        return 2;
    }
    public static boolean hasLoggedInPlayer() {
        return DataBase.getLoggedINPlayer() != null;
    }
}
