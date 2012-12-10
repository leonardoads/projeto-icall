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

public class AlunoEditar extends Professores implements ActionListener{
	JComboBox nomes = new JComboBox();
	JLabel labelAluno = new JLabel("Selecione o Aluno");
	JLabel labelNome = new JLabel("Digite o nome");
	JLabel labelMatricula = new JLabel("Digite a matricula");
	JTextField nomeAluno = new JTextField();
	JTextField numMatricula = new JTextField();


	JButton conf = new JButton("Editar");
	final String systemSeparator = java.io.File.separator;
	final String caminho = System.getProperty("user.home") + systemSeparator + "iCall" + systemSeparator ;
	final String caminho2 = System.getProperty("user.home") + systemSeparator + ".icall" + systemSeparator;

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == conf){
			Arquivo.editAraluno(nomes.getSelectedItem().toString().split(" - ")[1] , nomeAluno.getText(), numMatricula.getText() , caminho+"enroll.icall");
			try {
				Runtime.getRuntime().exec("mv "+caminho2+nomes.getSelectedItem().toString().split(" - ")[0]+" "+caminho2+numMatricula.getText());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			nomeAluno.setText("");
			numMatricula.setText("");
			trocaPainel(editarAluno,editarAluno,"iCall - Editar cadastro de aluno");
		
	}else if(e.getSource() == voltar){
		trocaPainel(gerCadasAluno,editarAluno,"iCall");
	}else if(e.getSource() == modificar){
		trocaPainel(editarAluno,editarAluno,"iCall - Editar cadastro de aluno");
	}else if(e.getSource() == listar){
		trocaPainel(listarAlunos,editarAluno,"iCall - Lista de cadastros");
	}else if(e.getSource() == novo){
		trocaPainel(cadastraAluno,editarAluno,"iCall - Cadastrar professor");
	}
}
public void criaPainelTroca(){
	try {
		ArrayList<String> alunos = Arquivo.listaOrdemAlfabetica(caminho+"enroll.icall");
		for(int i=0;i<alunos.size();i++){
			nomes.addItem(alunos.get(i).replace("##"," - "));
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}

	conf.setMnemonic(KeyEvent.VK_I);
	conf.addActionListener(this);
	label.setLabelFor(conf);

	painelFinalTroca.add(labelAluno);
	labelAluno.setBounds(5, 10, 350, 20);

	painelFinalTroca.add(nomes);
	nomes.setBounds(5, 40, 350, 20);

	painelFinalTroca.add(labelNome);
	labelNome.setBounds(5, 70, 150, 20);

	painelFinalTroca.add(nomeAluno);
	nomeAluno.setBounds(5, 100, 250, 20);

	painelFinalTroca.add(labelMatricula);
	labelMatricula.setBounds(5, 130, 150, 20);

	painelFinalTroca.add(numMatricula);
	numMatricula.setBounds(5, 160, 150, 20);

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
