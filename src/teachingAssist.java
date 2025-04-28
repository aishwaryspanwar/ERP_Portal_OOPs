import java.util.Scanner;


public class teachingAssist extends User{



    public static void run(){
        Scanner scanner=new Scanner(System.in);

        System.out.println("\n----------------------------------------");
        System.out.println("|      namaskar this is TA Portal!       |");
        System.out.println("----------------------------------------\n");
        System.out.print("type your user: ");
        String user=scanner.nextLine();

        System.out.print("type your password: ");
        String password=scanner.nextLine();

        try {
            if (login(user, password)) {
                System.out.println("Login successful.");
                menu();
            }
        } catch (NewException.InvalidLoginException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void menu(){
        Scanner scanner=new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n----------------------------------------");
            System.out.println("|              TA MENU                 |");
            System.out.println("----------------------------------------\n");
            System.out.println("1. View Registered Courses");
            System.out.println("2. View Student Grades");
            System.out.println("3. Update Student Grades");
            System.out.println("4. Logout");
            System.out.print("type any one(1/2/3/4): ");
            choice=scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Student.showregCoursesFinal();
                    break;
                case 2:
                    studGrades();
                    break;
                case 3:
                    editgrades(scanner);
                    break;
                case 4:
                    System.out.println("Logging out...");
                    System.out.println("...");
                    System.out.println("...");
                    main(null);
                default:
                    System.out.println("wrong choice! Please type 1, 2, 3, or 4.");
            }}while(choice!=4);
        scanner.close();
    }


    private static void studGrades() {
        if (Student.regCoursesFinal.isEmpty()) {
            System.out.println("register a subject first");
            return;
        }

        System.out.println("\n********** view Student Grades **********");
        System.out.println("--------------------------------------------------------------");
        System.out.println("Code     | Title                          | Grade            |");
        System.out.println("---------------------------------------------------------------");

        for (String[] course : Student.regCoursesFinal) {
            String courseCode=course[0];
            String courseTitle=course[2];
            String grade=(course.length > 10) ? course[10] : "Not Graded";
            System.out.printf("%-8s|%-34s|%s\n",courseCode,courseTitle,grade);
        }

        System.out.println("---------------------------------------------------------------");
    }

    private static void editgrades(Scanner scanner) {
        if (Student.regCoursesFinal.isEmpty()) {
            System.out.println("No courses registered to update grades.");
            return;
        }

        Student.showregCoursesFinal();
        System.out.print("type the id of subject to update grade: ");
        int subjectno=scanner.nextInt();
        scanner.nextLine();

        if (subjectno>=0 && subjectno<Student.regCoursesFinal.size()){
            System.out.print("type new grade for the student: ");
            String newGrade=scanner.nextLine();
            String[] course=Student.regCoursesFinal.get(subjectno);

            if (course.length>10) {
                course[10]=newGrade;}else {
                String[] updatedCourse=new String[11];
                System.arraycopy(course, 0, updatedCourse, 0, course.length);
                updatedCourse[10]=newGrade;
                Student.regCoursesFinal.set(subjectno, updatedCourse);
            }

            System.out.println("Grade updated successfully.");
        } else {
            System.out.println("woring course index.");
        }
    }



    private static final String[] usercheck={"ta1", "ta2", "t3"};
    private static final String[] passcheck={"123", "123", "123"};
    private static boolean login(String user, String password) throws NewException.InvalidLoginException{
        for (int i=0;i<usercheck.length;i++){
            if (user.equals(usercheck[i])&&password.equals(passcheck[i])){return true;}
        }throw new NewException.InvalidLoginException("wrong id pass.try again.");}
}
