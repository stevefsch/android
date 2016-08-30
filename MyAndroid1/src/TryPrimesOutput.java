import java.io.*;
import java.util.zip.*;;

public class TryPrimesOutput {

	public TryPrimesOutput() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String dirName = "c:/test_java";
		String fileName = "test.txt";
		
		File myPrimesDir = new File(dirName);

		long[] primes = new long[200];
		primes[0] = 2;
		primes[1] = 3;
		int count = 2;
		
		long number = 5;
		
	outer:
		for (; count < primes.length-1; number += 2L)
		{
			long limit = (long)Math.ceil(Math.sqrt(number));
			
			for (int i = 1; i < count && primes[i] <= limit; i++)
			{
				if (number % primes[i] == 0)
				{
					primes[count++] =number;
				}
			}
		}
		
		try
		{			
			if (!myPrimesDir.exists())
			{
				myPrimesDir.mkdir();
			}
			else
			{
				if (!myPrimesDir.isDirectory())
				{
					System.err.println(dirName + " is not a directory");
					return;
				}
			}
			
			File primesFile = new File(myPrimesDir, fileName);
			if (!primesFile.exists())
			{
				primesFile.createNewFile();
			}
			
			DataOutputStream primesStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(primesFile)));
			
			for (int i = 0; i < primes.length; i ++)
			{
				primesStream.writeLong(primes[i]);
			}
			
			primesStream.close();
			
			System.out.println("File size is " + primesStream.size());				
		}
		catch(IOException e)
		{
			System.out.println("IOException " + e + " occured!");
		}
		
		try{
			String zipName = "test.zip";
			File myPrimeZip = new File(myPrimesDir,zipName);
			myPrimeZip.createNewFile();
			
			ZipOutputStream myZipFile = new ZipOutputStream(new FileOutputStream(myPrimeZip));
			
			ZipEntry myZipEntry = new ZipEntry(fileName);
			myZipFile.putNextEntry(myZipEntry);
			
			DataOutputStream myFile = new DataOutputStream(new BufferedOutputStream(myZipFile));
			
			for (int i = 0; i < primes.length; i++)
			{
				myFile.writeLong(primes[i]);
			}
				
			myFile.flush();
			myZipFile.closeEntry();
			
			myFile.close();
			System.out.println("File size " + myFile.size());
			System.out.println("Compressed file size " + myZipEntry.getCompressedSize());

			File output = new File(dirName,"test_write.txt");
			output.createNewFile();
			
			if (!output.isFile())
			{
				System.out.println("Create file failed");
			}
			
			BufferedWriter out = new BufferedWriter(new FileWriter(output.getPath(),true));

			String[] sayings = {"This is a test\n\r","Hello\n\r","Ok"};
			
			for (int i = 0; i < sayings.length; i++)
			{
				out.write(sayings[i]);
			}
			
						
			out.flush();
			out.close();
			
			
			
		}
		catch(IOException e)
		{
			System.out.println("IOException " + e + " occured!");
		}

	}

}
