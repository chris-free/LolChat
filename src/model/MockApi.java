package model;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.github.theholywaffle.lolchatapi.ChatMode;
import com.github.theholywaffle.lolchatapi.LolStatus;
import com.github.theholywaffle.lolchatapi.LolStatus.GameStatus;
import com.github.theholywaffle.lolchatapi.LolStatus.Queue;
import com.github.theholywaffle.lolchatapi.wrapper.Friend;


public class MockApi implements Api {

	private List <MockSummoner> summoners;

	public  MockApi() {
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
		
	//	when(f7.setChatListener(Mockito.any(ChatListener.class)));

		ArrayList <Friend> arraylist = new ArrayList<Friend>();

		arraylist.add(f1);
		arraylist.add(f2);
		arraylist.add(f3);
		arraylist.add(f4);
		arraylist.add(f5);
		arraylist.add(f6);
		arraylist.add(f7);

		summoners = arraylist.stream().map(i -> new MockSummoner(i)).collect(Collectors.toList());

		for (Summoner sum: summoners) {
			Random rEnum = new Random();
			ChatMode c = ChatMode.values()[rEnum.nextInt(ChatMode.values().length)];
			when(sum.getFriend().getChatMode()).thenReturn(c);
		}
		
		for (Summoner sum: summoners) {
			Friend f = sum.getFriend();
			LolStatus status = mock(LolStatus.class);
			when(f.getStatus()).thenReturn(status);
			Random rEnum = new Random();
			GameStatus q = GameStatus.values()[rEnum.nextInt(GameStatus.values().length)];
			when(status.getGameStatus()).thenReturn(q);
		}
		
		for(int i = 0; i< 40; i++) {
			for(Friend sum: arraylist) {
				summoners.add(new MockSummoner(sum));
			}
		}
		
		
		
		Thread t = new Thread(new Runnable(){
			
			public void run() {
					for (int i = 0; i < 100; i++) {
						try {
							Random r = new Random();
							int index = r.nextInt(summoners.size());
							Summoner sum = summoners.get(index);
							System.out.println("Trying: " + sum.getName());

							Random rEnum = new Random();
							ChatMode c = ChatMode.values()[rEnum.nextInt(ChatMode.values().length)];
							
							when(sum.getFriend().getChatMode()).thenReturn(c);
							
							sum.notifyPresenceObservers();
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}}});
					
				
		t.start();

	}

	@Override
	public boolean login(String userName, String password) {
		return true;
	}

	@Override
	public List<Summoner> getSummoners() {
		// TODO Auto-generated method stub
		return summoners.stream().map(i -> (Summoner) i).collect(Collectors.toList());
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
