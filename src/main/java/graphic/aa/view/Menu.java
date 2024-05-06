package graphic.aa.view;

import graphic.aa.controller.SettingController;

import java.util.Set;

public class Menu {
    private static final HelloApplication helloApplication = new HelloApplication();

    private static final ProfileMenu profileMenu = new ProfileMenu();

    private static final ScoreboardMenu scoreboardMenu = new ScoreboardMenu();

    private static final SongMenu songMenu = new SongMenu();

    private static final Setting setting = new Setting();

    private static final AvatarMenu avatarMenu = new AvatarMenu();
    private static final LoginAndSignUpMenu loginAndSignUpMenu = new LoginAndSignUpMenu();

    private static final MainMenu mainMenu = new MainMenu();

    private static final SignUpMenu signUpMenu = new SignUpMenu();

    public static LoginAndSignUpMenu getLoginAndSignUpMenu() {
        return loginAndSignUpMenu;
    }

    public static MainMenu getMainMenu() {
        return mainMenu;
    }

    public static SignUpMenu getSignUpMenu() {
        return signUpMenu;
    }

    public static SongMenu getSongMenu() {
        return songMenu;
    }

    public static ProfileMenu getProfileMenu() {
        return profileMenu;
    }

    public static Setting getSetting() {
        return setting;
    }

    public static AvatarMenu getAvatarMenu() {
        return avatarMenu;
    }

    public static HelloApplication getHelloApplication() {
        return helloApplication;
    }

    public static ScoreboardMenu getScoreboardMenu() {
        return scoreboardMenu;
    }
}
