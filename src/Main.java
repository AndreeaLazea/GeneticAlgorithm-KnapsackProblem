public class Main {
    public static void main(String[] args) {
        try {
            KnapsackProblem problem20 = KnapsackFileReader.readProblemFromFile("rucsac-20.txt");
            KnapsackProblem problem200 = KnapsackFileReader.readProblemFromFile("rucsac-200.txt");


            GeneticAlgorithm ga20 = new GeneticAlgorithm(problem20);
            GeneticAlgorithm ga200 = new GeneticAlgorithm(problem200);

             Individual bestSolution20 = ga20.run();
             Individual bestSolution200 = ga200.run();
             System.out.println("Best solution for 20 items: " + bestSolution20);
             System.out.println("Best solution for 200 items: " + bestSolution200);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
