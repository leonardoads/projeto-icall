package InterfaceGrafica;

import java.awt.event.ActionEvent;

public class GerCadastrosAluno extends GerCadastros{
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == voltar){
			trocaPainel(janelaPrincipal,gerCadasAluno,"iCall");
		}else if(e.getSource() == modificar){
			
		}else if(e.getSource() == listar){
			trocaPainel(listarAlunos,gerCadasAluno,"iCall - Lista de cadastros");
		}else if(e.getSource() == deletar){
			
		}
		else if(e.getSource() == novo){
			trocaPainel(cadastraAluno,gerCadasAluno,"iCall - Cadastra aluno");
		}
	}
}
