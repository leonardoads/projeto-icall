package InterfaceGrafica;

import classes.*;

import java.awt.BorderLayout;
import java.awt.Color;
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

public class CadastAula extends GerCadastrosAula implements ActionListener{

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
		if(e.getSource() == modificar){
		//	trocaPainel(editarProf,gerCadasAula,"iCall - Editar cadastro");
		}else if(e.getSource() == listar){
			trocaPainel(listarAulas,cadastraAula,"iCall - Lista de cadastros");
		}else if(e.getSource() == deletar){
		//	trocaPainel(deletProf,gerCadasAula,"iCall - Excluir cadastro");
		}
		else if(e.getSource() == novo){
			trocaPainel(cadastraAula,cadastraAula,"iCall - Cadastra aula");
		}else if(e.getSource() == voltar){
			trocaPainel(gerCadasAula,cadastraAula,"iCall");
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

		dom.setBackground(new Color(255,255,255,0));  
		seg.setBackground(new Color(255,255,255,0));  
		ter.setBackground(new Color(255,255,255,0));  
		qua.setBackground(new Color(255,255,255,0));  
		qui.setBackground(new Color(255,255,255,0));  
		sex.setBackground(new Color(255,255,255,0));  
		sab.setBackground(new Color(255,255,255,0));  
		
		painelFinalTroca.add(labelAula);
		labelAula.setBounds(5, 10, 350, 20);
		
		painelFinalTroca.add(nomeAula);
		nomeAula.setBounds(5, 40, 350, 20);
		
		painelFinalTroca.add(labelprof);
		labelprof.setBounds(5, 70, 350, 20);
		
		painelFinalTroca.add(nomeProf);
		nomeProf.setBounds(5, 100, 350, 20);
		
		painelFinalTroca.add(labeldias);
		labeldias.setBounds(5, 130, 350, 20);
		
		painelFinalTroca.add(dom);
		dom.setBounds(5, 160, 60, 20);
		
		painelFinalTroca.add(seg);
		seg.setBounds(65, 160, 60, 20);
		
		painelFinalTroca.add(ter);
		ter.setBounds(125, 160, 60, 20);
		
		painelFinalTroca.add(qua);
		qua.setBounds(185, 160, 60, 20);
		
		painelFinalTroca.add(qui);
		qui.setBounds(245, 160, 60, 20);
		
		painelFinalTroca.add(sex);
		sex.setBounds(305, 160, 60, 20);
		
		painelFinalTroca.add(sab);
		sab.setBounds(365, 160, 60, 20);

		painelFinalTroca.add(cadastra);
		cadastra.setBounds(230, 200, 120, 20);
	}
	public Component panel(){
		colocaAjuda();
		criaPainelTroca();
		criaPainel();

		return painelFinal;
	}
}
