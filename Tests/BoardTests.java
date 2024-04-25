import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTests {
    @Test
    public void testMovePiece() {
        Board board = new Board();

        // Assuming you have a way to 
        // Test moving a piece to an empty spot
        Position start = new Position(1, 1);
        Position end = new Position(2, 2);
        board.movePiece(start, end);
        assertNull(board.getPiece(start));
        assertNotNull(board.getPiece(end));

        // Test moving a piece to a spot occupied by the opponent's piece
        start = new Position(2, 2);
        end = new Position(3, 3);
        board.movePiece(start, end);
        assertNull(board.getPiece(start));
        assertNotNull(board.getPiece(end));

        // Test moving a piece to a spot occupied by your own piece
        start = new Position(1, 1);
        end = new Position(2, 2);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            board.movePiece(start, end);
        });
        assertEquals("You can't move your piece to a position with your own piece", exception.getMessage());

        // Test moving a pawn to an empty spot not diagonally
        start = new Position(1, 1);
        end = new Position(2, 1);
        exception = assertThrows(IllegalArgumentException.class, () -> {
            board.movePiece(start, end);
        });
        assertEquals("Pawns can only move diagonally to take a piece", exception.getMessage());
    }
}