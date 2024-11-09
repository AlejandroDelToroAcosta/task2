package benchmark;

import matrix.mul.CSCMatrixMul.CSCMatrix;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.Random;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class CSCMatrixMulBenchmark {

    private CSCMatrix cscMatrixA;
    private CSCMatrix cscMatrixB;

    @Param({"100", "500", "1024"})  // Define los tamaños de las matrices a probar
    private int matrixSize;

    @Setup(Level.Trial)
    public void setup() {
        double[][] matrixA = generateRandomSparseMatrix(matrixSize, matrixSize, 0.1);
        double[][] matrixB = generateRandomSparseMatrix(matrixSize, matrixSize, 0.1);

        // Convertimos las matrices a formato CSC
        cscMatrixA = matrix.mul.CSCMatrixMul.convertToCSC(matrixA);
        cscMatrixB = matrix.mul.CSCMatrixMul.convertToCSC(matrixB);
    }

    @Benchmark
    @Fork(1)
    @Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public CSCMatrix benchmarkCSCMultiplication() {
        return cscMatrixA.multiply(cscMatrixB);
    }

    private double[][] generateRandomSparseMatrix(int rows, int cols, double sparsity) {
        double[][] matrix = new double[rows][cols];
        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (random.nextDouble() > sparsity) {
                    matrix[i][j] = random.nextDouble() * 10;
                }
            }
        }
        return matrix;
    }
}
