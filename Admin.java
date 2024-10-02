import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User implements Edit, View{
    private static ArrayList<Professor> profs = new ArrayList<Professor>();
    private static ArrayList<Student> students = new ArrayList<Student>();
    private static ArrayList<Admin> admins = new ArrayList<Admin>();
    private static ArrayList<Complaint> complaints = new ArrayList<Complaint>();
    private static ArrayList<TA> tas = new ArrayList<TA>();

    public Admin() {
        super();
    }

    public Admin(String name, String email, String cno, String pwd) {
        super(name, email, cno, pwd);
        Users.add_user(this);
        admins.add(this);
    }

    public static void add_student(Student _student) {
        students.add(_student);
    }

    public static void remove_student(Student _student) {
        students.remove(_student);
    }

    public static void add_TA(TA _ta) {
        tas.add(_ta);
    }

    public static void add_prof(Professor _prof) {
        profs.add(_prof);
    }

    public static ArrayList<Student> get_students() {
        return students;
    }

    public static ArrayList<TA> get_tas() {
        return tas;
    }

    public static ArrayList<Professor> get_profs() {
        return profs;
    }

    public static ArrayList<Admin> get_admins() {
        return admins;
    }

    public static void add_complaint(Complaint _comp) {
        complaints.add(_comp);
    }

    public static void view_complaints() {
        System.out.println("Enter:\n0 - Go back\n1 - No Filter\n2 - Filter complaints by pending status\n3 - Filter complaints by resolved status");
        Scanner scn = new Scanner(System.in);
        String action = scn.nextLine();

        if(action.equals("0")) return;
        else if(action.equals("1")) {
            for (int i = 0; i < complaints.size(); i++) {
                System.out.println("Complaint_id: " + complaints.get(i).get_id() + ".  Status: " + complaints.get(i).view_status() + ".   Description: " + complaints.get(i).get_description());
            }
        }else if(action.equals("2")) {
            int i = 0;
            while(i < complaints.size()) {
                if(complaints.get(i).view_status() == Status.Pending) System.out.println("Complaint_id: " + complaints.get(i).get_id() + ".  Status: Pending" + ".   Description: " + complaints.get(i).get_description());
                i++;
            }
        }else if(action.equals("3")) {
            int i = 0;
            while(i < complaints.size()) {
                if(complaints.get(i).view_status() == Status.Resolved) System.out.println("Complaint_id: " + complaints.get(i).get_id() + ".  Status: Resolved." + "   Description: " + complaints.get(i).get_description());
                i++;
            }
        }
    }

    public void change_complaint_status() {
        while(true) {
            System.out.println("Enter complaint id or write \'0\' to go back: ");
            Scanner scan_int = new Scanner(System.in);
            int id = scan_int.nextInt();

            if(id == 0) return;

            System.out.println("Enter new status (0-Pending, 1-Resolved): ");
            Scanner scn = new Scanner(System.in);
            String sts = scn.nextLine();

            if (sts.equals("0")) complaints.get(id - 1).set_status(Status.Pending);
            else complaints.get(id - 1).set_status(Status.Resolved);
        }
    }

    @Override
    public void view_courses() {
        for(int i=1; i<9; i++) {
            System.out.println("\n\n\nSem-" + i + " courses:");
            System.out.println(Courses.get_sem_courses(i));
        }
    }

    @Override
    public void edit_courses() {
        while(true) {
            System.out.println("Enter:\n0 - Go back\n1 - Add course\n2 - Remove Course");
            Scanner scn = new Scanner(System.in);
            String action = scn.nextLine();

            if (action.equals("0")) return;
            else if (action.equals("1")) {
                System.out.println("Enter semester(1-8) to add course in: ");
                ArrayList<Integer> sems = new ArrayList<Integer>();
                Scanner scn_int = new Scanner(System.in);
                int sem = scn_int.nextInt();
                sems.add(sem);
                System.out.println("Enter credits for course: ");
                int creds =  scn_int.nextInt();
                System.out.println("Enter enrollment limit: ");
                int enrol_limit = scn_int.nextInt();

                System.out.println("Enter course code: ");
                String code = scn.nextLine();
                System.out.println("Enter course title: ");
                String title = scn.nextLine();
                System.out.println("Enter prerequisites: ");
                String prereqs = scn.nextLine();

                Course new_course = new Course(code, title, creds, prereqs, "", sems, enrol_limit);
            } else if (action.equals("2")) {
                ArrayList<Integer> sems = new ArrayList<Integer>();
                while(true) {
                    System.out.println("Enter semester to remove course from or write \'0\' to continue: ");
                    Scanner scn_int = new Scanner(System.in);
                    int sem = scn_int.nextInt();

                    if(sem == 0) break;
                    else sems.add(sem);
                }

                System.out.println("Enter course code of course to be removed: ");
                String code = scn.nextLine();

                for(int sem: sems) Courses.remove_course(sem, code);
                System.out.println("Course removed successfully!");
            }else System.out.println("Incorrect Entry! Try Again.");
        }
    }

    @Override
    public void update_student_rec() {
       while(true) {
           Student _student = new Student();
           while (true) {
               System.out.println("Enter student email or write \'0\' to go back: ");
               Scanner scn_int = new Scanner(System.in);
               String _email = scn_int.nextLine();

               if(_email.equals("0")) return;

               boolean found_student = false;
               for (Student u : students) {
                   if (u.email.equals(_email)) {
                       _student = u;
                       found_student = true;
                   }
               }

               if (!found_student) System.out.println("Student Not Found! Try Again.");
               else break;
           }

           while (true) {
               System.out.println("Enter:\n0 - Go back\n1 - Update Student name\n2 - Update student grades\n3 - Update Student contact no.\n4 - Update student email");
               Scanner scc = new Scanner(System.in);

               String action = scc.nextLine();
               if(action.equals("0")) break;
               else if(action.equals("1")) {
                   System.out.println("Enter udpated name: ");
                   String _name = scc.nextLine();

                   _student.name = _name;
                   System.out.println("Name Updated Successfully!");
               }else if (action.equals("2")) {
                   while(true) {
                       System.out.println("Enter course for which grade to be updated or write \'0\' to go back: ");
                       String code = scc.nextLine();

                       if(code.equals("0")) break;

                       boolean course_found = false;
                       for (Course u : _student.get_curr_courses()) {
                           if (u.get_course_code().equals(code)) {
                               course_found = true;
                               break;
                           }
                       }

                       if (!(course_found || _student.course_completed(code))) {
                           System.out.println("Student has not registered for this course! Try Again.");
                           continue;
                       }

                       System.out.println("Enter gpa: ");
                       Scanner scn_float = new Scanner(System.in);
                       float gpa = scn_float.nextFloat();


                       boolean any_course_left_before = false;
                       for(Course u: _student.get_curr_courses()) {
                           if(!_student.course_completed(u.get_course_code()) || _student.get_gpa(u.get_course_code()) < 4.0f) {
                               any_course_left_before = true;
                               break;
                           }
                       }

                       _student.update_gpa(code, gpa);

                       boolean any_course_left_after = false;
                       for(Course u: _student.get_curr_courses()) {
                           if((!_student.course_completed(u.get_course_code())) || _student.get_gpa(u.get_course_code()) < 4.0f) {
                               any_course_left_after = true;
                               break;
                           }
                       }
                       if(any_course_left_before && !any_course_left_after) _student.inc_sem();

                       System.out.println("Grade Updated Successfully!");
                   }
               }else if (action.equals("3")) {
                   System.out.println("Enter new contact no.: ");
                   String _contact = scc.nextLine();

                   _student.contact_no = _contact;
                   System.out.println("Contact Updated Successfully!");
               } else if (action.equals("4")) {
                   System.out.println("Enter new email.: ");
                   String _email = scc.nextLine();

                   _student.email = _email;
                   System.out.println("Email Updated Successfully!");
               } else System.out.println("Incorrect Entry! Try Again.");
           }
       }
    }

    public void assign_prof() {
        while(true) {
            System.out.println("Enter professor name or write \'0\' to go back: ");
            Scanner scn = new Scanner(System.in);
            String _name = scn.nextLine();

            if(_name.equals("0")) return;

            boolean prof_available = true, prof_found = false;
            Professor _prof = new Professor();
            for (Professor u : profs) {
                if (u.name.equals(_name)) {
                    _prof = u;
                    if(!(_prof.get_course().get_course_code().equals(""))) prof_available = false;
                    prof_found = true;
                    break;
                }
            }

            if(!prof_found) {
                System.out.println("No such Professor! Try Again.");
                continue;
            }

            if(!prof_available) {
                System.out.println("Professor already assigned to a course. Enter:\n0 - if you wish to change the course he is assigned to\n1 - if you want to reenter the name");
                Scanner scn_int = new Scanner(System.in);
                String act = scn_int.nextLine();

                if (act.equals("1")) continue;
            }

            System.out.println("Enter course code: ");
            String code = scn.nextLine();

            boolean course_found = false;
            for(int sem = 1; sem < 9; sem++) {
                for(Course u: Courses.get_sem_courses(sem)) {
                    if(u.get_course_code().equals(code)) {
                        Professor old_prof = u.get_prof();
                        if(old_prof != null) {
                            ArrayList<Integer> sms = new ArrayList<>();
                            old_prof.set_course(new Course("", "", 0, "", "", sms, 150));
                        }
                        u.set_prof(_prof);
                        _prof.set_course(u);
                        course_found = true;
                        break;
                    }
                }
                if(course_found) break;
            }
            if(!course_found) {
                System.out.println("Course not found! Try Again.");
                continue;
            }

            System.out.println("Professor assigned to course successfully!");
        }
    }

    public void change_deadline() {
        Courses.set_drop_deadline();
    }

    public void assign_TA() {
        while(true) {
            System.out.println("Enter TA email or write \'0\' to go back: ");
            Scanner scn = new Scanner(System.in);
            String _name = scn.nextLine();

            if(_name.equals("0")) return;

            boolean TA_available = true, TA_found = false;
            TA _TA = new TA();
            for (TA u : tas) {
                if (u.email.equals(_name)) {
                    _TA = u;
                    if(!(_TA.get_course().get_course_code().equals(""))) TA_available = false;
                    TA_found = true;
                    break;
                }
            }

            if(!TA_found) {
                System.out.println("No such TA! Try Again.");
                continue;
            }

            if(!TA_available) {
                System.out.println("TA already assigned to a course. Enter:\n0 - if you wish to change the course he is assigned to\n1 - if you want to reenter the name");
                Scanner scn_int = new Scanner(System.in);
                String act = scn_int.nextLine();

                if (act.equals("1")) continue;
            }

            System.out.println("Enter course code: ");
            String code = scn.nextLine();

            ArrayList<Integer> sms = new ArrayList<>();
            Course _course = new Course("", "", 0, "", "", sms, 150);
            for(int sem = 1; sem < 9; sem++) {
                for(Course u: Courses.get_sem_courses(sem)) {
                    if(u.get_course_code().equals(_TA.get_course().get_course_code())) {
                        _course = u;
                        break;
                    }
                }
            }

            boolean course_found = false;
            for(int sem = 1; sem < 9; sem++) {
                for(Course u: Courses.get_sem_courses(sem)) {
                    if(u.get_course_code().equals(code)) {
                        u.add_TA(_TA);
                        _TA.assign_course(u);
                        course_found = true;
                        break;
                    }
                }
                if(course_found) break;
            }
            if(!course_found) {
                System.out.println("Course not found! Try Again.");
                continue;
            }
            _course.remove_TA(_TA);

            System.out.println("TA assigned to course successfully!");
        }
    }

    public void make_TA() {
        while(true) {
            System.out.println("Enter email id of student or write \'0\' to go back: ");
            Scanner scn = new Scanner(System.in);
            String _email = scn.nextLine();

            if(_email.equals("0")) return;

            boolean already_ta = false;
            for(TA u: tas) {
                if(u.email.equals(_email)) {
                    already_ta = true;
                    break;
                }
            }

            if(already_ta) {
                System.out.println("This student is already a TA. Try Again.");
                continue;
            }

            boolean student_found = false;
            for(Student u: students) {
                if(u.email.equals(_email)) {
                    TA _ta = new TA(u);
                    student_found = true;
                    break;
                }
            }

            if(!student_found) System.out.println("Student Not Found! Try Again.");
            else System.out.println("TA made succesfully!");
        }
    }
}
