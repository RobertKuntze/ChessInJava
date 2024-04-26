package ChessInJava;

public class UserInteractionManager {
    private Board board;

    public UserInteractionManager(Board board) {
        this.board = board;
    }

    public String[] getUserInput() {
        System.out.println("Please enter your move in the format 'a1 b2'");
        String input = System.console().readLine();
        if(input == null) {
            System.out.println("Invalid input");
            return getUserInput();
        }

        String[] result = input.split("\s+");

        if(result[0].equals("help")) {
            try {
                System.out.println("all available moves for the piece at the given position");
                System.out.println(board.getLegalMoves(board.getPiece(new Position(result[1]))
                .move(new Position(result[1])), new Position(result[1])));
                return getUserInput();
            } catch (Exception e) {
                System.out.println("Invalid input\nPlease use the help function like 'help a1'");
            }
        }

        if (result.length != 2) {
            System.out.println("Invalid input");
            return getUserInput();            
        } if (result[0].length() != 2 || result[1].length() != 2) {
            System.out.println("Invalid input");
            return getUserInput();
        } if (result[0].charAt(0) < 'a' || result[0].charAt(0) > 'h' 
        || result[1].charAt(0) < 'a' || result[1].charAt(0) > 'h') {
            System.out.println("Invalid input");
            return getUserInput();
        } if (result[0].charAt(1) < '1' || result[0].charAt(1) > '8' 
        || result[1].charAt(1) < '1' || result[1].charAt(1) > '8') {
            System.out.println("Invalid input");
            return getUserInput();
        }
        return result;
    }
}
