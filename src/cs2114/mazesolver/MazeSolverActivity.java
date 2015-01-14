package cs2114.mazesolver;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
// -------------------------------------------------------------------------
/**
 *  This Class is the controller for the MazeSolver application.
 *
 *  This class represents the "glue" between the view and the model
 *  It tells the view what to do when certain buttons are clicked.
 *
 *  @author  Your name (airshp12)
 *  @version 2012.3.19
 */
public class MazeSolverActivity extends Activity
{
    //~ Instance/static variables .............................................
    private MazeSolver solver;
    private boolean drawWalls;
    private MazeView mazeView;
    private TextView statusLabel;


    //~ Methods ...............................................................

    /**
     * This method is performed when the Wall drawing toggle is pressed.
     * @param view the view
     */
    public void wallToggle(View view)
    {
        drawWalls = !drawWalls;
    }

    /**
     * This method is performed when the Clear Maze button is pressed.
     * @param view the view
     */
    public void clearMaze(View view)
    {
        solver.getMaze().clearMaze();
    }

    /**
     * This method is performed when the solveMaze button is pressed
     * @param view the view
     */
    public void solveMaze(View view)
    {
        String result = solver.solve();
        if (result != null)
        {
            statusLabel.setText(result);
        }
        else
        {
            statusLabel.setText("No solution");
        }
        mazeView.postInvalidate();


    }

    // ----------------------------------------------------------
    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState previous state saved by the last run of the
     *     activity
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        drawWalls = false;
        solver = new MazeSolver(new Maze(10));
        mazeView = (MazeView)findViewById(R.id.mazeView);
        mazeView.setMaze(getMaze());
        mazeView.setActivity(this);
        statusLabel = (TextView)findViewById(R.id.statusLabel);


    }

    /**
     * This method returns whether or not the walls toggle button is on or off.
     * @return is drawWalls toggle is on or off
     */
    public boolean getDrawWalls()
    {
        return drawWalls;
    }

    /**
     * This method gets the maze the activity is using
     * @return the maze used by the activity
     */
    public Maze getMaze()
    {
        return solver.getMaze();
    }


}
