package simple;

import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleCrossover extends AbstractCrossover<Double[]> {

    private float alpha;

    protected SimpleCrossover(float alpha) {
        super(1);
        this.alpha = alpha;
    }

    @Override
    protected List<Double[]> mate(Double[] p1, Double[] p2, int i, Random random) {
        return wholeArithmeticRecombination(p1, p2);
    }

    private List<Double[]> wholeArithmeticRecombination(Double[] p1, Double[] p2) {
        ArrayList children = new ArrayList();

        List<Double> child1 = Stream.of(p1)
                .map(val -> val * alpha)
                .collect(Collectors.toList());
        List<Double> child2 = Stream.of(p2)
                .map(val -> val * (1.0 - alpha))
                .collect(Collectors.toList());

        children.add(child1.toArray(new Double[0]));
        children.add(child2.toArray(new Double[0]));

        return children;
    }
}
