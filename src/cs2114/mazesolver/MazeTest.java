package cs2114.mazesolver;

import junit.framework.TestCase;

/**
 * @author Eric Lewis (airshp12)
 * @version 2012.3.18
 * This Class tests the Maze class and it's methods
 *
 */
public class MazeTest
    extends TestCase
{
    /**
     * This data member is the maze instance being tested
     */
    private Maze maze;

    /**
     * This is the constructor for the MazeTest class
     */
    public MazeTest()
    {
        // empty constructor
    }


    /**
     * This method sets up the data member to have tests run on it
     */
    public void setUp()
    {
        maze = new Maze(6);
        maze.loadMazeState(
            " *   *",
            " * * *",
            "   * *",
            "**** *",
            "     *",
            "      ");
    }

    /**
     * This method tests the set and get Cell methods
     */
    public void testGetAndSetCell()
    {
        maze.setCell(0, 0, MazeCell.WALL);
        maze.setCell(0, 5, MazeCell.WALL);

        assertMaze(maze,
            " *   *",
            " * * *",
            "   * *",
            "**** *",
            "     *",
            "*     ");

        assertEquals(MazeCell.WALL, maze.getCell(5, 4));
        assertEquals(MazeCell.INVALID_CELL, maze.getCell(-1, 0));
        assertEquals(MazeCell.INVALID_CELL, maze.getCell(100, 0));
        assertEquals(MazeCell.INVALID_CELL, maze.getCell(0, -3));
        assertEquals(MazeCell.INVALID_CELL, maze.getCell(0, 50));
    }


    /**
     * Tests the clearMaze method
     */
    public void testClearMaze()
    {
        maze.setCell(2, 2, MazeCell.WALL);
        maze.clearMaze();
        assertEquals(MazeCell.UNEXPLORED, maze.getCell(2, 2));
    }
    /**
     * This method tests the resetPaths() method
     */
    public void testResetPaths()
    {
        maze.setCell(0, 0, MazeCell.CURRENT_PATH);
        maze.setCell(0, 1, MazeCell.CURRENT_PATH);
        maze.setCell(0, 2, MazeCell.CURRENT_PATH);
        maze.setCell(0, 5, MazeCell.UNEXPLORED);

        assertMaze(maze,
            ".*   *",
            ".* * *",
            ".  * *",
            "**** *",
            "     *",
            "      ");




        maze.resetPaths();

        assertMaze(maze,
            " *   *",
            " * * *",
            "   * *",
            "**** *",
            "     *",
            "      ");
    }

    /**
     * This method tests the size method
     */
    public void testSize()
    {
        assertEquals(maze.size(), 6);
        maze = new Maze(0);
        assertEquals(maze.size(), -1);

    }


       /**
        * This method helps test mazes
        * @param theMaze the maze you are testing
        * @param expected the expected maze board
        */
    public void assertMaze(Maze theMaze, String... expected)
    {
        Maze expectedMaze = new Maze(expected.length);
        expectedMaze.loadMazeState(expected);
        assertEquals(expectedMaze, theMaze);
    }
}
