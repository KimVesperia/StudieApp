package kimv.loginregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class EditCourseActivity extends AppCompatActivity {

    EditText editTextName;
    Spinner spinnerYear;
    EditText editTextEC;
    EditText editTextGrade;
    EditText editTextComments;
    Spinner spinnerSpecial;
    Spinner spinnerStatus;

    ListView listView;

    DatabaseReference databaseCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);

        editTextName = (EditText) findViewById(R.id.editTextName);
        spinnerYear = (Spinner) findViewById(R.id.spinnerYear);
        editTextEC = (EditText) findViewById(R.id.editTextEC);
        editTextGrade = (EditText) findViewById(R.id.editTextGrade);
        editTextComments = (EditText) findViewById(R.id.editTextComments);
        spinnerSpecial = (Spinner) findViewById(R.id.spinnerSpecial);
        spinnerStatus = (Spinner) findViewById(R.id.spinnerStatus);

        Intent intent = getIntent();

        String id = intent.getStringExtra(CourseListActivity.COURSE_ID);
        String name = intent.getStringExtra(CourseListActivity.COURSE_NAME);
        String year = intent.getStringExtra(CourseListActivity.COURSE_YEAR);


        String ec = intent.getStringExtra(CourseListActivity.COURSE_EC);
        String grade = intent.getStringExtra(CourseListActivity.COURSE_GRADE);
        String comments = intent.getStringExtra(CourseListActivity.COURSE_COMMENT);
        String special = intent.getStringExtra(CourseListActivity.COURSE_SPECIAL);
        String status = intent.getStringExtra(CourseListActivity.COURSE_STATUS);

        editTextName.setText(name);

//        spinnerYear.setSelection();
        editTextEC.setText(ec);
        editTextGrade.setText(grade);
        editTextComments.setText(comments);

        databaseCourses = FirebaseDatabase.getInstance().getReference("courses").child(id);
    }
}
