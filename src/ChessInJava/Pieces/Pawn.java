package ChessInJava.Pieces;

import java.util.ArrayList;
import java.util.List;

import ChessInJava.Position;

public class Pawn extends Piece {

    public Pawn(boolean white) {
        super(white);
    }

    public String toString() {
        return "P ";
    }

    
    public List<Position> move(Position position) {
        int range = 1;
        List<Position> results = new ArrayList<Position>();

        if ((position.getrank() == 1 && white) || (position.getrank() == 6 && !white)) {
            range = 2;
        }

        if (!white) {
            for (int i=1; i<=range; i++) {
                results.add(new Position(position.getfile(), position.getrank() - i));
            }
            results.add(new Position(position.getfile() + 1, position.getrank() - 1));
            results.add(new Position(position.getfile() - 1, position.getrank() - 1));

        }

        if (white) {
            for (int i=1; i<=range; i++) {
                results.add(new Position(position.getfile(), position.getrank() + i));
            }
            results.add(new Position(position.getfile() + 1, position.getrank() + 1));
            results.add(new Position(position.getfile() - 1, position.getrank() + 1));

        }

        return results;
    }
    
}
