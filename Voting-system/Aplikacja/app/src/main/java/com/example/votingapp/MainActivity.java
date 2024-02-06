package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.*;



public class MainActivity extends AppCompatActivity {
    Button submitCode;

    EditText passwordEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            ImportData importData = new ImportData();
            importData.main();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        submitCode = findViewById(R.id.submitCode);

        submitCode.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        passwordEditText = (EditText) findViewById(R.id.editTextTextPassword);
                        String password = passwordEditText.getText().toString();
                    }
                }
        );
    }


    public class ImportData {

        public void main() throws SQLException, ClassNotFoundException {

                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wybory", "root", "");

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM kandydaci");

                // Create a SQLiteOpenHelper to manage the local database
                Context context = getApplicationContext();
                SQLiteOpenHelper helper = new MySQLiteOpenHelper(context);
                SQLiteDatabase db = helper.getWritableDatabase();

                // Import data from the remote database to the local database
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String imie = resultSet.getString("imie");
                    String nazwisko = resultSet.getString("nazwisko");
                    String info = resultSet.getString("info");

                    // Insert the data into the local database
                    ContentValues values = new ContentValues();
                    values.put("id", id);
                    values.put("imie", imie);
                    values.put("nazwisko", nazwisko);
                    values.put("info", info);
                    db.insert("kandydaci", null, values);
                    Log.d("Tag", "Działa");
                }

                // Close the connections
                resultSet.close();
                statement.close();
                connection.close();
                db.close();

        }
    }



    public class MySQLiteOpenHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "my_db.sqlite";
        private static final int DATABASE_VERSION = 1;

        public MySQLiteOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            // Create the table to store the imported data
            db.execSQL("CREATE TABLE imported_data (id INTEGER PRIMARY KEY AUTOINCREMENT, data TEXT NOT NULL)");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Implement upgrade logic here
        }
    }
}