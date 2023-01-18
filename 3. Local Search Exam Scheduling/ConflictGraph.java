import java.util.ArrayList;
import java.util.List;

public class ConflictGraph {
    int days = 1;
    String constructive_heuristic;
    List<Course> courses;
    List<Student> students;

    public ConflictGraph(String constructive_heuristic, List<Course> courses, List<Student> students) {
        this.constructive_heuristic = constructive_heuristic;
        this.courses = courses;
        this.students = students;
    }

    public void createConflictGraph() {
        //calculate conflict
        for(Student s : students){
            for(Course c1 : s.courses){
                for(Course c2 : s.courses){
                    if(!c1.courseId.equals(c2.courseId)) c1.addConflict(c2);
                }
            }
        }

        //remove non-conflicting courses
        List<Course> conflicting_courses = new ArrayList<>();
        for(Course c : courses){
            if(c.getEdgeCount() == 0){
                c.assignDay(days);
            }else conflicting_courses.add(c);
        }

        Heuristic heuristic = new Heuristic(constructive_heuristic, conflicting_courses);

        //assign days
        while(conflicting_courses.size() > 0){
            Course c = heuristic.getNextCourse();
            conflicting_courses.remove(conflicting_courses.indexOf(c));
            c.assignDay(days);
            resetDegreeNonConflictingCourses(c, conflicting_courses);

            //remove c from conflict of all other courses
            for(Course course : conflicting_courses){
                course.conflicts.remove(c);
            }

            days++;
        }
    }

    public void resetDegreeNonConflictingCourses(Course course, List<Course> conflicting_courses) {
        List<Course> non_conflicting_courses = new ArrayList<>();
        for(Course c : conflicting_courses){
            if(!c.conflicts.contains(course)){
                non_conflicting_courses.add(c);
                c.assignDay(days);
            }
        }

        for(Course c : non_conflicting_courses) conflicting_courses.remove(c);
    }
}
