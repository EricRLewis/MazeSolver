package cs2114.mazesolver;

import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;
import student.AndroidTestCase;

/**
 * This class tests the GUI for mazeSolver
 * @author Eric Lewis (airshp12)
 * @version 2012.3.19
 *
 */
public class MazeSolverActivityTest
    extends AndroidTestCase<MazeSolverActivity>
{

    private MazeSolver solver;
    private Maze maze;
    private Button solveMaze;
    private Button clearMaze;
    private ToggleButton drawEraseMode;
    private MazeView mazeView;
    private TextView statusLabel;

    /**
     * The constructor for the GUI test
     */
    public MazeSolverActivityTest()
    {
        super(MazeSolverActivity.class);
    }

    /**
     * This method sets up the views and the MazeSolver for testing the
     *  activity.
     */
    public void setUp()
    {
        maze = new Maze(10);
        maze.loadMazeState(
            " *********",
            "          ",
            " *  *     ",
            "**  ***** ",
            "    *     ",
            "    *  ***",
            "    *     ",
            "    ****  ",
            "*****     ",
            "          ");
        solver = new MazeSolver(maze);
        solveMaze = getView(Button.class, R.id.solveMaze);
        clearMaze = getView(Button.class, R.id.clearMaze);
        drawEraseMode = getView(ToggleButton.class, R.id.drawEraseMode);
        statusLabel = getView(TextView.class, R.id.statusLabel);
        mazeView = getView(MazeView.class, R.id.mazeView);
        getActivity().getMaze().loadMazeState(
            " *********",
            "          ",
            " *  *     ",
            "**  ***** ",
            "    *     ",
            "    *  ***",
            "    *     ",
            "    ****  ",
            "*****     ",
            "          ");

    }

    /**
     * This method tests the solveMaze button
     */
    public void testSolveMaze()
    {
        click(solveMaze);
        assertEquals(solver.solve(), statusLabel.getText());

    }

    /**
     * This method tests what happens when you touch down on a square while the
     * toggle button is on.
     */
    public void testDrawWallTouch()
    {
        getActivity().getMaze().loadMazeState(
            "          ",
            "          ",
            "          ",
            "          ",
            "          ",
            "          ",
            "          ",
            "          ",
            "          ",
            "          ");
        float cellSize = mazeView.getWidth() / getActivity().getMaze().size();
        click(drawEraseMode);
        touchDown(mazeView, cellSize * 2 - 1, 0);
        assertEquals(getActivity().getMaze().getCell(1, 0), MazeCell.WALL);

    }

    /**
     * This method tests what happens when you slide your finger across a
     * square while the toggle button is on.
     */
    public void testDrawWallMove()
    {
        float cellSize = mazeView.getWidth() / getActivity().getMaze().size();
        click(drawEraseMode);
        touchDown(mazeView, cellSize * 2 - 1, 0);
        touchMove(cellSize * 3 - 1, 0);
        assertEquals(getActivity().getMaze().getCell(2, 0), MazeCell.WALL);
    }

    /**
     * This method tests what happens when you touch down on a square while the
     * toggle button is off.
     */
    public void testEraseWallTouch()
    {
        getActivity().getMaze().loadMazeState(
            " *********",
            "          ",
            " *  *     ",
            "**  ***** ",
            "    *     ",
            "    *  ***",
            "    *     ",
            "    ****  ",
            "*****     ",
            "          ");
        float cellSize = mazeView.getWidth() / getActivity().getMaze().size();
        touchDown(mazeView, cellSize * 2 - 1, 0);
        assertEquals(getActivity().getMaze().getCell(1, 0),
            MazeCell.UNEXPLORED);

    }

    /**
     * This method tests what happens when you slide your finger across a
     * square while the toggle button is on.
     */
    public void testEraseWallMove()
    {

        float cellSize = mazeView.getWidth() / getActivity().getMaze().size();
        touchDown(mazeView, cellSize * 2 - 1, 0);
        touchMove(cellSize * 3 - 1, 0);
        assertEquals(getActivity().getMaze().getCell(2, 0),
            MazeCell.UNEXPLORED);
    }



    /**
     * This method tests the WallToggle method on top of the onTouch
     * method in the view class
     */
    public void testWallToggle()
    {
        click(drawEraseMode);
        assertTrue(getActivity().getDrawWalls());

        click(drawEraseMode);
        assertFalse(getActivity().getDrawWalls());
    }



    /**
     * This method tests what happens when you click solve Maze when it is
     * unsolvable
     */

    public void testUnsolvableMaze()
    {
        getActivity().getMaze().loadMazeState(
            " *********",
            " *        ",
            " *  *     ",
            "**  ***** ",
            "    *     ",
            "    *  ***",
            "    *     ",
            "    ****  ",
            "*****     ",
            "          ");
        click(solveMaze);
        assertEquals("No solution", statusLabel.getText());
    }
    /**
     * This method tests the clearMaze button
     */
    public void testClearMaze()
    {
        click(clearMaze);

        assertMaze(getActivity().getMaze(),
            "          ",
            "          ",
            "          ",
            "          ",
            "          ",
            "          ",
            "          ",
            "          ",
            "          ",
            "          ");
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
