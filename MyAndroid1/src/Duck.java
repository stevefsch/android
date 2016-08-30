
public class Duck extends Animal{
	private String name;
	private String breed;
	
	public Duck(String aName)
	{
		super("Duck");
		name = aName;
		breed = "Unkown";
	}
	
	public Duck(String aName, String aBreed)
	{
		super("Duck");
		name = aName;
		breed = aBreed;
	}
	
	public void sound()
	{
		System.out.println("Gua Gua");
	}
	
	
	public String toString()
	{
		return "Name " + name +". Breed "  + breed;
	}
}
