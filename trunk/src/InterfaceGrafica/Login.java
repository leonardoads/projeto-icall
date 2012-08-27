package InterfaceGrafica;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends Main implements ActionListener{
	JPanel painelFinal = new JPanel(new GridLayout(0,1));
	
	JButton buttonLogin = new JButton("Login");
	
	JTextField nome = new JTextField();
	JPasswordField senha = new JPasswordField();
	
	JLabel labelNome = new JLabel("Nome ");
	JLabel labelSenha = new JLabel("Senha");
	
	JPanel NOME = new JPanel(new BorderLayout(1,0));
	JPanel SENHA = new JPanel(new BorderLayout(1,0));
	
	JPanel panelNome = new JPanel(new GridLayout(0,1));
	JPanel panelSenha = new JPanel(new GridLayout(0,1));
	
	JPanel panelLabNome = new JPanel(new GridLayout(0,1));
	JPanel panelLabSenha = new JPanel(new GridLayout(0,1));
	
	JPanel panelButton = new JPanel(new GridLayout(0,1));

	final JLabel label = new JLabel("iCall");
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buttonLogin){
			trocaPainel(janelaPrincipal, login, "iCall");
		}
	}
	
	public void criate(){
		buttonLogin.setMnemonic(KeyEvent.VK_I);
		buttonLogin.addActionListener(this);
        label.setLabelFor(buttonLogin);
        
        
		panelNome.add(nome);
		panelSenha.add(senha);
		panelLabNome.add(labelNome);
		panelLabSenha.add(labelSenha);
		panelButton.add(buttonLogin);
		
		NOME.setBorder(BorderFactory.createEmptyBorder(
              15, //top
              15, //lef8t
              7, //bottom
              250) //right
              );	
		
		SENHA.setBorder(BorderFactory.createEmptyBorder(
	          15, //top
	          15, //lef8t0
	          7, //bottom
	          250) //right
	          );
		
        panelButton.setBorder(BorderFactory.createEmptyBorder(
              15, //top
              85, //leftjanelaPrincipal
              7, //bottom
              270) //right
              );
		
		NOME.add(panelLabNome,BorderLayout.WEST);
		NOME.add(panelNome);
		
		SENHA.add(panelLabSenha,BorderLayout.WEST);
		SENHA.add(panelSenha);
		
		painelFinal.add(NOME);
		painelFinal.add(SENHA);
		painelFinal.add(panelButton);
		
		painelFinal.setBorder(BorderFactory.createEmptyBorder(
				220, //top
                250, //leftjanelaPrincipal
                10, //bottom
                0) //right
                );
	}
	public Component panel(){
		criate();
		return painelFinal;
	}

}
