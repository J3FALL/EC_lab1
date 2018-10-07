package simple;

import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

        List<Double> child1 = new ArrayList<>();
        List<Double> child2 = new ArrayList<>();

        for (int idx = 0; idx < p1.length; idx++) {
            child1.add(p1[idx] * alpha + p2[idx] * (1.0 - alpha));
            child2.add(p1[idx] * alpha + p2[idx] * (1.0 - alpha));
        }

        children.add(child1.toArray(new Double[0]));
        children.add(child2.toArray(new Double[0]));

        return children;
    }
}
