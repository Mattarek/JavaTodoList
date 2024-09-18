import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ToDoList {
    // Lista dynamiczna typu Task, która przechowuje zadania
    private final List<Task> tasks = new ArrayList<>();

    // Dodawanie zadania do listy
    public void addTask(final Task task) {
        tasks.add(task);  // Dodaje obiekt Task do listy
    }

    // Usuwanie zadania z listy na podstawie indeksu
    public boolean removeTask(final int index) {
        // Sprawdzenie, czy indeks jest poprawny (musi być w zakresie listy)
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);  // Usunięcie zadania o danym indeksie
            return true;
        }
        return false;
    }

    // Wyświetlanie wszystkich zadań
    public boolean displayTasks() {
        // Jeśli lista zadań jest pusta, wypisz komunikat
        return !tasks.isEmpty(); // Lista jest pusta
        // Jeśli lista nie jest pusta, wykonaj inną operację, np. zwróć `true`
        // Możesz tutaj przechować lub zwrócić zadania w innym formacie, jeśli potrzebujesz
// Lista nie jest pusta
    }

    // Wyświetlanie zadań w określonej kategorii
    public void displayTasksByCategory(final String category) {
        // Filtrowanie zadań według kategorii przy użyciu Stream API
        final List<Task> filteredTasks = tasks.stream()
                .filter(task -> task.getCategory().equalsIgnoreCase(category))  // Filtruje zadania po kategorii (ignorując wielkość liter)
                .collect(Collectors.toList());  // Zbiera przefiltrowane zadania do listy
    }

    // Wyświetlanie zadań, które nie są ukończone
    public void displayPendingTasks() {
        // Filtrowanie zadań, które nie są ukończone, przy użyciu Stream API
        final List<Task> pendingTasks = tasks.stream() // Tworzy strumień z listy zadań (tasks)
                .filter(task -> !task.isCompleted())  // Filtruje zadania, które nie są ukończone
                .collect(Collectors.toList());  // Zbiera przefiltrowane zadania do listy
    }

    // Wyświetlanie ukończonych zadań
    public void displayCompletedTasks() {
        // Filtrowanie ukończonych zadań przy użyciu Stream API
        final List<Task> completedTasks = tasks.stream()
                .filter(Task::isCompleted)  // Używa metody referencyjnej do filtrowania ukończonych zadań
                .collect(Collectors.toList());  // Zbiera przefiltrowane zadania do listy
    }

    // Oznaczanie zadania jako ukończone na podstawie indeksu
    public void markTaskAsCompleted(final int index) {
        tasks.get(index).markAsCompleted(true);  // Oznaczanie zadania jako ukończone
    }

    // Sortowanie zadań według priorytetu
    public void sortTasksByPriority() {
        Collections.sort(tasks);  // Użycie domyślnego sortowania, które działa zgodnie z metodą compareTo w klasie Task
    }

    // Sortowanie zadań według terminu (deadline)
    public void sortTasksByDeadline() {
        // Sortowanie zadań według daty zakończenia (null wartości są przesuwane na koniec)
        tasks.sort((t1, t2) -> {
            if (t1.getDeadline() == null && t2.getDeadline() == null) {
                return 0;  // Oba zadania nie mają terminu
            }
            if (t1.getDeadline() == null) {
                return 1;  // Zadanie 1 nie ma terminu, więc jest "mniejsze"
            }
            if (t2.getDeadline() == null) {
                return -1;  // Zadanie 2 nie ma terminu, więc jest "mniejsze"
            }
            return t1.getDeadline().compareTo(t2.getDeadline());  // Porównanie terminów
        });
    }

    // Pobranie listy zadań
    protected List<Task> getTasks() {
        return tasks;
    }
}
