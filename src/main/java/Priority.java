public enum Priority {
    HIGH("HIGH"),
    MEDIUM("MEDIUM"),
    LOW("LOW");

    private final String description;

    // Konstruktor
    Priority(final String description) {
        this.description = description;
    }

    // Metoda zwracająca opis priorytet
    public String getPriority() {
        return description;
    }
}