import java.util.Scanner;

public class User {

    // hardcode data
    private static String[][] Courses = {
            {"CS101", "data structures like arrays, linked lists, stacks", "Data Structures", "Dr. Jain", "3 credits", "None", "Mon/Wed 9:00-10:30 AM", "50", "C101", "0"},
            {"CS103", "Introduction to process management, memory management", "Operating Systems", "Dr. Arora", "4 credits", "CS101", "Tue/Thu 11:00-12:30 PM", "40", "C102", "0"},
            {"ECE113", "diodes, transistors, operational amplifier", "Basic Electronics", "Dr. Singh", "3 credits", "None", "Mon/Wed 2:00-3:30 PM", "60", "C01", "0"},
            {"MTH201", "Probability theory, random variables, hypothesis testing", "Probability and Statistics", "Dr. Rao", "3 credits", "None", "Fri 10:00-1:00 PM", "45", "C02", "0"},
            {"DES205", "user experience design, and interactive prototypes", "Design of Interactive Systems", "Dr. Das", "3 credits", "None", "Tue/Thu 1:00-2:30 PM", "30", "C12", "0"},
            {"CSE322", "Formal languages, automata theory, computational complexity", "Theory of Computation", "Dr. Katyal", "4 credits", "CS101", "Mon/Wed 10:30-12:00 PM", "35", "C201", "0"},
            {"BIO545", "Application of statistical methods to biological data", "Biostatistics", "Dr. Mehta", "3 credits", "MTH201", "Fri 2:00-5:00 PM", "25", "RnD B001", "0"},
            {"CSE535", "Mobile systems architecture, wireless communication", "Mobile Computing", "Dr. Sarkar", "3 credits", "CS103", "Tue/Thu 3:00-4:30 PM", "40", "C101", "0"}
    };


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("----------------------------------------");
        System.out.println("         IIIT Delhi ERP PORTAL   ");
        System.out.println("----------------------------------------\n");

        System.out.println("Login as: ");
        System.out.println("1.Student ");
        System.out.println("2.Professor ");

        System.out.println("3.Administrator ");
        System.out.println("4.Teaching Assistant ");
        System.out.println("5.Close the Program (-1) ");
        System.out.print("Enter Your Choice (1/2/3/4/-1): ");
        int choice = scanner.nextInt();scanner.nextLine();
        switch (choice) {
            case 1:
                Student.run();
                break;
            case 2:
                Professor.run();
                break;
            case 3:
                Administrator.run();
                break;
            case 4:
                teachingAssist.run();
            case -1:
                System.out.println("Closing the program...");
                break;
            default:
                System.out.println("wrong choice. Please select 1,2, or3.");
                break;
        }scanner.close();
    }

    protected static boolean isCourseFull(int index) {
        if (index >= 0 && index < Courses.length) {
            int currentEnrollment = Integer.parseInt(Courses[index][9]);
            int maxCapacity = Integer.parseInt(Courses[index][7]);
            return currentEnrollment >= maxCapacity;
        }return false;
    }

    protected static void registerCourse(int index) {
        if (index >= 0 && index < Courses.length) {
            int currentEnrollment = Integer.parseInt(Courses[index][9]);
            Courses[index][9] = String.valueOf(currentEnrollment + 1);
        }
    }



    protected static void addcourse(String[] newCourse) {
        String[][] newCoursesArray = new String[Courses.length + 1][];
        for (int i=0; i<Courses.length; i++) {
            newCoursesArray[i] = Courses[i];
        }
        newCoursesArray[Courses.length] = newCourse;
        Courses = newCoursesArray;
    }

    protected static void deletecor(int index) {
        if (index < 0 || index >= Courses.length) {
            System.out.println("wrong  index");
            return;
        }
        String[][] newCoursesArray = new String[Courses.length - 1][];
        for (int i = 0, j = 0; i < Courses.length; i++) {
            if (i != index) {
                newCoursesArray[j++] = Courses[i];
            }
        }
        Courses = newCoursesArray;
    }

    protected static String[][] mycourse() {
        return Courses;
    }

    public static void setCourse(int index, String[] updatedCourse) {
        if (index >= 0 && index < Courses.length) {
            Courses[index] = updatedCourse;
        } else {
            System.out.println("wrong  course index.");
        }
    }
}
