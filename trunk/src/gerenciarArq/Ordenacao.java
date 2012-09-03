package gerenciarArq;

import java.util.Arrays;

import auxiliadores.Auxiliadora;

public class Ordenacao {
	Auxiliadora auxiliadora = new Auxiliadora();
	String[] retorno;
	String[] aux;
	final static int nome = 1;
	final static int data = 2;
	int tamanho;
	
	public String[] ordenar(String[] lista, int metodo, boolean crescente){
		tamanho = lista.length;
		retorno = new String[tamanho];
		
		if(metodo==nome){
			ordenaNome();
		}
		else if(metodo==data){
			ordenaData();
		}
		
		if(!crescente){
			String[] aux = Arrays.copyOfRange(retorno, 0, tamanho);
			for(int i = tamanho-1; i >=0; i--){
				retorno[i] = aux[tamanho-(i+1)];
			}
		}
		
		return retorno;
	}
	private void ordenaNome(){
		aux = new String[tamanho];
		for(int i =0;i<tamanho;i++){
			aux[i] = retorno[i].split(auxiliadora.getSeparadorInf())[0];
		}
		Arrays.sort(aux);
		String[] aux2 = new String[tamanho];
		for(int i = 0;i<tamanho;i++){
			for(int j = 0;j<tamanho;j++){
				if(aux[i].equals(retorno[j].split(auxiliadora.getSeparadorInf())[0])){
					aux2[i] = retorno[j];
				}
			}
		}
		retorno = Arrays.copyOfRange(aux2, 0, tamanho);
	}
	private void ordenaData(){
		
	}
}
