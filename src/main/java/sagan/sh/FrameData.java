package sagan.sh;

import sagan.sh.character.Character;
import sagan.sh.character.CharacterId;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * The main data class returned by this api
 */
public class FrameData {

    private final Set<Character> characters = new HashSet<>();

    private FrameData(Set<Character> characters) {
        this.characters.addAll(characters);
    }

    /**
     * The main entry point into the api. This will perform async operations loading data from the respective frame data
     * websites and parse them into character objects to be placed into this frame data class.
     *
     * @return A future that will contain a FrameData class when completed containing all supported info of this api
     */
    public static CompletableFuture<FrameData> load() {
        Set<CompletableFuture<Character>> futures = Arrays.stream(CharacterId.values()).map(Character::load).collect(Collectors.toSet());
        CompletableFuture<Void> all = CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
        return all.thenApply(nothing -> {
            Set<Character> characters = new HashSet<>();
            for (CompletableFuture<Character> future : futures) {
                try {
                    characters.add(future.get());
                } catch (InterruptedException | ExecutionException exception) {
                    System.out.println("Could not load character:");
                    exception.printStackTrace();
                }
            }
            return new FrameData(characters);
        });
    }

    /**
     * @return A mutable set of characters in this data class, each object containing info on the character like their
     * id and moves.
     */
    public Set<Character> getCharacters() {
        return characters;
    }

    /**
     * Attempts to find a character based on a provided character id. This function will almost always return some
     * character, an optional is returned in the rare case that this data class had not loaded even loaded the character
     *
     * @param id The id of the character to find
     * @return An optional containing the found character, empty otherwise.
     */
    public Optional<Character> getCharacter(CharacterId id) {
        for (Character character : this.characters) {
            if (character.getId() == id) {
                return Optional.of(character);
            }
        }
        return Optional.empty();
    }
}
