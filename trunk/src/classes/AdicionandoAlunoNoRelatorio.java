package classes;

import java.io.*;

public class AdicionandoAlunoNoRelatorio {
	public static void adicionandoAlunoNoRelatorio(String diretorio,String nomeArquivo,String oqueEscreverNoArquivo) throws IOException  
    {  
         FileOutputStream saida;  
         PrintStream fileSaida;  
         
         File dir = new File(diretorio);        
         File arq = new File(dir, nomeArquivo);

         try{  
        	 FileWriter fileWriter = new FileWriter(arq, true);
        	 PrintWriter printWriter = new PrintWriter(fileWriter);
        	 
        	 printWriter.println(oqueEscreverNoArquivo);


         }  
         catch (Exception e)  
         {  
              System.err.println(e);  
         }  
    }  
}
