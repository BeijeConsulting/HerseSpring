package it.beije.herse.shop.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.herse.shop.entity.ShopUser;
import it.beije.herse.shop.repository.ShopUserRepository;

@Service
public class SearchService {
		
	@Autowired
	private ShopUserRepository userRepository;
	
	public List<ShopUser> parseParam(String pathVariable){
		Map<String, String> parameters = new HashMap<String, String>();
		
		String[] whereArray = pathVariable.split("&");
		for(String s : whereArray) {
			if(s.contains("=")) {
				String[] columnAndValue = s.split("=");
				if(columnAndValue.length==2) parameters.put(columnAndValue[0].toLowerCase(), columnAndValue[1]);
			}
		}
		
		return getQueries(parameters);
	}

	public List<ShopUser> getQueries(Map<String, String> parameters) {
		List<List<ShopUser>> queries = new ArrayList<>();
		
		for(String col : parameters.keySet()) {
			switch(col.toUpperCase()) {
			case "NAME":
				queries.add(userRepository.findByName(parameters.get(col)));
				break;
			case "SURNAME":
				queries.add(userRepository.findBySurname(parameters.get(col)));
				break;
			case "EMAIL":
				queries.add(userRepository.findByEmail(parameters.get(col)));
				break;
			case "PASSWORD":
				queries.add(userRepository.findByPassword(parameters.get(col)));
				break;
			}
		}
		
		return getResult(queries);
	}
	
	// TODO FiX
	public List<ShopUser> getResult(List<List<ShopUser>> queries) {
		List<ShopUser> result = new ArrayList<ShopUser>();
		if(queries!=null && queries.size()>0) {
			result = queries.get(0);
			for(ShopUser u : result) {
				for(List<ShopUser> q : queries) {
					if(!q.contains(u)) result.remove(u);
					break;
				}
			}		
		}
		return result;
	}
}
