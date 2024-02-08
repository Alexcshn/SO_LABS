import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) {
        TaskController taskController = new TaskController();

        // Task 1: Separate Class
        AdditionTask task1 = new AdditionTask(10, 20);
        taskController.scheduleTask(task1, 0, 5000);

        // Task 2: Directly in Main Method
        TimerTask task2 = new AdditionTask(40, 60);
        taskController.scheduleTask(task2, 2000, 8000);

        // Task 3: Using Anonymous Class
        taskController.scheduleTask(new AdditionTask(5, 15), 10000, 10000);

        // Simulate the program running for a while
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Stop all scheduled tasks
        taskController.stopTasks();
    }
}

class AdditionTask extends TimerTask {
    private int a;
    private int b;

    public AdditionTask(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        System.out.println("Performing the Addition");
        System.out.println("Numbers added: " + a + " and " + b);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("The sum is: " + (a + b));
    }
}

class TaskController {
    private Timer timer;

    public TaskController() {
        this.timer = new Timer();
    }

    public void scheduleTask(TimerTask task, long delay, long period) {
        timer.scheduleAtFixedRate(task, delay, period);
    }

    public void stopTasks() {
        timer.cancel();
    }
}