package org.ru.babidzhonio.board;

public enum Vertical {
    A, B, C, D, E, F, G, H;

    public static Vertical fromChar(char c){
        try {
            return Vertical.valueOf(String.valueOf(c).toUpperCase());
        }
        catch (IllegalArgumentException e){
            return null;
        }
    }

    public String getLetter(){
        return this.name();
    }
}
