/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package newpuzzle;

import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author Camilo
 */
public class Arbol {

    private Nodo raiz;
    private Nodo actual;

    private final HashMap nuestroHash;

    private Stack stack;
    

    Arbol(Nodo raiz) {
        this.raiz = raiz;
        nuestroHash = new HashMap();
        stack = new Stack();
        
    }

    /**
     * retorna un entero que almacena el estado o llave a partir de el estado
     * representado en un arreglo
     *
     * @param tablero
     * @return
     */
    public int toInt(int[] tablero) {
        String stringEstado = Integer.toString(tablero[0]);
        for (int i = 1; i < 9; i++) {
            stringEstado = stringEstado + tablero[i];
        }
        //System.out.println("nuevo estado " + pintar(tablero));
        return Integer.parseInt(stringEstado);
    }

    /**
     * retorna un entero que repreesenta un nuevo estado REVISAR
     *
     * @param tablero
     * @param inicioPosicion
     * @param nuevaPosicion
     * @return
     */
    private int nuevoEstado(int[] tablero, int inicioPosicion, int nuevaPosicion) {
        try {
            int aux = tablero[inicioPosicion];
            tablero[inicioPosicion] = tablero[nuevaPosicion];
            tablero[nuevaPosicion] = aux;
            return this.toInt(tablero);
        } catch (Exception e) {
            System.out.println("inicio: " + inicioPosicion + " fin: " + nuevaPosicion);
            return 0;

        }

    }

    /**
     * REVISAR NO DEBERIA IR ACA
     *
     * @param key
     * @return
     */
    public int[] toArray(int key) {
        int mapa[] = new int[9];
        int aux[] = new int[9];
        //
        int j = 8;
        for (int i = 0; i < 9; i++) {
            mapa[i] = ((int) key) % 10;
            key = key / 10;
            aux[j] = mapa[i];
            j--;
            //System.out.println("for " + i + ": key " + key);
        }
        mapa = aux;
        //System.out.println("mapa: " + pintar(mapa));
        return mapa;
    }

    /**
     * REVISAR METODO DUPLICADO EN NODO
     *
     * @param tablero
     * @return
     */
    public String pintar(int[] tablero) {
        System.out.println("");
        System.out.println("Pintar");
        System.out.println("");
        String r = "";
        for (int i = 0; i < 9; i++) {
            r += tablero[i];
        }
        return r;
    }
    
    /**
     * realiza una busqueda en anchura en el 8-puzzle
     * 
     */
    public void busquedaAnchura() {

        System.out.println("primer nodo: " + this.raiz.getKey());
        actual = this.raiz;

        stack.push(actual);
        //guia-->tabla.put(nodo.getKey(), nodo);
        nuestroHash.put(actual.getKey(), actual);
        //mientras sea verdadero sigue
        //stack.empty es verdadero si no tiene items
        while (actual.getKey() != 123456789 && !stack.empty()) {
            //saca el elemento del top de la pila
            actual = (Nodo) stack.pop();
            System.out.println("while actual.key = " + actual.getKey());
           
            generarEstadosD(actual);
            //imprimir la pila actual
            printPila(stack);
        }
        
        //System.out.println("fin?"+actual.getKey()+" "+actual.getPadre().getKey());
        
        System.out.println();
        System.out.println("camino solución\n");
        while(actual.getPadre() != null ){
            
            System.out.println(actual.getKey());
            actual = actual.getPadre();
        }
        System.out.println(raiz.getKey());   
    }
    
    /**
     * realiza una busqueda en profundidad en el 8-puzzle
     * FALTA AGREGAR UN RETURN CON EL STACK RESPUESTA
     */
    public void busquedaProfundidad() {

        System.out.println("primer nodo: " + this.raiz.getKey());
        actual = this.raiz; /*aca hay un error en cual es el actual*/

        stack.push(actual);
        nuestroHash.put(actual.getKey(), actual);
        //cambio al azar
        //mientras sea verdadero sigue
        //stack.empty es verdadero si no tiene items
        while (actual.getKey() != 123456789 && !stack.empty()) {
            //saca el elemento del top de la pila
            actual = (Nodo) stack.pop();
            System.out.println("while actual.key = " + actual.getKey());
            generarEstadosP(actual);
            //FALTA ir acumulando el resultado
            
            //imprimir la pila actual
            printPila(stack);
        }
    }
    
    /**
     * 
     * @param nodo 
     */
    public void generarEstadosP(Nodo nodo) {
        int posicion = nodo.getPos(nodo.toArray(nodo.getKey()));
        System.out.println("posicion " + posicion);
        if ((posicion + 3) < 9) {
            int estado1 = nuevoEstado(this.toArray(nodo.getKey()), posicion, posicion + 3);
            if(!nuestroHash.containsKey(estado1)){
                System.out.println("insertando1: " + estado1);
                Nodo n = new Nodo(estado1, nodo);

                nuestroHash.put(n.getKey(),n);
                //agrega el elemento en el top
                stack.push(n);
            }
        }

        if ((posicion - 1) >= 0 && (posicion - 1) != 2 && (posicion - 1) != 5) {
            int estado1 = nuevoEstado(this.toArray(nodo.getKey()), posicion, posicion - 1);
            if (!nuestroHash.containsKey(estado1)) {
                System.out.println("insertando2: " + estado1);
                Nodo n = new Nodo(estado1, nodo);

                nuestroHash.put(n.getKey(),n);
                //agrega el elemento en el top
                stack.push(n);
            }
        }

        if ((posicion - 3) >= 0) {
            int estado1 = nuevoEstado(this.toArray(nodo.getKey()), posicion, posicion - 3);

             if(!nuestroHash.containsKey(estado1)){
                System.out.println("insertando3: " + estado1);
                Nodo n = new Nodo(estado1, nodo);

               nuestroHash.put(n.getKey(),n);
                //agrega el elemento en el top
                stack.push(n);
            }
        }

        if ((posicion + 1) < 9 && (posicion + 1) != 3 && (posicion + 1) != 6) {
            int estado1 = nuevoEstado(this.toArray(nodo.getKey()), posicion, posicion + 1);
             if(!nuestroHash.containsKey(estado1)){
                System.out.println("insertando4: " + estado1);
                Nodo n = new Nodo(estado1, nodo);

                nuestroHash.put(n.getKey(),n);
                //agrega el elemento en el top
                stack.push(n);
            }
        }
    }

    void generarEstadosD(Nodo nodo) {
        int posicion = nodo.getPos(nodo.toArray(nodo.getKey()));
        System.out.println("posicion " + posicion);
        if ((posicion + 3) < 9) {
            int estado1 = nuevoEstado(this.toArray(nodo.getKey()), posicion, posicion + 3);
            if(!nuestroHash.containsKey(estado1)){
                System.out.println("insertando1: " + estado1);
                Nodo n = new Nodo(estado1, nodo);

                nuestroHash.put(n.getKey(),n);
                //agrega el elemento en el bottom
                stack.insertElementAt(n, 0);

            }
        }

        if ((posicion - 1) >= 0 && (posicion - 1) != 2 && (posicion - 1) != 5) {
            int estado1 = nuevoEstado(this.toArray(nodo.getKey()), posicion, posicion - 1);
             if(!nuestroHash.containsKey(estado1)){
                System.out.println("insertando2: " + estado1);
                Nodo n = new Nodo(estado1, nodo);

               nuestroHash.put(n.getKey(),n);
                //agrega el elemento en el bottom
                stack.insertElementAt(n, 0);
            }
        }

        if ((posicion - 3) >= 0) {
            int estado1 = nuevoEstado(this.toArray(nodo.getKey()), posicion, posicion - 3);

             if(!nuestroHash.containsKey(estado1)){
                System.out.println("insertando3: " + estado1);
                Nodo n = new Nodo(estado1, nodo);

                nuestroHash.put(n.getKey(),n);
                //agrega el elemento en el bottom
                stack.insertElementAt(n, 0);
            }
        }

        if ((posicion + 1) < 9 && (posicion + 1) != 3 && (posicion + 1) != 6) {
            int estado1 = nuevoEstado(this.toArray(nodo.getKey()), posicion, posicion + 1);
             if(!nuestroHash.containsKey(estado1)){
                System.out.println("insertando4: " + estado1);
                Nodo n = new Nodo(estado1, nodo);

                nuestroHash.put(n.getKey(),n);
                //agrega el elemento en el bottom
                stack.insertElementAt(n, 0);
            }
        }
    }

    private void printPila(Stack stack) {
        System.out.println("");
        System.out.println("Impresion pila");
        for (Object n : stack) {
            System.out.println("en pila: " + ((Nodo) n).getKey());
        }

        System.out.println("Fin Impresion pila");
    }
}
