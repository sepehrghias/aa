package graphic.aa.model;

import graphic.aa.view.GameMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class DataBase {
    private static final ArrayList<Player> players = new ArrayList<>();

    private static Player loggedInPlayer;

    private static final ArrayList<Integer> map2 = new ArrayList<>(Arrays.asList(0,30,90,200,300));

    private static final ArrayList<Integer> map1 = new ArrayList<>(Arrays.asList(0,30,90,60,120));
    private static final ArrayList<Integer> map3 = new ArrayList<>(Arrays.asList(0,45,180,200,330));

    private static GameMenu gameMenu;

    private static int song = 2;

    public static boolean hasPlayer(String username, String password) {
        for (Player player : players) {
            if (player.getUsername().equals(username) && player.getPassword().equals(password))
                return true;
        }
        return false;
    }

    public static void addPlayer(Player player) {
        players.add(player);
    }

    public static Player getPlayerByName(String username, String password) {
        for (Player player : players) {
            if (player.getUsername().equals(username) && player.getPassword().equals(password))
                return player;
        }
        return null;
    }

    public static void removePlayer(Player player) {
        players.remove(player);
    }

    public static Player getLoggedINPlayer() {
        return loggedInPlayer;
    }

    public static void setLoggedInPlayer(Player player) {
        DataBase.loggedInPlayer = player;
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public static GameMenu getGameMenu() {
        return gameMenu;
    }

    public static void setGameMenu(GameMenu gameMenu) {
        DataBase.gameMenu = gameMenu;
    }

    public static int getSong() {
        return song;
    }

    public static void setSong(int song) {
        DataBase.song = song;
    }

    public static void sortTopPlayer() {
            Player player;
            for (int i = 0; i < players.size(); i++) {
                for (int j = i + 1; j < players.size(); j++) {
                    if (players.get(i).getScore() <= players.get(j).getScore()) {
                        player = players.get(i);
                        players.set(i, players.get(j));
                        players.set(j, player);
                    }
                }
            }
    }

    public static ArrayList<Integer> getMap(int map) {
        if (map == 1)
            return map1;
        else if (map == 2)
            return map2;
        return map3;
    }
}
