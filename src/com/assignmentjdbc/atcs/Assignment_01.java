package com.assignmentjdbc.atcs;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Assignment_01 {

	public static void main(String[] args) {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/task", "root", "rehan12");

			if (conn != null) {
				System.out.println("Connected....");
			} else {
				System.out.println("Not connected...");
			}

			System.out.println('\n' + "********** BEFORE UPDATING *********" + '\n');
			String q = "select * from atcs";
			Statement stmt = conn.createStatement();
			ResultSet st = stmt.executeQuery(q);
			while (st.next()) {
				System.out.println("first-> " + st.getString("first") + " last-> " + st.getString("last") + " age-> "
						+ st.getInt("age") + " city-> " + st.getString("city") + "id-> " + st.getInt("id"));
			}

			// INSERT DATA BLOCK //
			String insert = "insert into atcs(first,last,age,city,id) values(?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(insert);

			System.out.println('\n' + "Are you insert the Data into table (yes/not)" + '\n');
			String insert1 = sc.readLine();
			if (insert1.equals("yes")) {
				System.out.println("how many data you are entered ?");
				int n = Integer.parseInt(sc.readLine());

				while (n != 0) {

					System.out.println("Enter first ");
					String first = sc.readLine();
					ps.setString(1, first);

					System.out.println("Enter last name");
					String last = sc.readLine();
					ps.setString(2, last);

					System.out.println("Enter age ");
					int age = Integer.parseInt(sc.readLine());
					ps.setInt(3, age);

					System.out.println("Enter city ");
					String city = sc.readLine();
					ps.setString(4, city);

					System.out.println("Enter id ");
					int id = Integer.parseInt(sc.readLine());
					ps.setInt(5, id);

					n--;
					ps.executeUpdate();
				}
				System.out.println('\n' + "Insert the data Successfully..." + '\n');
			} else {
				System.out.println('\n' + "No need to insert data" + '\n');
			}

			// *** End Insert block ***//

			// DELETE A BLOCK //
			String delete = "delete from atcs where id=?";
			PreparedStatement pd = conn.prepareStatement(delete);
			System.out.println("Are you Deleteing something (yes & no)" + '\n');
			String delete1 = sc.readLine();
			if (delete1.equals("yes")) {
				System.out.println("how many data are you Delete ?" + '\n');
				int d = Integer.parseInt(sc.readLine());
				while (d != 0) {

					System.out.println("Enter id Number " + '\n');
					int id1 = Integer.parseInt(sc.readLine());
					pd.setInt(1, id1);

					pd.executeUpdate();
					d--;
				}
				System.out.println("Delete Successfull..." + '\n');
			} else {
				System.out.println("Does not Delete.." + '\n');
			}
			// *** END DELETE BLOCK //

			// UPDATING DATA BLOCK //
			String update = "update atcs set age=? where id=?";
			PreparedStatement ud = conn.prepareStatement(update);

			System.out.println("Are you updating something (yes & no)" + '\n');
			String updating1 = sc.readLine();
			if (updating1.equals("yes")) {
				System.out.println("How many data are you update ?");
				int upd = Integer.parseInt(sc.readLine());

				while (upd != 0) {
					System.out.println("Enter correct age ");
					int uage = Integer.parseInt(sc.readLine());
					ud.setInt(1, uage);

					System.out.println("Enter id where to be update");
					int uid = Integer.parseInt(sc.readLine());
					ud.setInt(2, uid);

					ud.executeUpdate();
					upd--;
				}
				System.out.println("Update block successfull.." + '\n');
			} else {
				System.out.println("no updation" + '\n');
			}
			/// ****** END UPDATING BLOCK **** ///

			/// DISPLAY USING ID //
			String display = "select * from atcs where id=?";
			PreparedStatement dis = conn.prepareStatement(display);
			System.out.println("How many data you are display ?" + '\n');
			int d1 = Integer.parseInt(sc.readLine());
			while (d1 != 0) {
				System.out.println("Enter id number");
				int id1 = Integer.parseInt(sc.readLine());
				dis.setInt(1, id1);

				ResultSet dis1 = dis.executeQuery();
				d1--;
				while (dis1.next()) {
					System.out.println("first-> " + dis1.getString("first") + " last-> " + dis1.getString("last")
							+ " age-> " + dis1.getInt("age") + " city-> " + dis1.getString("city") + "id-> "
							+ dis1.getInt("id"));
				}
			}
			/// *** End Display section ***///

			// Search using name //
			String name = "select * from atcs where first=?";
			PreparedStatement nm = conn.prepareStatement(name);
			System.out.println('\n' + "How many data are you reterive from name ?" + '\n');
			int name1 = Integer.parseInt(sc.readLine());
			while (name1 != 0) {
				System.out.println("Enter the first name");
				String nam = sc.readLine();
				nm.setString(1, nam);

				ResultSet nme1 = nm.executeQuery();
				name1--;
				while (nme1.next()) {
					System.out.println("first-> " + nme1.getString("first") + " last-> " + nme1.getString("last")
							+ " age-> " + nme1.getInt("age") + " city-> " + nme1.getString("city") + "id-> "
							+ nme1.getInt("id"));
				}
			}
			/// END SEARCHING BLOCK ***//

			/// SORTING BLOCK ///
			System.out.println('\n' + "Sort all the record with first name " + '\n');
			String sort = "select * from atcs order by first asc";
			PreparedStatement st1 = conn.prepareStatement(name);
			Statement stmt1 = conn.createStatement();
			ResultSet sort1 = st1.executeQuery(sort);
			while (sort1.next()) {
				System.out.println("first-> " + sort1.getString("first") + " last-> " + sort1.getString("last")
						+ " age-> " + sort1.getInt("age") + " city-> " + sort1.getString("city") + "id-> "
						+ sort1.getInt("id"));

			}
			System.out.println('\n' + "Sorting the Result successfully");
			// *** END SORTING BLOCK ***/

			// SHOW DATA BLOCK //
			System.out.println('\n' + "********** AFTER UPDATING *********" + '\n');

			String q1 = "select * from atcs";
			Statement stmt2 = conn.createStatement();
			ResultSet st2 = stmt2.executeQuery(q1);
			while (st2.next()) {
				System.out.println("first-> " + st2.getString("first") + " last-> " + st2.getString("last") + " age-> "
						+ st2.getInt("age") + " city-> " + st2.getString("city") + "id-> " + st2.getInt("id"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
