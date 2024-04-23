import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private int enrolled;

    public Course(String code, String title, String description, int capacity) {
        this.code = code;
        this.title = title;
        this.description =description;
        this.capacity = capacity;
        this.enrolled = 0;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getEnrolled() {
        return enrolled;
    }

    public boolean isFull() {
        return enrolled >= capacity;
    }

    public void enrollStudent() {
        enrolled++;
    }

    public void dropStudent() {
        enrolled--;
    }
}

class Student {
    private int id;
    private String name;
    private List<Course> registeredCourses;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        if (!course.isFull()) {
            registeredCourses.add(course);
            course.enrollStudent();
            System.out.println("Course " + course.getCode() + " registered successfully.");
        } else {
            System.out.println("Course " + course.getCode() + " is already full.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.dropStudent();
            System.out.println("Course " + course.getCode() + " dropped successfully.");
        } else {
            System.out.println("You are not registered for course " + course.getCode() + ".");
        }
    }
}

public class Main3{
    public static void main(String[] args) {
        Course course1 = new Course("CS101", "Introduction to Computer Science", "Basic concepts of programming", 50);
        Course course2 = new Course("MATH202", "Linear Algebra", "Study of vectors and matrices", 40);
        Course course3 = new Course("ENG301", "English Literature", "Exploration of classic literature", 30);

        List<Course> courses = List.of(course1, course2, course3);

        Student student = new Student(12345, "jon dea");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Register for a course");
            System.out.println("2. Drop a course");
            System.out.println("3. View available courses");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Available Courses:");
                    for (Course course : courses) {
                        System.out.println(course.getCode() + " - " + course.getTitle() + " (" + course.getEnrolled() + "/" + course.getCapacity() + ")");
                    }
                    System.out.print("Enter course code to register: ");
                    String courseCode = scanner.nextLine();
                    Course selectedCourse = null;
                    for (Course course : courses) {
                        if (course.getCode().equals(courseCode)) {
                            selectedCourse = course;
                            break;
                        }
                    }
                    if (selectedCourse != null) {
                        student.registerCourse(selectedCourse);
                    } else {
                        System.out.println("Invalid course code.");
                    }
                    break;
                case 2:
                    System.out.println("Registered Courses:");
                    for (Course course : student.getRegisteredCourses()) {
                        System.out.println(course.getCode() + " - " + course.getTitle());
                    }
                    System.out.print("Enter course code to drop: ");
                    String dropCode = scanner.nextLine();
                    Course dropCourse = null;
                    for (Course course : student.getRegisteredCourses()) {
                        if (course.getCode().equals(dropCode)) {
                            dropCourse = course;
                            break;
                        }
                    }
                    if (dropCourse != null) {
                        student.dropCourse(dropCourse);
                    } else {
                        System.out.println("You are not registered for the specified course.");
                    }
                    break;
                case 3:
                    System.out.println("Available Courses:");
                    for (Course course : courses) {
                        System.out.println(course.getCode() + " - " + course.getTitle() + " (" + course.getEnrolled() + "/" + course.getCapacity() + ")");
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
