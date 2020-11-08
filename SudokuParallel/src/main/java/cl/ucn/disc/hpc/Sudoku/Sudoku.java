package cl.ucn.disc.hpc.Sudoku;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Código basado en https://codepumpkin.com/sudoku-solver-using-backtracking/
 *
 */
public class Sudoku {
    private int[][] tablero;
    private static final int SIN_ASIGNAR = 0;
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public Sudoku()
    {
        tablero = new int[9][9];
    }

    public Sudoku(int sudoku[][])
    {
        this.tablero= sudoku;
    }

    public boolean resolver() throws ExecutionException, InterruptedException {
        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {

                if (tablero[fila][columna] == SIN_ASIGNAR){
                    for (int numero = 1; numero <= 9 ; numero++) {
                        if(esValido(fila,columna,numero)){
                            tablero[fila][columna] = numero;
                            if(resolver()){
                                return true;
                            }else{
                                tablero[fila][columna] = SIN_ASIGNAR;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean contieneEnFila(int fila,int numero)
    {
        for(int i=0;i<9;i++)
        {
            if(tablero[fila][i]==numero)
            {
                return true;
            }
        }
        return false;
    }

    private boolean contieneEnColumna(int columna,int numero)
    {
        for(int i=0;i<9;i++)
        {
            if(tablero[i][columna]==numero)
            {
                return true;
            }
        }
        return false;
    }


    private boolean contieneEnCaja(int fila, int columna,int numero)
    {
        int f = fila - fila%3;
        int c = columna - columna%3;
        for(int i = f ; i< f+3 ; i++)
        {
            for(int j = c; j < c+3 ; j++)
            {
                if(tablero[i][j]==numero)
                {
                    return true;
                }
            }

        }
        return false;
    }

    /**
     * Página de como utilizar los hilos con ejemplo.
     * https://howtodoinjava.com/java/multi-threading/executorservice-invokeall/
     *      *
     * @param fila numero de la fila del tablero
     * @param columna numero de la columna del tablero
     * @param numero numero a verificar
     * @return depende de las funciones contiene*, si todas son falsas retornará true
     */
    private boolean esValido(int fila, int columna,int numero) throws InterruptedException, ExecutionException {

        //creamos una lista tipo callable de boolean
        List<Callable<Boolean>> validaciones = new ArrayList<>();
        //se agregan las operaciones de verificacion si están los numeros
        validaciones.add(() -> contieneEnFila(fila, numero));
        validaciones.add(() -> contieneEnColumna(columna, numero));
        validaciones.add(() -> contieneEnCaja(fila, columna, numero));

        //le asiganmos los hilos a utilizar y creamos la lista donde se guardaran los resultados.
        ExecutorService executorService = Executors.newFixedThreadPool(7);
        List<Future<Boolean>> resultados = executorService.invokeAll(validaciones);
        executorService.shutdown();

        //revisamos cada resultado, si ninguno es True, el metodo retornara true
        for(Future<Boolean> future : resultados) {
            Boolean revision = future.get();
            if (revision.equals(Boolean.TRUE)) {
                return false;
            }
        }
        return true;



    }

    public void imprimirTablero()
    {
        for(int i=0;i<9;i++)
        {
            if(i%3==0 && i!=0)
            {
                System.out.println("----------------------------------\n");
            }
            for(int j=0;j<9;j++)
            {
                if(j%3==0 && j!=0)
                {
                    System.out.print(" | ");
                }
                System.out.print(" " + tablero[i][j] + " ");

            }

            System.out.println();
        }
        System.out.println("\n\n__________________________________________\n\n");
    }


}
