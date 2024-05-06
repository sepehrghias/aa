package graphic.aa.controller;

import graphic.aa.model.DataBase;
import graphic.aa.model.Player;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class SettingController {

    public static void setBalls(String value) {
        if (DataBase.getLoggedINPlayer() != null) {
            int num = Integer.parseInt(value);
            DataBase.getLoggedINPlayer().setBall(num);
        }
        writeInJson();
    }

    public static int getBalls() {
        if (DataBase.getLoggedINPlayer() != null) {
            return DataBase.getLoggedINPlayer().getBall();
        }
        return 8;
    }
    public static void setMap(int map) {
        if (DataBase.getLoggedINPlayer() != null) {
            DataBase.getLoggedINPlayer().setMap(map);
        }
        writeInJson();
    }
    public static void setShootKey(String items) {
        if (DataBase.getLoggedINPlayer() != null) {
            DataBase.getLoggedINPlayer().setShootKey(items);
        }
        writeInJson();
    }

    public static boolean isLight() {
        return DataBase.getLoggedINPlayer().getLight();
    }

    public static void setLight(boolean light) {
        DataBase.getLoggedINPlayer().setLight(light);
        writeInJson();
    }

    public static void setHardDegree(String hardDegree) {
        if (DataBase.getLoggedINPlayer() != null) {
            int hard = Integer.parseInt(hardDegree);
            DataBase.getLoggedINPlayer().setHardDegree(hard);
        }
        writeInJson();
    }

    public static int getMap() {
        if (DataBase.getLoggedINPlayer() != null)
            return DataBase.getLoggedINPlayer().getMap();
        return 2;
    }

    public static String getShootKey() {
        if (DataBase.getLoggedINPlayer() != null)
            return DataBase.getLoggedINPlayer().getShootKey();
        return "Space";
    }
    public static int getHardDegree() {
        if (DataBase.getLoggedINPlayer() != null) {
            return DataBase.getLoggedINPlayer().getHardDegree();
        }
        return 2;
    }

    public static void writeInJson() {
        JSONArray users = new JSONArray();
        for (int i = 0 ; i < DataBase.getPlayers().size(); i++){
            Player player1 = DataBase.getPlayers().get(i);
            JSONObject person = new JSONObject();
            person.put("username",player1.getUsername());
            person.put("password", player1.getPassword());
            person.put("islight" , player1.getLight());
            person.put("avatar" , player1.getAvatar());
            person.put("ball", player1.getBall());
            person.put("url", player1.getUrl());
            person.put("shootKey", player1.getShootKey());
            person.put("score", player1.getScore());
            person.put("hardDegree", player1.getHardDegree());
            person.put("map", player1.getMap());
            users.add(person);
        }
        try (FileWriter writer = new FileWriter("/Users/sepehrghias/Desktop/java/aa/src/main/users.json")) {
            writer.write(users.toJSONString());
            writer.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
