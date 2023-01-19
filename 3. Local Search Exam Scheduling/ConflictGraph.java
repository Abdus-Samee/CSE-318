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

        Heuristic heuristic = new Heuristic(constructive_heuristic);

        //assign days
        if(constructive_heuristic.equals("largest degree")){
            while(conflicting_courses.size() > 0){
                Course c = heuristic.getNextCourse(conflicting_courses);
                conflicting_courses.remove(conflicting_courses.indexOf(c));
                c.assignDay(days);
                resetDegreeNonConflictingCourses(c, conflicting_courses);
    
                //remove c from conflict of all other courses
                for(Course course : conflicting_courses){
                    course.conflicts.remove(c);
                }
    
                days++;
            }
        }else if(constructive_heuristic.equals("saturation degree")){
                boolean[] used = new boolean[conflicting_courses.size()];
                while(conflicting_courses.size() > 0){
                    Course c = heuristic.getNextCourse(conflicting_courses);
                    //System.out.println("selected: " + c.courseId + "index: " + conflicting_courses.indexOf(c) + "\n");
                    conflicting_courses.remove(conflicting_courses.indexOf(c));
                    for(Course course : c.conflicts){
                        if(course.day != -1) used[course.day] = true;
                    }
                    for(int i = 1; i <= used.length; i++){
                        if(!used[i]){
                            //System.out.println("Assigning day " + i + " to " + c.courseId);
                            c.assignDay(i);
                            break;
                        }
                    }
                    for(Course course : c.conflicts){
                        if(course.day != -1) used[course.day] = false;
                    }
                }
        }else if(constructive_heuristic.equals("largest enrollment")){
            
        }
    }

    //add the non-conflicting courses from conflicting_courses list to the same day
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
