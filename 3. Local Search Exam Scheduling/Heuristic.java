import java.util.List;

public class Heuristic{
    String heuristic;
    List<Course> courses;

    public Heuristic(String heuristic, List<Course>courses){
        this.heuristic = heuristic;
        this.courses = courses;
    }

    public Course getNextCourse(){
        if(heuristic.equals("largest degree")){
            return largestDegree();
        }

        return null;
    }

    public Course largestDegree(){
        Course c = null;
        int mx = Integer.MIN_VALUE;

        for(Course course : courses){
            if(course.getEdgeCount() > mx){
                mx = course.getEdgeCount();
                c = course;
            }
        }


        return c;
    }
}
