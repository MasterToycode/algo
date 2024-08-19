import org.junit.Test;
import sort.Find;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test1 {
    @Test
    public void test_queston_2(){
        Random rand = new Random();
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < 1000000; i++)
        {
            numbers.add(rand.nextInt(0,100000000));
        }
        List<Integer> answers1 = new Find(numbers).answers_1();
        System.out.println(answers1);

        List<Integer> answers2 = new Find(numbers).answers_2();
        System.out.println(answers2);
    }
}
