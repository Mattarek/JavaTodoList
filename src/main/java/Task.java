import java.time.LocalDate;

public class Task implements Comparable<Task> {
    // Comparable<T> to interfejs w Javie, który umożliwia porównywanie obiektów
    // danego typu w sposób naturalny, czyli pozwala na ustalenie domyślnego porządku
    // obiektów. Interfejs ten jest szczególnie przydatny, kiedy chcesz sortować
    // kolekcje obiektów (np. List<Task>) lub używać obiektów w strukturach danych,
    // które wymagają porównywania (np. TreeSet, TreeMap).


    private final String name;        // Nazwa zadania
    private final String description; // Opis zadania
    private final Priority priority;  // Priorytet zadania (LOW, MEDIUM, HIGH)
    private final LocalDate deadline; // Termin wykonania zadania
    private final String category;     // Kategoria zadania

    // Informacja, czy zadanie zostało ukończone
    private boolean isCompleted;


    public Task(final String name, final String description, final Priority priority, final LocalDate deadline, final String category) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String getCategory() {
        return category;
    }


    public void setCompleted(final boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) [Priority: %s, Deadline: %s, Category: %s]",
                name,
                isCompleted ? "Done" : "Not Done",  // Sprawdza, czy zadanie jest ukończone
                priority,
                deadline != null ? deadline : "None",  // Jeśli brak deadline, wyświetla "None"
                category
        );
    }

    @Override
    public int compareTo(final Task other) {
        if (isCompleted != other.isCompleted) {
            return Boolean.compare(isCompleted, other.isCompleted);
        }
        return priority.compareTo(other.priority);
    }
}
