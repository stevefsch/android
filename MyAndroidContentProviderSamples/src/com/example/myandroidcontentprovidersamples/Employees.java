package com.example.myandroidcontentprovidersamples;

import android.net.Uri;
import android.provider.BaseColumns;	

public final class Employees {
	public static final String AUTHORITY = "com.example.provider.Employees";
	
	public static final class Employee  implements BaseColumns{
		private Employee(){}
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/employee");
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.example.employees";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.example.emplyees";
		
		public static final String DEFAUTL_SORT_ORDER = "name DESC";
		public static final String NAME = "name";
		public static final String GENDER = "gender";
		public static final String AGE = "age";
				
	}

	public Employees() {
		// TODO Auto-generated constructor stub
	}

}
