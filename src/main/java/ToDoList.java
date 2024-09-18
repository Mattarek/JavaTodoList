import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ToDoList {
    private final List<Task> tasks = new ArrayList<>();

    public List<Task> displayTasks() {
        System.out.println(tasks);
        return tasks;
    }

    public void addTask(final Task task) {
        tasks.add(task);
    }

    public boolean removeTask(final int index) {
        if (index < 0 && index > tasks.size()) {
            return false;
        }
        tasks.remove(index);
        return true;
    }


    public List<Task> displayTasksByCategory(final String category) {
        final List<Task> filteredTasks = tasks.stream()
                .filter(task -> task.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
        return filteredTasks;
    }

    public List<Task> displayPendingTasks() {
        final List<Task> pendingTasks = tasks.stream()
                .filter(task -> !task.isCompleted())
                .collect(Collectors.toList());
        return pendingTasks;
    }


    public List<Task> displayCompletedTasks() {

        final List<Task> completedTasks = tasks.stream()
                .filter(Task::isCompleted)
                .collect(Collectors.toList());
        return completedTasks;
    }

    public void markTaskAsCompleted(final int index) {
        tasks.get(index).setCompleted(true);
    }

    public void sortTasksByPriority() {
        Collections.sort(tasks);
    }

    public void sortTasksByDeadline() {

        tasks.sort((t1, t2) -> {
            if (t1.getDeadline() == null && t2.getDeadline() == null) {
                return 0;
            }
            if (t1.getDeadline() == null) {
                return 1;
            }
            if (t2.getDeadline() == null) {
                return -1;
            }
            return t1.getDeadline().compareTo(t2.getDeadline());
        });
    }

    protected List<Task> getTasks() {
        return tasks;
    }
}
