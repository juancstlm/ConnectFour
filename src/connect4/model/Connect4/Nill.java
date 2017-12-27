package connect4.model.Connect4;

/**
 * An extension of the node class where the node will always link to itself and there is only one
 * instance of this nill node.
 * This nill class is thread safe.
 */
public final class Nill extends Node {
    private static volatile Nill instance = null;

    /**
     * Private constructor for the nill node
     * Links everything back to the nill node
     * Parent and height should be set to -1
     */
    private Nill() {
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

    /**
     * Gets the instance of the Nill class node
     *
     * @return the Nill node
     */
    public static Nill getIntance() {
        if (instance == null) {
            synchronized (Nill.class) {
                if (instance == null) {
                    instance = new Nill();
                }
            }
        }
        return instance;
    }


    @Override
    void setLeft(Node n) {
    }

    void setRight(Node n) {
    }

    void setBottom(Node n) {
    }

    void setTop(Node n) {
    }

    void setBottomRight(Node bottomRight) {
    }

    void setBottomLeft(Node bottomLeft) {
    }

    void setTopLeft(Node topLeft) {
    }

    void setTopRight(Node topRight) {
    }

    void setHeight(int height){
    }

    /**
     * To string method of the nill node
     * Should just return "Nill"
     *
     * @return
     */
    public String toString() {
        return "Nill";
    }
}
