package InterfaceGrafica;

import classes.*;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import classes.CriarDiretorio;

public class CadastAula extends JanelaPrincipal implements ActionListener{

	JLabel labelAula = new JLabel("Digite o nome da disciplina");
	JLabel labelprof = new JLabel("Digite o nome do(a) professor(a)");
	JLabel labeldias = new JLabel("Selecione os dias");

	JTextField nomeAula = new JTextField();
	JTextField nomeProf = new JTextField();
	JRadioButton dom = new JRadioButton("Dom");
	JRadioButton seg = new JRadioButton("Seg");
	JRadioButton ter = new JRadioButton("Ter");
	JRadioButton qua = new JRadioButton("Qua");
	JRadioButton qui = new JRadioButton("Qui");
	JRadioButton sex = new JRadioButton("Sex");
	JRadioButton sab = new JRadioButton("Sab");

	JPanel labAula = new JPanel(new GridLayout(0,1));
	JPanel labProf = new JPanel(new GridLayout(0,1));
	JPanel labDias = new JPanel(new GridLayout(0,1));
	JPanel TexAula = new JPanel(new GridLayout(0,1));
	JPanel TexProf = new JPanel(new GridLayout(0,1));
	JPanel listDias = new JPanel();

	JPanel aula = new JPanel(new GridLayout(0,1));
	JPanel prof = new JPanel(new GridLayout(0,1));
	JPanel dias = new JPanel(new GridLayout(0,1));

	JButton cadastra = new JButton("Cadastra");
	JPanel botao = new JPanel(new GridLayout(0,1));

	JPanel painel = new JPanel(new GridLayout(0,1));
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == voltar){
			trocaPainel(janelaPrincipal,cadastraAula,"iCall");
		}else if(e.getSource() == cadastAula){

		}else if(e.getSource() == cadastAluno){
			trocaPainel(cadastraAluno,cadastraAula,"iCall - Cadastra aluno");
		}else if(e.getSource() == verificAulo){
			trocaPainel(marcapresenca,cadastraAula,"iCall - Marca presença");
		}else if(e.getSource() == gerRelatorio){

		}else if(e.getSource() == cadastAlunoAula){
			trocaPainel(cadastraAlunos,cadastraAula,"iCall - Cadastra aluno em aula");
		}else if(e.getSource() == cadastra){
			if (nomeAula.getText().trim().isEmpty()) {   // verifica se o nome da aula está preencido 
				JOptionPane.showMessageDialog(null,"Todos os campos precisam ser preenchidos!");
			}
			else if (nomeProf.getText().trim().isEmpty()) { // verifica se o nome do professor está preencido 
				JOptionPane.showMessageDialog(null,"Todos os campos precisam ser preenchidos!");
			}else{
				String dias = "";
				if(dom.isSelected()){
					dias+="dom";
					dom.setSelected(false);  //Se o dia estiver marcado ao cadastrar ele desmarca
				}
				if(seg.isSelected()){
					if(!dias.equals("")){
						dias+="//";
					}
					dias+="seg";
					seg.setSelected(false);//Se o dia estiver marcado ao cadastrar ele desmarca
				}
				if(ter.isSelected()){
					if(!dias.equals("")){
						dias+="//";
					}
					dias+="ter";
					ter.setSelected(false);//Se o dia estiver marcado ao cadastrar ele desmarca
				}
				if(qua.isSelected()){
					if(!dias.equals("")){
						dias+="//";
					}
					dias+="qua";
					qua.setSelected(false);//Se o dia estiver marcado ao cadastrar ele desmarca
				}
				if(qui.isSelected()){
					if(!dias.equals("")){
						dias+="//";
					}
					dias+="qui";

				}
				if(sex.isSelected()){
					if(!dias.equals("")){
						dias+="//";
					}
					dias+="sex";

				}
				if(sab.isSelected()){
					if(!dias.equals("")){
						dias+="//";
					}
					dias+="sab";

				}
				if(!dias.equals("")){
					String dados = nomeAula.getText().toString()+"##"+nomeProf.getText().toString()+"##"+dias;
	
					String folderpath = System.getProperty("user.home") + "/iCall";
					try {
						CriarDiretorio.criaDiretorio(folderpath);
					} catch (Exception err) {
						JOptionPane.showMessageDialog(null, err.getMessage());
					}
					try{
						if (!criaEventoPontoIcall.eventoCadastrado(folderpath,nomeAula.getText().toString())) {
							if (nomeAula.getText().toString().contains("//") || nomeAula.getText().toString().contains("##") ) {
								JOptionPane.showMessageDialog(null,"Nome da aula com caractere inválido foi inserido. " +
										"Não coloque '##' nem '//'");
							} else {
								criaEventoPontoIcall.escreveEvento(folderpath,dados);
								try {
									JOptionPane.showMessageDialog(null, " A aula - "
											+ nomeAula.getText()
											+ " - foi cadastrada com sucesso!");
								} catch (Exception err) {
									JOptionPane.showMessageDialog(null, err.getMessage());
								}
							}
						}
					}catch (Exception err) {
						JOptionPane.showMessageDialog(null, err.getMessage());
					}
					apagacampos();
				}else{
					JOptionPane.showMessageDialog(null, "Não cadastrado, você não escolheu os dias de aula");
				}


			}
		}
	}

	private void apagacampos() { //Apaga todos os campos editados
		nomeAula.setText(null);  
		nomeProf.setText(null);
		dom.setSelected(false);//Se o dia estiver marcado ao cadastrar ele desmarca
		seg.setSelected(false);
		ter.setSelected(false);
		qua.setSelected(false);//Se o dia estiver marcado ao cadastrar ele desmarca
		qui.setSelected(false);
		sex.setSelected(false);
		sab.setSelected(false);//Se o dia estiver marcado ao cadastrar ele desmarca


	}

	public void criaPainelTroca(){
		cadastra.setToolTipText("Click para continuar o cadastro");

		cadastra.setMnemonic(KeyEvent.VK_I);
		cadastra.addActionListener(this);
		label.setLabelFor(cadastra);

		labAula.add(labelAula);
		labProf.add(labelprof);
		labDias.add(labeldias);
		TexAula.add(nomeAula);
		TexProf.add(nomeProf);
		listDias.add(dom);
		listDias.add(seg);
		listDias.add(ter);
		listDias.add(qua);
		listDias.add(qui);
		listDias.add(sex);
		listDias.add(sab);

		aula.add(labelAula);
		aula.add(nomeAula);
		prof.add(labelprof);
		prof.add(nomeProf);
		dias.add(labDias);
		dias.add(listDias);

		aula.setBorder(BorderFactory.createEmptyBorder(
				0, //top
				20, //left
				0, //bottom
				20) //rightaula
				);
		prof.setBorder(BorderFactory.createEmptyBorder(
				0, //top
				20, //left
				0, //bottom
				20) //right
				);
		dias.setBorder(BorderFactory.createEmptyBorder(
				0, //top
				20, //left
				0, //bottom
				20) //right
				);

		botao.add(cadastra);
		botao.setBorder(BorderFactory.createEmptyBorder(
				25, //top
				280, //left
				15, //bottom
				10) //right
				);

		painel.add(aula);
		painel.add(prof);
		painel.add(dias);
		painel.add(botao);

		painelFinalTroca.add(painel, BorderLayout.NORTH);
	}
	public Component panel(){
		colocaAjuda();
		criaPainelTroca();
		criaPainel();

		return painelFinal;
	}
}
