package readability;

enum Request {
    ARI("Automated Readability Index"),
    FK("Flesch–Kincaid readability tests"),
    SMOG("Simple Measure of Gobbledygook"),
    CL("Coleman–Liau index"),
    ALL(null);

    private final String name;

    Request(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
