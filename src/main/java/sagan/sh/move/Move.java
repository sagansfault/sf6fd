package sagan.sh.move;

import java.util.Objects;

/**
 * A move or collection of frame data regarding a move. All data is representative of what is found on the frame data
 * pages of each character's data page.
 *
 * @param name The name of the move. In the case of a Normal, this is just the move input.
 */
public record Move(String name, String input, String damage, String guard, String cancel, String startup,
                   String active, String recovery, String hitAdv, String blockAdv) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return input.equals(move.input);
    }

    @Override
    public int hashCode() {
        return Objects.hash(input);
    }
}
