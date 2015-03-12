package com.example.map2;

import java.util.Vector;

import shitGame.shitgame01.utils.Key;


public class MapInfo {
	public int map_array[][];
	public int change_wall_num[];
	public Vector<EnemyInfo>enemyvector;
	public Vector<Key> keyvector;
	public int start_wall_num;
	public int end_wall_num;
	
	public MapInfo(int array[][],int wallnum[],int start,int end){
		map_array=array;
		change_wall_num=wallnum;
		enemyvector=new Vector<EnemyInfo>();
		keyvector=new Vector<Key>();
		start_wall_num=start;
		end_wall_num=end;
	}
	public void add_enemy(EnemyInfo enemy)
	{
		enemyvector.add(enemy);
	}
	public void add_key(Key key)
	{
		keyvector.add(key);
	}
	
}

