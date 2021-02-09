package com.hackernews.api.model;

public class ItemDetails {
private int id ;
private String type;
private String text;
private int[] kids;

public int[] getKids() {
	return kids;
}
public void setKids(int[] kids) {
	this.kids = kids;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
private String title;
private String url;
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public String getBy() {
	return by;
}
public void setBy(String by) {
	this.by = by;
}
public int getScore() {
	return Score;
}
public void setScore(int score) {
	Score = score;
}
public long getTime() {
	return time;
}
public void setTime(long time) {
	this.time = time;
}
private String by;
private int Score;
private long time;
}
