package classes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CriarArquivo {
	public static void criaArquivo(String localParaSalvar, String nome,
			String conteudo) throws Exception {
		File saida;
		try {
			saida = new File(localParaSalvar
					+ java.io.File.separator + nome);
			BufferedWriter br = new BufferedWriter(new FileWriter(saida));

			br.write(conteudo);
			br.close();

		} catch (Exception e) {
			throw new Exception("Erro ao criar o Arquivo");
		}
	}
	public static void main(String[] args) throws IOException  {
		try {
			CriarArquivo.criaArquivo(System.getProperty("user.home"), "leo.txt", "alguma szdbesteira");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
