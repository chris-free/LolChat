package model;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.github.theholywaffle.lolchatapi.listeners.ChatListener;
import com.github.theholywaffle.lolchatapi.listeners.FriendListener;
import com.github.theholywaffle.lolchatapi.wrapper.Friend;


public class MockApi implements Api {

	@Override
	public void addChatListener(ChatListener chatListener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addFriendListener(FriendListener friendListener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ObservableList<Friend> getOnlineFriends() {
		// TODO Auto-generated method stub
		
		   Friend f1 = mock(Friend.class);
		   Friend f2 = mock(Friend.class);
		   Friend f3 = mock(Friend.class);
		   Friend f4 = mock(Friend.class);
		   Friend f5 = mock(Friend.class);
		   Friend f6 = mock(Friend.class);
		   Friend f7 = mock(Friend.class);
		   
		   when(f1.getName()).thenReturn("Spanishsunday");
		   when(f2.getName()).thenReturn("Signoc");
		   when(f3.getName()).thenReturn("Pudge227");
		   when(f4.getName()).thenReturn("Kevin");
		   when(f5.getName()).thenReturn("James");
		   when(f6.getName()).thenReturn("PuddingRaising");
		   when(f7.getName()).thenReturn("Captain");
		   
		   
		   when(f1.getUserId()).thenReturn("Spanishsunday");
		   when(f2.getUserId()).thenReturn("Signoc");
		   when(f3.getUserId()).thenReturn("Pudge227");
		   when(f4.getUserId()).thenReturn("Kevin");
		   when(f5.getUserId()).thenReturn("James");
		   when(f6.getUserId()).thenReturn("PuddingRaising");
		   when(f7.getUserId()).thenReturn("Captain");
		   
		   when(f1.isOnline()).thenReturn(true);
		   when(f2.isOnline()).thenReturn(true);
		   when(f3.isOnline()).thenReturn(true);
		   when(f4.isOnline()).thenReturn(true);
		   when(f5.isOnline()).thenReturn(true);
		   when(f6.isOnline()).thenReturn(true);
		   when(f7.isOnline()).thenReturn(true);
		   
		   
		   ArrayList <Friend> arraylist = new ArrayList<Friend>();
		   
		   arraylist.add(f1);
		   arraylist.add(f2);
		   arraylist.add(f3);
		   arraylist.add(f4);
		   arraylist.add(f5);
		   arraylist.add(f6);
		   arraylist.add(f7);

		   ObservableList<Friend> listfriends = FXCollections.observableArrayList(arraylist);
		   return listfriends;
	}

	@Override
	public boolean login(String userName, String password) {
		return true;
	}

}
