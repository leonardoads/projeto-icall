package InterfaceGrafica;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MarcaPresenca2 extends JanelaPrincipal implements ActionListener{
	
	JPanel painel= new JPanel();
	JLabel labelMatricula = new JLabel("Digite a matricula");
	String disc = "";
	JTextField matricula = new JTextField();
	JButton computar = new JButton("Registra");
	JButton voltar = new JButton("Voltar");
	final String systemSeparator = java.io.File.separator;
	final String ICALLPATH = System.getProperty("user.home")+systemSeparator + ".iCall"+systemSeparator+"icall-libFP"+systemSeparator;
	String folderpath = System.getProperty("user.home") + "/iCall";
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == voltar){
			trocaPainel(marcapresenca,marcapresenca2,"iCall - Marca presença");
			this.matricula.setText("");
		}else if(e.getSource() == verificAulo){
			JOptionPane.showMessageDialog(null, "Você ja esta ai");
		}else if(e.getSource() == computar){
			String matricula = this.matricula.getText().toString();
			this.matricula.setText("");
			
			try {				
				Runtime.getRuntime().exec(
						"gnome-terminal -x sh "+ICALLPATH+"verify.sh"
								+" "
								+ matricula+" "
								+ this.disc.replaceAll(" ", "_"));
				Runtime.getRuntime().exec(
						"gnome-terminal -x sh "+ICALLPATH+"t.sh"
								+" "
								+ matricula);
			} catch (IOException err) {
				err.printStackTrace();
			}
			
		}
	}
	public void criaPainelTroca(){
		
		computar.setMnemonic(KeyEvent.VK_I);
		computar.addActionListener(this);
        
        voltar.setMnemonic(KeyEvent.VK_I);
        voltar.addActionListener(this);
        
        verificAulo.setMnemonic(KeyEvent.VK_I);
        verificAulo.addActionListener(this);
		
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
	public Component panel(String disc){
		this.disc = disc;
		colocaAjuda();
		criaPainelTroca();
		criaPainel();
		return painel;
	}
}
