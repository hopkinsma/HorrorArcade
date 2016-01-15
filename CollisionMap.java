import java.awt.image.*;
import java.awt.Image;

public class CollisionMap
{
	int [] pixels;
	PixelGrabber pg;
	int height, width;
	public CollisionMap(Image i, int x, int y) {
	    pixels = new int[(x * y)];
	    width = x;
	    height = y;
	    pg = new PixelGrabber(i, 0, 0, width, height, pixels, 0, height);
	    try
	    {
		pg.grabPixels();
	    }
	    catch (Exception e) {}
   	}

	public boolean walkable(int x, int y) {
	    int col = pixels[x + y * height] & 	0x000000FF;
	    if (col > 50)
		return true;
	    else
		return false;
	}

	public boolean canSee(int xStart, int yStart, int xEnd, int yEnd, int startDir) {
	    double angle;
	    int yDiff = yEnd - yStart;
	    int xDiff = xEnd - xStart;

	    if (yDiff >= 0) 
	        angle = (Math.atan2(yDiff, xDiff) / Math.PI) * 180;
	    else
	        angle = 180 + (180 + (Math.atan2(yDiff, xDiff)) / Math.PI * 180);

	    int reqDir = (int)(Math.round(angle/9));
	    if (reqDir > 39)
		reqDir = 0;
	    //see if they are facing in the right direction to be able to see	
	    if ((reqDir > (startDir - 8)) && (reqDir < (startDir))) { 
	        //do a traversal of the spots, see if they could walk a straight line to objective
		int curX = xStart;
		int curY = yStart;
		int moveTimes = (int)Math.round((Math.sqrt((xDiff * xDiff) + (yDiff * yDiff)) / 10));
		boolean travCheck = true;
		for (int i = 0; i < moveTimes; i++) {
		    curX += getXAffect(reqDir);
		    curY += getYAffect(reqDir);
		    if (walkable(curX, curY) == false)
		        travCheck = false;
		}
    	        return travCheck;
	    }
	    else {
		return false;
	    }
	}

	
    	private int getXAffect(int direction) {
            int spd = 10;
	    switch(direction) {
	        case 0: return (int)Math.round(1 *  spd);
	        case 1: return (int)Math.round(0.9 *  spd);
	        case 2: return (int)Math.round(0.8 * spd);
	        case 3: return (int)Math.round(0.7 * spd);
	        case 4: return (int)Math.round(0.6 * spd);
	        case 5: return (int)Math.round(0.5 * spd);
	        case 6: return (int)Math.round(0.4 * spd);
	        case 7: return (int)Math.round(0.3 * spd);
	    	case 8: return (int)Math.round(0.2 * spd);
	    	case 9: return (int)Math.round(0.1 * spd);
	    	case 10: return 0;
	    	case 11: return (int)Math.round(-0.1 * spd);
	    	case 12: return (int)Math.round(-0.2 * spd);
	    	case 13: return (int)Math.round(-0.3 * spd);
	    	case 14: return (int)Math.round(-0.4 * spd);
	    	case 15: return (int)Math.round(-0.5 * spd);
	    	case 16: return (int)Math.round(-0.6 * spd);
	    	case 17: return (int)Math.round(-0.7 * spd);
	    	case 18: return (int)Math.round(-0.8 * spd);
	    	case 19: return (int)Math.round(-0.9 * spd);
	    	case 20: return (int)Math.round(-1 * spd);
	    	case 21: return (int)Math.round(-0.9 * spd);
	    	case 22: return (int)Math.round(-0.8 * spd);
	    	case 23: return (int)Math.round(-0.7 * spd);
	    	case 24: return (int)Math.round(-0.6 * spd);
	    	case 25: return (int)Math.round(-0.5 * spd);
	    	case 26: return (int)Math.round(-0.4 * spd);
	    	case 27: return (int)Math.round(-0.3 * spd);
	    	case 28: return (int)Math.round(-0.2 * spd);
	    	case 29: return (int)Math.round(-0.1 * spd);
	    	case 30: return  0;
	    	case 31: return (int)Math.round(0.1 * spd);
	    	case 32: return (int)Math.round(0.2 * spd);
	    	case 33: return (int)Math.round(0.3 * spd); 
	    	case 34: return (int)Math.round(0.4 * spd);
	    	case 35: return (int)Math.round(0.5 * spd);
	    	case 36: return (int)Math.round(0.6 * spd);
	    	case 37: return (int)Math.round(0.7 * spd);
	    	case 38: return (int)Math.round(0.8 * spd);
	    	case 39: return (int)Math.round(0.9 * spd);
	    	default: return 0; 
	    }
        }

    	private int getYAffect(int direction) {
            int spd = 10;
            switch(direction) {
            	case 0: return 0;
            	case 1: return (int)Math.round(0.1 * spd);
            	case 2: return (int)Math.round(0.2 * spd);
            	case 3: return (int)Math.round(0.3 * spd);
            	case 4: return (int)Math.round(0.4 * spd);
            	case 5: return (int)Math.round(0.5 * spd);
            	case 6: return (int)Math.round(0.6 * spd);
            	case 7: return (int)Math.round(0.7 * spd);
            	case 8: return (int)Math.round(0.8 * spd);
            	case 9: return (int)Math.round(0.9 * spd);
            	case 10: return (int)Math.round(1 * spd);
            	case 11: return (int)Math.round(0.9 * spd);
            	case 12: return (int)Math.round(0.8 * spd);
            	case 13: return (int)Math.round(0.7 * spd);
            	case 14: return (int)Math.round(0.6 * spd);
            	case 15: return (int)Math.round(0.5 * spd);
            	case 16: return (int)Math.round(0.4 * spd);
            	case 17: return (int)Math.round(0.3 * spd);
            	case 18: return (int)Math.round(0.2 * spd);
            	case 19: return (int)Math.round(0.1 * spd);
            	case 20: return 0;
            	case 21: return (int)Math.round(-0.1 * spd);
            	case 22: return (int)Math.round(-0.2 * spd);
            	case 23: return (int)Math.round(-0.3 * spd);
            	case 24: return (int)Math.round(-0.4 * spd);
            	case 25: return (int)Math.round(-0.5 * spd);
            	case 26: return (int)Math.round(-0.6 * spd);
            	case 27: return (int)Math.round(-0.7 * spd);
            	case 28: return (int)Math.round(-0.8 * spd);
            	case 29: return (int)Math.round(-0.9 * spd);
            	case 30: return (int)Math.round(-1 * spd);
            	case 31: return (int)Math.round(-0.9 * spd);
            	case 32: return (int)Math.round(-0.8 * spd);
            	case 33: return (int)Math.round(-0.7 * spd);
            	case 34: return (int)Math.round(-0.6 * spd);
            	case 35: return (int)Math.round(-0.5 * spd);
            	case 36: return (int)Math.round(-0.4 * spd);
            	case 37: return (int)Math.round(-0.3 * spd);
            	case 38: return (int)Math.round(-0.2 * spd);
            	case 39: return (int)Math.round(-0.1 * spd);
            	default: return 0;
            }
        }
}
