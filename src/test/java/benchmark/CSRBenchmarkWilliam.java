package benchmark;

import matrix.mul.model.WilliamMatrixReader; // Importa MatrixReader desde matrixmultiplication
import matrix.mul.CSRMatrixMul.CSRMatrix;
import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class CSRBenchmarkWilliam {

    private static CSRMatrix williamsMatrix;

    @Setup(Level.Trial)
    public static void setup() throws IOException {
        String matrixFilePath = "C:\\Users\\aadel\\Desktop\\GCID\\Tercero\\BD\\Ind.Assignments\\MatrixOptimization\\WilliamMatrix\\mc2depi.mtx";
        williamsMatrix = WilliamMatrixReader.loadMatrixFromMTX(matrixFilePath);
    }

    @Benchmark
    @Warmup(iterations = 0)
    @Measurement(iterations = 1)
    public void benchmarkMatrixMultiplication() {
        System.out.println("Hola");
    }
}
