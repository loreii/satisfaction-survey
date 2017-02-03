package Satisfaction.survey;

import java.security.cert.CollectionCertStoreParameters;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

	Map<String,int[]> history =  Collections.synchronizedMap(new HashMap());

	@RequestMapping("/")
	public String index( Model model) {
		// model.addAttribute("name", name);

		return "index";
	}
	
	@RequestMapping("/vote")
	public String vote(@RequestParam(value = "value", required = false) Integer value, Model model) {
		// model.addAttribute("name", name);

		String key = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+Calendar.getInstance().get(Calendar.MONTH);
		int[] e = history.get(key);
		if(e==null){
			e=new int[5];
			history.put(key, e);
		}
		e[value]++;
			
		return "thanks";
	}


	@RequestMapping("/thanks")
	public String thanks() {
		// model.addAttribute("name", name);

		return "thanks";
	}
	

	@RequestMapping("/resume")
	public String resume(@RequestParam(value = "days", required = false) Integer days, Model model) {
		if(days==null)
			days=0;
		String key = (Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-days)+"/"+Calendar.getInstance().get(Calendar.MONTH);
		model.addAttribute("current", this.history.get(key));
		model.addAttribute("history", this.history);
		model.addAttribute("days", days-1);
		return "resume";
	}
	

}



