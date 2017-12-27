package connect4.model.Connect4;

public final class Nill extends Node{
    private static volatile Nill instance = null;

    private Nill(){
        super(-1);
        super.setHeight(-1);
        super.setTopLeft(this);
        super.setTopRight(this);
        super.setBottomLeft(this);
        super.setTop(this);
        super.setBottom(this);
        super.setRight(this);
        super.setBottomRight(this);
        super.setLeft(this);
    }

    public static Nill getIntance(){
        if(instance == null){
            synchronized (Nill.class){
                if(instance == null){
                    instance = new Nill();
                }
            }
        }
        return instance;
    }

    public String toString() {
        return "Nill";
    }
}
