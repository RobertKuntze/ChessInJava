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

    public Board(Board board) {
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                this.board[i][j] = board.board[i][j];
            }
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
                if (piece.equals(this.board[i][j])) {
                    return new Position(i, j);
                }
            }
        }
        return null;
    }

    public Map<Position, Piece> getAllPieces(boolean white) {
        Map<Position, Piece> result = new HashMap<Position, Piece>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null) {
                    if (board[i][j].isWhite() == white) {
                        result.put(new Position(i, j), board[i][j]);
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
        } if (!this.getLegalMoves(this.getPiece(start).move(start), start).contains(end)) {
            throw new IllegalArgumentException("Invalid move");
        }

        this.addPiece(getPiece(start), end);
        this.removePiece(start);
    }

    public List<Position> getLegalMoves(List<Position> positions, Position start) {
        Piece piece = this.getPiece(start);
                
        if (piece instanceof Knight) {
            return positions;
        }
        Map<Position, List<Position>> relativePositions = new HashMap<Position, List<Position>>();
        int[] directions = {-1, 0, 1};
        for (int i : directions) {
            for (int j : directions) {
                if (i == 0 && j == 0) {
                    continue;
                }
                relativePositions.put(new Position(i, j), new ArrayList<Position>());
            }
        }
        for (Position direction : relativePositions.keySet()) {
            for (Position position : positions) {
                if (Integer.signum(position.getfile() - start.getfile()) == direction.getfile() 
                && Integer.signum(position.getrank() - start.getrank()) == direction.getrank()) {
                    relativePositions.get(direction).add(position);
                }
            }
        }

        List<Position> result = new ArrayList<Position>();
        for (Position direction : relativePositions.keySet()) {
            for (Position position : relativePositions.get(direction)) {
                if (this.getPiece(position) == null) {
                    result.add(position);
                } else if (this.getPiece(position).isWhite() != piece.isWhite()) {
                    result.add(position);
                    break;
                } else {
                    break;
                }
            }
        }

        if (piece instanceof Pawn) {
            for (Position position : new ArrayList<Position>(result)) {
                if ((position.getfile() != start.getfile() && this.getPiece(position) == null)
                || (position.getfile() == start.getfile() && this.getPiece(position) != null)) {
                    result.remove(position);
                }
            }
        }

        // for (Position position : result) {
        //     Board board = new Board(this);
        //     board.removePiece(start);
        //     board.addPiece(piece, position);
        //     if(board.isCheck(piece.isWhite())) {
        //         result.remove(position);
        //     }
        // }

        return result;
    }

    public boolean isCheck(boolean white) {
        Position kingPosition = this.getPosition(new King(white));
        Map<Position, Piece> allPieces = this.getAllPieces(!white);
        for (Position position : allPieces.keySet()) {
            if (this.getLegalMoves(allPieces.get(position).move(position), position).contains(kingPosition)) {
                System.out.println("Check by: " + allPieces.get(position) + " on " + position);
                return true;
            }
        }
        return false;
    }

    public String toString() {
        String result = "";
        for (int i=7; i >= 0; i--) {
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
