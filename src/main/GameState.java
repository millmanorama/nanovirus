package main;
import java.util.ArrayList;

public class GameState {
	
	private boolean	paused;			// Is the game paused?
	private int		x;				// The the x- and y-coordinate of the
	private int		y;				// 		nanovirus within the current tunnel image.
	private String	transition;		// The current transition of the nanovirus ["up", "down", or else].
	private int		speed;			// The speed of the nanovirus.
	private int		yoff;			// The y-offset of the next tunnel image.
	
	private ArrayList<Enemy>	enemiesCurrent;	// The enemies in the current tunnel.
	private ArrayList<Enemy>	enemiesNext;	// The enemies in the next tunnel.
	
	// The main GameState constructor.
	public GameState(boolean paused, int x, String transition, int y,
			int speed, int yoff) {
		this.paused = paused;
		this.x = x;
		this.y = y;
		this.transition = transition;
		this.speed = speed;
		this.yoff = yoff;
		this.enemiesCurrent = new ArrayList<Enemy>();
		this.enemiesNext = new ArrayList<Enemy>();
	}
	
	// ** MEMBER VARIABLE ACCESS METHODS **/
	public boolean isPaused() {
		return paused;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public String getTransition() {
		return transition;
	}
	public int getSpeed() {
		return speed;
	}
	public int getYoff() {
		return yoff;
	}
	public ArrayList<Enemy> getCurrentEnemies() {
		return enemiesCurrent;
	}
	public ArrayList<Enemy> getNextEnemies() {
		return enemiesNext;
	}
	
	//** MEMBER VARIABLE MODIFICATION METHODS **/
	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setTransition(String transition) {
		this.transition = transition;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public void setYoff(int yoff) {
		this.yoff = yoff;
	}
	public void setCurrentEnemies(ArrayList<Enemy> currentEnemies) {
		this.enemiesCurrent = currentEnemies;
	}
	public void setNextEnemies(ArrayList<Enemy> nextEnemies) {
		this.enemiesNext = nextEnemies;
	}
	
	/**
	 * @param nanoVirusMain
	 * 
	 * 		updateBG: 	Updates the background, effectively "moving"
	 * 					the nanovirus around the game world.
	 */
	void updateBG(NanoVirusMain nanoVirusMain) {
		
		// The nanovirus moves slower before transition points ...
		if (getX() < 150)
			setX((int) (getX() + (150 + 5 - getX()) / 100.0 * getSpeed()));
		// ... otherwise the nanovirus moves at normal speed.
		else {
			setX(getX() + getSpeed());
			nanoVirusMain.bgx += nanoVirusMain.bgSpeed;
		}
		
		// If the nanovirus has passed out of the current tunnel and
		// entered a new one, reset the nanovirus x-coordinate for
		// the new tunnel and reset the transition and sound trigger.
		if (getX() >= 800) {
			setX(getX() - 800);
			setTransition("");
			nanoVirusMain.played = false;
		}
		// 
		if (nanoVirusMain.bgx >= 800)
			nanoVirusMain.bgx = nanoVirusMain.bgx - 800;
		
		// If the nanovirus is in the reaction area and the
		// heartbeat sound has not played yet, play the sound.
		if (150 < getX() && getX() < 350 && !nanoVirusMain.played) {
			nanoVirusMain.played = true;
			nanoVirusMain.heartbeat.play();
		}
		
		// If the nanovirus is transitioning up, adjust its y-coordinate
		// so that it will reach the upper tunnel by x == 600.
		if (getTransition().equals("up")) {
			
			setY(getY() + (0 - getY()) / (600 - getX()) * getSpeed());
			
		// If the nanovirus is transitioning down, adjust its y-coordinate
		// so that it will reach the lower tunnel by x == 600.
		} else if (getTransition().equals("down")) {
			
			setY(getY() - (getY() - 360) / (600 - getX()) * getSpeed());
			
		// If the nanovirus is staying in the middle tunnel,
		// reset its y-coordinate and y-offset.
		} else {
			
			setY(180);
			setYoff(0);
		}
	}
	
	/**
	 * @param nanoVirusMain
	 * 
	 * 		updateEnemies: 	Updates the enemies of the game world.
	 */
	void updateEnemies(NanoVirusMain nanoVirusMain) {
		
		// 
		
	}
	
	/**
	 * 		generateEnemySet: 	Generates a new set of enemies based
	 * 							on the current GameState.
	 */
	private ArrayList<Enemy> generateEnemySet() {
		
		// Set up a new ArrayList of Enemies
		ArrayList<Enemy> newEnemySet = new ArrayList<Enemy>();
		
		return newEnemySet;
	}
}