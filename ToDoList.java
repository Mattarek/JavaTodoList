import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ToDoList {
	// Lista dynamiczna typu Task, która przechowuje zadania
	private ArrayList<Task> tasks;

	// Konstruktor: inicjalizuje nową pustą listę zadań
	public ToDoList() {
		tasks = new ArrayList<>();
	}

	// Dodawanie zadania do listy
	public void addTask(Task task) {
		tasks.add(task);  // Dodaje obiekt Task do listy
		System.out.println("Zadanie dodane!");
	}

	// Usuwanie zadania z listy na podstawie indeksu
	public void removeTask(int index) {
		// Sprawdzenie, czy indeks jest poprawny (musi być w zakresie listy)
		if(index >= 0 && index < tasks.size()){
			tasks.remove(index);  // Usunięcie zadania o danym indeksie
			System.out.println("Zadanie usunięte!");
		} else {
			System.out.println("Błędny indeks zadania.");
		}
	}

	// Wyświetlanie wszystkich zadań
	public void displayTasks(){
		// Jeśli lista zadań jest pusta, wypisz komunikat
		if(tasks.isEmpty()){
			System.out.println("Brak zadań do wyświetlenia.");
		} else {
			// Wywołanie metody forEach, aby wypisać każde zadanie
			tasks.forEach(System.out::println);  // Użycie metody referencyjnej do wydrukowania każdego elementu
		}
	}

	// Wyświetlanie zadań w określonej kategorii
	public void displayTasksByCategory(String category){
		// Filtrowanie zadań według kategorii przy użyciu Stream API
		List<Task> filteredTasks = tasks.stream()
				.filter(task -> task.getCategory().equalsIgnoreCase(category))  // Filtruje zadania po kategorii (ignorując wielkość liter)
				.collect(Collectors.toList());  // Zbiera przefiltrowane zadania do listy
		// Wyświetlanie przefiltrowanych zadań
		filteredTasks.forEach(task -> System.out.println(task));
	}

	// Wyświetlanie zadań, które nie są ukończone
	public void displayPendingTasks() {
		// Filtrowanie zadań, które nie są ukończone, przy użyciu Stream API
		List<Task> pendingTasks = tasks.stream()
				.filter(task -> !task.isCompleted())  // Filtruje zadania, które nie są ukończone
				.collect(Collectors.toList());  // Zbiera przefiltrowane zadania do listy
		// Wyświetlanie nieukończonych zadań
		pendingTasks.forEach(System.out::println);
	}

	// Wyświetlanie ukończonych zadań
	public void displayCompletedTasks() {
		// Filtrowanie ukończonych zadań przy użyciu Stream API
		List<Task> completedTasks = tasks.stream()
				.filter(Task::isCompleted)  // Używa metody referencyjnej do filtrowania ukończonych zadań
				.collect(Collectors.toList());  // Zbiera przefiltrowane zadania do listy
		// Wyświetlanie ukończonych zadań
		completedTasks.forEach(System.out::println);
	}

	// Oznaczanie zadania jako ukończone na podstawie indeksu
	public void markTaskAsCompleted(int index) {
		// Sprawdzenie, czy indeks jest poprawny (musi być w zakresie listy)
		if(index >= 0 && index < tasks.size()) {
			tasks.get(index).markAsCompleted();  // Oznaczanie zadania jako ukończone
			System.out.println("Zadanie oznaczone jako ukończone!");
		} else {
			System.out.println("Błędny indeks zadania.");
		}
	}

	// Sortowanie zadań według priorytetu
	public void sortTasksByPriority(){
		Collections.sort(tasks);  // Użycie domyślnego sortowania, które działa zgodnie z metodą compareTo w klasie Task
		System.out.println("Zadania posortowane według priorytetu.");
	}

	// Sortowanie zadań według terminu (deadline)
	public void sortTasksByDeadline(){
		// Sortowanie zadań według daty zakończenia (null wartości są przesuwane na koniec)
		tasks.sort((t1, t2) -> {
			if(t1.getDeadline() == null && t2.getDeadline() == null) return 0;  // Oba zadania nie mają terminu
			if(t1.getDeadline() == null) return 1;  // Zadanie 1 nie ma terminu, więc jest "mniejsze"
			if(t2.getDeadline() == null) return -1;  // Zadanie 2 nie ma terminu, więc jest "mniejsze"
			return t1.getDeadline().compareTo(t2.getDeadline());  // Porównanie terminów
		});
		System.out.println("Zadania posortowane według terminu.");
	}

	// Pobranie listy zadań
	public ArrayList<Task> getTasks() {
		return tasks;
	}
}
