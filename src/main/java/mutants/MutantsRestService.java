package mutants;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

@Path("/mutantService")
public class MutantsRestService {

	@POST
	@Path("/mutant")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response mutant(String data) {

		JSONObject obj = new JSONObject(data);

		String[] dna = new String[6];

		JSONArray arr = obj.getJSONArray("dna");

		for (int i = 0; i < dna.length; i++) {
			dna[i] = arr.optString(i);
		}

		MutantService ws = new MutantService();
		boolean mutant = ws.isMutant(dna);

		if (mutant) {
			return Response.status(200).build();
		} else {
			return Response.status(403).build();
		}
	}

	@GET
	@Path("/stats")
	@Produces(MediaType.APPLICATION_JSON)
	public Response stats() {
		String object = "ADN: {“count_mutant_dna”:40, “count_human_dna”:100: “ratio”:0.4}";
		return Response.status(200).entity(object).build();
	}

}
