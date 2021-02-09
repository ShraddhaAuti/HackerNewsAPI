package com.hackernews.api.service;

import org.springframework.web.bind.annotation.PathVariable;

public interface HackerNewsService {
	public Object getBestStories();
    public Object getTopPastStories();
    public Object getTopComments(@PathVariable("id") int id);
}
