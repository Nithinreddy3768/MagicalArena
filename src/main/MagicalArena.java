package main;

import main.dao.MatchDao;
import main.dao.PlayerDao;
import main.model.Player;
import main.model.Match;

public class MagicalArena {
    private final PlayerDao playerDao = new PlayerDao();
    private final MatchDao matchDao = new MatchDao();

    public void addPlayerToArena(String userName, int health, int attack, int strength) {
        playerDao.createPlayer(attack, strength, health, userName);
    }

    public Player getPlayerDetails(String playerUserName) {
        return playerDao.getPlayer(playerUserName);
    }

    public Match playMatch(String playerAUserName, String playerBUserName) {
        Player playerA = playerDao.getPlayer(playerAUserName);
        Player playerB = playerDao.getPlayer(playerBUserName);
        Match match = matchDao.createMatch(playerA, playerB);
        match.play();
        return match;
    }
}
