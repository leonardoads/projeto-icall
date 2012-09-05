package InterfaceGrafica;

import gerenciarArq.Arquivo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import classes.LerArquivo;

public class CadastAlunoAula extends JanelaPrincipal implements ActionListener{
	final String systemSeparator = java.io.File.separator;
	final String ICALLPATH = System.getProperty("user.home") + systemSeparator
			+ "iCall" + systemSeparator;
	JLabel labelAluno = new JLabel("Digite o nome do aluno");
	JLabel labelAula = new JLabel("Selecione uma aula");
	
	JButton cadastrar = new JButton("Cadastra");
	
	JComboBox comboAlunos = new JComboBox();
	
	JComboBox comboAulas = new JComboBox();
	
	JList lista = new JList();
	
	String[] nomeAulas;
	String[] nomeAlunos;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == voltar){
			trocaPainel(janelaPrincipal,cadastraAlunos,"iCall");
		}else if(e.getSource() == cadastAula){
			trocaPainel(cadastraAula,cadastraAlunos,"iCall - Cadastra aula");
		}else if(e.getSource() == cadastAluno){
			trocaPainel(cadastraAluno,cadastraAlunos,"iCall - Cadastra aluno");
		}else if(e.getSource() == verificAulo){
			trocaPainel(marcapresenca,cadastraAlunos,"iCall - Marca presença");
		}else if(e.getSource() == gerRelatorio){
			trocaPainel(gerarRelatorio,cadastraAlunos,"iCall - Gerar relatório");
		}else if(e.getSource() == professores){
			trocaPainel(panelProfessores,cadastraAlunos,"iCall - Gerenciamento de contas");
		}else if(e.getSource() == cadastrar){
			String aula = comboAulas.getSelectedItem().toString();
			String aluno = comboAlunos.getSelectedItem().toString();
			
			Arquivo.escreveArquivo(aluno+"\n", ICALLPATH+"matriculados_"+aula+".icall", true);
		}
	}

	public void criaPainelTroca(){
		cadastrar.setToolTipText("Click para continuar o cadastro");
		cadastrar.setMnemonic(KeyEvent.VK_I);
		cadastrar.addActionListener(this);
        label.setLabelFor(cadastrar);
		try {
			String aulas = LerArquivo.lerArq(System.getProperty("user.home") + "/iCall/"+"eventos.icall");
			String[] arrayAulas = aulas.split("\n");
			nomeAulas = new String[arrayAulas.length];
			for(int i=0;i<arrayAulas.length;i++){
				nomeAulas[i] = arrayAulas[i].split("##")[0];
				comboAulas.addItem(arrayAulas[i].split("##")[0] +" - "+arrayAulas[i].split("##")[1]);
			}
			
			String alunos = LerArquivo.lerArq(System.getProperty("user.home") + "/iCall/"+"enroll.icall");
			String[] arrayAlunos = alunos.split("\n");
			nomeAlunos = new String[arrayAlunos.length];
			for(int i=0;i<arrayAulas.length;i++){
				nomeAlunos[i] = arrayAlunos[i].split("##")[0];
				comboAlunos.addItem(arrayAlunos[i].split("##")[0] +" - "+arrayAlunos[i].split("##")[1]);
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		painelFinalTroca.add(labelAula);
		labelAula.setBounds(5, 10, 350, 20);
		painelFinalTroca.add(comboAulas);
		comboAulas.setBounds(5, 40, 450, 20);
		painelFinalTroca.add(labelAluno);
		labelAluno.setBounds(5, 70, 350, 20);
		painelFinalTroca.add(comboAlunos);
		comboAlunos.setBounds(5, 100, 450, 20);
		painelFinalTroca.add(cadastrar);
		cadastrar.setBounds(105, 140, 150, 20);
	}
	public Component panel(){
		colocaAjuda();
		criaPainelTroca();
		criaPainel();
		
		return painelFinal;
	}
}