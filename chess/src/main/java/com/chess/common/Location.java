package com.chess.common;

import java.util.Objects;

public class Location {

    private final ChessFiles file;
    private final int rank;

    public Location(ChessFiles file, int rank) {
        this.rank = rank;
        this.file = file;


    }

    public ChessFiles getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return getRank() == location.getRank() && getFile() == location.getFile();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFile(), getRank());
    }

    @Override
    public String toString() {
        return file.toString() +
           rank
            ;
    }
}
