package InterfaceGrafica;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classes.LerArquivo;

public class MarcaPresenca2 extends JanelaPrincipal implements ActionListener{
	
	JPanel painel= new JPanel();
	
	JLabel labelMatricula = new JLabel("Digite a matricula");
	
	JTextField matricula = new JTextField();
	
	JButton computar = new JButton("Computar");
	JButton voltar = new JButton("Voltar");
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == voltar){
			trocaPainel(marcapresenca,marcapresenca2,"iCall - Marca presença");
			this.matricula.setText("");
		}else if(e.getSource() == cadastAula){
			trocaPainel(cadastraAula,marcapresenca2,"iCall - Cadastra aula");
		}else if(e.getSource() == cadastAluno){
			trocaPainel(cadastraAluno,marcapresenca2,"iCall - Cadastra aluno");
		}else if(e.getSource() == verificAulo){
			JOptionPane.showMessageDialog(null, "Você ja esta ai");
		}else if(e.getSource() == gerRelatorio){

		}else if(e.getSource() == cadastAlunoAula){
			trocaPainel(cadastraAlunos,marcapresenca2,"iCall - Cadastra aluno em aula");
		}else if(e.getSource() == computar){
			String matricula = this.matricula.getText().toString();
			this.matricula.setText("");
		}
	}
	public void criaPainelTroca(){
		
		computar.setMnemonic(KeyEvent.VK_I);
		computar.addActionListener(this);
        label.setLabelFor(computar);
        
        voltar.setMnemonic(KeyEvent.VK_I);
        voltar.addActionListener(this);
        label.setLabelFor(voltar);
        
        verificAulo.setMnemonic(KeyEvent.VK_I);
        verificAulo.addActionListener(this);
        label.setLabelFor(verificAulo);
		
		painel.setLayout(null);
		
		painel.add(labelMatricula);
		labelMatricula.setBounds(290, 90, 140, 20);
		
		painel.add(matricula);
		matricula.setBounds(230, 120,250, 20);
		
		painel.add(computar);
		computar.setBounds(230, 170,110, 20);
		
		painel.add(voltar);
		voltar.setBounds(370, 170,110, 20);
		
		painelFinalTroca.add(painel, BorderLayout.NORTH);
	}
	public Component panel(){
		colocaAjuda();
		criaPainelTroca();
		criaPainel();
		
		return painel;
	}
}
