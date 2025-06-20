package problems;

import java.util.PriorityQueue;

public class tests {
    public static void main(String[] args) {
        
        ProfitMaximizationImpl profitMaximizationImpl = new ProfitMaximizationImpl(new PriorityQueue<>());

        Integer[] array = {4,2,5,7};

        int resultado = profitMaximizationImpl.maximize(array, 2);

        System.out.println(resultado);

    }
}
