package ChessInJava;

public class Position implements Comparable<Position>{
    private int file;
    private int rank;

    public Position(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    public Position(String position) {
        this.file = position.charAt(0) - 'a';
        this.rank = position.charAt(1) - '1';
    }

    public int getfile() {
        return file;
    }

    public void setfile(int file) {
        this.file = file;
    }

    public int getrank() {
        return rank;
    }

    public void setrank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        if (this.file < 0 || this.file > 7 ) {
            return "(" + this.file + ", " + this.rank + ")";
        }
        String[] files = {"a", "b", "c", "d", "e", "f", "g", "h"};
        return files[file] + (rank + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != this.getClass()) {
            return false;
        }
        return this.toString().equals(o.toString());
}

    @Override
    public int compareTo(Position o) {
        if (this.rank == o.getrank()) {
            return this.file - o.getfile();
        } if (this.rank > o.getrank()) {
            return 1;
        } else {
            return -1;
        }
    }

    
    
}