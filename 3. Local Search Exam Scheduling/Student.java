import java.util.ArrayList;
import java.util.List;

public class Student {
    String studentId;
    List<Course> courses;
    
    public Student(String studentId){
        this.studentId = studentId;
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public Course getCourse(String courseId) {
        for (Course course : courses) {
            if (course.courseId.equals(courseId)) {
                return course;
            }
        }
        return null;
    }
}
