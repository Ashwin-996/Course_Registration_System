import java.util.Scanner;
import java.util.ArrayList;

public class Professor extends User implements Edit, View{
    private Course myCourse;
    private String office_hours;

    public Professor() {
        super();
        ArrayList<Integer> sems = new ArrayList<Integer>();
        myCourse = new Course("", "", 0, "", "", sems, 150);
        office_hours = "Not Available yet";
    }

    public Professor(String name, String email, String cno, String pwd) {
        super(name, email, cno, pwd);
        Admin.add_prof(this);
        Users.add_user(this);
        ArrayList<Integer> sems = new ArrayList<Integer>();
        myCourse = new Course("", "", 0, "", "", sems, 150);
        office_hours = "Not Available yet";
    }

    @Override
    public void view_courses() {
        System.out.println(myCourse);
    }

    public Course get_course() {
        return myCourse;
    }

    public void set_course(Course _course) {
        myCourse = _course;
    }

    public String get_oh() {
        return office_hours;
    }

    @Override
    public void edit_courses() {
        while(true) {
            System.out.println("Enter a number:\n0 - Go back\n1 - Update syllabus\n2 - Update class timings and location\n3 - Update credits\n4 - Update prerequisities\n5 - Update enrollment limit\n6 - Update office hours\n7 - Update course code");
            Scanner scn = new Scanner(System.in);
            String action = scn.nextLine();

            if (action.equals("0")) return;
            else if (action.equals("1")) {
                while (true) {
                    System.out.println("Enter:\n0 - Go back\n1 - Add Topic\n2 - Remove Topic");
                    String act = scn.nextLine();

                    if (act.equals("0")) break;
                    else if (act.equals("1")) {
                        System.out.println("Enter topic to be added: ");
                        String topic = scn.nextLine();
                        myCourse.add_topic(topic);

                        System.out.println("Topic added to syllabus");
                    } else if (act.equals("2")) {
                        System.out.println("Enter topic to be removed: ");
                        String topic = scn.nextLine();
                        boolean check = myCourse.remove_topic(topic);

                        if (check) System.out.println("Topic removed from syllabus");
                        else System.out.println("Topic not in syllabus");
                    }
                }
            }else if(action.equals("2")) {
                System.out.println("Enter new class timings and location (Format - Day-Timing Location)");
                String _schedule = scn.nextLine();

                myCourse.set_schedule(_schedule);
                System.out.println("Schedule Updated Successfully.");
            }
            else if(action.equals("3")) {
                System.out.println("Update credits for course(2 or 4): ");
                Scanner scan_int = new Scanner(System.in);

                int creds = scan_int.nextInt();
                myCourse.set_credits(creds);
                System.out.println("Credits Updated Successfully.");
            }else if(action.equals("4")) {
                System.out.println("Enter prerequisites for the course: ");
                String prereqs = scn.nextLine();

                myCourse.set_prerequisites(prereqs);
                System.out.println("Prerequisites Updated Successfully.");
            }else if(action.equals("5")) {
                System.out.println("Enter enrollment limit: ");
                Scanner scan_int = new Scanner(System.in);

                int limit = scan_int.nextInt();
                myCourse.set_enrollment_limit(limit);
                System.out.println("Enrollment Limit Updated Successfully.");
            }else if(action.equals("6")) {
                System.out.println("Enter office hours: ");
                String _oh = scn.nextLine();

                office_hours = _oh;
                System.out.println("Office hours Updated Successfully.");
            }else if(action.equals("7")) {
                System.out.println("Enter new course code: ");
                String code = scn.nextLine();

                myCourse.set_course_code(code);
                System.out.println("Course Code Updated Successfully.");
            }else System.out.println("Incorrect Entry!");
        }
    }

    @Override
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
