package io.deeplay;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

public class SolutionTest {

    @Test
    public void shouldReturnCorrectResultForHuman() {
        String human = "human";

        String layoutForHuman1 = "STWSWTPPTPTTPWPP";
        String layoutForHuman2 = "SPSSPSSPSPPSPPSS";
        String layoutForHuman3 = "WSPSSTTWWPPWWTTS";
        String layoutForHuman4 = "WTPSPWSTTSWPSPWW";

        try {
            Assertions.assertEquals(10, Solution.getResult(layoutForHuman1, human));
            Assertions.assertEquals(18, Solution.getResult(layoutForHuman2, human));
            Assertions.assertEquals(16, Solution.getResult(layoutForHuman3, human));
            Assertions.assertEquals(13, Solution.getResult(layoutForHuman4, human));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldReturnCorrectResultForSwamper() {
        String swamper = "swamper";

        String layoutForSwamper1 = "STWSWTPPTPTTPWPP";
        String layoutForSwamper2 = "TTTTTTTTTTTTTTTT";
        String layoutForSwamper3 = "WSPSSTTWWPPWWTTS";
        String layoutForSwamper4 = "WTPSPWSTTSWPSPWW";

        try {
            Assertions.assertEquals(15, Solution.getResult(layoutForSwamper1, swamper));
            Assertions.assertEquals(30, Solution.getResult(layoutForSwamper2, swamper));
            Assertions.assertEquals(12, Solution.getResult(layoutForSwamper3, swamper));
            Assertions.assertEquals(12, Solution.getResult(layoutForSwamper4, swamper));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldReturnCorrectResultForWoodman() {
        String woodman = "woodman";

        String layoutForWoodman1 = "STWSWTPPTPTTPWPP";
        String layoutForWoodman2 = "SPSSPSSPSPPSPPSS";
        String layoutForWoodman3 = "WSPSSTTWWPPWWTTS";
        String layoutForWoodman4 = "WTPSPWSTTSWPSPWW";

        try {
            Assertions.assertEquals(12, Solution.getResult(layoutForWoodman1, woodman));
            Assertions.assertEquals(15, Solution.getResult(layoutForWoodman2, woodman));
            Assertions.assertEquals(14, Solution.getResult(layoutForWoodman3, woodman));
            Assertions.assertEquals(14, Solution.getResult(layoutForWoodman4, woodman));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @MethodSource("layoutsWithWrongCharacters")
    public void shouldThrowIAExceptionWhenWrongCharacters(String layout) {
        String race = "human";
        Assertions.assertThrows(IllegalArgumentException.class, () -> Solution.getResult(layout, race),
                "Field Layout must contain only capital A-Z characters");
    }

    @ParameterizedTest
    @MethodSource("layoutsWithWrongNumberOfCharacters")
    public void shouldThrowIAExceptionWhenWrongNumberOfCharacters(String layout) {
        String race = "human";
        Assertions.assertThrows(IllegalArgumentException.class, () -> Solution.getResult(layout, race),
                "Field Layout must contain %d characters");
    }

    @Test
    public void shouldThrowIAExceptionWhenRedundantCharacters() {
        String race = "human";
        String layout = "NTRSOMEPHBSTKLPO";

        Assertions.assertThrows(IllegalArgumentException.class, () -> Solution.getResult(layout, race),
                "Field Layout must contain only capital [S, W, T, P] characters");
    }

    public static List<String> layoutsWithWrongCharacters() {
        return Arrays.asList("STWSWTPPRFTABGYD", "T8HSB2892HYSGY78", "STWSWTPPwwpttpww", "WPST-PPP++SS****");
    }

    public static List<String> layoutsWithWrongNumberOfCharacters() {
        return Arrays.asList("STWS", "STWSWTPPTPTTPWPPWPWP", "STWSWTPPT", "STWSWTPPTPTTPWPPSTWSWPPPP");
    }
}
