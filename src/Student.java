import java.time.Month;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;

public class Student extends User {
    // login ke liye id pass
    private static final String[] usercheck = {"student1","student2","student3"};
    private static final String[] passcheck = {"123","pass","pizzazz"};



    public static ArrayList<String[]> regCoursesFinal = new ArrayList<>();
    public static ArrayList<Complaint> complaints = new ArrayList<>();

    public static ArrayList<Feedback<?>> feedbackList = new ArrayList<>();

    public static class Complaint {
        private String description;
        private String status;

        public Complaint(String description) {
            this.description = description;
            this.status = "Pending"; // Default status
        }

        public String getDescription() {
            return description;
        }

        public String getStatus() {
            return status;
        }

        public void resolve() {
            this.status = "Resolved";
        }

        @Override
        public String toString() {
            return "Complaint: " + description + " | Status: " + status;
        }
    }


    public static void run() {
        Scanner scanner = new Scanner(System.in);

        try (scanner) {
            System.out.println("\n----------------------------------------");
            System.out.println("|      Namaskar this is Student Portal!     |");
            System.out.println("----------------------------------------\n");
            System.out.print("type your user: ");
            String user = scanner.nextLine();
            System.out.print("type your password : ");
            String key  = scanner.nextLine();
            if (login(user, key )) {
                System.out.println("login successful.");
                StartMenu();
            }
        } catch (NewException.InvalidLoginException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void StartMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n----------------------------------------");
            System.out.println("|            STUDENT MENU              |");
            System.out.println("----------------------------------------\n");
            System.out.println("1.list all courses");
            System.out.println("2.register for Courses");
            System.out.println("3.view schedule");
            System.out.println("4.show progress");
            System.out.println("5.drop Courses");
            System.out.println("6.make a complaint");
            System.out.println("7.submit feedback");
            System.out.println("8.logout");
            System.out.print("type any one of these (1/2/3/4/5/6/7/8): ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showCourses();
                    break;
                case 2:
                    registerCourse(scanner);
                    break;
                case 3:
                    schedule();
                    break;
                case 4:

                    break;
                case 5:
                    dropCourse(scanner);
                    break;
                case 6:
                    makecomplain(scanner);

                    break;
                case 7:
                    feedbackForm(scanner);


                    main(null);
                    return;
                case 8:
                    System.out.println("Logging out...");


                    main(null);
                    return;
                default:
                    System.out.println("wrong :( Please type 1, 2, or 3.");
            }

        } while (true);
    }


    private static void registerCourse(Scanner scanner) {
        showCourses();
        System.out.print("type the index of subject you want to register: ");
        int subjectno=scanner.nextInt();
        scanner.nextLine();

        String[][] newCourse=User.mycourse();
        try {
            if (subjectno>=0 && subjectno<newCourse.length) {
                if (User.isCourseFull(subjectno)) {
                    throw new NewException.CourseFullException("Course is full. Cannot register.");
                }
                regCoursesFinal.add(newCourse[subjectno]);
                User.registerCourse(subjectno);
                System.out.println("Course registered successfully! YAY :)");
                showregCoursesFinal();
            } else {
                System.out.println("wrong course index. (Note: the first subject has an index of 0)");
            }
        } catch (NewException.CourseFullException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void dropCourse(Scanner scanner) {
        if (regCoursesFinal.isEmpty()) {
            System.out.println("No registered courses to drop.");
            return;
        }

        try {
            checkdropdead ();

            showregCoursesFinal();
            System.out.print("type the index of the course you want to drop: ");
            int subjectno = scanner.nextInt();
            scanner.nextLine();

            if (subjectno >= 0 && subjectno < regCoursesFinal.size()) {
                regCoursesFinal.remove(subjectno);
                System.out.println("Course dropped successfully!");
            } else {
                System.out.println("wrong course index. Please try again. (Note: the first subject has an index of 0)");
            }
        } catch (NewException.DropDeadlinePassedException e) {
            System.out.println(e.getMessage());
        }
    }
    private static final LocalDate dropDeadline=LocalDate.of(2024, Month.OCTOBER, 30);
    private static void checkdropdead () throws NewException.DropDeadlinePassedException{
        LocalDate currentDate=LocalDate.now();
        if (currentDate.isAfter(dropDeadline)){
            throw new NewException.DropDeadlinePassedException("DEADLINE PASSED");
        }
    }


    public static void showregCoursesFinal() {
        if (regCoursesFinal.isEmpty()) {
            System.out.println("\nNo courses registered yet.");
            return;
        }

        System.out.println("\n         ****** ****   *already registereed courses*    ***** ******\n");
        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.println("Code     | name of the course               | instructor  | credits   | prereq   | time ");
        System.out.println("----------------------------------------------------------------------------------------------------------");
        for (String[] course : regCoursesFinal) {
            System.out.printf("%-8s|%-31s|%-10s|%-9s|%-15s |%s\n",course[0],course[2],course[3],course[4],course[5],course[6]);
        }
        System.out.println("----------------------------------------------------------------------------------------------------------");
    }



    public static void showCourses() {
        String[][] courses=User.mycourse();
        System.out.println("\n         **********     Available Courses for the Current Semester:      ***********\n");
        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.println("Code     | Title                          | instructor  | Credits   | Prerequisites   | Timings");
        System.out.println("----------------------------------------------------------------------------------------------------------");
        for (String[] course : courses) {
            System.out.printf("%-8s|%-31s|%-10s |%-9s|%-15s|%s\n",course[0],course[2],course[3],course[4],course[5],course[6]);
        }
        System.out.println("----------------------------------------------------------------------------------------------------------");
    }

    public static void schedule() {

        if (regCoursesFinal.isEmpty()) {
            System.out.println("\nNo courses registered yet.");
            return;
        }
        System.out.println("\n         ********     * *class schedule* *     *********\n");
        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.println("Sub_code     |title                          |instructor         |timings                    |classroom          ");
        System.out.println("----------------------------------------------------------------------------------------------------------");
        for (String[] course : regCoursesFinal) {
            System.out.printf("%-8s|%-31s|%-18s%-24s|%s\n",course[0],course[2],course[3],course[6],course[8]);
        }
        System.out.println("----------------------------------------------------------------------------------------------------------");
    }
    public static void makecomplain(Scanner scanner) {
        System.out.print("Type a description of your complaint: ");
        String description = scanner.nextLine();
        Complaint complaint = new Complaint(description); // Using the inner Complaint class
        complaints.add(complaint);
        System.out.println("Complaint submitted successfully. Status: Pending");
    }

    private static void feedbackForm(Scanner scanner) {
        if (regCoursesFinal.isEmpty()) {
            System.out.println("complete a course first");
            return;}

        showregCoursesFinal();
        System.out.print("type the index of the course you want to give feedback for: ");
        int subjectno = scanner.nextInt();
        scanner.nextLine();
        if (subjectno>= 0 && subjectno<regCoursesFinal.size()) {
            String courseCode = regCoursesFinal.get(subjectno)[0];
            System.out.println("You selected: " + courseCode);
            System.out.print("type your rating out of 5:");
            int rate = scanner.nextInt();scanner.nextLine();
            System.out.print("anything about the course");
            String comment = scanner.nextLine();
            feedbackList.add(new Feedback<>(rate, courseCode));
            feedbackList.add(new Feedback<>(comment, courseCode));
            System.out.println("feedback sent.");
        }else {System.out.println("wrong course");}
    }




    private static boolean login(String user, String key ) throws NewException.InvalidLoginException {
        for (int i = 0; i < usercheck.length; i++) {
            if (user.equals(usercheck[i])&&key .equals(passcheck[i])){return true;}}
        throw new NewException.InvalidLoginException("wrong id pass.try again.");
    }
}
