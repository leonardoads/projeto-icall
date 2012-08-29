package InterfaceGrafica;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JanelaPrincipal extends Main implements ActionListener{
	final String systemSeparator = java.io.File.separator;
	final String ICALLLOGO = System.getProperty("user.home") + systemSeparator
			+ ".iCall" + systemSeparator +"logo-Icall.png";

	JPanel painelFinal = new JPanel(new BorderLayout(1, 0));
	JPanel painelFinalTroca = new JPanel(new BorderLayout(1, 0));

	JPanel painel= new JPanel(new GridLayout(0,1));
	final JLabel label = new JLabel("iCall");
	JButton cadastAula = new JButton("Cadastra aula");
	JButton cadastAluno = new JButton("Cadastra aluno");
	JButton cadastAlunoAula = new JButton("Cad. aluno em aula");
	JButton verificAulo = new JButton("Marca presença");
	JButton gerRelatorio = new JButton("Gerar relatório");
	JButton voltar = new JButton("Voltar");
	
	JPanel buttonAula = new JPanel(new GridLayout(0, 1));
	JPanel buttonAluno = new JPanel(new GridLayout(0, 1));
	JPanel buttonVerif = new JPanel(new GridLayout(0, 1));
	JPanel buttonGerRe = new JPanel(new GridLayout(0, 1));
	JPanel buttonVoltar = new JPanel(new GridLayout(0, 1));
	JPanel buttonAlunoAula = new JPanel(new GridLayout(0, 1));
	
	JPanel botoes = new JPanel(new GridLayout(0, 1));
	JPanel esquerda = new JPanel(new BorderLayout(0, 1));
	JPanel direita = new JPanel(new BorderLayout(0, 1));
	
	public void colocaAjuda(){
		cadastAula.setToolTipText("Clique para cadastrar uma nova aula");
		cadastAluno.setToolTipText("Clique para cadastrar um novo aluno");
		verificAulo.setToolTipText("Clique para marcar a presença de um aluno na aula");
		cadastAlunoAula.setToolTipText("Clique para cadastrar alunos nas aulas");
		gerRelatorio.setToolTipText("Clique para gerar a lista de presença");
		voltar.setToolTipText("Clique para voltar a página inicial");
	}
	public void criaPainel(){
		cadastAula.setMnemonic(KeyEvent.VK_I);
		cadastAula.addActionListener(this);
        label.setLabelFor(cadastAula);
        
        cadastAluno.setMnemonic(KeyEvent.VK_I);
        cadastAluno.addActionListener(this);
        label.setLabelFor(cadastAluno);
        
        verificAulo.setMnemonic(KeyEvent.VK_I);
        verificAulo.addActionListener(this);
        label.setLabelFor(verificAulo);
        
        gerRelatorio.setMnemonic(KeyEvent.VK_I);
        gerRelatorio.addActionListener(this);
        label.setLabelFor(gerRelatorio);
        
        cadastAlunoAula.setMnemonic(KeyEvent.VK_I);
        cadastAlunoAula.addActionListener(this);
        label.setLabelFor(cadastAlunoAula);
        
        voltar.setMnemonic(KeyEvent.VK_I);
        voltar.addActionListener(this);
        label.setLabelFor(voltar);
        
        buttonAula.add(cadastAula);
    	buttonAluno.add(cadastAluno);
    	buttonAlunoAula.add(cadastAlunoAula);
    	buttonVerif.add(verificAulo);
    	buttonGerRe.add(gerRelatorio);
    	buttonVoltar.add(voltar);
    	
    	buttonAlunoAula.setBorder(BorderFactory.createEmptyBorder(
                5, //topesquerda
                10, //leftsai
                5, //bottom
                10) //right
                );
    	buttonAula.setBorder(BorderFactory.createEmptyBorder(
                5, //topesquerda
                10, //leftsai
                5, //bottom
                10) //right
                );
    	buttonAluno.setBorder(BorderFactory.createEmptyBorder(
                5, //top
                10, //left
                5, //bottom
                10) //right
                );
    	buttonVerif.setBorder(BorderFactory.createEmptyBorder(
                5, //top
                10, //left
                5, //bottomesquerda
                10) //right
                );
    	buttonGerRe.setBorder(BorderFactory.createEmptyBorder(
                5, //top
                10, //left
                5, //bottom
                10) //right
                );
    	buttonVoltar.setBorder(BorderFactory.createEmptyBorder(
                5, //top
                10, //left
                5, //bottom
                10) //right
                );
    	
    	botoes.add(buttonAula);
    	botoes.add(buttonAluno);
    	botoes.add(buttonAlunoAula);
    	botoes.add(buttonVerif);
    	botoes.add(buttonGerRe);
    	botoes.add(buttonVoltar);
        
    	esquerda.add(botoes, BorderLayout.NORTH);
    	direita.add(painelFinalTroca, BorderLayout.NORTH);
    	painelFinal.add(esquerda, BorderLayout.WEST);
    	painelFinal.add(direita, BorderLayout.CENTER);
    	
	}
	public void criaPainelTroca(){
		voltar.setText("logoff");
		JLabel image = new JLabel(new ImageIcon("res/ICALLTELA.jpg"));
		direita.add(image);
	}
	public Component panel(){
		colocaAjuda();
		criaPainelTroca();
		criaPainel();
		
		return painelFinal;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == voltar){
			trocaPainel(login,janelaPrincipal,"iCall - Cadastra aula");
		}else if(e.getSource() == cadastAula){
			trocaPainel(cadastraAula,janelaPrincipal,"iCall - Cadastra aula");
		}else if(e.getSource() == cadastAluno){
			trocaPainel(cadastraAluno,janelaPrincipal,"iCall - Cadastra aluno");
		}else if(e.getSource() == verificAulo){
			trocaPainel(marcapresenca,janelaPrincipal,"iCall - Marca presença");
		}else if(e.getSource() == gerRelatorio){
			
		}else if(e.getSource() == cadastAlunoAula){
			trocaPainel(cadastraAlunos,janelaPrincipal,"iCall - Cadastra aluno em aula");
		}
	}
}
