package org.letunov;

import org.junit.jupiter.api.Test;
import org.letunov.optimization.MultiCriteriaOptimization;

import static org.junit.jupiter.api.Assertions.*;

public class MultiCriteriaOptimizationTest {
    private final double EPSILON = 0.000001d;
    @Test
    void getOptimalBySuccessiveConcessionMethodTest() {
        int[] priorities = {0, 1, 2};
        double[] delta = {3, 5d/3d};
        double[][] criterias = {{-1, 2},
                                {2, 1},
                                {1, -3}};
        double[] boundariesValues = {6, -1, 3, -1, 4};
        double[][] boundaries = {{1, 1},
                                 {-1, 0},
                                 {1, 0},
                                 {0, -1},
                                 {0, 1}};
        double[] pareto = MultiCriteriaOptimization.getOptimalBySuccessiveConcessionMethod(criterias,
                priorities, boundaries, boundariesValues, delta);
        System.out.printf("Точка Парето: (%f, %f)%n", pareto[0], pareto[1]);
        assertAll(
                () -> assertTrue(Math.abs(pareto[0] - 2d) < EPSILON),
                () -> assertTrue(Math.abs(pareto[1] - 3d) < EPSILON)
        );
    }
}
