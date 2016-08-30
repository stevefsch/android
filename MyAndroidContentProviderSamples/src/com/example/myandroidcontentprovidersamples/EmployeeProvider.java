package com.example.myandroidcontentprovidersamples;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import com.example.myandroidcontentprovidersamples.Employees.Employee;

public class EmployeeProvider extends ContentProvider{
	private DBHelper dbHelper;
	private static final UriMatcher sUriMatcher;
	private static final int EMPLOYEE = 1;
	private static final int EMPLOYEE_ID = 2;
	
	private static HashMap<String, String> empProjectionMap;
	static{
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		sUriMatcher.addURI(Employees.AUTHORITY, "employee", EMPLOYEE);
		sUriMatcher.addURI(Employees.AUTHORITY, "employee/#", EMPLOYEE_ID);
		
		empProjectionMap = new HashMap<String,String>();
		empProjectionMap.put(Employee._ID, Employee._ID);
		empProjectionMap.put(Employee.NAME, Employee.NAME);
		empProjectionMap.put(Employee.GENDER, Employee.GENDER);
		empProjectionMap.put(Employee.AGE, Employee.AGE);
	}

	public EmployeeProvider() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		dbHelper = new DBHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		SQLiteQueryBuilder qd = new SQLiteQueryBuilder();
		switch(sUriMatcher.match(uri)){
		case EMPLOYEE:
			qd.setTables(DBHelper.EMPLOYEES_TABLE_NAME);
			qd.setProjectionMap(empProjectionMap);
			break;
		case EMPLOYEE_ID:
			qd.setTables(DBHelper.EMPLOYEES_TABLE_NAME);
			qd.setProjectionMap(empProjectionMap);
			qd.appendWhere(Employee._ID+"="+uri.getPathSegments().get(1));
			break;
		default:
			throw new IllegalArgumentException("´íÎóµÄURI" + uri);
		}
		
		String orderBy;
		if (TextUtils.isEmpty(sortOrder)){
			orderBy = Employee.DEFAUTL_SORT_ORDER;
		}
		else
		{
			orderBy = sortOrder;
		}
		
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor c = db.query(DBHelper.EMPLOYEES_TABLE_NAME, projection, selection, selectionArgs, null, null, orderBy);
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		long rowId = db.insert(DBHelper.EMPLOYEES_TABLE_NAME, Employee.NAME, values);
		if (rowId > 0)
		{
			Uri empUri = ContentUris.withAppendedId(Employee.CONTENT_URI, rowId);
			getContext().getContentResolver().notifyChange(empUri, null);
			return empUri;
		}
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int count;
		switch(sUriMatcher.match(uri)){
		case EMPLOYEE:
			count = db.delete(DBHelper.EMPLOYEES_TABLE_NAME, selection, selectionArgs);
			break;
		case EMPLOYEE_ID:
			String noteId = uri.getPathSegments().get(1);
			count  = db.delete(DBHelper.EMPLOYEES_TABLE_NAME, Employee._ID + "=" +noteId+(!TextUtils.isEmpty(selection)?
					" AND ("+selection+')':""), selectionArgs);  //")"?')'
			break;
			
		default:
			throw new IllegalArgumentException("´íÎóµÄURI" + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int count;
		switch(sUriMatcher.match(uri)){
		case EMPLOYEE:
			count = db.update(DBHelper.EMPLOYEES_TABLE_NAME, values, selection, selectionArgs);
			break;
		case EMPLOYEE_ID:
			String noteId = uri.getPathSegments().get(1);
			count = db.update(DBHelper.EMPLOYEES_TABLE_NAME, values, Employee._ID+"="+noteId+
					(!TextUtils.isEmpty(selection)?" AND "+selection+ ')':""), selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("´íÎóµÄURI" + uri);
			
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

}
