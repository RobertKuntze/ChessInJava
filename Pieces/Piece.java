package ChessInJava.Pieces;

import java.util.List;

import ChessInJava.Position;

public abstract class Piece {
    boolean white;

    public Piece(boolean white) {
        this.white = white;
    }

    abstract public List<Position> move(Position position);
}