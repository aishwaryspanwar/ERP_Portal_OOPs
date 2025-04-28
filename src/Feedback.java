class Feedback<T> {
    private final T feedbackstud;private final String subno;
    public Feedback(T feedbackstud, String subno) {
        this.feedbackstud = feedbackstud;
        this.subno = subno;}
    @Override
    public String toString(){
        return "Course: "+subno+ " Feedback: "+feedbackstud;}
}
