package br.com.zezesheep;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/travel")
public class TravelAgentResource {

    @Inject
    PackageExpertWithTemplate expert;

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String ask(String userMessage, @HeaderParam("X-User-Name") String userName) {
        if(userName != null && !userName.isBlank()){
            return expert.chat(userName,userMessage, userName);
        }
        else{
            return "Usuário precisa estar logado para usar o chat.";
        }
    }
}
