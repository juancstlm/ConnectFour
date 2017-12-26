package connect4.model;

public class Board {
    private Node gameboard[];
    private static final int HEIGHT = 6;
    private static final int WIDTH = 7;
    private static final int SCORE_TO_WIN = 4;
    private Node NILL;
    private int scoreToWin;
    private int height;
    private int width;

    private boolean winByCols =  false;
    private boolean winbyDiag = false;
    private boolean winbyRow = false;
    private boolean draw = false;


    public Board() {
        this(HEIGHT, WIDTH, SCORE_TO_WIN);
    }

    public Board(int height, int width) {
        this(height, width, SCORE_TO_WIN);
    }

    public Board(int height, int width, int scoreToWin) {

        // Creating the NILL Node
        NILL = new Node(-1);
        NILL.setTop(NILL);
        NILL.setLeft(NILL);
        NILL.setBottom(NILL);
        NILL.setRight(NILL);
        NILL.setHeight(-1);

        this.height = height;
        this.width = width;
        this.scoreToWin = scoreToWin;
        gameboard = new Node[this.width];

        for(int i = 0; i<width;i++){
            gameboard[i] = NILL;
        }
    }

    private void checkWinConditions(Node n){
        checkDiag(n);
        checkVertical(n);
    }

    private void checkVertical(Node n){
        int score = 1;
        if(n.getTop().getPlayer() == n.getPlayer()){
            Node check = n.getTop();
            score++;
            while(check.getTop().getPlayer() == n.getPlayer() && check != NILL ){
                check = check.getTop();
                score++;
            }

            if(score >= scoreToWin){
                winByCols = score >= scoreToWin;
                return;
            }
        } else if(n.getBottom().getPlayer() == n.getPlayer()){
            score = 1;
            Node check = n.getBottom();
            score++;
            while(check.getBottom().getPlayer() == n.getPlayer() && check != NILL ){
                score++;
                check = check.getBottom();
            }
            if(score >= scoreToWin){
                winByCols = score >= scoreToWin;
                return;
            }
        }

    }


    private void checkDiag(Node n){
        //TODO fully implement this
        int score = 1;
        if(n.getTopLeft().getPlayer() == n.getPlayer()){
            Node check = n.getTopLeft();
            score++;
            while(check.getTopLeft().getPlayer() == n.getPlayer() && check != NILL){
                check = check.getTopLeft();
                score++;
            }
            if(score >= scoreToWin){
                winbyDiag = score >= scoreToWin;
                return;
            }
        } else if (n.getTopRight().getPlayer() == n.getPlayer()){
            score = 1;
            Node check = n.getTopRight();
            score++;
            while(check.getTopRight().getPlayer() == n.getPlayer() && check != NILL){
                check = check.getTopRight();
                score++;
            }
            if(score >= scoreToWin){
                winbyDiag = score >= scoreToWin;
                return;
            }

        } else if(n.getBottomLeft().getPlayer() == n.getPlayer()){
            score = 1;
            Node check = n.getBottomLeft();
            score++;
            while(check.getBottomLeft().getPlayer() == n.getPlayer() && check != NILL){
                check = check.getBottomLeft();
                score++;
            }
            if(score >= scoreToWin){
                winbyDiag = score >= scoreToWin;
                return;
            }

        } else if(n.getBottomRight().getPlayer() == n.getPlayer()){
            score = 1;
            Node check = n.getBottomRight();
            score++;
            while(check.getBottomRight().getPlayer() == n.getPlayer() && check != NILL){
                check = check.getBottomRight();
                score++;
            }
            if(score >= scoreToWin){
                winbyDiag = score >= scoreToWin;
                return;
            }
        }
    }

    public int insert(int col, int player){
        Node n = new Node(player);
        n.setTop(NILL);
        Node parent = getHighestNode(col);
        if(parent == NILL){
            n.setBottom(NILL);
            n.setBottomLeft(NILL);
            n.setBottomRight(NILL);
            Node left = getNode(col-1, 0);
            Node right = getNode(col+1,0);
            Node topRight = getNode(col+1, 1);
            Node topLeft = getNode(col-1, 1);
            if(left != NILL) {
                left.setRight(n);
            } if(right != NILL){
                right.setLeft(n);
            } if(topLeft != NILL){
                topLeft.setBottomRight(n);
            } if(topRight != NILL){
                topRight.setBottomLeft(n);
            }
            n.setLeft(left);
            n.setRight(right);
            n.setTopLeft(topLeft);
            n.setTopRight(topRight);
            n.setHeight(parent.getHeight()+1);
            gameboard[col] = n;

            checkWinConditions(n);

            return height - n.getHeight();
        } else if(parent.getHeight() < height){
            n.setBottom(parent);
            int curHeight = parent.getHeight()+1;
            parent.setTop(n);
            Node left = getNode(col-1, curHeight);
            Node right = getNode(col+1,curHeight);
            Node topRight = getNode(col+1, curHeight+1);
            Node topLeft = getNode(col-1, curHeight+1);
            Node bottomLeft = getNode(col-1,curHeight-1);
            Node bottomRight = getNode(col+1,curHeight-1);
            if(left != NILL) {
                left.setRight(n);
            } if(right != NILL){
                right.setLeft(n);
            } if(topLeft != NILL){
                topLeft.setBottomRight(n);
            } if(topRight != NILL){
                topRight.setBottomLeft(n);
            } if(bottomLeft != NILL){
                bottomLeft.setTopRight(n);
            } if(bottomRight != NILL){
                bottomRight.setTopLeft(n);
            }
            n.setLeft(left);
            n.setRight(right);
            n.setTopLeft(topLeft);
            n.setTopRight(topRight);
            n.setBottomLeft(bottomLeft);
            n.setBottomRight(bottomRight);

            n.setHeight(parent.getHeight()+1);

            checkWinConditions(n);

            return height - n.getHeight();
        } else{
            return -1;
        }
    }

    public void clear(){
        //TODO implement
    }

    public boolean checkColumns(){
        //TODO implement
        return winByCols;
    }

    /**
     * Returns the node situated at the specified column and height
     * @param col
     * @param height
     * @return
     */
    private Node getNode(int col, int height){
        //TODO add condition for height bigger than the the actual height
        if(col <0 || col >= width){
            return NILL;
        } else {
            //TODO fix this
            Node n = gameboard[col];

            while(n.getHeight() != height){
                if(n == NILL){
                    return NILL;
                }
                n = n.getTop();
            }
            return n;
        }
    }

    /**
     * Gets the highest node in the specified column
     * @param col
     * @return
     */
    private Node getHighestNode(int col){
        Node n = gameboard[col];
        if(n == NILL){
            return NILL;
        } else {
            while(n.getTop() != NILL){
                n = n.getTop();
            }
            return n;
        }
    }

    public boolean checkDiagonals() {
        return winbyDiag;
    }

    public boolean checkRows() {
        return winbyRow;
    }

    public boolean checkDraw() {
        return draw;
    }


    private class Node {

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

        private void setLeft(Node n) {
            this.left = n;
        }

        private void setRight(Node n) {
            this.right = n;
        }

        private void setBottom(Node n) {
            this.bottom = n;
        }

        private void setTop(Node n) {
            this.top = n;
        }

        private int getPlayer() {
            return player;
        }

        public Node getTop() {
            return top;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        Node getBottom() {
            return bottom;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public Node getBottomRight() {
            return bottomRight;
        }

        public void setBottomRight(Node bottomRight) {
            this.bottomRight = bottomRight;
        }

        public Node getBottomLeft() {
            return bottomLeft;
        }

        public void setBottomLeft(Node bottomLeft) {
            this.bottomLeft = bottomLeft;
        }

        public Node getTopLeft() {
            return topLeft;
        }

        public void setTopLeft(Node topLeft) {
            this.topLeft = topLeft;
        }

        public Node getTopRight() {
            return topRight;
        }

        public void setTopRight(Node topRight) {
            this.topRight = topRight;
        }

        public String toString(){
            if(player == -1){
                return "NILL";
            }
            else return "Player: " + String.valueOf(player);
        }
    }
}
