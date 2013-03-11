package com.khatu.musicschool.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.khatu.musicschool.wsresource.DepartmentSearchCriteria;
import com.khatu.musicschool.wsresource.response.MusicSearchResponse;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

@Service
@DependsOn("cacheManager")
final public class SearchCache {
	@Autowired
	private CacheManager manager;
	private final Ehcache cache = manager.getEhcache("searchCache");
	
	
	public void put(final DepartmentSearchCriteria criteria, final MusicSearchResponse musicSearchResponse){
		cache.put(new Element(criteria,musicSearchResponse));
	}
	
	public MusicSearchResponse get(final DepartmentSearchCriteria criteria){
		Element value =  cache.get(criteria);
		return (MusicSearchResponse)value.getObjectValue();
	}

}
