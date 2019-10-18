package com.restapi.Rest_Application;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("alien")
public class AlienResource 
{
	
	AlienRepository repo=new AlienRepository();
	Aliens a=new Aliens();
	
	
	//for get all aliens list
	@GET
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<Aliens> getAliens()
	{
		return repo.getAliensList();
	}

	//for get particular alien
	@GET
	@Path("alien/{id}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Aliens getAlien(@PathParam("id") int id)
	{
	return repo.getAlien(id);
	
	}
	
	//insert data to db
	@POST
	@Path("alien1")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Aliens createAlien(Aliens a1)
	{
		repo.create(a1);
		return a1;
	}
	
	//delete particular data
	@PUT
	@Path("alien1")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Aliens UpdateAlien(Aliens a1)
	{
		System.out.println(a1);
		if(repo.getAlien(a1.getId()).getId()==0)
		{
			repo.create(a1);
		}
		else
		{
		repo.update(a1);
		}
		return a1;
	}
	
	@DELETE
	@Path("alien1/{id}")
	public Aliens killAlien(@PathParam("id") int id)
	{
		Aliens a=repo.getAlien(id);
		
		if(a.getId()!=0)
		{
			repo.delete(id);
		}
		return a;
	}
	
}
