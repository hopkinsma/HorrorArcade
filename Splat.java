public class Splat {
    int x, y, dir, speed, picNum, life;
    boolean alive;

    public Splat(int xStart, int yStart, int dirStart, int spStart, int lifespan, int pic) {
	    x = xStart;
	    y = yStart;
	    dir = dirStart;
	    speed = spStart;
	    life = lifespan;
	    picNum = pic;
	    alive = true;
    }

    public int getX() {
	    return x;
    }

    public int getY() {
	    return y;
    }

    public int getPicNum() {
	    return picNum;
    }

    public boolean isAlive() {
	    return alive;
    }

    public void update() {
	    x += getXAffect();
	    y += getYAffect();
	    life -= 1;
	    if (life <= 0)
		alive = false;
    }

    public int getXAffect() {
        switch(dir) {
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
        switch(dir) {
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
