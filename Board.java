package ChessInJava;

import ChessInJava.Pieces.*;

public class Board {
    public static void main(String[] args) {
        Piece piece = new Pawn(false);

        System.out.println(piece.move(new Position(0, 6)));
    }


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

    public String toString() {
        String result = "";
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                result += this.board[j][i];
            }
            result += "\n";
        }
        return result;
    }

}
