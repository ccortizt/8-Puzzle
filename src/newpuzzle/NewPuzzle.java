/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package newpuzzle;

import java.util.Stack;

/**
 *
 * @author Camilo
 */
public class NewPuzzle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //aqui se hacen las pruebas
        System.out.println(" inicio ");
      
        //este//Nodo inicio = new Nodo(123459786);
                               
        Nodo inicio = new Nodo(913425786);
        Arbol arbol = new Arbol(inicio);               
        
        //arbol.busquedaAnchura();
        arbol.busquedaProfundidad();
        
    }
}
