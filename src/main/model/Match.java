package main.model;

import java.util.ArrayList;
import java.util.List;

public class Match {
    private final Player playerA;
    private final Player playerB;

    private int currentHealthPlayerA;
    private int currentHealthPlayerB;

    private Player currentAttackingPlayer;
    private Player currentDefendingPlayer;

    private final Die attackingDie = new Die();
    private final Die defendingDie = new Die();

    private final List<Round> rounds = new ArrayList<>();

    private String winner;

    public Match(Player A, Player B) {
        this.playerA = A;
        this.currentHealthPlayerA = playerA.getHealth();

        this.playerB = B;
        this.currentHealthPlayerB = playerB.getHealth();

        currentAttackingPlayer = playerA.getHealth() <= playerB.getHealth() ? playerA : playerB;
        currentDefendingPlayer = playerA.getHealth() > playerB.getHealth() ? playerA : playerB;
    }

    public void play() {
        if (winner != null) {
            throw new RuntimeException("Match is over");
        }
        while(currentHealthPlayerA > 0 && currentHealthPlayerB > 0) {
            attack();
            swapPlayersForNextAttack();
        }
        winner = currentHealthPlayerA > 0 ? playerA.getUserName() : playerB.getUserName();
    }

    private void attack() {
        int attackingDieVal = attackingDie.roll();
        int defendingDieVal = defendingDie.roll();

        int attackDamage = currentAttackingPlayer.getAttack() * attackingDieVal;
        int defendingStrength = currentDefendingPlayer.getStrength() * defendingDieVal;

        int healthReduce = attackDamage - defendingStrength;
        if(currentDefendingPlayer.getUserName().equals(playerB.getUserName())) {
            if(healthReduce > 0) {
                currentHealthPlayerB -= healthReduce;
            }
            rounds.add(new Round(currentAttackingPlayer, currentDefendingPlayer, attackingDieVal, defendingDieVal, currentHealthPlayerA, currentHealthPlayerB));
        } else {
            if(healthReduce > 0) {
                currentHealthPlayerA -= healthReduce;
            }
            rounds.add(new Round(currentAttackingPlayer, currentDefendingPlayer, attackingDieVal, defendingDieVal, currentHealthPlayerB, currentHealthPlayerA));
        }
    }

    private void swapPlayersForNextAttack() {
        if(currentDefendingPlayer.getUserName().equals(playerB.getUserName())) {
            currentAttackingPlayer = playerB;
            currentDefendingPlayer = playerA;
        } else {
            currentAttackingPlayer = playerA;
            currentDefendingPlayer = playerB;
        }
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public String getWinner() {
        return winner;
    }

    public String getMatchName() {
        return playerA.getUserName() + "_" + playerB.getUserName();
    }
}
