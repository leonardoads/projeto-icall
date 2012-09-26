package InterfaceGrafica;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;

public class GerCadastrosAula extends GerCadastros{
	JButton cadastAlunoAula = new JButton("Preenche aulas");
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == voltar){
			trocaPainel(janelaPrincipal,gerCadasAula,"iCall");
		}else if(e.getSource() == modificar){

		}else if(e.getSource() == listar){
			trocaPainel(listarAulas,gerCadasAula,"iCall - Lista de cadastros");
		}else if(e.getSource() == deletar){

		}
		else if(e.getSource() == novo){
			trocaPainel(cadastraAula,gerCadasAula,"iCall - Cadastra aula");
		}else if(e.getSource() == cadastAlunoAula){
			trocaPainel(cadastraAlunos,gerCadasAula,"iCall - Cadastra aluno em aula");
		}
	}
	public void criaPainel(){
		local.setText("Disciplinas");
		
		novo.setMnemonic(KeyEvent.VK_I);
		novo.addActionListener(this);
        
        modificar.setMnemonic(KeyEvent.VK_I);
        modificar.addActionListener(this);
        
        deletar.setMnemonic(KeyEvent.VK_I);
        deletar.addActionListener(this);
        
        listar.setMnemonic(KeyEvent.VK_I);
        listar.addActionListener(this);
        
        voltar.setMnemonic(KeyEvent.VK_I);
        voltar.addActionListener(this);
        
        cadastAlunoAula.setMnemonic(KeyEvent.VK_I);
        cadastAlunoAula.addActionListener(this);
		
    	esquerda.add(novo);
    	novo.setBounds(10, 10, 150, 20);
        esquerda.add(modificar);
        modificar.setBounds(10, 40, 150, 20);
        esquerda.add(deletar);
        deletar.setBounds(10, 70, 150, 20);
        esquerda.add(voltar);
        voltar.setBounds(10, 130, 150, 20);    	
        
        direita.setLayout(null);
        painelFinalTroca.setLayout(null);
        
        painelFinalTroca.setBackground(new Color(255,255,255,0)); 
        
        esquerda.setLayout(null);
        esquerda.setBackground(new Color(255,255,255,0));  
        
        image.add(local);
        local.setBounds(350, 400, 300, 20);
        
    	direita.add(painelFinalTroca);
    	painelFinalTroca.setBounds(0, 0, 500, 250);
    	
    	image.add(esquerda);
    	esquerda.setBounds(0, 0, 160, 400);	
    	
    	direita.setBackground(new Color(255,255,255,0));  
    	
    	image.add(direita);
    	direita.setBounds(180, 10, 500, 350);
    	
    	painelFinal.add(image);
	}
}
