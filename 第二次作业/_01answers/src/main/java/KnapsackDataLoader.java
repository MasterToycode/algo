import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KnapsackDataLoader {

    public static List<Item> loadDataFromFile(String filePath)
    {
        List<Item> items = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            while ((line = br.readLine()) != null) {
                // 按空格分割每一行的数据
                String[] parts = line.split("\\s+");
                if (parts.length == 2) {
                    int value = Integer.parseInt(parts[0]);
                    int weight = Integer.parseInt(parts[1]);
                    items.add(new Item(value, weight));
                }
            }
        }
        catch
        (IOException e) {
            e.printStackTrace();
        }
        return items;
    }
}
