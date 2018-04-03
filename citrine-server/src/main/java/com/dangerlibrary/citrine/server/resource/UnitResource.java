package com.dangerlibrary.citrine.server.resource;


import com.dangerlibrary.citrine.UnitLexer;
import com.dangerlibrary.citrine.UnitParser;
import com.dangerlibrary.citrine.lib.parse.SIUnitVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/units/si")
public class UnitResource {

    @GET
    public Response convert(@QueryParam("units") String units) {
        try {
            CodePointCharStream stream = CharStreams.fromString(units);
            UnitLexer lexer = new UnitLexer(stream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            UnitParser parser = new UnitParser(tokens);
            String payload = new SIUnitVisitor().visitInput(parser.input()).toJson();
            return Response
                    .ok(payload + '\n', MediaType.APPLICATION_JSON_TYPE)
                    .build();
        } catch (IllegalArgumentException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage() +'\n')
                    .build();
        } catch (Throwable e) {
            return Response
                    .serverError()
                    .entity(e.getMessage() + '\n')
                    .build();
        }
    }
}
