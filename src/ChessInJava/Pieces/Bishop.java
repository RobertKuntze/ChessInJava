package ChessInJava.Pieces;

import java.util.ArrayList;
import java.util.List;

import ChessInJava.Position;

public class Bishop extends Piece {

    public Bishop(boolean white) {
        super(white);
    }

    public String toString() {
        return "B ";
    }

    public List<Position> move(Position position) {
        List<Position> allPositions = new ArrayList<Position>();

        for(int i = 1; i < 8; i++) {
            allPositions.add(new Position(position.getfile()+i, position.getrank()+i));
            allPositions.add(new Position(position.getfile()-i, position.getrank()-i));
            allPositions.add(new Position(position.getfile()+i, position.getrank()-i));
            allPositions.add(new Position(position.getfile()-i, position.getrank()+i));
        }
        return super.removeImpossiblePositions(allPositions);
    }
    
}
