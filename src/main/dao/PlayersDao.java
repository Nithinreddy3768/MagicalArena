package main.dao;

import main.model.Player;

import java.util.HashMap;

public class PlayersDao {
    HashMap<String, Player> players = new HashMap<>();

    public void createPlayer(int attack, int strength, int health, String username) {
        if (players.containsKey(username)) {
            throw new RuntimeException("username already exists");
        }
        players.put(username, new Player(username, health, attack, strength));
    }

    public Player getPlayer(String username) {
        if (!players.containsKey(username)) {
            throw new RuntimeException("username doesn't exists");
        }
        return players.get(username);
    }
}
