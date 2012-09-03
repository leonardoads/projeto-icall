package InterfaceGrafica;

import gerenciarArq.Arquivo;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import classes.LerArquivo;

public class EditarProfessor extends Professores implements ActionListener{
	JComboBox nomes = new JComboBox();
	JPasswordField senha = new JPasswordField();
	JPasswordField novaSenha = new JPasswordField();
	JPasswordField novaSenhaConf = new JPasswordField();
	
	JLabel LNome = new JLabel("Selecione um nome");
	JLabel LSenha = new JLabel("Digite a senha antiga:");
	JLabel LNovaSenha = new JLabel("Digite a nova senha:");
	JLabel LConfSenha = new JLabel("Confirme a senha:");
	
	JButton conf = new JButton("Editar");
	final String systemSeparator = java.io.File.separator;
	final String caminho = System.getProperty("user.home") + systemSeparator + "iCall" + systemSeparator ;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == conf){
			if(novaSenha.getText().equals(novaSenhaConf.getText())){
				Arquivo.editar(nomes.getSelectedItem().toString()+"##"+novaSenha.getText()+"##2", caminho+"contas.icall");
				senha.setText("");
				novaSenha.setText("");
				novaSenhaConf.setText("");
				
			}else{
				JOptionPane.showMessageDialog(null, "ERRO: as senhas não coincidem");
			}
		}else if(e.getSource() == voltar){
			trocaPainel(panelProfessores,editarProf,"iCall");
		}else if(e.getSource() == modificar){

		}else if(e.getSource() == listar){

		}else if(e.getSource() == deletar){

		}else if(e.getSource() == novo){
			trocaPainel(novoProfessor,editarProf,"iCall - Cadastrar professor");
		}
	}
	public void criaPainelTroca(){
		try {
			ArrayList<String> professores = Arquivo.listaOrdemAlfabetica(System.getProperty("user.home") + "/iCall/"+"contas.icall");
			for(int i=0;i<professores.size();i++){
				if(professores.get(i).split("##")[2].equals("2"))
					nomes.addItem(professores.get(i).split("##")[0]);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		conf.setMnemonic(KeyEvent.VK_I);
		conf.addActionListener(this);
        label.setLabelFor(conf);
        
		painelFinalTroca.add(LNome);
		LNome.setBounds(5, 10, 350, 20);
		
		painelFinalTroca.add(nomes);
		nomes.setBounds(5, 40, 350, 20);
		
		painelFinalTroca.add(LSenha);
		LSenha.setBounds(5, 70, 150, 20);
		
		painelFinalTroca.add(senha);
		senha.setBounds(5, 100, 250, 20);
		
		painelFinalTroca.add(LNovaSenha);
		LNovaSenha.setBounds(5, 130, 150, 20);

		painelFinalTroca.add(novaSenha);
		novaSenha.setBounds(5, 160, 150, 20);
		
		painelFinalTroca.add(LConfSenha);
		LConfSenha.setBounds(205, 130, 150, 20);
		
		painelFinalTroca.add(novaSenhaConf);
		novaSenhaConf.setBounds(205, 160, 150, 20);
		
		painelFinalTroca.add(conf);
		conf.setBounds(5, 200, 150, 20);
	}
	public Component panel(){
		colocaAjuda();
		criaPainelTroca();
		criaPainel();
		
		return painelFinal;
	}
}
