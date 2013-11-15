import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class InventoryDatabaseHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "inventory.db";
	public static final String TABLE_INVENTORY = "inventory";
	
	//Columns
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_ITEMNUM = "itemnum";
	public static final String COLUMN_CATNUM = "catnum";
	public static final String COLUMN_DESCRIPTION = "description";
	public static final String COLUMN_PER = "per";
	public static final String COLUMN_LISTPRICE = "listprice";
	public static final String COLUMN_OH = "oh";
	public static final String COLUMN_OR = "or";
	public static final String COLUMN_OO = "oo";
	public static final String COLUMN_ALTCODES = "altcodes";
		
	// Database creation sql statement
	private static final String DATABASE_CREATE =
		"create table " + TABLE_INVENTORY + 
		"(_id integer primary key autoincrement, " +
	     COLUMN_ITEMNUM + " integer," +
		 COLUMN_CATNUM + " text, " + 
	     COLUMN_DESCRIPTION + " text, " +
		 COLUMN_PER + " text, " +
	     COLUMN_LISTPRICE + " real, " +
		 COLUMN_OH + " real, " + 
	     COLUMN_OR + " real, " +
		 COLUMN_OO + " real, " +
	     COLUMN_ALTCODES + " text" +
		 ");";
	private static final int DATABASE_VERSION = 1;
	
	public InventoryDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		
		database.execSQL(DATABASE_CREATE);
		//populateDatabase();
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		Log.w(InventoryDatabaseHelper.class.getName(), 
			  "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy old data");
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY);
		onCreate(database);
	}
}
