import java.util.Scanner;

public class Professor extends User{




    public static final String[] enrolledStudents = {"Rahul Dravid", "Virat Kholi", "Tom Holland", "Dua Lipa", "Kabir Jain", "Dhruv rathee", "Elvish Yadav"};



    public static void run() {
        Scanner scanner = new Scanner(System.in);


        System.out.println("\n----------------------------------------");
        System.out.println("    namaskar this is instructor portal   ");
        System.out.println("----------------------------------------\n");
        System.out.print("Enter your Username: ");
        String user = scanner.nextLine();

        System.out.print("Enter your pass: ");
        String key = scanner.nextLine();


        try {
            if (login(user, key)) {
                System.out.println("login successful.");
                StartMenu();
            }
        } catch (NewException.InvalidLoginException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void StartMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n----------------------------------------");
            System.out.println("          instructor MENU                |");
            System.out.println("----------------------------------------\n");
            System.out.println("1. edit courses");
            System.out.println("2. show enrolled students");
            System.out.println("3.look at student feedbacks");
            System.out.println("4.logout");
            System.out.print("type your choice (1/2/3/4): ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    managecourse(scanner);
                    break;
                case 2:
                    viewEnrolledStudents();
                    break;
                case 3:
                    viewFeedback();
                    break;
                case 4:
                    System.out.println("Logging out...");
                    System.out.println("...");
                    System.out.println("...");
                    main(null);
                    return;
                default:
                    System.out.println("wrong  input Please enter 1, 2, or 3.");
            }

        } while (true);
    }



    public static void showCourses() {
        String[][] courses = Student.mycourse();

        System.out.println("\nCourse Names Offered:");
        System.out.println(" Title                        | Course code   | Syllabus                                                      |Timings                   |Enrollment Limit");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (String[] course : courses) {
            System.out.printf("%-31s|%-14s|%-60s|%-25s|%s\n",course[2],course[0],course[1],course[6],course[7]);
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
    }




    private static void managecourse(Scanner scanner) {
        System.out.println("\nManage course:");

        showCourses();

        System.out.print("\nEnter the index of the course you want to update: ");
        int course_num = scanner.nextInt();
        scanner.nextLine();

        if (course_num >= 0 && course_num < Student.mycourse().length) {
            String[] sub = Student.mycourse()[course_num];
            System.out.println("\nCurrent details of the selected course:");
            System.out.println("Course Code: "+ sub[0]);
            System.out.println("syllabus: "+sub[1]);
            System.out.println("subject Name: "+sub[2]);
            System.out.println("teacher: "+sub[3]);
            System.out.println("credits: "+sub[4]);
            System.out.println("prereq: "+sub[5]);
            System.out.println("time: "+sub[6]);
            System.out.println("enroll limit: "+sub[7]);

            System.out.println("\nDo you want to update course details? (yes/no): ");
            String updateChoice = scanner.nextLine();

            if (updateChoice.equalsIgnoreCase("yes")) {
                System.out.println("\n--------------------------------------------------------");
                System.out.println("          What do you want to change?");
                System.out.println("--------------------------------------------------------\n");
                System.out.println("1. Syllabus");
                System.out.println("2. Timings");
                System.out.println("3. Enrollment limit");
                System.out.println("type -1 to go back");
                System.out.print("type 1,2,3: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter new syllabus: ");
                        sub[1] = scanner.nextLine();
                        break;
                    case 2:
                        System.out.print("Enter new timings: ");
                        sub[6] = scanner.nextLine();
                        break;
                    case 3:
                        System.out.print("Enter new enrollment limit: ");
                        sub[7] = scanner.nextLine();
                        break;
                    default:
                        System.out.println("wrong  choice. Please select 1, 2, or 3.");
                        return;
                }


                Student.setCourse(course_num, sub);
                System.out.println("\nCourse details updated successfully!");
                showCourses();
            } else {
                System.out.println("No changes made to course details.");
            }
        } else {
            System.out.println("wrong  course index.");
        }
    }
    private static void viewFeedback() {
        if (Student.feedbackList.isEmpty()) {
            System.out.println("No feedbacks available.");
        } else {
            System.out.println("\n********* Course Feedback *********\n");
            for (Feedback<?> feedback : Student.feedbackList) {
                System.out.println(feedback);
            }
        }
    }

    private static void viewEnrolledStudents() {
        System.out.println("\n----------------------------------------");
        System.out.println("       List of Enrolled Students: ");
        System.out.println("----------------------------------------\n");
        for (String student : enrolledStudents) {
            System.out.println(" - " + student);
        }
    }
    // login check karne ke liye
    private static final String[] usercheck = {"prof1", "prof2", "prof3"};
    private static final String[] passcheck = {"123", "two", "three"};
    private static boolean login(String user,String key) throws NewException.InvalidLoginException{
        for (int i = 0; i < usercheck.length;i++){
            if(user.equals(usercheck[i])&&key.equals(passcheck[i])){return true;}}
        throw new NewException.InvalidLoginException("wrong id pass.try again.");
    }
}
