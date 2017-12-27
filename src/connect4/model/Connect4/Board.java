package connect4.model.Connect4;

/**
 * A class to model a n x k connect 4 game board with m score needed to win.
 */
public class Board {

    // The root nodes of the game board.
    private Node gameboard[];
    // Default sizes
    private static final int HEIGHT = 6;
    private static final int WIDTH = 7;
    private static final int SCORE_TO_WIN = 4;
    private Node NILL; // NILL node used to check the edges
    private int scoreToWin;
    private int height;
    private int width;

    private boolean winByCols = false;
    private boolean winbyDiag = false;
    private boolean winbyRow = false;
    private boolean draw = false;


    /**
     * Creates a default size game board with score to win of 4, width of 7, and height of 6
     */
    public Board() {
        this(HEIGHT, WIDTH, SCORE_TO_WIN);
    }

    /**
     * Creates a game board with the specified parameters of height, width, and score to win.
     *
     * @param height     the height of the board
     * @param width      the width of the board
     * @param scoreToWin the score that is required to win
     */
    public Board(int height, int width, int scoreToWin) {

        //Assign the NILL node
        NILL = Nill.getIntance();

        this.height = height;
        this.width = width;
        this.scoreToWin = scoreToWin;
        gameboard = new Node[this.width];

        // Set all the root nodes to be the NILL node
        for (int i = 0; i < width; i++) {
            gameboard[i] = NILL;
        }
    }

    private void checkWinConditions(Node n) {
        checkDiag(n);
        checkColumn(n);
        checkRow(n);
    }

    /**
     * Private method to check if there are the required number of matches in a column
     *
     * @param n
     */
    private void checkColumn(Node n) {
        // Start matches at 1
        int matches = 1;
        // Check that the bottom node is from the same player
        if (n.getBottom().getPlayer() == n.getPlayer()) {
            // the the check node to be the bottom node
            Node check = n.getBottom();
            // increase the number of matches
            matches++;
            // while the bottom node is by the same player and its not null
            while (check.getBottom().getPlayer() == n.getPlayer() && check != NILL) {
                // increase the number of matches
                matches++;
                // set the new bottom noe as the check node
                check = check.getBottom();
            }

            // set the win win condition to be true if the number of matches is greater than or equal to
            // the score required to win
            winByCols = matches >= scoreToWin;
        }

    }

    /**
     * Checks the row to see if there are the number of wining nodes in a row
     *
     * @param n The node from where to start checking
     */
    private void checkRow(Node n) {
        // Start the matches at 1
        int matches = 1;
        if (n.getLeft().getPlayer() == n.getPlayer()) {
            Node check = n.getLeft();
            matches++;
            while (check.getLeft().getPlayer() == n.getPlayer() && check != NILL) {
                matches++;
                check = check.getLeft();
            }
        }
        if (n.getRight().getPlayer() == n.getPlayer()) {
            Node check = n.getRight();
            matches++;
            while (check.getRight().getPlayer() == n.getPlayer() && check != NILL) {
                matches++;
                check = check.getRight();
            }
        }
        winbyRow = matches >= scoreToWin;
    }

    /**
     * @param n
     */
    private void checkDiag(Node n) {
        //TODO fully implement this
        // Check \  in this diagonal direction
        int matches = 1;
        if (n.getTopLeft().getPlayer() == n.getPlayer()) {
            Node check = n.getTopLeft();
            matches++;
            while (check.getTopLeft().getPlayer() == n.getPlayer() && check != NILL) {
                check = check.getTopLeft();
                matches++;
            }
        } if (n.getBottomRight().getPlayer() == n.getPlayer()) {
            Node check = n.getBottomRight();
            matches++;
            while (check.getBottomRight().getPlayer() == n.getPlayer() && check != NILL) {
                check = check.getBottomRight();
                matches++;
            }
        }
        // set the win conditions according
        winbyDiag = matches >= scoreToWin;
        // reset the matches back to 1
        matches =1;
        if (n.getTopRight().getPlayer() == n.getPlayer()) {
            Node check = n.getTopRight();
            matches++;
            while (check.getTopRight().getPlayer() == n.getPlayer() && check != NILL) {
                check = check.getTopRight();
                matches++;
            }
        } if (n.getBottomLeft().getPlayer() == n.getPlayer()) {
            Node check = n.getBottomLeft();
            matches++;
            while (check.getBottomLeft().getPlayer() == n.getPlayer() && check != NILL) {
                check = check.getBottomLeft();
                matches++;
            }
        }

        // set the win conditions according
        winbyDiag = (winbyDiag || matches >= scoreToWin);
    }

    /**
     * Inserts a players game piece into the specified column
     *
     * @param col    the column where to place the game piece
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
        for (int i = 0; i < gameboard.length; i++) {
            gameboard[i] = NILL;
        }

        // Set the win conditions to be false
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
