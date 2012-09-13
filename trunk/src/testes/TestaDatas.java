package testes;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import classes.Datas;

public class TestaDatas {
	@Test
	public  void testaDatas() {
		Datas primeiraData = new Datas();
		Datas segundaData = new Datas();
		Datas terceiraData = new Datas();
		Datas quartaData = new Datas();
		Datas quintaData = new Datas();
		List<String> ListaTeste = new ArrayList<String>();
		
		
		primeiraData.addData("12", "janeiro", "2012");
		segundaData.addData("12", "janeiro", "2012");
		terceiraData.addData("13", "fevereiro", "2012");
		quintaData.addData("12", "janeiro", "2012");
		
		
		Assert.assertEquals("erro no addData()",segundaData.getListaDeDatasDoEvento() ,primeiraData.getListaDeDatasDoEvento());
		Assert.assertEquals("erro no conteinData()",segundaData.conteinData("12", "janeiro","2012") ,primeiraData.conteinData("12", "janeiro", "2012"));
		primeiraData.removeData("12", "janeiro", "2012");
		
		Assert.assertEquals("Erro no removeData()", quartaData.toString(), primeiraData.toString());
		System.out.println(segundaData);
		System.out.println(quintaData);
		Assert.assertFalse("Erro no equals()", segundaData.equals(quartaData));
		Assert.assertTrue("Erro no equals()", segundaData.equals(quintaData));
		Assert.assertTrue("Erro no equals()", primeiraData.equals(quartaData));
		
		terceiraData.setListaDeDatasDoEvento(ListaTeste);
		Assert.assertFalse("Erro no setListaDeDatasDoEvento()", terceiraData.equals(segundaData));
		Assert.assertTrue("Erro no setListaDeDatasDoEvento()", terceiraData.equals(primeiraData));
		

	}
	
	

}
