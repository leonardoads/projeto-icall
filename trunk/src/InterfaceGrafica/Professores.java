package InterfaceGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Professores extends Main implements ActionListener{
	final String systemSeparator = java.io.File.separator;
	final String ICALLLOGO = System.getProperty("user.home") + systemSeparator
			+ ".iCall" + systemSeparator +"logo-Icall.png";

	JPanel painelFinal = new JPanel(new BorderLayout(1, 0));
	JPanel painelFinalTroca = new JPanel();

	JLabel image = new JLabel(new ImageIcon("res/ICALLTELA.jpg"));
	
	JPanel painel= new JPanel(new GridLayout(0,1));
	final JLabel label = new JLabel("iCall");
	
	JButton novo = new JButton("Novo");
	JButton modificar = new JButton("Modificar");
	JButton listar = new JButton("Listar");
	JButton deletar = new JButton("Excluir");
	JButton voltar = new JButton("Voltar");
	
	JPanel esquerda = new JPanel();
	JPanel direita = new JPanel(new BorderLayout(0, 1));
	
	public void colocaAjuda(){
		novo.setToolTipText("Nova conta para professor");
		modificar.setToolTipText("Modificar uma conta de professor");
		listar.setToolTipText("Lista de professores cadastrados");
		deletar.setToolTipText("Deletar o cadastro de um professor");
		voltar.setToolTipText("Clique para voltar a página inicial");
	}
	public void criaPainel(){
		novo.setMnemonic(KeyEvent.VK_I);
		novo.addActionListener(this);
        label.setLabelFor(novo);
        
        modificar.setMnemonic(KeyEvent.VK_I);
        modificar.addActionListener(this);
        label.setLabelFor(modificar);
        
        deletar.setMnemonic(KeyEvent.VK_I);
        deletar.addActionListener(this);
        label.setLabelFor(deletar);
        
        listar.setMnemonic(KeyEvent.VK_I);
        listar.addActionListener(this);
        label.setLabelFor(listar);
        
        voltar.setMnemonic(KeyEvent.VK_I);
        voltar.addActionListener(this);
        label.setLabelFor(voltar);
		
		
    	esquerda.add(novo);
    	novo.setBounds(10, 10, 150, 20);
        esquerda.add(modificar);
        modificar.setBounds(10, 40, 150, 20);
        esquerda.add(deletar);
        deletar.setBounds(10, 70, 150, 20);
        esquerda.add(listar);
        listar.setBounds(10, 100, 150, 20);
        esquerda.add(voltar);
        voltar.setBounds(10, 130, 150, 20);    	
        
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
    	direita.setBounds(180, 10, 500, 350);
    	
    	painelFinal.add(image);
	}
	public void criaPainelTroca(){
		
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
			trocaPainel(janelaPrincipal,panelProfessores,"iCall");
		}else if(e.getSource() == modificar){
			trocaPainel(editarProf,panelProfessores,"iCall - Editar senha");
		}else if(e.getSource() == listar){
			trocaPainel(listProf,panelProfessores,"iCall - Lista de cadastros");
		}else if(e.getSource() == deletar){
			trocaPainel(deletProf,panelProfessores,"iCall - Excluir cadastro");
		}else if(e.getSource() == novo){
			trocaPainel(novoProfessor,panelProfessores,"iCall - Cadastrar professor");
		}
	}
}
