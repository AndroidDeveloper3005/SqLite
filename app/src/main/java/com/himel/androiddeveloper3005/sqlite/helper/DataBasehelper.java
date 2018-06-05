package com.himel.androiddeveloper3005.sqlite.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.himel.androiddeveloper3005.sqlite.Model.Employee;

import java.util.ArrayList;

public class DataBasehelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "task_management";
    public static final int DB_VERSION =1;

    //table info
    public static final String EMPLOYEE_TABLE = "employee";
    public static final String ID_FIELD = "_id";
    public static final String NAME_FIELD = "name";
    public static final String EMAIL_FIELD = "email";
    public static final String PHONE_FIELD = "phone";
    public static final String AGE_FIELD = "age";
    public static final String IMAGE_FIELD = "image";

    //create table
    public static final String EMPLOYEE_TABLE_SQL = "CREATE TABLE "
            + EMPLOYEE_TABLE + " (" + ID_FIELD + " INTEGER PRIMARY KEY, "
            + NAME_FIELD + " TEXT, " + EMAIL_FIELD + " TEXT, " + PHONE_FIELD
            + " TEXT, " + AGE_FIELD + " TEXT, " + IMAGE_FIELD + " blob)";

    public DataBasehelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EMPLOYEE_TABLE_SQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertQuery(Employee employee){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_FIELD,employee.getName());
        values.put(EMAIL_FIELD,employee.getEmail());
        values.put(PHONE_FIELD,employee.getPhone());
        values.put(AGE_FIELD,employee.getAge());
        values.put(IMAGE_FIELD,employee.getImage());

        long inserted = db.insert(EMPLOYEE_TABLE, null, values);

        db.close();
        return inserted;
    }

    // query
    public ArrayList<Employee> getAllEmployees() {
        ArrayList<Employee> allEmployees = new ArrayList<Employee>();
        SQLiteDatabase db = this.getReadableDatabase();

        // String[] columns={NAME_FIELD, EMAIL_FIELD, PHONE_FIELD};
        // SELECT * FROM EMPLOYEE;
        Cursor cursor = db.query(EMPLOYEE_TABLE, null, null, null, null, null,
                AGE_FIELD);

        // Cursor cursor = db.rawQuery("SELECT * FROM EMPLOYEE", null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                //
                int id = cursor.getInt(cursor.getColumnIndex(ID_FIELD));
                String name = cursor.getString(cursor
                        .getColumnIndex(NAME_FIELD));
                String age = cursor.getString(cursor
                        .getColumnIndex(AGE_FIELD));
                String email = cursor.getString(cursor
                        .getColumnIndex(EMAIL_FIELD));
                String phone = cursor.getString(cursor
                        .getColumnIndex(PHONE_FIELD));
                byte[] image=cursor.getBlob(cursor.getColumnIndex(IMAGE_FIELD));
                Employee e = new Employee(id, name,age, email, phone,image);
                allEmployees.add(e);
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();

        return allEmployees;
    }


}
