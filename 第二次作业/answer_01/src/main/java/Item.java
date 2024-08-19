public class Item {
    int value; // 物品的价值
    int weight; // 物品的重量

    public Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    @Override
    public String toString()
    {
        return "Item{" +
                "value=" + value +
                ", weight=" + weight +
                '}';
    }
}