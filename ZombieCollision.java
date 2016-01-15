import java.awt.image.*;
import java.awt.Image;

public class ZombieCollision {
	int [] pixels;
	PixelGrabber pg;
	int height, width;
	int bestDamLevel, bestX, bestY;

	public ZombieCollision(Image i, int x, int y) {
	    pixels = new int[(x * y)];
	    width = x;
	    height = y;
	    bestDamLevel = 0;
	    bestX = 0;
	    bestY = 0;
	    pg = new PixelGrabber(i, 0, 0, width, height, pixels, 0, height);
	    try
	    {
		pg.grabPixels();
	    }
	    catch (Exception e) {}
   	}

	public boolean isAHit(int x, int y, int zDir, int pDir) {
	    
	    double curX = (double)x;
	    double curY = (double)y;

	    bestDamLevel = 0;
  	    bestX = 0;
	    bestY = 0;
	    while ((x > 0) && (y > 0) && (x < width) && (y < height)) {
	        int red = (pixels[(zDir * 80 + x) + (y * height)] & 0x00FF0000) >> 16;
	        int green = (pixels[(zDir * 80 + x) + (y * height)] & 0x00FF0000) >> 8;
	        int blue = (pixels[(zDir * 80 + x) + (y * height)] & 0x00FF0000);
		
		if ((red < 50) && (green < 50) && (blue < 50)) {
		    bestDamLevel = 3;
		    bestX = x;
		    bestY = y;
		}
	        else if ((red < 100) && (green < 100) && (blue < 100)) {
		    if (bestDamLevel <= 2) {
			bestDamLevel = 2;
			bestX = x;
			bestY = y;
			System.out.println("Hit!");
		    }
	        }
	        else if ((red < 150) && (green < 150) && (blue < 150)) {
		    if (bestDamLevel <= 1) {
			bestDamLevel = 1;
			bestX = x;
			bestY = y;
		    }
	        }
	        else { //no damage level
	        }
		
		curX += getXAffect(pDir);
		curY += getYAffect(pDir);
		x = (int)Math.round(curX);
		y = (int)Math.round(curY);
	    } 

	    if (bestDamLevel == 0) {
		return false;
	    }
	    else {
		return true;
	    }
        }

	public int getDamage() {
	    if (bestDamLevel == 0) {
		return 0;
	    }
	    else if (bestDamLevel == 1) {
		return (int)(Math.round(Math.random() * 10) + 15);
	    }
	    else if (bestDamLevel == 2) {
		return (int)(Math.round(Math.random() * 20) + 30);
	    }
	    else {
		return (int)(Math.round(Math.random() * 60) + 50);
	    }
	}

	public int getSplatSize() {
	    if (bestDamLevel == 0) {
		return 0;
	    }
	    else if (bestDamLevel == 1) {
		return 6;
	    }
	    else if (bestDamLevel == 2) {
		return 12;
	    }
	    else {
		return 20;
	    }
	}

	public int getBestX() {
	    return bestX;
	}

	public int getBestY() {
	    return bestY;
	}

	private double getXAffect(int direction) {
	    switch(direction) {
	        case 0: return 1;
	        case 1: return 0.9;
	        case 2: return 0.8;
	        case 3: return 0.7;
	        case 4: return 0.6;
	        case 5: return 0.5;
	        case 6: return 0.4;
	        case 7: return 0.3;
	    	case 8: return 0.2;
	    	case 9: return 0.1;
	    	case 10: return 0;
	    	case 11: return -0.1;
	    	case 12: return -0.2;
	    	case 13: return -0.3;
	    	case 14: return -0.4;
	    	case 15: return -0.5;
	    	case 16: return -0.6;
	    	case 17: return -0.7;
	    	case 18: return -0.8;
	    	case 19: return -0.9;
	    	case 20: return -1;
	    	case 21: return -0.9;
	    	case 22: return -0.8;
	    	case 23: return -0.7;
	    	case 24: return -0.6;
	    	case 25: return -0.5;
	    	case 26: return -0.4;
	    	case 27: return -0.3;
	    	case 28: return -0.2;
	    	case 29: return -0.1;
	    	case 30: return  0;
	    	case 31: return 0.1;
	    	case 32: return 0.2;
	    	case 33: return 0.3;
	    	case 34: return 0.4;
	    	case 35: return 0.5;
	    	case 36: return 0.6;
	    	case 37: return 0.7;
	    	case 38: return 0.8;
	    	case 39: return 0.9;
	    	default: return 0; 
	    }
        }

    	private double getYAffect(int direction) {
            switch(direction) {
            	case 0: return 0;
	    	case 1: return 0.1;
	    	case 2: return 0.2;
	    	case 3: return 0.3;
	    	case 4: return 0.4;
	    	case 5: return 0.5;
	    	case 6: return 0.6;
	    	case 7: return 0.7;
	    	case 8: return 0.8;
	    	case 9: return 0.9;
            	case 10: return 1;
	        case 11: return 0.9;
	        case 12: return 0.8;
	        case 13: return 0.7;
	        case 14: return 0.6;
	        case 15: return 0.5;
	        case 16: return 0.4;
	        case 17: return 0.3;
	    	case 18: return 0.2;
	    	case 19: return 0.1;
            	case 20: return 0;
            	case 21: return -0.1;
	    	case 22: return -0.2;
	    	case 23: return -0.3;
	    	case 24: return -0.4;
	    	case 25: return -0.5;
	    	case 26: return -0.6;
	    	case 27: return -0.7;
	    	case 28: return -0.8;
	    	case 29: return -0.9;
	    	case 30: return -1;
	    	case 31: return -0.9;
	    	case 32: return -0.8;
	    	case 33: return -0.7;
	    	case 34: return -0.6;
	    	case 35: return -0.5;
	    	case 36: return -0.4;
	    	case 37: return -0.3;
	    	case 38: return -0.2;
	    	case 39: return -0.1;
            	default: return 0;
            }
        }

}
