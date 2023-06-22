package at.htlleonding.schnapsn.model;

public class Player {
    private static int playerId;
    private String username;
    private String password;
    private String email;
    private Integer games_played;
    private Integer wins;
    private Integer losses;

    public Player(String username, String password, String email) {
        playerId++;
        this.username = username;
        this.password = password;
        this.email = email;
        this.games_played = 0;
        this.wins = 0;
        this.losses = 0;
    }
    public int getPlayerId() {
        return playerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGames_played() {
        return games_played;
    }

    public void setGames_played(Integer games_played) {
        this.games_played = games_played;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }
}
