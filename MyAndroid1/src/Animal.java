/**
 * 
 */

/**
 * @author Steve
 *
 */
//public class Animal {
public abstract class Animal implements Cloneable {
	private String type;

	public abstract void sound(); 
	/**
	 * 
	 */
	public Animal() {
		// TODO Auto-generated constructor stub
	}
	
	public Animal(String aType){
		type = new String(aType);
	}
	
/*	
	public void sound()
	{
		
	}
*/	
	public String toString(){
		return "This is a " + type;
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub		

	}
}
