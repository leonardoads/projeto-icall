package InterfaceGrafica;

import classes.*;

import java.awt.BorderLayout;
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
			
		}else if(e.getSource() == cadastAlunoAula){
			trocaPainel(cadastraAlunos,cadastraAluno,"iCall - Cadastra aluno em aula");
		}else if(e.getSource() == cadastrar){
			String matricula = numMatricula.getText().toString();
			String nome = nomeAluno.getText().toString() ;

			try {
				Runtime.getRuntime().exec("gnome-terminal -x sh "+ICALLPATH+"enroll.sh"
						+ " " + nome.replaceAll(" ", "_") + " "
						+ matricula);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void criaPainelTroca(){
		cadastrar.setToolTipText("Click para continuar o cadastro");
		
		cadastrar.setMnemonic(KeyEvent.VK_I);
		cadastrar.addActionListener(this);
        label.setLabelFor(cadastrar);
        
		panelAluno.add(labelAluno);
		panelAula.add(labelMatricula);
		panelButton.add(cadastrar);
		panelButton.setBorder(BorderFactory.createEmptyBorder(
                25, //top
                280, //left
                15, //bottom
                10) //right
                );
		panelAulas.add(numMatricula);
		panelNomeAluno.add(nomeAluno);
		
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

		painel.add(aluno);
		painel.add(aula);
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
