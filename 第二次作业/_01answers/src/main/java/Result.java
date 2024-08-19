import java.util.List;

public class Result {
    int totalValue;
    List<Item> selectedItems;

    public Result(int totalValue, List<Item> selectedItems) {
        this.totalValue = totalValue;
        this.selectedItems = selectedItems;
    }

    @Override
    public String toString() {
        return "Result{" +
                "totalValue=" + totalValue +
                ", selectedItems=" + selectedItems +
                '}';
    }
}
