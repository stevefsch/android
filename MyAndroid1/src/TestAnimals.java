/**
 * 
 */

/**
 * @author Steve
 *
 */
public class TestAnimals {

	/**
	 * 
	 */
	public TestAnimals() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new Animal("Dog"));
		
		Dog aDog = new Dog("Fido","Chihuahua");
		Dog starDog = new Dog("Lassie");
		
		System.out.println(aDog);
		System.out.println(starDog);
		
		Animal theAnimal;
		theAnimal = new Dog("myDog");
		System.out.println(theAnimal);
		
		Animal theAnimal1;
		theAnimal1 = new Cat("cat");
		System.out.println(theAnimal1);

		Animal theAnimal2;
		theAnimal2 = new Duck("Dukc","Unkown.");
		System.out.println(theAnimal2);
		
	}

}
