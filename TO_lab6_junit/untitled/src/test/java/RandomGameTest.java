import org.example.Main;
import org.example.RandomGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RandomGameTest {

    RandomGame game;

    int n;

    int k;

    @BeforeEach
    void setUp() {
        n = 20;

        k = 5;

        game = new RandomGame(n, k);
    }

    @Test
    @DisplayName("Check if draw() size is equal to param 'k'")
    void drawSizeEqualsTrue() {
        
        assertEquals(game.draw().size(), k);
    }

    @Test
    @DisplayName("Check if draw() size is not equal to param 'k-1'")
    void drawSizeNotEquals() {

        assertNotEquals(game.draw().size(), k - 1);
    }

    @Test
    @DisplayName("Check if draw() returned list equals draw() with the same seed")
    void drawReturnsAreEqual() {
        Random rand = new Random();
        rand.setSeed(0);
        LinkedList<Integer> drawnIntegers = new LinkedList<>();

        int randomInt = 0;

        for (int i = 0; i < k; i++) {
            randomInt = rand.nextInt(n) + 1;

            if (drawnIntegers.contains(randomInt)) {
                i--;
            } else {
                drawnIntegers.add(randomInt);
            }
        }

        assertEquals(game.draw(), drawnIntegers);
    }

    @Test
    @DisplayName("getNumeberOfDraws() equals 3")
    void getNumberOfDrawsEquals() {
        game.draw();
        game.draw();
        game.draw();

        assertEquals(game.getNumberOfDraws(), 3);

    }

    @Test
    @DisplayName("getNumeberOfDraws() doesnt equals 4")
    void getNumberOfDrawsNotEqual() {
        game.draw();
        game.draw();
        game.draw();

        assertNotEquals(4, game.getNumberOfDraws());

    }

    @Test
    @DisplayName("getMin() equals getMin() with the same seed")
    void getMinEquals() {

        Random rand = new Random();
        rand.setSeed(0);
        LinkedList<Integer> drawnIntegerstest = new LinkedList<>();

        int randomInt = 0;

        for (int i = 0; i < k; i++) {
            randomInt = rand.nextInt(n) + 1;

            if (drawnIntegerstest.contains(randomInt)) {
                i--;
            } else {
                drawnIntegerstest.add(randomInt);
            }
        }
        game.draw();

        assertEquals(game.getMin() ,drawnIntegerstest.stream().min(Integer::compare).get());
    }

    @Test
    @DisplayName("getMin() is not <= 0")
    void getMinAssertFalse() {
        game.draw();

        assertFalse(game.getMin() <= 0);
    }

    @Test
    @DisplayName("getMax() equals getMax() with the same seed")
    void getMaxEquals() {

        Random rand = new Random();
        rand.setSeed(0);
        LinkedList<Integer> drawnIntegerstest = new LinkedList<>();

        int randomInt = 0;

        for (int i = 0; i < k; i++) {
            randomInt = rand.nextInt(n) + 1;

            if (drawnIntegerstest.contains(randomInt)) {
                i--;
            } else {
                drawnIntegerstest.add(randomInt);
            }
        }
        game.draw();

        assertEquals(game.getMax() ,drawnIntegerstest.stream().max(Integer::compare).get());
    }

    @Test
    @DisplayName("getMax() is not > n")
    void getMaxAssertFalse() {
        game.draw();
        assertFalse(game.getMax() > n);
    }

    @Test
    @DisplayName("argument k > n")
    void wrongArgumentsException() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                new RandomGame(3,5));
        assertEquals("n must be greater than k", exception.getMessage());
    }

    @Test
    @DisplayName("argument k < 0")
    void negativeArgumentKException() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                new RandomGame(3,-6));
        assertEquals("k must be positive number", exception.getMessage());
    }

    @Test
    @DisplayName("argument n < 0")
    void negativeArgumentNException() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                new RandomGame(-4,5));
        assertEquals("n must be positive number", exception.getMessage());
    }


}
