import java.util.*;
import java.time.*;

public class Student extends User implements View{
    protected ArrayList<ArrayList<Course>> registered_courses;
    protected Map<String,Float> completed_courses_gpa;
    protected Map<String,Boolean> feedback_given;
    protected ArrayList<Course> sem_courses;
    protected ArrayList<Complaint> myComplaints;
    protected int current_sem, credits;
    protected int rno;
    protected static int counter = 1;

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
        feedback_given = new HashMap<String, Boolean>();
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

    @Override
    public boolean equals(Object o1) {
        if(o1 != null && getClass() == o1.getClass()) {
            Student o = (Student) o1;
            return (this.email.equals(o.email));
        }else return false;
    }

    public static void dec_counter() {counter--;}

    public Map<String,Float> get_ccg() { return completed_courses_gpa; }

    public void set_ccg(Map<String,Float> _ccg) { completed_courses_gpa = _ccg; }

    public Map<String, Boolean> get_fg() { return feedback_given; }

    public void set_fg(Map<String, Boolean> _fg) { feedback_given = _fg; }

    public ArrayList<Course> get_sem_courses() { return sem_courses; }

    public void set_sem_courses(ArrayList<Course> _sc) { sem_courses = _sc; }

    public ArrayList<Complaint> get_complaints() { return myComplaints; }

    public void set_complaints(ArrayList<Complaint> _mc) { myComplaints = _mc; }

    public ArrayList<ArrayList<Course>> get_rc() { return registered_courses; }

    public void set_rc(ArrayList<ArrayList<Course>> _rc) { registered_courses = _rc; }

    public int get_rno() {
        return rno;
    }

    public void set_rno(int rn) { rno = rn; }

    public int get_credits() { return credits; }

    public void set_credits(int creds) { credits = creds; }

    public int get_current_sem() { return current_sem; }

    public void set_current_sem(int cs) { current_sem = cs; }

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
        for(Course u: get_curr_courses()) {
            u.remove_student(this);
        }
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
                    else if(enrol_lmt_full) {
                        try {
                            throw new CourseFullException("Course enrollment limit full! Register for another course or wait for someone to drop the course.");
                        }catch (CourseFullException e) {
                            System.out.println(e.getMessage());
                        }
                    }
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
        if(LocalDateTime.now().isAfter(Courses.get_drop_deadline())) {
            try {
                throw new DropDeadlinePassedException("Deadline to drop courses passed. Cannot drop courses now.");
            }catch (DropDeadlinePassedException e) {
                System.out.println(e.getMessage());
                return;
            }
        }

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

    public void give_feedback() {
        while(true) {
            System.out.println("Enter course code of course to give feedback or write \'0\' to go back: ");

            Scanner scn = new Scanner(System.in);
            String code = scn.nextLine();

            if (code.equals("0")) return;

            if(feedback_given.containsKey(code)) {
                System.out.println("Feedback already given!");
                continue;
            }

            boolean course_completed = false, course_found = false, given = false;
            for(int sem = 1; sem < 9; sem++) {
                for (Course u : Courses.get_sem_courses(sem)) {
                    if(u.get_course_code().equals(code)) {
                        if(completed_courses_gpa.containsKey(code)) {
                            course_completed = true;
                            while (true) {
                                System.out.println("Enter:\n1 - Give rating (1-5)\n2 - Give textual feedback");
                                Scanner scn_act = new Scanner(System.in);
                                String action = scn_act.nextLine();
                                if (action.equals("1")) {
                                    try {
                                        System.out.println("Enter rating (1-5): ");
                                        Scanner scn_int = new Scanner(System.in);
                                        int rating = scn_int.nextInt();
                                        u.add_feedback(new Feedback<Integer>(rating));
                                    } catch (InputMismatchException inp) {
                                        System.out.println("Incorrect input! Try Again.");
                                        continue;
                                    }
                                } else {
                                    System.out.println("Enter Feedback: ");
                                    Scanner scn_str = new Scanner(System.in);
                                    String str = scn_str.nextLine();

                                    u.add_feedback(new Feedback<String>(str));
                                }
                                feedback_given.put(code, true);
                                given = true;
                                break;
                            }
                        }
                        course_found = true;
                        break;
                    }
                }
                if(given) break;
            }

            if(!course_found) System.out.println("Incorrect Entry! Try Again.");
            else if(!course_completed) System.out.println("Course not completed!");
            else if(given) System.out.println("Feedback given successfully!");
        }
    }
}
