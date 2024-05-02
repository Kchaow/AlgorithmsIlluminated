package org.letunov.optimization;

import java.util.Arrays;

public class MultiCriteriaOptimization {
    public static double[] getOptimalBySuccessiveConcessionMethod(double[][] criterias,
                                                                  int[] priorities,
                                                                  double[][] boundaries,
                                                                  double[] boundariesValues,
                                                                  double[] delta) {
        if (criterias.length == 0)
            throw new RuntimeException("criterias cannot be empty");
        if (priorities.length == 0)
            throw new RuntimeException("priorities cannot be empty");
        if (criterias[0].length == 0)
            throw new RuntimeException("criterias column number cannot equals zero");
        if (boundaries[0].length == 0)
            throw new RuntimeException("boundaries column number cannot equals zero");
        if (boundaries[0].length != criterias[0].length)
            throw new RuntimeException("boundaries column number must equals number of criteria plus one");
        if (criterias.length != priorities.length)
            throw new RuntimeException("priorities number must equals criteria number");
        if (delta.length != criterias.length-1)
            throw new RuntimeException("delta number must equals criterias number minus one");
        if (boundariesValues.length != boundaries.length)
            throw new RuntimeException("boundariesValues must equals boundaries row number");

        double[][] boundariesCopy = new double[boundaries.length][0];
        double[] boundariesValuesCopy = Arrays.copyOf(boundariesValues, boundariesValues.length);
        for (int i = 0; i < boundaries.length; i++)
            boundariesCopy[i] = Arrays.copyOf(boundaries[i], boundaries[i].length);
        Optimization.OptimizationResult optimizationResult
                = new Optimization.OptimizationResult(-1, new double[0]);
        for (int i = 0, deltaInd = 0; i < criterias.length; i++, deltaInd++) {
            optimizationResult =
                    Optimization.getMaxBySimplexAlgorithm(criterias[priorities[i]], boundariesValuesCopy, boundariesCopy);
            if (i == criterias.length-1)
                break;
            boundariesValuesCopy =
                    addToArray(boundariesValuesCopy, -1 * (optimizationResult.getValue() - delta[priorities[i]]));
            double[] additionalBoundaries = new double[boundariesCopy[0].length];
            for (int j = 0; j < additionalBoundaries.length; j++)
                additionalBoundaries[j] = -1 * criterias[priorities[i]][j];
            boundariesCopy = appendRowToMatrix(boundariesCopy, additionalBoundaries);
        }
        return optimizationResult.getU();
    }

    private static double[] addToArray(double[] arr, double el) {
        double[] newArr = Arrays.copyOf(arr, arr.length+1);
        newArr[newArr.length-1] = el;
        return newArr;
    }
    private static double[][] appendRowToMatrix(double[][] matrix, double[] row) {
        double[][] newMatrix = Arrays.copyOf(matrix, matrix.length+1);
        newMatrix[newMatrix.length-1] = row;
        return newMatrix;
    }
}
