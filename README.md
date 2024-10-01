# Instruction to run code:
Run the following commands in the same order:
<pre>
      javac Main.java
      java Main
</pre>


# Assumptions:
* Professors won't change the following fields after students have registered: prerequisites, enrollment limit and credits.
* Two courses cannot have the same course code.
* Grade for each course is an integer from 0-10. There is no I or W grade since there is no late drop system, the student can drop any registered course normally throughout the duration of the semester.
* Student fails a course if grade in that course is less than 4.
* A student cannot go to the next semester unless he passes all registered courses for this sem i.e. gets a grade of 4.0 or above in all registered courses.
* Professor or Admin will assign grades only when the semester is over. Also, once the grades have started being assigned meaning the semester is over, students cannot drop courses, they can only do so while the semester in ongoing.


# How OOPs concepts are applied:
* <b><ins>Classes</ins></b> - Classes have been used to reduce redundancy and help in reusing code through a blueprint. In the assignment, all objects of the student class will be able to use its functions such as view courses, view_cgpa etc. Similarly, all objects of the professor class will have access to all functionalities inside the professor class and same for the other classes.
* <b><ins>Inheritance</ins></b> - The User class is extended by the Student, Professor and Admin class since all three are users and are going to have login info details common between them which is maintained by the User class.
* <b><ins>Abstraction</ins></b> - The database of all users is hidden from the client and the class Users is abstract.
* <b><ins>Polymorphism</ins></b> :
  * Overriding -
    * The Professor and Admin class implement the Edit interface which has two methods (edit_courses and update_student_rec) both overriden by the Professor and the Admin class.
    * The Professor, Admin and Student class implement the View interface and override the view_courses method.
  * Overloading -
    * The view_course function in main is defined so as to take all three Professor, Student and Admin objects as parameters.
    * The edit_course and student_rec_update in main is defined so as to take both Professor and Admin objects as paramters.
* <b><ins>Interfaces</ins></b>
  * The Edit interface has two methods edit_courses and update_student_rec which is implemented by Professor and Admin class as both have these common functionalities.
  * The View interface has one method view_courses which is implemented by Professor, Student and Admin as all three have this common functionality.
* <b><ins>Generic programming</ins></b> - The Users database allows adding of users in the database through a function for which objects of all classes that extend the User class are allowed as parameters. The Feedback class is a generic class which allows students to give feedback for courses in the form of numeric rating or a description.
* <b><ins>Exception Handling</ins></b> - Three custom exception classes are created for handling the following :
      * Invalid Login - If a user enters invalid credentials while logging in, this exception is thrown.
      * Course Full - If a student tries to register for a course which has already reached its cap for enrollments, this exception is thrown.
      * Drop Deadline Passed - If a student tries to drop courses after the drop deadline has passed, this exception is thrown.
* <b><ins> Object class</ins></b> - The toString method of the Object Class is used to print course details for any course in the catalog. The instanceOf keyword is also paired with generic programming to help the professor view different kinds of feedback together. 
* <b><ins>Encapsulation</ins></b> - For each class private variables with respective accessors and mutators have been created to enforce encapsulation. The class wise breakdown for encapsulation is as follows:
  * ### Admin class:
    * ###### Private variables:
      * profs - A list of all Professors registered in the system.
      * students - A list of all students registered in the system.
      * admins - A list of all admins registered in the system.
      * complaints - A list of all issued complaints.
    * ###### Getters:
      * get_students() - returns students variable.
      * get_profs() - returns profs variable
      * get_admins() - returns admins variable
  * ### Professor class:
    * ###### Private variables:
      * myCourse - An object of the Course class containing details of the course the professor will be offering.
      * office_hours - A string for the professor's office hours.
    * ###### Getters:
      * get_course() - returns myCourse variable
      * get_oh() - returns office_hours variable
    * ###### Setters:
      * set_course(Course) - sets the myCourse variable
  * ### Course class:
    * ###### Private Variables:
      * course_code - string for the course code of the course
      * title - string for the title of the course
      * prerequisites - string for course prerequisites
      * credits - integer for credits of the course
      * schedule - the weekly timings and locations of the classes for the course
      * enrollment_limit - integer for the cap on the number of students in the course
      * prof - An object of the Professor class for details of the Professor who will be offering this course
      * student_list - an array of Student objects for all students who have registered for the course
      * semesters - a list of semesters in which the course is being offered
      * syllabus - a string of topics that will be taught in the course
    * ###### Getters:
      * get_schedule() - returns the schedule variable
      * get_course_code() - returns the course code variable
      * get_prof() - returns the prof variable
      * get_credits() - returns the credits variable
      * get_prerequisites() - returns prerequisites as an array of strings
      * get_student_list() - returns the student_list variable
      * get_enrollment_limit() - returns the enrollment_limit variable
    * ###### Setters:
      * set_prof(Professor) - sets the prof variable
      * set_schedule(String) - sets the schedule variable
      * set_credits(Integer) - sets the credits variable
      * set_prerequisites(String) - sets the prerequisites variable
      * set_course_code(String) - sets the course_code variable
  * ### Student class:
    * ###### Private variables:
      * registered_courses - An array of lists containing courses registered for each semester
      * current_sem - int for semester in which student is currently in
      * completed_courses_gpa - GPA for all the courses that have been completed by the student
      * sem_courses - courses available for the current semester
      * myComplaints - An array of issues raised by the student
      * rno - Roll number of the student
    * ###### Getters:
      * get_rno()
  * ### Courses class:
    * ###### Private variables:
      * sems - An array of lists storing all courses offered in the institute semester-wise
  * ### Users class:
    * ###### Private Variables:
      * Admin_pwd - string containing the fixed password required for admin login
      * users - an array of all users signed up in the system
    * ###### Getters:
      * get_users() - returns the users variable
      * get_pwd() - returns the Admin_pwd variable
  * ### User class:
    * ###### Protected variables:
      * name - string for name of user
      * email - string for email of user
      * contact_no - string for contact of user
      * password - string for password of user
  * ### Complaint class:
    * ###### Private variables:
      * status - status of the complaint
      * description - string for description of the complaint
      * complaint_id - a unique integer id for each complaint
    * ###### Getters:
      * get_id() - returns the complaint_id variable
      * get_description() - returns the description variable
      * view_status() - returns the status variable
    * ###### Setters:
      * set_status(Status) - sets the status variable

# Brief overview of functions in some classes:
* ### Professor class -
  * <b>edit_courses</b> - This function allows the professor to update the following course details - Syllabus, Class timings and Locations, Credits, Prerequisites, Enrollment limit, Office hours, Course code
  * <b>update_student_rec</b> - Update grades of students registered for the course offered by the professor
* ### Admin class -
  * <b>view_complaints</b> - View all the complaints and their details with the option of viewing only each type of complaint
  * <b>change_complaint_status</b> - change the status of a complaint to pending or resolved
  * <b>edit_courses</b> - Add and Remove courses from the course catalog
  * <b>update_student_rec</b> - Update details of any student such as their name, email, contact no., password and grades
* ### Student class -
  * <b>update_gpa</b> - Assign or update gpa for a completed course
  * <b>inc_sem</b> - Move student to the next semester 
  * <b>view_schedule</b> - View all details of registered courses including weekly class timings and locations.
  * <b>register_for_courses</b> - register for courses for the ongoing semester
  * <b>drop_course</b> - drop course in the ongoing semester
  * <b>view_sgpa</b> - View sgpa of any completed semester
  * <b>view_cgpa</b> - View cgpa of the student up till the latest completed semester
  * <b>issues_complaint</b> - Issue a new complaint
* ### Main class:
  * <b>initialize</b> - Sign-up three students, two professors and one admin
  * <b>sign_up</b> - Allow user to sign-up in the system
  * <b>login</b> - Allow user to login into the system
* ### TA class -
  * <b>update_student_rec</b> - Update grades of students registered for the course for which one is a TA
  * <b>view_students</b> - View details and grades of students registered for the course for which one is a TA


# Github link - https://github.com/Ashwin-996/AP_Assignment_1.git
