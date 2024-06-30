package main.model;

public class Player {
    private final String userName;
    private final int health;
    private final int attack;
    private final int strength;

    public Player(String userName, int health, int attack, int strength) {
        this.userName = userName;
        this.health = health;
        this.attack = attack;
        this.strength = strength;
    }

    public String getUserName() {
        return userName;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getStrength() {
        return strength;
    }
}
