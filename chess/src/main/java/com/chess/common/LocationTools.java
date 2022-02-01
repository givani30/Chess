package com.chess.common;

public class LocationTools {
    private static final File[] files=File.values();

    /**
     * Builds a new location from the current one +offset
     * @param current The current Location
     * @param fileOffset The file offset
     * @param rankOffset The rank offset
     * @return A new location
     */
    public static Location build(Location current, Integer fileOffset,Integer rankOffset){
    Integer currentFile = current.getFile().ordinal();
    return new Location(files[currentFile +fileOffset],current.getRank()+rankOffset);
    }
}
