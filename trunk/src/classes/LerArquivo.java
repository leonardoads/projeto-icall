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
	public static String lerArq(String arquivo) throws FileNotFoundException, IOException{
		String arq = "";
		File file = new File(arquivo);
		
		if(! file.exists()){
			return "ERRO AO LER ARQUIVO";
		}
		
		BufferedReader br = new BufferedReader(new FileReader(arquivo));
		StringBuffer bufSaida = new StringBuffer();
		
		String linha;
		while((linha = br.readLine()) != null){
			bufSaida.append(linha+"\n");
			arq+= linha+"\n";
		}
		br.close();
		return bufSaida.toString().replace("\t", " ");
	}
}
