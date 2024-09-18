import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystemNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileHandler {
    // Nazwa pliku, w którym będą przechowywane dane o zadaniach
    private final String FILE_NAME = "tasks.txt";

    // Metoda zapisująca listę zadań do pliku
    public boolean saveTasksToFile(final List<Task> tasks) {
        // Tworzymy instancję Gson
        final Gson gson = new Gson();

        // Próba otwarcia pliku do zapisu
        try (final PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME)))) {
            // Iteracja przez wszystkie zadania
            for (final Task task : tasks) {
                // Serializacja zadania do formatu JSON
                final String jsonTask = gson.toJson(task);

                // Zapisanie zadania w formacie JSON do pliku
                writer.println(jsonTask);
            }
            // Informacja o poprawnym zapisaniu zadań do pliku
            return true;
        } catch (final IOException e) {
            return false;
        }
    }

    // Metoda odczytująca zadania z pliku i zwracająca listę zadań
    public List<Task> loadTasksFromFile() {
        // Tworzenie pustej listy, do której będą dodawane wczytane zadania
        final List<Task> tasks = new ArrayList<>();

        // Próba otwarcia pliku do odczytu przy użyciu BufferedReader opakowanego w FileReader
        try (final BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            // Odczyt linii z pliku aż do końca pliku (null)
            while ((line = reader.readLine()) != null) {
                // Podzielenie odczytanej linii na poszczególne elementy (oddzielone przecinkami)
                final String[] taskData = line.split(",");

                // Konwersja danych z pliku do odpowiednich typów danych
                final Priority priority = Priority.valueOf(taskData[2]); // Konwersja priorytetu
                final LocalDate deadline = taskData[3].equals("null") ? null : LocalDate.parse(taskData[3]); // Konwersja terminu (jeśli istnieje)

                // Tworzenie nowego obiektu Task z wczytanymi danymi
                final Task task = new Task(taskData[0], taskData[1], priority, deadline, taskData[5]);

                // Sprawdzenie, czy zadanie było oznaczone jako ukończone, i jeśli tak, oznacz je jako ukończone
                if (Boolean.parseBoolean(taskData[4])) {
                    task.markAsCompleted(true);
                }

                // Dodanie zadania do listy zadań
                tasks.add(task);
            }

        } catch (final FileSystemNotFoundException e) {
            // Obsługa błędu, gdy plik nie istnieje - informacja o nowej liście
            return Collections.emptyList();
        } catch (final FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        // Zwrócenie listy wczytanych zadań
        return tasks;
    }
}
