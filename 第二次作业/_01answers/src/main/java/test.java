import java.util.List;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 文件路径
        String filePath = "instances_01_KP/large_scale/knapPI_1_100_1000_1";
        // 加载物品数据
        List<Item> items = KnapsackDataLoader.loadDataFromFile(filePath);
        // 设置背包容量
        answer_01 knapsack = new answer_01(995);
        while (true) {
            System.out.println("请选择测试的算法：");
            System.out.println("1 - 贪心算法");
            System.out.println("2 - 动态规划算法");
            System.out.println("3 - 暴力法");
            System.out.println("输入#退出测试");
            String choice = scanner.nextLine();
            if (choice.equals("#")) {
                break;
            }
            switch (choice) {
                case "1":
                    long startTimeGreedy = System.currentTimeMillis();
                    Result resultGreedy = knapsack.solveKnapsackProblem(items);
                    long endTimeGreedy = System.currentTimeMillis();
                    long timeGreedy = endTimeGreedy - startTimeGreedy;
                    System.out.println("贪心算法结果: " + resultGreedy + "，耗时: " + timeGreedy + " 毫秒");
                    break;
                case "2":
                    long startTimeDP = System.currentTimeMillis();
                    Result resultDP = knapsack.solveKnapsackProblemDP(items);
                    long endTimeDP = System.currentTimeMillis();
                    long timeDP = endTimeDP - startTimeDP;
                    System.out.println("动态规划算法结果: " + resultDP + "，耗时: " + timeDP + " 毫秒");
                    break;
                case "3":
                    long startTimeBruteForce = System.currentTimeMillis();
                    Result resultBruteForce = knapsack.solveKnapsackProblemBruteForce(items);
                    long endTimeBruteForce = System.currentTimeMillis();
                    long timeBruteForce = endTimeBruteForce - startTimeBruteForce;
                    System.out.println("暴力法结果: " + resultBruteForce + "，耗时: " + timeBruteForce + " 毫秒");
                    break;
                default:
                    System.out.println("无效的选择，请重新输入。");
            }
        }
        scanner.close();
        System.out.println("测试结束");
    }
}
