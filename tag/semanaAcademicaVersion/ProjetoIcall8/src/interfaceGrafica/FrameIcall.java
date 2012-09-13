package interfaceGrafica;




import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;



/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class FrameIcall extends JFrame {
	JTabbedPane paneEventos;	
	JTabbedPane paneAlunos;	
	final String systemSeparator = java.io.File.separator;
	final String ICALLPATH = System.getProperty("user.home")+systemSeparator + ".iCall"+systemSeparator+"icall-libFP"+systemSeparator;

	public static PainelCadastroEventos painelCadastroEventos;
	PainelCadastroAluno painelCadastroAluno;
	private JButton gerarRelatorio;
	PainelVerificacaoAluno painelVerificacaoAluno;
	
	
	public FrameIcall() throws Exception{
		this.setLayout(null);
		
		paneEventos = new JTabbedPane();
		paneAlunos = new JTabbedPane();		
		painelCadastroEventos = new PainelCadastroEventos();	
		painelCadastroAluno = new PainelCadastroAluno();	
		painelVerificacaoAluno=  new PainelVerificacaoAluno();
		
		//configurando frame
		this.setTitle("Icall 2.0");
		this.setSize(500, 500);
		this.setBounds(50, 50, 600, 430);		
		
		//configurando paineis
		paneEventos.setBounds(0,40,600,400);
		paneAlunos.setBounds(0,40,600,400);
		paneEventos.setVisible(false);
		paneAlunos.setVisible(false);		
		
		//adicionando e criando paineis		
		this.add(paneEventos);
		this.add(paneAlunos);
		criaPaineis();
		
		//adicionando botoes
		botãoGeraRelatorio();
		botaoAtivaCadastroEvento();
		botaoAtivaCadastroEVarificacaoAluno();
	}	
	
	public void criaPaineis() throws Exception{
		try {
			paneEventos.add(painelCadastroEventos, "Cadastro de Eventos");
			painelCadastroEventos.setVisible(false);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();		
		}	
		
		try {
			paneAlunos.add(painelCadastroAluno, "Cadastro de Alunos");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();		
		}
		try {
			paneAlunos.add(painelVerificacaoAluno, "Verificacao de Alunos");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();		
		}
	}
	public void botaoAtivaCadastroEvento(){
		JButton buttonAtiva = new JButton("Cadastrar Evento");
		add(buttonAtiva);
		buttonAtiva.setBounds(0, 0, 200, 20);
		buttonAtiva.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				paneEventos.setVisible(true);
				paneAlunos.setVisible(false);
			}
			
		});			
		
	}
	public void botaoAtivaCadastroEVarificacaoAluno(){
		JButton buttonAtiva = new JButton("Cadastrar / verificar Aluno");
		add(buttonAtiva);
		buttonAtiva.setBounds(200, 0, 204, 20);
		buttonAtiva.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				paneEventos.setVisible(false);
				paneAlunos.setVisible(true);
			}
			
		});			
			
		
	}
	public void botãoGeraRelatorio(){
		gerarRelatorio = new JButton();
		getContentPane().add(gerarRelatorio);
		gerarRelatorio.setText("Gerar Relatório");
		gerarRelatorio.setBounds(404, -1, 183, 22);
		gerarRelatorio.addMouseListener(new MouseAdapter() {	
			public void mouseClicked(MouseEvent evt) {
	
				try {
					Runtime.getRuntime().exec(
							"gnome-terminal -x sh "+ICALLPATH+"relatorio.sh"
					);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
		});
	
	}
}