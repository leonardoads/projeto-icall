package interfaceGrafica;

import classes.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PainelCadastroEventos extends JPanel {
	JLabel jLabelNomeEvento = new JLabel("Nome do evento: ");
	JLabel jLabelTemaEvento = new JLabel("Tema do evento: ");
	JLabel jLabellabelNomeApresentador = new JLabel(
			"Nome do apresentador do evento: ");
	JLabel jLabelDataEvento = new JLabel("Data do evento (dd/mm/aaaa): ");

	JTextField jTextNomeEvento = new JTextField();
	JTextField jTextTemaEvento = new JTextField();
	JTextField jTextNomeApresentadorEvento = new JTextField();
	JTextField jTextDataEvento = new JTextField();

	public PainelCadastroEventos() {
		// atribui�oes

		setLayout(null);
		// adicionando Jlabels
		labelNomeEvento();
		labelTemaEvento();
		labelNomeApresentadorEvento();
		labelaDataEvento();
		// adicionando JTextField
		fieldNomeEvento();
		fieldTemaEvento();
		fieldNomeApresentador();
		fieldDataEvento();
		// bot�o
		botaoCadastraEvento();

	}

	// JLabels------------------------------------------------------------------------------------------------
	public void labelNomeEvento() {
		jLabelNomeEvento.setBounds(15, 50, 300, 20);
		this.add(jLabelNomeEvento);
	}

	public void labelTemaEvento() {
		jLabelTemaEvento.setBounds(15, 100, 300, 20);
		this.add(jLabelTemaEvento);
	}

	public void labelNomeApresentadorEvento() {
		jLabellabelNomeApresentador.setBounds(15, 150, 300, 20);
		this.add(jLabellabelNomeApresentador);
	}

	public void labelaDataEvento() {
		jLabelDataEvento.setBounds(15, 200, 300, 20);
		this.add(jLabelDataEvento);
	}

	// JTextField----------------------------------------------------------------------------------------------
	public void fieldNomeEvento() {
		jTextNomeEvento.setBounds(260, 50, 300, 20);
		this.add(jTextNomeEvento);
	}

	public void fieldTemaEvento() {
		jTextTemaEvento.setBounds(260, 100, 300, 20);
		this.add(jTextTemaEvento);
	}

	public void fieldNomeApresentador() {
		jTextNomeApresentadorEvento.setBounds(260, 150, 300, 20);
		this.add(jTextNomeApresentadorEvento);
	}

	public void fieldDataEvento() {
		jTextDataEvento.setBounds(260, 200, 300, 20);
		this.add(jTextDataEvento);
	}

	// bot�o---------------------------------------------------------------------------------------------------
	public void botaoCadastraEvento() {
		JButton buttonCadastraEvento = new JButton("Cadastrar Evento");
		add(buttonCadastraEvento);
		buttonCadastraEvento.setBounds(150, 250, 300, 32);
		buttonCadastraEvento.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				cadastraEventoMouseClicked(evt);
			}

		});
	}

	public void cadastraEventoMouseClicked(MouseEvent evt) {
		// verifica se a data eh valida
		boolean ehDataValida = ValidaData.isDataValida(jTextDataEvento
				.getText());
		if (jTextNomeEvento.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null,"Todos os campos precisam ser preenchidos!");
		}else {

			// Capturando dados da entrada
			String dadosEvento = jTextNomeEvento.getText().replaceAll(" ", "_")
					+ "|" + jTextTemaEvento.getText() + "|"
					+ jTextNomeApresentadorEvento.getText() + "|"
					+ jTextDataEvento.getText();

			// criando Arquivo do evento ANTIGO
			String folderpath = System.getProperty("user.home") + "/iCall";
			// System.out.println(folderpath);
			try {
				CriarDiretorio.criaDiretorio(folderpath);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			try {
				// MODIFICADO POR DIEGO
				if (!ehDataValida) {
					JOptionPane.showMessageDialog(null, "Data do evento inválida");
				}else if (!criaEventoPontoIcall.eventoCadastrado(folderpath,jTextNomeEvento.getText())) {
					if (jTextNomeEvento.getText().contains("/")) {
						JOptionPane.showMessageDialog(null,"Nome do evento com caractere inválido foi inserido.");
					} else {

						criaEventoPontoIcall.escreveEvento(folderpath,dadosEvento);
						// lançando mensagem na tela

						try {
							JOptionPane.showMessageDialog(null, " O evento - "
									+ jTextNomeEvento.getText()
									+ " - foi cadastrado com sucesso!");
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
					}
				} else {// nao eh possivel cadastrar dois eventos de mesmo nome
						// lançando mensagem na tela
					try {
						JOptionPane.showMessageDialog(null, " O evento - "
								+ jTextNomeEvento.getText()
								+ " - já foi cadastrado anteriormente");
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());

					}
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}
}
