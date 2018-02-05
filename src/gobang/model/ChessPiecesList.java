package gobang.model;

import java.util.ArrayList;

/**
 * this ChessPieceList is used to store pieces in order of dropping
 * offer point to determine the position of one piece,
 * thus we can get a range of pieces each time
 */

public class ChessPiecesList {
    private ArrayList<ChessPiece> list;
    private int point;

    private static ChessPiecesList cplist = new ChessPiecesList();

    public static ChessPiecesList getPieceList(){
        if(cplist == null)
            cplist = new ChessPiecesList();
        return cplist;
    }

    private ChessPiecesList(){
        list = new ArrayList<>();
        point = -1;
    }

    public void addPoint(){
        point = (point + 1 >= list.size()) ? (list.size() - 1) : (point + 1);
    }

    public void subPoint(){
        point = (point - 1 < 0) ? -1 : point - 1;
    }

    public int PointValue(){
        return point;
    }

    public ChessPiece getPiece(int i){
        return list.get(i);
    }

    public int getSize(){
        return list.size();
    }

    public void addPiece(ChessPiece p){
        list.add(p);
    }

    public ChessPiece removePiece(){
        if(!list.isEmpty())
            return list.remove(list.size() - 1);
        return null;
    }

    public void reStart(){
        list.clear();
        point = -1;
    }
}
