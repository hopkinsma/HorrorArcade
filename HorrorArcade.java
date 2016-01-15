import java.applet.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;

public class HorrorArcade extends Applet implements Runnable, MouseListener, KeyListener {
	Thread timeThread;
	MediaTracker theTracker;
	LoadThread gameLoader;
	Player thePlayer;
	Zombie[] theZombies;
	ZombieCollision zCollide;
	ProjectileManager pMan;
	SplatterManager sMan;
	Graphics bBufferG;
	Font myFont;
	CollisionMap gameMap;
	public Image zombieLoad, heroLoad, bulletLoad;
	public Image zombieImage, heroImage, bulletImage;
	public Image zombieCol;
	public Image mapCollision, mapPicture;
	public Image shadowLoad, shadowImage;
	public Image destLoad, destImage;
	public Image bloodLoad, bloodImage;
	public Image backBuffer;
	SpawnZone[] spawnZones;
	boolean loaded, shifted;
	int updatePoint = 0;

	public void init()
	{
		theTracker = new MediaTracker(this);
		loaded = false;
		shifted = false;
		backBuffer = createImage(400, 400);
		bBufferG = backBuffer.getGraphics();
		thePlayer = new Player("Bob");
		myFont = new Font("Lucida Console", Font.BOLD, 16);
		addMouseListener(this);
		addKeyListener(this);
		gameLoader = new LoadThread();
		gameLoader.start();
		spawnZones = new SpawnZone[5];
		theZombies = new Zombie[10];
		pMan = new ProjectileManager(10);
		sMan = new SplatterManager(20);
		repaint();
	}

	public void start()
	{
		timeThread = new Thread(this);
		timeThread.start();
	}

	//Mouse input.
	public void mousePressed(MouseEvent e) 
	{
	    int x = e.getX();
	    int y = e.getY();
	    if (!loaded) {/*do nothing!*/}
	    else {
		//add other if blocks in here for menus.
		if (shifted) {  //fire new projectile, if possible.
		    if (thePlayer.canFire()) {
		        thePlayer.fire((thePlayer.getX() - 200) + x, (thePlayer.getY() - 200) + y);
			Projectile temp = new Projectile(thePlayer.getX(), thePlayer.getY(), thePlayer.getLookDir());
			pMan.newProjectile(temp);
		    }
		    else {
		    }
		}
		else { //set new destination for character.
		    int mapX = (thePlayer.getX() - 200) + x;
		    int mapY = (thePlayer.getY() - 200) + y;
		    thePlayer.setDest(mapX, mapY);
		}
	    }
	}

    	//Unused mouse event classes.
    	public void mouseClicked(MouseEvent e) {} 
    	public void mouseEntered(MouseEvent e) {}
    	public void mouseExited(MouseEvent e) {}
    	public void mouseReleased(MouseEvent e) {}

	//Keyboard input
    	public void keyPressed( KeyEvent e ) { 
    	    if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
	        shifted = true;
	    }
	    else {
	    }
	    e.consume();    
        }

        public void keyReleased( KeyEvent e ) { 
	    if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
	        shifted = false;
	    }
	    else {
	    }
	    e.consume();    
	}
	
	//Unused keyevent class.
        public void keyTyped( KeyEvent e ) {}
	
	
	
	
	//main game loop
	public void run()
	{
	    while (true) {
		if (loaded) {
		    //once again, add more iff blocks for menus.

		    thePlayer.update(gameMap.walkable(thePlayer.getX() + thePlayer.getXAffect(), thePlayer.getY() + thePlayer.getYAffect()));
		    //update the zombies.
		    for (int i = 0; i < 10; i++) {
		        if (theZombies[i].isAlive()) {
			    int zombX = theZombies[i].getX();
			    int zombY = theZombies[i].getY();
			    int playX = thePlayer.getX();
			    int playY = thePlayer.getY();
			    boolean lookCheck = gameMap.canSee(zombX, zombY, playX, playY, theZombies[i].getDir());
			    theZombies[i].decideUpdate(playX, playY, lookCheck);
			   
			    if (theZombies[i].checkAttack()) {
				thePlayer.takeDamage(theZombies[i].getDamage());
				Splatter newSplat = new Splatter(playX, playY, 5, theZombies[i].getDir()); 
				sMan.createNew(newSplat);
			    }

			    boolean walkCheck = gameMap.walkable(zombX + theZombies[i].getXAffect(), zombY + theZombies[i].getYAffect());
			    theZombies[i].doUpdate(walkCheck);
			}
		    }

		    //update the projectiles here
		    for (int i = 0; i < pMan.getLength(); i++) {
			Projectile temp = pMan.getProj(i); //get the projectile we need
			//calculate where it will be when it moves.
			int newX = temp.getX() + temp.getXAffect();
			int newY = temp.getY() + temp.getYAffect();
			//see if it hit a zombie, if so, kill it.
			boolean hitZombie = false;
			for (int z = 0; z < 10; z++) {
			    if ((theZombies[z].isAlive()) && (theZombies[z].containsPoint(newX, newY))) {
				//bullet is inside bounds of zombie picture, test against zombie collision map.
				int testX = newX - (theZombies[z].getX() - 40);
				int testY = newY - (theZombies[z].getY() - 40);
				if (zCollide.isAHit(testX, testY, theZombies[z].getDir(), temp.getDir())) {
				    hitZombie = true;
				    theZombies[z].takeDamage(zCollide.getDamage());
				    //create a blood-spatter.
				    int splatSize = zCollide.getSplatSize();
				    int sX = zCollide.getBestX() + (theZombies[z].getX() - 40);
				    int sY = zCollide.getBestY() + (theZombies[z].getY() - 40);
				    Splatter newSplat = new Splatter(sX, sY, splatSize, temp.getDir()); 
				    sMan.createNew(newSplat);
				    temp.kill();
				}
			    }
			}
			//if it didn't hit a zombie, update it (if it can move in the direction it is moving)
			if (!hitZombie) {
	  		    boolean walkCheck = gameMap.walkable(newX, newY);
			    temp.update(walkCheck);
			}
			//replace it, if it still exists.
			if (temp.isAlive())
			    pMan.replaceProjectile(temp);
			else
			    pMan.replaceProjectile(false);
		    }
		    //update the splatters.
		    sMan.update();
		}
		else {
		    loaded = gameLoader.isLoaded();
		    if (loaded) {
			for (int i = 0; i < 10; i++) {
	        	    theZombies[i].spawn();
	    		}
		    }

		}
		repaint();
		try
		{
		    Thread.sleep(60);
		}
		catch (InterruptedException ex) {
		}
	    }
	}
			
	public void update(Graphics g)
	{
	    paint(g);
	}

	public void paint(Graphics g)
	{
	    bBufferG.setColor(Color.black);
	    bBufferG.fillRect(0, 0, 400, 400);
	    if (!loaded) {
		bBufferG.setFont(myFont);
		bBufferG.setColor(Color.white);
		bBufferG.drawString(gameLoader.getStatus(), 50, 100);
	    }
	    else {
		//More if blocks for menus later, blah blah.
		bBufferG.setColor(Color.white);

		//calculate some things.
		int sX = thePlayer.getLookDir() * 60;
		int playX = thePlayer.getX();
		int playY = thePlayer.getY();

		//draw the section of the map we are on.
		bBufferG.drawImage(mapPicture, 0, 0, 400, 400, playX - 200, playY - 200, playX + 200, playY + 200, this);
		
		//if we have a destination, show where it is on the map
		if (thePlayer.isAtDest() == false) {
		    int destPicX = thePlayer.getXDest() - (playX - 200);
		    int destPicY = thePlayer.getYDest() - (playY - 200);
		    bBufferG.drawImage(destImage, destPicX - 15, destPicY - 15, destPicX + 15, destPicY + 15, 0, 0, 30, 30, this);
		}
		
		//draw bullets
		for (int i = 0; i < pMan.getLength(); i++) {
		    Projectile temp = pMan.getProj(i);
		    int projX = temp.getX() - (thePlayer.getX() - 200);
		    int projY = temp.getY() - (thePlayer.getY() - 200);
		    if ((projX >= 0) && (projX < 400) && (projY >= 0) && (projY < 400)) {
			bBufferG.drawImage(bulletImage, projX - 5, projY - 5, projX + 5, projY + 5, 0, 0, 10, 10, this);
		    }
		}
		
		//draw character
		bBufferG.drawImage(heroImage, 170, 170, 230, 230, sX, 0, sX + 60, 60, this);
		
		    
		//draw the zombies
		for (int i = 0; i < 10; i++) {
		    int zombX = theZombies[i].getX() - (thePlayer.getX() - 200);
		    int zombY = theZombies[i].getY() - (thePlayer.getY() - 200);
		    int dirX = theZombies[i].getDir() * 80;
		    if ((zombX >= 0) && (zombX < 400) && (zombY >= 0) && (zombY < 400) && (theZombies[i].isAlive())) {
		        bBufferG.drawImage(zombieImage, zombX - 40, zombY - 40, zombX + 40, zombY + 40, dirX, 0, dirX + 80, 80, this);

		    }
		}

		//draw blood
		for (int i = 0; i < sMan.getLength(); i++) {
		    Splatter drawSplat = sMan.getSplatter(i);
		    int splatX = drawSplat.getX() - (thePlayer.getX() - 200);
		    int splatY = drawSplat.getY() - (thePlayer.getY() - 200);
		    if ((splatX >= 0) && (splatX < 400) && (splatY >= 0) && (splatY < 400) && (drawSplat.isComplete() == false)) {
			for (int b = 0; b < drawSplat.getSize(); b++) {
			    if (drawSplat.isSplatAlive(b)) {
			        int drawX = splatX + drawSplat.getSplatX(b);
 			        int drawY = splatY + drawSplat.getSplatY(b);
			        int drawPic = drawSplat.getSplatPic(b) * 20;
 			        bBufferG.drawImage(bloodImage, drawX - 10, drawY - 10, drawX + 10, drawY + 10, drawPic, 0, drawPic + 20, 20, this);
			    }
			}
		    }
		}

	        
		//draw shadow effect
		bBufferG.drawImage(shadowImage, 0, 0, this);

		//draw life
		bBufferG.setColor(Color.red);
		bBufferG.drawRoundRect(150, 370, 100, 20, 8, 8);
		bBufferG.fillRoundRect(150, 370, thePlayer.getLife(), 20, 8, 8);
	    }
	    g.drawImage(backBuffer, 0, 0, this);
	}

    //LoadThread - inner thread doing the image and data loading for applet.
    class LoadThread extends Thread {
    	boolean done;

	String status = "Loading images.";
	public LoadThread () {
	    done = false;
	}
	
	public void run ()
    	{

	    //source images
	    heroLoad = getImage(getDocumentBase(), "playerImage2.jpg"); 
	    zombieLoad = getImage(getDocumentBase(), "zombieImage2.jpg");
	    mapCollision = getImage(getDocumentBase(), "collisionMap.jpg");
	    mapPicture = getImage(getDocumentBase(), "pictureMap.jpg");
	    shadowLoad = getImage(getDocumentBase(), "lighting.jpg");
	    bulletLoad = getImage(getDocumentBase(), "bullet.gif");
	    destLoad = getImage(getDocumentBase(), "destPic.gif");
	    bloodLoad = getImage(getDocumentBase(), "blood.gif");
	    zombieCol = getImage(getDocumentBase(), "zombieCol.bmp");
	    
	    
	    theTracker.addImage(heroLoad, 0);
	    theTracker.addImage(zombieLoad, 0);
	    theTracker.addImage(mapCollision, 0);
	    theTracker.addImage(mapPicture, 0);
	    theTracker.addImage(shadowLoad, 0);
	    theTracker.addImage(bulletLoad, 0);
	    theTracker.addImage(destLoad, 0);
	    theTracker.addImage(bloodLoad, 0);
	    theTracker.addImage(zombieCol, 0);
	    try
            {
                theTracker.waitForID(0);
            }
            catch (Exception e)
            {
            }
        
            //transparencies
            ImageProducer heroProd = heroLoad.getSource();
	    ImageProducer zombieProd = zombieLoad.getSource();
	    ImageProducer shadowProd = shadowLoad.getSource();
	    ImageProducer bulletProd = bulletLoad.getSource();
	    ImageProducer destProd = destLoad.getSource();
	    ImageProducer bloodProd = bloodLoad.getSource();

	    TransparencyFilter transFilt = new TransparencyFilter();
	    ShadowFilter shadFilt = new ShadowFilter();
	    BloodFilter destFilt = new BloodFilter();
	    BloodFilter bloodFilt = new BloodFilter();
	    
	    heroImage = createImage(new FilteredImageSource(heroProd, transFilt));
	    zombieImage = createImage(new FilteredImageSource(zombieProd, transFilt));
	    bulletImage = createImage(new FilteredImageSource(bulletProd, transFilt));

	    shadowImage = createImage(new FilteredImageSource(shadowProd, shadFilt));
	    destImage = createImage(new FilteredImageSource(destProd, destFilt));
	    bloodImage = createImage(new FilteredImageSource(bloodProd, bloodFilt));

	    theTracker.addImage(heroImage, 1);
	    theTracker.addImage(zombieImage, 1);
	    theTracker.addImage(bulletImage, 1);
	    theTracker.addImage(shadowImage, 1);
	    theTracker.addImage(destImage, 1);
	    theTracker.addImage(bloodImage, 1);
	    try
            {
            	theTracker.waitForID(1);
	    }
            catch (Exception e)
            {
	    }
	    
	    //make zombie collision map. (for testing to see if bullets hit)
	    zCollide = new ZombieCollision(zombieCol, 3200, 80);
	    
	    status = "Building map.";
	    spawnZones[0] = new SpawnZone(200, 200, 300, 1200);
	    spawnZones[1] = new SpawnZone(300, 200, 1200, 300);
	    spawnZones[2] = new SpawnZone(900, 300, 1200, 1200);
	    spawnZones[3] = new SpawnZone(600, 400, 900, 800);
	    spawnZones[4] = new SpawnZone(300, 600, 600, 800);

	    gameMap = new CollisionMap(mapCollision, 1400, 1400);
	    for (int i = 0; i < 10; i++) {
	        int spawnNum = (int)Math.floor(i/2);
		theZombies[i] = new Zombie(1400, 1400, spawnZones[spawnNum]);
	    }
	        
	    // load sounds here if necesarry.
	    done = true;
	}
	
	public String getStatus() {
	    return status;
	}

	public boolean isLoaded() {
	    return done;
	}

    }



}
	    

		    
	  



	
