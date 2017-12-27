package connect4.model.Connect4;

/**
 * A class to model a nxk connect 4 game board.
 */
public class Board {

    private Node gameboard[];
    private static final int HEIGHT = 6;
    private static final int WIDTH = 7;
    private static final int SCORE_TO_WIN = 4;
    private Node NILL;
    private int scoreToWin;
    private int height;
    private int width;

    private boolean winByCols = false;
    private boolean winbyDiag = false;
    private boolean winbyRow = false;
    private boolean draw = false;


    public Board() {
        this(HEIGHT, WIDTH, SCORE_TO_WIN);
    }

    public Board(int height, int width, int scoreToWin) {
        //Assign the NILL node
        NILL = Nill.getIntance();

        this.height = height;
        this.width = width;
        this.scoreToWin = scoreToWin;
        gameboard = new Node[this.width];

        for (int i = 0; i < width; i++) {
            gameboard[i] = NILL;
        }
    }

    private void checkWinConditions(Node n) {
        checkDiag(n);
        checkVertical(n);
        checkRows(n);
    }

    private void checkVertical(Node n) {
        int score = 1;
        if (n.getTop().getPlayer() == n.getPlayer()) {
            Node check = n.getTop();
            score++;
            while (check.getTop().getPlayer() == n.getPlayer() && check != NILL) {
                check = check.getTop();
                score++;
            }

            if (score >= scoreToWin) {
                winByCols = score >= scoreToWin;
                return;
            }
        } else if (n.getBottom().getPlayer() == n.getPlayer()) {
            score = 1;
            Node check = n.getBottom();
            score++;
            while (check.getBottom().getPlayer() == n.getPlayer() && check != NILL) {
                score++;
                check = check.getBottom();
            }
            if (score >= scoreToWin) {
                winByCols = score >= scoreToWin;
                return;
            }
        }

    }

    private void checkRows(Node n){
        int score = 1;
        if(n.getLeft().getPlayer() == n.getPlayer()){
            Node check = n.getLeft();
            score++;
            while(check.getLeft().getPlayer() == n.getPlayer() && check != NILL){
                score ++;
                check = check.getLeft();
            }
            if (score >= scoreToWin) {
                winbyRow = score >= scoreToWin;
                return;
            }
        } else if(n.getRight().getPlayer() == n.getPlayer()){
            score = 1;
            Node check = n.getRight();
            while(check.getRight().getPlayer() == n.getPlayer() && check != NILL){
                score ++;
                check = check.getRight();
            }
            if (score >= scoreToWin) {
                winbyRow = score >= scoreToWin;
                return;
            }
        }
    }

    private void checkDiag(Node n) {
        //TODO fully implement this
        int score = 1;
        if (n.getTopLeft().getPlayer() == n.getPlayer()) {
            Node check = n.getTopLeft();
            score++;
            while (check.getTopLeft().getPlayer() == n.getPlayer() && check != NILL) {
                check = check.getTopLeft();
                score++;
            }
            if (score >= scoreToWin) {
                winbyDiag = score >= scoreToWin;
                return;
            }
        } else if (n.getTopRight().getPlayer() == n.getPlayer()) {
            score = 1;
            Node check = n.getTopRight();
            score++;
            while (check.getTopRight().getPlayer() == n.getPlayer() && check != NILL) {
                check = check.getTopRight();
                score++;
            }
            if (score >= scoreToWin) {
                winbyDiag = score >= scoreToWin;
                return;
            }

        } else if (n.getBottomLeft().getPlayer() == n.getPlayer()) {
            score = 1;
            Node check = n.getBottomLeft();
            score++;
            while (check.getBottomLeft().getPlayer() == n.getPlayer() && check != NILL) {
                check = check.getBottomLeft();
                score++;
            }
            if (score >= scoreToWin) {
                winbyDiag = score >= scoreToWin;
                return;
            }

        } else if (n.getBottomRight().getPlayer() == n.getPlayer()) {
            score = 1;
            Node check = n.getBottomRight();
            score++;
            while (check.getBottomRight().getPlayer() == n.getPlayer() && check != NILL) {
                check = check.getBottomRight();
                score++;
            }
            if (score >= scoreToWin) {
                winbyDiag = score >= scoreToWin;
                return;
            }
        }
    }

    /**
     * Inserts a players game piece into the specified column
     * @param col the column where to place the game piece
     * @param player the player id
     * @return the row where the game piece was placed
     */
    public int insert(int col, int player) {
        Node n = new Node(player);
        n.setTop(NILL);
        Node parent = getHighestNode(col);
        if (parent == NILL) {
            n.setBottom(NILL);
            n.setBottomLeft(NILL);
            n.setBottomRight(NILL);
            Node left = getNode(col - 1, 0);
            Node right = getNode(col + 1, 0);
            Node topRight = getNode(col + 1, 1);
            Node topLeft = getNode(col - 1, 1);
            if (left != NILL) {
                left.setRight(n);
            }
            if (right != NILL) {
                right.setLeft(n);
            }
            if (topLeft != NILL) {
                topLeft.setBottomRight(n);
            }
            if (topRight != NILL) {
                topRight.setBottomLeft(n);
            }
            n.setLeft(left);
            n.setRight(right);
            n.setTopLeft(topLeft);
            n.setTopRight(topRight);
            n.setHeight(parent.getHeight() + 1);
            gameboard[col] = n;

            checkWinConditions(n);

            return n.getHeight();
        } else if (parent.getHeight() < height) {
            n.setBottom(parent);
            int curHeight = parent.getHeight() + 1;
            parent.setTop(n);
            Node left = getNode(col - 1, curHeight);
            Node right = getNode(col + 1, curHeight);
            Node topRight = getNode(col + 1, curHeight + 1);
            Node topLeft = getNode(col - 1, curHeight + 1);
            Node bottomLeft = getNode(col - 1, curHeight - 1);
            Node bottomRight = getNode(col + 1, curHeight - 1);
            if (left != NILL) {
                left.setRight(n);
            }
            if (right != NILL) {
                right.setLeft(n);
            }
            if (topLeft != NILL) {
                topLeft.setBottomRight(n);
            }
            if (topRight != NILL) {
                topRight.setBottomLeft(n);
            }
            if (bottomLeft != NILL) {
                bottomLeft.setTopRight(n);
            }
            if (bottomRight != NILL) {
                bottomRight.setTopLeft(n);
            }
            n.setLeft(left);
            n.setRight(right);
            n.setTopLeft(topLeft);
            n.setTopRight(topRight);
            n.setBottomLeft(bottomLeft);
            n.setBottomRight(bottomRight);

            n.setHeight(parent.getHeight() + 1);

            checkWinConditions(n);

            return n.getHeight();
        } else {
            return -1;
        }
    }

    /**
     * Clears the game board
     */
    public void clear() {
        for(int i = 0; i< gameboard.length; i++){
            gameboard[i] = NILL;
        }

        winbyRow = false;
        winbyDiag = false;
        winByCols = false;
    }

    public boolean checkColumns() {
        //TODO implement
        return winByCols;
    }

    /**
     * Returns the node situated at the specified column and height
     *
     * @param col
     * @param height
     * @return
     */
    private Node getNode(int col, int height) {
        //TODO add condition for height bigger than the the actual height
        if (col < 0 || col >= width) {
            return NILL;
        } else {
            //TODO fix this
            Node n = gameboard[col];

            while (n.getHeight() != height) {
                if (n == NILL) {
                    return NILL;
                }
                n = n.getTop();
            }
            return n;
        }
    }

    /**
     * Gets the highest node in the specified column
     *
     * @param col the column to get the highest node from
     * @return the node at the highest level
     */
    private Node getHighestNode(int col) {
        Node n = gameboard[col];
        if (n == NILL) {
            return NILL;
        } else {
            while (n.getTop() != NILL) {
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

}
