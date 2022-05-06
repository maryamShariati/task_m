package ir.amitis.taskManagement.model;

public enum TaskType {

    undefined(0),
    FixedWork(1),
    FixedDuration(2),
    FixedUnits(3);


    private int label;

    TaskType(int label) {
        this.label = label;
    }


    @Override
    public String toString() {
        return "TaskType{" +
                "label=" + label +
                '}';
    }
}
