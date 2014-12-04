package com.example.map2;

import java.util.Vector;


public class MapInfo {
	public int map_array[][];
	public int change_wall_num[];
	public Vector<EnemyInfo>enemyvector;
	public int start_wall_num;
	public int end_wall_num;
	
	public MapInfo(int array[][],int wallnum[],int start,int end){
		map_array=array;
		change_wall_num=wallnum;
		start_wall_num=start;
		end_wall_num=end;
	}
	public void add_vector(EnemyInfo enemy)
	{
		enemyvector.add(enemy);
	}
	
}

