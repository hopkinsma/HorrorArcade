public class Player {
	int life, destX, destY, x, y, level, slowAffect;
	int direction, lookDir, timesMoved, coolDown, speed;
	String name;
	double angle;
	boolean atDest, isAlive;
	final int COOLDOWN_TIME = 7;

	public Player(String s) {
		name = s;
		life = 100;
		level = 1;
		direction = 0;
		lookDir = 0;
		x = 950;
		y = 400;
		destX = x;
		destY = y;
		speed = 10;
		slowAffect = 0;
		isAlive = true;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public boolean isAtDest() {
		return atDest;
	}
	
	public int getXDest() {
		return destX;
	}

	public int getYDest() {
		return destY;
	}
	
	public int getDir() {
		return direction;
	}

	public int getLookDir() {
		return lookDir;
	}

	public double getAngle() {
		return angle;
	}
	
	private int getXDiff() {
		return (destX - x);
	}

	private int getYDiff() {
		return (destY - y);
	}

	public void takeDamage(int amt) {
	    	life -= amt;
	}
	
	public int getLife() {
	    	return life;
	}
	
	public void fire(int xDest, int yDest) {
	    //calculate the direction we need to face.
	    calcLookDir(xDest, yDest);
	    
	    //and the other things.
	    coolDown = COOLDOWN_TIME;
	    int turnDist = Math.abs(direction - lookDir);
	    if (turnDist > 20) {
		if ((direction > 30) && (lookDir <= 10))
		    turnDist = (40 - direction) + lookDir;
		else
		    turnDist = (40 - lookDir) + direction;
	    }
	    
	    if (turnDist < 6) {
		slowAffect = 0;
	    }
	    else if (turnDist < 12) {
		slowAffect = 2;
	    }
	    else {
		slowAffect = 4;
	    }
	
	} 

	public boolean canFire() {
	    if (coolDown == 0)
	        return true;
	    else
		return false;
	}
	
	public void update(boolean walkable) 
	{
		int distFromDest;
		
		//do movement stuff.
		if ((!walkable) || (atDest)) {
			//do nothing;
		}
		else {
		    distFromDest = Math.abs(getXDiff()) + Math.abs(getYDiff());
		    if (distFromDest <= 10) {
			x = destX;
			y = destY;
			atDest = true;
		    }
		    else {
			x += getXAffect();
			y += getYAffect();
			timesMoved += 1;
			//correct course if we've moved 5 times.
			if (timesMoved >= 5)
			    calcDirection();
		    }
		}

		//do cooldown stuff
		if (coolDown > 0)
		    coolDown -= 1;
	}
		    
		
	private void calcDirection() {
		if (getYDiff() >= 0) 
		    angle = (Math.atan2(getYDiff(), getXDiff()) / Math.PI) * 180;
		else
		    angle = 180 + (180 + (Math.atan2(getYDiff(), getXDiff())) / Math.PI * 180);
 		
		direction = (int)Math.round(angle/9);
		if (direction == 40)
		    direction = 0;
		timesMoved = 0;
	}

	private void calcLookDir(int x1, int y1) {
		if ((y1 - y) >= 0) 
		    angle = (Math.atan2((y1 - y), (x1 - x)) / Math.PI) * 180;
		else
		    angle = 180 + (180 + (Math.atan2((y1 - y), (x1 - x)) / Math.PI * 180));
 		
		lookDir = (int)Math.round(angle/9);
		if (lookDir == 40)
		    lookDir = 0;
	}
	    
		
	
	public void setDest(int setX, int setY) {
		//set the destination.
		destX = setX;
  		destY = setY;
		atDest = false;
		calcDirection();
		lookDir = direction;
		slowAffect = 0;
    	}

    public int getXAffect() {
        int spd = speed - slowAffect;
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

    public int getYAffect() {
        int spd = speed - slowAffect;
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

