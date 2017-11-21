package ipc.myapplication.database.country.table;

/**
 * Created by haykc on 11/21/2017.
 */

public class CountryTable {
    public static final String TABLE_NAME = "countries";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "c_name";

    public static final String CREATE_TABLE_COUNTRY= "create table countries ( " +
            COLUMN_ID+" integer primary key autoincrement ," +
            COLUMN_NAME+" text );";

    public static final String[] allColumns(){
        return  new String[]{COLUMN_ID,COLUMN_NAME};
    }

}
