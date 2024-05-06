package graphic.aa.controller;

import graphic.aa.model.DataBase;
import graphic.aa.model.Player;
import graphic.aa.view.message.LoginMessage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.EmptyStackException;

public class LoginController {
    public static LoginMessage login(String username, String password) {
        if (username.equals(""))
            return LoginMessage.USERNAME_IS_NULL;
        if (password.equals(""))
            return LoginMessage.PASSWORD_IS_NULL;
        if (DataBase.hasPlayer(username, password)) {
            Player player = DataBase.getPlayerByName(username, password);
            DataBase.setLoggedInPlayer(player);
            return LoginMessage.SUCCESS;
        }
        return LoginMessage.NOT_EXIST;
    }

    public static LoginMessage signUp(String username, String password) throws Exception {
        if (username.equals(""))
            return LoginMessage.USERNAME_IS_NULL;
        if (password.equals(""))
            return LoginMessage.PASSWORD_IS_NULL;
        if (DataBase.hasPlayer(username, password))
            return LoginMessage.EXIST;
        Player player = new Player(username, password);
        DataBase.addPlayer(player);
        DataBase.setLoggedInPlayer(player);
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
        return LoginMessage.SUCCESS;
    }
}
