package com.example;

import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HTTPClient implements CommandLineRunner {
	
	static final String body = "{\n" + 
			"  \"item_1\": \"abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz00abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz00abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz00\",\n" + 
			"  \"item_2\": \"abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz00abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz00abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz00\",\n" + 
			"  \"item_3\": \"abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz00abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz00abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz00\",\n" + 
			"  \"item_4\": \"abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz00abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz00abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz00\",\n" + 
			"  \"item_5\": \"abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz00abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz00abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz0123456789abcdefghigklmnopqrstuvwxyz00\"\n" + 
			"}";
	


	public static void main(String[] args) {
		SpringApplication.run(HTTPClient.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		String postEndpoint = "http://10.1.10.32/mgs.mgw.hml";
		
		
		
		
		for(int i = 0 ; i < 10 ; i ++) {
			
			CloseableHttpClient client = HttpClients.createDefault();
			
			HttpPost post = new HttpPost(postEndpoint);
			post.setHeader("accept", "*/*");
			post.setHeader("Content-Type", "application/json");
			post.setHeader("traceid", UUID.randomUUID().toString());
			
			StringEntity entity = new StringEntity(body);
			post.setEntity(entity);
			
			System.out.println("Executing request: " + post.getRequestLine());
			HttpResponse response = client.execute(post);
			System.out.println(response.getStatusLine().getStatusCode() );
			
			client.close();
		}
		
		
		
		
	}

	
}
