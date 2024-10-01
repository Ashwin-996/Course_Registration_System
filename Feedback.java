public class Feedback <T> {
    private T feedback;

    public Feedback(T feedback) {
        this.feedback = feedback;
    }

    public T get_feedback() {
        return feedback;
    }
}