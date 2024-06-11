import java.util.List;

public class KnapsackProblem {
    private List<Item> items;
    private int maxWeight;

    public KnapsackProblem(List<Item> items, int maxWeight) {
        this.items = items;
        this.maxWeight = maxWeight;
    }

    public List<Item> getItems() {
        return items;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public int calculateFitness(boolean[] solution) {
        int totalWeight = 0;
        int totalValue = 0;
        for (int i = 0; i < solution.length; i++) {
            if (solution[i]) {
                totalWeight += items.get(i).getWeight();
                totalValue += items.get(i).getValue();
            }
        }
        return (totalWeight <= maxWeight) ? totalValue : 0;
    }

}