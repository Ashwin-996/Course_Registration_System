import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Student extends User implements View{
    private ArrayList<ArrayList<Course>> registered_courses;
    private Map<String,Float> completed_courses_gpa;
    private ArrayList<Course> sem_courses;
    private ArrayList<Complaint> myComplaints;
    private int current_sem, credits;
    private final int rno;
    private static int counter = 1;

    public Student() {
        super();
        rno = counter;
    }

    public Student(String name, String email, String cno, String pwd){
        super(name, email, cno, pwd);
        credits = 0;
        current_sem = 1;
        rno = counter;
        counter++;
        this.name = name;
        this.contact_no = contact_no;
        this.email = email;
        completed_courses_gpa = new HashMap<String, Float>();
        myComplaints = new ArrayList<Complaint>();
        sem_courses = new ArrayList<Course>();
        sem_courses = Courses.get_sem_courses(this.current_sem);
        registered_courses = new ArrayList<ArrayList<Course>>();
        for(int i=0; i<8; i++)
        {
            ArrayList<Course> in = new ArrayList<Course>();
            registered_courses.add(in);
        }
        Admin.add_student(this);
        Users.add_user(this);
    }

    public int get_rno() {
        return rno;
    }

    public void update_gpa(String code, float gpa) {
        completed_courses_gpa.put(code, gpa);
    }

    @Override
    public void view_courses()
    {
        System.out.println(sem_courses);
    }

    public ArrayList<Course> get_reg_courses(int sem) {
        return registered_courses.get(sem-1);
    }

    public ArrayList<Course> get_curr_courses() {
        return registered_courses.get(current_sem-1);
    }

    public void inc_sem() {
        current_sem++;
        sem_courses = Courses.get_sem_courses(this.current_sem);
        credits = 0;
    }

    public boolean course_completed(String code) {
        if(completed_courses_gpa.containsKey(code)) return true;
        return false;
    }

    public float get_gpa(String code) {
        return completed_courses_gpa.get(code);
    }

    public void remove_reg_course(int sem, Course _course) {
        registered_courses.get(sem-1).remove(_course);
    }

    public void view_schedule() {
        while(true) {
            System.out.println("Enter:\n0 - Go back\n1 - View details of all courses\nOR - Write the course code for which you want to view schedule");
            Scanner scn = new Scanner(System.in);
            String action = scn.nextLine();

            if(action.equals("0")) return;
            else if(action.equals("1")) {
                if(registered_courses.get(current_sem-1).size() == 0) System.out.println("No Course registered!");
                else System.out.println(registered_courses.get(current_sem-1));
            }
            else {
                boolean course_found = false;
                for(Course u: registered_courses.get(current_sem-1)) {
                    if(u.get_course_code().equals(action)) {
                        System.out.println(u);
                        course_found = true;
                        break;
                    }
                }

                if(!course_found) System.out.println("Not registered for such a course! Try Again.");
            }
        }
    }

    public void register_for_courses()
    {
        Scanner scn = new Scanner(System.in);
        while(true) {
            System.out.println("Enter Semester from 1-8: ");
            int sem = scn.nextInt();
            if (sem != current_sem) System.out.println("No courses available for registration!");
            else break;
        }

        System.out.println("Courses available for registration:\n" + sem_courses);
        Scanner scnn = new Scanner(System.in);
        Scanner scn_action = new Scanner(System.in);
        while(true) {
            while(true) {
                System.out.println("Enter:\n0 - Go back\n1 - View available courses\n2 - To continue to register for courses");
                String action = scn_action.nextLine();
                if (action.equals("0")) return;
                else if (action.equals("1")) {
                    System.out.println("Courses available for registration:");
                    view_courses();
                    continue;
                }else if(action.equals("2")) break;
                else System.out.println("Incorrect Entry! Try Again");
            }
            System.out.println("Enter Course Code: ");

            String code = scnn.nextLine();
            System.out.println("code: " + code);
            boolean found_course = false, course_done = false, already_reg = false, enrol_lmt_full = false;
            String pre_not_done = "None";
            Course do_course = new Course("", "", 0, "", "", new ArrayList<Integer>(), 150);
            for(Course u: registered_courses.get(current_sem-1)) {
                if(code.equals(u.get_course_code())) already_reg = true;
            }
            for (Course u : sem_courses) {
                if (!u.get_course_code().equals(code)) continue;
                found_course = true;
                if (completed_courses_gpa.containsKey(code)) {
                    course_done = true;
                }

                String[] prereqs = u.get_prerequisites();
                if (!prereqs[0].equals("None")) {
                    for (String prereq : prereqs) {
                        if (!completed_courses_gpa.containsKey(prereq)) {
                            pre_not_done = prereq;
                            break;
                        }
                    }
                }

                if(u.get_enrollment_limit() == u.num_students()) enrol_lmt_full = true;

                do_course = u;
                break;
            }

            if (!found_course) System.out.println("Incorrect Course Code!");
            else {
                if (course_done) System.out.println("Course already completed!");
                else if(already_reg) System.out.println("Already registered for this course!");
                else {
                    if (!pre_not_done.equals("None")) System.out.println("Prerequisite " + pre_not_done + " not done!");
                    else if(enrol_lmt_full) System.out.println("Course enrollment limit full! Register for another course or wait for someone to drop the course.");
                    else {
                        if(do_course.get_credits() + credits > 20) System.out.println("Cannot exceed credit limit of 20 for a semester! Currently credits taken: " + credits);
                        else {
                            registered_courses.get(current_sem-1).add(do_course);
                            do_course.add_student(this);
                            System.out.println("Registered for course " + code);
                            credits += do_course.get_credits();
                        }
                    }
                }
            }
        }
    }

    public void drop_course() {
        System.out.println("Registered courses: " + registered_courses.get(current_sem - 1));
        while (true) {
            System.out.println("Enter course code of course to be dropped or write \'0\' to go back: ");

            Scanner scn = new Scanner(System.in);
            String code = scn.nextLine();

            if (code.equals("0")) return;

            boolean course_registered = false;
            for (Course u : registered_courses.get(current_sem - 1)) {
                if (u.get_course_code().equals(code)) {
                    course_registered = true;
                    break;
                }
            }

            if (!course_registered) {
                System.out.println("Not registered for such a course! Try Again.");
                continue;
            }

            boolean found_code = false;
            for (Course u : sem_courses) {
                if (u.get_course_code().equals(code)) {
                    credits -= u.get_credits();
                    boolean check = registered_courses.get(current_sem - 1).remove(u);
                    u.remove_student(this);
                    found_code = true;
                    break;
                }
            }

            if (!found_code) System.out.println("Not registered for such a course! Try Again.");
            else System.out.println("Course dropped Successfully");
        }
    }

    public void view_sgpa(int sem) {
        boolean sem_completed = true;
        float counter = 0;
        float sgpa = 0;
        for(int i=0; i<registered_courses.get(sem-1).size(); i++) {
            if((!completed_courses_gpa.containsKey(registered_courses.get(sem-1).get(i).get_course_code())) || completed_courses_gpa.get(registered_courses.get(sem-1).get(i).get_course_code()) < 4.0f) {
                sem_completed = false;
                break;
            }
            float cred = (float)registered_courses.get(sem-1).get(i).get_credits();
            sgpa += cred*completed_courses_gpa.get(registered_courses.get(sem-1).get(i).get_course_code());
            counter += cred;
        }

        try {
            if (counter > 1) sgpa = sgpa / counter;
        }catch (Exception e) {
            System.out.println("FPE. Division by zero!");
        }

        if(!sem_completed || (registered_courses.get(sem-1).size() == 0)) System.out.println("Cannot calculate SGPA, sem not completed!");
        else System.out.printf("SGPA for sem %d: %.2f\n", sem, sgpa);
    }

    public void view_cgpa() {
        float tot_counter = 0;
        float tot_gpa = 0;
        for(int sem=1; sem<9; sem++) {
            boolean sem_completed = true;
            float counter = 0;
            float sgpa = 0;
            for (int i = 0; i < registered_courses.get(sem - 1).size(); i++) {
                if (!completed_courses_gpa.containsKey(registered_courses.get(sem - 1).get(i).get_course_code()) || completed_courses_gpa.get(registered_courses.get(sem-1).get(i).get_course_code()) < 4.0f) {
                    sem_completed = false;
                    break;
                }
                float cred = (float) registered_courses.get(sem - 1).get(i).get_credits();
                sgpa += cred * completed_courses_gpa.get(registered_courses.get(sem - 1).get(i).get_course_code());
                counter += cred;
            }

            if(sem_completed) {
                tot_gpa += sgpa;
                tot_counter += counter;
            }else break;
        }

        try {
            if (tot_counter > 1) {
                tot_gpa = tot_gpa / tot_counter;
                System.out.printf("CGPA: %.2f\n", tot_gpa);
            } else System.out.println("CGPA: No semester completed!");
        } catch (Exception e) {
            System.out.println("FPE. Divison by zero!");
        }
    }

    public void issue_complaint() {
        while(true) {
            System.out.println("Enter:\n0 - Go back\n1 - Issue New Complaint");
            Scanner scn = new Scanner(System.in);
            String action = scn.nextLine();

            if(action.equals("0")) return;
            else if(action.equals("1")) {
                System.out.println("Write a description of the issue: ");
                String desc = scn.nextLine();

                myComplaints.add(new Complaint(desc));
            }
            else System.out.println("Incorrect Entry! Try Again.");
        }
    }

    public void view_complaints() {
        for(Complaint u: myComplaints) {
            System.out.printf("Status: ");
            if(u.view_status() == Status.Pending) System.out.printf("Pending   ");
            else System.out.printf("Resolved   ");

            System.out.println("Description: " + u.get_description());
        }
    }
}