package connect4.model.Connect4;

import javafx.util.Pair;

import java.util.LinkedList;

/**
 * A class to model a n x k connect 4 game board with m score needed to win.
 */
public class Board {

    // The root nodes of the game board.
    private Node gameboard[];
    private Node NILL; // NILL node used to check the edges
    // Default sizes
    private static final int HEIGHT = 6;
    private static final int WIDTH = 7;
    private static final int SCORE_TO_WIN = 4;
    // Sizing
    private int scoreToWin;
    private int height;
    private int width;

    private LinkedList<Node> moves;
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

        // set the sizing accordingly
        this.height = height;
        this.width = width;
        this.scoreToWin = scoreToWin;
        gameboard = new Node[this.width];

        // Set all the root nodes to be the NILL node
        for (int i = 0; i < width; i++) {
            gameboard[i] = NILL;
        }

        // Initialize the moves queue
        moves = new LinkedList<>();
    }

    private void checkWinConditions(Node n) {
        checkDiag(n);
        checkColumn(n);
        checkRow(n);
        checkDraw(n);
    }

    /**
     * Checks
     *
     * @param n
     */
    private void checkDraw(Node n) {
        draw = (moves.size() == width * height);
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
        }
        if (n.getBottomRight().getPlayer() == n.getPlayer()) {
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
        matches = 1;
        if (n.getTopRight().getPlayer() == n.getPlayer()) {
            Node check = n.getTopRight();
            matches++;
            while (check.getTopRight().getPlayer() == n.getPlayer() && check != NILL) {
                check = check.getTopRight();
                matches++;
            }
        }
        if (n.getBottomLeft().getPlayer() == n.getPlayer()) {
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

    //TODO implement a fucntion to check draw conditions

    /**
     * Inserts a players game piece into the specified column and returns the row where the game piece was placed.
     *
     * @param col    the column where to place the game piece
     * @param player the player id
     * @return the row where the game piece was placed
     */
    public int insert(int col, int player) throws Exception {
        Node n = new Node(player);
        n.setTop(NILL);
        Node parent = getHighestNode(col);
        // If the parent node is the NILL node. The node n will be the first node in the column
        if (parent == NILL) {
            // Set the bottom of the node to be the NILL node
            n.setBottom(NILL);
            n.setBottomLeft(NILL);
            n.setBottomRight(NILL);
            // Get the neighboring nodes
            Node left = getNode(col - 1, 0);
            Node right = getNode(col + 1, 0);
            Node topRight = getNode(col + 1, 1);
            Node topLeft = getNode(col - 1, 1);

            // Set the neighboring nodes to point to the new node
            left.setRight(n);
            right.setLeft(n);
            topLeft.setBottomRight(n);
            topRight.setBottomLeft(n);
            // Set the appropriate neighboring nodes
            n.setLeft(left);
            n.setRight(right);
            n.setTopLeft(topLeft);
            n.setTopRight(topRight);
            // Set the height of the node as the height of the parent plus 1
            n.setHeight(parent.getHeight() + 1);
            // set this node as the root of the column
            gameboard[col] = n;
            // add this node to the list of moves
            moves.add(n);

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
            left.setRight(n);
            right.setLeft(n);
            topLeft.setBottomRight(n);
            topRight.setBottomLeft(n);
            bottomLeft.setTopRight(n);
            bottomRight.setTopLeft(n);
            n.setLeft(left);
            n.setRight(right);
            n.setTopLeft(topLeft);
            n.setTopRight(topRight);
            n.setBottomLeft(bottomLeft);
            n.setBottomRight(bottomRight);

            n.setHeight(parent.getHeight() + 1);
            moves.add(n);

            checkWinConditions(n);

            return n.getHeight();
        } else {
            throw new Exception("Column specified is full. Cannot insert player's game piece");
        }
    }

    /**
     * Undoes the last insert that was made into the game board
     */
    public Pair undoLast() throws NullPointerException {
        if (!moves.isEmpty()) {
            Node n = moves.removeLast();
            // Find all the nodes that link to this node
            Node top = n.getTop();
            Node bottom = n.getBottom();
            Node right = n.getRight();
            Node left = n.getLeft();
            Node topLeft = n.getTopLeft();
            Node topRight = n.getTopRight();
            Node bottomLeft = n.getBottomLeft();
            Node bottomRight = n.getBottomRight();
            top.setBottom(NILL);
            left.setRight(NILL);
            right.setLeft(NILL);
            bottom.setTop(NILL);
            topLeft.setBottomRight(NILL);
            topRight.setBottomLeft(NILL);
            bottomLeft.setTopRight(NILL);
            bottomRight.setTopLeft(NILL);
            // Set all the nodes that link to this node to link to the NILL node
            // return the position of the removed node
            return {n.getHeight(),0};
        } else {
            throw new NullPointerException();
        }
    }


    /**
     * Gets the number of moves that have been performed in the current game
     *
     * @return the number of moves of the current board
     */
    public int getMoveCount() {
        return moves.size();
    }

    /**
     * Clears the game board
     */
    public void clear() {
        // Set all the root noes to be the NILL node
        for (int i = 0; i < gameboard.length; i++) {
            gameboard[i] = NILL;
        }
        // Set the win conditions to be false
        winbyRow = false;
        winbyDiag = false;
        winByCols = false;
    }

    public boolean checkColumns() {
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
