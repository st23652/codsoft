import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Task5 implements Serializable {

    public class student implements Serializable {
        private String name;
        private int rollNo;
        private String grade;

        public student(String name, int rollNo, String grade) {
            this.name = name;
            this.rollNo = rollNo;
            this.grade = grade;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getRollNo() {
            return rollNo;
        }

        public void setRollNo(int rollNo) {
            this.rollNo = rollNo;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        @Override
        public String toString() {
            return "student" +
                    "{" +
                    "name='" + name + '\'' +
                    ", rollNo=" + rollNo +
                    ", grade='" + grade + '\'' +
                    '}';
        }
    }

    public class studentManagementSystem {
        private List<student> students;
        private final String filePath = "students.ser";

        public studentManagementSystem() {
            this.students = new ArrayList<>();
            load();
        }

        public void add(student pupil) {
            students.add(pupil);
            save();
        }

        public void remove(int rollNo) {
            students.removeIf(student -> student.getRollNo() == rollNo);
            save();
        }

        public student search(int rollNo) {
            for (student pupil : students) {
                if (pupil.getRollNo() == rollNo) {
                    return pupil;
                }
            }
            return null;
        }

        public void displayAll() {
            for (student pupil : students) {
                System.out.println(pupil);
            }
        }

        private void save() {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
                oos.writeObject(students);
            } catch (IOException e) {
                System.out.println("error in saving: " + e.getMessage());
            }
        }

        private void load() {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
                students = (List<student>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("no data found");
            }
        }
    }

    public static void main(String[] args) {
        Task5 task5 = new Task5();
        studentManagementSystem sms = task5.new studentManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("student management system");
            System.out.println("1: add a new student");
            System.out.println("2: remove a student");
            System.out.println("3: search for a student");
            System.out.println("4: display all students");
            System.out.println("5: exit");
            System.out.print("choose an option: ");

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("invalid, please enter a number: ");
                scanner.nextLine();
                continue;
            }

            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("enter name: ");
                    String name = scanner.nextLine();
                    if (name.isEmpty()) {
                        System.out.println("name cannot be empty");
                        break;
                    }

                    System.out.print("enter roll number: ");
                    int rollNo;
                    try {
                        rollNo = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("invalid, please enter a valid roll number: ");
                        scanner.nextLine();
                        break;
                    }

                    scanner.nextLine();

                    System.out.print("enter grade: ");
                    String grade = scanner.nextLine();
                    if (grade.isEmpty()) {
                        System.out.println("grade cannot be empty");
                        break;
                    }

                    student newPupil = task5.new student(name, rollNo, grade);
                    sms.add(newPupil);
                    System.out.println("student added");
                    break;
                case 2:
                    System.out.print("enter roll number to remove: ");
                    int removeRollNo;
                    try {
                        removeRollNo = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("invalid, please enter a valid roll number: ");
                        scanner.nextLine();
                        break;
                    }

                    sms.remove(removeRollNo);
                    System.out.println("student removed");
                    break;
                case 3:
                    System.out.print("enter roll number for search: ");
                    int searchRollNo;
                    try {
                        searchRollNo = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("invalid, please enter a valid roll number: ");
                        scanner.nextLine();
                        break;
                    }

                    student found = sms.search(searchRollNo);
                    if (found != null) {
                        System.out.println("student found: " + found);
                    } else {
                        System.out.println("not found");
                    }
                    break;
                case 4:
                    sms.displayAll();
                    break;
                case 5:
                    System.out.println("exiting");
                    System.exit(0);
                default:
                    System.out.println("invalid, try again");
            }
        }
    }
}