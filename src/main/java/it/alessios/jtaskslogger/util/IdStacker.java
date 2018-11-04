/**
 * 
 */
package it.alessios.jtaskslogger.util;

/**
 * @author alessio
 *
 */
public class IdStacker {
	
	private static int id = 0;
	
	private static IdStacker stacker = null;
	private IdStacker() {
		
	}
	
	public static IdStacker getInstance() {
		if(stacker == null) {
			stacker = new IdStacker();
		}
		return stacker;
	}
	
	public int getId() {
		int currentId = id;
		id++;
		return currentId;
	}
}
