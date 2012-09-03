package InterfaceGrafica;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;

import classes.LerArquivo;

public class GerarRelatorio extends JanelaPrincipal implements ActionListener{

	JComboBox comboAulas = new JComboBox();
	JButton presenca = new JButton("Presença");
	JButton matriculados = new JButton("Matriculados");

	String[] nomeAulas;
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == voltar){
			trocaPainel(janelaPrincipal,gerarRelatorio,"iCall");
		}else if(e.getSource() == cadastAula){
			trocaPainel(cadastraAula,gerarRelatorio,"iCall - Cadastra aula");
		}else if(e.getSource() == cadastAluno){
			trocaPainel(cadastraAluno,gerarRelatorio,"iCall - Cadastra aluno");
		}else if(e.getSource() == verificAulo){
			trocaPainel(marcapresenca,gerarRelatorio,"iCall - Marca presença");
		}else if(e.getSource() == gerRelatorio){
			
		}else if(e.getSource() == professores){
			trocaPainel(panelProfessores,gerarRelatorio,"iCall - Gerenciamento de contas");
		}else if(e.getSource() == cadastAlunoAula){
			trocaPainel(cadastraAlunos,gerarRelatorio,"iCall - Cadastra aluno em aula");
		}
	}
	public void criaPainelTroca(){
		try {
			String aulas = LerArquivo.lerArq(System.getProperty("user.home") + "/iCall/"+"eventos.icall");
			String[] arrayAulas = aulas.split("\n");
			nomeAulas = new String[arrayAulas.length];
			for(int i=0;i<arrayAulas.length;i++){
				nomeAulas[i] = arrayAulas[i].split("##")[0];
				comboAulas.addItem(arrayAulas[i].split("##")[0] +" - "+arrayAulas[i].split("##")[1]);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		painelFinalTroca.add(comboAulas);
		comboAulas.setBounds(5, 10, 450, 20);
		
		painelFinalTroca.add(presenca);
		presenca.setBounds(300, 60, 150, 20);
		
		painelFinalTroca.add(matriculados);
		matriculados.setBounds(300, 90, 150, 20);
	}
	public Component panel(){
		colocaAjuda();
		criaPainelTroca();
		criaPainel();
		
		return painelFinal;
	}
}
