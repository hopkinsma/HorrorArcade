public class Splatter {
    Splat[] splats;
    int x, y, size, baseDir;
    boolean complete;

    public Splatter(int xPos, int yPos, int amt, int dir) {
        x = xPos;
	y = yPos;
	size = amt;
	baseDir = dir;
	splats = new Splat[size];
	complete = false;

	for (int i = 0; i < size; i++) {
	   int sDir = (int)(baseDir + Math.round((Math.random() * 2 - 1) * (Math.random() * 6)));
	   int speed = (int)(Math.round(Math.random() * 8)) + 8;
	   int lifeSpan = (int)(Math.round(Math.random() * 5) + 5);
	   int pic = (int)(Math.round(Math.random() * 5));
	   if (pic == 5)
	       pic = 4;
	   splats[i] = new Splat(0, 0, sDir, speed, lifeSpan, pic);
	}
    }

    public void update() {
	int numAlive = 0;
	for (int i = 0; i < size; i++) {
	   splats[i].update();
	   if (splats[i].isAlive())
	       numAlive += 1;
	}
	if (numAlive > 0)
	    complete = false;
	else
	    complete = true;
   
    }
    
    public int getX() {
	return x;
    }

    public int getY() {
	return y;
    }

    public int getSize() {
	return size;
    }
    
    public int getSplatX(int i) {
	return splats[i].getX();
    }

    public int getSplatY(int i) {
	return splats[i].getY();
    }

    public boolean isSplatAlive(int i) {
	return splats[i].isAlive();
    }

    public int getSplatPic(int i) {
	return splats[i].getPicNum();
    }
    
    public boolean isComplete() {
	return complete;
    }

}
    

    
