
public class MatchStrings {

	public MatchStrings() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String string1 = "Too many ";
		String string2 = "cooks";
		String string3 = "Too many cooks";
		
		string1 += string2;
		
		System.out.println("Test 1");
		System.out.println("string3 now is "+ string3);
		System.out.println("string1 now is " + string1);

		if (string1 == string3)
		{
			System.out.println("string1 == string3 is true. " + "string 1 and string3 point to the same string");
		}
		else
		{
			System.out.println("string1 and string3 do not point to the same tring");
		}
		
		string3 = string1;

		System.out.println("Test 1");
		System.out.println("string3 now is "+ string3);
		System.out.println("string1 now is " + string1);

		if (string1 == string3)
		{
			System.out.println("string1 == string3 is true. " + "string 1 and string3 point to the same string");
		}
		else
		{
			System.out.println("string1 and string3 do not point to the same tring");
		}

		if (string3.equals(string3))
		{
			System.out.println("string3.equal(string1) is true." + "so the string3 and string1 are equal.");
		}
		else
		{
			System.out.println("string3.equal(string1) is false." + "so the string3 and string1 are not equal.");
		}

		if (string1.equals(string3))
		{
			System.out.println("string1.equal(string3) is true." + "so the string3 and string1 are equal.");
		}
		else
		{
			System.out.println("string1.equal(string3) is false." + "so the string3 and string1 are not equal.");
		}
	
	}

}
