import java.util.ArrayList;
import java.util.Arrays;
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
        //List<Course> conflicting_courses = new ArrayList<>();
        // for(Course c : courses){
        //     if(c.getEdgeCount() == 0){
        //         c.assignDay(days);
        //     }else conflicting_courses.add(c);
        // }

        Heuristic heuristic = new Heuristic(constructive_heuristic);

        //assign days
        if(constructive_heuristic.equals("largest degree")){
            boolean availableColor[] = new boolean[courses.size()+1];
            Arrays.fill(availableColor, true);
            
            while(courses.size() > 0){
                Course c = heuristic.getNextCourse(courses);
                courses.remove(courses.indexOf(c));
                //c.assignDay(days);
                //resetDegreeNonConflictingCourses(c, courses);
    
                for(Course course : c.conflicts){
                    if(course.day != -1) availableColor[course.day] = false;
                }
    
                for(int i = 1; i <= courses.size(); i++){
                    if(availableColor[i]){
                        c.assignDay(i);
                        days = Math.max(days, i);
                        break;
                    }
                }

                Arrays.fill(availableColor, true);
            }
        }else if(constructive_heuristic.equals("saturation degree")){
                boolean[] used = new boolean[courses.size()+1];
                for(Course course : courses) course.d = course.getEdgeCount();

                while(courses.size() > 0){
                    Course c = heuristic.getNextCourse(courses);
                    //System.out.println("selected: " + c.courseId + "index: " + conflicting_courses.indexOf(c) + "\n");
                    courses.remove(courses.indexOf(c));
                    for(Course course : c.conflicts){
                        if(course.day != -1) used[course.day] = true;
                    }
                    for(int i = 1; i <= courses.size(); i++){
                        if(!used[i]){
                            //System.out.println("Assigning day " + i + " to " + c.courseId);
                            c.assignDay(i);
                            days = Math.max(days, i);
                            break;
                        }
                    }
                    for(Course course : c.conflicts){
                        if(course.day != -1) used[course.day] = false;
                    }

                    //reset degree of the courses conflicting to the selected course
                    for(Course course : c.conflicts){
                        if(course.day == -1) course.d--;
                    }
                }
        }else if(constructive_heuristic.equals("largest enrollment")){
            boolean[] used = new boolean[courses.size()+1];
            while(courses.size() > 0){
                Course c = heuristic.getNextCourse(courses);
                courses.remove(courses.indexOf(c));
                //c.assignDay(days);
                //resetDegreeNonConflictingCourses(c, conflicting_courses);
    
                for(Course course : c.conflicts){
                    if(course.day != -1) used[course.day] = true;
                }
    
                for(int i = 1; i <= courses.size(); i++){
                    if(!used[i]){
                        c.assignDay(i);
                        days = Math.max(days, i);
                        break;
                    }
                }
                
                for(Course course : c.conflicts){
                    if(course.day != -1) used[course.day] = false;
                }
            }
        }else if(constructive_heuristic.equals("random")){
            boolean availableColor[] = new boolean[courses.size()+1];
            Arrays.fill(availableColor, true);

            while(courses.size() > 0){
                Course c = heuristic.getNextCourse(courses);
                courses.remove(courses.indexOf(c));
                //c.assignDay(days);
                //resetDegreeNonConflictingCourses(c, conflicting_courses);
    
                for(Course course : c.conflicts){
                    if(course.day != -1) availableColor[course.day] = false;
                }
    
                for(int i = 1; i <= courses.size(); i++){
                    if(availableColor[i]){
                        c.assignDay(i);
                        days = Math.max(days, i);
                        break;
                    }
                }

                Arrays.fill(availableColor, true);
            }
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
