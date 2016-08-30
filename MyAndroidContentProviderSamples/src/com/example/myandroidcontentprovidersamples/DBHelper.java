package com.example.myandroidcontentprovidersamples;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.myandroidcontentprovidersamples.Employees.Employee;

public class DBHelper extends SQLiteOpenHelper{
	private static final String DATABASE_NAME ="Employees.db";
	private static final int DATABASE_VERSION=1;
	public static final String EMPLOYEES_TABLE_NAME ="employee";

	public DBHelper(Context context) {
		// TODO Auto-generated constructor stub
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE " + EMPLOYEES_TABLE_NAME + " (" 
				+Employee._ID + " INTEGER PRIMARY KEY,"
				+Employee.NAME+ " TEXT,"
				+Employee.GENDER + " TEXT,"
				+Employee.AGE + " INTEGER"
				+");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXSITS employee");
		onCreate(db);
	}

}
