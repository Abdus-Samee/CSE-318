import java.util.List;

public class Heuristic{
    String heuristic;

    public Heuristic(String heuristic){
        this.heuristic = heuristic;
    }

    public Course getNextCourse(List<Course> courses){
        if(heuristic.equals("largest degree")) return largestDegree(courses);
        else if(heuristic.equals("saturation degree")) return saturationDegree(courses);

        return null;
    }

    public Course largestDegree(List<Course> courses){
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

    public Course saturationDegree(List<Course> courses){
        Course c = null;
        int mx = Integer.MIN_VALUE;
        //System.out.println("INNNNNNNNNNNNNNN");
        
        for(Course course : courses){
            if(course.saturationDegree() > mx){
                mx = course.saturationDegree();
                c = course;
            }else if(course.saturationDegree() == mx){
                if(course.getEdgeCount() > c.getEdgeCount()){
                    mx = course.saturationDegree();
                    c = course;
                }
            }
            //System.out.println("selected: " + c.courseId + "index: " + courses.indexOf(c) );
        }

        return c;
    }
}
