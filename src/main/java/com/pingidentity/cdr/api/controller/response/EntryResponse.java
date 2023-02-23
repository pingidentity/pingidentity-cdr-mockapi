package com.pingidentity.cdr.api.controller.response;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.pingidentity.cdr.api.model.IModel;

public class EntryResponse<T extends IModel> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5799883084480864278L;
	private T data;
	private Map<String, String> links;
	private Map<String, Object> meta;
	
	private EntryResponse()
	{
		
	}
	
	private EntryResponse(T data, Map<String, String> links, Map<String, Object> meta)
	{
		this.data = data;
		this.links = links;
		this.meta = meta;
	}
	
	public static <T extends IModel> EntryResponse<T> getInstance (T data, String self)
	{
		Map<String, String> links = new HashMap<String, String>();
		links.put("self", self);
		
		Map<String, Object> meta = new HashMap<String, Object>();
		
		return new EntryResponse<T>(data, links, meta);
	}
	
	public T getData() {
		return data;
	}

	public Map<String, String> getLinks() {
		return links;
	}

	public Map<String, Object> getMeta() {
		return meta;
	}
}
