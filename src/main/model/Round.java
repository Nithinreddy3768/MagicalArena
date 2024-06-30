package main.model;

public class Round {
    private final Player attackingPlayer;
    private final Player defendingPlayer;

    private final int attackingDieVal;
    private final int defendingDieVal;

    private final int attackingPlayerCurrentHealth;
    private final int defendingPlayerCurrentHealth;

    public Round(Player attackingPlayer, Player defendingPlayer, int attackingDieVal, int defendingDieVal, int attackingPlayerCurrentHealth, int defendingPlayerCurrentHealth) {
        this.attackingPlayer = attackingPlayer;
        this.defendingPlayer = defendingPlayer;
        this.attackingDieVal = attackingDieVal;
        this.defendingDieVal = defendingDieVal;
        this.attackingPlayerCurrentHealth = attackingPlayerCurrentHealth;
        this.defendingPlayerCurrentHealth = defendingPlayerCurrentHealth;
    }

    public Player getAttackingPlayer() {
        return attackingPlayer;
    }

    public Player getDefendingPlayer() {
        return defendingPlayer;
    }

    public int getAttackingDieVal() {
        return attackingDieVal;
    }

    public int getDefendingDieVal() {
        return defendingDieVal;
    }

    public int getAttackingPlayerCurrentHealth() {
        return attackingPlayerCurrentHealth;
    }

    public int getDefendingPlayerCurrentHealth() {
        return defendingPlayerCurrentHealth;
    }
}
