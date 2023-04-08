/** 
* Tom Chiapete
* Assignment 3 
* CSCI - 340 
* 
* Known Bugs:  
* I couldn't find a way to eliminate duplicates.
* My theory is that if I was able to elimnate the duplicates, possibly
* with a use with a Hashtable, I could get the correct answer.
* 
* Description: 
* Returns all lines with 4 or more points that lie on one line by comparing angles.
* 
* The compiler warning I think will go away with use of generics.  
* It should compile though anyway.
*/ 
 

// Required libraries.
import java.io.*; 
import java.util.*; 
 
public class Fast 
{ 

    // Array of MyPoints
    private static MyPoint [] points;
    
    // ArrayList of lines
    private static ArrayList linesAL = new ArrayList();
    
    // ArrayList that holds all the lines.
    private static ArrayList list = new ArrayList();
 
    /**
     * main() method
     * Asks for a filename if no filename is specified in the command line arguments.
     */
    public static void main (String [] args) 
    { 
 
        if (args.length > 0)
            readInFile(args[0]);
        else
            readInFile("");
        printLines(); 
    } 
 
    /**
     * readInFile() method
     * The readInFile() method takes in one parameter, a filename.
     * If no filename was specified in the command line, ask for it now.
     * Check to see if it's valid, otherwise throw and exception.
     * Throw our points into MyPoint array.
     * 
     * This method is almost the same as in the Brute.java
     */
    public static void readInFile(String filename) 
    { 
        
        if (filename.equals(""))
        {
            System.out.print("Please enter the filename:  ");
            Scanner input = new Scanner(System.in);
            filename = input.next();
        }
       
        // Try opening the file specified using filename
        try 
        { 
            
            // Create a new Scanner object
            Scanner set = new Scanner(new File(filename)); 
            
            // Initialize the array points with the array size, found as the first int 
            // in valid files.
            points = new MyPoint[ set.nextInt() ]; 
     
            // Fills the MyPoints array with the x , y coordinates
            for (int x = 0; x < points.length; x++) 
            { 
                MyPoint mp = new MyPoint(set.nextInt(), set.nextInt()); 
                points[x]= mp; 
            } 
     
        } 
     
        // There was a problem, throw an exception.
        catch ( FileNotFoundException e) 
        { 
            System.out.print(filename); 
            System.out.println(" not found."); 
            System.exit(1); 
        } 
     
      } 
     
      
      
      /** 
       * printLines() method
       * Locates and prints out all the lines that contain at least 4 points.
       */
      public static void printLines() 
      { 
     
            // Find aX and aY points.
            for(int a = 0 ; a < points.length; a++) 
            { 
                
                // Store the value of x at the points array value at a
                int aX = points[a].getX();
                
                // Store the value of y at the points array value at a
                int aY = points[a].getY();
     
                // Find bX and bY points.
                for(int b = 0; b < points.length; b++) 
                { 
     
                    // Store the value of x at the points array value at b
                    int bX = points[b].getX();
                    
                    // Store the value of y at the points array value at b 
                    int bY = points[b].getY();
                    
                    // Find x by subtracting the bX minus the aX.
                    int x = bX - aX;
                    
                    // Then, find the y by subtracting bY minus the aY
                    int y = bY - aY;
                    
                    // Find the angle of the line.  This can be done by taking arctan of when y and x, 
                    // times 180 over pi.  To keep it simple, and we don't have to worry about doubles,
                    // cast the result as an int.
                    int angle = (int) (
                            Math.atan2(y,x)  *  (180  / Math.PI)  );
     
                    // If the angle is less than or equal to zero, add another semicircle to get a 
                    // positive number.
                    if(angle <= 0) 
                        angle += 180; 
     
                    // Go and set the angle for points[b] 
                    points[b].setAngle(angle); 
                }
     
                // Step through with the counter, and holding the original value of m when you first 
                // enter the loop.  Store the angle of the first point you check.  While m is less than 
                // the number of points and the temporary angle is equal to angle at point m, increment 
                // the counter and 'm'.  Once you fall out of the while loop, if the counter is greater than 
                // 2, set counter equal to the counter plus temporary M. Then decrement tempM by 1.
                // When temporary M is less than counter, add the point at temporary M to the ArrayList linesAL.
                // Print linesAL and add linesAL to list.  Then clear() linesAL, and print the size of the list, 
                // which is the number of lines.
                for(int m = a+1; m < points.length; m++) 
                { 
                    
                    // Keeps the number of segments counted for the same angle.
                    int counter = 0;
                    
                    // tempM is what m was
                    int tempM = m;
                    
                    // Stores the angle at the original point m.
                    double tempAngle = points[m].getAngle();
     
                    // Do this while m is less than the length of points, and 
                    // tempAngle is equal to the angle at point m.
                    while(m < points.length && tempAngle == points[m].getAngle()) 
                    { 
                        // Increment m.
                        m++;
                        
                        // Also, increment counter.
                        counter++;
                         
                    } 
     
                    // If counter is greater than two, this means the line has four or more points on it.
                    if (counter > 2) 
                    { 
     
                        // Counter equals counter plus the tempM.
                        counter += tempM;
                        
                        // Also, decrement counter.
                        tempM = tempM - 1; 
     
                        // Copy the points into linesAL.
                        for(   ; tempM < counter; tempM++) 
                        { 
                            // Add points to the linesAL list at the point at index tempM.
                            linesAL.add(points[tempM]);
                        } 
                        
                        // Call the printArray method with linesAL as the parameter.
                        printArrayList(linesAL);
                        
                        // Add lines to list from linesAL.
                        list.add(linesAL);
                        
                        // Clear linesAL.
                        linesAL.clear();
                    } 
     
                } 
                
            } 
     
            System.out.println(list.size() + " lines found");// prints out the number of lines found 
        } 
        
        /**
         * printArrayList() method
         * Takes in an ArrayList and outputs its contents.
         */
        public static void printArrayList(ArrayList lines)
        {
            Object [] thing = new Object[lines.size()];
            thing = lines.toArray();
            for (int i=0; i<thing.length; i++)
                System.out.print (thing[i] + ">>");
            System.out.println();   
        }
} 