package panter.gmbh.basics4project.GEOCODIERUNG;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import panter.gmbh.indep.myVectors.VEK;

public class GEO_Routing_JSON_Parser {

	public VEK<GEO_Road_Model> parse_graphhopper(StringBuffer input_stream) throws JSONException {

		VEK<GEO_Road_Model> vRoutingParsed = new VEK<>();

		JSONObject js = new JSONObject(input_stream.toString());

		JSONArray paths_obj = js.getJSONArray("paths");

		for(int i=0;i<paths_obj.length();i++) {

			GEO_Road_Model path_model = new GEO_Road_Model();

			JSONObject path = paths_obj.getJSONObject(i);

			path_model.set_distance(path.getString("distance"));
			path_model.set_time(path.getString("time"));
			path_model.set_bounding_box("bbox");
			//			path.getJSONArray("instructions");

			vRoutingParsed._a(path_model);
		}

		return vRoutingParsed;
	}

	/** 
	 * ONLY FOR TEST
	 * @param input_stream
	 * @return Vektor(distance, duration) as String 
	 * 
	 * @throws JSONException
	 */
	public VEK<GEO_Road_Model> parse_osrm_response(StringBuffer input_stream) throws JSONException {

		VEK<GEO_Road_Model> vRoutingParsed = new VEK<>();

		JSONObject js = new JSONObject(input_stream.toString());

		String code_obj = js.getString("code").toLowerCase();
		
		if(code_obj.equals("ok")) {

			JSONArray paths_obj = js.getJSONArray("routes");

			for(int i=0;i<paths_obj.length();i++) {
				GEO_Road_Model path_model = new GEO_Road_Model();
				
				JSONObject path = paths_obj.getJSONObject(i);
				
				path_model.set_distance(path.getString("distance"));
				path_model.set_time(path.getString("duration"));
				path_model.set_polyline(path.getString("geometry"));
				vRoutingParsed._a(path_model);
			}
		}

		return vRoutingParsed;
	}

}

