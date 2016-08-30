import java.io.*;
import java.util.Date;

public class TestFiles {

	public TestFiles() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File myDir = new File("c:/test_java");
		File myFile = new File(myDir,"test.txt");
		
		if (myDir.isDirectory() == true)
		{
			System.out.println(myDir + " is a directory");
		}
		
		System.out.println(myFile.toString() + (myFile.exists()?" does ":" does not") + "exist");
		System.out.println(myFile + (myFile.canRead()?" can":" can not")+ " read");
		System.out.println(myFile + (myFile.canWrite()?" can":" can not")+ " write");
		System.out.println(myFile + (myFile.canExecute()?" can":" can not")+ " execute");
		System.out.println(myFile.getPath());
		
		System.out.println(myFile.getParent());
		
		String[] contents = myDir.list();
		
		if (contents != null)
		{
			File file;
			Date date;
			System.out.println("\nThe " + contents.length + " items in the directory " + myDir.getName() + " are:\n");
			
			for (int i = 0; i < contents.length; i++)
			{
				file = new File(contents[i]);
				date = new Date(file.lastModified());
				System.out.println(contents[i]);
				System.out.println(file + " is a " + (file.isDirectory()?"direcoty":"file ") + "last modified " + date);
				System.out.println();
			}
		}
		else
		{
			System.out.println(myDir.getName() + "is not a directory");
		}
	}

}
