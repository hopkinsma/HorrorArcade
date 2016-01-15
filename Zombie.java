public class Zombie {
	int x, y, destX, destY, direction, mapX, mapY, timesMoved, speed, destDir;
	int life, attackCounter, strength;
	double angle;
	boolean spawned, atDest, attackSequence, attackFlag;
	SpawnZone myZone;

	public Zombie(int mapX, int mapY, SpawnZone s) {
	    this.mapX = mapX;
	    this.mapY = mapY;
	    myZone = s;
	    spawned = false;
	}

	public void spawn() {
	    direction = (int)Math.round(Math.random() * 40);
	    destDir = direction;
	    x = myZone.getRandX();
	    y = myZone.getRandY();
	    destX = x;
	    destY = y;
	    timesMoved = 0;
	    speed = 10;
	    life = 100;
	    strength = 20;
	    atDest = true;
	    attackCounter = 0;
	    attackFlag = false;
	    attackSequence = false;
	    spawned = true;
	}

	
	public boolean containsPoint(int pointX, int pointY) {
	    if ((pointX > (x - 39)) && (pointX < (x + 39)) && (pointY > (y - 39)) && (pointY < (y + 39))) {
		return true;
	    }
	    else {
		return false;
	    }
	}
	
	public void takeDamage(int amt) {
	    life -= amt;
	}
	
	public boolean isAlive() {
	    if (life > 0) 
		return true;
	    else
		return false;
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

	public int getDir() {
	   return direction;
	}

	private int getXDiff() {
	    return (destX - x);
	}

	private int getYDiff() {
	    return (destY - y);
	}

	private void updateAttack() {
	    attackSequence = true;
	    attackCounter += 1;
	    if (attackCounter >= 8) {
		attackCounter = 0;
		attackFlag = true;
	    }
	}

	public boolean checkAttack() {
	    if (attackFlag == true) {
		attackFlag = false;
		return true;
	    }
	    else {
		return false;
	    }
	}

	public int getDamage() {
	    return (int)Math.round(Math.random() * strength);
	}

	public void decideUpdate(int playX, int playY, boolean canSee) {
	    int xD = x - playX;
	    int yD = y - playY;
	    int playDist = (int)Math.round(Math.sqrt((xD * xD) + (yD * yD)));
	    //decide where we need to be going.
	    if (playDist < 35) { //set attacking to be true, just turn around a little for now.
	        updateAttack();
		if (destDir == direction) {
		    destDir = direction + (int)Math.round(((Math.random() * 2) + -1) * 5);
		    if (destDir < 0) 
		        destDir = destDir + 40;
		    else if (destDir > 39) 
		        destDir = destDir - 40;
		}
		atDest = true;
            }
	    else if (playDist < 100) { //head towards the player.
   	        destX = playX;
		destY = playY;
		atDest = false;
		attackSequence = false;
		calcDirection();
	    }
	    else if (playDist < 200) { //head towards the player if zombie can see them.
		if (canSee) {
		    destX = playX;
		    destY = playY;
		    atDest = false;
		    calcDirection();
		}
		else if (atDest) {
		    destX = (int)Math.round(((Math.random() * 2) - 1) * 100) + x;
    	            destY = (int)Math.round(((Math.random() * 2) - 1) * 100) + x;
		    atDest = false;
		    calcDirection();
		}
   	    }
	    else { //if we are at our current destination, pick a new one.
		if (atDest) {
		    destX = (int)Math.round(((Math.random() * 2) - 1) * 100) + x;
    	            destY = (int)Math.round(((Math.random() * 2) - 1) * 100) + x;
		    atDest = false;
		    calcDirection();
		}
	    }
	}
	    
	
	
	public void doUpdate(boolean walkable) 
	{
	    //if we are facing in the right direction, move, otherwise turn.
	    if ((direction > 30) && (destDir < 10)) {
		direction += 1;
		if (direction > 39)
		    direction = 0;
	    }
	    else if ((direction < 10) && (destDir > 30)) {
		direction -= 1;
		if (direction < 0)
		    direction = 39;
	    }
	    else if (direction < destDir) {
		direction += 1;
	    }
	    else if (direction > destDir) {
		direction -= 1;
	    }
	    else {
	        if (atDest) {
		    //do nothing.
		}
		else if (!walkable) { //set a random dest, away from the direction we are headed.
		    if ((direction > 35) || (direction <= 5)) {
			destX = (int)Math.round(((Math.random() * 2) - 1) * 100) + x;
    	            	destY = (int)Math.round((Math.random() * -100) + y);
		    }
		    else if ((direction > 5) && (direction <=15)) {
			destX = (int)Math.round((Math.random() * -100) + x);
    	            	destY = (int)Math.round(((Math.random() * 2) - 1) * 100) + y;
		    }
		    else if((direction > 15) && (direction <= 25)) {
			destX = (int)Math.round((Math.random() * 100) + x);
    	            	destY = (int)Math.round(((Math.random() * 2) - 1) * 100) + y;
		    }
		    else {
			destX = (int)Math.round(((Math.random() * 2) - 1) * 100) + x;
    	            	destY = (int)Math.round((Math.random() * 100) + y);
		    }
		    atDest = false;
		    calcDirection();
	        }
	        else if ((Math.abs(getXDiff()) + Math.abs(getYDiff())) <= 10) { //we're close, just put us there.
	            x = destX;
		    y = destY;
		    atDest = true;
	        }
	        else { //move along, zombie friend.
		    x += getXAffect();
		    y += getYAffect();
		    timesMoved += 1;
		    //correct course if we've moved 5 times.
		    if (timesMoved >= 5)
		        calcDirection();
	        }
	    }
	}

	private void calcDirection() {
	    if (getYDiff() >= 0) 
	        angle = (Math.atan2(getYDiff(), getXDiff()) / Math.PI) * 180;
	    else
	        angle = 180 + (180 + (Math.atan2(getYDiff(), getXDiff())) / Math.PI * 180);
 	    destDir = (int)Math.round(angle/9);
	    if (destDir == 40)
	        destDir = 0;
	    timesMoved = 0;
	}

	public int getXAffect() {
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
	}
	
	/*public int getXAffect() {
	    double aff;
	    switch(direction) {
		case 0: aff =  1;
		case 1: aff = .9;
		case 2: aff = .8;
		case 3: aff = .7;
	 	case 4: aff = .6;
		case 5: aff = .5;
	 	case 6: aff = .4;
		case 7: aff = .3;
		case 8: aff = .2;
		case 9: aff = .1;
		case 10: aff = 0;
		case 11: aff = -.1;
		case 12: aff = -.2;
		case 13: aff = -.3;
	 	case 14: aff = -.4;
		case 15: aff = -.5;
	 	case 16: aff = -.6;
		case 17: aff = -.7;
		case 18: aff = -.8;
		case 19: aff = -.9;
		case 20: aff = -1;
		case 21: aff = -.9;
		case 22: aff = -.8;
		case 23: aff = -.7;
	 	case 24: aff = -.6;
		case 25: aff = -.5;
	 	case 26: aff = -.4;
		case 27: aff = -.3;
		case 28: aff = -.2;
		case 29: aff = -.1;
		case 30: aff =  0;
		case 31: aff = .1;
		case 32: aff = .2;
		case 33: aff = .3;
	 	case 34: aff = .4;
		case 35: aff = .5;
	 	case 36: aff = .6;
		case 37: aff = .7;
		case 38: aff = .8;
		case 39: aff = .9;
		default: aff = 0; 
	    }
	    return (int)(Math.round(aff * speed));
	}

	public int getYAffect() {
	    double aff;
	    switch(direction) {
	        case 0: aff = 0;
	        case 1: aff = .1;
	        case 2: aff = .2;
	        case 3: aff = .3;
	        case 4: aff = .4;
	        case 5: aff = .5;
	        case 6: aff = .6;
	        case 7: aff = .7;
	        case 8: aff = .8;
	        case 9: aff = .9;
	        case 10: aff = 1;
	        case 11: aff = .9;
	        case 12: aff = .8;
	        case 13: aff = .7;
	        case 14: aff = .6;
	        case 15: aff = .5;
	        case 16: aff = .4;
	        case 17: aff = .3;
	        case 18: aff = .2;
	        case 19: aff = .1;
	        case 20: aff = 0;
	        case 21: aff = -.1;
	        case 22: aff = -.2;
	        case 23: aff = -.3;
	        case 24: aff = -.4;
	        case 25: aff = -.5;
	        case 26: aff = -.6;
	        case 27: aff = -.7;
	        case 28: aff = -.8;
	        case 29: aff = -.9;
	        case 30: aff = -1;
	        case 31: aff = -.9;
	        case 32: aff = -.8;
	        case 33: aff = -.7;
	        case 34: aff = -.6;
	        case 35: aff = -.5;
	        case 36: aff = -.4;
	        case 37: aff = -.3;
	        case 38: aff = -.2;
	        case 39: aff = -.1;
	        default: aff = 0;
	    }
	    return (int)(Math.round(aff * speed));
	}*/
}

