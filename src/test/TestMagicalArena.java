package test;

import main.MagicalArena;
import main.model.Match;
import main.model.Player;
import main.model.Round;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestMagicalArena {

    @Test
    public void testAddPlayerToArena() {
        MagicalArena magicalArena = new MagicalArena();
        magicalArena.addPlayerToArena("playerA", 50, 10, 5);

        Player createdPlayer = magicalArena.getPlayerDetails("playerA");
        assertEquals(50, createdPlayer.getHealth());
        assertEquals(10, createdPlayer.getAttack());
        assertEquals(5, createdPlayer.getStrength());
    }

    @Test
    public void testAddPlayerToArena_PlayerAlreadyExists() {
        MagicalArena magicalArena = new MagicalArena();
        magicalArena.addPlayerToArena("playerA", 50, 10, 5);

        String message = null;
        try {
            magicalArena.addPlayerToArena("playerA", 50, 10, 5);
        } catch (RuntimeException ex) {
            message = ex.getMessage();
        }

        assertEquals("username already exists", message);
    }

    @Test
    public void testPlayMatch() {
        MagicalArena magicalArena = new MagicalArena();
        magicalArena.addPlayerToArena("playerA", 50, 10, 5);
        magicalArena.addPlayerToArena("playerB", 60, 5, 10);

        Match match = magicalArena.playMatch("playerA", "playerB");
        assertEquals("playerA_playerB", match.getMatchName());

        for(int i = 0; i < match.getRounds().size(); i++) {
            if(i > 0) {
                verifyRoundToRoundResult(match.getRounds().get(i - 1), match.getRounds().get(i));
            }
        }

        // assertWinner
        String expectedWinner = match.getRounds().get(match.getRounds().size() - 1).getAttackingPlayer().getUserName();
        assertEquals(expectedWinner, match.getWinner());
    }

    @Test
    public void testPlayMatch_2() {
        MagicalArena magicalArena = new MagicalArena();
        magicalArena.addPlayerToArena("playerA", 50, 10, 5);
        magicalArena.addPlayerToArena("playerB", 60, 5, 10);

        Match match = magicalArena.playMatch("playerB", "playerA");
        assertEquals("playerB_playerA", match.getMatchName());

        for(int i = 0; i < match.getRounds().size(); i++) {
            if(i > 0) {
                verifyRoundToRoundResult(match.getRounds().get(i - 1), match.getRounds().get(i));
            }
        }

        // assertWinner
        String expectedWinner = match.getRounds().get(match.getRounds().size() - 1).getAttackingPlayer().getUserName();
        assertEquals(expectedWinner, match.getWinner());
    }

    private void verifyRoundToRoundResult(Round prevRound, Round currRound) {
        // assert dice values
        assertTrue(prevRound.getAttackingDieVal() <= 6 && prevRound.getAttackingDieVal() >= 1);
        assertTrue(prevRound.getDefendingDieVal() <= 6 && prevRound.getDefendingDieVal() >= 1);
        assertTrue(currRound.getAttackingDieVal() <= 6 && currRound.getAttackingDieVal() >= 1);
        assertTrue(currRound.getDefendingDieVal() <= 6 && currRound.getDefendingDieVal() >= 1);

        // assert if players were swapped after round
        assertEquals(prevRound.getAttackingPlayer().getUserName(), currRound.getDefendingPlayer().getUserName());
        assertEquals(prevRound.getDefendingPlayer().getUserName(), currRound.getAttackingPlayer().getUserName());

        // assert if prev round defender health is same as curr round attacker health
        assertEquals(prevRound.getDefendingPlayerCurrentHealth(), currRound.getAttackingPlayerCurrentHealth());

        int healthReduce = currRound.getAttackingDieVal() * currRound.getAttackingPlayer().getAttack()
                - currRound.getDefendingDieVal() * currRound.getDefendingPlayer().getStrength();

        int expectedCurrRoundDefendingHealth = healthReduce >= 0 ? prevRound.getAttackingPlayerCurrentHealth() - healthReduce :
                prevRound.getAttackingPlayerCurrentHealth();

        assertEquals(expectedCurrRoundDefendingHealth, currRound.getDefendingPlayerCurrentHealth());
    }
}
