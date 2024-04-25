package ChessInJava;

import java.util.ArrayList;
import java.util.List;

public class GameLoop {
    public static void main(String[] args) {
        Board board = new Board();
        boolean white = true;
        

        while (true) {
            System.out.println(board);
            System.out.println(white ? "White's move" : "Black's move");
            System.out.println("Enter move in format <start position> <end position>: ");
            String move = System.console().readLine();
            String[] positions = move.split(" ");
            Position start = new Position(positions[0]);
            Position end = new Position(positions[1]);
            if (board.getPiece(start).isWhite() != white) {
                System.out.println("Invalid Move: That piece does not belond to the current player");
                continue;
            }
            try {
                board.movePiece(start, end);
                if(board.isCheck(white)) {
                    System.out.println("You lost");
                    return;
                }
                white = !white;
            } catch (Exception e) {
                System.out.println("Invalid move: " + e.getMessage());
                List<Position> pos = new ArrayList<Position>();  
            }
        }
    }
}
