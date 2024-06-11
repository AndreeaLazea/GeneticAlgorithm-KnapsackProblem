import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class GeneticAlgorithm {
    private KnapsackProblem problem;
    private Random random;
    private int populationSize;
    private double mutationRate;
    private double crossoverRate;
    private int elitismCount;

    public GeneticAlgorithm(KnapsackProblem problem, int populationSize, double mutationRate, double crossoverRate, int elitismCount) {
        this.problem = problem;
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.elitismCount = elitismCount;
        this.random = new Random();
    }

    public GeneticAlgorithm(KnapsackProblem problem) {
        this(problem, 200, 0.015, 0.85, 7);
    }

    public Individual[] initializePopulation() {
        Individual[] population = new Individual[populationSize];
        for (int i = 0; i < populationSize; i++) {
            Individual newIndividual = new Individual(problem);
            newIndividual.generateIndividual();
            population[i] = newIndividual;
        }
        return population;
    }

    public void evalPopulation(Individual[] population) {
        for (Individual individual : population) {
            individual.calcFitness();
        }
    }

    public boolean isTerminationConditionMet(int generationsCount, int maxGenerations) {
        return (generationsCount > maxGenerations);
    }

    //e folosit pt a crea indivizi noi, e un individ
    public Individual selectParent(Individual[] population) {
        Individual[] tournament = new Individual[5];
        for (int i = 0; i < tournament.length; i++) {
            int randomIndex = random.nextInt(populationSize);
            tournament[i] = population[randomIndex];
        }
        Arrays.sort(tournament, (individual1, individual2) -> Double.compare(individual2.getFitness(), individual1.getFitness()));
        return tournament[0];
    }

    //crossover imbina cromozomii a doi parinti pt a face copii noi
    //alegerea aleatoare a unui mic subset de indivizi din populație
    // iar apoi selectarea celui mai bun individ din acest subset pentru a fi părinte.
    public void crossoverPopulation(Individual[] population) {
        for (int i = elitismCount; i < populationSize; i++) {
            if (crossoverRate > Math.random()) {
                Individual parent1 = population[i];
                Individual parent2 = selectParent(population);
                for (int geneIndex = 0; geneIndex < parent1.getChromosomeLength(); geneIndex++) {
                    if (0.5 > Math.random()) {
                        parent1.setGene(geneIndex, parent2.getGene(geneIndex));
                    }
                }
            }
        }
    }

    //Mutând periodic genele indivizilor,
    // se introduce o variație care poate permite descoperirea unor soluții mai bune.
    public void mutatePopulation(Individual[] population) {
        for (int i = elitismCount; i < populationSize; i++) {
            for (int geneIndex = 0; geneIndex < population[i].getChromosomeLength(); geneIndex++) {
                if (mutationRate > Math.random()) {
                    boolean gene = random.nextBoolean();
                    population[i].setGene(geneIndex, gene);
                }
            }
        }
    }
    private double calculateAverageFitness(Individual[] population) {
        double totalFitness = 0;
        for (Individual individual : population) {
            totalFitness += individual.getFitness();
        }
        return totalFitness / population.length;
    }
    public Individual run() {
        int generation = 1;
        Individual[] population = initializePopulation();
        evalPopulation(population);

        while (!isTerminationConditionMet(generation, 100)) {
            crossoverPopulation(population);
            mutatePopulation(population);
            evalPopulation(population);

            double bestFitness = Arrays.stream(population).mapToDouble(Individual::getFitness).max().orElse(0);
            double averageFitness = calculateAverageFitness(population);
            System.out.println("Generation " + generation + ": Best fitness = " + bestFitness + ", Average fitness = " + averageFitness);
            generation++;
        }

        // Return the best individual found
        return Arrays.stream(population).max(Comparator.comparingDouble(Individual::getFitness)).orElse(null);
    }




}
