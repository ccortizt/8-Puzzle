package newpuzzle;

public class Nodo {

    private Nodo padre;
    private int key;

    Nodo() {
    }

    Nodo(int key) {
        this.key = key;

    }
    Nodo(int key, Nodo padre) {
        this.key = key;
        this.padre = padre;
    }
    
    /**
     * a partir de la llave entera genera un array con el estado del puzzle
     * @param key
     * @return mapa
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
     * realiza la impresion del estado del tablero
     * @param tablero
     * @return 
     */
    public String pintar(int[] tablero) {
        
        String r = "";
        for (int i = 0; i < 9; i++) {
            r += tablero[i];
        }
        return r;
    }
    
    /**
     * busca y retorna la posicion vacia en el array de estado
     * la cual esta representada por el digito 9
     * @param key
     * @return pos
     */
    public int getPos(int[] key) {
       
        int pos = 0;
        for (int i = 0; i < 9; i++) {
            if (key[i] == 9) {
                pos = i;
            }
        }
        return pos;
    }

    /**
     * retorna la posicion vacia dado el nodo
     * la cual esta representada por el digito 9
     * @return  posVacia
     */
    //Nodo.getPos();
    public int getPosVacia() {
        System.out.println("fueusadogetposvacianodo");
        return this.getPos(this.toArray((Integer) getKey()));
    }

    /**
     * @return the key
     */
    public int getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * @return the padre
     */
    public Nodo getPadre() {
        return padre;
    }

    /**
     * @param padre the padre to set
     */
    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

}