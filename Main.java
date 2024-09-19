import java.util.Scanner;

public class Main {

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

        _student = new Student("Ashwin Singh", "ashwin@iiitd.ac.in", "7000909901", "Ashwin");
        _student = new Student("Preeti Singh", "preeti@iiitd.ac.in", "9981705151", "Preeti");
        _student = new Student("Ayush Singh", "ayush@iiitd.ac.in", "7000909900", "Ayush");

        _prof = new Professor("Suyash Singh", "suyash@iiitd.ac.in", "8827740839", "Suyash");
        _prof = new Professor("Vivek Singh", "vivek@iiitd.ac.in", "7000909902", "Vivek");

        _admin = new Admin("Subodh Singh", "subodh@iiitd.ac.in", "9425250151", "x2@jh");
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
            System.out.println("Enter:\n0 - Go back\n1 - View available Courses\n2 - Register for Courses\n3 - View details and schedule for registered courses\n4 - Track Academic Progress\n5 - Drop Courses\n6 - Submit New Complaint\n7 - View status of your complaints");
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
            else System.out.println("Incorrect Entry! Try Again.");
        }
    }

    public static void prof_function(Professor _prof) {
        while (true) {
            System.out.println("Enter:\n0 - Go back\n1 - View Course\n2 - Manage Course\n3 - View Enrolled Students\n4 - Update Student Records");
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
        }
    }

    public static void admin_function(Admin _admin) {
        while(true) {
            System.out.println("Enter:\n0 - Go back\n1 - View and Manage Course Catalog\n2 - Manage Student Record\n3 - Assign professor to course\n4 - Handle Complaints");
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
                    System.out.println("Enter:\n0 - Go back\n1 - View Complaints\n2 - Change complaint status");
                    Scanner scnn = new Scanner(System.in);
                    String act = scnn.nextLine();

                    if(act.equals("0")) break;
                    else if(act.equals("1")) Admin.view_complaints();
                    else if(act.equals("2")) _admin.change_complaint_status();
                    else System.out.println("Incorrect Entry! Try Again.");
                }
            }
        }
    }

    public static void sign_up() {
        while(true) {
            System.out.println("Enter:\n0 - Go back\n1 - Sign-up for student\n2 - Sign-up for professor ");
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
            else System.out.println("Incorrect Entry! Try Again");
        }
    }

    public static void login() {
        while(true) {
            System.out.println("Enter:\n0 - Go back\n1 - Login as Admin\n2 - Login as Student\n3 - Login as Professor");
            Scanner scn = new Scanner(System.in);
            String action = scn.nextLine();

            if (action.equals("0")) return;
            System.out.println("Enter email or contact no.: ");
            String username = scn.nextLine();
            System.out.println("Enter password: ");
            String pwd = scn.nextLine();

            if(action.equals("1")) {
                boolean user_exists = false;
                for (User u : Users.get_users()) {
                    if ((u.email.equals(username) || u.contact_no.equals(username)) && pwd.equals("x2@jh")) {
                        user_exists = true;
                        break;
                    }
                }

                if (!user_exists) {
                    System.out.println("User not found! Try Again or sign-up first!");
                    continue;
                }

                user_exists = false;
                Admin _admin = new Admin();
                for(Admin u: Admin.get_admins()) {
                    if ((u.email.equals(username) || u.contact_no.equals(username)) && pwd.equals("x2@jh")) {
                        _admin = u;
                        user_exists = true;
                        break;
                    }
                }

                if(!user_exists) {
                    System.out.println("User does not exist!");
                    continue;
                }

                System.out.println("Logged in as: " + _admin.name);
                admin_function(_admin);
            }
            else if (action.equals("2")) {
                boolean user_exists = false;
                for (User u : Users.get_users()) {
                    if ((u.email.equals(username) || u.contact_no.equals(username)) && u.password.equals(pwd)) {
                        user_exists = true;
                        break;
                    }
                }

                if (!user_exists) {
                    System.out.println("User not found! Try Again or sign-up first!");
                    continue;
                }

                user_exists = false;
                Student _student = new Student();
                for(Student u: Admin.get_students()) {
                    if ((u.email.equals(username) || u.contact_no.equals(username)) && u.password.equals(pwd)) {
                        _student = u;
                        user_exists = true;
                        break;
                    }
                }

                if(!user_exists) {
                    System.out.println("User does not exist!");
                    continue;
                }

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

                if (!user_exists) {
                    System.out.println("User not found! Try Again or sign-up first!");
                    continue;
                }

                user_exists = false;
                Professor _prof = new Professor();
                for (Professor u : Admin.get_profs()) {
                    if ((u.email.equals(username) || u.contact_no.equals(username)) && u.password.equals(pwd)) {
                        _prof = u;
                        user_exists = true;
                        break;
                    }
                }

                if(!user_exists) {
                    System.out.println("User does not exist!");
                    continue;
                }

                System.out.println("Logged in as: " + _prof.name);
                prof_function(_prof);
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