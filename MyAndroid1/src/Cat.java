	public class Cat extends Animal{
		private String name;
		private String breed;
		
		public Cat(String aName)
		{
			super("Cat");
			name = aName;
			breed = "Unkown";
		}
		
		public Cat(String aName, String aBreed)
		{
			super("Cat");
			name = aName;
			breed = aBreed;
		}
		
		public void sound()
		{
			System.out.println("Miiaooww");
		}
		
/*		
		public String toString()
		{
			return "Name " + name +". Breed "  + breed;
		}
*/
	}
