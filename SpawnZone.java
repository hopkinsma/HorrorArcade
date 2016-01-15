public class SpawnZone {
    int startX, endX, startY, endY;
    int width, height;

    public SpawnZone(int x1, int y1, int x2, int y2) {
	startX = x1;
	startY = y1;
	endX = x2;
	endY = y2;
	width = endX - startX;
	height = endY - startY;
    }

    public int getRandX() {
	int rand = (int)(Math.round(Math.random() * width) + startX);
	return rand;
    }

    public int getRandY() {
	int rand = (int)(Math.round(Math.random() * height) + startY);
	return rand;
    }
}
	    
