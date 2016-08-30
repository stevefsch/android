//import MagicHat.Rabbit;


public class MagicHat1 {

	private String hatName;
	private Rabbit rabbits[];

		
	static class Rabbit
	{
		static private String[] rabbitNames = {"Floppsy","Moppsy","Gnasher","Thumper"};
		static private int[] rabbitNamesCount = new int[rabbitNames.length];
		private String name;
		
		public Rabbit()
		{
			int index = (int) (rabbitNames.length*Math.random());
			name = rabbitNames[index] + (++rabbitNamesCount[index]);
		}
		
		public String toString()
		{
			return name;
		}
	}

	/**
	 * 
	 */
	public MagicHat1() {
		// TODO Auto-generated constructor stub
	}
	
	public MagicHat1(final String hatName)
	{
		this.hatName = hatName;
		rabbits = new Rabbit[(int)(4*Math.random())];
		
		for (int i = 0; i < rabbits.length; i++)
		{
			rabbits[i] = new Rabbit();
		}
	}
	
	public String toString()
	{
		String hatString = '\n' + this.hatName + " contains:\n";
		
		for (int i = 0; i < rabbits.length; i++)
		{
			hatString += "\t" + rabbits[i] + " ";
		}
		
		return hatString;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new MagicHat1("Gray Topper"));
		System.out.println(new MagicHat1("Black Topper"));
		System.out.println(new MagicHat1("Baseball Cap"));

	}

}
