package view.panels;

public enum PANELS {
    LOGIN_AND_REGISTER("lar"),
    LOGIN("login"),
    REGISTER("reg"),
    MENU("menu"),
    SELECTION("selection"),
    JOIN_OR_CREATE("joc"),
    JOIN("join"),
    CREATE("create"),
    LOBBY_CONSOLE("lobbyC"),
    LOBBY_GRAPHICS("lobbyG"),
    CONSOLE("console"),
    GRAPHICS("graphics"),
    RANKING("ranking"),
    HOW_PLAY("how_play");

    private final String name;

    PANELS(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
