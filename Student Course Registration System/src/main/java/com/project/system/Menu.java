package com.project.system;

import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;

import java.util.Scanner;

/**
 * The {@code Menu} class provides the user interface for the Student-Course Registration System.
 * It allows performing CRUD operations on students and courses using a console-based menu.
 */
public class Menu {

    /**
     * Constructs a new {@code Menu} instance and initializes the MongoDB connection.
     * It continuously displays the menu and processes user input until the program exits.
     */
    public Menu() {
        MongoDatabase database = MongoDBUtil.getDatabase();

        StudentService studentService = new StudentService(database);
        CourseService courseService = new CourseService(database);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1] Add Student");
            System.out.println("2] Edit Student");
            System.out.println("3] Delete Student");
            System.out.println("4] Display all Students");
            System.out.println("5] Search Student");
            System.out.println("6] Add Course");
            System.out.println("7] Delete Course");
            System.out.println("8] Display all Courses");
            System.out.println("9] Search Course");
            System.out.println("10] Exit");

            try {
                System.out.print("Enter your choice: ");
                byte choice = scanner.nextByte();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addStudent(scanner, studentService);
                        break;
                    case 2:
                        editStudent(scanner, studentService);
                        break;
                    case 3:
                        deleteStudent(scanner, studentService);
                        break;
                    case 4:
                        studentService.displayAllStudents();
                        break;
                    case 5:
                        searchStudent(scanner, studentService);
                        break;
                    case 6:
                        addCourse(scanner, courseService);
                        break;
                    case 7:
                        deleteCourse(scanner, courseService);
                        break;
                    case 8:
                        courseService.displayAllCourses();
                        break;
                    case 9:
                        searchCourse(scanner, courseService);
                        break;
                    case 10:
                        System.out.println("Exiting...");
                        MongoDBUtil.close();
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Enter a valid option");
            }
        }
    }

    /**
     * Adds a new student by taking input from the user.
     *
     * @param scanner        The scanner to read user input.
     * @param studentService The service for managing student operations.
     */
    private void addStudent(Scanner scanner, StudentService studentService) {
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Student Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Course: ");
        String course = scanner.nextLine();

        Student student = new Student(name, email, course);
        studentService.addStudent(student);
    }

    /**
     * Edits the details of an existing student based on the user-provided ID.
     *
     * @param scanner        The scanner to read user input.
     * @param studentService The service for managing student operations.
     */
    private void editStudent(Scanner scanner, StudentService studentService) {
        System.out.print("Enter Student ID to edit: ");
        String id = scanner.nextLine().trim();

        if (!ObjectId.isValid(id)) {
            System.out.println("Invalid ID format. Please enter a valid ID.");
            return;
        }

        String name = null, email = null, course = null;

        System.out.println("\nSelect option to edit:");
        System.out.println("1] Student Name");
        System.out.println("2] Student Email");
        System.out.println("3] Student Course");
        System.out.println("4] Edit All Fields");
        System.out.println("5] Back");

        try {
            System.out.print("Enter your option: ");
            byte option = scanner.nextByte();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Enter new Student Name: ");
                    name = scanner.nextLine();
                    break;

                case 2:
                    System.out.print("Enter new Student Email: ");
                    email = scanner.nextLine();
                    break;

                case 3:
                    System.out.print("Enter new Course: ");
                    course = scanner.nextLine();
                    break;

                case 4:
                    System.out.print("Enter new Student Name: ");
                    name = scanner.nextLine();

                    System.out.print("Enter new Student Email: ");
                    email = scanner.nextLine();

                    System.out.print("Enter new Course: ");
                    course = scanner.nextLine();
                    break;

                case 5:
                    return;
                default:
                    System.out.println("Invalid option.");
                    return;
            }

            studentService.updateStudent(id,
                    name != null ? name : "",
                    email != null ? email : "",
                    course != null ? course : ""
            );
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine();
        }
    }

    /**
     * Deletes a student by ID.
     *
     * @param scanner        The scanner to read user input.
     * @param studentService The service for managing student operations.
     */
    private void deleteStudent(Scanner scanner, StudentService studentService) {
        System.out.print("Enter Student ID to delete: ");
        String id = scanner.nextLine();
        studentService.deleteStudent(id);
    }

    /**
     * Searches for a student by ID and displays the result.
     *
     * @param scanner        The scanner to read user input.
     * @param studentService The service for managing student operations.
     */
    private void searchStudent(Scanner scanner, StudentService studentService) {
        System.out.print("Enter Student id to search: ");
        String id = scanner.nextLine();
        studentService.searchStudent(id);
    }

    /**
     * Adds a new course by taking input from the user.
     *
     * @param scanner       The scanner to read user input.
     * @param courseService The service for managing course operations.
     */
    private void addCourse(Scanner scanner, CourseService courseService) {
        System.out.print("Enter Course Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Course Description: ");
        String description = scanner.nextLine();

        Course course = new Course(name, description);
        courseService.addCourse(course);
    }

    /**
     * Deletes a course by name.
     *
     * @param scanner       The scanner to read user input.
     * @param courseService The service for managing course operations.
     */
    private void deleteCourse(Scanner scanner, CourseService courseService) {
        System.out.print("Enter Course name to delete: ");
        String id = scanner.nextLine();
        courseService.deleteCourse(id);
    }

    /**
     * Searches for a course by name and displays the result.
     *
     * @param scanner       The scanner to read user input.
     * @param courseService The service for managing course operations.
     */
    private void searchCourse(Scanner scanner, CourseService courseService) {
        System.out.print("Enter Course Name to search: ");
        String name = scanner.nextLine();
        courseService.searchCourse(name);
    }
}
