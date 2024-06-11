public class Individual {
    private boolean[] chromosome;
    private double fitness = -1;
    private KnapsackProblem problem;

    public Individual(KnapsackProblem problem) {
        this.problem = problem;
        this.chromosome = new boolean[problem.getItems().size()];
    }


    public void generateIndividual() {
        for (int geneIndex = 0; geneIndex < this.chromosome.length; geneIndex++) {
            this.chromosome[geneIndex] = Math.random() > 0.5;
        }
    }

    public boolean[] getChromosome() {
        return this.chromosome;
    }

    public boolean getGene(int index) {
        return this.chromosome[index];
    }

    public void setGene(int index, boolean value) {
        this.chromosome[index] = value;
    }

    public int getChromosomeLength() {
        return this.chromosome.length;
    }


    public void calcFitness() {
        int totalWeight = 0;
        int totalValue = 0;
        for (int geneIndex = 0; geneIndex < this.chromosome.length; geneIndex++) {
            if (getGene(geneIndex)) {
                totalWeight += problem.getItems().get(geneIndex).getWeight();
                totalValue += problem.getItems().get(geneIndex).getValue();
            }
        }
        if (totalWeight > problem.getMaxWeight()) {
            this.fitness = 0;
        } else {
            this.fitness = totalValue;
        }
    }

    public double getFitness() {
        return this.fitness;
    }

    @Override
    public String toString() {
        StringBuilder chromosomeString = new StringBuilder();
        for (boolean gene : chromosome) {
            chromosomeString.append(gene ? "1" : "0");
        }
        return String.format("Cromozom: %s, Fitness: %.2f", chromosomeString.toString(), getFitness());
    }

}
