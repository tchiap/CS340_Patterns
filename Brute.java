
/**
 * Tom Chiapete
 * March 7, 2006
 * Assign 3 
 * 
 * Description:
 * Returns all possible combinations of four points that create a line 
 * by comparing slope and intercept.
 * 
 * Known bugs:  None that I know of.
 */

// Required libraries
import java.io.*;
import java.util.*;



public class Brute
{

    private static MyPoint [] points; // Array that holds all MyPoints

    /**
     * main() method
     * Asks for a filename if no filename is specified in the command line arguments.
     */
    public static void main (String [] args)
    {
        // Check to see if there is anything in the first argument
        if (args.length > 0)
            readInFile(args[0]);
        else
            readInFile("");
            
        // Print the lines found.    
        printLines();
    }
    
    public static void printLines()
    {
        
        // Slopes of specified points
        double slopeAB = 0.0; // slope of points a and b
        double slopeCD = 0.0; // slope of points c and d
        double slopeDA = 0.0; // slope of points d and a
        
        // Counter variable that will be outputted later.
        int counter = 0;
        
        // A rather inefficient way to crawl through the points array, but it gets
        // the job done in this instance.
        for (int a = 0; a < points.length; a++)
        {
            for (int b = a + 1; b < points.length; b++)
            {
                
                // Find slope of a and b
                slopeAB = findSlope(points[a],points[b]);
                
                // For loops to find slopes of c and d and d and a.
                for (int c = b + 1; c < points.length; c++)
                {
                    for (int d = c + 1; d < points.length; d++)
                    {
                        // Find the slope of c and d
                        slopeCD = findSlope(points[c],points[d]);
                        
                        // Also, find the slope of d and a
                        slopeDA = findSlope(points[d],points[a]);
                        
                        // Check to see if all the slopes are equal
                        if (slopeAB == slopeCD  && slopeCD == slopeDA)
                        {
                            
                            // Use the areOnSlope() method to return true or false, checking
                            // the points a, b, c, d 
                            if(areOnSlope(slopeAB,points[a],points[b],points[c],points[d]))
                            {
                                // Print out the points that are on the same line.
                                System.out.print(points[a].toString()+
                                    ">>"+points[b].toString()+">>"); 
                                System.out.println(points[c].toString()+
                                    ">>"+points[d].toString());
                                
                                // Increment the counter
                                counter++;
                            }
                        }
                    }
                } 
            }
        }
        
        // Print out the value of the counter.
        System.out.println("\nUsing brute force:  "+counter);
                    
    }

    /**
     * findIntercept() method
     * Find the intercept.  I used y = mx + b and found b by subtracting the mx for both
     * sides.
     */
    public static double findIntercept(MyPoint mp, double slope)
    {
        // b = y - mx
        return mp.getY() - slope * mp.getX(); 
    }
    
    /** 
     * areOnSlope() method
     * Returns a boolean to check to see if MyPoints w, x, y, and z 
     * are all on the same slope.  If so, return true.  Otherwise, 
     * return false.
     */
    public static boolean areOnSlope(double slope, MyPoint w, MyPoint x, MyPoint y, MyPoint z)
    {
        
        if ((findIntercept(w,slope) == findIntercept(y,slope)) &&
            (findIntercept(y,slope) == findIntercept(x,slope)) &&
            (findIntercept(w,slope) == findIntercept(z,slope)))
            return true;

        // Not on slope, return false.
        return false;
    }
    
    /**
     * findSlope() method
     * Returns the slope using x1, y1, x2, y2
     */
    public static double findSlope(double x1, double y1, double x2, double y2)
    {
        return ((y2 - y1) / (x2 - x1));
    }
    
    /**
     * findSlope() method
     * Returns the slope of two points, using MyPoint objects.
     */
    public static double findSlope(MyPoint p, MyPoint q)
    {
        
        // pX is assigned the X value of MyPoint p.
        double pX = p.getX();
        
        // pY is assigned the Y value of MyPoint p.
        double pY = p.getY();
        
        // qX is assigned the X value of MyPoint q.
        double qX = q.getX();
        
        // qY is assigned the Y value of MyPoint q.
        double qY = q.getY();
        
        // When pX minus qX is not zero, return the slope.
        if ((p.getX() - q.getX()) != 0)
            return ((pY - qY) / (pX - qX));
            
        // Otherwise, I've been told to return +infinity, otherwise it'll give you 
        // unexpected results later.
        else
            return Double.POSITIVE_INFINITY;
    }
    
    
    /**
     * readInFile() method
     * The readInFile() method takes in one parameter, a filename.
     * If no filename was specified in the command line, ask for it now.
     * Check to see if it's valid, otherwise throw and exception.
     * Throw our points into MyPoint array.
     */
    public static void readInFile(String filename)
    {
        // If the filename provided as the argument to this method is blank, 
        // we know that we need to now ask for a filename.
        // Read in filename.
        if (filename.equals(""))
        {
            System.out.print("Please enter the filename:  ");
            Scanner input = new Scanner(System.in);
            filename = input.next();
        }
        
        // Try bringing in the file.  If the file is found, there shouldn't be
        // a problem.
        try
        {
            Scanner loadFile = new Scanner(new File(filename));
            
            // Read in size
            points = new MyPoint [loadFile.nextInt()];
            
            // Read in points.
            for (int x = 0; x< points.length ; x++)
            {
                MyPoint mp = new MyPoint(loadFile.nextInt(),loadFile.nextInt());
                
                // Assign MyPoint to the points array at index x.
                points[x] = mp;
            }
        }
        
        // File not found.  Return an error.
        catch (FileNotFoundException e)
        {
            System.out.println(filename);
            System.out.println(" was not found.");
            System.out.println("-----------------------------");
            System.exit(1);
        }
    }
   
    
}
