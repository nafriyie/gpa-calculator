import java.text.DecimalFormat;
import java.util.Scanner;

public class GPACalculator {

    static double semCredits;
    
    private static double SemesterGPA() {

        Scanner scanner = new Scanner(System.in); // System.in represent the standard input stream - what we input in the command line

        double totalPoints = 0.0;
        double totalCredits = 0.0;
        int pncCredits = 0;
        boolean moreClasses = false;

        System.out.println("Enter Semester Period: ");
        String sem = scanner.nextLine();

        // do while loops are used when the num of iterations are unknown but we know we iterate thru the loop at least once
        do {
            int credits = 0; // credits of class
            boolean validCredits = true; // if string user inputted is valid

            // do while loop is used here to make sure user inputs a valid credit entry
            do { 
                validCredits = true;
                System.out.println("Enter your credits: ");
                String creditsString = scanner.nextLine(); // read user input
                try {
                    credits = Integer.parseInt(creditsString); // convert string to an integer
                } catch (NumberFormatException nfe) { // if value input is not a num
                    System.out.println("Please enter enter a valid integer: ");
                    validCredits = false; 
                }
            } while (!validCredits); // keeps running until vaildCredits is true
    
            boolean validGrade = true; // checks if string inputted is valid
            String grade = ""; // string value that user inputted for the grade
            double gradeValue = 0.0; // the corresponding grade point value associated with letter grade
    
            do {
                validGrade = true;
                System.out.println("Enter your grade: ");
                grade = scanner.nextLine(); // stops program and checks
                
                if (grade.equalsIgnoreCase("A")) {
                    gradeValue = 4.0;
                } else if (grade.equalsIgnoreCase("B+")) {
                    gradeValue = 3.5;
                } else if (grade.equalsIgnoreCase("B")) {
                    gradeValue = 3.0;
                } else if (grade.equalsIgnoreCase("C+")) {
                    gradeValue = 2.5;
                } else if (grade.equalsIgnoreCase("C")) {
                    gradeValue = 2.0;
                } else if (grade.equalsIgnoreCase("D")) {
                    gradeValue = 1.0;
                } else if (grade.equalsIgnoreCase("F")) {
                    gradeValue = 0.0;
                } else if (grade.equalsIgnoreCase("W")) {
                    gradeValue = 0.0;
                    credits = 0; // since we gave the credits earlier, we must set the this to zero bc withdrawn classes don't count for creds
                } else if (grade.equalsIgnoreCase("P")) {
                    gradeValue = 0.0;
                    pncCredits = credits; // passing a class doesn't effect your gpa, but still gives the credits, we store the credits so we can add it back later
                    credits = 0;
                } else if (grade.equalsIgnoreCase("NC")) {
                    gradeValue = 0.0;
                    credits = 0;
                } else {
                    System.out.println("You didn't enter a valid grade boomer");
                    validGrade = false;
                }
            } while (!validGrade); // goes until you enter valid grade
            
            double points = gradeValue * credits; // calculates the grade-point for a single class

            totalPoints += points; // adds the grade points of each class together
            totalCredits += credits; // adds the credits of each line together

            System.out.println("Would you like to enter another class? (Y/N)");
            String moreClassesString = scanner.nextLine(); // stops program and checks

            moreClasses = moreClassesString.equalsIgnoreCase("Y"); // if user input equals the target string (in this case "Y") then boolean is true

        } while (moreClasses); // while more classes is true, repeat

        DecimalFormat df = new DecimalFormat("0.000");

        double finalCredits = totalCredits + pncCredits; // if there was a class that was passed, we add the credits back to the total bc we had to set credits to zero so it didn't sway the gpa

        double gpa = totalPoints / totalCredits;
        
        System.out.println();

        System.out.println(sem);
        System.out.println("Credits: " + finalCredits);
        System.out.println("Points: " + totalPoints);
        System.out.println("GPA: " + df.format(gpa)); // makes gpa appear in decimal format, how it would show on your transcript

        System.out.println();

        semCredits = totalCredits;

        return gpa;
    }
    
    public static void main (String[] args) {

       Scanner scanner = new Scanner(System.in);

       double totalGPA = 0.0;
       double sumPoints = 0.0;
       double sumCredits = 0.0;
       boolean moreSem = false;

       do {
           double gpa = SemesterGPA();
           double semPoints = gpa * semCredits;
           sumPoints += semPoints;
           sumCredits += semCredits;

           System.out.println("Do you have another semester to add? (Y/N)");
           String hasSem = scanner.nextLine();
           moreSem = hasSem.equalsIgnoreCase("Y");

       } while (moreSem);

       System.out.println();

       DecimalFormat round = new DecimalFormat("0.000");
       totalGPA = sumPoints / sumCredits;

       System.out.println("Degree Credits: " + sumCredits);
       System.out.println("Cumulative GPA: " + round.format(totalGPA));

       scanner.close();
    }
}
