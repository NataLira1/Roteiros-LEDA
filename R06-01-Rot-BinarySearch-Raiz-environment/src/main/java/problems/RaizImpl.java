package problems;

/**
 * Classe com metodos para calcular raiz n-esima de um numero com aproximacao
 * e para encontrar os limites que dividem um array em 3 partes de mesmo tamanho
 * 
 * @author adalbertocajueiro
 *
 */
public class RaizImpl implements Raiz {

	public double raiz(int numero, int raiz, double erro){
		Double resultado = 0.0;

		if(erro >= 0.0){
			resultado = buscaBinaria(0.0,(double)numero, numero, raiz, erro);
		}
		
		return resultado;
	}

	public double buscaBinaria(Double left, Double rigth, int numero, int raiz, double erro){
		Double resultado = 0.0;
		Double middle = (double) ((left + rigth)/2);
		Double potencia = potencia(middle, raiz);
		Double valorErro = potencia - numero;

		if(valorErro < 0){
			valorErro = -valorErro;
		}

		if(valorErro <= erro){
			resultado = middle;
		} else if(potencia > numero){
			resultado = buscaBinaria(left, middle, numero, raiz, erro);
		} else {
			resultado = buscaBinaria(middle, rigth, numero, raiz, erro);
		}
		return resultado;
	}

	public double potencia(Double num, int raiz){
		double resultado = 0.0;

		if(raiz < 0){
			raiz = raiz * -1;
		}

		if(raiz == 0){
			resultado = 1;
		} else {
			resultado = num * potencia(num, raiz - 1);
		}
		
		return resultado;
	}

}