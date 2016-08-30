
public class JoinStrings {

	public JoinStrings() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String firstString = "many ";
		String secondString = "hands ";
		String thirdString = "make light work";
		
		String myString;
		
		myString = firstString + secondString + thirdString;
		System.out.println(myString);
		
		int numHands = 99;
		myString = numHands + " " + secondString + thirdString;
		System.out.println(myString);
		
		myString = "fifty five is " + 5 + 5;
		System.out.println(myString);
		
		myString = 5 + 5 + " is ten";
		System.out.println(myString);

	}

}
