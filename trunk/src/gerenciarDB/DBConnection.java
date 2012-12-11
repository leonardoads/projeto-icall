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
	//comando para criar tabela contas
//		StringBuilder tbContas = new StringBuilder();
//		tbContas.append("CREATE TABLE IF NOT EXISTS [contas](");
//		tbContas.append("[_id] INTEGER PRIMARY KEY AUTOINCREMENT, ");
//		tbContas.append("nome varchar(100), ");
//		tbContas.append("senha varchar(8));");
//		tbContas.append("tipo varchar(1));");
		

		//comando para criar tabela alunos
//		StringBuilder tbAlunos = new StringBuilder();
//		tbAlunos.append("CREATE TABLE IF NOT EXISTS [alunos](");
//		tbAlunos.append("[_id] INTEGER PRIMARY KEY AUTOINCREMENT, ");
//		tbAlunos.append("nome varchar(100), ");
//		tbAlunos.append("matricula varchar(20));");
			
	try {
//		this.stm.executeUpdate(tbContas.toString());
//		this.stm.executeUpdate(tbAlunos.toString());
		String a = "v";
		a = "c";
		this.stm.executeUpdate("DROP TABLE IF EXISTS contas");
		this.stm.executeUpdate("CREATE TABLE contas (" +
          "nome varchar(50) PRIMARY KEY NOT NULL," +
          "senha varchar(10)"+
          "tipo varchar(1));");
		
		this.stm.executeUpdate("DROP TABLE IF EXISTS alunos");
		this.stm.executeUpdate("CREATE TABLE alunos (" +
          "nome varchar(50) PRIMARY KEY NOT NULL," +
			"matricula varchar(50));");
		
//      this.stm.executeUpdate("DROP TABLE IF EXISTS Recordes");
//      this.stm.executeUpdate("CREATE TABLE Recordes (" +
//          "jogo varchar(50) PRIMARY KEY NOT NULL," +
//      "score integer);");
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
	      this.stm.executeUpdate("INSERT INTO contas VALUES (\"" +
	          nome + "\"," + senha + "\"," + tipo + ")");
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }  
  }
//  /**
//   * Adiciona uma nova linha na tabela de recordes.
//   * @param score
//   */
//  public void addHiScore(HiScore score) {
//    try {
//      this.stm = this.conn.createStatement();
//      this.stm.executeUpdate("INSERT INTO Recordes VALUES (\"" +
//          score.getJogo() + "\"," + 
//          String.valueOf(score.getScore()) + ")");
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
//  }
  
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
//  /**
//   * Remove a linha da tabela cuja coluna "jogo" seja igual a string passada
//   * como parâmetro. 
//   * @param jogo
//   */
//  public void removeHiScore(String jogo) {
//    try {
//      this.stm = this.conn.createStatement();
//      this.stm.executeUpdate("DELETE FROM Recordes WHERE " +
//          "jogo=\"" + jogo + "\"");
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
//  }
  
//  /**
//   * Altera o valor de uma linha do banco de dados.
//   * @param hiScore
//   */
//  public void atualizaHiScore(HiScore hiScore) {
//    try {
//      this.stm = this.conn.createStatement();
//      this.stm.executeUpdate("UPDATE Recordes SET score=\"" +
//          String.valueOf(hiScore.getScore()) + "\"" +
//          "WHERE jogo=\"" + hiScore.getJogo() + "\"");
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
//  }
  
  public Vector getContas(){
	  Vector contas = new Vector();
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
//  /**
//   * Retorna um objeto Vector com todos os recordes do banco de dados
//   * organizados em ordem decrescente de pontuação.
//   * @return
//   */
//  public Vector getAll() {
//    Vector hiScores = new Vector();
//    ResultSet rs;
//    try {
//      rs = this.stm.executeQuery("SELECT * FROM Recordes " +
//      "ORDER BY score DESC");
//      while (rs.next()) {
//        hiScores.add(new HiScore(rs.getString("jogo"), rs.getInt("score")));
//      }
//      rs.close();
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
//    
//    return hiScores;
//  }
}
