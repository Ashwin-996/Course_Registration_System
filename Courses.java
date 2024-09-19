import java.util.ArrayList;
// Make prerequisites a list
public class Courses
{
    private static ArrayList<ArrayList<Course>> sems;

    public Courses()
    {
        sems = new ArrayList<ArrayList<Course>>();

        for(int i=0; i<8; i++) {
            ArrayList<Course> sem = new ArrayList<Course>();
            sems.add(sem);
        }

        ArrayList<Integer> sems_off = new ArrayList<Integer>();
        sems_off.add(1);
        sems_off.add(3);
        sems_off.add(5);
        sems_off.add(7);


        Course _course;
        _course = new Course("CSE101", "Introduction to programming", 4, "None", "Monday-9:30-11 C-101 Wednesday-9:30-11 C-102", sems_off, 150);
        _course = new Course("ECE111", "Digital Circuits", 4, "None", "Tuesday-11-12 C-101 Thursday-11-12 C-102 Friday-11-12 A-302", sems_off, 150);
        _course = new Course("DES102", "Introduction to HCI", 4, "None", "Tuesday-9:30-11 C-03 Thursday-9:30-11 C-101", sems_off, 150);
        _course = new Course("MTH100", "Linear Algebra", 4, "None", "Monday-11-12 C-12 Wednesday-11-12 C-02 Friday-9:30-10:30 B-320", sems_off, 2);
        _course = new Course("COM101", "Communication Skills", 4, "None", "Wednesday-3-6 C-102", sems_off, 150);

        sems_off.remove(0);
        _course = new Course("CSE201", "Advanced Programming", 4, "CSE101, CSE102", "Monday-3-4:30 C-101 Wednesday-3-4:30 C-03", sems_off, 150);
        _course = new Course("CSE231", "Operating Systems", 4, "CSE102", "Tuesday-3-4:30 C-02 Thursday-3-4:30 C-11", sems_off, 150);
        _course = new Course("MTH203", "Multivariate Calculus", 4, "None", "Tuesday-4:30-6 A-101 Thursday-4:30-6 C-03", sems_off, 150);
        _course = new Course("CSE121", "Discrete Mathematics", 4, "None", "Monday-9:30-11 A-232 Wednesday-9:30-11 B-114", sems_off, 150);
        _course = new Course("ECE250", "Signals and Systems", 4, "MTH100", "Wednesday-11-12:30 A-102 Friday-9:30-11 C-101", sems_off, 150);

        sems_off.remove(0);
        _course = new Course("CSE643", "Artificial Intelligence", 4, "CSE102", "Monday-9:30-11 C-102 Wednesday-3-4:30 B-211", sems_off, 150);
        _course = new Course("CSE231", "Machine Learning", 4, "MTH203", "Tuesday-3-4:30 C-101 Thursday-3-4:30 C-03", sems_off, 150);
        _course = new Course("CSE546", "Applied Cryptography", 4, "CSE121", "Wednesday-9:30-11 B-112 Thursday-4:30-6 A-110", sems_off, 150);
        _course = new Course("ECE654/CSE564", "Reinforcement Learning", 4, "MTH201", "Monday-1:30-3 A-301 Friday-11-12:30 B-313", sems_off, 150);
        _course = new Course("COM301A", "Technical Communication", 2, "COM101", "Tuesday-2-3 C-03 Friday-2-3 C-101", sems_off, 150);
        _course = new Course("ESC205A", "Environmental Science", 2, "None", "Wednesday-4:6 C-202", sems_off, 150);
        _course = new Course("MTH341/MTH541", "Complex Analysis", 4, "MTH203", "Monday-3-4:30 C-231 Thursday-9:30-11 C-02", sems_off, 150);

        sems_off.clear();
        sems_off.add(2);
        sems_off.add(4);
        sems_off.add(6);
        sems_off.add(8);
        _course = new Course("CSE102", "Data Structures and Algorithms", 4, "CSE101", "Wednesday-9:30-11 C-101 Friday-11-12:30 B-21", sems_off, 150);
        _course = new Course("CSE112", "Computer Organization", 4, "ECE111", "Tuesday-3-4 A-11 Thursday-9:30-10:30 B-101 Friday-9:30-10:30 C-11", sems_off, 150);
        _course = new Course("MTH201", "Probability and Statistics", 4, "None", "Monday-9:30-10:30 B-122 Tuesday-4:30-5:30 C-103 Thursday-4:30-5:30 C-23", sems_off, 150);
        _course = new Course("ECO223", "Money and Banking", 4, "None", "Monday-3-4:30 A-312 Wednesday-3-4:30 A-221", sems_off, 150);
        _course = new Course("ECE113", "Basic Electronics", 4, "ECE111", "Tuesday-11-12:30 C-01 Thursday-11-12:30 C-11", sems_off, 150);


        sems_off.remove(0);
        _course = new Course("CSE322", "Theory of Computation", 4, "CSE121", "Monday-3-4:30 A-301 Wednesday-3-4:30 B-112", sems_off, 150);
        _course = new Course("ECE356/CSE342", "Statistical Machine Learning", 4, "CSE101, MTH201", "Tuesday-3-4:30 C-03 Thursday-3-4:30 B-101", sems_off, 150);
        _course = new Course("MTH310", "Graph Theory", 4, "None", "Tuesday-4:30-6 C-101 Thursday-4:30-6 C-12", sems_off, 150);
        _course = new Course("ESC207A", "Ecology, Evolution and Environment", 2, "None", "Monday-9:30-11 A-110 Wednesday-9:30-11 B-131", sems_off, 150);
        _course = new Course("MTH377/MTH577", "Convex Optimization", 4, "MTH100", "Wednesday-11-12:30 C-04 Friday-9:30-11 B-111", sems_off, 150);
        _course = new Course("CSE222", "Analysis and Design of Algorithms", 4, "CSE101, CSE102", "Monday-1:30:3 C-13 Thursday-1:30-2 A-021", sems_off, 150);


        sems_off.remove(0);
        _course = new Course("CSE344/CSE544/ECE344/ECE544", "Computer Vision", 4, "MTH100", "Monday-9:30-11 C-13 Wednesday-3-4:30 C-102", sems_off, 150);
        _course = new Course("CSE556", "Natural Language Processing", 4, "CSE101, MTH201, CSE222", "Tuesday-3-4:30 C-201 Thursday-3-4:30 A-22", sems_off, 150);
        _course = new Course("CSE350/CSE550", "Network Security", 4, "None", "Wednesday-9:30-11 C-201 Thursday-4:30-6 B-23", sems_off, 150);
        _course = new Course("CSE641/ECE555", "Deep Learning", 4, "None", "Monday-1:30-3 C-121 Friday-11-12:30 B-21", sems_off, 150);
        _course = new Course("CSE601", "Compilers", 4, "CSE101, CSE102, CSE201", "Tuesday-2-3 C-202 Friday-2-3 A-12", sems_off, 150);
        _course = new Course("ENG599s", "Research Methods", 2, "None", "Wednesday-4:6 B-11", sems_off, 150);
        _course = new Course("ECO333", "Macroeconomics", 4, "None", "Monday-3-4:30 C-202 Thursday-9:30-11 B-203", sems_off, 150);
    }

    public static ArrayList<Course> get_sem_courses(int sem_no)
    {
        return sems.get(sem_no-1);
    }

    public static void add_course(int sem, Course _course) {
        sems.get(sem-1).add(_course);
    }

    public static void remove_course(int sem, String code) {
        for(Course u: sems.get(sem-1)) {
            if(u.get_course_code().equals(code)) {
                ArrayList<Student> _students = Admin.get_students();
                for(Student _student: _students) {
                    for(Course _course: _student.get_reg_courses(sem)) {
                        if(_course == u) {
                            _student.remove_reg_course(sem, _course);
                            break;
                        }
                    }
                }
                u.remove_from_sem(sem);
                sems.get(sem-1).remove(u);
                break;
            }
        }
    }
}