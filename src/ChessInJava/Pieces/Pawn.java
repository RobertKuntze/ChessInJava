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
        List<Position> result = new ArrayList<Position>();

        if ((position.getrank() == 1 && white) || (position.getrank() == 6 && !white)) {
            range = 2;
        }

        if (!white) {
            for (int i=1; i<=range; i++) {
                result.add(new Position(position.getfile(), position.getrank() - i));
            }
            result.add(new Position(position.getfile() + 1, position.getrank() - 1));
            result.add(new Position(position.getfile() - 1, position.getrank() - 1));

        }

        if (white) {
            for (int i=1; i<=range; i++) {
                result.add(new Position(position.getfile(), position.getrank() + i));
            }
            result.add(new Position(position.getfile() + 1, position.getrank() + 1));
            result.add(new Position(position.getfile() - 1, position.getrank() + 1));

        }

        for(Position position2 : new ArrayList<Position>(result)) {
            if (position2.getfile() < 0 || position2.getfile() > 7 || position2.getrank() < 0 || position2.getrank() > 7) {
                result.remove(position2);
            }
        }

        return result;
    }
    
}
