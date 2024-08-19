package sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Find {
    private List<Integer> numbers = new ArrayList<>();

     public Find(List<Integer> numbers) {
          this.numbers = numbers;
     }

     public Find() {
     }

     public List<Integer> getNumbers() {
          return numbers;
     }

     public void setNumbers(List<Integer> numbers) {
          this.numbers = numbers;
     }

    public List<Integer> answers_1() {
        if (numbers == null || numbers.isEmpty()) {
            return new ArrayList<>();
        }

        int max = Collections.max(numbers);
        int min = Collections.min(numbers);
        int bucketCount = (max - min) / 10 + 1;
        List<List<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        for (int number : numbers) {
            int bucketIndex = (number - min) / 10;
            buckets.get(bucketIndex).add(number);
        }

        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
        }

        List<Integer> sortedNumbers = new ArrayList<>();
        for (List<Integer> bucket : buckets) {
            sortedNumbers.addAll(bucket);
        }

        int startIndex = Math.max(0, sortedNumbers.size() - 10);
        return sortedNumbers.subList(startIndex, sortedNumbers.size());
    }


    public List<Integer> answers_2(){
        if (numbers == null || numbers.size() == 0) {
            return new ArrayList<>();
        }

        int max = Collections.max(numbers);
        int[] count = new int[max + 1];

        // 3. 统计每个数字出现的次数
        for (int number : numbers) {
            count[number]++;
        }

        // 4. 按照计数数组重构排序后的数组
        List<Integer> sortedNumbers = new ArrayList<>();
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                sortedNumbers.add(i);
                count[i]--;
            }
        }

        int startIndex = Math.max(0, sortedNumbers.size() - 10);
        return sortedNumbers.subList(startIndex, sortedNumbers.size());
    }

}
