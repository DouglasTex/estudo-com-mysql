package servlets;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AtrativoDAO {
	
	public Atrativo atrativo;
	public BD bd;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private String men, sql;
	public static final byte INCLUSAO = 1;
	public static final byte ALTERACAO = 2;
	public static final byte EXCLUSAO = 3;
	
	public AtrativoDAO() {
		bd = new BD();
		atrativo = new Atrativo();
	}
	
	public boolean localizar() {
		sql = "select * from atrativos where id=?;";
		try {
			statement = bd.connection.prepareStatement(sql);
			statement.setString(1, atrativo.getCodigo());
			resultSet = statement.executeQuery();
			resultSet.next();
			atrativo.setCodigo(resultSet.getString(1));
			atrativo.setNome(resultSet.getString(2));
			atrativo.setCidade(resultSet.getString(3));
			atrativo.setEstado(resultSet.getString(4));
			atrativo.setDescricao(resultSet.getString(5));
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public String atualizar (int operacao) {
		men = "Operação realizada com sucesso.";
		try {
			if (operacao == INCLUSAO) {
				sql = "insert into atrativos values (?,?,?,?,?)";
				statement = bd.connection.prepareStatement(sql);
				statement.setString(1, atrativo.getCodigo());
				statement.setString(2, atrativo.getNome());
				statement.setString(3, atrativo.getCidade());
				statement.setString(4, atrativo.getEstado());
				statement.setString(5, atrativo.getDescricao());
			} else if (operacao == ALTERACAO) {
				sql = "update atrativos set nome = ?, cidade = ?, estado = ?, descricao = ?"
						+ " where id = ?";
				statement = bd.connection.prepareStatement(sql);
				statement.setString(5, atrativo.getCodigo());
				statement.setString(1, atrativo.getNome());
				statement.setString(2, atrativo.getCidade());
				statement.setString(3, atrativo.getEstado());
				statement.setString(4, atrativo.getDescricao());
			} else if (operacao == EXCLUSAO) {
				sql = "delete from atrativos where id=?";
				statement = bd.connection.prepareStatement(sql);
				statement.setString(1, atrativo.getCodigo());
			}
			if (statement.executeUpdate() == 0) {
				men = "Falha na operacao";
			}
		} catch (Exception e) {
			men = "Falha na operacao "+ e.toString();
		}
		
		return men;
	}

	public ArrayList<Atrativo> listar() {
		
		ArrayList<Atrativo> lista = new ArrayList<Atrativo>();
		
		try {
			sql = "SELECT * from atrativos";
			statement = bd.connection.prepareStatement(sql);
			resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				atrativo = new Atrativo();
				atrativo.setCodigo(resultSet.getString(1));
				atrativo.setNome(resultSet.getString(2));
				atrativo.setCidade(resultSet.getString(3));
				atrativo.setEstado(resultSet.getString(4));
				atrativo.setDescricao(resultSet.getString(5));
				
				lista.add(atrativo);
			}
			
			
		} catch (Exception e) {
			System.out.println("Falha na listagem.");
		}
		
		
		return lista;
	}
	
}
