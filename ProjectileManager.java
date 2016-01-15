public class ProjectileManager {
	Projectile[] projectiles;
	int indexOut, length;
	
	public ProjectileManager(int cap) {
	    projectiles = new Projectile[cap];
	    length = 0;
	    indexOut = 0;
	}

	public void newProjectile(Projectile p) {
	    length += 1;
	    projectiles[length - 1] = p;   
	}
	
	public int getLength() {
	    return length;
	}
	
	public Projectile getProj(int index) {
	    indexOut = index;
	    return projectiles[index];
	}

	public void replaceProjectile(Projectile p) {
	    projectiles[indexOut] = p;
	}

	public void replaceProjectile(boolean choose) {
	    if (!choose) {
		for (int i = indexOut; i < (projectiles.length - 1); i++) {
		    projectiles[i] = projectiles[i+1];
		}
		length -= 1;
	    }
	}


	    
	    
}


