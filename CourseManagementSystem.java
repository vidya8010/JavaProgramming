import java.sql.*;
import java.util.Scanner;

public class CourseManagementSystem {
    private static final String URL = "jdbc:mysql://localhost:3306/coursedb";
    private static final String USER = "root";
    private static final String PASSWORD = "sp14@vidya#";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add Course\n2. List Courses\n3. Register Student\n4. Enroll in Course\n5. Drop Course\n6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: addCourse(scanner); break;
                case 2: listCourses(); break;
                case 3: registerStudent(scanner); break;
                case 4: enrollStudent(scanner); break;
                case 5: dropCourse(scanner); break;
                case 6: System.exit(0);
                default: System.out.println("Invalid choice.");
            }
        }
    }

    // Database Connection
    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Add a new Course
    private static void addCourse(Scanner scanner) {
        System.out.print("Course Code: ");
        String code = scanner.nextLine();
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Description: ");
        String desc = scanner.nextLine();
        System.out.print("Capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Schedule: ");
        String schedule = scanner.nextLine();

        String query = "INSERT INTO course (course_code, title, description, capacity, schedule) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setString(1, code);
            stmt.setString(2, title);
            stmt.setString(3, desc);
            stmt.setInt(4, capacity);
            stmt.setString(5, schedule);
            stmt.executeUpdate();
            System.out.println("Course added successfully!");
        }
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // List all Courses
    private static void listCourses() {
        String query = "SELECT * FROM course";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query))
        {
            System.out.println("\nAvailable Courses:");
            while (rs.next()) {
                System.out.println("Code: " + rs.getString("course_code") +
                        " | Title: " + rs.getString("title") +
                        " | Capacity: " + rs.getInt("capacity") +
                        " | Schedule: " + rs.getString("schedule"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Register a Student
    private static void registerStudent(Scanner scanner) {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        String query = "INSERT INTO student (name) VALUES (?)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    System.out.println("Student registered successfully! ID: " + rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Enroll a Student in a Course
    private static void enrollStudent(Scanner scanner) {
        System.out.print("Student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Course Code: ");
        String courseCode = scanner.nextLine();

        String query = "INSERT INTO registration (student_id, course_code) VALUES (?, ?)";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            stmt.setString(2, courseCode);
            stmt.executeUpdate();
            System.out.println("Student enrolled successfully!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Drop a Course for a Student
    private static void dropCourse(Scanner scanner) {
        System.out.print("Student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Course Code: ");
        String courseCode = scanner.nextLine();

        String query = "DELETE FROM registration WHERE student_id = ? AND course_code = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            stmt.setString(2, courseCode);
            stmt.executeUpdate();
            System.out.println("Course dropped successfully!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
