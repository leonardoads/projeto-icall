package gerenciarDB;
import java.sql.*;
import java.util.Vector;
 
public class DBConnection {
  private Connection conn;
  private Statement stm;
  
  /**
   * O construtor cria uma nova conexão com o banco de dados sqlite contido
   * no arquivo passado como parâmetro. A conexão é possibilitada pelo driver
   * JDBC, fornecido por SQLiteJDBC.
   */
  public DBConnection(String file) throws SQLException, ClassNotFoundException {
    Class.forName("org.sqlite.JDBC");
    this.conn = DriverManager.getConnection("jdbc:sqlite:" + file);
    this.stm = this.conn.createStatement();
  }
  
  /**
   * Cria as tabelas
   */
  public void initDB() {			
	try {
		this.stm.executeUpdate("CREATE TABLE IF NOT EXISTS contas (" +
          "nome varchar(50) PRIMARY KEY NOT NULL," +
          "senha varchar(50),"+
          "tipo varchar(50));");
		this.stm.executeUpdate("CREATE TABLE IF NOT EXISTS alunos (" +
          "nome varchar(50) PRIMARY KEY NOT NULL," +
			"matricula varchar(50));");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Adiciona um novo aluno ao Banco de Dados
   * @param nome: Nome do aluno a cadastrar
   * @param matricula: Matrícula do aluno
   */
  public void addAluno(String nome, String matricula){
	  try {
	      this.stm = this.conn.createStatement();
	      this.stm.executeUpdate("INSERT INTO alunos VALUES (\"" +
	          nome + "\"," + matricula + ")");
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }  
  }
  /**
   * Adiciona uma nova conta a tabela contas
   * @param nome: Nome(id) do usuario da nova conta (O nome que será usado para acessar o iCall)
   * @param senha: Senha de acesso para o usuário
   * @param tipo: Tipo de conta, 'p' se for professor, 'c' se for coordenação
   * @throws Exception caso o tipo seja diferente do aceitado 
   */
  public void addConta(String nome, String senha, String tipo) throws Exception{
	  if(tipo!="c" && tipo!="p"){
		  throw new Exception("Tipo inválido, deve ser passado c (coodenação) ou p (professor)");
	  }
	  try {
	      this.stm = this.conn.createStatement();
	      this.stm.executeUpdate("INSERT INTO contas VALUES ('" +
	          nome + "','" + senha + "','" + tipo + "')");
	    } catch (SQLException e) {
	     	System.err.println(e.getMessage());
	    }  
  }
  
  /**
   * Remove a linha da tabela cuja coluna "matricula" seja igual a string passada como parâmetro
   * @param matricula
   */
  public void removeAluno(String matricula){
	  try {
	      this.stm = this.conn.createStatement();
	      this.stm.executeUpdate("DELETE FROM alunos WHERE " +
	          "matricula=\"" + matricula + "\"");
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } 
  }
  
  /**
   * Remove a linha da tabela cuja coluna nome seja igual a string passada como parâmetro
   * @param nome
   */
  public void removeConta(String nome){
	  try {
	      this.stm = this.conn.createStatement();
	      this.stm.executeUpdate("DELETE FROM contas WHERE " +
	          "nome=\"" + nome + "\"");
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } 
  }
  
  public Vector<String[]> getContas(){
	  Vector<String[]> contas = new Vector<String[]>();
	    ResultSet rs;
	    try {
	      rs = this.stm.executeQuery("SELECT * FROM contas " +
	      "ORDER BY nome DESC");
	      while (rs.next()) {
	        contas.add(new String[]{rs.getString("nome"), rs.getString("senha"), rs.getString("tipo")});
	      }
	      rs.close();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    
	    return contas;
  }
  /**
   * Retorna um objeto do tipo Vector contendo todos os alunos que estão no Banco de Dados, cada elemento do
   * vector será uma array de String contendo [matrícula, nome], ordenado pelas matriculas
   * @return
   */
   public Vector getAlunosMatricula(){
	   Vector alunos = new Vector();
	    ResultSet rs;
	    try {
	      rs = this.stm.executeQuery("SELECT * FROM alunos " +
	      "ORDER BY matricula DESC");
	      while (rs.next()) {
	        alunos.add(new String[]{rs.getString("matricula"), rs.getString("nome")});
	      }
	      rs.close();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    
	    return alunos;
   }
   /**
    * Retorna um objeto do tipo Vector contendo todos os alunos que estão no Banco de Dados, cada elemento do
    * vector será uma array de String contendo [nome, matrícula], ordenado pelos nomes
    * @return
    */
    public Vector getAlunosNomes(){
 	   Vector alunos = new Vector();
 	    ResultSet rs;
 	    try {
 	      rs = this.stm.executeQuery("SELECT * FROM alunos " +
 	      "ORDER BY nome DESC");
 	      while (rs.next()) {
 	        alunos.add(new String[]{rs.getString("nome"), rs.getString("matricula")});
 	      }
 	      rs.close();
 	    } catch (SQLException e) {
 	      e.printStackTrace();
 	    }
 	    
 	    return alunos;
    }
}