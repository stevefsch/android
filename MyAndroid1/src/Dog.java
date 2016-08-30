	public class Dog extends Animal implements Cloneable{
		private String name;
		private String breed;
		
		public Dog(String aName)
		{
			super("Dog");
			name = aName;
			breed = "Unkown";
		}
		
		public Dog(String aName, String aBreed)
		{
			super("Dog");
			name = aName;
			breed = aBreed;
		}
		
		public void sound()
		{
			System.out.println("Woof Woof");
		}
		
		
		public String toString()
		{
			return "Name " + name +". Breed "  + breed;
		}
	}


