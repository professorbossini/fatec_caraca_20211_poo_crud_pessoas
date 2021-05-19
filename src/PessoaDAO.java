import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//Data Access Object
public class PessoaDAO {

	public static boolean inserir(Pessoa p) {
		Connection conn = null;
		try {
			// 1. Especificar o comando SQL
			String sql = "INSERT INTO tb_pessoa (nome, fone, email) VALUES (?, ?, ?)";
			// 2. Abrir uma conexão com o banco
			conn = ConnectionFactory.obterConexao();
			// 3. Pré compilar o comando
			PreparedStatement ps = conn.prepareStatement(sql);
			// 4. Substituir os placeholders ou preencher os dados faltantes
			ps.setString(1, p.getNome());
			ps.setString(2, p.getFone());
			ps.setString(3, p.getEmail());
			// 5. Executar
			ps.execute();
			ps.close();
			conn.close();
			return true;
		} catch (Exception e) {
			try {
				conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}
	}

	public static boolean atualizar(Pessoa pessoa) {
		//1. Especificar o comando SQL
		String sql = "UPDATE tb_pessoa SET nome = ?, fone = ?, email = ? WHERE codigo = ?";
		//usando um try with resources
		//2. Abrir conexão
		//3. Pré compilar o comando
		try (Connection conexao = ConnectionFactory.obterConexao();
				PreparedStatement ps = conexao.prepareStatement(sql);) {
			//4. Substituir os placeholders ou preencher os dados faltantes
			ps.setString(1, pessoa.getNome());
			ps.setString(2, pessoa.getFone());
			ps.setString(3, pessoa.getEmail());
			ps.setInt(4, pessoa.getCodigo());
			//5. Executar
			ps.execute();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean remover (Pessoa pessoa) {
		//1. Especificar o comando sql
		String sql = "DELETE FROM tb_pessoa WHERE codigo = ?";
		//usando o try with resources
		//2. Abrir conexão
		//3. Pré compilar o comando
		try(Connection conexao = ConnectionFactory.obterConexao();
				PreparedStatement ps = conexao.prepareStatement(sql);){
			//4. Substituir os placeholders (os símbolos ?)
			ps.setInt(1, pessoa.getCodigo());
			//5. Executar
			ps.execute();
			return true;
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static String listar() {
		//1. Especificar o comando sql
		String sql = "SELECT * FROM tb_pessoa";
		//2. Obter conexão
		//3. Pré compilar o comando
		//4. Executar o comando
		try(Connection conexao = ConnectionFactory.obterConexao();
				PreparedStatement ps = conexao.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()){
			//5. Iterar sobre o resultado
			StringBuilder sb = new StringBuilder("");
			while (rs.next()) {
				int codigo = rs.getInt("codigo");
				String nome = rs.getString("nome");
				String fone = rs.getString("fone");
				String email = rs.getString("email");
				sb.append(String.format(
						"código: %d, nome: %s, fone: %s, email: %s\n",
						codigo, nome, fone, email
				));
			}
			return sb.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}











