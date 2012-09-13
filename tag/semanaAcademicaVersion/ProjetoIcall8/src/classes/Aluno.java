package classes;

public class Aluno {
	String nome, matricula;

	
	public Aluno(String nome, String matricula){
		this.nome = nome;
		this.matricula = matricula;
		
	}
	//metodos gets and sets
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public boolean equals(Aluno usuario){
		boolean retorno = false;
		
		if(this.nome.equals(usuario.getNome()) && this.matricula.equals(usuario.getMatricula())){
			retorno = true;
		}
		
		return retorno;
	}
	
	public String toString(){		
		return nome  + ", " + matricula;
	}
	
	

}
