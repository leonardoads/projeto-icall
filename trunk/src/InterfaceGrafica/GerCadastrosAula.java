package InterfaceGrafica;

import java.awt.event.ActionEvent;

public class GerCadastrosAula extends GerCadastros{
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == voltar){
			trocaPainel(janelaPrincipal,gerCadasAula,"iCall");
		}else if(e.getSource() == modificar){
		//	trocaPainel(editarProf,gerCadasAula,"iCall - Editar cadastro");
		}else if(e.getSource() == listar){
		//	trocaPainel(listProf,gerCadasAula,"iCall - Lista de cadastros");
		}else if(e.getSource() == deletar){
		//	trocaPainel(deletProf,gerCadasAula,"iCall - Excluir cadastro");
		}
		else if(e.getSource() == novo){
			trocaPainel(cadastraAula,gerCadasAula,"iCall - Cadastra aula");
		}
	}
}
