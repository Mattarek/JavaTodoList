import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        // Tworzenie instancji skanera, który będzie pobierał dane od użytkownika
        final Scanner scanner = new Scanner(System.in);
        // Tworzenie instancji listy zadań ToDoList
        final ToDoList toDoList = new ToDoList();


        // Zmienna kontrolująca pętlę programu
        boolean running = true;

        final FileHandler fileHandler = new FileHandler();

        // Wczytanie zadań z pliku do listy przy starcie programu
        toDoList.getTasks().addAll(fileHandler.loadTasksFromFile());
        // Pętla programu - główne menu
        while (running) {
            // Wyświetlanie menu użytkownikowi
            System.out.println("\n--- ToDo List Menu ---");
            System.out.println("1. Wyświetl zadania");
            System.out.println("2. Dodaj zadanie");
            System.out.println("3. Usuń zadanie");
            System.out.println("4. Oznacz zadanie jako ukończone");
            System.out.println("5. Filtruj zadania (ukończone/nieukończone)");
            System.out.println("6. Sortuj zadania (priorytet, termin)");
            System.out.println("7. Wyświetl zadania wg kategorii");
            System.out.println("8. Zapisz zadania do pliku");
            System.out.println("9. Wyjście");
            System.out.print("Wybierz opcję: ");

            // Odczyt wyboru użytkownika, definiujemy, że nasza wartość będzie intem, to daje nam znak zachęty, aby wpisać ilczbę
            // to przyjmuje tylko pierwszą wartość
            final int choice = scanner.nextInt();

            // Zabezpieczenie przed błędem przy wczytywaniu znaków po liczbie
            scanner.nextLine(); // to mówi, zapisz to co masz i przejdź do dalszego działania programu

            // Przełącznik opcji, który wykonuje odpowiednie działania w zależności od wyboru użytkownika
            switch (choice) {
                case 1:
                    // Wyświetlenie listy zadań
                    toDoList.displayTasks();
                    break;

                case 2:
                    // Dodawanie nowego zadania
                    System.out.print("Podaj nazwę zadania: ");
                    final String name = scanner.nextLine();

                    System.out.print("Podaj opis zadania: ");
                    final String description = scanner.nextLine();

                    System.out.print("Podaj priorytet (LOW, MEDIUM, HIGH): ");
                    final Priority priority = Priority.valueOf(scanner.nextLine().toUpperCase());

                    System.out.print("Podaj termin wykonania (YYYY-MM-DD) lub 'null': ");
                    final String deadlineStr = scanner.nextLine();
                    // Jeśli użytkownik wpisze 'null', deadline jest ustawiany na null
                    final LocalDate deadline = deadlineStr.equalsIgnoreCase("null") ? null : LocalDate.parse(deadlineStr);

                    System.out.print("Podaj kategorię zadania: ");
                    final String category = scanner.nextLine();

                    // Tworzenie nowego zadania i dodawanie do listy
                    final Task task = new Task(name, description, priority, deadline, category);
                    toDoList.addTask(task);
                    break;

                case 3:
                    // Usuwanie zadania
                    final int taskIndexToRemove = scanner.nextInt() - 1;
                    toDoList.removeTask(taskIndexToRemove);
                    break;

                case 4:
                    // Oznaczanie zadania jako ukończone
                    final int taskIndexToComplete = scanner.nextInt() - 1;
                    toDoList.markTaskAsCompleted(taskIndexToComplete);
                    break;

                case 5:
                    final int filterChoice = scanner.nextInt();
                    scanner.nextLine(); // Oczyszczenie bufora

                    // Wyświetlanie zadań w zależności od wyboru filtru
                    if (filterChoice == 1) {
                        toDoList.displayCompletedTasks();
                    } else if (filterChoice == 2) {
                        toDoList.displayPendingTasks();
                    }
                    break;

                case 6:
                    final int sortChoice = scanner.nextInt();
                    if (sortChoice == 1) {
                        toDoList.sortTasksByPriority(); // Sortowanie według priorytetu
                    } else if (sortChoice == 2) {
                        toDoList.sortTasksByDeadline(); // Sortowanie według terminu
                    }
                    break;

                case 7:
                    // Wyświetlanie zadań według kategorii
                    final String categoryFilter = scanner.nextLine();
                    toDoList.displayTasksByCategory(categoryFilter);
                    break;

                case 8:
                    // Zapis zadań do pliku
                    fileHandler.saveTasksToFile(toDoList.getTasks());
                    break;

                case 9:
                    // Wyjście z programu - zapis zadań do pliku i zakończenie pętli
                    fileHandler.saveTasksToFile(toDoList.getTasks());
                    running = false;
                    break;
            }
        }
        // Zamknięcie skanera
        scanner.close();
    }
}
