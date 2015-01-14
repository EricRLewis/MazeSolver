package cs2114.mazesolver;

/**
 * @author Eric Lewis (airshp12)
 * @version 2012.3.18
 *
 * The Maze class represents the maze board for the MazeSolver application.
 * It has a 2D array representing the board itself and also keeps track of the
 * size of the board.
 *
 */
public class Maze
    extends MazeBase
{
    /**
     * maze is the 2D-array representation of the maze board.
     */
    private MazeCell[][] theMaze;

    /**
     * This integer value is the size of the board.
     */
    private int mazeSize;


    /**
     * This is the constructor for the Maze class
     * @param size is a side length of the always square maze board.
     */
    public Maze(int size)
    {
        if (size > 1)
        {
            mazeSize = size;
            theMaze = new MazeCell[size][size];
            for (int i = 0; i < size; i++)
            {
                for (int j = 0; j < size; j++)
                {
                    theMaze[i][j] = MazeCell.UNEXPLORED;
                }
            }
        }
    }

    /**
     * This method will make the entire board unexplored
     */
    @Override
    public void clearMaze()
    {
        for (int i = 0; i < mazeSize; i++)
        {
            for (int j = 0; j < mazeSize; j++)
            {
                theMaze[j][i] = MazeCell.UNEXPLORED;
            }
        }
        setChanged();
        notifyObservers();
    }


    /**
     * This method returns the cell at the provided coordinates
     * @param row The row of the maze
     * @param col The column of the maze
     * @return The specified cell
     */
    @Override
    public MazeCell getCell(int col, int row)
    {
        if (row >= 0 && row < mazeSize && col >= 0 && col < mazeSize)
        {
            return theMaze[row][col];
        }
        else
        {
            return MazeCell.INVALID_CELL;
        }
    }


    /**
     * This method will return any paths to unexplored. It will leave the walls.
     */
    @Override
    public void resetPaths()
    {
        for (int i = 0; i < mazeSize; i++)
        {
            for (int j = 0; j < mazeSize; j++)
            {
                if (!getCell(j, i).equals(MazeCell.WALL))
                {
                    setCell(j, i, MazeCell.UNEXPLORED);
                }
            }
        }
        setChanged();
        notifyObservers();

    }


    /**
     * This method sets any cell to the specified cell value. The starting
     * cell and ending cell cannot be set to a wall value.
     * The user also cannot set a cell outside of the
     * maze.
     *
     * @param x The row
     * @param y The column
     * @param newCell The new cell value
     */
    @Override
    public void setCell(int y, int x, MazeCell newCell)
    {
        if (!getCell(x, y).equals(MazeCell.INVALID_CELL))
        {
            if (newCell.equals(MazeCell.WALL))
            {
                if (!(x == 0 && y == 0) &&
                    !(x == mazeSize - 1 && y == mazeSize - 1))
                {
                    theMaze[x][y] = newCell;
                }
            }

            else
            {
                theMaze[x][y] = newCell;
            }
        }
    }


    /**
     * This method returns the side length of the maze
     * @return size of the maze
     */
    @Override
    public int size()
    {
        if (theMaze != null)
        {
            return mazeSize;
        }

        else
        {
            return -1;
        }
    }

}
