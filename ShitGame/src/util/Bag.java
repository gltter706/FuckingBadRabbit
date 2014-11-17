package util;

import android.R;
import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

public class Bag {
	private Bundle item_to_num;
	private int coin;
	private Item selected_1;
	private Item selected_2;
	private Item selected_role;
	private SharedPreferences sharedPreferences;
	
	public Bag(Context context){
		selected_1 = null;
		selected_2 = null;
		selected_role = null;
		sharedPreferences = context.getSharedPreferences("data",0);//¶ÁÈ¡coin,item_to_numÊý¾Ý
		context.getResources().getString(com.example.shitgame.R.string.speed_up);
	}
	public void useItem(Item used){
		int tmp = item_to_num.getInt(used.item_id);
		item_to_num.putInt(used.item_id, tmp-1);
	}
	public void buyItem(Item item){
		int tmp = item_to_num.getInt(used.item_id);
		item_to_num.putInt(used.item, tmp+1);
	}
	
}
