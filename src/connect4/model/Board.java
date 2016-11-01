package connect4.model;

import connect4.ConnectFour;

public class Board 
{
    private Player[][] board;
    private int emptySpaces = 0;
    private int boardSize;
    
    public Board()
    {
    	boardSize = ConnectFour.getBoardSize();
        board = new Player[boardSize][boardSize];
                
    }
    
    public void setStack(int row, int col, Player p){
    	board[row][col] = p;
    }
    
    
    public Player[][] getBoard()
    {
        return board;
    }
    
    public int getEmptySpaces()
    {
        for (int i = 0; i < ConnectFour.getBoardSize(); i++)
            
            for(int j = 0; j < ConnectFour.getBoardSize(); j++)
            {
                board[i][j] = null;
                emptySpaces++;
                
            }
        
        return emptySpaces;
    }
    
    public int getAvailbleRow(int column) {
		for (int r = boardSize - 1; r >= 0; r--) {
			if (board[r][column] == null) {
				return r;
			}
		}
		return -1;
	}
    
    public void checkRows()
    {
        for(int i = 0; i < ConnectFour.getBoardSize(); i++)
        {
            int matches = 0;
            for (int j = 1; j < ConnectFour.getBoardSize(); j++)
            {
                if(board[i][j] != null) // this checks whether or not its empty, since im using a int array atm
                {
                    if(board[i][j] == board[i][j-1]) // we would need to change these to colors
                    {
                        matches++;
                    }
                    else matches = 1;
                }   
                if(matches >= ConnectFour.getScoreToWin())
                {
                    System.out.println("Player" + " " + board[i][j] + " " + "Wins"); //this will be changed to end the game here + doing something else 
                }
                else continue;
                
            }
        }
        
    }
    
    public void checkColumns()
    {
        for(int j = 0; j < ConnectFour.getBoardSize(); j++)
        {
            int matches = 0;
            for (int i = 1; i < ConnectFour.getBoardSize(); i++)
            {
                if(board[i][j] != null) // this checks whether or not its empty, since im using a int array atm
                {
                    if(board[i][j] == board[i-1][j]) // we would need to change these to colors
                    {
                        matches++;
                    }
                    else matches = 1;
                }   
                if(matches >= ConnectFour.getScoreToWin())
                {
                    System.out.println("Player" + " " + board[i][j] + " " + "Wins"); //this will be changed to end the game here + doing something else 
                }
                else continue;
                
            }
        }
        
    }
    
    public void checkDiagnols()
    {
        for(int j = 0; j < ConnectFour.getBoardSize(); j++) // checks top left to bottom right
        {
            int matches = 0;
            for(int i = 1; i < ConnectFour.getBoardSize(); i++)
            {
                if(i + j > ConnectFour.getBoardSize())
                {
                    break;
                }
                else if(board[i][i+j] != null)
                    {
                        if(board[i-1][i+j-1] == board[i][i+j])
                        {
                            matches++;
                        }
                    }   
                else matches = 0;
                if(matches >= ConnectFour.getScoreToWin())
                {
                    System.out.println("Player" + " " + board[i][i+j] + " " + "Wins");
                }
            }
        
        }
        
            
        for(int i = 0; i < ConnectFour.getBoardSize(); i++)
        {
            int matches = 0;
            for(int j = 1; j < ConnectFour.getBoardSize(); i++)
                {
                    if(i + j >= ConnectFour.getBoardSize())
                    {
                        break;
                    }
                    if(board[i+j][j] != null)
                    {
                        if(board[i+j-1][j-1] == board[i+j][j])
                        {
                            matches++;
                        }
                    }
                    else matches = 1;
                    if(matches >= ConnectFour.getScoreToWin())
                    {
                        System.out.println("Player" + " " + board[i+j][j] + " " + "Wins");
                    }
                }
        }
        
        for(int j = 0; j < ConnectFour.getBoardSize(); j++ )// checks top right to bottom left
        {
            int matches = 0;
            for(int i = 1; i < ConnectFour.getBoardSize(); i++)
            {
                if(j-i < 0)
                {
                    break;
                }
                if(board[i][j-i] != null)
                {
                    if(board[i-1][j-i+1] == board[i][j-i])
                    {
                        matches++;
                    }
                }
                else
                    matches = 1;
                
                if(matches >= 4)
                {
                    System.out.println("Player" + board[i][i+j] + "Wins");
                }
            }
            
        }
        for(int i = 0; i < ConnectFour.getBoardSize(); i++)
        {
            int matches = 0;
            for(int j = ConnectFour.getBoardSize()-2; j >=0; j--)
            {
                if(j-i < 0)
                {
                    break;
                }
                if(board[j-i][j] != null)
                {
                    if(board[j-i-1][j+1] == board[j-i][j])
                    {
                        matches++;
                    }
                }
                else
                    matches = 1;
                if(matches >= 4)
                {
                    System.out.println("Player" + " " + board[j-1][j] + " " + "Wins");
                }
            }
        }
        
        
    }   
    
    public void checkDraw()
    {
        if(emptySpaces == 0)
        {
            System.out.println("it's a draw!");
        }
    }
    
}