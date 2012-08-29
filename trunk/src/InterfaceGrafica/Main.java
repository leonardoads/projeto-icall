package InterfaceGrafica;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Main {
	static JFrame janela;
	
	public static Component janelaPrincipal = (new JanelaPrincipal()).panel();
	public static Component cadastraAula = (new CadastAula()).panel();
	public static Component cadastraAluno = (new CadastAluno()).panel();
	public static Component cadastraAlunos = (new CadastAlunoAula()).panel();
	public static Component login = (new Login()).panel();
	public static Component marcapresenca = (new MarcaPresenca()).panel();
	public static Component marcapresenca2 = (new MarcaPresenca2()).panel();
	boolean LOGIN = true;
	
	public void run(){
		criaJanela();
	}
	public void criaJanela(){
		JFrame.setDefaultLookAndFeelDecorated(true);
		janela = new JFrame();
		janela.setSize(720,380);//Tamanho da janela
		janela.setResizable(false);//pode redimensionar a janela
		janela.setLocation(50,50);//Local onde a janela aparecera
		janela.setIconImage(null);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		trocaPainel(login, null, "iCall - login");
		//trocaPainel(janelaPrincipal, null, "iCall");
	}
	public void trocaPainel(Component coloca, Component remover, String titulo){
		try{
			JPanel novo = (JPanel) coloca;
			if(remover != null){
				janela.remove(remover);
			}
			janela.add(novo);
			janela.setTitle(titulo);
			janela.repaint();
			janela.setVisible(true);
		}catch (Exception e) {
			System.err.println("Erro: trocaPainel\n\n"+e.getMessage());
		}
	}
	
}
