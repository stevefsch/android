import java.io.*;
import java.util.*;

public class Crowd {
	private Vector people;

	public Crowd() {
		// TODO Auto-generated constructor stub
		people = new Vector();
	}

	public Crowd(int numPersons)
	{
		people = new Vector[numPersons];
	}
	
	public boolean add(Person someone)
	{
		return people.add(someone);
	}
	
	public Person get(int index)
	{
		return (Person)people.get(index);
	}
	
	public int size()
	{
		return people.size();
	}
	
	public int capacity()
	{
		return people.capacity();
	}
	
	public Iterator iterator()
	{
		return people.iterator();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void sort()
	{
		Collections.sort(people);
	}
}
