package com.zenhomes.service.impl;

import com.google.gson.Gson;
import com.zenhomes.data.Counter;
import com.zenhomes.service.CounterService;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DefaultCounterService implements CounterService {

	private final HttpClient httpClient = HttpClient.newBuilder()
			.version(HttpClient.Version.HTTP_2)
			.build();

	@Override
	public Counter getCounterById(String id)  {
		var httpRequest = httpGetRequest(String.format("https://europe-west2-zenhomes-development-project.cloudfunctions.net/counters/%s", id));
		HttpResponse<String> send = null;
		try {
			send = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			System.err.printf("Request error: [%s]", httpRequest.uri());//Amend to logger
			e.printStackTrace();
			return null;
		}
		if(send.statusCode()==200){

		return new Gson().fromJson(send.body(), Counter.class);
		}
		return null;
	}


	private HttpRequest httpGetRequest(String url){
		return HttpRequest.newBuilder()
				.GET()
				.uri(URI.create(url))
				.setHeader("User-Agent", "Java 11 HttpClient")
				.build();
	}
}
