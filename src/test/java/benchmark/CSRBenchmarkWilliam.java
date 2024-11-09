package benchmark;

import matrix.mul.model.WilliamMatrixReader; // Importa MatrixReader desde matrixmultiplication
import matrix.mul.CSRMatrixMul.CSRMatrix;
import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Warmup(iterations = 0)       // Configura para 0 iteraciones de calentamiento
@Measurement(iterations = 1)  // Configura para 1 iteración de medición
@Fork(1)                      // Configura para 1 solo fork
public class CSRBenchmarkWilliam {

    private static CSRMatrix williamsMatrix;

    @Setup(Level.Trial)
    public static void setup() throws IOException {
        String matrixFilePath = "C:\\Users\\aadel\\Desktop\\GCID\\Tercero\\BD\\Ind.Assignments\\MatrixOptimization\\WilliamMatrix\\mc2depi.mtx";
        williamsMatrix = WilliamMatrixReader.loadMatrixFromMTX(matrixFilePath);
    }

    @Benchmark
    public void benchmarkMatrixMultiplication() {
        CSRMatrix resultMatrix = williamsMatrix.multiply(williamsMatrix);
    }
}
