/**
 * Square.java

 * @version 2.0.0
 * @author Gabriel Petcu
 */

import javafx.scene.paint.Color;

import javafx.scene.canvas.GraphicsContext;

/**
 * Square is a shape that can be drawn to the screen.
 * filled with colour or opaque.
 */
public class Square extends ClosedShape {
    //The side and initial side of the square
	private int side;
	private int initialSide;

    /**
     * Creates a Square.
     * @param x The display component's x position.
     * @param y The display component's y position.
     * @param vx The display component's x velocity.
     * @param vy The display component's y velocity.
     * @param side The side of the Square.
     * @param colour The line colour or fill colour.
     * @param isFilled True if the square is filled with colour, false if opaque.
     * @param isPulsing True if the shape grows constantly , false stays the same.
     * @param shape checks the shape name.
     */
    public Square (String shape,int insertionTime, int x, int y, int vx, int vy, int side, Color colour, boolean isFilled, boolean isPulsing) {
    	super (shape,insertionTime, x, y, vx, vy, colour, isFilled,isPulsing);
    	this.side = side;
    	 setInitialSide();
    }
    
    /**
     * Method to convert a square to a string.
     */
    public String toString () {
    	String result = "This is a Square\n";
    	result += super.toString ();
	result += "Its side is " + this.side + "\n";
    	return result;
    }
    
    /**
     * @param Resets the side.
     */
    
    public void setSide (int side) {
    	this.side = side;
    }
    
    /**
     * @return The side of the square.
     */
    public int getSide() {
    	return side;
    }

    /**
     * @return The other side of the square.
     */
 	public int getWidth() {
		return side;
	}

 	/**
 	 * @return The side of the square.
 	 */
 	
 	public int getHeight() {
		return side;
	}
 	
 	/**
 	 * @return the type of the shape in this case is "Square"
 	 */
 	
 	public String getShape(){
        return this.shape;
    }
 	
 	/**
 	 * Set initial sides so we don't lose the shape.
 	 */

    public void setInitialSide(){
        this.initialSide=side;
    }
    
    /**
     * @return the initial side of the shape.
     */
    
    public int getInitialSide(){
        return initialSide;
    }


    /**
     * Draw the circle on the screen.
     * @param g The graphics object of the scene component.
     */
    public void draw (GraphicsContext g) {
    	g.setFill( colour );
    	g.setStroke( colour );
    	if (isFilled) {
    		g.fillRect( x, y, side, side );
    	} 
    	else {
    		g.strokeRect( x, y, side, side );
	    }
    }
}