package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.HttpMessageNotReadableException;

import hello.ToDoData;

public class H2Database {
	private Connection conn;

	/*public static void main(String args[]) {
		H2Database client = new H2Database(); // client.getAll();
		client.update(new Greeting(1, "Laddu Babu"));
		//client.add(new Greeting(3, "Teddy")); // client.delete(1);
	}*/

	public H2Database() {
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection(
					"jdbc:h2:tcp://localhost/~/Teddy", "sa", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean delete(int i) {
		PreparedStatement stmnt;
		boolean result = false;
		try {
			String delQuery = "Delete from GreetingInfo where id=" + i;
			stmnt = conn.prepareStatement(delQuery);
			System.out.println(delQuery);
			stmnt.execute();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

	public Boolean update(ToDoData greeting) {
		Boolean result = null;
		try {
			String updateQuery = "Update GreetingInfo set content = '"
					+ greeting.getContent() + "' where id =" + greeting.getId();
			PreparedStatement stmnt = conn.prepareStatement(updateQuery);
			System.out.println(updateQuery);
			result = stmnt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String add(ToDoData greeting) {
		try {
			String queryString = "Insert into GreetingInfo values ("
					+ greeting.getId() + ", '" + greeting.getContent() + "')";
			System.out.println(queryString);
			PreparedStatement stmnt = conn.prepareStatement(queryString);
			Boolean result = stmnt.execute();
			System.out.println(result);
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			return e.getLocalizedMessage();
		}
	}

	public List<ToDoData> getAll() {
		ToDoData g2 = null;
		List<ToDoData> queryResult = new ArrayList<ToDoData>();
		try {
			PreparedStatement stmnt = conn.prepareStatement("Select * from GreetingInfo");
			ResultSet result = stmnt.executeQuery();

			while (result.next()) {
				g2 = new ToDoData();
				g2.setId(result.getInt(1));
				g2.setContent(result.getString(2));

				queryResult.add(g2);
			}

			System.out.println(g2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return queryResult;
	}

	public List<ToDoData> getById(int idParam) {
		ToDoData g2 = new ToDoData();
		List<ToDoData> queryResult = new ArrayList<ToDoData>();
		try {
			PreparedStatement stmnt = conn.prepareStatement("Select * from GreetingInfo where id="+idParam);
			ResultSet result = stmnt.executeQuery();

			while (result.next()) {
				g2.setId(result.getInt(1));

				g2.setContent(result.getString(2));
				System.out.println(result.getInt(1));
				System.out.println(result.getString(2));

				queryResult.add(g2);
			}

			System.out.println(g2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return queryResult;
	}

}
