import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class answer_01 {
    private int capacity;
    public answer_01(int capacity) {
        this.capacity = capacity;
    }
    public answer_01() {
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }





    //使用贪心算法
    public Result solveKnapsackProblem(List<Item> items)
    {
        // 按照单位重量价值从高到低排序
        Collections.sort(items,
                Comparator.comparingDouble(Item::getValuePerWeight).reversed());
        int totalValue = 0;
        int totalWeight = 0;
        List<Item> selectedItems = new ArrayList<>();
        for (Item item : items) {
            if (totalWeight + item.weight <= capacity) {
                selectedItems.add(item);
                totalWeight += item.weight;
                totalValue += item.value;
            }
        }
        return new Result(totalValue, selectedItems);
    }



    // 使用动态规划算法
    public Result solveKnapsackProblemDP(List<Item> items) {
        int n = items.size();
        int[][] dp = new int[n + 1][capacity + 1];
        //dp[i][w] 表示前 i 个物品在背包容量为 w 时的最大价值。
        // 构建动态规划表
        for (int i = 1; i <= n; i++) {
            int value = items.get(i - 1).value;
            int weight = items.get(i - 1).weight;
            for (int w = 1; w <= capacity; w++) {
                if (weight <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weight] + value);
                   // 如果当前物品的重量 weight 小于等于当前背包容量 w，则我们有两种选择：
                   // 不放入当前物品：最大价值等于 dp[i-1][w]。
                   // 放入当前物品：最大价值等于 dp[i-1][w-weight] + value。
                    //取这两种选择的最大值作为 dp[i][w]。
                } else {
                    dp[i][w] = dp[i - 1][w]; // 当前物品的重量大于背包容量，只能不放入当前物品
                }
            }
        }
        // 追踪所选择的物品
        List<Item> selectedItems = new ArrayList<>();
        int w = capacity;
        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                selectedItems.add(items.get(i - 1));
                w -= items.get(i - 1).weight;
            }
        }
        int totalValue = dp[n][capacity];
        return new Result(totalValue, selectedItems);
    }





    // 使用暴力法解决0-1背包问题
    public Result solveKnapsackProblemBruteForce(List<Item> items) {
        return knapsackHelper(items, capacity, 0);
    }
    private Result knapsackHelper(List<Item> items, int capacity, int currentIndex) {
        // 基本条件：当没有更多物品或容量耗尽时
        if (currentIndex >= items.size() || capacity <= 0) {
            return new Result(0, new ArrayList<>());
        }
        // 选择当前物品
        Item currentItem = items.get(currentIndex);
        Result resultWithCurrent = new Result(0, new ArrayList<>());
        if (currentItem.weight <= capacity) {
            resultWithCurrent = knapsackHelper(items, capacity - currentItem.weight, currentIndex + 1);
            resultWithCurrent.totalValue += currentItem.value;
            resultWithCurrent.selectedItems.add(currentItem);
        }
        // 不选择当前物品
        Result resultWithoutCurrent = knapsackHelper(items, capacity, currentIndex + 1);
        // 选择价值更大的方案
        if (resultWithCurrent.totalValue > resultWithoutCurrent.totalValue) {
            return resultWithCurrent;
        } else {
            return resultWithoutCurrent;
        }
    }


}
