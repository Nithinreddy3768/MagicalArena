package main.model;

public class Die {
    private int minVal;
    private int maxVal;

    public Die(int minVal, int maxVal) {
        this.minVal = minVal;
        this.maxVal = maxVal;
    }

    public Die() {
        this(1, 6);
    }

    public int roll() {
        return (int) (Math.random() * (maxVal - minVal + 1)) + minVal;
    }
}
