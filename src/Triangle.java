/**
 * Triangle.java

 * @version 2.0.0
 * @author Gabriel Petcu
 */
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
/**
 * Triangle is a shape that can be drawn to the screen.
 * filled with colour or opaque.
 */
public class Triangle extends ClosedShape{
    private double[] xPoints = new double[3]; // The coordinates of triangle's edges
    private double[] yPoints = new double[3]; // The coordinates of triangle's edges
    private int side; // The side of the Triangle
    private int initialSide; // Initial side of the Triangle
    
    /**
     * Creates a Triangle.
     * @param x The display component's x position.
     * @param y The display component's y position.
     * @param vx The display component's x velocity.
     * @param vy The display component's y velocity.
     * @param side The side of the Triangle.
     * @param colour The line colour or fill colour.
     * @param isFilled True if the square is filled with colour, false if opaque.
     * @param isPulsing True if the shape grows constantly , false stays the same.
     * @param shape checks the shape name.
     */

    public Triangle(String shape,int insertionTime, int x, int y, int vx, int vy, int side, Color color, boolean isFilled,boolean isPulsing){
        super(shape,insertionTime, x, y, vx, vy, color, isFilled,isPulsing);
        this.side = side;
        setCoordinates(10,10);
    }
    /**
     * One edge of the triangle has 2 points(x,y);
     * @param x1 the coordinate for the first edge
     * @param y1 the coordinate for the first edge
     * This function sets the coordinate for all 3 edges of the triangle so we know how to draw the triangle properly.
     */
    public void setCoordinates(double x1, double y1){
    	this.xPoints[0] = x1;
        this.yPoints[0] = y1;

        this.xPoints[1] = this.xPoints[0] + side;
        this.yPoints[1] = this.yPoints[0];

        this.xPoints[2] = (this.xPoints[0] + this.xPoints[1]) / 2;
        this.yPoints[2] = this.xPoints[2] - this.xPoints[0] + this.yPoints[0];
    }

    /**
     * @return the side of the Triangle. 
     */
    
    public int getWidth() {
        return side;
    }
    
    
    /**
     * @return the shape of the Triangle. 
     */
    
    public String getShape(){
        return this.shape;
    }
    
    /**
     * Set the initial side of the Triangle so we don't lose the initial side.
     */
    
    public void setInitialSide(){
        this.initialSide=side;
    }

    /**
     * @return the initial side of the Triangle. 
     */
    
    public int getInitialSide(){
        return this.initialSide;
    }
    
    /**
     * @return the side of the Triangle. 
     */

    public int getSide(){
        return this.side;
    }
    
    /**
     * @param Resets the side.
     */
    
    public void setSide(int side){
        this.side=side;
    }

    /**
     * @return the side of the Triangle.
     */
    
    public int getHeight() {
        return side;
    }
    
    
    /**
     * @return True if the Triangle has gone out of bounds in x.
     */
    @Override
    public boolean outOfBoundsX(double winX){
        return (this.xPoints[1] > winX) || (this.xPoints[0] < 0);
    }
    
    /**
     * @return True if the Triangle has gone out of bounds in y.
     */

    @Override
    public boolean outOfBoundsY(double winY){
        return (this.yPoints[2] > winY) || (this.yPoints[0] < 0);
    }

    /**
     * Puts the Triangle back in bounds in x.
     */
    @Override
    public void putInBoundsX (double winX) {
        if (this.xPoints[0] < 0){
            this.xPoints[0] = 0;
         
            setCoordinates(this.xPoints[0], this.yPoints[0]);
           
        }
        if (this.xPoints[2] > winX) {
            this.xPoints[2] = (int) (winX - Math.ceil (this.getWidth ()));
            //System.out.println("out of bounds"); checking if there is any errors.
            setCoordinates(this.xPoints[0], this.yPoints[0]);
          
          
        }
    }

    /**
     * Puts the Triangle back in bounds in y.
     */
    
    @Override
    public void putInBoundsY (double winY) {
        if (this.yPoints[2] > winY ){
        	//System.out.println("out of bounds"); checking if there is any errors
            this.yPoints[2] = winY;
            
          
        }
        if (this.yPoints[0] < 0) {
            this.yPoints[0] = 0;
          
        }
    } 

    /**
     * Moves the shape around the screen.
     */

    @Override
    public void move() {
        for(int i = 0; i < 3; i++){
            this.xPoints[i] += xVec;
            this.yPoints[i] += yVec;
        }
    } 

    /**
     * Draw the circle on the screen.
     * @param g The graphics object of the scene component.
     */
    
    public void draw(GraphicsContext g) {
        g.setFill( colour );
        g.setStroke( colour );
        if (isFilled) {
            g.fillPolygon(xPoints, yPoints, 3);
        }
        else {
            g.strokePolygon(xPoints, yPoints, 3);
        }
    }
}