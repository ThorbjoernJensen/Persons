package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpServer;
import dtos.PersonsDTO;
import facades.PersonFacade;
import org.glassfish.jersey.server.ResourceConfig;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;


@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final PersonFacade FACADE = PersonFacade.getInstance(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("count")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonCount() {

        long count = FACADE.getPersonCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":" + count + "}";  //Done manually so no need for a DTO

    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll(){
        PersonsDTO personsDTO = FACADE.getAllPersons();
        return GSON.toJson(personsDTO);

    }

    @GET
    @Path("populate")
    @Produces({MediaType.APPLICATION_JSON})
    public String populate(){
        FACADE.populator();
        return "\"msg\": \"DB populated\"";
    }



//        @POST
//        @Produces({MediaType.APPLICATION_JSON})
//        @Consumes({MediaType.APPLICATION_JSON})
//        public Response postExample(String input){
//            RenameMeDTO rmdto = GSON.fromJson(input, RenameMeDTO.class);
//            System.out.println(rmdto);
//            return Response.ok().entity(rmdto).build();
}

