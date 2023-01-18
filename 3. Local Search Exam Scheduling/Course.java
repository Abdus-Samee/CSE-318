import java.util.HashSet;
import java.util.Set;

public class Course {
    int day;
    String courseId;
    String enrollment;
    Set<Course> conflicts;

    public Course(String courseId, String enrollment) {
        this.day = -1;
        this.courseId = courseId;
        this.enrollment = enrollment;
        this.conflicts = new HashSet<>();
    }

    public void addConflict(Course course) {
        this.conflicts.add(course);
    }

    public int getEdgeCount() {
        return conflicts.size();
    }

    public void assignDay(int day) {
        this.day = day;
    }
}
