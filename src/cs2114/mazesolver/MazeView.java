package cs2114.mazesolver;

import java.util.Observable;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 *
 * @author Eric Lewis (airshp12)
 * @version 2012.3.19
 *
 * This class is a customized view for the Maze class
 *
 */
public class MazeView
    extends View
{

    private Maze maze;
    private MazeSolverActivity activity;

    /**
     * The constructor for the custom MazeView class
     * @param context the context
     * @param attrs the attributes
     */
    public MazeView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    /**
     * This method sets the Maze for the view
     * @param newMaze the maze you wish to use
     */
    public void setMaze(Maze newMaze)
    {
        maze = newMaze;
        maze.addObserver(new MazeObserver());
    }

    /**
     * This method sets the activity for the view.
     * Used for checking the toggle box
     * @param newActivity the activity being set
     */
    public void setActivity(MazeSolverActivity newActivity)
    {
        activity = newActivity;
    }

    /**
     *
     * @author Eric Lewis
     * @version 2012.3.19
     *
     * This acts as an observer for the Maze
     *
     */
    private class MazeObserver implements java.util.Observer
    {

        /**
         * This method calls postInvalidate() on the view when the model
         * is modified
         * @param observable the observable
         * @param data the data
         */
        public void update(Observable observable, Object data)
        {
            postInvalidate();
        }

    }

    /**
     * This method handles any interaction from the user.
     * @param event the event or touch
     * @return whether or not the event was handled
     */
    public boolean onTouchEvent(MotionEvent event)
    {
        boolean handled = false;
        if (event.getAction() ==  MotionEvent.ACTION_DOWN ||
            event.getAction() ==  MotionEvent.ACTION_MOVE)
        {
            maze.resetPaths();

            if (activity.getDrawWalls())
            {
                maze.setCell(getCellX(event.getX()), getCellY(event.getY()),
                    MazeCell.WALL);
                handled = true;
            }

            else
            {
                maze.setCell(getCellX(event.getX()), getCellY(event.getY()),
                    MazeCell.UNEXPLORED);
                handled = true;
            }
        }
        postInvalidate();
        return handled;
    }

    /**
     * This method gets the cell from the maze when given pixel coordinates
     * @param x the x coordinate
     * @return the maze cell x value
     */
    private int getCellX(float x)
    {
        if (maze != null)
        {
            int length = getWidth() / maze.size();
            return (int) x / length;
        }

        return -1;
    }

    /**
     * This method gets the cell from the maze when given pixel coordinates
     * @param y the y coordinate
     * @return the maze cell y value
     */
    private int getCellY(float y)
    {
        if (maze != null)
        {
            int length = getWidth() / maze.size();
            return (int) y / length;
        }

        return -1;
    }

    /**
     * This method overrides the onDraw method from View. This customizes
     * our view.
     * @param canvas the canvas
     */
    public void onDraw(Canvas canvas)
    {
        if (maze != null)
        {
            int length = getWidth() / maze.size();
            Paint unexplored = new Paint();
            Paint current = new Paint();
            Paint failed = new Paint();
            Paint wall = new Paint();
            Paint grid = new Paint();

            unexplored.setColor(Color.BLACK);
            unexplored.setStyle(Style.FILL);
            current.setColor(Color.GREEN);
            current.setStyle(Style.FILL);
            failed.setColor(Color.RED);
            failed.setStyle(Style.FILL);
            wall.setColor(Color.WHITE);
            wall.setStyle(Style.FILL);
            grid.setColor(Color.BLUE);
            grid.setStyle(Style.STROKE);

            for (int i = 0; i < maze.size(); i++)
            {
                for (int j = 0; j < maze.size(); j++)
                {
                    if (maze.getCell(j, i).equals(MazeCell.UNEXPLORED))
                    {
                        canvas.drawRect(j * length, i * length,
                            (j + 1) * length, (i + 1) * length, unexplored);
                        canvas.drawRect(j * length, i * length,
                            (j + 1) * length, (i + 1) * length, grid);
                    }

                    else if (maze.getCell(j, i).equals(MazeCell.CURRENT_PATH))
                    {
                        canvas.drawRect(j * length, i * length,
                            (j + 1) * length, (i + 1) * length, current);
                        canvas.drawRect(j * length, i * length,
                            (j + 1) * length, (i + 1) * length, grid);
                    }

                    else if (maze.getCell(j, i).equals(MazeCell.FAILED_PATH))
                    {
                        canvas.drawRect(j * length, i * length,
                            (j + 1) * length, (i + 1) * length, failed);
                        canvas.drawRect(j * length, i * length,
                            (j + 1) * length, (i + 1) * length, grid);
                    }

                    else if (maze.getCell(j, i).equals(MazeCell.WALL))
                    {
                        canvas.drawRect(j * length, i * length,
                            (j + 1) * length, (i + 1) * length, wall);
                        canvas.drawRect(j * length, i * length,
                            (j + 1) * length, (i + 1) * length, grid);
                    }
                }
            }
        }

    }
    // ----------------------------------------------------------
    /**
     * Overridden to force the view to be square (have the same width and
     * height).
     *
     * @param widthMeasureSpec the desired width as determined by the layout
     * @param heightMeasureSpec the desired height as determined by the layout
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        // Choose the smallest of the two dimensions to use for both.
        int measureSpec = Math.min(widthMeasureSpec, heightMeasureSpec);

        // Call the superclass implementation but pass it our modified width
        // and height instead of the incoming ones.
        super.onMeasure(measureSpec, measureSpec);
    }


}
