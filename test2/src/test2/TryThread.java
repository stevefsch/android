package test2;

import java.io.IOException;
import java.io.*;

public class TryThread extends Thread{
	private String firstName;
	private String secondName;
	private long aWhile;
	
	static int count = 1000;

	public TryThread(String firstName,String secondName,long delay) {
		// TODO Auto-generated constructor stub
		this.firstName = firstName;
		this.secondName = secondName;
		aWhile =delay;
		//setDaemon(true);
	}
	
	synchronized public void addCount()
	{
		count ++;
	}
	
	synchronized public void decCount()
	{
		count--;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread first = new TryThread("Hopalong","cassidy",200L);
		Thread second = new TryThread("Marylyn","Monroe",300L);
		Thread third = new TryThread("Slim","Pickens",500L);
		
		System.out.println("Press enter whien you have enough...\n");

		first.setDaemon(true);
		second.setName("second third name");
		second.setDaemon(true);
		
		first.start();
		second.start();
		third.start();
		
		first.interrupt();
		
		System.out.println(second.getName());
		
		try
		{
			System.in.read();
			System.out.println("Enter pressed...\n");
			
		}
		catch (IOException e)
		{
			System.out.println(e);
		}
		
		System.out.println("Ending main()");
		return;

	}

	public void run()
	{
		try
		{
			while (true)
			{
				System.out.println(firstName);
				sleep(aWhile);  //sleep must be called, otherwise the first thread will excute all the time, the second and third will never be excuted
				System.out.println(secondName + "\n");
				addCount();
				System.out.println(count);
				decCount();
			}
		}
		catch(InterruptedException e)
		{
			System.out.println(firstName + secondName + e);
		}
	}
}

