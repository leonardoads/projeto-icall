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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends Main implements ActionListener{
	JPanel painelFinal = new JPanel();
	
	JButton buttonLogin = new JButton("Login");
	
	JTextField nome = new JTextField();
	JPasswordField senha = new JPasswordField();
	
	JLabel labelNome = new JLabel("Nome ");
	JLabel labelSenha = new JLabel("Senha");
	JLabel img = new JLabel(new ImageIcon("res/backgroundLoginIcall.jpg"));
	final JLabel label = new JLabel("iCall");
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buttonLogin){
			if(nome.getText().toString().equals("icall") && 
					senha.getText().toString().equals("icall"))
			trocaPainel(janelaPrincipal, login, "iCall");
			else{
				senha.setText("");
				JOptionPane.showMessageDialog(null, "Usuario ou senha errado");
			}
		}
	}
	public void limparCampos(){
		nome.setText("");
        senha.setText("");
	}
	public void criate(){
		buttonLogin.setMnemonic(KeyEvent.VK_I);
		buttonLogin.addActionListener(this);
        label.setLabelFor(buttonLogin);
        
        painelFinal.setLayout(null);
        
        painelFinal.add(img);
        img.setBounds(0, 0, 720, 380);
        
        painelFinal.add(nome);
        nome.setBounds(320, 241, 150, 20);
        painelFinal.add(senha);
        senha.setBounds(320, 271, 150, 20);
        painelFinal.add(labelNome);
		labelNome.setBounds(260, 241, 50, 20);
        painelFinal.add(labelSenha);
		labelSenha.setBounds(260, 271, 50, 20);
        painelFinal.add(buttonLogin);
		buttonLogin.setBounds(335, 301, 90, 20);
	}
	public Component panel(){
		criate();
		return painelFinal;
	}

}
