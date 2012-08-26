package classes;

import java.util.ArrayList;
import java.util.List;

public class Facade {
	
	
	static ListaEventos listaEventos = new ListaEventos();	
	
	 //Cadastra usuario
	static Evento evento;
	
	public static  void cadastraEvento(String nomeEvento, String temaEvento, String nomeApresentador,  String dataEvento){
			evento = new Evento(nomeEvento,temaEvento, nomeApresentador,  dataEvento);		
	}
	
	public static void cadastraAluno(Aluno usuario) throws Exception{
			evento.addUsuario(usuario);
		
	}
	public static void removeAluno(String matricula) throws Exception{
		evento.removeUsuario(matricula);
		
	}
	
	public static Aluno buscaAlunoPelaMatricula(String matricula) throws Exception{
		return evento.buscaUsuarioPelaMatricula(matricula);
	}
	
	
	
	
}	
