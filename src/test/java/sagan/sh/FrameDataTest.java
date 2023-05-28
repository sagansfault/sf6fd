package sagan.sh;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sagan.sh.character.Character;
import sagan.sh.character.CharacterId;
import sagan.sh.move.Move;

import java.util.Comparator;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.concurrent.ExecutionException;

class FrameDataTest {

    static FrameData frameData;

    @BeforeAll
    static void before() {
        try {
            frameData = FrameData.load().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void charactersLengthTest() {
        Assertions.assertEquals(frameData.getCharacters().size(), CharacterId.values().length);
    }

    @Test
    void characterPresentTest() {
        Optional<Character> characterOptional = frameData.getCharacter(CharacterId.MARISA);
        Assertions.assertTrue(characterOptional.isPresent());
    }

    @Test
    void movesPresentTest() {
        Character marisa = frameData.getCharacter(CharacterId.MARISA).get();
        Assertions.assertFalse(marisa.getMoves().isEmpty());
    }
}
