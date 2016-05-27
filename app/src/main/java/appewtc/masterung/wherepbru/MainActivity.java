package appewtc.masterung.wherepbru;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

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

        SynRoom synRoom = new SynRoom();
        synRoom.execute();

    }   // Main Method

    public class SynRoom extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(urlJSON).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                return null;
            }

        }   // doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("whereV1", "JSON ==> " + s);

            try {

                JSONArray jsonArray = new JSONArray(s);

                for (int i=0;i<jsonArray.length();i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String strBuild = jsonObject.getString(MyManage.column_Build);
                    String strRoom = jsonObject.getString(MyManage.column_Room);
                    String strLat = jsonObject.getString(MyManage.column_Lat);
                    String strLng = jsonObject.getString(MyManage.column_Lng);
                    String strIcon = jsonObject.getString(MyManage.column_Icon);

                    myManage.addRoom(strBuild, strRoom, strLat, strLng, strIcon);

                }   // for



            } catch (Exception e) {
                e.printStackTrace();
            }

        }   // onPost

    }   // SynRoom Class


    private void deleteAllSQLite() {

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        sqLiteDatabase.delete(MyManage.room_table, null, null);

    }


}   // Main Class
