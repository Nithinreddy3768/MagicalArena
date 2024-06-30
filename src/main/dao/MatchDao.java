package main.dao;

import main.model.Player;
import main.model.Match;

import java.util.HashMap;

public class MatchDao {
    private final HashMap<String, Match> matches = new HashMap<>();

    public Match createMatch(Player A, Player B) {
        Match newMatch = new Match(A, B);
        matches.put(newMatch.getMatchName(), newMatch);
        return newMatch;
    }

    public Match getMatch(String matchName) {
        return matches.get(matchName);
    }
}
