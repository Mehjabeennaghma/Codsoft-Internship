import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

class QuizQuestion {
    private String question;
    private List<String> options;
    private String correctAnswer;

    public QuizQuestion(String question, List<String> options, String correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}

class Quiz {
    private List<QuizQuestion> questions;
    private int score;

    public Quiz(List<QuizQuestion> questions) {
        this.questions = questions;
        this.score = 0;
    }

    public void displayQuestion(QuizQuestion question) {
        System.out.println(question.getQuestion());
        List<String> options = question.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    public void startQuiz() {
        System.out.println("Welcome to the Quiz!");
        for (QuizQuestion question : questions) {
            displayQuestion(question);
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your answer (1, 2, 3, or 4): ");
            String answer = scanner.nextLine();
            if (isValidInput(answer)) {
                ExecutorService executor = Executors.newSingleThreadExecutor();
                Future<String> future = executor.submit(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(10); // 10 seconds timer
                        return "Time's up!";
                    } catch (InterruptedException e) {
                        return "Interrupted!";
                    }
                });
                try {
                    System.out.println(future.get());
                    executor.shutdownNow();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                if (answer.equals(question.getCorrectAnswer())) {
                    System.out.println("Correct!");
                    score++;
                } else {
                    System.out.println("Incorrect!");
                }
            } else {
                System.out.println("Invalid input. Skipping question.");
            }
            System.out.println();
        }
        System.out.println("Quiz completed!");
        System.out.println("Your score: " + score + "/" + questions.size());
    }

    private boolean isValidInput(String input) {
        return input.matches("[1-4]");
    }
}

public class Main2 {
    public static void main(String[] args) {
        List<QuizQuestion> questions = new ArrayList<>();
        questions.add(new QuizQuestion("What is the capital of India?", List.of("Mumbai", "Delhi", "jaipur", "lucknow"), "2"));
        questions.add(new QuizQuestion("Who is the author of 'To Kill a Mockingbird'?", List.of("Harper Lee", "Jane Austen", "Charles Dickens", "Mark Twain"), "1"));
        questions.add(new QuizQuestion("Which planet is known as the Red Planet?", List.of("Venus", "Jupiter","Mars",  "Saturn"), "3"));
        questions.add(new QuizQuestion("What is the powerhouse of the cell?", List.of("Mitochondria", "Nucleus", "Ribosome", "Golgi Apparatus"), "1"));

        Quiz quiz = new Quiz(questions);
        quiz.startQuiz();
    }
}
