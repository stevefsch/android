import java.io.*;
import java.util.*;

public class ReadCharacters {

	public ReadCharacters() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			String dirName = "c:/test_java";
			String fileName = "test.txt";
			
			File input = new File(dirName,fileName);
			PushbackReader in = new PushbackReader(new BufferedReader(new FileReader(input)));
			
			int c;
			while(true)
			{
				String number = "";
				while (Character.isDigit((char)(c = in.read())))
				{
					number += (char)c;
				}
				
				if (c == -1)
				{
					break;
				}
				else
				{
					in.unread(c);
				}
				
				try
				{
					char[] proverb = new char[2000];
					in.read(proverb);
					System.out.println(proverb);
				}
				catch (IOException e)
				{
					System.err.println(e);
				}
			}
		}
		catch(FileNotFoundException e)
		{
			System.err.println(e);
			return;
		}
		catch (IOException e)
		{
			System.err.println("error reading input file " + e);
			return;
		}		
		

	}

}
