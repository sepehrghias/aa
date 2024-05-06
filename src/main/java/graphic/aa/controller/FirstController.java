package graphic.aa.controller;

import graphic.aa.model.DataBase;
import graphic.aa.model.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FirstController {
    public static void initializeUser() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("/Users/sepehrghias/Desktop/java/aa/src/main/users.json")){
            Object obj = jsonParser.parse(reader);
            JSONArray users = (JSONArray) obj;
            for (Object jsonObject : users) {
                JSONObject jsonObject1 = (JSONObject)jsonObject;
                Player player = new Player((String)jsonObject1.get("username"),(String)jsonObject1.get("password"));
                player.setLight((boolean)jsonObject1.get("islight"));
                player.setUrl((String)jsonObject1.get("url"));
                int avatar = Integer.parseInt(jsonObject1.get("avatar").toString());
                player.setAvatar(avatar);
                int ball = Integer.parseInt(jsonObject1.get("ball").toString());
                player.setBall(ball);
                player.setShootKey((String)jsonObject1.get("shootKey"));
                int score = Integer.parseInt(jsonObject1.get("score").toString());
                player.setScore(score);
                int hardDegree = Integer.parseInt((String)jsonObject1.get("hardDegree").toString());
                player.setHardDegree(hardDegree);
                DataBase.addPlayer(player);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
