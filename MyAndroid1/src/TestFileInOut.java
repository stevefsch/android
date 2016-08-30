import java.io.*;
import java.util.zip.*;

public class TestFileInOut {
	public TestFileInOut() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String dir = "c:/test_java";
		String file = "test.txt";
		String zipFile = "test.zip";
		
		try{
			File myPrimes = new File(dir,file);
			DataInputStream primesIn = new DataInputStream(new FileInputStream(myPrimes));
			
			long[] primes = new long[6];
			boolean EOF = false;
			
			int index = 0;
			
			while(!EOF)
			{
				try 
				{
					for (index = 0; index < primes.length; index++)
					{
						primes[index] = primesIn.readLong();
					}
				}
				catch (EOFException e)
				{
					System.err.println(e);
					EOF = true;
				}
			}
			
			for (int j = 0; j < index; j++)
			{
				System.out.println(primes[j]);
			}
			
			primesIn.close();
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
				
		try
		{
			File myPrimeZip = new File(dir,zipFile);
			ZipInputStream myZipFile = new ZipInputStream(new FileInputStream(myPrimeZip));
			DataInputStream primesIn = new DataInputStream(new BufferedInputStream(myZipFile));
			ZipEntry myZipEntry = myZipFile.getNextEntry();
			
			System.out.println("Compressed file is " + myZipEntry.getName());
			
			long[] primes = new long[6];
			boolean EOF = false;
			
			int index = 0;
			
			while(!EOF)
			{
				try 
				{
					for (index = 0; index < primes.length; index++)
					{
						primes[index] = primesIn.readLong();
					}
				}
				catch (EOFException e)
				{
					System.err.println(e);
					EOF = true;
				}
			}
			
			for (int j = 0; j < index; j++)
			{
				System.out.println(primes[j]);
			}
			
			primesIn.close();
			
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
