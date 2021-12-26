package com.lightfeather.challenge.submission;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.validation.Valid;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
public class NotificationController implements WebMvcConfigurer {


	//This will occur whenever the form gets loaded
	@ModelAttribute
	public void preloadModel(Model model) {
		model.addAttribute("supervisors", getSupervisorList());
	}

	@GetMapping("/notification")
	public String greetingForm(Model model) {
		model.addAttribute("notification", new Notification());

		return "notification";
	}

	@PostMapping("/notification")
	public String notificationSubmit(@Valid Notification notification, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "notification";
		}

		System.out.println("First Name = " + notification.getFirstName());
		System.out.println("Last Name = " + notification.getLastName());
		System.out.println("Email = " + notification.getEmail());
		System.out.println("Phone Number = " + notification.getPhoneNumber());
		System.out.println("Supervisor = " + notification.getSupervisor());

		return "success";
	}

	/*
	 * This is for testing without needing to hit the api endpoint
	private List<Supervisor> getSupervisorList() {
		List<Supervisor> supervisors = new ArrayList<Supervisor>();
		supervisors.add(new Supervisor(1,"204-798-9969","u","d4900a18-a304-42c6-a8e5-a6c8c3f17bc0","Karson","Olson"));
		supervisors.add(new Supervisor(2,"792.910.1754","9","96188a56-1f92-4876-8df3-d8761ea5162f","Robbie","Heller"));
		supervisors.add(new Supervisor(3,"(255) 885-3433","u","3e662eb4-4434-499a-a64e-dc5ca9da87e5","Lavon","Deckow"));
		supervisors.add(new Supervisor(4,"727-659-7861","b","7d8a9ed8-685f-4408-92f1-e13a7c118d97","Elijah","Cremin"));

		return supervisors;	  
	}
	 */  

	/**
	 * Fetches the supervisor list from the api provided
	 * It then deserializes the json into Supervisor objects
	 * It then removes all numeric jurisdictions
	 * It then sorts the list alphabetically, first by jurisdiction, then by last name, then by first name
	 * Finally, it returns the list 
	 * @return
	 */
	private List<Supervisor> getSupervisorList() {
		HttpURLConnection con = null;
		try {
			URL url = new URL("https://o3m5qixdng.execute-api.us-east-1.amazonaws.com/api/managers");
			con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();

			Gson gson = new Gson();

			Type collectionType = new TypeToken<Collection<Supervisor>>() {}.getType();
			ArrayList<Supervisor> objCollection = gson.fromJson(content.toString(), collectionType);

			//To start, we want to exclude from the supervisor dropdown any with numeric jurisdictions

			//the easiest way to do this is to iterate through the collection backwards, and upon finding
			//an entry with a numeric jurisdiction, remove it.  We do this backwards so I don't have to worry
			//about removing an entry messing with the number of iterations			
			for(int i = objCollection.size() -1; i >=0; i--) {
				if(NumberUtils.isParsable(objCollection.get(i).getJurisdiction())) {
					objCollection.remove(i);
				}
			}			

			//With those taken care of, I next want to sort the list, first by jurisdiction, then by last name,
			//and last by first name
			Collections.sort(objCollection);

			return objCollection;

		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.disconnect();
		}
		return null;
	}

}