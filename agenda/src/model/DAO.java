package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {

	/** Modulo de conexão **/
	// Parametros de conexão

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "password";

	// Metodo de conexão

	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return null;
		}
	}

	// Teste de conexão

	public void texteConexao() {
		try {
			Connection con = conectar();
			System.out.println(con);
			con.close();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	/* CRUD CREATE */

	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos (nome,telefone,email) values(?,?,?)";
		try {

			// Abrindo conexão
			Connection con = conectar();
			// Preparar Query para inserção no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			// Definindo os parametros de acordo com os conteúdos do JavaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			// ExecutarQuery
			pst.executeUpdate();
			// Encerrando conexão com o banco de dados
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/* CRUD CREATE */
	public ArrayList<JavaBeans> listarContato() {
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		String read = "select * from contatos order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				// Variaveis de apoio dos dados do banco
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				// Alimentando o ArrayList
				contatos.add(new JavaBeans(idcon, nome, fone, email));
			}
			con.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	
	/* CRUD UPDATE */
	
	public void selecionarContato(JavaBeans contato) {
		String selecionar = "select * from contatos where idcon = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(selecionar);
			pst.setString(1, contato.getIdCon());
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				contato.setIdCon(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void editarContato(JavaBeans contato) {
		String editar = "update contatos set nome=?,telefone=?,email=? where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(editar);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getIdCon());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
