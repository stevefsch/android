/**
 * 
 */

/**
 * @author Steve
 *
 */
public class Flea extends Animal implements Cloneable{
	
	private String name;
	private String species;

	/**
	 * 
	 */
	public Flea(String aName, String aSpecies) {
		// TODO Auto-generated constructor stub
		super("Flea");
		name = aName;
		species = aSpecies;
	}
	
	public void sound()
	{
		System.out.println("psst");
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getSpecies()
	{
		return species;
	}
	
	public String toString()
	{
		return super.toString() + "\nIt's " + name + " the" + species;
	}
	
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
