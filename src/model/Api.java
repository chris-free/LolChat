package model;

import java.util.List;

import javafx.collections.ObservableList;

import com.github.theholywaffle.lolchatapi.wrapper.Friend;

public interface Api {
	   public boolean login(String userName, String password);
	   public List <Summoner> getSummoners();
}
