package com.anantech.quiz.cricketStadiumQuiz;

/**
 * Created by Sandeep on 18-06-2016.
 */
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QuizHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "mathsone";
    // tasks table name
    private static final String TABLE_QUEST = "quest";
    // tasks Table Columns names
    private static final String KEY_ID = "qid";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer"; // correct option
    private static final String KEY_OPTA = "opta"; // option a
    private static final String KEY_OPTB = "optb"; // option b
    private static final String KEY_OPTC = "optc"; // option c

    private SQLiteDatabase dbase;

    public QuizHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase = db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "
                + KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT)";
        db.execSQL(sql);
        addQuestion();
        // db.close();
    }

    private void addQuestion() {
        Question q1 = new Question("Barabati Stadium is located in which city", "Bhubaneswar", "Cuttack", "Pune", "Cuttack");
        this.addQuestion(q1);
        Question q2 = new Question("Barkatullah Khan Stadium  is located in which city", "Jaipur", "Jodhpur", "Gwalior", "Jodhpur");
        this.addQuestion(q2);
        Question q3 = new Question("Sher-e-Bangla Cricket Stadium  is located in which city", "Dhaka", "Chittagong", "Sylhet", "Dhaka");
        this.addQuestion(q3);
        Question q4 = new Question("Green Park Stadium  is located in which city", "Kanpur", "Lucknow", "Indore", "Kanpur");
        this.addQuestion(q4);
        Question q5 = new Question("Captain Roop Singh Stadium  is located in which city", "Agra", "Gwalior", "Jaipur", "Gwalior");
        this.addQuestion(q5);
        Question q6 = new Question("Khandheri Cricket Stadium is located in which city", "Rajkot", "Ahemdabad", "Surat", "Rajkot");
        this.addQuestion(q6);
        Question q7 = new Question("Chinnaswamy Stadium  is located in which city", "Bangalore", "Chennai", "Hyderabad", "Bangalore");
        this.addQuestion(q7);
        Question q8 = new Question("Chepauk Stadium  is located in which city", "Vijaywada", "Chennai", "Hyderabad", "Chennai");
        this.addQuestion(q8);
        Question q9 = new Question("Rajasekhara Reddy Cricket Stadium  is located in which city", "Vijaywada", "Vizag", "Hyderabad", "Vizag");
        this.addQuestion(q9);
        Question q10 = new Question("Feroz Shah Kotla Cricket Stadium  is located in which city", "Delhi", "Ajmer", "Bhopal", "Delhi");
        this.addQuestion(q10);
        Question q11 = new Question("Eden Garden Cricket Stadium  is located in which city", "Delhi", "Calcutta", "London", "Calcutta");
        this.addQuestion(q11);
        Question q12 = new Question("Lord's Cricket Ground  is located in which city", "Sydney", "Calcutta", "London", "London");
        this.addQuestion(q12);
        Question q13 = new Question("Kingsmead Cricket Ground  is located in which city", "Durban", "Auckland", "Perth", "Durban");
        this.addQuestion(q13);
        Question q14 = new Question("The Gabba  is located in which city", "Brisbane", "Auckland", "Perth", "Brisbane");
        this.addQuestion(q14);
        Question q15 = new Question("Wanderers Stadium  is located in which city", "Johannesburg", "Auckland", "Perth", "Johannesburg");
        this.addQuestion(q15);
        Question q16 = new Question("Old Trafford Stadium  is located in which city", "Manchester", "Auckland", "Perth", "Manchester");
        this.addQuestion(q16);
        Question q17 = new Question("Brabourne Stadium is located in which city", "Mumbai", "Auckland", "Perth", "Mumbai");
        this.addQuestion(q17);
        Question q18 = new Question("DY Patil Stadium is located in which city", "Navi Mumbai", "Mumbai", "Pune", "Navi Mumbai");
        this.addQuestion(q18);
        Question q19 = new Question("Gaddafi Stadium is located in which city", "Lahore", "Multan", "Karachi", "Lahore");
        this.addQuestion(q19);
        Question q20 = new Question("Sawai Mansingh Stadium is located in which city", "Jaipur", "Jodhpur", "Gwalior", "Jaipur");
        this.addQuestion(q20);
        Question q21 = new Question("Sabina Park is located in which city", "Kingston", "Saint Lucia", "Cardiff", "Kingston");
        this.addQuestion(q21);


        Question q22 = new Question("The Oval Cricket Ground is located in which city", "Brisbane", "Cardiff", "London", "London");
        this.addQuestion(q22);
        Question q23 = new Question("Basin Reserve is located in which city", "Sydney", "Wellington", "Perth", "Wellington");
        this.addQuestion(q23);
        Question q24 = new Question("Queen's Park Oval is located in which city", "Port of Spain", "Saint Lucia", "Perth", "Port of Spain");
        this.addQuestion(q24);
        Question q25 = new Question("Kensington Oval is located in which city", "Brisbane", "Bridgetown", "Perth", "Bridgetown");
        this.addQuestion(q25);
        Question q26 = new Question("National Stadium  is located in which city", "Karachi", "Lahore", "Faisalabad", "Karachi");
        this.addQuestion(q26);
        Question q27 = new Question("Vidarbha Cricket Association Ground is located in which city", "Nagpur", "Pune", "Mumbai", "Nagpur");
        this.addQuestion(q27);
        Question q28 = new Question("Western Australian Cricket Association Ground is located in which city", "Perth", "Auckland", "Hobat", "Perth");
        this.addQuestion(q28);
        Question q29 = new Question("Iqbal Stadium is located in which city", "Faisalabad", "Multan", "Lahore", "Faisalabad");
        this.addQuestion(q29);
        Question q30 = new Question("Motera is located in which city", "Ahmedabad", "Multan", "Bangalore", "Ahmedabad");
        this.addQuestion(q30);
        Question q31 = new Question("Premadasa Stadium is located in which city", "Colombo", "Candy", "Bulawayo", "Colombo");
        this.addQuestion(q31);
        Question q32 = new Question("Zahur Ahmed Chowdhury Stadium is located in which city", "Chittagong", "Calcutta", "Dhaka", "Kingston");
        this.addQuestion(q32);

        Question q33 = new Question("Sir Vivian Richards Stadium is located in which city", "Antigua", "Saint Lucia", "Kingston", "Antigua");
        this.addQuestion(q33);
        Question q34 = new Question("MCG is located in which city", "Mumbai", "Melbourne", "Manchester", "Melbourne");
        this.addQuestion(q34);
        Question q35 = new Question("Sinhalese Sports Club Ground is located in which city", "Colombo", "Candy", "Galle", "Colombo");
        this.addQuestion(q35);
        Question q36 = new Question("Lal Bahadur Shastri Stadium is located in which city", "Hyderabad", "Bhopal", "Jaipur", "Hyderabad");
        this.addQuestion(q36);



        // END
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        // Create tables again
        onCreate(db);
    }

    // Adding new question
    public void addQuestion(Question quest) {
        // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());

        // Inserting Row
        dbase.insert(TABLE_QUEST, null, values);
    }

    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));

                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }

}

