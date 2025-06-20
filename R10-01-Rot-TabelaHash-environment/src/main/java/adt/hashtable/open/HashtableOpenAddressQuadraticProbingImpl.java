package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;
import adt.hashtable.hashfunction.HashFunctionOpenAddress;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if(element != null && !this.isFull()){

			int probe = 0;
			int posicao = ((HashFunctionOpenAddress) this.hashFunction).hash(element, probe);

			if(table[posicao] == null){
				table[posicao] = element;
				elements++;
			} else {
				while(table[posicao] != null && table[posicao] != this.deletedElement){
					COLLISIONS++;
					probe++;
					posicao = ((HashFunctionOpenAddress) this.hashFunction).hash(element, probe);
				}
				
				table[posicao] = element;
				elements++;
				
			}
		} else if(this.isFull()){
			throw new HashtableOverflowException();
		}
	}

	@Override
	public void remove(T element) {
		if(!isEmpty() && element != null){
			int probe = 0;
			int posicao = ((HashFunctionOpenAddress) this.hashFunction).hash(element, probe);

				while(table[posicao] != null && !table[posicao].equals(element) && table[posicao] != this.deletedElement  && probe < this.table.length){
					probe++;
					posicao = ((HashFunctionOpenAddress) this.hashFunction).hash(element, probe);
				}

				if (this.table[posicao] != null && this.table[posicao].equals(element)){
					table[posicao] = this.deletedElement;
					elements--;
				}
			
		}
	}

	@Override
	public T search(T element) {
		T resultado = null;

		if(element != null && !this.isEmpty()){
			int probe = 0;
			int posicao = ((HashFunctionOpenAddress) this.hashFunction).hash(element, probe);

			if(table[posicao].equals(element)){
				resultado = (T) table[posicao];
			}else{
				while(table[posicao] != null && table[posicao] != this.deletedElement && !table[posicao].equals(element) && probe < table.length){
					probe++;
					posicao = ((HashFunctionOpenAddress) this.hashFunction).hash(element, probe);
				}
				if(indexOf(element) > 0){
					if(table[posicao] instanceof DELETED){
						resultado = null;
					} else {
						resultado = (T)table[posicao];
					}
				}	
			}
		}

		return resultado;
	}

	@Override
	public int indexOf(T element) {
		int indice = -1;

		if(element != null && !this.isEmpty()){
			int probe = 0;
			int posicao = ((HashFunctionOpenAddress) this.hashFunction).hash(element, probe);

			if(table[posicao].equals(element)){
				indice = posicao;
			} else {
				while (table[posicao] != null && table[posicao] != this.deletedElement && !table[posicao].equals(element) && probe < table.length){
					probe++;
					posicao = ((HashFunctionOpenAddress) this.hashFunction).hash(element, probe);
				}
				if (probe < this.table.length){
					indice = posicao;
				}
			}
		}
		return indice;
	}
}
