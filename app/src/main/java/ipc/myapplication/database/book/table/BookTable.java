package ipc.myapplication.database.book.table;

/**
 * Created by haykc on 11/18/2017.
 */

public class BookTable {
    public static final String TABLE_NAME = "books";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "b_name";
    public static final String COLUMN_DATE = "r_date";
    public static final String COLUMN_COUNTRY_ID = "c_id";

    public static final String CREATE_TABLE_BOOK = "create table books ( " +
            COLUMN_ID+" integer primary key autoincrement ," +
            COLUMN_NAME+" text , "+COLUMN_DATE+" text , "+COLUMN_COUNTRY_ID+ " int );";

    public static final String[] allColumns(){
        return  new String[]{COLUMN_ID,COLUMN_NAME,COLUMN_DATE,COLUMN_COUNTRY_ID};
    }
}
