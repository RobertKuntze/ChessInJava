package ChessInJava.Pieces;

import java.util.ArrayList;
import java.util.List;

import ChessInJava.Position;

public class King extends Piece {

    public King(boolean white) {
        super(white);
    }

    public String toString() {
        return "K ";
    }

    @Override
    public List<Position> move(Position position) {
        List<Position> allPositions = new ArrayList<Position>();
        int[] directions = {-1, 0, 1};
        for (int i : directions) {
            for (int j : directions) {
                if (i == 0 && j == 0) {
                    continue;
                }
                allPositions.add(new Position(position.getfile() + i, position.getrank() + j));
            }
        }
        
        return super.removeImpossiblePositions(allPositions);
    }

    
}
