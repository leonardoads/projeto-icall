package InterfaceGrafica;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import classes.LerArquivo;

public class ListarAulas extends GerCadastrosAula implements ActionListener{
	String nomeArq = "eventos.icall";
	JList lista;
	JScrollPane scroll;
	final String systemSeparator = java.io.File.separator;
	final String ICALLPATH = System.getProperty("user.home") + systemSeparator
			+ "iCall" + systemSeparator;
	
	public void preencheLista(String nomeArq){
		try {
			DefaultComboBoxModel modelo = new DefaultComboBoxModel(LerArquivo.lerArq(nomeArq).replace("##", " - ").split("\n"));
			this.lista  = new JList(modelo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		scroll = new JScrollPane(this.lista);
	}
	public void criaPainelTroca(String nomeArq){        
		preencheLista(ICALLPATH+nomeArq);
		
		painelFinalTroca.add(scroll);
		scroll.setBounds(5, 10, 480, 230);
		
	}
	public Component panel(){
		colocaAjuda();
		criaPainelTroca(nomeArq);
		criaPainel();
		
		return painelFinal;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	if(e.getSource() == modificar){
		//	trocaPainel(editarProf,gerCadasAluno,"iCall - Editar cadastro");
		}else if(e.getSource() == deletar){
		//	trocaPainel(deletProf,gerCadasAluno,"iCall - Excluir cadastro");
		}
		else if(e.getSource() == novo){
			trocaPainel(cadastraAula,listarAulas,"iCall - Cadastra aula");
		}else if(e.getSource() == voltar){
			trocaPainel(gerCadasAula,listarAulas,"iCall");
		}else if(e.getSource() == cadastAlunoAula){
			trocaPainel(cadastraAlunos,listarAulas,"iCall - Cadastra aluno em aula");
		}
	}
}
