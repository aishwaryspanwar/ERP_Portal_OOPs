import java.util.Scanner;
public class Administrator extends User{
    public static void run(){
        Scanner scanner=new Scanner(System.in);
        try (scanner) {
            System.out.println("\n-------------------------------------------------");
            System.out.println("    namaskar this is Administration Portal!   ");
            System.out.println("---------------------------------------------------\n");
            System.out.print("type your user: ");
            String user=scanner.nextLine();
            System.out.print("type your password: ");
            String key=scanner.nextLine();
            if(login(user,key)){
                System.out.println("login successful");
                StartMenu();
            }
        }catch(NewException.InvalidLoginException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void StartMenu(){
        Scanner scanner=new Scanner(System.in);
        int choice;
// yeh basic login page hai
        do {
            System.out.println("\n----------------------------------------");
            System.out.println("|            Administrator func          |");
            System.out.println("----------------------------------------\n");
            System.out.println("1.edit course list");
            System.out.println("2.change student info");
            System.out.println("3.assign prof");
            System.out.println("4. Resolve complaints");
            System.out.println("5.logout");
            System.out.print("type your choice (1/2/3/4/5): ");
            choice=scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1:
                    editcourse();
                    break;
                case 2:
                    changeStudentName();
                    break;
                case 3:
                    return;
                case 4:
                    resolveComplain();
                    break;
                case 5:
                    System.out.println("logging out...");
                    System.out.println("...");
                    System.out.println("...");
                    main(null);
                default:
                    System.out.println("wrong input plsType 1,2,3,4,5");
            }

        } while (true);}

//this func will edit subject
    private static void editcourse(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--------------------------------------------");
        System.out.println("|             course techtree                |");
        System.out.println("----------------------------------------------\n");
        System.out.println("1.list all added Courses");
        System.out.println("2. add course");
        System.out.println("3. drop course");
        System.out.println("type -1 for back");
        System.out.print("type 1,2 or 3: ");
        int choice=scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                peekcourse();

                break;
            case 2:
                String[] newCourse=new String[9];
                System.out.println("type course details:");
                System.out.print("Course Code: ");
                newCourse[0]=scanner.nextLine();
                System.out.print("Course Description: ");
                newCourse[1]=scanner.nextLine();
                System.out.print("Course Name: ");
                newCourse[2]=scanner.nextLine();
                System.out.print("instructor: ");
                newCourse[3]=scanner.nextLine();
                System.out.print("Credits: ");
                newCourse[4]=scanner.nextLine();
                System.out.print("prereq: ");
                newCourse[5]=scanner.nextLine();
                System.out.print("Timings: ");
                newCourse[6]=scanner.nextLine();
                System.out.print("Enrollment limit: ");
                newCourse[7]=scanner.nextLine();
                System.out.print("Location: ");
                newCourse[8]=scanner.nextLine();
                addcourse(newCourse);

                System.out.println("                ***********changed database************");

                peekcourse();
                break;

            case 3:
                peekcourse();
                System.out.print("\ntype index of subject to be removed from database: ");
                int Id=scanner.nextInt();
                scanner.nextLine();
                deletecor(Id);
                break;

            case -1:
                System.out.println("returning to the previous menu...");
                return;
            default:
                System.out.println("wrong choice. Please select 1, 2, or 3.");
        }
    }
//this func will show available course
    private static void peekcourse() {
        String[][] courses=User.mycourse();
        System.out.println("\n         **********     (list of all courses)      ***********\n");
        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.println("code     | title                          | instructor  | Credits   | prereq   | time");
        System.out.println("----------------------------------------------------------------------------------------------------------");
        for (String[] course:courses) {
            System.out.printf("%-8s | %-28s|%-60s |%-10s|%-12s | %-7s |%-25s| %-5s|%-5s\n",course[0],course[2],course[1],course[3],course[4], course[5], course[6], course[7], course[8]);
        }
        System.out.println("----------------------------------------------------------------------------------------------------------");
    }

    private static void resolveComplain() {
        if (Student.complaints.isEmpty()) {
            System.out.println("No complaints to resolve.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n********** Submitted Complaints **********\n");
        for (int i = 0;i<Student.complaints.size();i++) {
            Student.Complaint complaint = Student.complaints.get(i);
            System.out.println(i + ". " + complaint);
        }

        System.out.print("\nSelect index of the complaint to resolve(-1 to go back): ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index >= 0 && index < Student.complaints.size()) {
            Student.Complaint complaintToResolve = Student.complaints.get(index);
            if ("Resolved".equals(complaintToResolve.getStatus())) {
                System.out.println("Complaint already resolved.");
            } else {
                complaintToResolve.resolve();
                System.out.println("Complaint resolved successfully.");
            }
        } else if (index == -1) {
            System.out.println("going back :(...");
        } else {
            System.out.println("invalid index");
        }
    }

    private static void changeStudentName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the current name of the student: ");
        String currentName = scanner.nextLine();


        boolean studentFound = false;
        for (int i = 0; i < Professor.enrolledStudents.length; i++) {
            if (Professor.enrolledStudents[i].equalsIgnoreCase(currentName)) {
                studentFound = true;
                System.out.print("Enter the new name for the student: ");
                String newName = scanner.nextLine();
                // Update the name
                Professor.enrolledStudents[i] = newName;
                System.out.println("Student name updated successfully.");
                break;
            }
        }
        if (!studentFound) {
            System.out.println("Student with the name '" + currentName + "' not found.");
        }
    }



//login check for admin user
    private static final String[] usercheck={"admin1","admin2","admin3"};
    private static final String[] passcheck={"123","123","123"};

    private static boolean login(String user, String key ) throws NewException.InvalidLoginException {
        for (int i=0; i<usercheck.length;i++) {
            if (user.equals(usercheck[i]) && key .equals(passcheck[i])) {
                return true;
            }
        }
        throw new NewException.InvalidLoginException("wrong id pass.try again.");
    }
}
