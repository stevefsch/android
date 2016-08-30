/**
 * 
 */

/**
 * @author Steve
 *
 */
public class PetDog extends Animal implements Cloneable{
	private String name;
	private String breed;
	private Flea petFlea;

	/**
	 * 
	 */
	public PetDog(String name, String breed) {
		// TODO Auto-generated constructor stub
		super("Dog");
		petFlea = new Flea("Max","circus flea");
		this.name = name;
		this.breed = breed;
	}
	
	public void setName(String name)
	{
		this.name = name;		
	}
	
	public String getNmae()
	{
		return this.name;
	}
	
	public String getBreed()
	{
		return this.breed;
	}
	
	public Flea getFlea()
	{
		return petFlea;
	}
	
	public void sound()
	{
		System.out.println("Woof");
	}
	
	public String toString()
	{
		return super.toString() + "\nIt's " + name + " the " + breed + "\n" + petFlea;
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
		try
		{
			PetDog myPet = new PetDog("Fang","Chihuahua");
			PetDog yourPet = (PetDog)myPet.clone();

			System.out.println("Your pet details:\n" + yourPet);
			System.out.println("\nMy pet details:\n" + myPet);

			yourPet.setName("Grasher");
			yourPet.getFlea().setName("Atlas");
			
			System.out.println("\nYour pet details:\n" + yourPet);
			System.out.println("\nMy pet details:\n" + myPet);
			
			Animal theAnimal = (Animal)myPet;
			System.out.println();
			System.out.println(theAnimal);			
		}
		catch (CloneNotSupportedException e)
		{
			System.out.println(e);
		}

	}

}
