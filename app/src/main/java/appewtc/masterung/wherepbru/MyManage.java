package appewtc.masterung.wherepbru;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by masterUNG on 5/27/16 AD.
 */
public class MyManage {

    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public static final String room_table = "roomTABLE";
    public static final String column_id = "_id";
    public static final String column_Build = "Build";
    public static final String column_Room = "Room";
    public static final String column_Lat = "Lat";
    public static final String column_Lng = "Lng";
    public static final String column_Icon = "Icon";

    public MyManage(Context context) {

        myOpenHelper = new MyOpenHelper(context);
        sqLiteDatabase = myOpenHelper.getWritableDatabase();

    } //Constructor

    public long addRoom(String strBuild,
                        String strRoom,
                        String strLat,
                        String strLng,
                        String strIcon) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(column_Build, strBuild);
        contentValues.put(column_Room, strRoom);
        contentValues.put(column_Lat, strLat);
        contentValues.put(column_Lng, strLng);
        contentValues.put(column_Icon, strIcon);

        return sqLiteDatabase.insert(room_table, null, contentValues);
    }

}   // Main Class
