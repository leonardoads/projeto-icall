package InterfaceGrafica;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class NovoProfessor extends Professores implements ActionListener{
	JTextField nome = new JTextField();
	JPasswordField senha = new JPasswordField();
	JPasswordField senhaConf = new JPasswordField();
	
	JLabel LNome = new JLabel("Nome:");
	JLabel LSenha = new JLabel("Senha:");
	JLabel LConfSenha = new JLabel("Confirme a senha:");
	
	JButton conf = new JButton("Cadastrar");
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == conf){
			if(senha.getText().equals(senhaConf.getText())){
				JOptionPane.showMessageDialog(null, "ERRO: ainda não implementado");
			}else{
				JOptionPane.showMessageDialog(null, "ERRO: as senhas não coincidem");
			}
		}else if(e.getSource() == voltar){
			trocaPainel(panelProfessores,novoProfessor,"iCall");
		}else if(e.getSource() == modificar){

		}else if(e.getSource() == listar){

		}else if(e.getSource() == deletar){

		}else if(e.getSource() == novo){
			trocaPainel(novoProfessor,novoProfessor,"iCall - Cadastrar professor");
		}
	}
	public void criaPainelTroca(){
		conf.setMnemonic(KeyEvent.VK_I);
		conf.addActionListener(this);
        label.setLabelFor(conf);
        
		painelFinalTroca.add(LNome);
		LNome.setBounds(5, 10, 350, 20);
		
		painelFinalTroca.add(nome);
		nome.setBounds(5, 40, 350, 20);
		
		painelFinalTroca.add(LSenha);
		LSenha.setBounds(5, 70, 150, 20);
		
		painelFinalTroca.add(senha);
		senha.setBounds(5, 100, 150, 20);
		
		painelFinalTroca.add(LConfSenha);
		LConfSenha.setBounds(205, 70, 150, 20);

		painelFinalTroca.add(senhaConf);
		senhaConf.setBounds(205, 100, 150, 20);
		
		painelFinalTroca.add(conf);
		conf.setBounds(5, 140, 150, 20);
		
	}
	public Component panel(){
		colocaAjuda();
		criaPainelTroca();
		criaPainel();
		
		return painelFinal;
	}
}
