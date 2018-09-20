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
				nota.setDatacriacao(rs.getTimestamp("data"));
				nota.setIdcor(rs.getInt("id_cor"));
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
	
	public List<Cor> getListaCor() {
		List<Cor> cores = new ArrayList<Cor>();
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("SELECT * FROM Cores");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Cor cor = new Cor();
				cor.setId(rs.getInt("id_cor"));
				cor.setCorHexa(rs.getString("CorHexa"));
				//Calendar data = Calendar.getInstance();
				//data.setTime(rs.getDate("nascimento"));
				//nota.setDatacriacao(rs.getTimestamp("data"));
				//pessoa.setAltura(rs.getDouble("altura"));
				cores.add(cor);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cores;
	}
	
	
	
	
	
	
	public void adiciona(Notas nota) {
		String sql = "INSERT INTO Notas" + "(conteudo, data, id_cor) values(?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1,nota.getConteudo());
			stmt.setTimestamp(2, nota.getDatacriacao()); 
			stmt.setInt(3, nota.getIdcor());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void adicionaCOR(Cor cor) {
		String sql = "INSERT INTO Cores" + "(CorHexa) values(?)";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1,cor.getCorHexa());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void altera(Notas nota) {
		String sql = "UPDATE Notas SET " + "conteudo=?, data=?, id_cor=? WHERE id_nota =?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, nota.getConteudo());
			stmt.setTimestamp(2, nota.getDatacriacao());
			stmt.setInt(3, nota.getIdcor());
			stmt.setInt(4, nota.getId());
			stmt.execute();
			stmt.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void alteraCOR(Cor cor) {
		String sql = "UPDATE Cores SET " + "CorHexa=? WHERE id_cor =?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, cor.getCorHexa());
			stmt.setInt(2, cor.getId());
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
	
	public void removeCOR(Integer id) {
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("DELETE FROM Cores WHERE id_cor =?");
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
