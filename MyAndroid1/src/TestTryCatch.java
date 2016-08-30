
public class TestTryCatch {

	public TestTryCatch() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i = 1;
		int j = 0;
		
		try{
			System.out.println("Try block entered " + "i = " + i + " j = " + j);
			System.out.println(i/j);
			System.out.println("Ending try block");
			
		}
		catch(ArithmeticException e)
		{
			System.out.println(e);
			System.out.println("Arithmetica exception caught");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			System.out.println("here");
		}
		
		System.out.println("After try block");
		
		return;

	}

}
