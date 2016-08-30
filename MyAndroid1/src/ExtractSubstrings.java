
public class ExtractSubstrings {

	public ExtractSubstrings() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text = "To be or not to be";
		int count = 0;
		char seperator = ' ';
		
		int index = 0;
		
		do 
		{
			++count;
			++index;
			index = text.indexOf(seperator, index);
		}while (index != -1);
		
		String[]  subStr = new String[count];
		index = 0;
		int endIndex = 0;
		for (int i = 0; i < count; i++)
		{
			endIndex = text.indexOf(seperator, index);
			
			if (endIndex == -1)
			{
				subStr[i] = text.substring(index);
			}
			else
			{
				subStr[i] = text.substring(index,endIndex);
			}
			
			index = endIndex +1;
		}
		
		for (int i = 0; i < subStr.length; i++)
		{
			System.out.println(subStr[i]);
		}

	}

}
