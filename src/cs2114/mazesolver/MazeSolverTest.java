package cs2114.mazesolver;


import junit.framework.TestCase;

/**
 * @author Eric Lewis (airshp12)
 * @version 2012.3.18
 *
 * This class tests the MazeSolver Class and it's methods.
 *
 */
public class MazeSolverTest
    extends TestCase
{
    private MazeSolver solver;
    private MazeSolver solver2;
    private MazeSolver solver3;
    private MazeSolver solver4;
    private MazeSolver solver5;
    private Maze maze;
    private Maze maze2;
    private Maze maze3;
    private Maze maze4;
    private Maze maze5;

    /**
     * This is the constructor for the test class
     */
    public MazeSolverTest()
    {
        //Empty constructor
    }

    /**
     * This method sets up the MazeSolver instance to test it.
     */
    public void setUp()
    {
        maze = new Maze(4);
        maze.setCell(0, 1, MazeCell.WALL);
        maze.setCell(1, 1, MazeCell.WALL);
        maze.setCell(2, 1, MazeCell.WALL);
        maze2 = new Maze(4);
        maze2.loadMazeState("    ",
                            "*x* ",
                            ".*  ",
                            "... ");
        maze3 = new Maze(6);
        maze3.loadMazeState(
            " *   *",
            " * * *",
            "   * *",
            "**** *",
            "     *",
            "      ");
        maze4 = new Maze(6);
        maze4.loadMazeState(
            "   *  ",
            "** *  ",
            "   *  ",
            " ***  ",
            "      ",
            " ***  ");
        maze5 = new Maze(2);

        solver = new MazeSolver(maze);
        solver2 = new MazeSolver(maze2);
        solver3 = new MazeSolver(maze3);
        solver4 = new MazeSolver(maze4);
        solver5 = new MazeSolver(maze5);
    }

    /**
     * This method tests the getMaze method
     */
    public void testGetMaze()
    {
        assertEquals(solver.getMaze().size(), 4);
    }

    /**
     * this method tests the solve method
     */
    public void testSolve()
    {

        solver5.solve();
        assertMaze(solver5.getMaze(),
            ". ",
            "..");

        assertEquals("(0, 0) (1, 0) (2, 0) (3, 0) (3, 1) " +
            "(3, 2) (3, 3)", solver.solve());

        assertEquals("(0, 0) (1, 0) (2, 0) (3, 0) (3, 1) " +
            "(3, 2) (3, 3)", solver2.solve());

        assertEquals("(0, 0) (0, 1) (0, 2) (1, 2) (2, 2) (2, 1) (2, 0) (3, 0)" +
            " (4, 0) (4, 1) (4, 2) (4, 3) (4, 4) (4, 5) (5, 5)",
            solver3.solve());
        assertMaze(solver3.getMaze(),
              ".*...*",
              ".*.*.*",
              "...*.*",
              "****.*",
              "    .*",
              "    ..");
        solver4.solve();
        assertMaze(solver4.getMaze(),
            "...*  ",
            "**.*  ",
            "...*  ",
            ".***  ",
            "..... ",
            "x***..");

    }

    /**
     * This method tests the helper method UnexploredNeighbors
     */
    public void testUnexploredNeighbors()
    {
        Point point = new Point(0, 0);

        assertEquals(new Point(1, 0).toString(),
            solver2.unexploredNeighbors(point).toString());
        point = new Point(1, 0);
        assertEquals(new Point(2, 0).toString(),
            solver2.unexploredNeighbors(point).toString());
        point = new Point(2, 0);
        assertEquals(new Point(3, 0).toString(),
            solver2.unexploredNeighbors(point).toString());
        point = new Point(0, 2);
        assertEquals(null,
            solver2.unexploredNeighbors(point));
        point = new Point(2, 2);
        assertEquals(new Point(3, 2).toString(),
            solver2.unexploredNeighbors(point).toString());
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
