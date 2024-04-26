package org.letunov.optimization;

import java.util.Arrays;

public class LinearProgramming {
    private LinearProgramming() {}

    public static SimplexAlgorithmResult getMaxBySimplexAlgorithm(double[] c, double[] b, double[][] A) {
        double[] criteriaCopy = Arrays.copyOf(c, c.length);
        for (int i = 0; i < c.length; i++)
            criteriaCopy[i] *= -1;
        return getMinBySimplexAlgorithm(criteriaCopy, b, A);
    }
    public static SimplexAlgorithmResult getMinBySimplexAlgorithm(double[] c, double[] b, double[][] A) {
        if (A == null)
            throw new RuntimeException("Matrix A cannot be null");
        if (A[0].length != c.length)
            throw  new RuntimeException("Vector c components count must equals A matrix's column count");
        if (A.length != b.length)
            throw  new RuntimeException("Vector b components count must equals A matrix's row count");

        double[][] simplexTable = new double[A.length+1][A.length + c.length+1];
        int[] basisVarIndexes = new int[A.length];
        for (int i = 0; i < simplexTable.length; i++) {
            if (i == simplexTable.length-1) {
                System.arraycopy(c, 0, simplexTable[i], 1, c.length);
                break;
            }
            for (int j = 0; j < simplexTable[0].length; j++) {
                if (j == 0)
                    simplexTable[i][j] = b[i];
                else if (j < c.length+1)
                    simplexTable[i][j] = A[i][j-1];
                else if (i == j - c.length - 1) {
                    simplexTable[i][j] = 1;
                    break;
                }
            }
        }
        for (int i = 0; i < A.length; i++)
            basisVarIndexes[i] = c.length+i;
        while (isBasicContainNegative(simplexTable)) {
            int indexOfMinNegativeBasic = getIndexOfMinNegativeBasic(simplexTable);
            int indexOfMinNegativeInBasic = getIndexOfMinNegativeInBasic(simplexTable[indexOfMinNegativeBasic]);
            if (indexOfMinNegativeInBasic == -1)
                return null;
            double leadingEl = simplexTable[indexOfMinNegativeBasic][indexOfMinNegativeInBasic];
            for (int i = 0; i < simplexTable[0].length; i++)
                if (simplexTable[indexOfMinNegativeBasic][i] != 0)
                    simplexTable[indexOfMinNegativeBasic][i] /= leadingEl;

            for (int i = 0; i < simplexTable.length; i++) {
                if (i == indexOfMinNegativeBasic)
                    continue;
                double leadingElForThisRow = simplexTable[i][indexOfMinNegativeInBasic];
                for (int j = 0; j < simplexTable[0].length; j++)
                    simplexTable[i][j] = simplexTable[i][j] - leadingElForThisRow * simplexTable[indexOfMinNegativeBasic][j];
            }
            basisVarIndexes[indexOfMinNegativeBasic] = indexOfMinNegativeInBasic-1;
        }
        while (isHasNegative(simplexTable[simplexTable.length - 1])) {
            int varToEnterColumnInd = getIndexOfMinEl(simplexTable[simplexTable.length-1]);
            int varToGetOutRowInd = -1;
            double minRelValue = Double.MAX_VALUE;
            for (int i = 0; i < simplexTable.length-1; i++)
                if (simplexTable[i][varToEnterColumnInd] > 0
                        && simplexTable[i][0] / simplexTable[i][varToEnterColumnInd] < minRelValue) {
                    varToGetOutRowInd = i;
                    minRelValue = simplexTable[i][0] / simplexTable[i][varToEnterColumnInd];
                }
            if (varToGetOutRowInd == -1)
                break;
            double leadingEl = simplexTable[varToGetOutRowInd][varToEnterColumnInd];
            for (int i = 0; i < simplexTable[0].length; i++)
                simplexTable[varToGetOutRowInd][i] /= leadingEl;
            for (int i = 0; i < simplexTable.length; i++) {
                if (i == varToGetOutRowInd)
                    continue;
                double leadingElForThisRow = simplexTable[i][varToEnterColumnInd];
                for (int j = 0; j < simplexTable[0].length; j++) {
                    simplexTable[i][j] = simplexTable[i][j] -
                            leadingElForThisRow * simplexTable[varToGetOutRowInd][j];
                }
            }
            basisVarIndexes[varToGetOutRowInd] = varToEnterColumnInd-1;
        }

        double[] u = new double[c.length];
        for (int i = 0; i < basisVarIndexes.length; i++)
            if (basisVarIndexes[i] < u.length)
                u[basisVarIndexes[i]] = simplexTable[i][0];
        return new SimplexAlgorithmResult(simplexTable[simplexTable.length-1][0], u);
    }

    private static boolean isHasNegative(double[] arr) {
        for (int i = 1; i < arr.length; i++)
            if (arr[i] < 0)
                return true;
        return false;
    }
    private static int getIndexOfMinNegativeInBasic(double[] arr) {
        double min = Double.MAX_VALUE;
        int ind = -1;
        for (int i = 1; i < arr.length; i++)
            if (arr[i] < 0 && arr[i] < min) {
                min = arr[i];
                ind = i;
            }
        return ind;
    }

    private static int getIndexOfMinNegativeBasic(double[][] table) {
        double min = Double.MAX_VALUE;
        int ind = -1;
        for (int i = 0; i < table.length-1; i++)
            if (table[i][0] < 0 && table[i][0] < min) {
                min = table[i][0];
                ind = i;
            }
        return ind;
    }

    private static boolean isBasicContainNegative(double[][] table) {
        for (int i = 0; i < table.length-1; i++)
            if (table[i][0] < 0)
                return true;
        return false;
    }

    private static int getIndexOfMinEl(double[] arr) {
        int ind = -1;
        double min = Double.MAX_VALUE;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
                ind = i;
            }
        }
        return ind;
    }

    public static class SimplexAlgorithmResult {
        private double value;
        private double[] u;
        public SimplexAlgorithmResult(double value, double[] u) {
            this.value = value;
            this.u = u;
        }
        public double[] getU() {
            return u;
        }
        public void setU(double[] u) {
            this.u = u;
        }
        public double getValue() {
            return value;
        }
        public void setValue(double value) {
            this.value = value;
        }
    }
}
