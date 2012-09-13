package classes;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class criaEventoPontoIcall{ 
	public static void escreveEvento(String diretorio, String nomeEvento) throws IOException{
		try{
			File arquivo = new File(diretorio + "/eventos.icall");
			FileWriter fw = new FileWriter(arquivo, true);  
			BufferedWriter bw = new BufferedWriter(fw);  
	        
			bw.write(nomeEvento + System.getProperty("line.separator"));  
			bw.flush();  
			bw.close();
		}
		catch(Exception e){
			System.err.println("Erro ao cadastrar o evento " + nomeEvento);
		}
	}
		
	public static boolean eventoCadastrado(String diretorio, String nomeEvento) {  
		boolean cadastrado = false;  
		String NomeArq= diretorio + "/eventos.icall";  
		String linha="";         
		try {  
			BufferedReader in = new BufferedReader(new FileReader(NomeArq));  
			while ((linha = in.readLine()) != null) {  
				if (linha.substring(0, linha.indexOf("|")).contains(nomeEvento.replace(" ", "_"))) {  
					cadastrado = true;  
				}  
			}  
		} catch (Exception e) {  
			return false; 
		}  
		return cadastrado;  
	}
	
	public static boolean alunoCadastrado(String diretorio, String matricula) {  
		boolean cadastrado = false;  
		String NomeArq= diretorio + "/enroll.icall";  
		String linha="";         
		try {  
			BufferedReader in = new BufferedReader(new FileReader(NomeArq));  
			while ((linha = in.readLine()) != null) {  
				if (linha.contains(matricula)) {  
					cadastrado = true;  
				}  
			}  
		} catch (Exception e) {  
			return false; 
		}  
		return cadastrado;  
	}
	
	public static boolean alunoVerificado(String diretorio, String matricula, String nomeEvento) {  
		boolean cadastrado = false;  
		String NomeArq= diretorio + "/" + nomeEvento + "_verify.icall";  
		String linha="";         
		try {  
			BufferedReader in = new BufferedReader(new FileReader(NomeArq));  
			while ((linha = in.readLine()) != null) {  
				if (linha.contains(matricula)) {  
					cadastrado = true;  
				}  
			}  
		} catch (Exception e) {  
			return false; 
		}  
		return cadastrado;  
	}

}
