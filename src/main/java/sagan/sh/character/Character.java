package sagan.sh.character;

import sagan.sh.move.Move;
import sagan.sh.parser.Parser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class Character {

    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    private final CharacterId id;
    private final Set<Move> moves;

    private Character(CharacterId id) {
        this.id = id;
        this.moves = new HashSet<>();
    }

    /**
     * Creates a character by loading their info from their data page. Not intended for outward facing public use. There
     * should never be a reason this needs to be used by an api implementer.
     *
     * @param id The id of the character to load
     * @return A future of the created character
     */
    public static CompletableFuture<Character> load(CharacterId id) {
        Character character = new Character(id);
        CompletableFuture<Character> future = new CompletableFuture<>();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(id.getFrameDataURL()))
                .GET()
                .build();
        CLIENT.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(body -> {
                    Parser parser = new Parser(body);
                    List<Move> moves = parser.parse();
                    character.moves.addAll(moves);
                    future.complete(character);
                });

        return future;
    }

    public CharacterId getId() {
        return id;
    }

    public Set<Move> getMoves() {
        return moves;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return id == character.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
