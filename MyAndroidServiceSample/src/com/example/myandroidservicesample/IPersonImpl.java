package com.example.myandroidservicesample;
import android.os.RemoteException;

public class IPersonImpl extends IPerson.Stub{
	
	private int age;
	private String name;

	public IPersonImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setAge(int age) throws RemoteException {
		// TODO Auto-generated method stub
		this.age = age;
	}

	@Override
	public void setName(String name) throws RemoteException {
		// TODO Auto-generated method stub
		this.name = name;
	}

	@Override
	public String display() throws RemoteException {
		// TODO Auto-generated method stub
		return "name:" + name + "; age:" + age;
	}

}
