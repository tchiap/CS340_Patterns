 
/**
 * Tom Chiapete
 * March 7, 2006
 * Assign 3 
 * 
 * Description:
 * This has methods for setting and getting points, as 
 * well as comparing.
 * 
 * Mostly written by Dr. Hansen, but there are a few additions.
 * 
 */   


// Required libraries.
import java.util.*;
    
public class MyPoint implements Comparable<MyPoint>
{
   
   // Holds the angle
   private double angle;
   
   // Holds the x of the point.
   private int x;
   
   // Holds y.
   private int y;
   

   /**
    * compareTo() method 
    * If the angle is greater than the paramter's angle, 
    * return 1.
    * 
    * If the angle is less than the paramter's angle,
    * return -1.
    * 
    * Else, return 0.
    * 
    */
   public int compareTo(MyPoint p)
   {
       if(angle > p.getAngle())
            return 1;

       else if (angle < p.getAngle())
            return -1;

       return 0;
   }
   
   
   /** Creates a new instance of MyPoint */
   public MyPoint(int x, int y) 
   {
       this.x = x;
       this.y = y;
       angle = -1;
   }

   public MyPoint(int x, int y, double angle) 
   {
       this.x = x;
       this.y = y;
       this.angle = angle;
   }

   // Returns x.
   public int getX()
   {
       return x;
   }

   // Returns y.
   public int getY()
   {
       return y;
   }

   // Returns the angle.
   public double getAngle()
   {
       return angle;
   }


   // sets the angle
   public void setAngle(double x)
   {
       angle = x;
   }

   // toString() method.
   public String toString()
   {
       return "(" + x + ", " + y + ")";
   }


   /** 
    * printArray() method
    * Goes through and prints the array.
    */
   public static void printArray(MyPoint [] array)
   {
       for (int x=0; x<array.length; x++)
       {
           System.out.print (array[x] + " ");
       }
       System.out.println();
   }


}


