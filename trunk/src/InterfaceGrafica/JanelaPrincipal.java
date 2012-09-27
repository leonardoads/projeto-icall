package InterfaceGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

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
	JButton gerenciaAlunos = new JButton("Alunos");
	JButton gerenciaAulas = new JButton("Disciplinas");
	JButton verificAulo = new JButton("Reg. presença");
	JButton gerRelatorio = new JButton("Relatórios");
	JButton professores = new JButton("Professor");
	JButton btsobre = new JButton("Sobre");
	JButton btajuda = new JButton("Ajuda");
	JButton voltar = new JButton("Voltar");
	
	JPanel esquerda = new JPanel();
	JPanel direita = new JPanel(new BorderLayout(0, 1));
	
	
	public void colocaAjuda(){
		verificAulo.setToolTipText("Clique para marcar a presença de um aluno na aula");
		gerenciaAlunos.setToolTipText("Editar ou excluir algum cadastro ou Listar todos os cadastrados");
		gerenciaAulas.setToolTipText("Editar ou excluir algum cadastro ou Listar todos as disciplinas cadastradas cadastradas");
		gerRelatorio.setToolTipText("Clique para gerar a lista de presença");
		professores.setToolTipText("Clique para gerenciar o cadastro de professores");
		voltar.setToolTipText("Clique para voltar a página inicial");
	}
	public void criaPainel(){
        verificAulo.addActionListener(this);
        
        gerenciaAlunos.setMnemonic(KeyEvent.VK_A);
        gerenciaAlunos.addActionListener(this);
        
        gerenciaAulas.setMnemonic(KeyEvent.VK_D);
        gerenciaAulas.addActionListener(this);
        
        gerRelatorio.setMnemonic(KeyEvent.VK_R);
        gerRelatorio.addActionListener(this);

        professores.setMnemonic(KeyEvent.VK_P);
        professores.addActionListener(this);
        
        btsobre.setMnemonic(KeyEvent.VK_S);
		btsobre.addActionListener(this);
		
		btajuda.setMnemonic(KeyEvent.VK_J);
		btajuda.addActionListener(this);
		
        voltar.setMnemonic(KeyEvent.VK_L);
        voltar.addActionListener(this);
		
		local.setText("iCall");
		local.setForeground(new Color(250,250,250));
		local.setFont(new Font("Arial",Font.BOLD,19));
		
		if(tipoUsuario.equals("PROFESSOR")){
    		esquerda.add(verificAulo);
    		verificAulo.setBounds(10, 10, 150, 20);
            esquerda.add(gerRelatorio);
            gerRelatorio.setBounds(10, 40, 150, 20);
            esquerda.add(voltar);
            voltar.setBounds(10, 100, 150, 20);
    	}else{
    		esquerda.add(gerenciaAlunos);
            gerenciaAlunos.setBounds(10, 10, 150, 20);
            esquerda.add(gerenciaAulas);
            gerenciaAulas.setBounds(10, 40, 150, 20);
            esquerda.add(professores);
            professores.setBounds(10, 70, 150, 20);
            esquerda.add(gerRelatorio);
            gerRelatorio.setBounds(10, 100, 150, 20);
            esquerda.add(btsobre);
            btsobre.setBounds(10, 160, 150, 20);
            esquerda.add(btajuda);
            btajuda.setBounds(10, 190, 150, 20);
            esquerda.add(voltar);
            voltar.setBounds(10, 220, 150, 20);
    	}
        
        direita.setLayout(null);
        painelFinalTroca.setLayout(null);
        
        painelFinalTroca.setBackground(new Color(255,255,255,0)); 
        
        esquerda.setLayout(null);
        esquerda.setBackground(new Color(255,255,255,0));  
        
        image.add(local);
        local.setBounds(350, 400, 300, 20);
        
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
		}else if(e.getSource() == gerenciaAlunos){
			trocaPainel(gerCadasAluno,janelaPrincipal,"iCall - Alunos");
		}else if(e.getSource() == gerenciaAulas){
			trocaPainel(gerCadasAula,janelaPrincipal,"iCall - Aulas");
		}else if(e.getSource() == verificAulo){
			trocaPainel(marcapresenca,janelaPrincipal,"iCall - Marca presença");
		}else if(e.getSource() == gerRelatorio){
			trocaPainel(gerarRelatorio,janelaPrincipal,"iCall - Relatórios");
		}else if(e.getSource() == professores){
			trocaPainel(panelProfessores,janelaPrincipal,"iCall - Professores");
		}else if(e.getSource() == btsobre){
			trocaPainel(panelSobre,janelaPrincipal,"Sobre");
		}
	}
}
