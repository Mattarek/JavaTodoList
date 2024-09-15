import java.time.LocalDate;

public class Task implements Comparable<Task> {
	// Comparable<T> to interfejs w Javie, który umożliwia porównywanie obiektów
	// danego typu w sposób naturalny, czyli pozwala na ustalenie domyślnego porządku
	// obiektów. Interfejs ten jest szczególnie przydatny, kiedy chcesz sortować
	// kolekcje obiektów (np. List<Task>) lub używać obiektów w strukturach danych,
	// które wymagają porównywania (np. TreeSet, TreeMap).

	// Enum Priority definiuje priorytety zadań
	public enum Priority {
		LOW, MEDIUM, HIGH;
	}

	// Definicja zmiennych dla klasy Task
	private String name;        // Nazwa zadania
	private String description; // Opis zadania
	private Priority priority;  // Priorytet zadania (LOW, MEDIUM, HIGH)
	private LocalDate deadline; // Termin wykonania zadania
	private boolean isCompleted; // Informacja, czy zadanie zostało ukończone
	private String category;     // Kategoria zadania

	// Konstruktor klasy Task - tworzy nowe zadanie
	public Task(String name, String description, Priority priority, LocalDate deadline, String category) {
		this.name = name;
		this.description = description;
		this.priority = priority;
		this.deadline = deadline;
		this.category = category;
	}

	// Gettery umożliwiające dostęp do prywatnych pól klasy Task
	public String getName(){
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Priority getPriority(){
		return priority;
	}

	public LocalDate getDeadline(){
		return deadline;
	}

	public boolean isCompleted(){
		return isCompleted;
	}

	public String getCategory() {
		return category;
	}

	// Metoda do oznaczania zadania jako ukończone
	public boolean markAsCompleted(){
		return this.isCompleted = true;  // Ustawia pole isCompleted na true
	}

	// Metoda toString - zwraca opis obiektu Task w postaci czytelnej dla użytkownika
	@Override
	public String toString(){
		return String.format("%s (%s) [Priority: %s, Deadline: %s, Category: %s]",
				name,
				isCompleted ? "Done" : "Not Done",  // Sprawdza, czy zadanie jest ukończone
				priority,
				(deadline != null ? deadline : "None"),  // Jeśli brak deadline, wyświetla "None"
				category
		);
	}

	// Metoda compareTo z interfejsu Comparable - porównuje zadania, aby można było je sortować
	@Override
	public int compareTo(Task other){
		// Najpierw porównuje zadania na podstawie tego, czy są ukończone
		if(this.isCompleted != other.isCompleted){
			return Boolean.compare(this.isCompleted, other.isCompleted);  // Ukończone zadania są niżej
		}
		// Jeśli zadania mają taki sam status ukończenia, porównuje po priorytecie
		return this.priority.compareTo(other.priority);  // Zadania z wyższym priorytetem są wyżej
	}
}
