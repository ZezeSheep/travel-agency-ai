package br.com.zezesheep;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/travel")
public class TravelAgentResource {

    @Inject
    PackageExpert expert;

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String ask(String userMessage, @HeaderParam("X-User-Name") String userName) {
        if(userName != null && !userName.isBlank()){
            SecurityContext.setCurrentUser(userName);
            return expert.chat(userName,userMessage);
        }
        else{
            return "Usuário precisa estar logado para usar o chat.";
        }
    }
}
