import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FileHandler {
    final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();
    private final String FILE_NAME = "./tasks.txt";

    public boolean saveTasksToFile(final List<Task> tasks) {
        try (final PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME)))) {
            for (final Task task : tasks) {
                final String jsonTask = gson.toJson(task);
                writer.println(jsonTask);
            }
            return true;
        } catch (final IOException e) {
            return false;
        }
    }

    public List<Task> loadTasksFromFile() {
        List<Task> tasks = new ArrayList<>();

        try (final BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            final Type taskListType = TypeToken.getParameterized(List.class, ToDoList.class).getType();
            tasks = gson.fromJson(reader, taskListType);
            System.out.println(tasks);
        } catch (final IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

        return tasks;
    }
}
