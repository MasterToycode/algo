import org.junit.Test;

import java.util.List;

public class test_read
{
//    @Test
//    public void test_Reader(){
//        String filePath = "instances_01_KP/large_scale/knapPI_1_100_1000_1";
//        List<Item> items = KnapsackDataLoader.loadDataFromFile(filePath);
//        for (Item item : items) {
//            System.out.println(item);
//        }
//    }


    @Test
    public void TESTALL(){
        String filePath = "instances_01_KP/large_scale/knapPI_1_500_1000_1";
        List<Item> items = KnapsackDataLoader.loadDataFromFile(filePath);
        answer_01 knapsack = new answer_01(50);
        Result result = knapsack.solveKnapsackProblem(items);
        Result result1 = knapsack.solveKnapsackProblemDP(items);
        Result result2 = knapsack.solveKnapsackProblemBruteForce(items);
        System.out.println(result);
        System.out.println(result1);
        System.out.println(result2);
    }
}
