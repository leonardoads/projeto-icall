package classes;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Datas {
	
	
	
	private List<String> listaDeDatasDoEvento = new ArrayList<String>();
	
	public List<String> getListaDeDatasDoEvento() {
		return listaDeDatasDoEvento;
	}
	public void setListaDeDatasDoEvento(List<String> listaDeDatasDoEvento) {
		this.listaDeDatasDoEvento = listaDeDatasDoEvento;
	}
	
	public void addData(String dia, String nomeDoMes, String ano){
		
		listaDeDatasDoEvento.add(this.geraData(dia, nomeDoMes, ano));		
	}
	
	public boolean removeData(String dia, String nomeDoMes, String ano){
		boolean retorno = false;
		String dataAhRemover = this.geraData(dia, nomeDoMes, ano);
		if(listaDeDatasDoEvento.contains(dataAhRemover)){
			listaDeDatasDoEvento.remove(dataAhRemover);
			retorno = true;
		}
		return retorno;		
	}
	
	public boolean conteinData(String dia, String nomeDoMes, String ano){
		String data = this.geraData(dia, nomeDoMes, ano);
		return listaDeDatasDoEvento.contains(data);
	}
	
	
	
	public String toString(){
		String retorno = "";		
		int numero = 0;
		
		for(String datas : listaDeDatasDoEvento){
			numero+=1;
			retorno += numero + "º "+ "data: " + datas + "\n ";
		}		
		return retorno;
	}
	
	public String geraData(String dia, String nomeDoMes, String ano){		
		return dia + "/" + nomeDoMes + "/" + ano;
		
	}
	
	public boolean equals(Datas listaDeDatasDoEvento){
		return this.toString().equals(listaDeDatasDoEvento.toString());
		
	}

	
	
		
	
	
	
}
