import main.MagicalArena;
import main.model.Match;
import main.model.Round;

public class Main {
    public static void main(String[] args) {
        MagicalArena magicalArena = new MagicalArena();
        magicalArena.addPlayerToArena("playerA", 50, 10, 5);
        magicalArena.addPlayerToArena("playerB", 60, 5, 10);

        Match match = magicalArena.playMatch("playerA", "playerB");
        System.out.println(match.getWinner());
    }
}