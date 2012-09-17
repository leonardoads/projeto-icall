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

public class CadastAluno extends GerCadastrosAluno implements ActionListener{

	final String systemSeparator = java.io.File.separator;
	final String ICALLPATH = System.getProperty("user.home") + systemSeparator
			+ ".iCall" + systemSeparator + "icall-libFP" + systemSeparator;
	
	JLabel labelAluno = new JLabel("Digite o nome do aluno");
	JLabel labelMatricula = new JLabel("Digite a matricula");
	JTextField nomeAluno = new JTextField();
	JTextField numMatricula = new JTextField();

	JButton cadastrar = new JButton("Cadastra");
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == modificar){
		//	trocaPainel(editarProf,gerCadasAluno,"iCall - Editar cadastro");
		}else if(e.getSource() == listar){
			trocaPainel(listarAlunos,cadastraAluno,"iCall - Lista de cadastros");
		}else if(e.getSource() == deletar){
		//	trocaPainel(deletProf,gerCadasAluno,"iCall - Excluir cadastro");
		}
		else if(e.getSource() == novo){
			trocaPainel(cadastraAluno,cadastraAluno,"iCall - Cadastra aluno");
		}else if(e.getSource() == voltar){
			trocaPainel(gerCadasAluno,cadastraAluno,"iCall");
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
