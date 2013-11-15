package com.example.storeinventory;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class InventoryDataSource {
	
	private SQLiteDatabase database;
	private InventoryDatabaseHelper inventoryHelper;
	
	private String[] allColumns = {
			InventoryDatabaseHelper.COLUMN_ID,
			InventoryDatabaseHelper.COLUMN_ITEMNUM,
			InventoryDatabaseHelper.COLUMN_CATNUM,
			InventoryDatabaseHelper.COLUMN_DESCRIPTION, 
			InventoryDatabaseHelper.COLUMN_PER,
			InventoryDatabaseHelper.COLUMN_LISTPRICE, 
			InventoryDatabaseHelper.COLUMN_OH,
			InventoryDatabaseHelper.COLUMN_OR,
			InventoryDatabaseHelper.COLUMN_OO,
			InventoryDatabaseHelper.COLUMN_OO,
			InventoryDatabaseHelper.COLUMN_ALTCODES
	};
	
	private String[] queryColumns = {
			InventoryDatabaseHelper.COLUMN_ITEMNUM,
			InventoryDatabaseHelper.COLUMN_CATNUM,
			InventoryDatabaseHelper.COLUMN_DESCRIPTION, 
	};
	
	public InventoryDataSource(Context context) {
		inventoryHelper = new InventoryDatabaseHelper(context);
	}
	
	public void open() throws SQLException {
		 database = inventoryHelper.getReadableDatabase();
	}
	
	public void close() {
		inventoryHelper.close();
	}
	
	public List<String[]> getItemsMatchingPartialItemNum(String itemNumVar) {
		List<String[]> foundItems = new ArrayList<String[]>();
		
		Cursor cursor = database.query(
				InventoryDatabaseHelper.TABLE_INVENTORY,
				queryColumns,
				"cast(itemnum as text) like '?%'",
				new String[]{ itemNumVar },
				null, null, null, "30"
		);
		
		//Iterate through, add item to list
		cursor.moveToFirst();
		do {
			//Get fields from current row.
			int itemNum = cursor.getInt(1);
			String strItemNum = String.valueOf(itemNum);
			String catNum = cursor.getString(2);
			String description = cursor.getString(3);
			
			String[] item = { strItemNum, catNum, description };			
			foundItems.add(item);
		} while (cursor.moveToNext());
		cursor.close();
				
		return foundItems;
	}
}
