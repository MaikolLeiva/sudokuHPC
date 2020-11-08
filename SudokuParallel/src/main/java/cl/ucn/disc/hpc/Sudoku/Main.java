package cl.ucn.disc.hpc.Sudoku;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * El main
 * @autor Maikol Leiva Carrasco
 *
 */
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        final StopWatch stopWatch = StopWatch.createStarted();

        int[][] tablero = {{0,0,0,4,0,0,7,0,0},
                           {0,5,0,0,0,9,2,0,0},
                           {6,0,0,0,3,0,0,0,0},
                           {3,1,0,0,0,0,0,9,0},
                           {0,4,5,0,0,0,0,0,1},
                           {0,0,6,0,7,0,4,0,0},
                           {0,0,0,0,2,8,9,7,0},
                           {0,0,0,0,1,0,0,0,0},
                           {0,0,0,0,0,5,6,0,3}};

        /*
        int[][] tablero = {{0,7,0,0,4,5,6,0,3},
                           {4,0,8,0,6,7,0,5,1},
                           {0,6,0,0,1,0,0,7,0},
                           {3,0,7,9,0,0,1,8,0},
                           {0,1,6,0,7,8,2,3,0},
                           {0,5,0,0,0,0,0,4,0},
                           {6,0,0,0,2,0,4,0,0},
                           {2,3,0,7,0,6,0,1,0},
                           {0,0,0,1,8,0,0,0,0}};

         int[][] tablero = {{0,0,0,0,0,0,0,0,0},
                           {0,0,0,0,0,0,0,0,0},
                           {0,0,0,0,0,0,0,0,0},
                           {0,0,0,0,0,0,0,0,0},
                           {0,0,0,0,0,0,0,0,0},
                           {0,0,0,0,0,0,0,0,0},
                           {0,0,0,0,0,0,0,0,0},
                           {0,0,0,0,0,0,0,0,0},
                           {0,0,0,0,0,0,0,0,0}};
         */

        Sudoku sudoku = new Sudoku(tablero);
        sudoku.resolver();
        log.info("Done in {}.", stopWatch);
        sudoku.imprimirTablero();

    }
}
