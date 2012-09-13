package interfaceGrafica;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.RandomAccessFile;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classes.Evento;
import classes.criaEventoPontoIcall;

public class PainelCadastroAluno extends JPanel {
	final String systemSeparator = java.io.File.separator;
	final String ICALLPATH = System.getProperty("user.home") + systemSeparator
			+ ".iCall" + systemSeparator + "icall-libFP" + systemSeparator;

	// MODIFICADO POR DIEGO
	String folderpath = System.getProperty("user.home") + "/iCall";
	JLabel jLabelNomeEvento;
	JLabel jLabelNomeAluno;
	JLabel jLabelMatriculaAluno;

	JTextField jTextNomeEvento = new JTextField();
	JTextField jTextNomeAluno = new JTextField();
	JTextField jTextMatriculaAluno = new JTextField();

	Evento evento;
	String nomeEvento;
	
	public PainelCadastroAluno() throws Exception {
		jLabelNomeEvento = new JLabel("Nome do Evento");
		jLabelNomeAluno = new JLabel("Nome do Aluno:");
		jLabelMatriculaAluno = new JLabel("Matricula do aluno:");

		this.setLayout(null);
		// adicionando Jlabels
		labelNomeEvento();
		labelNomeAluno();
		labelMatriculaAluno();
		// adicionando JTextField
		fieldNomeEvento();
		fieldNomeAluno();
		fieldMatriculaAluno();
		// botao
		botaoCadastraAluno();

	}

	// Jlabels----------------------------------------------------------------------------------------
	public void labelNomeEvento() {
		this.add(jLabelNomeEvento);
		jLabelNomeEvento.setBounds(15, 50, 300, 20);
	}

	public void labelNomeAluno() {
		this.add(jLabelNomeAluno);
		jLabelNomeAluno.setBounds(15, 100, 300, 20);
	}

	public void labelMatriculaAluno() {
		this.add(jLabelMatriculaAluno);
		jLabelMatriculaAluno.setBounds(15, 150, 300, 20);
	}

	// JTextFields---------------------------------------------------------------------------------------

	public void fieldNomeEvento() {
		this.add(jTextNomeEvento);
		jTextNomeEvento.setBounds(250, 50, 300, 20);
	}

	public void fieldNomeAluno() {
		this.add(jTextNomeAluno);
		jTextNomeAluno.setBounds(250, 100, 300, 20);
	}

	public void fieldMatriculaAluno() {
		this.add(jTextMatriculaAluno);
		jTextMatriculaAluno.setBounds(250, 150, 300, 20);
	}

	// bot�o---------------------------------------------------------------------------------------------------
	public void botaoCadastraAluno() {
		JButton buttonCadastraAluno = new JButton("Cadastrar Aluno");
		add(buttonCadastraAluno);
		buttonCadastraAluno.setBounds(150, 250, 300, 32);
		buttonCadastraAluno.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				try {
					cadastraAlunoMouseClicked(evt);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	}

	public void cadastraAlunoMouseClicked(MouseEvent evt) throws Exception {
		String nomeEvento = jTextNomeEvento.getText().replaceAll(" ", "_");
		String nomeAluno = jTextNomeAluno.getText();
		String matriculaAluno = jTextMatriculaAluno.getText();

		if (jTextNomeAluno.getText().trim().isEmpty()
				|| jTextMatriculaAluno.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"Todos os campos precisam ser preenchidos!");

		} else if (jTextMatriculaAluno.getText().length() < 8) {
			JOptionPane.showMessageDialog(null,
					"Matricula deve conter no mínimo 8 dígitos.");
		}

		else {
			// Facade.cadastraAluno(new Aluno(nomeAluno, matriculaAluno));

			// Chamando o enroll
			// MODIFICADO POR DIEGO AQUI ########################
			if (!criaEventoPontoIcall.eventoCadastrado(folderpath,jTextNomeEvento.getText())) {
				try {
					JOptionPane.showMessageDialog(null, " O evento - "
							+ jTextNomeEvento.getText()
							+ " - ainda não foi cadastrado");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}

			} else if (criaEventoPontoIcall.alunoCadastrado(folderpath,jTextMatriculaAluno.getText())){
				// nao eh possivel cadastrar dois alunos de mesma matricula
					// lançando mensagem na tela
				try {
					JOptionPane.showMessageDialog(null, " O participante - "
							+ jTextMatriculaAluno.getText()
							+ " - já foi cadastrado anteriormente");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
			else{
				Runtime.getRuntime().exec("gnome-terminal -x sh "+ICALLPATH+"enroll.sh"
						+ " " + jTextNomeAluno.getText().replaceAll(" ", "_") + " "
						+ jTextMatriculaAluno.getText());
			}
		}

	}

}
