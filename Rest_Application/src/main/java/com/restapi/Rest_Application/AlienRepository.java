package com.restapi.Rest_Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlienRepository {

	//List<Aliens> alienlist;
	Connection con=null;
    PreparedStatement pst; 
	public AlienRepository()
	{
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/restDb";
		String username="root";
		String pass="root";

		try
		{
			Class.forName(driver);
			con=DriverManager.getConnection(url, username,pass);
		}
		catch (Exception e) {
			System.out.println(e);

		}
	}


	public List<Aliens> getAliensList()
	{
		List<Aliens> alist=new ArrayList<>();
		
		try
		{
		String sql="select * from alien";
		
		pst=con.prepareStatement(sql);
		
		ResultSet rs=pst.executeQuery();
		
		while(rs.next())
		{
			Aliens a=new Aliens();
			a.setId(rs.getInt(1));
			a.setName(rs.getString(2));
			a.setPoints(rs.getInt(3));
		
			alist.add(a);
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return alist;
	}

	public Aliens getAlien(int id)
	{
		Aliens a=new Aliens();

		try
		{
		String sql="select * from alien where id=?";
		
		
		pst=con.prepareStatement(sql);
		pst.setInt(1, id);
		ResultSet rs=pst.executeQuery();
		
		if(rs.next())
		{
			
			a.setId(rs.getInt(1));
			a.setName(rs.getString(2));
			a.setPoints(rs.getInt(3));
		
		}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return a;
		

	}

	public void create(Aliens a1)
	{
		try
		{
		String sql="insert into alien values(?,?,?)";
		
		
		pst=con.prepareStatement(sql);
		pst.setInt(1, a1.getId());
		pst.setString(2, a1.getName());
		pst.setInt(3, a1.getPoints());
		pst.executeUpdate();
		
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void update(Aliens a1)
	{
		try
		{
		String sql="update alien set name=?,points=? where Id=?";
		
		
		pst=con.prepareStatement(sql);
		
		pst.setString(1, a1.getName());
		pst.setInt(2, a1.getPoints());
		pst.setInt(3, a1.getId());
		pst.executeUpdate();
		
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}


	public void delete(int id) {
		
		try
		{
		String sql="delete from alien where Id=?";
		
		pst=con.prepareStatement(sql);
		
		pst.setInt(1,id);
		pst.executeUpdate();
		
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}

}
