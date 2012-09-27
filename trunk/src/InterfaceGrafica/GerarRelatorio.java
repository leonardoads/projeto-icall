package InterfaceGrafica;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import classes.LerArquivo;

public class GerarRelatorio extends JanelaPrincipal implements ActionListener {
	
	final String systemSeparator = java.io.File.separator;
	final String ICALLPATH = System.getProperty("user.home") + systemSeparator
			+ "iCall" + systemSeparator;
	
	JComboBox comboAulas = new JComboBox();
	JButton presenca = new JButton("Presença");
	JButton matriculados = new JButton("Matriculados");
	JButton relacaoProf = new JButton("Professores");
	JButton relacaoAlu = new JButton("Alunos");
	JButton relacaoDisc = new JButton("Disciplinas");

	String[] nomeAulas;
	String copiar;
	PrintWriter pw;

	private FileWriter out = null;
	private FileReader arq = null;
	private BufferedReader buffer = null;
	private List<String> linhas = new ArrayList<String>();

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == voltar) {
			trocaPainel(janelaPrincipal, gerarRelatorio, "iCall");
		}else if (e.getSource() == verificAulo) {
			trocaPainel(marcapresenca, gerarRelatorio, "iCall - Marca presença");
		}else if(e.getSource() == btsobre){
			trocaPainel(panelSobre,gerarRelatorio,"Sobre");
		}else if (e.getSource() == gerRelatorio) {

		} else if (e.getSource() == professores) {
			trocaPainel(panelProfessores, gerarRelatorio,
					"iCall - Gerenciamento de contas");
		}else if (e.getSource() == presenca) {

			JFileChooser salvandoArquivo = new JFileChooser();
			int resultado = salvandoArquivo.showSaveDialog(salvandoArquivo);
			File salvarArquivoEscolhido = salvandoArquivo.getSelectedFile();

			String caminhoArquivoPresenca = ICALLPATH +comboAulas.getSelectedItem().toString().split(" - ")[0].replace(" ", "_")+"_verify.icall";

			if (resultado != JFileChooser.APPROVE_OPTION) {
				return;
			}

			try {
				pw = new PrintWriter(new FileWriter(salvarArquivoEscolhido));

			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				pw.println(lerArquivo(caminhoArquivoPresenca));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			pw.close();

		} else if (e.getSource() == matriculados) {

			JFileChooser salvandoArquivo = new JFileChooser();
			int resultado = salvandoArquivo.showSaveDialog(salvandoArquivo);
			File salvarArquivoEscolhido = salvandoArquivo.getSelectedFile();

			String caminhoArquivoMatriculados = ICALLPATH+"matriculados_"+comboAulas.getSelectedItem().toString().split(" - ")[0]+".icall";

			if (resultado != JFileChooser.APPROVE_OPTION) {
				return;
			}

			try {
				pw = new PrintWriter(new FileWriter(salvarArquivoEscolhido));

			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				pw.println(lerArquivo(caminhoArquivoMatriculados));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			pw.close();

		}
	}

	public List<String> lerArquivo(String arquivo) throws Exception {

		try {
			arq = new FileReader(arquivo);
			buffer = new BufferedReader(arq);
		} catch (Exception e) {
			throw new Exception("Arquivo nao encontrado!");
		}

		String linha;
		try {
			while (true) {
				linha = buffer.readLine();
				if (linha != null) {
					linhas.add(linha);
				} else {
					break;
				}
			}
			buffer.close();
			arq.close();
		} catch (IOException e) {
			throw new Exception("Erro ao ler o arquivo!");
		}
		return linhas;
	}

	public void criaPainelTroca() {
		try {
			String aulas = LerArquivo.lerArq(System.getProperty("user.home")
					+ "/iCall/" + "eventos.icall");
			String[] arrayAulas = aulas.split("\n");
			nomeAulas = new String[arrayAulas.length];
			for (int i = 0; i < arrayAulas.length; i++) {
				nomeAulas[i] = arrayAulas[i].split("##")[0];
				comboAulas.addItem(arrayAulas[i].split("##")[0] + " - "
						+ arrayAulas[i].split("##")[1]);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		presenca.addActionListener(this);

		matriculados.addActionListener(this);
		
		relacaoProf.addActionListener(this);
		relacaoAlu.addActionListener(this);
		relacaoDisc.addActionListener(this);

		painelFinalTroca.add(comboAulas);
		comboAulas.setBounds(5, 10, 450, 20);

		painelFinalTroca.add(presenca);
		presenca.setBounds(300, 60, 150, 20);

		painelFinalTroca.add(matriculados);
		matriculados.setBounds(300, 90, 150, 20);
		
		JLabel relacao = new JLabel("Relação de:");
		painelFinalTroca.add(relacao);
		relacao.setBounds(200, 140, 100, 20);
		
	}

	public Component panel() {
		colocaAjuda();
		criaPainelTroca();
		criaPainel();

		return painelFinal;
	}
}
