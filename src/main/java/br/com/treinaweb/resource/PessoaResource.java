package br.com.treinaweb.resource;

import java.util.List;

import br.com.treinaweb.modal.Pessoa;
import br.com.treinaweb.repository.PessoaRepository;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/pessoa")
public class PessoaResource {

    private PessoaRepository _repositorio = new PessoaRepository();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pessoa> get() {
        return _repositorio.GetAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Pessoa getById(@PathParam("id") int id) {
        return _repositorio.GetById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(Pessoa pessoa)
    {
        try{
            _repositorio.AddPessoa(pessoa);
            return Response.status(Response.Status.CREATED).entity(pessoa).build();
        }
        catch(Exception ex)
        {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        } 
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response put(@PathParam("id") int id, Pessoa pessoa)
    {
        Pessoa p = _repositorio.GetById(id);
        if(p == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        try{
            pessoa.setId(id);
            _repositorio.EditarPessoa(pessoa);
            return Response.status(Response.Status.OK).entity(pessoa).build();
        }
        catch(Exception ex)
        {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        } 
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id)
    {
        Pessoa p = _repositorio.GetById(id);
        if(p == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        try{
            _repositorio.DeletarPessoa(id);
            return Response.status(Response.Status.OK).build();
        }
        catch(Exception ex)
        {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        } 
    }
}
