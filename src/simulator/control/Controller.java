package simulator.control;

import java.io.InputStream;
import java.io.OutputStream;

import org.json.JSONObject;
import org.json.JSONTokener;


public class Controller {
	public void loadData(InputStream in) {
		JSONObject jsonInupt = new JSONObject(new JSONTokener(in));
		
		String g[] = JSONObject.getNames(jsonInupt) ;
		for(String group: g) {
			 
		}
	}
	public void run(int n, OutputStream out) {
		
	}
}
