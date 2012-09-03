package gerenciarArq;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

public class Arquivo {
	String[] retorno;
	List<String> listaOpinioes = new ArrayList<String>(); 
	static List<String> listaUsuarios = new ArrayList<String>(); 
	List<String> nomeUsuarios = new ArrayList<String>(); 
	List<String> nomeEstabelecimentos = new ArrayList<String>();
	static String sepInf = "##";
	
	public static void escreveArquivo(String conteudo, String Arquivo, boolean adicionar){
		Arquivo = Arquivo+".txt";
        try {
            FileWriter fw;
            fw = new FileWriter(Arquivo, adicionar);
            fw.write(conteudo);
            fw.close();
			JOptionPane.showMessageDialog(null, "Opera��o realizada com sucesso");
        } catch (IOException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
	public static ArrayList lerArquivo(String arquivo)throws FileNotFoundException, IOException {
		String arq = "";
		ArrayList<String> List = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(arquivo+".txt"));   
		  
        while(br.ready()){   
	        String linha = br.readLine();   
	        List.add(linha);
        }   
        br.close();  
        return List;
	}
	public static String[] buscar(String nome){
		try{
			ArrayList<String> arq = lerArquivo("cadastros");
			for(int i=0;i<arq.size();i++){
				if(arq.get(i).split(sepInf)[0].equals(nome)){
					return arq.get(i).split(sepInf);
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public static ArrayList<String> niver(String mes){
		ArrayList<String> retorno = new ArrayList<String>();
		try{
			ArrayList<String> arq = lerArquivo("cadastros");
			for(int i=0;i<arq.size();i++){
				if(Integer.parseInt(arq.get(i).split(sepInf)[2].split("/")[1]) == Integer.parseInt(mes)){
					retorno.add(arq.get(i));
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return retorno;
	}
	public static ArrayList<String> listaOrdemAlfabetica(){
		String[] nomes = null;
		try{
			ArrayList<String> arq = lerArquivo("cadastros");
			nomes = new String[arq.size()];
			String[] retorno = new String[arq.size()];
			for (int i = 0; i < nomes.length; i++) {
				nomes[i] = arq.get(i).split(sepInf)[0];
			}
			Arrays.sort(nomes);
			for (int i = 0; i < nomes.length; i++) {
				for (int j = 0; j < retorno.length; j++) {
					if(nomes[i].equals(arq.get(j).split(sepInf)[0])){
						nomes[i] = arq.get(j);
					}
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		ArrayList<String> retorno = new ArrayList<String>();
		for(String i : nomes){
			retorno.add(i);
		}
		return retorno;
	}
	public static boolean verificaNome(String nome){
		try{
			ArrayList<String> arq = lerArquivo("cadastros");
			for(int i=0;i<arq.size();i++){
				if(arq.get(i).split(sepInf)[0].equals(nome))
					return false;
			}
		}catch (Exception e) {
			return true;
		}
		return true;
	}
	public static void editar(String inf, String nomeArq){
		try{
			String novoConteudo = "";
			ArrayList<String> arq = lerArquivo("cadastros");
			for (int i = 0; i < arq.size(); i++) {
				if(arq.get(i).split(sepInf)[0].equals(inf.split(sepInf)[0])){
					novoConteudo+=inf;
				}
				else{
					novoConteudo+=arq.get(i);
				}
				novoConteudo +="\n";
			}
			Arquivo.escreveArquivo(novoConteudo, nomeArq, false);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void excluir(String inf, String nomeArq){
		try{
			String novoConteudo = "";
			ArrayList<String> arq = lerArquivo("cadastros");
			for (int i = 0; i < arq.size(); i++) {
				if(!arq.get(i).split(sepInf)[0].equals(inf)){
					novoConteudo+=arq.get(i)+"\n";
				}
			}
			Arquivo.escreveArquivo(novoConteudo,nomeArq, false);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static ArrayList<String> nomeMembros(){
		ArrayList<String> retorno = new ArrayList<String>();
		try{
			ArrayList<String> arq = lerArquivo("cadastros");
			for(int i=0;i<arq.size();i++){
				retorno.add(arq.get(i).split(sepInf)[0]);
			}
		}catch (Exception e) {
			
		}
		return retorno;
	}
}
