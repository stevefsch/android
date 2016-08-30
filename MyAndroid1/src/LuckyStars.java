
public class LuckyStars {

	public LuckyStars() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] stars = {
				"Robert Redford", "Marilyn Monroe",
				"Boris karloff", "Lassie",
				"Hopalong Cassidy","Trigger"
		};
		
		System.out.println("Your lucky star for today is " + stars[(int) (stars.length*Math.random())]);

	}

}
