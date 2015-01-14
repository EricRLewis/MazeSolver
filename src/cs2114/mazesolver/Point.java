package cs2114.mazesolver;

/**
 *
 * @author Eric Lewis (airshp12)
 * @version 2012.3.19
 *
 * This class represents a point.
 *
 * Created for the purpose of pleasing Web-Cat.
 *
 */
public class Point
{

    private int x;
    private int y;

    /**
     * The constructor for the point class
     * @param xVal x
     * @param yVal y
     */
    public Point(int xVal, int yVal)
    {
        x = xVal;
        y = yVal;
    }

    /**
     * gets the x value
     * @return x
     */
    public int getX()
    {
        return x;
    }

    /**
     * gets the y value
     * @return y
     */
    public int getY()
    {
        return y;
    }

    /**
     * This method gets a String form of the point object with the syntax
     * "(x, y)
     * @return the point as a string
     */
    public String toString()
    {
        return "(" + getX() + ", " + getY() + ")";
    }


}
