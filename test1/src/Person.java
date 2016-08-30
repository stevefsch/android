import java.io.*;
import java.util.*;

public class Person implements Comparable,Serializable{
	private String firstName;
	private String surname;

	public Person(final String firstName, final String surname) {
		// TODO Auto-generated constructor stub
		this.firstName = firstName;
		this.surname = surname;
	}

	public String toString()
	{
		return firstName + " " + surname;
	}


	public int compareTo(Object person)
	{
		int result = surname.compareTo((Person)person.surname);
		return result == 0 ? firstName.compareTo((Person)person.firstName) : result;
	}
	
	public boolean equals(Object person)
	{
		return CompareTO(person) ==0;
	}
	
	public int hashCode()
	{
		return 7*firstName.hashCode() + 13*surname.hashCode();
	}
	
	public static Person readPerson()
	{
		//StreamTokenizer myStream = new StreamTokenizer(new FileReader(FileDescriptor.out));
		StreamTokenizer in = new StreamTokenizer(new InputSteamReader(System.in));
		System.out.println("Enter first name:");
		//firstName = myStream.nextToken();
		firstName = in.nextToken();
		
	}

}
