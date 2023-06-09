/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package buscaminasejercicio;

import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Sandsower
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Declaramos variables
        String temp;
        int x,y,minas,tablero[][];

        //Nucleo del programa
        temp = JOptionPane.showInputDialog("Dame el ancho del tablero.");
        x = Integer.parseInt(temp);
        temp = JOptionPane.showInputDialog("Dame la altura del tablero.");
        y = Integer.parseInt(temp);
        temp = JOptionPane.showInputDialog("Dame cantidad de minas en el tablero.");
        minas = Integer.parseInt(temp);
        tablero = crearGrid(x, y);
        tablero = insertarMinas(tablero, x, y, minas);
        tablero = buscarMinas(tablero, x, y);
        imprimirTablero(tablero, x, y);
    }
    
    /**
     * esta función crea el tablero a partir del ancho y la altura
     * @param x anchura
     * @param y altura
     * @return devuelve el tablero.
     */
    public static int[][] crearGrid(int x,int y){
        int[][] cadena = new int[x][y];
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                cadena[i][j] = 0;
            }
        }
        return cadena;
    }

    /**
     * esta funcion muestra por pantalla el tablero.
     * @param tablero se le pasa el tablero
     * @param x anchura
     * @param y altura
     */
    public static void imprimirTablero(int[][] tablero,int x,int y){
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                if(tablero[i][j] == -1)
                    System.out.print("*");
                else
                System.out.print(tablero[i][j]);
            }
            System.out.println("");
        }
    }

    /**
     * pone las minas en el tablero
     * @param tablero se le pasa el tablero
     * @param x alchura
     * @param y altura
     * @param minas numero de minas
     * @return devuelve el tablero.
     */
    public static int[][] insertarMinas(int[][] tablero,int x,int y, int minas){
        int cuenta = 0, posicionX,posicionY;
        Random rand = new Random();
        while(cuenta<minas){
            posicionX = rand.nextInt(x);
            posicionY = rand.nextInt(y);
            if(tablero[posicionX][posicionY]!=-1){
                tablero[posicionX][posicionY]=-1;
                cuenta++;
            }
        }
        return tablero;
    }

    /**
     * busca las minas en el tablero
     * @param tablero se le pasa el tablero
     * @param x anchura
     * @param y altura
     * @return devuelve el tablero
     */
    public static int[][] buscarMinas(int[][] tablero,int x,int y){
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                if(tablero[i][j] == -1)
                    tablero = llenarCasillasContiguas(tablero, i, j,x,y);
            }
        }
        return tablero;
    }

    /**
     * llena las casillas contuguas a donde se ha encontrado la mina
     * @param tablero se le pasa el tablero
     * @param x posición anchura de donde se encuentra la mina
     * @param y posición altura de donde se encuentra la mina
     * @param sizeX anchura
     * @param sizeY altura
     * @return devuelve el tablero
     */
    public static int[][] llenarCasillasContiguas(int[][] tablero,int x, int y, int sizeX, int sizeY){
        for(int i=x-1;i<=x+1;i++){
            for(int j=y-1;j<=y+1;j++){
                if(i>=0 && j>=0 && i<sizeX && j<sizeY){
                if(tablero[i][j] != -1)
                    tablero[i][j]++;
                }
            }
        }
        return tablero;
    }

}
