package simulator.control;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Body;
import simulator.model.ForceLaws;
import simulator.model.PhysicsSimulator;


public class Controller {

    private PhysicsSimulator simulator;
    private Factory<Body> factoryB;
    private Factory<ForceLaws> factoryL;

    public Controller (PhysicsSimulator sim, Factory<ForceLaws> factoryL, Factory<Body> factoryB) {

        if(sim == null || factoryB == null || factoryL == null)
            throw new IllegalArgumentException();

        simulator = sim;
        this.factoryB = factoryB;
        this.factoryL = factoryL;
    }

    public void loadData(InputStream in) {
        JSONObject jsonInupt = new JSONObject(new JSONTokener(in));

        if(!jsonInupt.has("groups") || !jsonInupt.has("bodies")) {
            throw new IllegalArgumentException("No tiene group o bodies");
        }
        
        JSONArray g = jsonInupt.getJSONArray("groups");
        JSONArray arrL = null;
        if(jsonInupt.has("laws")) {
        	arrL = jsonInupt.getJSONArray("laws");
        }
        JSONArray arrB = jsonInupt.getJSONArray("bodies");

        for(int i = 0; i < g.length(); i++) {
             simulator.addGroup(g.getString(i));
        }
        if(arrL != null) {
       	 for(int i = 0; i < arrL.length(); i++) 
       		 simulator.setForceLaws(arrL.getJSONObject(i).getString("id"), factoryL.createInstance(arrL.getJSONObject(i).getJSONObject("laws")));
        }
        for(int i = 0; i < arrB.length(); i++) {
       	 simulator.addBody(factoryB.createInstance(arrB.getJSONObject(i)));;
        }
    }

    public void run(int n, OutputStream out) { //n = num de pasos

        PrintStream p = new PrintStream(out);

        p.println("{");
        p.println("\"states\": [");

        p.println(simulator.toString());
        for(int i = 0; i < n; i++) { 
            simulator.advance();
            p.println("," +simulator.toString());
        }
        
        p.println("]");
        p.println("}");

    }
}