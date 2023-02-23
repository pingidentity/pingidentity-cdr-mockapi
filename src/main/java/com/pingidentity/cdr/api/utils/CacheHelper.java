package com.pingidentity.cdr.api.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pingidentity.cdr.api.model.IModel;

public class CacheHelper<T extends IModel> {
	private final static String cacheFolderNameTemplate = "cache" + File.separator + "%s";
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	private final String folderName;
	
	private final Class<T> classType;
	
	public CacheHelper(Class<T> classType)
	{
		this.folderName = String.format(cacheFolderNameTemplate, classType.getName());
		this.classType = classType;
	}

	public List<T> getCachedObjects(String userId) {
		
		try {
			
			List<T> accounts = new ArrayList<T>();
			
			File userFolder = new File(getUserFolderName(userId));
			for(File accountFile : userFolder.listFiles())
			{
				T currentAccount = objectMapper.readValue(accountFile, this.classType);
				accounts.add(currentAccount);
			}
			
			return accounts;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean isCached(String userId) {		
		File file = new File(getUserFolderName(userId));		
		return file.exists();
	}
	
	public void cacheObjects(String userId, List<T> returnList) {
		
		File file = new File(this.folderName + File.separator + userId);
		file.mkdirs();
		
		for(T currentObject: returnList)
		{
			try {						
				objectMapper.writeValue(new File(getUserFolderName(userId) + File.separator + currentObject.toString() + ".txt"), currentObject);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private String getUserFolderName(String userId)
	{
		return this.folderName + File.separator + userId;
	}

}
