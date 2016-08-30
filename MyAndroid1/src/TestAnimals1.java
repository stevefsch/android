/**
 * 
 */

/**
 * @author Steve
 *
 */

//import java.util.Random

public class TestAnimals1 {

	/**
	 * 
	 */
	public TestAnimals1() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Animal theAnimals[] = {
				new Dog("Rover","Poodle"),
				new Cat("Max","Abvssinian"),
				new Duck("Daffy","Aylesbury"),
				new Spaniel("Spaniel")
		};
		
		Animal petChoice;
		
		for (int i = 0; i < 5; i++)
		{
			petChoice = theAnimals[(int)(theAnimals.length*Math.random())];
			System.out.println(petChoice);
			petChoice.sound();
			
			Class objectType = petChoice.getClass();
			System.out.println(objectType.getName());
			
			System.out.println();
		}		
	}
}
