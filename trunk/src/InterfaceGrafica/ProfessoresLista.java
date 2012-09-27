package InterfaceGrafica;

import gerenciarArq.Arquivo;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ProfessoresLista extends Professores implements ActionListener{
	JList nomes = new JList();
	JTable tabelasNomes = new JTable();
	final String systemSeparator = java.io.File.separator;
	final String caminho = System.getProperty("user.home") + systemSeparator + "iCall" + systemSeparator ;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == voltar){
			trocaPainel(panelProfessores,listProf,"iCall");
		}else if(e.getSource() == modificar){
			trocaPainel(editarProf,listProf,"iCall - Editar senha");
		}else if(e.getSource() == listar){

		}else if(e.getSource() == deletar){
			trocaPainel(deletProf,listProf,"iCall - Excluir cadastro");
		}else if(e.getSource() == novo){
			trocaPainel(novoProfessor,listProf,"iCall - Cadastrar professor");
		}
	}
	public void criaPainelTroca(){
		ArrayList<String> cadastrados = Arquivo.listaOrdemAlfabetica(System.getProperty("user.home") + "/iCall/"+"contas.icall");
		String valores = "";
		for (int i = 0; i < cadastrados.size(); i++) {
			valores += (i+1)+" - "+cadastrados.get(i).split("##")[0]+"\n";
		}
		final String valoresFinal = valores;
		nomes.setModel(new AbstractListModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] array = valoresFinal.split("\n");
			@Override
			public int getSize() {
				return array.length;
			}
			
			@Override
			public Object getElementAt(int arg0) {
				return array[arg0];
			}
		});
		JScrollPane scroll = new JScrollPane(nomes);
		
		painelFinalTroca.add(scroll);
		scroll.setBounds(5, 10, 350, 200);
		
	}
	public Component panel(){
		colocaAjuda();
		criaPainelTroca();
		criaPainel();
		
		return painelFinal;
	}
}
