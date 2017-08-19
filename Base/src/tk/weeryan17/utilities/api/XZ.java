package tk.weeryan17.utilities.api;

import org.bukkit.World;
/**
 * Represents a Location but without the y.
 * So 2-dimensional instead of 3-dimensional.
 * 
 * @author weeryan17
 *
 */
public class XZ {
	World world;
	int x;
	int z;
	/**
	 * Used to create a new position.
	 * 
	 * @param world The world
	 * @param x the x cord of the position.
	 * @param z the z cord of the position.
	 */
	public XZ(World world, int x, int z){
		this.world = world;
		this.x = x;
		this.z = z;
	}
	
	/**
	 * Get's the x value involved it this position.
	 * 
	 * @return the x cord.
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Get's the z value involved it this position.
	 * 
	 * @return the z cord.
	 */
	public int getZ(){
		return z;
	}
	
	/**
	 * Get's the world value involved it this position.
	 * 
	 * @return the world this position is in.
	 */
	public World getWorld(){
		return this.world;
	}
}
