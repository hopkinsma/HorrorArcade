public class SplatterManager {
    Splatter[] splatters;
    int length;

    public SplatterManager(int cap) {
	splatters = new Splatter[cap];
	length = 0;
    }

    public void createNew(Splatter s) {
	length += 1;
	splatters[length - 1] = s;
    }

    public int getLength() {
	return length;
    }

    public Splatter getSplatter(int i) {
	return splatters[i];
    }

    public void update() {
	if (length > 0) {
	    int i = 0;
	    while (i < length) {
		splatters[i].update();
		if (splatters[i].isComplete()) {
		    for (int x = i; x < (splatters.length - 1); x++) {
			splatters[x] = splatters[x + 1];
		    }
		    length -= 1;
		}
		i += 1;
	    }
	}
    }

}

    
