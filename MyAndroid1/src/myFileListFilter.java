import java.io.*;
import java.util.*;

public class myFileListFilter implements FileNameFilter{

	private String name;
	private String extension;

	public myFileListFilter(String name, String extension) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.extension = extension;
	}
	
	public boolean accept(File directory, String filename)
	{
		boolean fileOk = true;
		
		if (name != null)
		{
			fileOk &= filename.startsWith(name);
		}
		
		if (extension != null)
		{
			fileOk &= filename.endsWith("." + extension);
		}
		
		return fileOk;
	}	
	
	public String toString()
	{
		return name + "." + extension;
	}
	
	public static void main(String[] args)
	{
		myFileListFilter filter = new myFileListFilter("test","txt");

		File myDir = new File("c:/test_java");
	
		if (myDir.isDirectory() == true)
		{
			System.out.println(myDir + " is a directory");
		}
		else
			return;
		
		String[] contents = myDir.list();
		
		if (contents != null)
		{
			File file;
			
			System.out.println("Find " + filter + "in direcotry" + myDir + ":\n");
			for (int i = 0; i < contents.length; i++)
			{
				if (filter.accept(myDir, contents[i]))
				{
					System.out.println(contents[i] + " find");
				}
			}
		}
		else
		{
			System.out.println(myDir.getName() + "is not a directory");
		}		
	}
}