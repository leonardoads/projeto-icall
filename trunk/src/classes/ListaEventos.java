package classes;

import java.util.ArrayList;
import java.util.List;

public class ListaEventos {	
	private List<Evento> listaEventos = new ArrayList<Evento>();	
	
	public List<Evento> getListaEventos() {
		return listaEventos;
	}


	public void addEvento1(Evento evento){
		listaEventos.add(evento);		
	}
	
	public void addEvento2(String nomeEvento, String dataEvento, String nomeApresentador, String temaEvento){
		listaEventos.add(new Evento(nomeEvento, dataEvento, nomeApresentador, temaEvento));		
	}
	
	
	public void removeEventoPeloNome (String nomeEvento) throws Exception{		
		Evento eventoAhSerRemovido = this.buscaEventoPeloNome(nomeEvento);
		listaEventos.remove(eventoAhSerRemovido);		
	}
	
	public void removeEvento(Evento evento){
		listaEventos.remove(evento);
	}
	
	public Evento buscaEventoPeloNome(String nomeEvento) throws Exception{
		boolean contemEvento = false;		
		Evento retorno = null;
		for(Evento evento : listaEventos){
			if(evento != null && evento.getNomeEvento().equals(nomeEvento)){
				contemEvento = true;		
				retorno = evento;
				break;
			}
		}
		if(!contemEvento){
			throw new Exception("Evento inexistente");
		}		
		return retorno;
	}
	
	public Evento buscaEvento(Evento evento) throws Exception{
		boolean contemEvento = false;		
		Evento retorno = null;
		for(Evento evento1 : listaEventos){
			if(evento != null && evento.equals(evento1)){
				contemEvento = true;		
				retorno = evento;
				break;
			}
		}
		if(!contemEvento){
			throw new Exception("Evento inexistente");
		}		
		return retorno;
	}
		
	
	
	public boolean containEvento(Evento evento) throws Exception {
		boolean retorno = false;
		if(listaEventos.contains(evento)){	
			retorno = true;
		}
		return retorno;
	}
	
	public void addUsuario(Aluno usuario, Evento evento) throws Exception{
		this.buscaEvento(evento).addUsuario(usuario);
		
	}
	
	public Aluno buscaUsuario(String matricula) throws Exception{
		Aluno retorno = null;
		
		for(Evento evento : listaEventos){
			if(evento.contemUsuario(matricula)){
				retorno = evento.buscaUsuarioPelaMatricula(matricula);
			}
			
		}
		
		if(retorno == null){
			throw new Exception ("usuario inexistente");
			
		}
		return retorno;
		
	}
	
	public boolean equals(ListaEventos listaEvento){
		boolean retorno = false;
		
		if(listaEvento.getListaEventos().equals(this.getListaEventos())){
			retorno = true;
		}
		
		return retorno;
		
	}
	
	public String toString(){
		String retorno = "";
		
		for(Evento evento : this.getListaEventos()){
			retorno += evento.toString() + "\n";
		}
		
		return retorno;
	}
	
		
		
		
	
}
