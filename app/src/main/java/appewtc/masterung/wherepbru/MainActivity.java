package appewtc.masterung.wherepbru;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private MyManage myManage;
    private String urlJSON = "http://swiftcodingthai.com/pbru/get_room.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myManage = new MyManage(this);

        //myManage.addRoom("build", "room", "lat", "lng", "icon");

        deleteAllSQLite();

    }   // Main Method

    public class SynRoom extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {

            try {

            } catch (Exception e) {
                return null;
            }

            return null;
        }   // doInBack

    }   // SynRoom Class


    private void deleteAllSQLite() {

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        sqLiteDatabase.delete(MyManage.room_table, null, null);

    }


}   // Main Class
