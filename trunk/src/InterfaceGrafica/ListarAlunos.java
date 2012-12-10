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

public class ListarAlunos extends JanelaPrincipal implements ActionListener{
	String nomeArq = "enroll.icall";
	JList lista;
	JScrollPane scroll;
	final String systemSeparator = java.io.File.separator;
	final String ICALLPATH = System.getProperty("user.home") + systemSeparator
			+ "iCall" + systemSeparator;
	
	public void preencheLista(String nomeArq){
		try {
			DefaultComboBoxModel modelo = new DefaultComboBoxModel(LerArquivo.lerArq(nomeArq).replace("##", " - ").replace("|", " - ").split("\n"));
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
		if (e.getSource() == voltar) {
			trocaPainel(gerarRelatorio, listarAlunos, "iCall");
		}else if (e.getSource() == verificAulo) {
			trocaPainel(marcapresenca, listarAlunos, "iCall - Marca presença");
		}else if(e.getSource() == btsobre){
			trocaPainel(panelSobre,listarAlunos,"Sobre");
		}else if (e.getSource() == gerRelatorio) {

		} else if (e.getSource() == professores) {
			trocaPainel(panelProfessores, listarAlunos,
					"iCall - Gerenciamento de contas");
		}else if(e.getSource() == gerenciaAlunos){
			trocaPainel(gerCadasAluno,listarAlunos,"iCall - Alunos");
		}else if(e.getSource() == gerenciaAulas){
			trocaPainel(gerCadasAula,listarAlunos,"iCall - Aulas");
		}
	}
}
