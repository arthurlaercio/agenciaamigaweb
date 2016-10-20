package dao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.doadorVO;
import java.sql.Connection;

public class doadorDAO {
	
	private static Connection con = ConexaoMySQL.conexaoMySQL();
	
	public void criarDoador(doadorVO doador){
		
		String sql = "INSERT INTO doadores (nome,cpf,senha,endereco,email,telefone,data_nascimento,sexo) values (?,?,?,?,?,?,?,?)";
		try{
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, doador.getNome());
			preparador.setString(2, doador.getCpf());
			preparador.setString(3, doador.getSenha());
			preparador.setString(4, doador.getEndereco());
			preparador.setString(5, doador.getEmail());
			preparador.setString(6, doador.getTelefone());
			preparador.setString(7, doador.getDataNascimento());
			preparador.setString(8, doador.getSexo());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Doador criado com sucesso!");
		}catch (SQLException e) {
			System.out.println("Erro no sql");
		}
	}
	
	public  void alterarDoador(doadorVO doador){
		
		String sql = "UPDATE doadores SET nome = ? , cpf = ? , senha = ? , endereco = ? , email = ? , telefone = ? , data_nascimento = ? , sexo = ? where id = ? ";
		try{
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, doador.getNome());
			preparador.setString(2, doador.getCpf());
			preparador.setString(3, doador.getSenha());
			preparador.setString(4, doador.getEndereco());
			preparador.setString(5, doador.getEmail());
			preparador.setString(6, doador.getTelefone());
			preparador.setString(7, doador.getDataNascimento());
			preparador.setString(8, doador.getSexo());
			preparador.setInt(9, doador.getId());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Doador alterado com sucesso!");
		}catch (SQLException e) {
			System.out.println("Erro no sql");
		}
	}
	
	public  void deletarDoador(doadorVO doador){
		
		String sql = "DELETE FROM doadores where id = ? ";
		try{
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, doador.getId());			
			preparador.execute();
			preparador.close();
			
			System.out.println("Doador deletado com sucesso!");
		}catch (SQLException e) {
			System.out.println("Erro no sql");
		}
	}
	
public  List<doadorVO> listarDoadores(doadorVO doador){
		
		String sql = "SELECT * FROM doadores";
		List<doadorVO> lista = new ArrayList<doadorVO>();
		try{
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet resultados = preparador.executeQuery();
			
			while(resultados.next()){
				
				doadorVO user = new doadorVO();
				user.setId(resultados.getInt("id"));
				user.setNome(resultados.getNome());
				user.setCpf(resultados.getCpf("cpf"));
				user.setSenha(resultados.getSenha("senha"));
				user.setEndereco(resultados.getEndereco("endereco"));
				user.setEmail(resultados.getEmail("email"));
				user.setTelefone(resultados.getTelefone("telefone"));
				user.setDataNascimento(resultados.getDataNascimento("data_nascimento"));
				user.setSexo(resultados.getSexo("sexo"));
				lista.add(user);
			}
				
		}catch (SQLException e) {
			System.out.println("Erro no sql");
		}
		return lista;
		
	}
}
