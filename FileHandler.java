import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class FileHandler {
	// Nazwa pliku, w którym będą przechowywane dane o zadaniach
	private static final String FILE_NAME = "tasks.txt";

	// Metoda zapisująca listę zadań do pliku
	public static void saveTasksToFile(ArrayList<Task> tasks) {
		// Próba otwarcia pliku do zapisu przy użyciu PrintWriter opakowanego w BufferedWriter i FileWriter
		try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME)))) {
			// Iteracja przez wszystkie zadania
			for (Task task : tasks) {
				// Zapisanie każdego zadania w formacie CSV (oddzielone przecinkami)
				writer.println(task.getName() + "," + task.getDescription() + "," + task.getPriority() + "," +
						// Jeżeli termin nie jest ustawiony, zapisz "null"
						(task.getDeadline() != null ? task.getDeadline() : "null") + "," +
						// Zapisz, czy zadanie jest ukończone
						task.isCompleted() + "," + task.getCategory());
			}
			// Informacja o poprawnym zapisaniu zadań do pliku
			System.out.println("Zadania zapisane do pliku.");
		} catch (IOException e) {
			// Obsługa błędów podczas zapisu do pliku
			System.out.println("Błąd zapisu do pliku: " + e.getMessage());
		}
	}

	// Metoda odczytująca zadania z pliku i zwracająca listę zadań
	public static ArrayList<Task> loadTasksFromFile() {
		// Tworzenie pustej listy, do której będą dodawane wczytane zadania
		ArrayList<Task> tasks = new ArrayList<>();

		// Próba otwarcia pliku do odczytu przy użyciu BufferedReader opakowanego w FileReader
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
			String line;
			// Odczyt linii z pliku aż do końca pliku (null)
			while ((line = reader.readLine()) != null) {
				// Podzielenie odczytanej linii na poszczególne elementy (oddzielone przecinkami)
				String[] taskData = line.split(",");

				// Konwersja danych z pliku do odpowiednich typów danych
				Task.Priority priority = Task.Priority.valueOf(taskData[2]); // Konwersja priorytetu
				LocalDate deadline = taskData[3].equals("null") ? null : LocalDate.parse(taskData[3]); // Konwersja terminu (jeśli istnieje)

				// Tworzenie nowego obiektu Task z wczytanymi danymi
				Task task = new Task(taskData[0], taskData[1], priority, deadline, taskData[5]);

				// Sprawdzenie, czy zadanie było oznaczone jako ukończone, i jeśli tak, oznacz je jako ukończone
				if (Boolean.parseBoolean(taskData[4])) {
					task.markAsCompleted();
				}

				// Dodanie zadania do listy zadań
				tasks.add(task);
			}
			// Informacja o poprawnym wczytaniu zadań z pliku
			System.out.println("Zadania wczytane z pliku.");
		} catch (FileNotFoundException e) {
			// Obsługa błędu, gdy plik nie istnieje - informacja o nowej liście
			System.out.println("Plik nie istnieje. Stworzono nową listę.");
		} catch (IOException e) {
			// Obsługa błędów podczas odczytu z pliku
			System.out.println("Błąd odczytu pliku: " + e.getMessage());
		}
		// Zwrócenie listy wczytanych zadań
		return tasks;
	}
}
