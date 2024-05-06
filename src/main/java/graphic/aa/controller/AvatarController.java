package graphic.aa.controller;

import graphic.aa.model.DataBase;

import java.util.Set;

public class AvatarController {

    public static void changeImage(String path) {
        DataBase.getLoggedINPlayer().setUrl(path);
        SettingController.writeInJson();
    }

    public static String getImage() {
        return DataBase.getLoggedINPlayer().getUrl();
    }
}
