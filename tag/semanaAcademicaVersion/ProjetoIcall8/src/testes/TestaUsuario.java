package testes;

import junit.framework.Assert;

import org.junit.Test;

import classes.Aluno;

public class TestaUsuario {
	
		
	
	
	@Test
	public void testaUsuario() {
		Aluno usuario1 = new Aluno("Arley", "22211078");
		Aluno usuario2 = new Aluno("Igor", "21011078");
		Aluno usuario3 = new Aluno("Carlos", "23031079");
		
		usuario1.setNome("Igor");
		usuario1.setMatricula("21011078");
		
		Assert.assertEquals("Erro no getNome", "Igor", usuario1.getNome());
		Assert.assertEquals("Erro no getMatricula", "21011078", usuario1.getMatricula());
		Assert.assertTrue("Erro no equals",usuario1.equals(usuario2));
		Assert.assertFalse("Erro no equals",usuario1.equals(usuario3));
		Assert.assertEquals("Erro no toString","Igor, 21011078",usuario1.toString());
		
	}

}
