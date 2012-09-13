package interfaceGrafica;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classes.Evento;
import classes.criaEventoPontoIcall;

public class PainelVerificacaoAluno extends JPanel{
	final String systemSeparator = java.io.File.separator;
	final String ICALLPATH = System.getProperty("user.home")+systemSeparator + ".iCall"+systemSeparator+"icall-libFP"+systemSeparator;
	
	JLabel jLabelMatriculaAluno = new JLabel("Matricula do aluno:");
	JLabel jLabelNomeEvento= new JLabel("Nome do evento:");
	JTextField jTextMatriculaAluno = new JTextField();
	JTextField jTextNomeEvento = new JTextField();
	
	Evento evento;
	String nomeEvento;
	String folderpath = System.getProperty("user.home") + "/iCall";
	
	
	public PainelVerificacaoAluno(){
		this.setLayout(null);
		labelNomeEvento();
		labelMatriculaAluno();	
		fieldNomeEvento();
		//adicionando JTextField
		
		fieldMatriculaAluno();
		//bot�o
		botaoVerificacaoAluno();
	}
	
	//Jlabels----------------------------------------------------------------------------------------
	public void labelNomeEvento() {
		this.add(jLabelNomeEvento);
		jLabelNomeEvento.setBounds(15, 50, 300, 20);
	}	
	public void labelMatriculaAluno(){
			this.add(jLabelMatriculaAluno);
			jLabelMatriculaAluno.setBounds(15,100,300,20);
		}
	//JTextFields---------------------------------------------------------------------------------------
	public void fieldNomeEvento() {
		this.add(jTextNomeEvento);
		jTextNomeEvento.setBounds(250, 50, 300, 20);
	}
	
	public void fieldMatriculaAluno(){
			this.add(jTextMatriculaAluno);
			jTextMatriculaAluno.setBounds(250,100,300,20);
		}
		//bot�o---------------------------------------------------------------------------------------------------
		public void botaoVerificacaoAluno(){
			JButton buttonVerificacaoAluno = new JButton("Verificar Aluno");
			add(buttonVerificacaoAluno);
			buttonVerificacaoAluno.setBounds(150, 250, 300, 32);
			buttonVerificacaoAluno.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					verificaAlunoMouseClicked(evt);		 				
				}
				
			});
		}
		
		public void verificaAlunoMouseClicked(MouseEvent evt)  {
			if(jTextMatriculaAluno.getText().trim().isEmpty()){
				JOptionPane.showMessageDialog(null, "Todos os campos precisam ser preenchidos!");
			}
			else if(jTextMatriculaAluno.getText().length() < 8){
				JOptionPane.showMessageDialog(null, "Matricula deve conter no mínimo 8 dígitos.");
			}
			else if(criaEventoPontoIcall.alunoVerificado(folderpath, jTextMatriculaAluno.getText(), jTextNomeEvento.getText().replaceAll(" ", "_"))){
			//aluno so pode ter presença verificada uma vez para cada evento
				JOptionPane.showMessageDialog(null, "Sua presença já foi verificada neste evento.");
			}
			else if (!criaEventoPontoIcall.eventoCadastrado(folderpath,jTextNomeEvento.getText())) {
			//nao verifica se o evento ainda nao foi cadastrado
				try {
					JOptionPane.showMessageDialog(null, " O evento - "
							+ jTextNomeEvento.getText()
							+ " - ainda não foi cadastrado");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
			else if(!criaEventoPontoIcall.alunoCadastrado(folderpath,jTextMatriculaAluno.getText())){
			//nao verifica se o participante ainda nao foi cadastrado
				try {
					JOptionPane.showMessageDialog(null, " O participante - "
							+ jTextMatriculaAluno.getText()
							+ " - ainda não foi cadastrado");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
			else{
				try {				
					Runtime.getRuntime().exec(
							"gnome-terminal -x sh "+ICALLPATH+"verify.sh"
									+" "
									+ jTextMatriculaAluno.getText() +" "
									+ jTextNomeEvento.getText().replaceAll(" ", "_"));
					Runtime.getRuntime().exec(
							"gnome-terminal -x sh "+ICALLPATH+"t.sh"
									+" "
									+ jTextMatriculaAluno.getText());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//JOptionPane.showMessageDialog(null, " O Aluno, " +"com matricula " + jTextMatriculaAluno.getText() + ", foi verificado com sucesso!");
			}
			
			
		}

}	

