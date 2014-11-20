package shitGame.shitgame01.utils;

import java.io.Serializable;
import java.util.Map;

import android.R;
import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.content.Loader.ForceLoadContentObserver;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class Bag implements Serializable{
	
	private Bundle item_to_num;
	private int coinInt;
	private String coinString;
	
	private Item selected_1;
	private Item selected_2;
	private Item selected_role;
	private int  mission;
	private SharedPreferences sharedPreferences;
	
	private String speedupString;
	private String speeddownString;
	private String shieldString;
	private String angelString;
	private String roundString;
	private String starString;
	private String hexagonString;
	
	private int speedupInt;
	private int speeddownInt;
	private int shieldInt;
	private int angelInt;
	private int roundInt;
	private int starInt;
	private int hexagonInt;
	
	public Bag(Context context){
		selected_1 = null;
		selected_2 = null;
		selected_role = null;
		sharedPreferences = context.getSharedPreferences("data",0);//read data of coin,item_to_num
		
		speedupString = context.getResources().getString(shitGame.shitgame01.R.string.speed_up);
	    speeddownString = context.getResources().getString(shitGame.shitgame01.R.string.speed_down);
		shieldString = context.getResources().getString(shitGame.shitgame01.R.string.shield);
		angelString = context.getResources().getString(shitGame.shitgame01.R.string.angel);
		roundString = context.getResources().getString(shitGame.shitgame01.R.string.round);
		starString = context.getResources().getString(shitGame.shitgame01.R.string.star);
		hexagonString = context.getResources().getString(shitGame.shitgame01.R.string.hexagon);
		coinString = context.getResources().getString(shitGame.shitgame01.R.string.coin);
		
		speedupInt = sharedPreferences.getInt(speedupString, 0);
		speeddownInt = sharedPreferences.getInt(speeddownString, 0);
		shieldInt = sharedPreferences.getInt(shieldString, 0);
		angelInt = sharedPreferences.getInt(angelString, 0);
		roundInt = sharedPreferences.getInt(roundString, 0);
		starInt = sharedPreferences.getInt(starString, 0);
		hexagonInt = sharedPreferences.getInt(hexagonString, 0);
		coinInt = sharedPreferences.getInt("coin", 0);
		
		item_to_num.putInt(speedupString, speedupInt);
		item_to_num.putInt(speeddownString, speeddownInt);
		item_to_num.putInt(shieldString, shieldInt);
		item_to_num.putInt(angelString, angelInt);
		item_to_num.putInt(roundString, roundInt);
		item_to_num.putInt(starString, starInt);
		item_to_num.putInt(hexagonString, hexagonInt);
		item_to_num.putInt(coinString, coinInt);
	}
	
	public void select(Item item_1,Item item_2,Item role_item){
		selected_1 = item_1;
		selected_2 = item_2;
		selected_role = role_item;
	}
	
	public void writeItem(String key,int val){
		SharedPreferences.Editor editor;
		editor=sharedPreferences.edit(); 
		
		editor.putInt(key, val);  
		editor.commit();  
	}
	
	public void sumItem(){
		writeItem(speedupString, item_to_num.getInt(speedupString));
		writeItem(speeddownString, item_to_num.getInt(speeddownString));
		writeItem(shieldString, item_to_num.getInt(shieldString));
		writeItem(coinString, item_to_num.getInt(coinString));
		writeItem(roundString, item_to_num.getInt(roundString));
		writeItem(starString, item_to_num.getInt(starString));
		writeItem(hexagonString,item_to_num.getInt(hexagonString));
	}
	
	public void useItem(Item used){
		int tmp = item_to_num.getInt(used.getItem_id());
		item_to_num.putInt(used.getItem_id(), tmp-1);
	}

	public void buyItem(Item item,int num){
		int tmp = item_to_num.getInt(item.getItem_id());
		item_to_num.putInt(item.getItem_id(), tmp+num);
		writeItem(item.getItem_id(), tmp+num);
	}
	
	public void setMission(int mission){
		this.mission = mission;
	}
	
	public int getMission(){
		return mission;
	}
	
	public Bundle getItem_to_num() {
		return item_to_num;
	}
	
	public void setItem_to_num(Bundle item_to_num) {
		this.item_to_num = item_to_num;
	}

	public int getCoinInt() {
		return coinInt;
	}
	
	public void setCoinInt(int coin) {
		this.coinInt = coinInt;
	}
	
	public Item getSelected_1() {
		return selected_1;
	}
	
	public void setSelected_1(Item selected_1) {
		this.selected_1 = selected_1;
	}
	
	public Item getSelected_2() {
		return selected_2;
	}
	
	public void setSelected_2(Item selected_2) {
		this.selected_2 = selected_2;
	}
	
	public Item getSelected_role() {
		return selected_role;
	}
	
	public void setSelected_role(Item selected_role) {
		this.selected_role = selected_role;
	}
	
	public int getSpeedupInt() {
		return speedupInt;
	}
	
	public void setSpeedupInt(int speedupInt) {
		this.speedupInt = speedupInt;
	}
	
	public int getSpeeddownInt() {
		return speeddownInt;
	}
	
	public void setSpeeddownInt(int speeddownInt) {
		this.speeddownInt = speeddownInt;
	}
	
	public int getShieldInt() {
		return shieldInt;
	}
	
	public void setShieldInt(int shieldInt) {
		this.shieldInt = shieldInt;
	}
	
	public int getAngelInt() {
		return angelInt;
	}
	
	public void setAngelInt(int angelInt) {
		this.angelInt = angelInt;
	}
	
	public int getRoundInt() {
		return roundInt;
	}
	
	public void setRoundInt(int roundInt) {
		this.roundInt = roundInt;
	}
	
	public int getStarInt() {
		return starInt;
	}
	
	public void setStarInt(int starInt) {
		this.starInt = starInt;
	}
	
	public int getHexagonInt() {
		return hexagonInt;
	}
	
	public void setHexagonInt(int hexagonInt) {
		this.hexagonInt = hexagonInt;
	}
	
	
}
