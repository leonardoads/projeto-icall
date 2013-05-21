package InterfaceGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import classes.Evento;
import classes.LerArquivo;

public class MarcaPresenca2 extends JanelaPrincipal implements ActionListener{
	
	JPanel painel= new JPanel();
	JDialog dialogSenhaProf = new JDialog();
	JLabel labelSenha = new JLabel("Digite a senha do professor: ");
	JLabel labelSenhaErrada = new JLabel();
	JPanel painelSenhaProf = new JPanel(new FlowLayout());
	JPasswordField fieldSenhaProf = new JPasswordField(25);
	JButton buttonOK = new JButton("Ok");
	JButton buttonCancelar = new JButton("Cancelar");
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
			painelSenhaProf.add(labelSenha);
			painelSenhaProf.add(fieldSenhaProf);
			painelSenhaProf.add(labelSenhaErrada);
			painelSenhaProf.add(buttonOK);
			painelSenhaProf.add(buttonCancelar);
			dialogSenhaProf.add(painelSenhaProf);
			dialogSenhaProf.setSize(new Dimension(360, 200));
			dialogSenhaProf.setVisible(true);
			buttonOK.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(arg0.getActionCommand().equals("Ok")){
						System.out.println(Login.senhaUsada);
						if(Login.senhaUsada.equals(String.valueOf(fieldSenhaProf.getPassword()))){
							System.out.println("senha correta");
							dialogSenhaProf.setVisible(false);
							trocaPainel(marcapresenca,marcapresenca2,"iCall - Marca presença");
							matricula.setText("");
							fieldSenhaProf.setText("");
							labelSenhaErrada.setText("");
						}
					else{
						System.out.println("senha errada");
						labelSenhaErrada.setForeground(Color.RED);
						labelSenhaErrada.setText("*");
					}
					
					
					}
				}
			} );
			
			buttonCancelar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(arg0.getActionCommand().equals("Cancelar")){
						fieldSenhaProf.setText("");
						labelSenhaErrada.setText("");
						dialogSenhaProf.setVisible(false);
					}
					
				}
			});
			
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
				// TODO Auto-generated catch block
				err.printStackTrace();
			}
			
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
	public Component panel(String disc){
		this.disc = disc;
		colocaAjuda();
		criaPainelTroca();
		criaPainel();
		
		return painel;
	}
}
