import main.dao.PlayersDao;
import main.model.Player;
import main.service.Match;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        PlayersDao playersDao = new PlayersDao();
        playersDao.createPlayer(5, 10, 50, "playerA");
        playersDao.createPlayer(10, 5, 60, "playerB");

        Player playerA = playersDao.getPlayer("playerA");
        Player playerB = playersDao.getPlayer("playerB");
        Match match = new Match(playerA, playerB);
        System.out.println(match.play());
    }
}