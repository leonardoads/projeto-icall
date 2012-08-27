package InterfaceGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classes.LerArquivo;

public class CadastAlunoAula extends JanelaPrincipal implements ActionListener{
	
	JLabel labelAluno = new JLabel("Digite o nome do aluno");
	JLabel labelAula = new JLabel("Selecione uma aula");
	
	JButton cadastrar = new JButton("Cadastra");
	
	JComboBox comboAlunos = new JComboBox();
	
	JComboBox comboAulas = new JComboBox();
	
	JPanel panelAluno= new JPanel(new GridLayout(0,1));
	JPanel panelAula= new JPanel(new GridLayout(0,1));
	JPanel panelButton= new JPanel(new GridLayout(0,1));
	JPanel panelAulas= new JPanel(new GridLayout(0,1));
	JPanel panelNomeAluno= new JPanel(new GridLayout(0,1));
	
	JPanel aluno= new JPanel(new GridLayout(0,1));
	JPanel aula= new JPanel(new GridLayout(0,1));
	
	JPanel painel= new JPanel(new GridLayout(0,1));
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
			
		}else if(e.getSource() == gerRelatorio){
			
		}else if(e.getSource() == cadastrar){
			String aula = nomeAulas[comboAulas.getSelectedIndex()];
			String aulo = nomeAlunos[comboAlunos.getSelectedIndex()];
		}
	}

	public void criaPainelTroca(){
		cadastrar.setToolTipText("Click para continuar o cadastro");
		
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
		
		panelAluno.add(labelAluno);
		panelAula.add(labelAula);
		panelButton.add(cadastrar);
		panelButton.setBorder(BorderFactory.createEmptyBorder(
                25, //top
                280, //left
                15, //bottom
                10) //right
                );
		comboAulas.setBackground(Color.WHITE);
		comboAlunos.setBackground(Color.WHITE);
		panelAulas.add(comboAulas);
		panelNomeAluno.add(comboAlunos);
		
		aluno.add(panelAluno);
		aluno.add(panelNomeAluno);
		aula.add(panelAula);
		aula.add(panelAulas);
		
		aluno.setBorder(BorderFactory.createEmptyBorder(
				 0, //top
	             20, //left
	             0, //bottom
	             20) //right
	             );
		
		aula.setBorder(BorderFactory.createEmptyBorder(
				0, //top
                20, //left
                0, //bottom
                20) //right
                ); //right

		painel.add(aula);
		painel.add(aluno);
		painel.add(panelButton);

    	painelFinalTroca.add(painel, BorderLayout.NORTH);
	}
	public Component panel(){
		colocaAjuda();
		criaPainelTroca();
		criaPainel();
		
		return painelFinal;
	}
}