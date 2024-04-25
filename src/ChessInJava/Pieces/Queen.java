package ChessInJava.Pieces;

import java.util.List;

import ChessInJava.Position;

public class Queen extends Piece {

    public Queen(boolean white) {
        super(white);
    }

    public String toString() {
        return "Q ";
    }

    public List<Position> move(Position position) {
        List<Position> allPositions = new Bishop(white).move(position);
        allPositions.addAll(new Rook(white).move(position));
        return super.removeImpossiblePositions(allPositions);
    }
    
}
