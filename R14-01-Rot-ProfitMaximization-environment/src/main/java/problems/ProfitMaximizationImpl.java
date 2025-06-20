package problems;

import java.util.PriorityQueue;

public class ProfitMaximizationImpl implements ProfitMaximization{

    /**
     * Implementacao de heap sobrejacente. PriorityQueue é uma min-heap que 
     * pode trabalhar com um comparator interno e permite elementos duplicados
     *
     * O método poll() é semelhante a extrair o root da PriorityQueue
     * O método add(elem) insere in elemento na heap
     */
    private PriorityQueue<Integer> heap;

    
    public ProfitMaximizationImpl(PriorityQueue<Integer> heap) {
        this.heap = new PriorityQueue<>((o1, o2) -> o2-o1);
        
    }

    public int maximize(Integer[] array, int amount){
        int resultado = 0;

        //Adcionando a maxHeap
        for(Integer valor : array){
            heap.add(valor);
        }

        while(!heap.isEmpty() && amount > 0){
            int numero = heap.poll();
            resultado += numero;
            if(numero - 1 > 0){
                heap.add(--numero);
            }
            amount--;
        }

        return resultado;
    }
}
