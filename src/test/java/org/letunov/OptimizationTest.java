package org.letunov;

import org.junit.jupiter.api.Test;
import org.letunov.optimization.Optimization;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptimizationTest
{
    @Test
    public void SimplexMethodTest() {
        double[] c = {3, 2};
        double[] b = {20, 24, 3};
        double[][] A = {{-5, 4},
                        {2, 3},
                        {1, -3}};
        Optimization.OptimizationResult firstResult = Optimization.getMaxBySimplexAlgorithm(c, b, A);
        assertAll(
                () -> assertEquals(31.0d, firstResult.getValue()),
                () -> assertEquals(9.0d, firstResult.getU()[0]),
                () -> assertEquals(2.0d, firstResult.getU()[1])
        );
        double[] c2 = {-3, -2};
        double[] b2 = {20, 24, 3};
        double[][] A2 = {{-5, 4},
                {2, 3},
                {1, -3}};
        Optimization.OptimizationResult secondResult = Optimization.getMinBySimplexAlgorithm(c2, b2, A2);
        assertAll(
                () -> assertEquals(31.0d, firstResult.getValue()),
                () -> assertEquals(9.0d, firstResult.getU()[0]),
                () -> assertEquals(2.0d, firstResult.getU()[1])
        );
    }

    @Test
    public void mainCriteriaMethodTest() {
        double[] c = {-1, 2};
        double[] b = {6, -1, 3, -1, 4, -4, 8};
        double[][] A = {{1, 1},
                {-1, 0},
                {1, 0},
                {0, -1},
                {0, 1},
                {-2, -1},
                {-1, 3}};
        Optimization.OptimizationResult result = Optimization.getMaxBySimplexAlgorithm(c, b, A);

        System.out.printf("Максимальное значение функции при заданых ограничениях: %f\n", result.getValue());
        System.out.printf("Оптимальное решение: P(%f, %f)\n", result.getU()[0], result.getU()[1]);
        assertAll(
                () -> assertEquals(1, result.getU()[0]),
                () -> assertEquals(3, result.getU()[1])
        );
    }

    @Test
    public void mainCriteriaMethodExample() {
        double[] c = {1, 1};
        double[] b = {-20, 10, 20, 0, 40, 0, 30, 20, 60};
        double[][] A = {{-1, -1},
                        {1, -2},
                        {-3, 2},
                        {-1, 0},
                        {1, 0},
                        {0, -1},
                        {0, 1},
                        {3, -1},
                        {-1, 3}};
        Optimization.OptimizationResult result = Optimization.getMaxBySimplexAlgorithm(c, b, A);
        System.out.printf("Максимальное значение функции при заданых ограничениях: %f\n", result.getValue());
        System.out.printf("Оптимальное решение: P(%f, %f)\n", result.getU()[0], result.getU()[1]);
    }

    @Test
    public void t() {
        double[] c = {-4, -8};
        double[] b = {3, 2};
        double[][] A = {{1, 1}, {1, -1}};
        Optimization.OptimizationResult result = Optimization.getMinBySimplexAlgorithm(c, b, A);
        System.out.printf("Максимальное значение функции при заданых ограничениях: %f\n", result.getValue());
        System.out.printf("Оптимальное решение: P(%f, %f)\n", result.getU()[0], result.getU()[1]);
    }
}
