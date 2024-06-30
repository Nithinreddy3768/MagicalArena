package main.service;

import main.model.Player;
import main.model.Die;

public class Match {
    private final Player playerA;
    private final Player playerB;

    private int currentHealthPlayerA;
    private int currentHealthPlayerB;

    private Player currentAttackingPlayer;
    private Player currentDefendingPlayer;

    private final Die attackingDie = new Die();
    private final Die defendingDie = new Die();

    public Match(Player A, Player B) {
        this.playerA = A;
        this.currentHealthPlayerA = playerA.getHealth();

        this.playerB = B;
        this.currentHealthPlayerB = playerB.getHealth();

        currentAttackingPlayer = playerA.getHealth() <= playerB.getHealth() ? playerA : playerB;
        currentDefendingPlayer = playerA.getHealth() > playerB.getHealth() ? playerA : playerB;
    }

    public String play() {
        System.out.println("initialAttackPlayer: " + currentAttackingPlayer.getUserName());
        while(currentHealthPlayerA > 0 && currentHealthPlayerB > 0) {
            attack();
            swapPlayersForNextAttack();
            System.out.println("currentHealthPlayerA: " + currentHealthPlayerA + " " + "currentHealthPlayerB: " + currentHealthPlayerB);
        }
        return currentHealthPlayerA > 0 ? playerA.getUserName() : playerB.getUserName();
    }

    private void attack() {
        int attackingDieVal = attackingDie.roll();
        int defendingDieVal = defendingDie.roll();

        int attackDamage = currentAttackingPlayer.getAttack() * attackingDieVal;
        int defendingStrength = currentDefendingPlayer.getStrength() * defendingDieVal;

        int healthReduce = attackDamage - defendingStrength;
        if(healthReduce > 0) {
            if(currentDefendingPlayer.getUserName().equals(playerB.getUserName())) {
                currentHealthPlayerB -= healthReduce;
            } else {
                currentHealthPlayerA -= healthReduce;
            }
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
}
