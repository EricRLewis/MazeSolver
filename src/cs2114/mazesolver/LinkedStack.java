package cs2114.mazesolver;

import java.util.EmptyStackException;
import cs2114.link.Link;




/**
 * @author Eric Lewis (airshp12)
 * @version 2012.3.18
 *
 * The LinkedStack class represents a stack of linked points.
 * @param <T> a generic data type to be declared upon instantiation
 *
 *
 */
public class LinkedStack<T>
    implements SimpleStack<T>
{
    private Link<T> topOfStack;
    private int size;

    /**
     * This method is the constructor for the LinkedStack class
     */
    public LinkedStack()
    {
        topOfStack = null;
        size = 0;
    }

    /**
     * This method tests to see if the stack is empty
     * @return if the stack is empty
     */
    public boolean isEmpty()
    {
        return topOfStack == null;
    }

    /**
     * This method removes the first link on the stack
     */
    public void pop()
    {
        if (isEmpty())
        {
            throw new EmptyStackException();
        }
        else
        {
            topOfStack = topOfStack.split();
            size--;
        }



    }

    /**
     * This method returns the size of the stack
     * @return the size of the stack
     */
    public int size()
    {
        return size;
    }

    /**
     * This method returns the link on the top of the stack
     * @return the top link
     */
    public T top()
    {
        if (isEmpty())
        {
            throw new EmptyStackException();
        }
        else
        {
            return topOfStack.data();
        }
    }

    /**
     * This method pushes a new Link on to the stack
     * @param link the new link to be pushed on top
     */
    public void push(Link<T> link)
    {
        Link<T> temp = link;
        temp.join(topOfStack);
        topOfStack = temp;
        size++;
    }

    /**
     * This overloaded method helps for this project by creating a link
     * out of a Point for you.
     * @param obj the object you want to push onto the stack.
     */
    public void push(T obj)
    {
        Link<T> temp = new Link<T>(obj);
        push(temp);
    }
}
