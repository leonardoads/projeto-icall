package classes;

import java.io.File;

public class CriarDiretorio {
	public static void criaDiretorio(String novoDiretorio) throws Exception{  
	    String nomeDiretorio = null;   
	    String separador = java.io.File.separator;   
	    try {       
	         nomeDiretorio = novoDiretorio;   
	         if (!new File(nomeDiretorio).exists()) { // Verifica se o diretório existe.   
	             (new File(nomeDiretorio)).mkdir();   // Cria o diretório   
	         }   
	    } catch (Exception ex) {   
//	         JOptionPane.showMessageDialog(null,"Err","Erro ao criar o diretório" + ex.toString(),JOptionPane.ERROR_MESSAGE);
	    	throw new Exception("Erro ao criar Diretorio "+nomeDiretorio);
	    }  
	}  
}
