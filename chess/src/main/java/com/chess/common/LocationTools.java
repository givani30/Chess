package com.chess.common;

public class LocationTools {
    private static final ChessFiles[] files= ChessFiles.values();

    /**
     * Builds a new location from the current one +offset
     * @param current The current Location
     * @param fileOffset The file offset
     * @param rankOffset The rank offset
     * @return A new location
     */
    public static Location build(Location current, Integer fileOffset,Integer rankOffset){
    Integer currentFile = current.getFile().ordinal();
        int fileLoc = currentFile + fileOffset;
        if(fileLoc < 0||fileLoc>7 || rankOffset+current.getRank()<0){
            return null;
        }
    return new Location(files[currentFile +fileOffset],current.getRank()+rankOffset);
    }
}
