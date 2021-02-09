package com.hackernews.api.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import com.hackernews.api.model.ItemDetails;

@CacheConfig(cacheNames = { "hackernewscache" })
@Service("hackernewsservice")
public class HackerNewsServiceImpl {

	@Cacheable
	public ResponseEntity<Object> getBestStories() {
		System.out.println("inside beststories");

		final String uri = "https://hacker-news.firebaseio.com/v0/beststories.json?print=pretty";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		Object response = restTemplate.exchange(uri, HttpMethod.GET, entity, Object.class).getBody();
		ArrayList<Integer> cl = (ArrayList<Integer>) response;
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			al.add(cl.get(i));

		}
		JSONArray jsonarray = new JSONArray();

		ItemDetails responseEntity = null;
		for (int j = 0; j < 10; j++) {
			JSONObject jsonfinal = new JSONObject();
			final String urinext = "https://hacker-news.firebaseio.com/v0/item/" + al.get(j) + ".json?print=pretty";
			responseEntity = restTemplate.exchange(urinext, HttpMethod.GET, entity, ItemDetails.class).getBody();
			jsonfinal.put("id", responseEntity.getId());
			jsonfinal.put("title", responseEntity.getTitle());
			jsonfinal.put("url", responseEntity.getUrl());
			jsonfinal.put("score", responseEntity.getScore());
			jsonfinal.put("time", responseEntity.getTime());
			jsonfinal.put("by", responseEntity.getBy());
			jsonfinal.put("type", responseEntity.getType());
			jsonarray.put(jsonfinal);
		}
		
		return new ResponseEntity<>(jsonarray.toList(), headers, HttpStatus.OK);

	}

	@Cacheable
	public ResponseEntity<Object> getTopPastStories() {

		final String uri = "https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		Object response = restTemplate.exchange(uri, HttpMethod.GET, entity, Object.class).getBody();
		ArrayList<Integer> cl = (ArrayList<Integer>) response;

		ArrayList<Integer> al = new ArrayList<Integer>();
		Iterator itr = cl.iterator();
		while (itr.hasNext()) {
			Integer i = (Integer) itr.next();
             al.add(i);
		}

		JSONArray jsonarray = new JSONArray();

		ItemDetails responseEntity = null;
		int counter = 0;
		while (counter < al.size()) {
			JSONObject jsonfinal = new JSONObject();
			final String urinext = "https://hacker-news.firebaseio.com/v0/item/" + al.get(counter)
					+ ".json?print=pretty";
			responseEntity = restTemplate.exchange(urinext, HttpMethod.GET, entity, ItemDetails.class).getBody();
			if (responseEntity.getType().equals("story")) {
				jsonfinal.put("id", responseEntity.getId());
				jsonfinal.put("title", responseEntity.getTitle());
				jsonfinal.put("url", responseEntity.getUrl());
				jsonfinal.put("score", responseEntity.getScore());
				jsonfinal.put("time", responseEntity.getTime());
				jsonfinal.put("by", responseEntity.getBy());
				jsonarray.put(jsonfinal);
				counter++;
			} else {
				continue;
			}
		}
		
		return new ResponseEntity<>(jsonarray.toList(), headers, HttpStatus.OK);
	}

	@Cacheable
	public ResponseEntity<Object> getTopComments(int id) {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		JSONObject jsonfinal1 = new JSONObject();

		ItemDetails responseEntity = null;
		final String urinext1 = "https://hacker-news.firebaseio.com/v0/item/" + id + ".json?print=pretty";
		responseEntity = restTemplate.exchange(urinext1, HttpMethod.GET, entity, ItemDetails.class).getBody();

		JSONArray jsonarray = new JSONArray();
		int kids[] = responseEntity.getKids();
		int arraysize = 0;
		if (responseEntity.getKids().length > 10) {
			arraysize = 10;
		} else {
			arraysize = responseEntity.getKids().length;
		}
		for (int j = 0; j < arraysize; j++) {
			JSONObject jsonfinal = new JSONObject();
			final String urinext = "https://hacker-news.firebaseio.com/v0/item/" + kids[j] + ".json?print=pretty";
			responseEntity = restTemplate.exchange(urinext, HttpMethod.GET, entity, ItemDetails.class).getBody();
			jsonfinal.put("time", responseEntity.getTime());
			jsonfinal.put("by", responseEntity.getBy());
			jsonfinal.put("text", responseEntity.getText());
			jsonarray.put(jsonfinal);
		}
		return new ResponseEntity<>(jsonarray.toList(), headers, HttpStatus.OK);
	}

}
