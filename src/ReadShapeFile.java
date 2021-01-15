
/**
 * This class reads a shape file.  For the format of this shape file, see the assignment description.


 * Also, please see the shape files ExampleShapes.txt, ExampleShapesStill.txt, and TwoRedCircles.txt
 *
 * @author Gabriel Petcu
 *
 */

import javafx.scene.paint.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadShapeFile {
	
	/**
	 * Reads the data of a circle and returns the object.
	 * @param in the Scanner is reading the data from the file.
	 * @return circle created from the data file.
	 */
	   private static Circle readCircle(Scanner in) {
	        int x=in.nextInt();
	        int y=in.nextInt();
	        int vx=in.nextInt();
	        int vy=in.nextInt();
	        boolean isFilled=in.nextBoolean();
	        int diameter=in.nextInt();
	        int R=in.nextInt();
	        int G=in.nextInt();
	        int B=in.nextInt();        
	        int insertionTime=in.nextInt();
	        boolean isPulsing = in.nextBoolean();
	        Color colour= Color.rgb(R,G,B);

	        Circle circle=new Circle("circle",insertionTime, x, y, vx, vy, diameter, colour, isFilled,isPulsing);

	        return circle;
	    }

		/**
		 * Reads the data of an oval and returns the object.
		 * @param in the Scanner is reading the data from the file.
		 * @return oval created from the data file.
		 */
	   
	private static Oval readOval(Scanner in)
	{
		int x = in.nextInt();	
		int y = in.nextInt();
		int vx = in.nextInt();
		int vy = in.nextInt();
		boolean isFilled  = in.nextBoolean();
		int width = in.nextInt();
		int height = in.nextInt();
		int R = in.nextInt();
		int G = in.nextInt();
		int B = in.nextInt();
		int insertionTime = in.nextInt();
		boolean isPulsing = in.nextBoolean();
		Color colour = Color.rgb(R, G, B); 
		Oval oval = new Oval("oval",insertionTime,x,y,vx,vy,height,width,colour,isFilled, isPulsing);
				
				return oval;
		
	}
	
	/**
	 * Reads the data of a square and returns the object.
	 * @param in the Scanner is reading the data from the file.
	 * @return square created from the data file.
	 */
	
	private static Square readSquare(Scanner in)
	{
		int x = in.nextInt();	
		int y = in.nextInt();
		int vx = in.nextInt();
		int vy = in.nextInt();
		boolean isFilled  = in.nextBoolean();
		int side = in.nextInt();
		int R = in.nextInt();
		int G = in.nextInt();
		int B = in.nextInt();
		int insertionTime = in.nextInt();
		boolean isPulsing = in.nextBoolean();
		Color colour = Color.rgb(R, G, B); 
		Square square = new Square("square",insertionTime,x,y,vx,vy,side,colour,isFilled,isPulsing);
			
			return square;
	}
	
	/**
	 * Reads the data of a rect and returns the object.
	 * @param in the Scanner is reading the data from the file.
	 * @return rect created from the data file.
	 */
	
	private static Rect readRect(Scanner in)
	{
		int x = in.nextInt();	
		int y = in.nextInt();
		int vx = in.nextInt();
		int vy = in.nextInt();
		boolean isFilled  = in.nextBoolean();
		int width = in.nextInt();
		int height = in.nextInt();
		int R = in.nextInt();
		int G = in.nextInt();
		int B = in.nextInt();
		int insertionTime = in.nextInt();
		boolean isPulsing = in.nextBoolean();
		Color colour = Color.rgb(R, G, B); 
		Rect rect = new Rect("rect",insertionTime,x,y,vx,vy,height,width,colour,isFilled,isPulsing);
				
				return rect;
		
	}
	
	/**
	 * Reads the data of a triangle and returns the object.
	 * @param in the Scanner is reading the data from the file.
	 * @return triangle created from the data file.
	 */
	
	 private static Triangle readTriangle(Scanner in){
	        int x = in.nextInt();
	        int y = in.nextInt();
	        int vx = in.nextInt();
	        int vy = in.nextInt();
	        boolean isFilled = in.nextBoolean();
	        int side = in.nextInt();
	        int R = in.nextInt();
	        int G = in.nextInt();
	        int B = in.nextInt();
	        int insertionTime = in.nextInt();
	        boolean isPulsing = in.nextBoolean();
	        Color color = Color.rgb(R,G,B);

	        Triangle triangle = new Triangle("triangle",insertionTime, x, y, vx, vy, side, color, isFilled,isPulsing);

	        return triangle;
	    }
	
	 /**
	  * Reads the data from the file, sees which shape has been added in queue and place them in queue.
	  * @param in reads the data from the file created
	  * @return the queue represented by the file created.
	  */
	   
	
	private static Queue<ClosedShape> readDataFile(Scanner in) {
		Queue<ClosedShape> shapeQueue = new Queue<ClosedShape>();

		//read in the shape files and place them on the Queue

		//Right now, returning an empty Queue.  You need to change this.
		
		while(in.hasNext()) {
            String objectType=in.next();
            // Checks the type of the object.
            if(objectType.equals("circle")){
                Circle circle= readCircle(in);
                shapeQueue.enqueue(circle);
            }
            else if(objectType.equals("oval")){
                Oval oval=readOval(in);
                shapeQueue.enqueue(oval);
            }
            else if(objectType.equals("rect")) {
                Rect rect = readRect(in);
                shapeQueue.enqueue(rect);
            }
            else if(objectType.equals("square")) {
                Square square = readSquare(in);
                shapeQueue.enqueue(square);
            }
            else if(objectType.equals("triangle")){
                Triangle triangle = readTriangle(in);
                shapeQueue.enqueue(triangle);
            }
           
        }
		return shapeQueue;
	}





	/**
	 * Method to read the file and return a queue of shapes from this file. The
	 * program should handle the file not found exception here and shut down the
	 * program gracefully.
	 * 
	 * @param filename
	 *            the name of the file
	 * @return the queue of shapes from the file
	 */
	public static Queue<ClosedShape> readDataFile(String filename) {
	    // HINT: You might want to open a file here.
		File inputFile = new File(filename);
	    Scanner in = null; //null is not sensible. Please change
		try{
			
			in = new Scanner(inputFile);
			
		}
		catch(FileNotFoundException e){
			System.out.println("Cannot open " + filename);
			System.exit(0);
		}
	   
	    return ReadShapeFile.readDataFile(in);
	    
	}
	
}
