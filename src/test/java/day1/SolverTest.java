package day1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolverTest {

    private PartOne.Solver solver;

    @BeforeEach
    void setup() {
        solver = new PartOne.Solver(Integer.MAX_VALUE);
    }

    @Test
    void testSolver() {
        assertEquals(2, solver.solve(Stream.of(1,2,3)));
        assertEquals(2, solver.solve(Stream.of(1,3,2,5)));
    }
}
