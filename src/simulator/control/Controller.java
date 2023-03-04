package simulator.control;

import java.io.InputStream;
import java.io.OutputStream;

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
            throw new IllegalArgumentException();
        }

        String g[] = JSONObject.getNames(jsonInupt); //?
        JSONArray arrL = null;
        if(jsonInupt.has("laws")) {
        	arrL = jsonInupt.getJSONArray("laws");
        }
        JSONArray arrB = jsonInupt.getJSONArray("bodies");

        for(String group: g) {
             simulator.addGroup(group);
        }
        for(int i = 0; i < arrL.length(); i++) {

        }
        for(int i = 0; i < arrB.length(); i++) {

        }
    }

    public void run(int n, OutputStream out) { //n = num de pasos
        JSONObject jsonOut = new JSONObject();
        JSONArray arr = new JSONArray();

        //estado inicial s0, se muestra tambien si n < 1
        arr.put(simulator.getState());

        for(int i = 1; i < n; i++) { //estados del 1 hasta n
            simulator.advance();
            arr.put(simulator.getState());
        }

        jsonOut.put("states", arr);
    }
}