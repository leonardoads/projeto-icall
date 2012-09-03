package InterfaceGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
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
	JPanel painelFinalTroca = new JPanel();

	JLabel image = new JLabel(new ImageIcon("res/ICALLTELA.jpg"));
	
	JPanel painel= new JPanel(new GridLayout(0,1));
	final JLabel label = new JLabel("iCall");
	JButton cadastAula = new JButton("Cadastra aula");
	JButton cadastAluno = new JButton("Cadastra aluno");
	JButton cadastAlunoAula = new JButton("Preenche aulas");
	JButton verificAulo = new JButton("Marca presença");
	JButton gerRelatorio = new JButton("Gerar relatório");
	JButton professores = new JButton("Professores");
	JButton voltar = new JButton("Voltar");
	
	JPanel esquerda = new JPanel();
	JPanel direita = new JPanel(new BorderLayout(0, 1));
	
	
	public void colocaAjuda(){
		cadastAula.setToolTipText("Clique para cadastrar uma nova aula");
		cadastAluno.setToolTipText("Clique para cadastrar um novo aluno");
		verificAulo.setToolTipText("Clique para marcar a presença de um aluno na aula");
		cadastAlunoAula.setToolTipText("Clique para cadastrar alunos nas aulas");
		gerRelatorio.setToolTipText("Clique para gerar a lista de presença");
		professores.setToolTipText("Clique para gerenciar o cadastro de professores");
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

        professores.setMnemonic(KeyEvent.VK_I);
        professores.addActionListener(this);
        label.setLabelFor(professores);
        
        voltar.setMnemonic(KeyEvent.VK_I);
        voltar.addActionListener(this);
        label.setLabelFor(voltar);
		
		local.setText(tipoUsuario);
		
		if(tipoUsuario.equals("PROFESSOR")){
    		esquerda.add(verificAulo);
    		verificAulo.setBounds(10, 10, 150, 20);
            esquerda.add(gerRelatorio);
            gerRelatorio.setBounds(10, 40, 150, 20);
            esquerda.add(voltar);
            voltar.setBounds(10, 70, 150, 20);
    	}else{
    		esquerda.add(cadastAluno);
            cadastAluno.setBounds(10, 10, 150, 20);
            esquerda.add(cadastAula);
            cadastAula.setBounds(10, 40, 150, 20);
            esquerda.add(cadastAlunoAula);
            cadastAlunoAula.setBounds(10, 70, 150, 20);
            esquerda.add(verificAulo);
            verificAulo.setBounds(10, 100, 150, 20);
            esquerda.add(gerRelatorio);
            gerRelatorio.setBounds(10, 130, 150, 20);
            esquerda.add(professores);
            professores.setBounds(10, 160, 150, 20);
            esquerda.add(voltar);
            voltar.setBounds(10, 190, 150, 20);
    	}
        
        direita.setLayout(null);
        painelFinalTroca.setLayout(null);
        
        painelFinalTroca.setBackground(new Color(255,255,255,0)); 
        
        esquerda.setLayout(null);
        esquerda.setBackground(new Color(255,255,255,0));  
        
        image.add(local);
        local.setBounds(350, 400, 200, 20);
        
    	direita.add(painelFinalTroca);
    	painelFinalTroca.setBounds(0, 0, 500, 250);
    	
    	image.add(esquerda);
    	esquerda.setBounds(0, 0, 160, 300);	
    	
    	direita.setBackground(new Color(255,255,255,0));  
    	
    	image.add(direita);
    	direita.setBounds(180, 10, 500, 250);
    	
    	painelFinal.add(image);
	}
	public void criaPainelTroca(){
		voltar.setText("logoff");
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
			trocaPainel(gerarRelatorio,janelaPrincipal,"iCall - Gerar relatório");
		}else if(e.getSource() == cadastAlunoAula){
			trocaPainel(cadastraAlunos,janelaPrincipal,"iCall - Cadastra aluno em aula");
		}else if(e.getSource() == professores){
			trocaPainel(panelProfessores,janelaPrincipal,"iCall - Gerenciamento de contas");
		}
	}
}
