package InterfaceGrafica;

import classes.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CadastAluno extends JanelaPrincipal implements ActionListener{

	final String systemSeparator = java.io.File.separator;
	final String ICALLPATH = System.getProperty("user.home") + systemSeparator
			+ ".iCall" + systemSeparator + "icall-libFP" + systemSeparator;
	
	JLabel labelAluno = new JLabel("Digite o nome do aluno");
	JLabel labelMatricula = new JLabel("Digite a matricula");
	
	JButton cadastrar = new JButton("Cadastra");
	
	JTextField nomeAluno = new JTextField();
	JTextField numMatricula = new JTextField();
	
	
	JPanel panelAluno= new JPanel(new GridLayout(0,1));
	JPanel panelAula= new JPanel(new GridLayout(0,1));
	JPanel panelButton= new JPanel(new GridLayout(0,1));
	JPanel panelAulas= new JPanel(new GridLayout(0,1));
	JPanel panelNomeAluno= new JPanel(new GridLayout(0,1));
	
	JPanel aluno= new JPanel(new GridLayout(0,1));
	JPanel aula= new JPanel(new GridLayout(0,1));
	
	JPanel painel= new JPanel(new GridLayout(0,1));
	String[] nomeAulas;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == voltar){
			trocaPainel(janelaPrincipal,cadastraAluno,"iCall");
		}else if(e.getSource() == cadastAula){
			trocaPainel(cadastraAula,cadastraAluno,"iCall - Cadastra aula");
		}else if(e.getSource() == cadastAluno){
			
		}else if(e.getSource() == verificAulo){
			trocaPainel(marcapresenca,cadastraAluno,"iCall - Marca presença");
		}else if(e.getSource() == gerRelatorio){
			trocaPainel(gerarRelatorio,cadastraAluno,"iCall - Gerar relatório");
		}else if(e.getSource() == cadastAlunoAula){
			trocaPainel(cadastraAlunos,cadastraAluno,"iCall - Cadastra aluno em aula");
		}else if(e.getSource() == professores){
			trocaPainel(panelProfessores,cadastraAluno,"iCall - Gerenciamento de contas");
		}else if(e.getSource() == cadastrar){
			String matricula = numMatricula.getText().toString();
			String nome = nomeAluno.getText().toString() ;
			if(!matricula.equals("") && !nome.equals("")){
				try {
					Runtime.getRuntime().exec("gnome-terminal -x sh "+ICALLPATH+"enroll.sh"
							+ " " + nome.replaceAll(" ", "_") + " "
							+ matricula);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else{
				JOptionPane.showMessageDialog(null, "ERRO: existem campos em branco");
			}
		}
	}

	public void criaPainelTroca(){
		cadastrar.setToolTipText("Click para continuar o cadastro");
		
		cadastrar.setMnemonic(KeyEvent.VK_I);
		cadastrar.addActionListener(this);
        label.setLabelFor(cadastrar);
        
        painelFinalTroca.add(labelAluno);
        labelAluno.setBounds(5, 10, 350, 20);
        painelFinalTroca.add(nomeAluno);
        nomeAluno.setBounds(5, 40, 350, 20);
        painelFinalTroca.add(labelMatricula);
        labelMatricula.setBounds(5, 70, 350, 20);
        painelFinalTroca.add(numMatricula);
        numMatricula.setBounds(5, 100, 350, 20);
        painelFinalTroca.add(cadastrar);
        cadastrar.setBounds(225, 140, 130, 20);
	}
	public Component panel(){
		colocaAjuda();
		criaPainelTroca();
		criaPainel();
		
		return painelFinal;
	}
}
