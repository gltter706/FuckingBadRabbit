package shitGame.shitgame01.utils;

import java.io.Serializable;
import java.util.*;
import java.util.Map;

import shitGame.shitgame01.R.drawable;
import shitGame.shitgame01.R.string;
import shitGame.shitgame01.activities.StartActivity;
import android.R;
import android.R.integer;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

public class Item implements Serializable{
	private int last_time;
	private int coinInt = 0;
	private String item_id;
	private String itemShortDesc;
	private String itemLongDesc;
	private int drawableId;
	private int drawableResource[] = {
			shitGame.shitgame01.R.drawable.ic_up,
			shitGame.shitgame01.R.drawable.ic_down,
			shitGame.shitgame01.R.drawable.ic_shield,
			shitGame.shitgame01.R.drawable.ic_angel,
			shitGame.shitgame01.R.drawable.ic_circle,
			shitGame.shitgame01.R.drawable.ic_star,
			shitGame.shitgame01.R.drawable.ic_hexagon,
	 };
	private int idResource[] = {
			shitGame.shitgame01.R.string.speed_up,
			shitGame.shitgame01.R.string.speed_down,
			shitGame.shitgame01.R.string.shield,
			shitGame.shitgame01.R.string.angel,
			shitGame.shitgame01.R.string.round,
			shitGame.shitgame01.R.string.star,
			shitGame.shitgame01.R.string.hexagon,
	};
	private int shortResource[] = {		
			shitGame.shitgame01.R.string.speed_up_short,
			shitGame.shitgame01.R.string.speed_down_short,
			shitGame.shitgame01.R.string.shield_short,
			shitGame.shitgame01.R.string.angel_short,
			shitGame.shitgame01.R.string.round_short,
			shitGame.shitgame01.R.string.star_short,
			shitGame.shitgame01.R.string.hexagon_short,
		  };
	private int longResource[] = {		
			shitGame.shitgame01.R.string.speed_up_long,
			shitGame.shitgame01.R.string.speed_down_long,
			shitGame.shitgame01.R.string.shield_long,
			shitGame.shitgame01.R.string.angel_long,
			shitGame.shitgame01.R.string.round_long,
			shitGame.shitgame01.R.string.star_long,
			shitGame.shitgame01.R.string.hexagon_long,
	  };
	private int coinRef[] = {
			10,
			10,
			50,
			100,
			1000,
			2000,
			3000,
	};
	public Item(Context context,String item_id){
		String idResourceTmp;
		int k = 0;
		
		this.item_id = item_id;
		for(int i = 0;i != idResource.length;i ++){
			idResourceTmp = context.getResources().getString(idResource[i]);
			if(this.item_id.equals(idResourceTmp)){
				k = i;
				break;
			}
		}
		drawableId = drawableResource[k];
		BitmapDrawable bitmapDrawable = (BitmapDrawable)context.getResources().getDrawable(drawableResource[k]);
		coinInt = coinRef[k];
		itemShortDesc = context.getResources().getString(shortResource[k]);
		itemLongDesc = context.getResources().getString(longResource[k]);
		last_time = 1;//need to be solved
		
	}
	public int getDrawableId(){
		return drawableId;
	}
	public String getItemShort(){
		return itemShortDesc;
	}
	public String getItemLong() {
		return itemLongDesc;
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


	public int getCoin(){
		return coinInt;
	}
	public void bonus(){
		//need to be solved
	}
	
	
}
