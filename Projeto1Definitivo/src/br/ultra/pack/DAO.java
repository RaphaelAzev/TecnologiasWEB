package br.ultra.pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;
//import com.mysql.jdbc.Driver;

public class DAO {
	private Connection connection = null;
	public DAO() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				//DriverManager.registerDriver(new com.mysql.jdbc.Driver()); 
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost/crudeNotes", "root", "hamburguer61");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public List<Notas> getLista() {
		List<Notas> notas = new ArrayList<Notas>();
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("SELECT * FROM Notas");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Notas nota = new Notas();
				nota.setId(rs.getInt("id_nota"));
				nota.setConteudo(rs.getString("conteudo"));
				//Calendar data = Calendar.getInstance();
				//data.setTime(rs.getDate("nascimento"));
				nota.setDatacriacao(rs.getString("data"));
				//pessoa.setAltura(rs.getDouble("altura"));
				notas.add(nota);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notas;
	}
	
	
	public void adiciona(Notas nota) {
		String sql = "INSERT INTO Notas" + "(conteudo, data) values(?,?)";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1,nota.getConteudo());
			stmt.setString(2, nota.getDatacriacao()); 
			//stmt.setDouble(3,pessoa.getAltura());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void altera(Notas nota) {
		String sql = "UPDATE Notas SET " + "conteudo=?, data=? WHERE id_nota =?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, nota.getConteudo());
			stmt.setString(2, nota.getDatacriacao());
			//stmt.setDouble(3, pessoa.getAltura());
			stmt.setInt(3, nota.getId());
			stmt.execute();
			stmt.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void remove(Integer id) {
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("DELETE FROM Notas WHERE id_nota =?");
			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
