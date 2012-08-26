package testes;

import junit.framework.Assert;

import org.junit.Test;

import classes.Evento;
import classes.Aluno;

public class testaEvento {

	
	@Test
	//testando metodos gets, sets, toString e equals
	public void testaEvento1(){
		Evento evento1 = new Evento("WPPC", "Veteranos", "03/03/2012", "Igor");
		Evento evento2 = new Evento("semana do fera", "feras", "01/03/2013", "Joseana");
		Evento evento3 = new Evento("WWW", "Veteranos", "05/03/2012", "Savyo");
		
		evento1.setNomeEvento("semana do fera");
		evento1.setTemaEvento("feras");
		evento1.setDataEvento("01/03/2013");
		evento1.setNomeApresentador("Joseana");
		
		Assert.assertEquals("Erro no setNomeEvento","semana do fera" , evento1.getNomeEvento());
		Assert.assertEquals("Erro no setTemaEvento","feras" , evento1.getTemaEvento());
		Assert.assertEquals("Erro no setDataEvento","01/03/2013" , evento1.getDataEvento());
		Assert.assertEquals("Erro no setNomeApresentador","Joseana" , evento1.getNomeApresentador());

		Assert.assertTrue("Erro no equals", evento1.equals(evento2));
		Assert.assertFalse("Erro no equals", evento1.equals(evento3));
		
		Assert.assertEquals("Erro no toString","semana do fera, feras, 01/03/2013, Joseana", evento1.toString());
	
	
	
	
	}
	@Test
	//testando metodos outros metodos
		public void testaEvento2() throws Exception{
			Evento evento1 = new Evento("WPPC", "Veteranos", "03/03/2012", "Igor");
			Evento evento2 = new Evento("semana do fera", "feras", "01/03/2013", "Joseana");
			Evento evento3 = new Evento("WWW", "Veteranos", "05/03/2012", "Savyo");
			
			Aluno usuario1 = new Aluno("Igor", "21011078");
			Aluno usuario2 = new Aluno("Carlos", "23031079");
			Aluno usuario3 = new Aluno("Arley", "22211078");
			
			evento1.addUsuario(usuario1);
			evento1.addUsuario(usuario2);
			Assert.assertEquals("Erro no getUsuarioCadastrado", "Igor, 21011078\nCarlos, 23031079\n", evento1.getUsuariosCadastrados());
			
			evento1.removeUsuario("23031079");
			Assert.assertEquals("Erro no removeUsuario", "Igor, 21011078\n", evento1.getUsuariosCadastrados());
			evento1.removeUsuario("00000000000000000000000000000000");
			Assert.assertEquals("Erro no removeUsuario", "Igor, 21011078\n", evento1.getUsuariosCadastrados());
			
			Assert.assertTrue("Erro no comtemUsuario", evento1.contemUsuario("21011078"));
			Assert.assertFalse("Erro no comtemUsuario", evento1.contemUsuario("000000000000000000000"));
		}
}