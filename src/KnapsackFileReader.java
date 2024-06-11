import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class KnapsackFileReader {
    public static KnapsackProblem readProblemFromFile(String filePath) throws Exception {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        List<Item> items = new ArrayList<>();
        int maxWeight = Integer.parseInt(lines.remove(lines.size() - 1));

        for (String line : lines.subList(1, lines.size())) {
            String[] parts = line.trim().split("\\s+");
            int weight = Integer.parseInt(parts[1]);
            int value = Integer.parseInt(parts[2]);
            items.add(new Item(weight, value));
        }

        return new KnapsackProblem(items, maxWeight);
    }
}
