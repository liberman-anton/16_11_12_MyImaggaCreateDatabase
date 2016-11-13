package tel_ran.imagga.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import tel_ran.imagga.entities.Image;
import tel_ran.imagga.model.dao.ImagesMongoDB;

public class CreateDatabaseAppl {
	static RestTemplate restTemplate = new RestTemplate();
	static String urlPicture = "http://cbsnews2.cbsistatic.com/hub/i/r/2015/05/31/7cecb2a0-6bb0-4f9d-bd8d-559c706906a0/thumbnail/620x350/52baa5b68d0bcb54a6127be0842ccd16/putin474954598.jpg";
	static final String AUTH_TOKEN = "Basic YWNjXzYwMjM2N2E3YTZiNzVmZTo2ZDAyMjQyODRjZmVjNDZlZjA4MmY5OGE5NTA5ZTdjMw==";
	private static final String FILE_NAME = "images.txt";
	static String URL_SERVICE = "http://api.imagga.com/v1/tagging?url=";
	static BufferedReader input;
	
	public static void main(String[] args) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", AUTH_TOKEN);
		HttpEntity<String> request = new HttpEntity<>(headers);
		
		List<String> listUrls = new ArrayList<>();
		try {
			listUrls = getImagesUrl();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		List<Image> images = new ArrayList<>();
		Image image = null;
		for(String url : listUrls){
			ResponseEntity<Map<String,List<Image>>> response = restTemplate.exchange
				(URL_SERVICE + url, HttpMethod.GET, request, 
						new ParameterizedTypeReference<Map<String,List<Image>>>() {});
			Map<String,List<Image>> map = response.getBody();
			if(!map.isEmpty() && map.containsKey("results") && !map.get("results").isEmpty())
				image = map.get("results").get(0);
			System.out.println(image);
			images.add(image);
		}
		System.out.println(images.size());
		
		AbstractApplicationContext ctx = new FileSystemXmlApplicationContext("beans.xml");
		ImagesMongoDB imagesMongo = ctx.getBean(ImagesMongoDB.class);
		imagesMongo.addImages(images);
		ctx.close();
	}
	
	private static List<String> getImagesUrl() throws IOException {
		input = getInput(FILE_NAME);
		return getLines();
	}
	
	private static BufferedReader getInput(String str) {
		BufferedReader input = null;
		try {
			input = new BufferedReader(new FileReader(str));
		} catch (FileNotFoundException e) {
			System.out.println("input file not found");
		}
		return input;
	}

	private static  List<String> getLines() throws IOException{
		List<String> lines = new LinkedList<>();
		while(true){
				String line = input.readLine();
				if(line == null)
					break;
				lines.add(line);
			}
			input.close();
		return lines;
	}

}
