/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package newpuzzle;

/**
 *
 * @author Nicolas
 */
public class Heuristicas {
    
    int[] objetivo = {1,2,3,4,5,6,7,8,9};
    
    public int heuristica1(int[] estado){
        int contador = 0;
        for(int i=0;i<9;i++){
            if(estado[i]!=objetivo[i]){
                contador++;
            }
        }
        return contador;
    }
            
    public int heuristica2(int[] m){ //le quite el static
        int d = 0; 
        //0
        if((m[0] == 2 && m[1] == 1 )||( m[0]== 4 && m[4]==1)){
            d++;
        }
        //2
        if((m[5] == 3 && m[2] == 6 )||( m[1]== 3 && m[2]==2)){
            d++;
        }
        //6
        if((m[6] == 4 && m[3] == 7 )||( m[6]== 8 && m[7] == 7)){
            d++;
        } 
        //8
        if((m[8] == 8 && m[7] == 9 )||( m[8]== 6 && m[5]==9)){
            d++;
        } 
        //4
        if((m[1] == 5 && m[4] == 2 )||( m[4]== 4 && m[3] == 5)||(m[4] == 6 && m[5] == 5 )||( m[4]== 8 && m[7] == 5)){
            d++;
        } 
        System.out.println(d+ ": d");
        return d;
    }
    
    public int heuristica3(int[] estado){
        
        return 0;
    }
    
}
