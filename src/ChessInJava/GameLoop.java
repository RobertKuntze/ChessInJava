package ChessInJava;

import java.util.ArrayList;
import java.util.List;

public class GameLoop {
    public static void main(String[] args) {
        Board board = new Board();
        boolean white = true;     
        UserInteractionManager userInteractionManager = new UserInteractionManager(board);   

        while (true) {
            System.out.println(board);
            System.out.println(white ? "White's move" : "Black's move");
            String[] positions = userInteractionManager.getUserInput();
            Position start = new Position(positions[0]);
            Position end = new Position(positions[1]);
            if (board.getPiece(start) == null || board.getPiece(start).isWhite() != white) {
                System.out.println("Invalid Move: Please pick a valid piece");
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
            }
        }
    }
}
