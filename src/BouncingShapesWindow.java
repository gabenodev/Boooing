
import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 
 * @author Dr D. Archambault, Modified  for JAVAFX by Dr J. L. Jones
 * 
 */
public class BouncingShapesWindow {
	
	private static final int ANIMATION_DELAY = 10;
	private static final String FRAME_TITLE = "Shape Booooiiinggg Frame";
	
	private GraphicsContext gc;
	private Queue<ClosedShape> shapesToAdd;
	private ArrayList<ClosedShape> activeShapes;
	private int currentTime = 0;
	private boolean flag=true;
	
	private String filename;
	
	
	public BouncingShapesWindow(GraphicsContext gc,String filename) {
		this.gc=gc;
		
		activeShapes=new ArrayList<ClosedShape>();
		this.initShapes(filename);
		this.insertShapes ();
		drawClosedShapes();
		actionPerformed();
	}
	
	private void drawClosedShapes () {
		for (ClosedShape s : activeShapes)
		{
			s.draw(gc);
		}
	}
	
	private void initShapes (String filename) {
		shapesToAdd = ReadShapeFile.readDataFile(filename);
	}
	
	private void insertShapes() {
		//no more shapes to add, we are done
		if (shapesToAdd.isEmpty ()) {
			return;
		}
		
		//add shapes if needed
		ClosedShape current = shapesToAdd.peek ();
		while (!shapesToAdd.isEmpty () && (current.getInsertionTime() <= currentTime*ANIMATION_DELAY)) {
			activeShapes.add(current);
			shapesToAdd.dequeue();
			if (!shapesToAdd.isEmpty ()) {
				current = shapesToAdd.peek();
			}
		}
	}
	
	public void actionPerformed()
	{
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(5),ae -> onTime()));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();

	}
	
	private void onTime() {
		currentTime++;
		double h =gc.getCanvas().getHeight();
		double w = gc.getCanvas().getWidth();
		gc.clearRect(0, 0, w, h);
		moveShapes();
		insertShapes ();
		drawClosedShapes();
	}
	
	/**
	 * Moves the shape around the screen.
	 * Makes the shape pulse depending if it's true or false.
	 */
	public void moveShapes()
	{
		double  dimsY = gc.getCanvas().getHeight() ;
		double  dimsX = gc.getCanvas().getWidth() ;
		for (ClosedShape s : activeShapes)
		{
			s.move();
			// check if the Pulsing is true.
			if(s.isPulsing()){
				// If the pulse is true check what kind of shape is(circle,rect,oval,triangle,square)
                if(s.getShape().equals("circle")){
                    Circle circle=(Circle)s;
                    if(circle.getDiameter()<3*circle.getInitialDiameter() && circle.getMoving()) {
                        circle.setDiameter(circle.getDiameter() + 1);
                    }
                    else if(circle.getDiameter()>circle.getInitialDiameter()) {
                        circle.setDiameter(circle.getDiameter() - 1);
                        circle.setMoving(false);
                    }
                    else{
                        circle.setMoving(true);
                    }
                }
                else if(s.getShape().equals("rect")){
                    Rect rect=(Rect)s;
                    if(rect.getHeight()*rect.getWidth()<4*rect.getInitialHeight()*rect.getInitialWidth() && rect.getMoving()){
                        rect.setHeight(rect.getHeight()+1);
                        rect.setWidth(rect.getWidth()+1);
                    }
                    else if(rect.getHeight()*rect.getWidth()
                    		>rect.getInitialHeight()*rect.getWidth()) {
                        rect.setHeight(rect.getHeight() - 1);
                        rect.setWidth(rect.getWidth()-1);
                        rect.setMoving(false);
                    }
                    else{
                        rect.setMoving(true);
                    }
                }
                else if(s.getShape().equals("oval")){
                    Oval oval=(Oval) s;
                   if(oval.getHeight()*oval.getWidth()<10*oval.getInitialHeight()*oval.getInitialWidth() && oval.getMoving()){
                       oval.setHeight(oval.getHeight()+1);
                       oval.setWidth(oval.getWidth()+1);
                   }
                   else if(oval.getHeight()*oval.getWidth()>oval.getInitialHeight()*oval.getInitialWidth()){
                        oval.setHeight(oval.getHeight()-1);
                        oval.setWidth(oval.getWidth()-1);
                        oval.setMoving(false);
                   }
                   else{
                       oval.setMoving(true);
                   }
                }
                else if(s.getShape().equals("triangle")){
                    Triangle triangle=(Triangle) s;
                    if(triangle.getSide()<3*triangle.getInitialSide() && triangle.getMoving()) {
                        triangle.setSide(triangle.getSide() + 1);
                    }
                    else if(triangle.getSide()>triangle.getInitialSide()){
                        triangle.setSide(triangle.getWidth()-1);
                        triangle.setMoving(false);
                    //    System.out.println("Side =" + triangle.getSide()); couldnt figure out why it goes here
                    }
                    else{
                        triangle.setMoving(true);
                    }
                }
                else if(s.getShape().equals("square")){
                    Square square=(Square) s;
                    if(square.getSide()<5*square.getInitialSide() && square.getMoving()) {
                        square.setSide(square.getSide() + 1);
                    }
                    else if(square.getSide()>square.getInitialSide()){
                        square.setSide(square.getSide()-1);
                        square.setMoving(false);
                    }
                    else{
                        square.setMoving(true);
                    }
                }
            }
			
			// Move us back in and bounce if we went outside the drawing area.
			if (s.outOfBoundsX(dimsX))
			{
				s.putInBoundsX(dimsX);
				s.bounceX();
			}
			
			if (s.outOfBoundsY(dimsY))
			{
				s.putInBoundsY(dimsY);
				s.bounceY();
			}
			
		}
	}
	 
}

