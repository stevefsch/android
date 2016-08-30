import java.io.*;
import java.util.*;

public class TryVector {

	public TryVector() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person[] persons = {new Person("Tom","Li"),new Person("Thomas","Wang"),new Person("Jessie","Liu")};
		Person aPerson;
		Crowd filmCast = new Crowd();	
		
		//for (;;)
		{
			for (int i = 0; i < persons.length; i++)
			{
				aPerson = persons[i];
				
				if (aPerson == null)
					break;
				
				filmCast.add(aPerson);
				System.out.println(filmCast.get(i));								
			}
		}
		
		//filmCast.sort();
		for (int i = 0; i < persons.length; i++)
		{
			System.out.println(filmCast.get(i));
		}
		

	}

}
