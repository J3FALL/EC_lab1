package simple;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleMutation implements EvolutionaryOperator<Double[]> {
    private float mutatePopulationRate;
    private float mutateValueRate;
    private int mutationRange = 2;
    private int min = -5, max = 5;

    public SimpleMutation(float mutatePopulationRate, float mutateValueRate) {
        super();
        this.mutatePopulationRate = mutatePopulationRate;
        this.mutateValueRate = mutateValueRate;
    }

    @Override
    public List<Double[]> apply(List<Double[]> population, Random random) {
        return population.stream()
                .map(p -> mutated(p, random))
                .collect(Collectors.toList());
    }

    private Double[] mutated(Double[] individ, Random random) {
        if (random.nextDouble() < mutatePopulationRate) {
            return individ;
        } else {
            return Stream.of(individ)
                    .map(val -> newValue(val, random))
                    .collect(Collectors.toList())
                    .toArray(new Double[0]);
        }
    }

    private Double newValue(Double oldValue, Random random) {
        if (random.nextDouble() < mutateValueRate) {
            Double result = oldValue + random.nextGaussian() * mutationRange;
            return ensureRange(result);
        } else {
            return oldValue;
        }

    }

    private double ensureRange(Double value) {
        return Math.min(Math.max(value, min), max);
    }
}
