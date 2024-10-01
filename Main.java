import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void view_list(ArrayList<Feedback<?>> list) {
        for(int i=0; i < list.size(); i++) {
            if(list.get(i).get_feedback() instanceof Integer) {
                System.out.println("Rating feedback: " + list.get(i).get_feedback() + "/5");
            }else {
                System.out.println("Feedback: " + list.get(i).get_feedback());
            }
        }
    }

    public static void view_course(View _viewer) {
        _viewer.view_courses();
    }

    public static void course_edit(Edit _editor) {
        _editor.edit_courses();
    }

    public static void student_rec_update(Edit _editor) {
        _editor.update_student_rec();
    }

    public static void initialize() {
        Courses _courses = new Courses();

        Student _student;
        Professor _prof;
        Admin _admin;
        TA _ta;

        _student = new Student("Ashwin Singh", "ashwin@iiitd.ac.in", "7000909901", "Ashwin");
        _student = new Student("Preeti Singh", "preeti@iiitd.ac.in", "9981705151", "Preeti");
        _student = new Student("Ayush Singh", "ayush@iiitd.ac.in", "7000909900", "Ayush");

        _prof = new Professor("Suyash Singh", "suyash@iiitd.ac.in", "8827740839", "Suyash");
        _prof = new Professor("Vivek Singh", "vivek@iiitd.ac.in", "7000909902", "Vivek");

        _admin = new Admin("Subodh Singh", "subodh@iiitd.ac.in", "9425250151", "x2@jh");

        _ta = new TA("Rohan Singh", "rohan@iiitd.ac.in", "0000000000", "Rohan");
        _ta = new TA("Mohan Singh", "mohan@iiitd.ac.in", "1111111111", "Mohan");
    }

    public static void track_acadp(Student _student) {
        while(true) {
            System.out.println("Enter:\n0 - Go back\n1 - View sgpa\n2 - View cgpa");
            Scanner scn = new Scanner(System.in);
            String action = scn.nextLine();

            if (action.equals("0")) return;
            else if (action.equals("1")) {
                while(true) {
                    System.out.println("Enter semester for which you want to view sgpa or write \'0\' to go back: ");
                    Scanner scnn = new Scanner(System.in);
                    int sem = scnn.nextInt();

                    if(sem == 0) break;

                    _student.view_sgpa(sem);
                }
            } else if (action.equals("2")) _student.view_cgpa();
        }
    }

    public static void student_function(Student _student) {
        while(true) {
            System.out.println("Enter:\n0 - Go back\n1 - View available Courses\n2 - Register for Courses\n3 - View details and schedule for registered courses\n4 - Track Academic Progress\n5 - Drop Courses\n6 - Submit New Complaint\n7 - View status of your complaints\n8 - Give Course Feedback");
            Scanner scn = new Scanner(System.in);
            String action = scn.nextLine();

            if(action.equals("0")) break;
            else if(action.equals("1")) view_course(_student);
            else if(action.equals("2")) _student.register_for_courses();
            else if(action.equals("3")) _student.view_schedule();
            else if(action.equals("4")) track_acadp(_student);
            else if(action.equals("5")) _student.drop_course();
            else if(action.equals("6")) _student.issue_complaint();
            else if(action.equals("7")) _student.view_complaints();
            else if(action.equals("8")) _student.give_feedback();
            else System.out.println("Incorrect Entry! Try Again.");
        }
    }

    public static void prof_function(Professor _prof) {
        while (true) {
            System.out.println("Enter:\n0 - Go back\n1 - View Course\n2 - Manage Course\n3 - View Enrolled Students\n4 - Update Student Records\n5 - View Feedback");
            Scanner scn = new Scanner(System.in);
            String action = scn.nextLine();

            if(action.equals("0")) break;
            else if(action.equals("1")) view_course(_prof);
            else if(action.equals("2")) course_edit(_prof);
            else if(action.equals("3")) {
                for(Student _student: _prof.get_course().get_student_list()) {
                    System.out.printf("Student name: %s   ", _student.name);
                    System.out.printf("Roll no.: %d   ", _student.get_rno());
                    System.out.printf("Email: %s   ", _student.email);
                    System.out.printf("Contact: %s   ", _student.contact_no);
                    if(_student.course_completed(_prof.get_course().get_course_code())) System.out.printf("GPA in your course: %.2f   ", _student.get_gpa(_prof.get_course().get_course_code()));
                    _student.view_cgpa();
                }
            }else if(action.equals("4")) student_rec_update(_prof);
            else if(action.equals("5")) view_list(_prof.get_course().get_feedback());
        }
    }

    public static void admin_function(Admin _admin) {
        while(true) {
            System.out.println("Enter:\n0 - Go back\n1 - View and Manage Course Catalog\n2 - Manage Student Record\n3 - Assign professor to course\n4 - Handle Complaintsn\n5 - Change Course Drop Deadline\n6 - Assign TA to courses");
            Scanner scn = new Scanner(System.in);
            String action = scn.nextLine();

            if(action.equals("0")) break;
            else if(action.equals("1")) {
                while(true) {
                    System.out.println("Enter:\n0 - Go back\n1 - View Courses\n2 - Update Course Catalog");
                    Scanner scnn = new Scanner(System.in);
                    String act = scnn.nextLine();

                    if(act.equals("0")) break;
                    else if(act.equals("1")) view_course(_admin);
                    else if(act.equals("2")) course_edit(_admin);
                    else System.out.println("Incorrect Entry! Try Again.");
                }
            }
            else if(action.equals("2")) student_rec_update(_admin);
            else if(action.equals("3")) _admin.assign_prof();
            else if(action.equals("4")) {
                while(true) {
                    System.out.println("Enter:\n0 - Go back\n1 - View Complaints\n2 - Change complaint status\n");
                    Scanner scnn = new Scanner(System.in);
                    String act = scnn.nextLine();

                    if(act.equals("0")) break;
                    else if(act.equals("1")) Admin.view_complaints();
                    else if(act.equals("2")) _admin.change_complaint_status();
                    else System.out.println("Incorrect Entry! Try Again.");
                }
            }
            else if(action.equals("5")) _admin.change_deadline();
            else if(action.equals("6")) _admin.assign_TA();
        }
    }

    public static void TA_function(TA _TA) {
        while(true) {
            System.out.println("Enter:\n0 - Go back\n1 - View Courses Available for Registration\n2 - Register for Courses\n3 - View details and schedule for registered courses\n4 - Track Academic Progress\n5 - Drop Courses\n6 - Submit New Complaint\n7 - View status of your complaints\n8 - Give Course Feedback\n9 - View student details\n10 - Update Student Grades");
            Scanner scn = new Scanner(System.in);
            String action = scn.nextLine();

            if(action.equals("0")) break;
            else if(action.equals("1")) view_course(_TA);
            else if(action.equals("2")) _TA.register_for_courses();
            else if(action.equals("3")) _TA.view_schedule();
            else if(action.equals("4")) track_acadp(_TA);
            else if(action.equals("5")) _TA.drop_course();
            else if(action.equals("6")) _TA.issue_complaint();
            else if(action.equals("7")) _TA.view_complaints();
            else if(action.equals("8")) _TA.give_feedback();
            else if(action.equals("9")) _TA.view_students();
            else if(action.equals("10")) _TA.update_student_rec();
            else System.out.println("Incorrect Entry! Try Again.");
        }
    }

    public static void sign_up() {
        while(true) {
            System.out.println("Enter:\n0 - Go back\n1 - Sign-up for student\n2 - Sign-up for professor\n3 - Sign-up for Admin\n4 - Sign-up for TA");
            Scanner scn = new Scanner(System.in);
            String action = scn.nextLine();

            if (action.equals("0")) return;
            System.out.println("Enter Full name: ");
            String name = scn.nextLine();
            System.out.println("Enter email: ");
            String email = scn.nextLine();
            System.out.println("Enter contact no.: ");
            String cno = scn.nextLine();
            System.out.println("Enter password: ");
            String pwd = scn.nextLine();

            Student _student;
            Professor _prof;
            Admin _admin;
            TA _ta;
            if (action.equals("1")) {
                _student = new Student(name, email, cno, pwd);
                System.out.println("Sign-up Successfull!");
            }
            else if (action.equals("2")) {
                _prof = new Professor(name, email, cno, pwd);
                System.out.println("Sign-up Successfull!");
            }
            else if (action.equals("3")) {
                if(pwd.equals(Users.get_pwd())) {
                    _admin = new Admin(name, email, cno, pwd);
                    System.out.println("Sign-up Successfull!");
                }
                else System.out.println("Incorrect password for Admin! Try Again.");
            }
            else if (action.equals("4")) {
                _ta = new TA(name, email, cno, pwd);
                System.out.println("Sign-up Successfull!");
            }
            else System.out.println("Incorrect Entry! Try Again");
        }
    }

    public static void login() {
        while(true) {
            try {
                System.out.println("Enter:\n0 - Go back\n1 - Login as Admin\n2 - Login as Student\n3 - Login as Professor\n4 - Login as TA");
                Scanner scn = new Scanner(System.in);
                String action = scn.nextLine();

                if (action.equals("0")) return;
                System.out.println("Enter email or contact no.: ");
                String username = scn.nextLine();
                System.out.println("Enter password: ");
                String pwd = scn.nextLine();

                if (action.equals("1")) {
                    boolean user_exists = false;
                    for (User u : Users.get_users()) {
                        if ((u.email.equals(username) || u.contact_no.equals(username)) && pwd.equals("x2@jh")) {
                            user_exists = true;
                            break;
                        }
                    }

                    if (!user_exists) throw new InvalidLoginException("User not found! Try Again or sign-up first!");

                    user_exists = false;
                    Admin _admin = new Admin();
                    for (Admin u : Admin.get_admins()) {
                        if ((u.email.equals(username) || u.contact_no.equals(username)) && pwd.equals("x2@jh")) {
                            _admin = u;
                            user_exists = true;
                            break;
                        }
                    }

                    if (!user_exists) throw new InvalidLoginException("User not found! Try Again or sign-up first!");

                    System.out.println("Logged in as: " + _admin.name);
                    admin_function(_admin);
                } else if (action.equals("2")) {
                    boolean user_exists = false;
                    for (User u : Users.get_users()) {
                        if ((u.email.equals(username) || u.contact_no.equals(username)) && u.password.equals(pwd)) {
                            user_exists = true;
                            break;
                        }
                    }

                    if (!user_exists) throw new InvalidLoginException("User not found! Try Again or sign-up first!");

                    user_exists = false;
                    Student _student = new Student();
                    for (Student u : Admin.get_students()) {
                        if ((u.email.equals(username) || u.contact_no.equals(username)) && u.password.equals(pwd)) {
                            _student = u;
                            user_exists = true;
                            break;
                        }
                    }

                    if (!user_exists) throw new InvalidLoginException("User not found! Try Again or sign-up first!");

                    System.out.println("Logged in as: " + _student.name);
                    student_function(_student);
                } else if (action.equals("3")) {
                    boolean user_exists = false;
                    for (User u : Users.get_users()) {
                        if ((u.email.equals(username) || u.contact_no.equals(username)) && u.password.equals(pwd)) {
                            user_exists = true;
                            break;
                        }
                    }

                    if (!user_exists) throw new InvalidLoginException("User not found! Try Again or sign-up first!");

                    user_exists = false;
                    Professor _prof = new Professor();
                    for (Professor u : Admin.get_profs()) {
                        if ((u.email.equals(username) || u.contact_no.equals(username)) && u.password.equals(pwd)) {
                            _prof = u;
                            user_exists = true;
                            break;
                        }
                    }

                    if (!user_exists) throw new InvalidLoginException("User not found! Try Again or sign-up first!");

                    System.out.println("Logged in as: " + _prof.name);
                    prof_function(_prof);
                } else if (action.equals("4")) {
                    boolean user_exists = false;
                    for (User u : Users.get_users()) {
                        if ((u.email.equals(username) || u.contact_no.equals(username)) && u.password.equals(pwd)) {
                            user_exists = true;
                            break;
                        }
                    }

                    if (!user_exists) throw new InvalidLoginException("User not found! Try Again or sign-up first!");

                    user_exists = false;
                    TA _TA = new TA();
                    for (TA u : Admin.get_tas()) {
                        if ((u.email.equals(username) || u.contact_no.equals(username)) && u.password.equals(pwd)) {
                            _TA = u;
                            user_exists = true;
                            break;
                        }
                    }

                    if (!user_exists) throw new InvalidLoginException("User not found! Try Again or sign-up first!");

                    System.out.println("Logged in as: " + _TA.name);
                    TA_function(_TA);
                }
            }catch (InvalidLoginException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        initialize();

        while(true) {
            System.out.println("Enter:\n0 - Exit\n1 - Login\n2 - Sign-up");
            Scanner scn = new Scanner(System.in);
            String action = scn.nextLine();

            if(action.equals("0")) return;
            else if(action.equals("1")) login();
            else if(action.equals("2")) sign_up();
            else System.out.println("Incorrect Entry! Try Again.");
        }
    }
}
