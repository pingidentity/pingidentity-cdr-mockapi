package com.pingidentity.cdr.api.controller.response;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.pingidentity.cdr.api.model.IData;

public class ListResponse<T extends IData> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5799883084480864278L;
	private final T data;
	private final Map<String, String> links;
	private final Map<String, Object> meta;
	
	private ListResponse(T data, Map<String, String> links, Map<String, Object> meta)
	{
		this.data = data;
		this.links = links;
		this.meta = meta;
	}
	
	public static <T extends IData> ListResponse<T> getInstance (T data, String self, int totalRecords)
	{
		Map<String, String> links = new HashMap<String, String>();
		links.put("self", self);
		
		Map<String, Object> meta = new HashMap<String, Object>();
		meta.put("totalRecords", totalRecords);
		meta.put("totalPages", 1);
		
		return new ListResponse<T>(data, links, meta);
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
