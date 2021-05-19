import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionFactory {
  private static final String usuario = "rodrigo";
  private static final String senha = "1234";
  private static final String host = "localhost";
  private static final String porta = "3306";
  private static final String db = "fatec_caraca_20211_pessoas";

  

  public static Connection obterConexao (){
    try{
      Connection conexao = DriverManager.getConnection(
        String.format("jdbc:mysql://%s:%s/%s", host, porta, db),
        usuario,
        senha
      );
      return conexao;
      
    }
    catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  

}
