package simple;

import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleFactory extends AbstractCandidateFactory<Double[]> {
    private int dimension;

    private double min, max;

    public SimpleFactory(int dimension, double min, double max) {
        this.dimension = dimension;
        this.min = min;
        this.max = max;
    }

    public Double[] generateRandomCandidate(Random random) {
        List<Double> solution = Stream
                .generate(() -> randomDouble(random))
                .limit(dimension)
                .collect(Collectors.toList());
        return solution.toArray(new Double[0]);
    }

    private Double randomDouble(Random random) {
        return min + (max - min) * random.nextDouble();
    }

}
