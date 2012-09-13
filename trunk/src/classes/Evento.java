package classes;

import java.util.List;
import java.util.ArrayList;

public class Evento {
	private String nomeEvento, dataEvento, nomeApresentador, temaEvento;
	private List<Aluno> listaUsuarios = new ArrayList<Aluno>();
	
	
	//metodos gets and sets
	
	public Evento(String nomeEvento, String temaEvento, String dataEvento, String nomeApresentador){
		this.nomeEvento = nomeEvento;
		this.temaEvento = temaEvento;
		this.dataEvento = dataEvento;
		this.nomeApresentador = nomeApresentador;
		
	}
	
	
	public String getNomeEvento() {
		return nomeEvento;
	}
	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}
	public String getDataEvento() {
		return dataEvento;
	}
	public void setDataEvento(String dataEvento) {
		this.dataEvento = dataEvento;
	}
	public String getNomeApresentador() {
		return nomeApresentador;
	}
	public void setNomeApresentador(String nomeApresentador) {
		this.nomeApresentador = nomeApresentador;
	}
	public String getTemaEvento() {
		return temaEvento;
	}
	public void setTemaEvento(String temaEvento) {
		this.temaEvento = temaEvento;
	}
	public List<Aluno> getListaUsuario() {
		return listaUsuarios;
	}
	
	
	//metodos para gerenciar a listaUsuarios
	public boolean addUsuario(Aluno usuario) {
		listaUsuarios.add(usuario);		
		return true;
		
	}
	
	public boolean removeUsuario(String matricula) throws Exception {
		if(this.contemUsuario(matricula)){
			listaUsuarios.remove(this.buscaUsuarioPelaMatricula(matricula));
			return true;
		}
		else{
			throw new Exception("Usuario inexistente");
		}
	}
	
	
	public boolean contemUsuario(String matricula) throws Exception{
		boolean contemUsuario = false;		
		for(Aluno usuario : listaUsuarios){
			if(usuario != null && usuario.getMatricula().equals(matricula)){
				contemUsuario = true;				
				break;
			}
		}		
		return contemUsuario;
		
	}
	
	public Aluno buscaUsuarioPelaMatricula(String matricula) throws Exception{
		boolean contemUsuario = false;		
		Aluno retorno = null;
		for(Aluno usuario : listaUsuarios){
			if(usuario != null && usuario.getMatricula().equals(matricula)){
				contemUsuario = true;		
				retorno = usuario;
				break;
			}
		}		
	
		if(!contemUsuario){
			throw new Exception("Usuario inexistente");
		}
		
		return retorno;
		
	}
	public String getUsuariosCadastrados(){
		String retorno = "";
		
		for(Aluno usuario : this.getListaUsuario()){
			retorno += usuario.toString() + "\n";
	
		}
		
		return retorno;
	}
	public boolean equals(Evento evento){
		boolean retorno = false;
		if(this.getDataEvento().equals(evento.getDataEvento()) && this.getListaUsuario().equals(evento.getListaUsuario()) &&
		this.getNomeApresentador().equals(evento.getNomeApresentador()) && this.getNomeEvento().equals(evento.getNomeEvento()) &&
		this.getTemaEvento().equals(evento.getTemaEvento())){
			retorno = true;
		}		
		return retorno;
	}
	
	public String toString(){
		return this.nomeEvento + ", " + this.temaEvento + ", " + this.dataEvento + ", " + this.nomeApresentador; 
	}
	
	
	

}
