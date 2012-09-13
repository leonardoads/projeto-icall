package InterfaceGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class About extends Main implements ActionListener{

	JPanel painel = new JPanel(new BorderLayout(1, 0));
	ImageIcon i = new ImageIcon("res/abouticall.jpg");
	JLabel image = new JLabel(i);
	final JLabel label = new JLabel("iCall");
	JButton voltar = new JButton("Ok");


	public void criaPainel(){

		voltar.setMnemonic(KeyEvent.VK_I);
		voltar.addActionListener(this);
		label.setLabelFor(voltar);

		painel.add(voltar);
		voltar.setBounds(315, 350, 100, 20);    	

		painel.add(image);

	}
	public void criaPainelTroca(){

	}
	public Component panel(){
		criaPainelTroca();
		criaPainel();

		return painel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == voltar){
			trocaPainel(janelaPrincipal,panelSobre,"iCall");
		}
	}
}
