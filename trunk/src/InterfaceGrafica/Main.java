package InterfaceGrafica;

import gerenciarDB.DBConnection;

import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



public class Main {
	/*final String systemSeparator = java.io.File.separator;
	final String ICALLPATH = System.getProperty("user.home") + systemSeparator
			+ ".iCall" + systemSeparator + "icall-libFP" + systemSeparator;*/
	
	static JFrame janela;
	
	public static String tipoUsuario = "PROFESSOR";
	public static Component janelaPrincipal = (new JanelaPrincipal()).panel();
	public static Component cadastraAula = (new CadastAula()).panel();
	public static Component cadastraAluno = (new CadastAluno()).panel();
	public static Component cadastraAlunos = (new CadastAlunoAula()).panel();
	public static Component login = (new Login()).panel();
	public static Component marcapresenca = (new MarcaPresenca()).panel();
	public static Component marcapresenca2 = (new MarcaPresenca2()).panel();
	public static Component gerarRelatorio = (new GerarRelatorio()).panel();
	public static Component panelProfessores = (new Professores()).panel();
	public static Component novoProfessor = (new ProfessorNovo()).panel();
	public static Component editarProf = (new ProfessorEditar()).panel();
	public static Component editarAluno = (new AlunoEditar()).panel();
	public static Component deletProf = (new ProfessorExcluir()).panel();
	public static Component listProf = (new ProfessoresLista()).panel();
	public static Component gerCadasAluno = (new GerCadastrosAluno()).panel();
	public static Component gerCadasAula = (new GerCadastrosAula()).panel();
	public static Component listarAlunos = (new ListarAlunos()).panel();
	public static Component listarAulas = (new ListarAulas()).panel();
	public static Component panelSobre = (new About()).panel();

	boolean LOGIN = true;

	JLabel local = new JLabel("Janela inicial");

	public void run(){	
		/*try {
			Runtime.getRuntime().exec("gnome-terminal -x sh "+ICALLPATH+"login.sh");
		} catch (IOException e1) {

			e1.printStackTrace();
		}*/
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		criaJanela();
	}
	public void criaJanela(){
		//JFrame.setDefaultLookAndFeelDecorated(true);
		janela = new JFrame();
		//janela.setAlwaysOnTop(true);
		janela.setSize(720,450);//Tamanho da janela
		janela.setResizable(false);//pode redimensionar a janela
		janela.setLocation(50,50);//Local onde a janela aparecera
		janela.setIconImage(null);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		trocaPainel(login, null, "iCall - login");
	  
		//trocaPainel(janelaPrincipal, null, "iCall");
	}
	public void trocaPainel(Component coloca, Component remover, String titulo){
		try{
			if(remover != null){
				janela.remove(remover);
			}
			if(coloca.equals(janelaPrincipal)){
				janelaPrincipal = (new JanelaPrincipal()).panel();
				janela.add((JPanel)janelaPrincipal);
			}else if(coloca.equals(cadastraAula)){
				cadastraAula = (new CadastAula()).panel();
				janela.add((JPanel)cadastraAula);
			}else if(coloca.equals(cadastraAluno)){
				cadastraAluno = (new CadastAluno()).panel();
				janela.add((JPanel)cadastraAluno);
			}else if(coloca.equals(cadastraAlunos)){
				cadastraAlunos = (new CadastAlunoAula()).panel();
				janela.add((JPanel)cadastraAlunos);
			}else if(coloca.equals(login)){
				login = (new Login()).panel();
				janela.add((JPanel)login);
			}else if(coloca.equals(marcapresenca)){
				marcapresenca = (new MarcaPresenca()).panel();
				janela.add((JPanel)marcapresenca);
			}else if(coloca.equals(marcapresenca2)){
				marcapresenca2 = (new MarcaPresenca2()).panel(titulo.split("##")[1]);
				janela.add((JPanel)marcapresenca2);
			}else if(coloca.equals(gerarRelatorio)){
				gerarRelatorio = (new GerarRelatorio()).panel();
				janela.add((JPanel)gerarRelatorio);
			}else if(coloca.equals(panelProfessores)){
				panelProfessores = (new Professores()).panel();
				janela.add((JPanel)panelProfessores);
			}else if(coloca.equals(editarAluno)){
				editarAluno = (new AlunoEditar()).panel();
				janela.add((JPanel)editarAluno);
			}else if(coloca.equals(novoProfessor)){
				novoProfessor = (new ProfessorNovo()).panel();
				janela.add((JPanel)novoProfessor);
			}else if(coloca.equals(editarProf)){
				editarProf = (new ProfessorEditar()).panel();
				janela.add((JPanel)editarProf);
			}else if(coloca.equals(deletProf)){
				deletProf = (new ProfessorExcluir()).panel();
				janela.add((JPanel)deletProf);
			}else if(coloca.equals(listProf)){
				listProf = (new ProfessoresLista()).panel();
				janela.add((JPanel)listProf);
			}else if(coloca.equals(gerCadasAula)){
				gerCadasAula = (new GerCadastrosAula()).panel();
				janela.add((JPanel)gerCadasAula);
			}else if(coloca.equals(gerCadasAluno)){
				gerCadasAluno = (new GerCadastrosAluno()).panel();
				janela.add((JPanel)gerCadasAluno);
			}else if(coloca.equals(listarAlunos)){
				listarAlunos = (new ListarAlunos()).panel();
				janela.add((JPanel)listarAlunos);
			}else if(coloca.equals(listarAulas)){
				listarAulas = (new ListarAulas()).panel();
				janela.add((JPanel)listarAulas);
			}
			else if(coloca.equals(panelSobre)){
				panelSobre = (new About()).panel();
				janela.add((JPanel)panelSobre);
			}
				janela.setTitle(titulo.replace("##", " - "));
				janela.repaint();
				janela.setVisible(true);
			}catch (Exception e) {
				System.err.println("Erro: trocaPainel\n\n"+e.getMessage());
			}
		}

	}
