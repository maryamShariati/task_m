package ir.amitis.taskManagement.model;

public enum Priority {
    LOW(1),MEDIUM(2),HIGH(3),HIGHEST(4);;


    private int label;

    Priority(int label) {
        this.label = label;
    }

    public int getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "TaskPriority{" +
                "label=" + label +
                '}';
    }
}
