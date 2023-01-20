import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConflictGraph {
    int days;
    double penalty_after_constructive;
    double penalty_after_kempe;
    double penalty_after_pair_swap;
    String constructive_heuristic;
    String penalty_strategy;
    List<Course> courses;
    List<Student> students;

    public ConflictGraph(String constructive_heuristic, String penalty_strategy, List<Course> courses, List<Student> students) {
        this.days = 1;
        this.penalty_after_constructive = 0;
        this.penalty_after_kempe = 0;
        this.penalty_after_pair_swap = 0;
        this.constructive_heuristic = constructive_heuristic;
        this.penalty_strategy = penalty_strategy;
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

        Heuristic heuristic = new Heuristic(constructive_heuristic);

        //assign days to courses
        if(constructive_heuristic.equals("saturation degree")){
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
        }else{
            boolean availableColor[] = new boolean[courses.size()+1];
            Arrays.fill(availableColor, true);
            
            while(courses.size() > 0){
                Course c = heuristic.getNextCourse(courses);
                courses.remove(courses.indexOf(c));
    
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

        if(this.penalty_strategy.equals("exponential")){
            this.penalty_after_constructive = getExponentialPenalty();
            execKempeChain();
            this.penalty_after_kempe = getExponentialPenalty();
            execPairSwap();
            this.penalty_after_pair_swap = getExponentialPenalty();
        }
    }

    public double getLinearPenalty(){
        double penalty = 0;

        for(Student s : students){
            List<Course> courses = s.courses;

            for(Course c1 : courses){
                for(Course c2 : courses){
                    if(!c1.courseId.equals(c2.courseId)){
                        int diff = Math.abs(c1.day - c2.day);
                        if(diff == 0) penalty += 10;
                        else if(diff == 1) penalty += 8;
                        else if(diff == 2) penalty += 6;
                        else if(diff == 3) penalty += 4;
                        else if(diff == 4) penalty += 2;
                    }
                }
            }
        }
        
        return penalty/students.size();
    }

    public double getExponentialPenalty(){
        double penalty = 0;

        for(Student s : students){
            List<Course> courses = s.courses;

            for(Course c1 : courses){
                for(Course c2 : courses){
                    if(!c1.courseId.equals(c2.courseId)){
                        int diff = Math.abs(c1.day - c2.day);
                        if(diff == 0) penalty += 32;
                        else if(diff == 1) penalty += 16;
                        else if(diff == 2) penalty += 8;
                        else if(diff == 3) penalty += 4;
                        else if(diff == 4) penalty += 2;
                        else if(diff == 5) penalty += 1;
                    }
                }
            }
        }
        
        return penalty/students.size();
    }

    public void execKempeChain(){

    }

    public void execPairSwap(){

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
