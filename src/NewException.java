public class NewException{
    public static class InvalidLoginException extends Exception{
        public InvalidLoginException(String message){super(message);}}
    public static class DropDeadlinePassedException extends Exception {
        public DropDeadlinePassedException(String message){super(message);}}
    public static class CourseFullException extends Exception{
        public CourseFullException(String message){
            super(message);
        }}}
