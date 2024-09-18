import java.time.LocalDate;

public class Task implements Comparable<Task> {
    // Comparable<T> to interfejs w Javie, który umożliwia porównywanie obiektów
    // danego typu w sposób naturalny, czyli pozwala na ustalenie domyślnego porządku
    // obiektów. Interfejs ten jest szczególnie przydatny, kiedy chcesz sortować
    // kolekcje obiektów (np. List<Task>) lub używać obiektów w strukturach danych,
    // które wymagają porównywania (np. TreeSet, TreeMap).


    // Definicja zmiennych dla klasy Task
    private final String name;        // Nazwa zadania
    private final String description; // Opis zadania
    private final Priority priority;  // Priorytet zadania (LOW, MEDIUM, HIGH)
    private final LocalDate deadline; // Termin wykonania zadania
    private boolean isCompleted; // Informacja, czy zadanie zostało ukończone
    private final String category;     // Kategoria zadania

    // Konstruktor klasy Task - tworzy nowe zadanie
    public Task(final String name, final String description, final Priority priority, final LocalDate deadline, final String category) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
        this.category = category;
    }

    // Gettery umożliwiające dostęp do prywatnych pól klasy Task
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

    // Metoda do oznaczania zadania jako ukończone
    public void markAsCompleted(final boolean isCompleted) {
        this.isCompleted = isCompleted;  // Ustawia pole isCompleted na true
    }

    // Metoda toString - zwraca opis obiektu Task w postaci czytelnej dla użytkownika
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

    // Metoda compareTo z interfejsu Comparable - porównuje zadania, aby można było je sortować
    @Override
    public int compareTo(final Task other) {
        // Najpierw porównuje zadania na podstawie tego, czy są ukończone
        if (isCompleted != other.isCompleted) {
            return Boolean.compare(isCompleted, other.isCompleted);  // Ukończone zadania są niżej
        }
        // Jeśli zadania mają taki sam status ukończenia, porównuje po priorytecie
        return priority.compareTo(other.priority);  // Zadania z wyższym priorytetem są wyżej
    }
}
