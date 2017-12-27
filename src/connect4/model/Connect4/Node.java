package connect4.model.Connect4;

class Node {

    private int player;
    private int height;
    private Node topLeft;
    private Node topRight;
    private Node bottomLeft;
    private Node bottomRight;
    private Node top;
    private Node left;
    private Node right;
    private Node bottom;

    Node(int player) {
        this.player = player;
    }

    void setLeft(Node n) {
        this.left = n;
    }

    void setRight(Node n) {
        this.right = n;
    }

    void setBottom(Node n) {
        this.bottom = n;
    }

    void setTop(Node n) {
        this.top = n;
    }

    int getPlayer() {
        return player;
    }

    Node getTop() {
        return top;
    }

    Node getLeft() {
        return left;
    }

    Node getRight() {
        return right;
    }

    Node getBottom() {
        return bottom;
    }

    int getHeight() {
        return height;
    }

    void setHeight(int height) {
        this.height = height;
    }

    Node getBottomRight() {
        return bottomRight;
    }

    void setBottomRight(Node bottomRight) {
        this.bottomRight = bottomRight;
    }

    Node getBottomLeft() {
        return bottomLeft;
    }

    void setBottomLeft(Node bottomLeft) {
        this.bottomLeft = bottomLeft;
    }

    Node getTopLeft() {
        return topLeft;
    }

    void setTopLeft(Node topLeft) {
        this.topLeft = topLeft;
    }

    Node getTopRight() {
        return topRight;
    }

    void setTopRight(Node topRight) {
        this.topRight = topRight;
    }

    public String toString() {
        return "Player: " + String.valueOf(player);
    }
}
