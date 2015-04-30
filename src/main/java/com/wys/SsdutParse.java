package com.wys;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.jsoup.nodes.Document;

public class SsdutParse implements ParseStrategy{
	Document doc=null;
	String rootUrl="";
	private List<String> indexList=new LinkedList<String>();
	private List<String> contentList=new LinkedList<String>();
	private Set<String> visitedSet=new TreeSet<String>();
	public SsdutParse(Document doc,String url){
		this.doc=doc;
		this.rootUrl=url;
		visitedSet.add(rootUrl);
		indexList.add(rootUrl);
	}
	public void parse(){
		
	}
	private boolean isIndexPage(String url){
		return true;
	}
	private boolean isContentPage(String url){
		return true;
	}
}
