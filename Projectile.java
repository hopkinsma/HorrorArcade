public class Projectile {
    int x, y, direction;
    boolean alive;
    int lifeSpan = 20;
    int speed = 20;

    public Projectile(int startX, int startY, int dir) {
	x = startX;
	y = startY;
	direction = dir;
	alive = true;
    }

    public int getDir() {
	return direction;
    }

    public void update(boolean walkable) {
       if (alive) {
       	   if (walkable) {
	       x += getXAffect();
	       y += getYAffect();
	       lifeSpan -= 1;
	       if (lifeSpan <= 0)
	           alive = false;
	   }
	   else {
	       alive = false;
	   }
       }
       else{
       }
    }

    public void kill() {
	alive = false;
    }

    public boolean isAlive() {
	return alive;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
	return y;
    }
    
    /*	public int getXAffect() {
		switch(direction) {
		    case 0: return 10;
		    case 1: return 9;
		    case 2: return 8;
		    case 3: return 7;
	 	    case 4: return 6;
		    case 5: return 5;
	 	    case 6: return 4;
		    case 7: return 3;
		    case 8: return 2;
		    case 9: return 1;
		    case 10: return 0;
		    case 11: return -1;
		    case 12: return -2;
		    case 13: return -3;
	 	    case 14: return -4;
		    case 15: return -5;
	 	    case 16: return -6;
		    case 17: return -7;
		    case 18: return -8;
		    case 19: return -9;
		    case 20: return -10;
		    case 21: return -9;
		    case 22: return -8;
		    case 23: return -7;
	 	    case 24: return -6;
		    case 25: return -5;
	 	    case 26: return -4;
		    case 27: return -3;
		    case 28: return -2;
		    case 29: return -1;
		    case 30: return -0;
		    case 31: return 1;
		    case 32: return 2;
		    case 33: return 3;
	 	    case 34: return 4;
		    case 35: return 5;
	 	    case 36: return 6;
		    case 37: return 7;
		    case 38: return 8;
		    case 39: return 9;
		    default: return 0; 
		}
	}

	public int getYAffect() {
		switch(direction) {
		    case 0: return 0;
		    case 1: return 1;
		    case 2: return 2;
		    case 3: return 3;
	 	    case 4: return 4;
		    case 5: return 5;
	 	    case 6: return 6;
		    case 7: return 7;
		    case 8: return 8;
		    case 9: return 9;
		    case 10: return 10;
		    case 11: return 9;
		    case 12: return 8;
		    case 13: return 7;
	 	    case 14: return 6;
		    case 15: return 5;
	 	    case 16: return 4;
		    case 17: return 3;
		    case 18: return 2;
		    case 19: return 1;
		    case 20: return 0;
		    case 21: return -1;
		    case 22: return -2;
		    case 23: return -3;
	 	    case 24: return -4;
		    case 25: return -5;
	 	    case 26: return -6;
		    case 27: return -7;
		    case 28: return -8;
		    case 29: return -9;
		    case 30: return -10;
		    case 31: return -9;
		    case 32: return -8;
		    case 33: return -7;
	 	    case 34: return -6;
		    case 35: return -5;
	 	    case 36: return -4;
		    case 37: return -3;
		    case 38: return -2;
		    case 39: return -1;
		    default: return 0;
		}
	}*/
    
    public int getXAffect() {
        double aff;
	switch(direction) {
	    case 0: return (int)Math.round(1 *  speed);
	    case 1: return (int)Math.round(0.9 *  speed);
	    case 2: return (int)Math.round(0.8 * speed);
	    case 3: return (int)Math.round(0.7 * speed);
	    case 4: return (int)Math.round(0.6 * speed);
	    case 5: return (int)Math.round(0.5 * speed);
	    case 6: return (int)Math.round(0.4 * speed);
	    case 7: return (int)Math.round(0.3 * speed);
	    case 8: return (int)Math.round(0.2 * speed);
	    case 9: return (int)Math.round(0.1 * speed);
	    case 10: return 0;
	    case 11: return (int)Math.round(-0.1 * speed);
	    case 12: return (int)Math.round(-0.2 * speed);
	    case 13: return (int)Math.round(-0.3 * speed);
	    case 14: return (int)Math.round(-0.4 * speed);
	    case 15: return (int)Math.round(-0.5 * speed);
	    case 16: return (int)Math.round(-0.6 * speed);
	    case 17: return (int)Math.round(-0.7 * speed);
	    case 18: return (int)Math.round(-0.8 * speed);
	    case 19: return (int)Math.round(-0.9 * speed);
	    case 20: return (int)Math.round(-1 * speed);
	    case 21: return (int)Math.round(-0.9 * speed);
	    case 22: return (int)Math.round(-0.8 * speed);
	    case 23: return (int)Math.round(-0.7 * speed);
	    case 24: return (int)Math.round(-0.6 * speed);
	    case 25: return (int)Math.round(-0.5 * speed);
	    case 26: return (int)Math.round(-0.4 * speed);
	    case 27: return (int)Math.round(-0.3 * speed);
	    case 28: return (int)Math.round(-0.2 * speed);
	    case 29: return (int)Math.round(-0.1 * speed);
	    case 30: return  0;
	    case 31: return (int)Math.round(0.1 * speed);
	    case 32: return (int)Math.round(0.2 * speed);
	    case 33: return (int)Math.round(0.3 * speed); 
	    case 34: return (int)Math.round(0.4 * speed);
	    case 35: return (int)Math.round(0.5 * speed);
	    case 36: return (int)Math.round(0.6 * speed);
	    case 37: return (int)Math.round(0.7 * speed);
	    case 38: return (int)Math.round(0.8 * speed);
	    case 39: return (int)Math.round(0.9 * speed);
	    default: return 0; 
	}
    }

    public int getYAffect() {
        double aff;
        switch(direction) {
            case 0: return 0;
            case 1: return (int)Math.round(0.1 * speed);
            case 2: return (int)Math.round(0.2 * speed);
            case 3: return (int)Math.round(0.3 * speed);
            case 4: return (int)Math.round(0.4 * speed);
            case 5: return (int)Math.round(0.5 * speed);
            case 6: return (int)Math.round(0.6 * speed);
            case 7: return (int)Math.round(0.7 * speed);
            case 8: return (int)Math.round(0.8 * speed);
            case 9: return (int)Math.round(0.9 * speed);
            case 10: return (int)Math.round(1 * speed);
            case 11: return (int)Math.round(0.9 * speed);
            case 12: return (int)Math.round(0.8 * speed);
            case 13: return (int)Math.round(0.7 * speed);
            case 14: return (int)Math.round(0.6 * speed);
            case 15: return (int)Math.round(0.5 * speed);
            case 16: return (int)Math.round(0.4 * speed);
            case 17: return (int)Math.round(0.3 * speed);
            case 18: return (int)Math.round(0.2 * speed);
            case 19: return (int)Math.round(0.1 * speed);
            case 20: return 0;
            case 21: return (int)Math.round(-0.1 * speed);
            case 22: return (int)Math.round(-0.2 * speed);
            case 23: return (int)Math.round(-0.3 * speed);
            case 24: return (int)Math.round(-0.4 * speed);
            case 25: return (int)Math.round(-0.5 * speed);
            case 26: return (int)Math.round(-0.6 * speed);
            case 27: return (int)Math.round(-0.7 * speed);
            case 28: return (int)Math.round(-0.8 * speed);
            case 29: return (int)Math.round(-0.9 * speed);
            case 30: return (int)Math.round(-1 * speed);
            case 31: return (int)Math.round(-0.9 * speed);
            case 32: return (int)Math.round(-0.8 * speed);
            case 33: return (int)Math.round(-0.7 * speed);
            case 34: return (int)Math.round(-0.6 * speed);
            case 35: return (int)Math.round(-0.5 * speed);
            case 36: return (int)Math.round(-0.4 * speed);
            case 37: return (int)Math.round(-0.3 * speed);
            case 38: return (int)Math.round(-0.2 * speed);
            case 39: return (int)Math.round(-0.1 * speed);
            default: return 0;
        }
    }
    
}
	

