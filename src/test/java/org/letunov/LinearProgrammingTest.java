package org.letunov;

import org.junit.jupiter.api.Test;
import org.letunov.optimization.LinearProgramming;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinearProgrammingTest
{
    @Test
    public void SimplexMethodTest()
    {
        double[] c = {3, 2};
        double[] b = {20, 24, 3};
        double[][] A = {{-5, 4},
                        {2, 3},
                        {1, -3}};
        LinearProgramming.SimplexAlgorithmResult firstResult = LinearProgramming.getMaxBySimplexAlgorithm(c, b, A);
        assertAll(
                () -> assertEquals(31.0d, firstResult.value()),
                () -> assertEquals(9.0d, firstResult.u()[0]),
                () -> assertEquals(2.0d, firstResult.u()[1])
        );
        double[] c2 = {-3, -2};
        double[] b2 = {20, 24, 3};
        double[][] A2 = {{-5, 4},
                {2, 3},
                {1, -3}};
        LinearProgramming.SimplexAlgorithmResult secondResult = LinearProgramming.getMinBySimplexAlgorithm(c2, b2, A2);
        assertAll(
                () -> assertEquals(31.0d, firstResult.value()),
                () -> assertEquals(9.0d, firstResult.u()[0]),
                () -> assertEquals(2.0d, firstResult.u()[1])
        );
    }
}
