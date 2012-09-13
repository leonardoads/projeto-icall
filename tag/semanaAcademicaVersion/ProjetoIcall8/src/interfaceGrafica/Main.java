package interfaceGrafica;

import classes.CriarDiretorio;

public class Main {
	final static String systemSeparator = java.io.File.separator;
	final static String ICALLPATH = System.getProperty("user.home")+systemSeparator + ".iCall"+systemSeparator+"icall-libFP"+systemSeparator;
	public static void main(String args[]) throws Exception {
		
		
	String folderpath = System.getProperty("user.home")+"/iCall";
	System.out.println(folderpath);
	try{
		CriarDiretorio.criaDiretorio(folderpath);
	}catch (Exception e) {
		
	}	
	Runtime.getRuntime().exec(
			"gnome-terminal -x sh "+ICALLPATH+"login.sh"
	);
		
		FrameIcall minhaJanela = new FrameIcall();
		minhaJanela.setVisible(true);
		
	}

}
