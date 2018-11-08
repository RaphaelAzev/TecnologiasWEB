package mvc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import org.springframework.web.servlet.ModelAndView;

public class NotasDAO {
	private Connection connection = null;
	//ModelAndView mv = new ModelAndView();

	public NotasDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/crudenotes", "root", "hamburguer61");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void adicionaConteudo(Nota nota) {
		try {
			String sql = "INSERT INTO notas (conteudo, titulo) values (?, ?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, nota.getConteudo());
			stmt.setString(2, nota.getTitulo());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void adiciona(Nota nota) {
		try {
			String sql = "INSERT INTO notas" + "(conteudo, cor, datacriacao, titulo) values(?,?,?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, nota.getConteudo());
			stmt.setString(2, nota.getCor());
			stmt.setString(3, nota.getDatacriacao());
			stmt.setString(4, nota.getTitulo());
			//stmt.setLong(5, nota.getId_poke());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Nota> getLista() {
		List<Nota> notas = new ArrayList<Nota>();
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM notas");
			//PreparedStatement stmt2 = connection.prepareStatement("SELECT * FROM pokes");
			ResultSet rs = stmt.executeQuery();
			//ResultSet rs2 = stmt2.executeQuery();
			while (rs.next()) {
				Nota nota = new Nota();
				nota.setId(rs.getLong("id"));
				nota.setTitulo(rs.getString("titulo"));
				nota.setConteudo(rs.getString("conteudo"));
				nota.setCor(rs.getString("cor"));
				nota.setDatacriacao(rs.getString("datacriacao"));
				nota.setId_pokenota(rs.getLong("id_pokenota"));
//				Calendar data = Calendar.getInstance();
//				Date dataFinalizacao = rs.getDate("dataFinalizacao");
//				if (dataFinalizacao != null) {
//					data.setTime(dataFinalizacao);
//					nota.setDataFinalizacao(data);
//				}
				notas.add(nota);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return notas;
	}
	
	public List<Poke> getPokelista() {
		List<Poke> pokes = new ArrayList<Poke>();
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM pokes");
			//PreparedStatement stmt2 = connection.prepareStatement("SELECT * FROM pokes");
			ResultSet rs = stmt.executeQuery();
			//ResultSet rs2 = stmt2.executeQuery();
			while (rs.next()) {
				Poke poke = new Poke();
				poke.setId_poke(rs.getLong("id_poke"));
				poke.setMove1(rs.getString("move1"));
				poke.setMove2(rs.getString("move2"));
				poke.setMove3(rs.getString("move3"));
				poke.setMove4(rs.getString("move4"));
				poke.setEvolutions(rs.getString("evolutions"));
				poke.setPokename(rs.getString("pokename"));
				poke.setSprite(rs.getString("sprite"));
//				Calendar data = Calendar.getInstance();
//				Date dataFinalizacao = rs.getDate("dataFinalizacao");
//				if (dataFinalizacao != null) {
//					data.setTime(dataFinalizacao);
//					nota.setDataFinalizacao(data);
//				}
				pokes.add(poke);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return pokes;
	}

	public void remove(Nota nota) {
		try {
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM notas WHERE id=?");
			stmt.setLong(1, nota.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public Nota buscaPorId(Long id) {
		Nota nota = new Nota();
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM notas WHERE id=? ");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				nota.setId(rs.getLong("id"));
				nota.setConteudo(rs.getString("conteudo"));

				nota.setCor(rs.getString("cor"));
				nota.setDatacriacao(rs.getString("Datacriacao"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return nota;
	}
	
	public void mudapokesprite(Poke poke) {
		try {
			String sql = "UPDATE pokes SET move1=?, move2=?, move3=?, move4=? WHERE id=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, poke.getMove1());
			stmt.setString(2, poke.getMove2());
			stmt.setString(3, poke.getMove3());
			stmt.setString(4, poke.getMove4());
			
			stmt.setLong(5, poke.getId_poke());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void altera(Nota nota) {
		try {
			String sql = "UPDATE notas SET conteudo=?, cor=?, dataCriacao=?, titulo=?, id_pokenota=? WHERE id=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, nota.getConteudo());
			stmt.setString(2, nota.getCor());
			stmt.setString(3, nota.getDatacriacao());
			stmt.setString(4, nota.getTitulo());
			if (nota.getId_pokenota() == null) {
				stmt.setLong(5, 0);
			} else {
				stmt.setLong(5, nota.getId_pokenota());
			}
			stmt.setLong(6, nota.getId());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

//	public void finaliza(Long id) {
//		try {
//			String sql = "UPDATE Nota SET finalizado=?, dataFinalizacao=? " + "WHERE id=?";
//			PreparedStatement stmt = connection.prepareStatement(sql);
//			stmt.setBoolean(1, true);
//			stmt.setDate(2, new Date(Calendar.getInstance().getTimeInMillis()));
//			stmt.setLong(3, id);
//			stmt.executeUpdate();
//			stmt.close();
//		} catch (SQLException e) {
//			System.out.println(e);
//		}
//	}
	
	public void adicionaPoke(Poke poke) {
		try {
			String sql = "INSERT INTO pokes" + "(move1, move2, move3, move4, pokename, evolutions, sprite) values(?,?,?,?,?,?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, poke.getMove1());
			stmt.setString(2, poke.getMove2());
			stmt.setString(3, poke.getMove3());
			stmt.setString(4, poke.getMove4());
			stmt.setString(5, poke.getPokename());
			stmt.setString(6, poke.getEvolutions());
			stmt.setString(7, poke.getSprite());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}