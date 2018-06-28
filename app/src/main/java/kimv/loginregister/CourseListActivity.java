package kimv.loginregister;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class CourseListActivity extends AppCompatActivity {

    public static final String COURSE_NAME = "coursename";
    public static final String COURSE_ID = "courseid";
    public static final String COURSE_YEAR = "courseyear";
    public static final String COURSE_EC = "courseec";
    public static final String COURSE_GRADE = "coursegrade";
    public static final String COURSE_COMMENT = "coursecomment";
    public static final String COURSE_SPECIAL = "coursespecial";
    public static final String COURSE_STATUS = "coursestatus";

    ListView listViewCourses;
    List<Course> courses;
    DatabaseReference databaseCourses;
    Query query;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courselist);

        databaseCourses = FirebaseDatabase.getInstance().getReference("courses");


        listViewCourses = (ListView) findViewById(R.id.listViewCourses);

        courses = new ArrayList<>();

        listViewCourses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Course course = courses.get(i);

                Intent intent = new Intent(getApplicationContext(), EditCourseActivity.class);

                intent.putExtra(COURSE_ID, course.getCourseID());
                intent.putExtra(COURSE_NAME, course.getCourseName());
                intent.putExtra(COURSE_YEAR, course.getCourseYear());
                intent.putExtra(COURSE_EC, course.getCourseEC());
                intent.putExtra(COURSE_GRADE, course.getCourseGrade());
                intent.putExtra(COURSE_COMMENT, course.getCourseComments());
                intent.putExtra(COURSE_SPECIAL, course.getCourseSpecial());
                intent.putExtra(COURSE_STATUS, course.getCourseStatus());

                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {

        super.onStart();

        Query query = databaseCourses.orderByChild("courseYear").equalTo("Jaar 1");
        query.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){

                courses.clear();

                for(DataSnapshot courseSnapshot: dataSnapshot.getChildren()){
                    Course course = courseSnapshot.getValue(Course.class);

                    courses.add(course);
                }

                CourseList adapter = new CourseList(CourseListActivity.this, courses);
                listViewCourses.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError){

            }
        });
    }
}
