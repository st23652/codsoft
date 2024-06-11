import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        System.out.println("number of subjects: ");
        int totalSubj = reader.nextInt();

        int[] marks = new int[totalSubj];
        int totalMarks = 0;

        for (int x =0; x < totalSubj; x++) {
            System.out.println("marks for subject " + (x + 1) + "out of 100: ");
            marks[x] = reader.nextInt();
            totalMarks += marks[x];
        }

        double percentageAvg = (double) totalMarks / totalSubj;

        char grade;
        if (percentageAvg >= 90) {
            grade = 'A';
        } else if (percentageAvg >= 80) {
            grade = 'B';
        } else if (percentageAvg >= 70) {
            grade = 'C';
        } else if (percentageAvg >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        System.out.println("/n Results: ");
        System.out.println("total marks: " + totalMarks);
        System.out.println("avg %: " + percentageAvg);
        System.out.println("grade: " + grade);

        reader.close();
    }
}