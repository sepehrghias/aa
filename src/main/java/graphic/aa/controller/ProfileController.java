package graphic.aa.controller;

import graphic.aa.model.DataBase;
import graphic.aa.view.message.ProfileMenuMessage;
import javafx.scene.image.Image;

import java.net.URL;

public class ProfileController {
    public static void logout() {
        DataBase.setLoggedInPlayer(null);
    }

    public static void removeAccount() {
        DataBase.removePlayer(DataBase.getLoggedINPlayer());
        DataBase.setLoggedInPlayer(null);
        SettingController.writeInJson();
    }

    public static ProfileMenuMessage changePassword(String oldPassword, String newPassword) {
        if (!oldPassword.equals(DataBase.getLoggedINPlayer().getPassword()))
            return ProfileMenuMessage.WRONG_PASSWORD;
        if (oldPassword.equals(newPassword))
            return ProfileMenuMessage.PASSWORD_IS_SAME;
        DataBase.getLoggedINPlayer().setPassword(newPassword);
        SettingController.writeInJson();
        return ProfileMenuMessage.SUCCESS;
    }

    public static ProfileMenuMessage changeUsername(String oldUsername, String newUsername) {
        if (!oldUsername.equals(DataBase.getLoggedINPlayer().getUsername()))
            return ProfileMenuMessage.WRONG_USERNAME;
        if (oldUsername.equals(newUsername))
            return ProfileMenuMessage.USERNAME_IS_SAME;
        DataBase.getLoggedINPlayer().setUsername(newUsername);
        SettingController.writeInJson();
        return ProfileMenuMessage.SUCCESS;
    }

    public static String getImage() {
        return DataBase.getLoggedINPlayer().getUrl();
    }

    public static void changeAvatar(int i) {
        DataBase.getLoggedINPlayer().setAvatar(i);
        DataBase.getLoggedINPlayer().changeImage();
        SettingController.writeInJson();
    }
}
