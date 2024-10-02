import java.util.ArrayList;

public class Course {
    private String course_code, title, prerequisites, schedule;
    private ArrayList<String> syllabus;
    private int credits, enrollment_limit;
    private Professor prof;
    private ArrayList<Student> student_list;
    private ArrayList<Integer> semesters;
    private ArrayList<Feedback<?>> allFeedback;
    private ArrayList<TA> tas_list;

    public Course(String course_code, String title, int credits, String prerequisites, String schedule, ArrayList<Integer> semests, int enrollment_limit)
    {
        syllabus = new ArrayList<String>();
        student_list = new ArrayList<Student>();
        tas_list = new ArrayList<TA>();
        allFeedback = new ArrayList<Feedback<?>>();
        this.course_code = course_code;
        this.title = title;
        this.credits = credits;
        this.prerequisites = prerequisites;
        this.schedule = schedule;
        for(int u: semests) Courses.add_course(u, this);
        this.semesters = semests;
        this.enrollment_limit = enrollment_limit;
    }

    public String[] get_schedule() {
        String[] _schedule = schedule.split(" ");
        return _schedule;
    }

    public void add_feedback(Feedback<?> feedback) {
        allFeedback.add(feedback);
    }

    public ArrayList<Feedback<?>> get_feedback() {
        return allFeedback;
    }

    @Override
    public String toString()
    {
        String ret = "";
        ret += "Course code: ";
        if(course_code.equals("")) ret += "Not assigned yet.";
        else ret += course_code;

        ret += "\nCourse Title: ";
        if(title.equals("")) ret += "Not assigned yet.";
        else ret += title;

        ret += "\nCredits: ";
        if(credits == 0) ret += "Not assigned yet.";
        else ret += credits;

        ret += "\nPrerequisites: ";
        if(prerequisites.equals("")) ret += "Not assigned yet.";
        else ret += prerequisites;

        ret += "\nSchedule:";
        String[] _schedule = get_schedule();
        if(_schedule.length == 1) ret += (" Not decided.");
        else {
            int counter = 1;
            for (int i = 0; i < _schedule.length; i += 2) {
                String temp = ("\nClass-" + counter + " : " + _schedule[i] + "   Location: " + _schedule[i + 1]);
                ret += temp;
                counter++;
            }
        }
        if(syllabus.size() == 0) ret += ("\nSyllabus: Not decided.");
        else {
            ret += ("\nSyllabus: ");
            for (int i = 0; i < syllabus.size(); i++) {
                ret += (syllabus.get(i) + "  ");
            }
        }
        if(prof != null) ret += ("\nProfessor: " + prof.name + "   Office hours: " + prof.get_oh());
        else ret += ("\nProfessor: Not assigned yet");

        return (ret + "\n\n\n");
    }

    public String get_course_code()
    {
        return course_code;
    }

    public Professor get_prof() {
        return prof;
    }

    public int get_credits(){
        return credits;
    }

    public String[] get_prerequisites()
    {
        String[] prereq = prerequisites.split(", ");
        return prereq;
    }

    public ArrayList<Student> get_student_list() {
        return student_list;
    }

    public void set_prof(Professor _prof) {
        prof = _prof;
    }

    public void set_schedule(String _schedule) {
        schedule = _schedule;
    }

    public int num_students() {
        return student_list.size();
    }

    public int get_enrollment_limit() {
        return enrollment_limit;
    }

    public void add_student(Student student) {
        student_list.add(student);
    }

    public void add_TA(TA _ta) {
        tas_list.add(_ta);
    }

    public void remove_TA(TA _ta) {
        tas_list.remove(_ta);
    }

    public void remove_student(Student student) {
        student_list.remove(student);
    }

    public void add_topic(String topic) {
        syllabus.add(topic);
    }

    public void remove_from_sem(int sem) {
        for(int i=0; i<semesters.size(); i++) {
            if(semesters.get(i) == sem) semesters.remove(i);
            return;
        }
    }

    public boolean remove_topic(String topic) {
        for(int i=0; i<syllabus.size(); i++) {
            if(syllabus.get(i).equals(topic)) {
                syllabus.remove(i);
                return true;
            }
        }
        return false;
    }

    public void set_credits(int _credits) {
        credits = _credits;
    }

    public void set_prerequisites(String _prerequisites) {
        prerequisites = _prerequisites;
    }

    public void set_enrollment_limit(int _enrollment_limit) {
        enrollment_limit = _enrollment_limit;
    }

    public void set_course_code(String _course_code) {
        course_code = _course_code;
    }
}
