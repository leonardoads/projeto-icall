package InterfaceGrafica;

import gerenciarArq.Arquivo;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class ProfessorExcluir extends Professores implements ActionListener{
	JComboBox nomes = new JComboBox();
	JLabel LNome = new JLabel("Selecione um nome");
	JButton conf = new JButton("Excluir");
	final String systemSeparator = java.io.File.separator;
	final String caminho = System.getProperty("user.home") + systemSeparator + "iCall" + systemSeparator ;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == conf){
			Arquivo.excluir(nomes.getSelectedItem().toString(), caminho+"contas.icall");
			trocaPainel(deletProf,deletProf,"iCall - Excluir cadastro");
		}else if(e.getSource() == voltar){
			trocaPainel(panelProfessores,deletProf,"iCall");
		}else if(e.getSource() == modificar){
			trocaPainel(editarProf,deletProf,"iCall - Editar senha");
		}else if(e.getSource() == listar){
			trocaPainel(listProf,deletProf,"iCall - Lista de cadastros");
		}else if(e.getSource() == deletar){
			trocaPainel(deletProf,deletProf,"iCall - Excluir cadastro");
		}else if(e.getSource() == novo){
			trocaPainel(novoProfessor,deletProf,"iCall - Cadastrar professor");
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
		
		painelFinalTroca.add(conf);
		conf.setBounds(5, 80, 150, 20);
	}
	public Component panel(){
		colocaAjuda();
		criaPainelTroca();
		criaPainel();
		
		return painelFinal;
	}
}
