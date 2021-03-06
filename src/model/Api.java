package model;

import java.util.List;
import java.util.Map;

public interface Api {
	   public boolean login(String userName, String password);
	   public List <Summoner> getSummoners();
	   public void reset();
	   public Map<String, List<Summoner>> getSummonersByGroup();
}
