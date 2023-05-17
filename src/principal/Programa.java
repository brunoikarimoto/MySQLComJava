package principal;

import java.sql.Statement;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Programa {
	public static void printaPessoas(Connection con) {
		try {
			Statement comando = con.createStatement();
			ResultSet rs = comando.executeQuery("SELECT * FROM Pessoas;");
			
			while(rs.next()) {
				int id = rs.getInt("idPessoa");
				String nome = rs.getString("nome");
				String cpf = rs.getString("cpf");
				float peso = rs.getFloat("peso");
				
				System.out.println("ID: " + id);
				System.out.println("Nome: " + nome);
				System.out.println("CPF: " + cpf);
				System.out.println("Peso: " + peso);
				System.out.println("----------------------------------------");
			}
			
			comando.close();
		}
		catch(Exception e) {
			System.out.println("Algo deu errado!");
			System.out.println("Exception: " + e);
		}
	}
	
	public static void adicionarPessoa(Connection con, Scanner sc) {
		try {
			String sql = "INSERT INTO Pessoas (nome, cpf, peso) VALUES (?, ?, ?);";
			PreparedStatement comando = con.prepareStatement(sql);
			
			System.out.println("Nome: ");
			String nome = sc.nextLine();
			System.out.println("CPF: ");
			String cpf = sc.nextLine();
			System.out.println("Peso: ");
			float peso = sc.nextFloat();
			sc.nextLine();
			
			comando.setString(1, nome);
			comando.setString(2, cpf);
			comando.setFloat(3, peso);
			
			comando.execute();
			
			comando.close();
		}
		catch(Exception e) {
			System.out.println("Algo deu errado!");
			System.out.println("Exception: " + e);
		}
	}

	public static void atualizarPessoa(Connection con, Scanner sc) {
		try {
			String sql = "UPDATE Pessoas SET nome = ?, cpf = ?, peso = ? WHERE idPessoa = ?;";
			PreparedStatement comando = con.prepareStatement(sql);
			
			System.out.println("Nome: ");
			String nome = sc.nextLine();
			System.out.println("CPF: ");
			String cpf = sc.nextLine();
			System.out.println("Peso: ");
			float peso = sc.nextFloat();
			System.out.println("ID: ");
			int id = sc.nextInt();
			sc.nextLine();
			
			comando.setString(1, nome);
			comando.setString(2, cpf);
			comando.setFloat(3, peso);
			comando.setInt(4, id);
			
			comando.execute();
			
			comando.close();
		}
		catch(Exception e){
			System.out.println("Algo deu errado!");
			System.out.println("Exception: " + e);
		}
	}
	
	public static void deletaPessoa(Connection con, Scanner sc) {
		try {
			String sql = "DELETE FROM Pessoas WHERE idPessoa = ?";
			PreparedStatement comando = con.prepareStatement(sql);
			
			System.out.println("ID: ");
			int id = sc.nextInt();
			sc.nextLine();
			
			comando.setInt(1, id);
			
			comando.execute();
			
			comando.close();
		}
		catch(Exception e) {
			System.out.println("Algo deu errado!");
			System.out.println("Exception: " + e);
		}
	}
	
	public static void main(String[] args) throws SQLException {
		String url = "jdbc:mysql://localhost:3306/exemplojdbc";
		String user = "root";
		String senha = "positivo";
		Scanner sc = new Scanner(System.in);
		
		Connection con = DriverManager.getConnection(url, user, senha);
		
		adicionarPessoa(con, sc);
		atualizarPessoa(con, sc);
		deletaPessoa(con, sc);
		printaPessoas(con);
		
		sc.close();
		con.close();
	}

}
