package cs2114.mazesolver;

import junit.framework.TestCase;

/**
 * @author Eric Lewis (airshp12)
 * @version 2012.3.18
 * This is the test case for the LinkedStack Class
 *
 */
public class LinkedStackTest
    extends TestCase
{
    private LinkedStack<Point> stack;
    private Point point1;

    /**
     * This is the constructor for the test case
     */
    public LinkedStackTest()
    {
        //There is nothing in the constructor for a test class.
    }


    /**
     * This method sets up the stack and a few testable links
     */
    public void setUp()
    {
        stack = new LinkedStack<Point>();
        point1 = new Point(1, 0);
    }

    /**
     * This method tests the push and size methods
     */
    public void testPush()
    {
        stack.push(point1);
        assertEquals(stack.size(), 1);
    }

    /**
     * This method tests the pop method
     */
    public void testPop()
    {
        Exception occured = null;
        try
        {
            stack.pop();
        }
        catch (Exception e)
        {
            occured = e;
        }
        assertNotNull(occured);
        stack.push(point1);
        stack.pop();
        assertEquals(stack.size(), 0);
    }

    /**
     * This method tests the top method
     */
    public void testTop()
    {
        Exception occured = null;
        try
        {
            stack.top();
        }
        catch (Exception e)
        {
            occured = e;
        }

        assertNotNull(occured);
        stack.push(point1);
        assertEquals(point1, stack.top() );
    }

    /**
     * This method tests the isEmpty method.
     */
    public void testIsEmpty()
    {
        stack.push(point1);
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }


}
