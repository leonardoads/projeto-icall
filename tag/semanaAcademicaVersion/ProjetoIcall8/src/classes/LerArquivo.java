package classes;

import java.io.*;

public class LerArquivo {
	public static void lerArquivo(String localParaLerMaisNomeArquivo) throws IOException  
    {  
          
         FileInputStream arq;  
         try{  
              arq = new FileInputStream(localParaLerMaisNomeArquivo); 
              arq.close();
         }  
         catch(FileNotFoundException e){  
              System.out.println("nao existe");  
         }  
    }  
}
