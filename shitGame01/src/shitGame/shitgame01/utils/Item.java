package shitGame.shitgame01.utils;

import android.R;
import android.content.SharedPreferences;

public class Item {
	private int last_time;
	private String item_id;
	private String item_description;
	private int coinInt = 0;
	
	public Item(){
		last_time = 1;//need to be solved
		
	}
	public int getCoin(){
		return coinInt;
	}
	public String getItem_description(){
		return item_description;
	}
	public int getLast_time() {
		return last_time;
	}


	public void setLast_time(int last_time) {
		this.last_time = last_time;
	}


	public String getItem_id() {
		return item_id;
	}


	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}


	public void bonus(){
		
	}
	
	
}
