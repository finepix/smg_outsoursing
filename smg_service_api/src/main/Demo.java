package main;
import java.util.TreeMap;

import com.qcloud.QcloudApiModuleCenter;
import com.qcloud.Module.Wenzhi;
import com.qcloud.Utilities.Json.JSONObject;

public class Demo {
	public static void main(String[] args) {
		TreeMap<String, Object> config = new TreeMap<String, Object>();
		config.put("SecretId", "AKIDpzlrUyF2DNV369zx2PNn5syzRvdah26F");
		config.put("SecretKey", "AN8RdiA8v6SANWp4JmnJdStyQO40fOz3");
		config.put("RequestMethod", "GET");
		config.put("DefaultRegion", "gz");

		
		QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Wenzhi(),
				config);

		TreeMap<String, Object> params = new TreeMap<String, Object>();
//		params.put("offset", 0);
//		params.put("limit", 3);
		params.put("code", "2097152");
		params.put("text", "Œ“ «ƒ„∞÷∞÷");
		
		String result = null;
		try {
			result = module.call("LexicalAnalysis", params);
			JSONObject json_result = new JSONObject(result);
			System.out.println(json_result);
		} catch (Exception e) {
			System.out.println("error..." + e.getMessage());
		}

	}
}
