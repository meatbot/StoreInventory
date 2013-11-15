import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "inventory.db";
	private static final String TABLE_INVENTORY = "inventory";
	
	// Database creation sql statement
	private static final String DATABASE_CREATE =
		"create table " + TABLE_INVENTORY + 
		"(_id integer primary key autoincrement, " +
	     "itemNum integer," +
		 "catNum text, " + 
	     "description text, " +
		 "per text, " +
	     "listPrice real, " +
		 "oh real, " + 
	     "or real, " +
		 "oo real, " +
	     "altCodes text" +
		 ");";
	private static final int DATABASE_VERSION = 1;
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
		//populateDatabase();
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		Log.w(DatabaseHelper.class.getName(), 
			  "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy old data");
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY);
	}
}
