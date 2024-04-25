package ChessInJava.Pieces;

import java.util.ArrayList;
import java.util.List;

import ChessInJava.Position;

public abstract class Piece implements Comparable<Piece>{
    boolean white;

    public Piece(boolean white) {
        this.white = white;
    }

    abstract public List<Position> move(Position position);

    public int compareTo(Piece o) {
        String[] pieceOrder = {"P ", "R ", "Kn", "B ", "Q ", "K "};

        return pieceOrder.toString().indexOf(this.toString()) - pieceOrder.toString().indexOf(o.toString());
    }

    public List<Position> removeImpossiblePositions(List<Position> positions) {
        List<Position> results = new ArrayList<Position>();
        for (Position position : positions) {
            if (position.getfile() >= 0 && position.getfile() < 8 && position.getrank() >= 0 && position.getrank() < 8) {
                results.add(position);
            }
        }
        return results;
    }

    public boolean equals(Object o) {
        if (o instanceof Piece) {
            return this.toString().equals(o.toString()) && this.white == ((Piece) o).white;
        }
        return false;
    }

    public boolean isWhite() {
        return white;
    }
}