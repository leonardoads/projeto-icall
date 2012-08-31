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

public class MarcaPresenca extends JanelaPrincipal implements ActionListener{
	
	JPanel painel= new JPanel();
	
	JLabel labelaula = new JLabel("Escolha a aula");
	JLabel labeltempo = new JLabel("Digite o tempo em que o sistema ficara online. EM MINUTOS");
	
	JComboBox comboAulas = new JComboBox();
	
	JTextField tempo = new JTextField();
	
	JButton iniciar = new JButton("Iniciar");
	JButton voltar = new JButton("Voltar");
	
	String[] nomeAulas;
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == voltar){
			trocaPainel(janelaPrincipal,marcapresenca,"iCall");
		}else if(e.getSource() == cadastAula){
			trocaPainel(cadastraAula,marcapresenca,"iCall - Cadastra aula");
		}else if(e.getSource() == cadastAluno){
			trocaPainel(cadastraAluno,marcapresenca,"iCall - Cadastra aluno");
		}else if(e.getSource() == verificAulo){
			JOptionPane.showMessageDialog(null, "Você ja esta ai");
		}else if(e.getSource() == gerRelatorio){

		}else if(e.getSource() == cadastAlunoAula){
			trocaPainel(cadastraAlunos,marcapresenca,"iCall - Cadastra aluno em aula");
		}else if(e.getSource() == iniciar){
			trocaPainel(marcapresenca2,marcapresenca,"iCall - Marca presença##"+nomeAulas[comboAulas.getSelectedIndex()]);
		}
	}
	public void criaPainelTroca(){
		try {
			String aulas = LerArquivo.lerArq(System.getProperty("user.home") + "/iCall/"+"eventos.icall");
			String[] arrayAulas = aulas.split("\n");
			nomeAulas = new String[arrayAulas.length];
			for(int i=0;i<arrayAulas.length;i++){
				nomeAulas[i] = arrayAulas[i].split("##")[0];
				comboAulas.addItem(arrayAulas[i].split("##")[0] +" - "+arrayAulas[i].split("##")[1]);
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
		
		iniciar.setMnemonic(KeyEvent.VK_I);
		iniciar.addActionListener(this);
        label.setLabelFor(iniciar);
        
        voltar.setMnemonic(KeyEvent.VK_I);
        voltar.addActionListener(this);
        label.setLabelFor(voltar);
        
        verificAulo.setMnemonic(KeyEvent.VK_I);
        verificAulo.addActionListener(this);
        label.setLabelFor(verificAulo);
		
		painel.setLayout(null);
		
		painel.add(labelaula);
		labelaula.setBounds(20, 20, 450, 20);
		
		painel.add(comboAulas);
		comboAulas.setBounds(20, 50, 550, 20);
		
		painel.add(labeltempo);
		labeltempo.setBounds(20, 90,450, 20);
		
		painel.add(tempo);
		tempo.setBounds(20, 120,250, 20);
		
		painel.add(iniciar);
		iniciar.setBounds(20, 170,110, 20);
		
		painel.add(voltar);
		voltar.setBounds(140, 170,110, 20);
		
		painelFinalTroca.add(painel, BorderLayout.NORTH);
	}
	public Component panel(){
		colocaAjuda();
		criaPainelTroca();
		criaPainel();
		
		return painel;
	}
}
