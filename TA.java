import java.util.ArrayList;
import java.util.Scanner;

public class TA extends Student{
    private Course myCourse;

    public TA() {
        super();
    }

    public TA(String name, String email, String cno, String pwd){
        super(name, email, cno, pwd);
        ArrayList<Integer> sems = new ArrayList<Integer>();
        myCourse = new Course("", "", 0, "", "", sems, 150);
        Admin.add_TA(this);
    }

    public Course get_course() {
        return myCourse;
    }

    public void assign_course(Course _course) {
        myCourse = _course;
    }

    public void view_students() {
        for(Student _student: myCourse.get_student_list()) {
            System.out.printf("Student name: %s   ", _student.name);
            System.out.printf("Roll no.: %d   ", _student.get_rno());
            System.out.printf("Email: %s   ", _student.email);
            System.out.printf("Contact: %s   ", _student.contact_no);
            if(_student.course_completed(myCourse.get_course_code())) System.out.println("GPA in this course: %.2f   " + _student.get_gpa(myCourse.get_course_code()));
            else System.out.println("GPA in this course: Not assigned yet");
        }
    }

    public void update_student_rec() {
        System.out.println("Enter Student Roll no.: ");
        Scanner scn = new Scanner(System.in);
        int rno = scn.nextInt();

        System.out.println("Enter GPA of student for the course: ");
        Scanner scnf = new Scanner(System.in);
        float gpa = scnf.nextFloat();

        boolean student_found = false;
        ArrayList<Student> lst = myCourse.get_student_list();
        for(int i=0; i<lst.size(); i++) {
            if(lst.get(i).get_rno() == rno) {
                student_found = true;

                Student _student = lst.get(i);

                boolean any_course_left_before = false;
                for(Course u: _student.get_curr_courses()) {
                    if((!_student.course_completed(u.get_course_code())) || _student.get_gpa(u.get_course_code()) < 4.0f) {
                        any_course_left_before = true;
                        break;
                    }
                }

                _student.update_gpa(myCourse.get_course_code(), gpa);

                boolean any_course_left_after = false;
                for(Course u: _student.get_curr_courses()) {
                    if((!_student.course_completed(u.get_course_code())) || _student.get_gpa(u.get_course_code()) < 4.0f) {
                        any_course_left_after = true;
                        break;
                    }
                }

                if(any_course_left_before && !any_course_left_after) _student.inc_sem();

                break;
            }
        }

        if(!student_found) System.out.println("Student not registered for the course.");
        else System.out.println("GPA Updated Successfully!");
    }
}