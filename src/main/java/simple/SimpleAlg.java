package simple;

import org.uncommons.watchmaker.framework.*;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
import org.uncommons.watchmaker.framework.termination.GenerationCount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SimpleAlg {
    public static void main(String[] args) {
        int dimension = 2; // dimension of problem
        int populationSize = 10; // size of population
        int generations = 10; // number of generations

        Random random = new Random(); // random

        CandidateFactory<Double[]> factory = new SimpleFactory(dimension, -5, 5); // generation of solutions

        ArrayList<EvolutionaryOperator<Double[]>> operators = new ArrayList<EvolutionaryOperator<Double[]>>();
        operators.add(new SimpleCrossover(0.7f));
        operators.add(new SimpleMutation(0.7f));

        EvolutionPipeline<Double[]> pipeline = new EvolutionPipeline<>(operators);
        SelectionStrategy<Object> selection = new RouletteWheelSelection();
        FitnessEvaluator<Double[]> evaluator = new FitnessFunction(dimension);

        EvolutionEngine<Double[]> algorithm = new SteadyStateEvolutionEngine<Double[]>(
                factory, pipeline, evaluator, selection, populationSize, false, random);

        algorithm.addEvolutionObserver(new EvolutionObserver() {
            public void populationUpdate(PopulationData populationData) {
                double bestFit = populationData.getBestCandidateFitness();
                System.out.println("Generation " + populationData.getGenerationNumber() + ": " + bestFit);
                System.out.println("\tBest solution = " + Arrays.toString((Double[]) populationData.getBestCandidate()));
                System.out.println("\tPop size = " + populationData.getPopulationSize());
            }
        });

        TerminationCondition terminate = new GenerationCount(generations);
        algorithm.evolve(populationSize, 1, terminate);
    }
}
