package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {
	
	/**  Módulo de conexão *. */

	// Parâmetro de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://localhost/dbagenda?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "root";
	
	/** The password. */
	private String password = "Umbra*1997";

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	// Método de conexão
	private Connection conectar() {

		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	/**
	 *  CREATE *.
	 *
	 * @param contato the contato
	 */
	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos (nome,fone,email) values (?,?,?)";
		try {
			// abrir conexão primeiro
			Connection con = conectar();
			// preparar a querry (instrução) SQL para o Java executar
			PreparedStatement pst = con.prepareStatement(create);
			// substituir os parâmetros (?) pelo conteúdo das variáveis JavaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			// executar a querry (instrução)
			pst.executeUpdate();
			// Encerrar a conexão com o BD
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 *  READ *.
	 *
	 * @return the array list
	 */
	public ArrayList<JavaBeans> listarContatos() {
		// Criando um objeto para acessar a classe JavaBeans
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		String read = "select * from contatos order by nome";
		try {
			// abrir conexão primeiro
			Connection con = conectar();
			// preparar a querry (instrução) SQL para o Java executar
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			// o laço abaixo será executado enquanto houver contatos
			while (rs.next()) {
				// variáveis de apoio que recebem os dados do banco
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				// armazenar os dados do banco na ArrayList
				contatos.add(new JavaBeans(idcon, nome, fone, email));

			}
			con.close();
			return contatos;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	/**
	 *  UPDATE SELECIONAR *.
	 *
	 * @param contato the contato
	 */
	public void selecionarContato(JavaBeans contato) {
		String read2 = "select * from contatos where idcon = ?";
		try {
			// abrir conexão primeiro
			Connection con = conectar();
			// preparar a querry (instrução) SQL para o Java executar
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, contato.getIdcon());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				contato.setIdcon(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Alterar contato.
	 *
	 * @param contato the contato
	 */
	// **UPDATE** ATUALIZAR//
	public void alterarContato(JavaBeans contato) {
		String altera = "update contatos set nome=?, fone=?, email=? where idcon=?";
		try {
			// abrir conexão primeiro
			Connection con = conectar();
			// preparar a querry (instrução) SQL para o Java executar
			PreparedStatement pst = con.prepareStatement(altera);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getIdcon());
			// executar a querry (instrução)
			pst.executeUpdate();
			// Encerrar a conexão com o BD
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Deletar contato.
	 *
	 * @param contato the contato
	 */
	// **DELETE**
	public void deletarContato(JavaBeans contato) {
		String delete = "delete from contatos where idcon=?";
		try {
			// abrir conexão primeiro
			Connection con = conectar();
			// preparar a querry (instrução) SQL para o Java executar
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, contato.getIdcon());
			// executar a querry (instrução)
			pst.executeUpdate();
			// Encerrar a conexão com o BD
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
