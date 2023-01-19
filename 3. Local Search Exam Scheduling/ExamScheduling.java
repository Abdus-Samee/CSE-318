import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ExamScheduling {
    static String constructive_heuristic = "";
    static ArrayList<Course> courses = new ArrayList<>();
    static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        readCourseData("src/data/car-f-92.crs");
        readStudentData("src/data/car-f-92.stu");
        selectMenu();

        //System.out.println(constructive_heuristic);

        ConflictGraph conflictGraph = new ConflictGraph(constructive_heuristic, courses, students);
        conflictGraph.createConflictGraph();

        System.out.println("Days: " + conflictGraph.days);
        // for(Course c : courses){
        //     System.out.println(c.courseId + " " + c.day);
        // }
    }

    public static void readCourseData(String file){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));

            while(true){
                String in = reader.readLine();
                if(in == null) break;

                String[] arr = in.split(" ");
                courses.add(new Course(arr[0], arr[1]));
            }

            reader.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void readStudentData(String file){
        int c = 1;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));

            while(true){
                String in = reader.readLine();
                if(in == null) break;

                String[] arr = in.split(" ");
                Student student = new Student(String.valueOf(c));
                for(int i=0; i<arr.length; i++){
                    AddCourseToStudent(student, arr[i]);
                }

                students.add(student);
                c++;
            }

            reader.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void AddCourseToStudent(Student student, String courseId){
        Course course = null;
        for(Course c : courses){
            if(c.courseId.equals(courseId)){
                course = c;
                break;
            }
        }

        if(course != null){
            student.addCourse(course);
        }
    }

    public static void selectMenu(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("----------SELECT CONSTRUCTIVE HEURISTIC----------");
        System.out.println("(1)Largest Degree\n(2)Saturation Degree\n(3)Largest Enrollment\n(4)Random Ordering\n");
        String in = scanner.nextLine();
        if(in.equals("1")) constructive_heuristic = "largest degree";
        else if(in.equals("2")) constructive_heuristic = "saturation degree";
        else if(in.equals("3")) constructive_heuristic = "largest enrollment";
        else constructive_heuristic = "random ordering";
        System.out.println("----------------------------------\n");

        scanner.close();
    }
}
