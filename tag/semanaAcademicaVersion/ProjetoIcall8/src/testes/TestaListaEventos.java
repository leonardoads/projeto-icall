package testes;

import junit.framework.Assert;

import org.junit.Test;

import classes.Evento;
import classes.ListaEventos;
import classes.Aluno;

public class TestaListaEventos {
	
	@Test
	public void testaListaEvento1() throws Exception{
		ListaEventos listaEvento1 = new ListaEventos();
		ListaEventos listaEvento2 = new ListaEventos();
		ListaEventos listaEvento3 = new ListaEventos();		
		
		Evento evento1 = new Evento("WPPC", "Veteranos", "03/03/2012", "Igor");
		Evento evento2 = new Evento("semana do fera", "feras", "01/03/2013", "Joseana");
		Evento evento3 = new Evento("WWW", "Veteranos", "05/03/2012", "Savyo");
		
		Aluno usuario1 = new Aluno("Igor", "21011078");
		Aluno usuario2 = new Aluno("Carlos", "23031079");
		Aluno usuario3 = new Aluno("Arley", "22211078");
		
		evento1.addUsuario(usuario1);
		evento1.addUsuario(usuario2);
		
		listaEvento1.addEvento1(evento1);
		listaEvento2.addEvento1(evento1);
		Assert.assertEquals("Erro no addEvento ou toString","WPPC, Veteranos, 03/03/2012, Igor\n", listaEvento1.toString());
		Assert.assertTrue("Erro no equals", listaEvento1.equals(listaEvento2));
		Assert.assertFalse("Erro no equals", listaEvento1.equals(listaEvento3));
		
		listaEvento1.addEvento1(evento2);
		Assert.assertEquals("Erro no addEvento ou toString","WPPC, Veteranos, 03/03/2012, Igor\n"+ 
		                    "semana do fera, feras, 01/03/2013, Joseana\n", listaEvento1.toString());
		
		listaEvento1.removeEventoPeloNome("semana do fera");
		Assert.assertEquals("Erro no removerEventoPeloNome","WPPC, Veteranos, 03/03/2012, Igor\n", listaEvento1.toString());
		listaEvento1.removeEvento(evento1);
		Assert.assertEquals("Erro no removerEvento","", listaEvento1.toString());
		
		evento1.addUsuario(usuario1);
		listaEvento1.addEvento1(evento1);
		Assert.assertFalse("Erro no containEvento()", listaEvento1.containEvento(evento2));
		Assert.assertTrue("Erro no containEvento()", listaEvento1.containEvento(evento1));
		
		
		Assert.assertEquals("Erro no buscaUsuarioPeloNome()", "Igor, 21011078", listaEvento1.buscaUsuario("21011078").toString());
		
		
		Assert.assertEquals("Erro no buscaEvento()", evento1, listaEvento1.buscaEvento(evento1));
				
	}

}
