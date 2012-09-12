package InterfaceGrafica;

import java.awt.event.ActionEvent;

public class GerCadastrosAluno extends GerCadastros{
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == voltar){
			trocaPainel(janelaPrincipal,gerCadasAluno,"iCall");
		}else if(e.getSource() == modificar){
		//	trocaPainel(editarProf,gerCadasAluno,"iCall - Editar cadastro");
		}else if(e.getSource() == listar){
		//	trocaPainel(listProf,gerCadasAluno,"iCall - Lista de cadastros");
		}else if(e.getSource() == deletar){
		//	trocaPainel(deletProf,gerCadasAluno,"iCall - Excluir cadastro");
		}
		else if(e.getSource() == novo){
			trocaPainel(cadastraAluno,gerCadasAluno,"iCall - Cadastra aluno");
		}
	}
}
