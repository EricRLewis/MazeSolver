package cs2114.mazesolver;


/**
 * @author Eric Lewis (airshp12)
 * @version 2012.3.18
 *
 * This MazeSolver Class will act as the machine that solves the maze when the
 * solve maze button is pressed.
 *
 */
public class MazeSolver
{
    private Maze maze;
    private LinkedStack<Point> stack;


    /**
     * This is the constructor for the MazeSolver class
     * @param newMaze the maze for the solver to solve
     */
    public MazeSolver(Maze newMaze)
    {
        maze = newMaze;
        stack = null;
    }

    /**
     * This method solves the maze
     * @return The string of points in the order the solver traveled to solve
     * the maze
     */
    public String solve()
    {
        String result = null;
        maze.resetPaths();
        stack = new LinkedStack<Point>();
        stack.push(new Point (0, 0));

        while (!stack.isEmpty())
        {
            Point currentPoint = stack.top();
            maze.setCell(currentPoint.getX(), currentPoint.getY(),
                MazeCell.CURRENT_PATH);
            if (currentPoint.getX() == maze.size() - 1 &&
                currentPoint.getY() == maze.size() - 1)
            {
                result = currentPoint.toString();
                stack.pop();
                while (!stack.isEmpty())
                {
                    result = stack.top().toString() + " " + result;
                    stack.pop();
                }
            }

            else
            {
                Point newPoint = unexploredNeighbors(currentPoint);
                if (newPoint != null)
                {
                    stack.push(newPoint);
                }
                else
                {
                    maze.setCell(currentPoint.getX(), currentPoint.getY(),
                        MazeCell.FAILED_PATH);
                    stack.pop();
                }
            }
        }

        return result;
    }


    /**
     * This method Finds any neighbors that remain unexplored and returns them
     *  in the order of south, east, north, then west. If  nothing is found
     *  then it returns the point (-1, -1).
     *
     * @param point the current point the solver is on
     * @return an unexplored neighbor Point or the Point (-1, -1)
     */
    public Point unexploredNeighbors(Point point)
    {
        Point result = null;
        LinkedStack<Point> openSpaces = new LinkedStack<Point>();

        if (getMaze().getCell(point.getX() - 1,
            point.getY()).equals(MazeCell.UNEXPLORED))
        {
            openSpaces.push(new Point(point.getX() - 1, point.getY()));
        }

        if (getMaze().getCell(point.getX(),
            point.getY() - 1).equals(MazeCell.UNEXPLORED))
        {
            openSpaces.push(new Point(point.getX(), point.getY() - 1));
        }

        if (getMaze().getCell(point.getX() + 1,
            point.getY()).equals(MazeCell.UNEXPLORED))
        {
            openSpaces.push(new Point(point.getX() + 1, point.getY()));
        }

        if (getMaze().getCell(point.getX(),
            point.getY() + 1).equals(MazeCell.UNEXPLORED))
        {
            openSpaces.push(new Point(point.getX(), point.getY() + 1));
        }
        if (!openSpaces.isEmpty())
        {
            result = openSpaces.top();
        }

        return result;
    }

    /**
     * This method gets the maze from the solver
     * @return the maze
     */
    public Maze getMaze()
    {
        return maze;
    }


}
