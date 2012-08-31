package InterfaceGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import classes.LerArquivo;

public class Login extends Main implements ActionListener{
	JPanel painelFinal = new JPanel();
	
	JButton buttonLogin = new JButton("Login");
	
	JTextField nome = new JTextField();
	JPasswordField senha = new JPasswordField();
	
	JRadioButton professor = new JRadioButton("Professor");
	JRadioButton coordenacao = new JRadioButton("Coordenação");
	
	JLabel labelNome = new JLabel("Nome ");
	JLabel labelSenha = new JLabel("Senha");
	JLabel img = new JLabel(new ImageIcon("res/backgroundLoginIcall.jpg"));
	ButtonGroup tipo = new ButtonGroup();
	final JLabel label = new JLabel("iCall");
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buttonLogin){
			try {
				String arq = LerArquivo.lerArq(System.getProperty("user.home") + "/iCall/"+"contas.icall");
				int i;
				boolean acc = false;
				int type = 1;
				if(professor.isSelected()){
					type =2;
				}
				for(i=0;i<arq.split("\n").length;i++){
					if(nome.getText().toString().equals(arq.split("\n")[i].split("##")[0]) && 
							senha.getText().toString().equals(arq.split("\n")[i].split("##")[1])){
						if(type==1)
							tipoUsuario = "COORDENAÇÂO";
						else
							tipoUsuario = "PROFESSOR";
						trocaPainel(janelaPrincipal, login, "iCall");
						limparCampos();
						acc = true;
						break;
					}
				}
				if(!acc){
					senha.setText("");
					JOptionPane.showMessageDialog(null, "Usuario ou senha errado");
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
        img.setBounds(0, 0, 720, 450);
       
        img.add(nome);
        nome.setBounds(320, 241, 150, 20);
        img.add(senha);
        senha.setBounds(320, 271, 150, 20);
        img.add(labelNome);
		labelNome.setBounds(260, 241, 50, 20);
		img.add(labelSenha);
		labelSenha.setBounds(260, 271, 50, 20);
		img.add(buttonLogin);
		buttonLogin.setBounds(365, 331, 90, 20);
		
		tipo.add(professor);
		tipo.add(coordenacao);
		
		professor.setBackground(new Color(255,255,255,0));  
		coordenacao.setBackground(new Color(255,255,255,0));  
		img.add(professor);
		professor.setBounds(255, 301, 100, 20);
		img.add(coordenacao);
		coordenacao.setBounds(355, 301, 120, 20);
		
		professor.setSelected(true);
	}
	public Component panel(){
		criate();
		return painelFinal;
	}

}
