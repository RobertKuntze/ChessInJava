package ChessInJava.Pieces;

import java.util.ArrayList;
import java.util.List;

import ChessInJava.Position;

public class Knight extends Piece {

    public Knight(boolean white) {
        super(white);
    }

    public String toString() {
        return "Kn";
    }

    public List<Position> move(Position position) {
        List<Position> allPositions = new ArrayList<Position>();
        allPositions.add(new Position(position.getfile()+1, position.getrank()+2));
        allPositions.add(new Position(position.getfile()-1, position.getrank()+2));
        allPositions.add(new Position(position.getfile()+1, position.getrank()-2));
        allPositions.add(new Position(position.getfile()-1, position.getrank()-2));
        allPositions.add(new Position(position.getfile()+2, position.getrank()+1));
        allPositions.add(new Position(position.getfile()-2, position.getrank()+1));
        allPositions.add(new Position(position.getfile()+2, position.getrank()-1));
        allPositions.add(new Position(position.getfile()-2, position.getrank()-1));
        return super.removeImpossiblePositions(allPositions);
    }
    
}
