package ChessInJava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ChessInJava.Pieces.*;

public class Board {

    Piece[][] board = new Piece[8][8];

    public Board() {
        this.board[0][0] = new Rook(true);
        this.board[1][0] = new Knight(true);
        this.board[2][0] = new Bishop(true);
        this.board[3][0] = new Queen(true);
        this.board[4][0] = new King(true);
        this.board[5][0] = new Bishop(true);
        this.board[6][0] = new Knight(true);
        this.board[7][0] = new Rook(true);

        for (int i = 0; i<8; i++) {
            this.board[i][1] = new Pawn(true);
        }

        this.board[0][7] = new Rook(false);
        this.board[1][7] = new Knight(false);
        this.board[2][7] = new Bishop(false);
        this.board[3][7] = new Queen(false);
        this.board[4][7] = new King(false);
        this.board[5][7] = new Bishop(false);
        this.board[6][7] = new Knight(false);
        this.board[7][7] = new Rook(false);

        for (int i = 0; i<8; i++) {
            this.board[i][6] = new Pawn(false);
        }
    }

    public void addPiece(Piece piece, Position position) {
        this.board[position.getfile()][position.getrank()] = piece;
    }

    public void removePiece(Position position) {
        this.board[position.getfile()][position.getrank()] = null;
    }

    public Piece getPiece(Position position) {
        return this.board[position.getfile()][position.getrank()];
    }

    public Position getPosition(Piece piece) {
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                if (this.board[i][j] == piece) {
                    return new Position(i, j);
                }
            }
        }
        return null;
    }

    public List<Piece> getAllPieces(boolean white) {
        List<Piece> result = new ArrayList<Piece>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null) {
                    if (board[i][j].isWhite() == white) {
                        result.add(board[i][j]);
                    }
                }
            }
        }
        return result;
    }

    public void movePiece(Position start, Position end) {
        if (getPiece(end) != null) {
            if(getPiece(start).isWhite() == getPiece(end).isWhite()) {
                throw new IllegalArgumentException("You can't move your piece to a position with your own piece");
            }
        } if (getPiece(start).getClass() == Pawn.class && ((end.getfile() != start.getfile() && this.getPiece(end) == null) || (end.getfile() == start.getfile() && this.getPiece(end) != null))){
            throw new IllegalArgumentException("Pawns can only take diagonally");
        } if (!getPiece(start).move(start).contains(end)) {
            throw new IllegalArgumentException("Invalid move");
        }

        this.addPiece(getPiece(start), end);
        this.removePiece(start);
    }

    public boolean isCheck(boolean white) {
        Position kingPosition = this.getPosition(new King(white));
        List<Piece> allPieces = this.getAllPieces(!white);
        for (Piece piece : allPieces) {
            if (piece.move(this.getPosition(piece)).contains(kingPosition)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        String result = "";
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                if (this.board[j][i] == null) {
                    result += "O ";
                } else {
                    result += this.board[j][i];
                }
            }
            result += "\n";
        }
        return result;
    }

}
