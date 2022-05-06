package ir.amitis.taskManagement.model;

public enum Sex {
    MALE(1),FEMALE(2);

   private final int item;

    Sex(int item) {
        this.item = item;
    }



    @Override
    public String toString() {
        return "Sex{" +
                "item=" + item +
                '}';
    }
}
