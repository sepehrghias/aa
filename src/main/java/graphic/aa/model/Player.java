package graphic.aa.model;

import javafx.scene.image.Image;
import java.util.Random;

public class Player {
    private String username;

    private String password;

    private Boolean isLight = true;

    private int avatar;

    private int ball = 8;
    private int map = 2;
    private String url;

    private String shootKey = "Space";

    private int hardDegree;

    private int score;
    public Player(String username, String password) {
        this.username = username;
        this.password = password;
        avatar = getRandomAvatar();
        hardDegree = 2;
        url =  "/graphic/aa/image/avatar" + avatar + ".png";
        score = 0;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private int getRandomAvatar(){
        Random random = new Random();
        return random.nextInt(6)+1;
    }
    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public void changeImage() {
        url =  "/graphic/aa/image/avatar" + avatar + ".png";
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score += score;
    }

    public int getBall() {
        return ball;
    }

    public void setBall(int ball) {
        this.ball = ball;
    }

    public String getShootKey() {
        return shootKey;
    }

    public void setShootKey(String shootKey) {
        this.shootKey = shootKey;
    }

    public Boolean getLight() {
        return isLight;
    }

    public void setLight(Boolean light) {
        isLight = light;
    }

    public int getHardDegree() {
        return hardDegree;
    }

    public void setHardDegree(int hardDegree) {
        this.hardDegree = hardDegree;
    }

    public int getMap() {
        return map;
    }

    public void setMap(int map) {
        this.map = map;
    }
}
