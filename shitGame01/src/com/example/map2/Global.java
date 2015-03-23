package com.example.map2;

import java.util.*;

import shitGame.shitgame01.utils.Key;



public class Global {

	//地图数组，0表示可走的格子，1表示墙，大于1表示传送门，小于0表示钥匙
	//传送门2传到2,3传到3
	//钥匙-1号表示mapinfo.keyvector[1]这条钥匙，-2则表示mapinfo.keyvector[2]，便于快速查找对应钥匙
	//右上角一定是0，用于放暂停按钮
	//起点和终点一定为0
	public static Vector<MapInfo> ALL_MAP=new Vector<MapInfo>();
	public Global(){
	int map_array1[][]=new int[][]{
				{0,1,0,1,0,1,0,1,0,0},
				{0,0,0,1,0,0,0,0,0,1},
				{1,0,2,0,1,0,0,0,0,1},
				{0,0,1,0,0,0,0,0,1,0},
				{0,0,2,0,1,0,0,0,1,0},
				{0,0,0,0,0,0,1,0,0,1},
				{0,1,0,0,1,1,1,1,1,0},
				{0,0,1,0,0,0,1,0,3,1},
				{1,0,0,0,0,1,1,0,0,0},
				{3,-1,0,1,0,1,0,1,0,0}};
	int change_wall_num1[]=new int[]{5,6,7};
	
	MapInfo mapinfo1=new MapInfo(map_array1,change_wall_num1,0,99);
	mapinfo1.add_enemy(new EnemyInfo(77,2,2));
	mapinfo1.add_enemy(new EnemyInfo(23, 7, 1));
	mapinfo1.add_key(new Key(91, true, new int[]{67}));
	ALL_MAP.add(mapinfo1);
		
	int map_array2[][]=new int[][]{
			{1,1,1,1,1,1,1,1,0,0},
			{1,1,1,1,1,1,1,0,0,0},
			{1,1,1,1,1,1,0,0,0,1},
			{1,1,1,1,1,0,0,0,1,1},
			{1,1,1,1,0,0,0,1,1,1},
			{1,1,1,0,0,0,1,1,1,1},
			{1,1,0,0,0,1,1,1,1,1},
			{1,0,0,0,1,1,1,1,1,1},
			{0,0,0,1,1,1,1,1,1,1},
			{0,0,1,1,1,1,1,1,1,1}};
	int change_wall_num2[]=new int[]{};
	MapInfo mapinfo2=new MapInfo(map_array2,change_wall_num2,90,9);
	mapinfo2.add_enemy(new EnemyInfo(53, 3, 1));
	mapinfo2.add_enemy(new EnemyInfo(35, 3, 1));
	mapinfo2.add_enemy(new EnemyInfo(17, 3, 1));
	ALL_MAP.add(mapinfo2);
	
	int map_array3[][]=new int[][]{
			{0,1,0,1,0,1,0,1,0,0},
			{0,0,0,1,0,0,0,0,0,1},
			{1,0,0,0,1,0,0,0,0,1},
			{0,0,1,0,0,0,0,0,1,0},
			{0,0,0,0,1,0,0,0,1,0},
			{0,0,0,0,0,0,1,0,0,1},
			{0,1,0,0,1,1,1,0,1,0},
			{0,0,1,0,0,0,1,0,0,1},
			{1,0,0,0,0,1,1,0,0,0},
			{0,0,0,1,0,1,0,1,0,0}};
	int change_wall_num3[]=new int[]{67};
	
	MapInfo mapinfo3=new MapInfo(map_array3,change_wall_num3,0,99);
	ALL_MAP.add(mapinfo3);
	
	
	int map_array4[][]=new int[][]{
			{0,0,0,0,0,0,0,1,0,0},
			{0,0,1,0,0,0,0,1,0,0},
			{0,0,0,0,1,0,0,1,0,0},
			{0,0,1,1,0,0,0,0,0,1},
			{0,0,1,0,0,1,1,0,0,0},
			{0,0,0,0,0,1,0,0,0,0},
			{0,0,1,0,0,1,0,1,1,0},
			{0,0,1,0,0,0,0,0,0,0},
			{0,0,0,0,1,1,1,0,1,0},
			{0,0,0,0,0,0,1,0,0,0}};
	int change_wall_num4[]=new int[]{};
	MapInfo mapinfo4=new MapInfo(map_array4,change_wall_num4,40,99);
	ALL_MAP.add(mapinfo4);
	
	int map_array5[][]=new int[][]{
			{0,0,1,1,1,0,0,0,0,0},
			{0,0,0,0,1,1,0,0,0,0},
			{1,0,0,0,0,0,0,0,0,0},
			{1,0,0,0,0,0,1,1,0,0},
			{1,0,0,1,0,0,1,0,0,0},
			{0,0,0,1,0,0,1,0,1,1},
			{1,1,0,1,0,0,1,0,0,1},
			{0,0,0,1,0,0,1,0,0,0},
			{0,0,0,1,0,0,0,1,0,0},
			{0,0,0,0,0,0,0,0,0,0}};
	int change_wall_num5[]=new int[]{87,97};
	
	MapInfo mapinfo5=new MapInfo(map_array5,change_wall_num5,0,99);
	mapinfo5.add_enemy(new EnemyInfo(34, 2, 2));
	ALL_MAP.add(mapinfo5);
	
	
	int map_array6[][]=new int[][]{
			{0,0,0,1,0,0,1,0,0,0},
			{0,0,0,1,0,0,1,0,1,0},
			{0,1,0,0,0,0,1,0,0,0},
			{1,1,1,1,0,0,1,0,1,1},
			{0,0,0,0,0,0,1,0,0,0},
			{0,0,1,0,1,1,1,0,0,0},
			{0,0,1,0,0,0,0,0,1,1},
			{0,0,1,0,0,1,0,0,1,1},
			{0,0,0,0,0,1,1,0,1,0},
			{0,0,0,0,0,0,0,0,1,0}};
	int change_wall_num6[]=new int[]{52,53,67,68};
	MapInfo mapinfo6=new MapInfo(map_array6,change_wall_num6,0,9);
	ALL_MAP.add(mapinfo6);
	
	int map_array7[][]=new int[][]{
			{1,0,1,1,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,0,1,0},
			{0,1,1,1,0,1,1,0,1,0},
			{0,0,0,0,0,0,1,0,1,0},
			{0,1,0,0,1,0,0,0,0,0},
			{0,0,1,0,0,1,0,1,1,1},
			{0,0,1,0,0,1,0,0,0,0},
			{1,1,1,1,0,1,0,0,0,0},
			{0,0,0,0,0,1,0,0,0,0},
			{0,0,0,0,0,1,0,0,0,0}};
	int change_wall_num7[]=new int[]{18,19,23,33,47,57,49,59};
	MapInfo mapinfo7=new MapInfo(map_array7,change_wall_num7,90,39);
	mapinfo7.add_enemy(new EnemyInfo(4, 3, 1));
	ALL_MAP.add(mapinfo7);
	
	
	int map_array8[][]=new int[][]{
			{0,0,0,1,0,0,0,0,1,1},
			{0,0,0,1,0,0,0,1,0,0},
			{0,1,1,1,0,0,1,0,0,0},
			{0,0,0,1,0,1,0,0,1,0},
			{1,1,0,1,1,0,0,1,0,0},
			{0,0,0,0,0,0,0,1,0,1},
			{0,0,0,0,1,0,1,1,0,1},
			{0,1,1,1,1,0,0,1,0,0},
			{0,1,0,0,0,0,0,1,1,0},
			{0,0,0,1,0,0,0,1,0,0}};
	int change_wall_num8[]=new int[]{22,32,54,64};
	MapInfo mapinfo8=new MapInfo(map_array8,change_wall_num8,0,99);
	mapinfo8.add_enemy(new EnemyInfo(55, 2, 2));
	ALL_MAP.add(mapinfo8);
	
	
	int map_array9[][]=new int[][]{
			{0,0,0,1,1,1,1,1,1,1},
			{0,1,0,1,1,0,0,0,0,0},
			{0,1,0,0,0,0,1,0,1,0},
			{0,1,1,1,1,1,1,0,0,0},
			{0,0,0,0,1,1,1,1,1,0},
			{0,1,1,0,1,1,1,0,0,0},
			{0,1,0,0,1,1,1,0,1,1},
			{0,1,0,0,0,0,0,0,1,1},
			{0,1,0,0,0,0,0,1,1,1},
			{0,0,0,1,1,0,0,1,1,1}};
	int change_wall_num9[]=new int[]{18,28,50,51,42,52,81,91};
	
	MapInfo mapinfo9=new MapInfo(map_array9,change_wall_num9,74,12);
	ALL_MAP.add(mapinfo9);
	
	
	int map_array10[][]=new int[][]{
			{0,0,1,1,1,0,1,1,1,1},
			{0,0,0,0,1,0,0,0,0,1},
			{1,1,1,0,1,0,1,1,0,1},
			{1,0,0,0,1,0,0,1,0,0},
			{1,0,1,1,1,0,1,1,0,0},
			{1,0,1,1,1,0,0,1,0,0},
			{1,0,1,1,0,0,0,1,0,1},
			{1,0,1,0,0,1,1,1,0,1},
			{1,0,0,0,1,1,1,1,0,0},
			{1,1,1,1,1,1,1,1,0,0}};
	int change_wall_num10[]=new int[]{45,46};
	MapInfo mapinfo10=new MapInfo(map_array10,change_wall_num10,0,99);
	ALL_MAP.add(mapinfo10);
	
	int map_array11[][]=new int[][]{
			{-1,0,0,0,1,1,1,1,0,0},
			{0,1,1,0,1,1,1,1,0,0},
			{0,0,1,0,1,1,0,1,0,1},
			{1,0,1,0,1,0,0,1,1,1},
			{1,0,1,0,0,0,0,1,1,1},
			{1,0,1,0,0,0,1,1,1,1},
			{1,0,0,0,0,1,1,1,1,1},
			{1,0,0,0,1,1,1,1,1,1},
			{0,0,0,1,1,1,1,1,1,1},
			{0,0,1,1,1,1,1,1,1,1}};
	int change_wall_num11[]=new int[]{};
	
	MapInfo mapinfo11=new MapInfo(map_array11,change_wall_num11,90,9);
	mapinfo11.add_key(new Key(0, true, new int[]{17,27,37}));
	ALL_MAP.add(mapinfo11);
	
	
	int map_array12[][]=new int[][]{
			{-1,0,0,0,1,1,1,1,0,0},
			{0,1,1,0,1,1,1,1,0,0},
			{0,0,1,0,1,1,0,1,0,1},
			{1,0,1,0,1,0,0,1,1,1},
			{1,0,1,0,1,0,0,1,1,1},
			{1,0,1,0,0,0,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,0},
			{1,0,0,0,1,1,1,1,1,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,1,1,1,1,1,1,0,-2}};
	int change_wall_num12[]=new int[]{43,44};
	
	MapInfo mapinfo12=new MapInfo(map_array12,change_wall_num12,90,9);
	mapinfo12.add_enemy(new EnemyInfo(88, 2, 1));
	mapinfo12.add_key(new Key(0, false, new int[]{41}));
	mapinfo12.add_key(new Key(99, true, new int[]{17,27,37}));
	ALL_MAP.add(mapinfo12);
	
	int map_array13[][]=new int[][]{
			{0,0,0,1,0,0,1,3,0,0},
			{0,0,0,1,0,0,1,0,0,0},
			{0,0,2,1,0,0,1,4,0,0},
			{1,1,1,0,0,0,0,1,1,1},
			{0,0,0,1,0,0,0,0,0,0},
			{0,0,0,0,0,5,0,0,0,0},
			{1,1,1,0,0,0,0,1,1,1},
			{0,0,3,1,0,0,1,5,0,0},
			{0,0,0,1,0,0,1,0,0,0},
			{2,0,0,1,0,0,1,0,0,4}};
	int change_wall_num13[]=new int[]{43,53};
	
	MapInfo mapinfo13=new MapInfo(map_array13,change_wall_num13,0,52);
	ALL_MAP.add(mapinfo13);
	
	int map_array14[][]=new int[][]{
			{0,0,0,1,0,0,0,0,2,0},
			{0,0,0,1,0,0,0,0,0,0},
			{1,0,0,1,0,0,0,1,1,1},
			{1,0,0,1,0,0,0,0,0,-1},
			{1,0,1,1,0,1,1,1,1,1},
			{1,0,1,0,0,0,0,0,0,0},
			{1,0,1,0,0,0,1,1,1,1},
			{0,0,1,1,1,0,1,0,0,0},
			{0,0,0,0,1,0,1,0,0,0},
			{0,0,0,2,1,0,1,0,0,0}};
	int change_wall_num14[]=new int[]{};
	
	MapInfo mapinfo14=new MapInfo(map_array14,change_wall_num14,0,99);
	mapinfo14.add_enemy(new EnemyInfo(81, 2, 1));
	mapinfo14.add_key(new Key(39, true, new int[]{66,76}));
	ALL_MAP.add(mapinfo14);
	
	int map_array15[][]=new int[][]{
			{0,0,1,0,0,0,1,0,0,0},
			{0,0,1,0,0,0,1,0,0,0},
			{0,1,1,0,0,1,1,0,1,0},
			{0,2,1,0,0,0,0,0,1,0},
			{1,1,1,0,0,0,1,1,1,0},
			{0,0,0,0,1,0,0,0,0,0},
			{0,0,0,0,1,0,0,0,0,0},
			{0,0,0,0,1,0,0,0,0,0},
			{0,2,0,0,1,0,0,1,1,0},
			{0,0,0,0,1,0,0,0,0,0}};
	int change_wall_num15[]=new int[]{20,21,25,35};
	
	MapInfo mapinfo15=new MapInfo(map_array15,change_wall_num15,0,9);
	mapinfo15.add_enemy(new EnemyInfo(52, 3, 1));
	mapinfo15.add_enemy(new EnemyInfo(58, 3, 1));
	ALL_MAP.add(mapinfo15);
	
	
	int map_array16[][]=new int[][]{
			{1,1,1,1,1,1,1,1,1,1},
			{0,0,0,0,0,2,0,0,0,0},
			{1,1,1,1,1,1,1,1,1,1},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,0,-1,0,0},
			{0,0,0,1,0,0,0,0,0,0},
			{1,1,1,1,1,1,1,1,1,1},
			{0,0,0,0,1,0,0,0,0,0},
			{0,0,1,0,0,0,0,0,2,0},
			{1,1,1,1,0,1,1,0,0,0}};
	int change_wall_num16[]=new int[]{22,32,74,84};
	
	MapInfo mapinfo16=new MapInfo(map_array16,change_wall_num16,40,80);
	mapinfo16.add_enemy(new EnemyInfo(35, 3, 1));
	mapinfo16.add_key(new Key(47, true, new int[]{28}));
	ALL_MAP.add(mapinfo16);
	
	int map_array17[][]=new int[][]{
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0}};
	int change_wall_num17[]=new int[]{8,18,21,22,28,29,30,40,38,39,47,48,54,64,58,48};
	
	MapInfo mapinfo17=new MapInfo(map_array17,change_wall_num17,0,99);
	//mapinfo17.add_vector(null);
	ALL_MAP.add(mapinfo17);
	
	
	int map_array18[][]=new int[][]{{0,0,0,1,0,0,1,1,1,1},
			{1,1,0,1,0,0,1,0,0,0},
			{0,0,1,1,1,1,1,0,1,0},
			{0,0,0,0,0,0,0,0,1,0},
			{1,0,1,1,1,1,1,1,0,0},
			{1,0,1,0,0,0,0,0,0,0},
			{1,0,1,1,1,1,1,1,1,0},
			{1,0,1,0,0,0,0,0,1,0},
			{1,0,1,1,0,1,1,0,1,0},
			{0,0,0,0,0,1,1,0,0,0}};
	int change_wall_num18[]=new int[]{8,18,21,22,28,29,30,40,38,39,47,48,54,64,58,48};
	
	MapInfo mapinfo18=new MapInfo(map_array18,change_wall_num18,0,99);
	//mapinfo18.add_vector(null);
	ALL_MAP.add(mapinfo18);
	
	int map_array19[][]=new int[][]{{0,0,0,1,0,0,1,1,1,1},
			{1,1,0,1,0,0,1,0,0,0},
			{0,0,1,1,1,1,1,0,1,0},
			{0,0,0,0,0,0,0,0,1,0},
			{1,0,1,1,1,1,1,1,0,0},
			{1,0,1,0,0,0,0,0,0,0},
			{1,0,1,1,1,1,1,1,1,0},
			{1,0,1,0,0,0,0,0,1,0},
			{1,0,1,1,0,1,1,0,1,0},
			{0,0,0,0,0,1,1,0,0,0}};
	int change_wall_num19[]=new int[]{8,18,21,22,28,29,30,40,38,39,47,48,54,64,58,48};
	
	MapInfo mapinfo19=new MapInfo(map_array19,change_wall_num19,0,99);
	//mapinfo19.add_vector(null);
	ALL_MAP.add(mapinfo19);
	
	int map_array20[][]=new int[][]{{0,0,0,1,0,0,1,1,1,1},
			{1,1,0,1,0,0,1,0,0,0},
			{0,0,1,1,1,1,1,0,1,0},
			{0,0,0,0,0,0,0,0,1,0},
			{1,0,1,1,1,1,1,1,0,0},
			{1,0,1,0,0,0,0,0,0,0},
			{1,0,1,1,1,1,1,1,1,0},
			{1,0,1,0,0,0,0,0,1,0},
			{1,0,1,1,0,1,1,0,1,0},
			{0,0,0,0,0,1,1,0,0,0}};
	int change_wall_num20[]=new int[]{8,18,21,22,28,29,30,40,38,39,47,48,54,64,58,48};
	
	MapInfo mapinfo20=new MapInfo(map_array20,change_wall_num20,0,99);
	//mapinfo20.add_vector(null);
	ALL_MAP.add(mapinfo20);
	
	int map_array21[][]=new int[][]{{0,0,0,1,0,0,1,1,1,1},
			{1,1,0,1,0,0,1,0,0,0},
			{0,0,1,1,1,1,1,0,1,0},
			{0,0,0,0,0,0,0,0,1,0},
			{1,0,1,1,1,1,1,1,0,0},
			{1,0,1,0,0,0,0,0,0,0},
			{1,0,1,1,1,1,1,1,1,0},
			{1,0,1,0,0,0,0,0,1,0},
			{1,0,1,1,0,1,1,0,1,0},
			{0,0,0,0,0,1,1,0,0,0}};
	int change_wall_num21[]=new int[]{8,18,21,22,28,29,30,40,38,39,47,48,54,64,58,48};
	
	MapInfo mapinfo21=new MapInfo(map_array21,change_wall_num21,0,99);
	//mapinfo21.add_vector(null);
	ALL_MAP.add(mapinfo21);
	
	
	int map_array22[][]=new int[][]{{0,0,0,1,0,0,1,1,1,1},
			{1,1,0,1,0,0,1,0,0,0},
			{0,0,1,1,1,1,1,0,1,0},
			{0,0,0,0,0,0,0,0,1,0},
			{1,0,1,1,1,1,1,1,0,0},
			{1,0,1,0,0,0,0,0,0,0},
			{1,0,1,1,1,1,1,1,1,0},
			{1,0,1,0,0,0,0,0,1,0},
			{1,0,1,1,0,1,1,0,1,0},
			{0,0,0,0,0,1,1,0,0,0}};
	int change_wall_num22[]=new int[]{8,18,21,22,28,29,30,40,38,39,47,48,54,64,58,48};
	
	MapInfo mapinfo22=new MapInfo(map_array22,change_wall_num22,0,99);
	//mapinfo22.add_vector(null);
	ALL_MAP.add(mapinfo22);
	
	int map_array23[][]=new int[][]{{0,0,0,1,0,0,1,1,1,1},
			{1,1,0,1,0,0,1,0,0,0},
			{0,0,1,1,1,1,1,0,1,0},
			{0,0,0,0,0,0,0,0,1,0},
			{1,0,1,1,1,1,1,1,0,0},
			{1,0,1,0,0,0,0,0,0,0},
			{1,0,1,1,1,1,1,1,1,0},
			{1,0,1,0,0,0,0,0,1,0},
			{1,0,1,1,0,1,1,0,1,0},
			{0,0,0,0,0,1,1,0,0,0}};
	int change_wall_num23[]=new int[]{8,18,21,22,28,29,30,40,38,39,47,48,54,64,58,48};
	
	MapInfo mapinfo23=new MapInfo(map_array23,change_wall_num23,0,99);
	//mapinfo23.add_vector(null);
	ALL_MAP.add(mapinfo23);
	
	int map_array24[][]=new int[][]{{0,0,0,1,0,0,1,1,1,1},
			{1,1,0,1,0,0,1,0,0,0},
			{0,0,1,1,1,1,1,0,1,0},
			{0,0,0,0,0,0,0,0,1,0},
			{1,0,1,1,1,1,1,1,0,0},
			{1,0,1,0,0,0,0,0,0,0},
			{1,0,1,1,1,1,1,1,1,0},
			{1,0,1,0,0,0,0,0,1,0},
			{1,0,1,1,0,1,1,0,1,0},
			{0,0,0,0,0,1,1,0,0,0}};
	int change_wall_num24[]=new int[]{8,18,21,22,28,29,30,40,38,39,47,48,54,64,58,48};
	
	MapInfo mapinfo24=new MapInfo(map_array24,change_wall_num24,0,99);
	//mapinfo24.add_vector(null);
	ALL_MAP.add(mapinfo24);
	
	int map_array25[][]=new int[][]{{0,0,0,1,0,0,1,1,1,1},
			{1,1,0,1,0,0,1,0,0,0},
			{0,0,1,1,1,1,1,0,1,0},
			{0,0,0,0,0,0,0,0,1,0},
			{1,0,1,1,1,1,1,1,0,0},
			{1,0,1,0,0,0,0,0,0,0},
			{1,0,1,1,1,1,1,1,1,0},
			{1,0,1,0,0,0,0,0,1,0},
			{1,0,1,1,0,1,1,0,1,0},
			{0,0,0,0,0,1,1,0,0,0}};
	int change_wall_num25[]=new int[]{8,18,21,22,28,29,30,40,38,39,47,48,54,64,58,48};
	
	MapInfo mapinfo25=new MapInfo(map_array25,change_wall_num25,0,99);
	//mapinfo25.add_vector(null);
	ALL_MAP.add(mapinfo25);
	
	
	int map_array26[][]=new int[][]{{0,0,0,1,0,0,1,1,1,1},
			{1,1,0,1,0,0,1,0,0,0},
			{0,0,1,1,1,1,1,0,1,0},
			{0,0,0,0,0,0,0,0,1,0},
			{1,0,1,1,1,1,1,1,0,0},
			{1,0,1,0,0,0,0,0,0,0},
			{1,0,1,1,1,1,1,1,1,0},
			{1,0,1,0,0,0,0,0,1,0},
			{1,0,1,1,0,1,1,0,1,0},
			{0,0,0,0,0,1,1,0,0,0}};
	int change_wall_num26[]=new int[]{8,18,21,22,28,29,30,40,38,39,47,48,54,64,58,48};
	
	MapInfo mapinfo26=new MapInfo(map_array26,change_wall_num26,0,99);
	//mapinfo26.add_vector(null);
	ALL_MAP.add(mapinfo26);
	
	
	int map_array27[][]=new int[][]{{0,0,0,1,0,0,1,1,1,1},
			{1,1,0,1,0,0,1,0,0,0},
			{0,0,1,1,1,1,1,0,1,0},
			{0,0,0,0,0,0,0,0,1,0},
			{1,0,1,1,1,1,1,1,0,0},
			{1,0,1,0,0,0,0,0,0,0},
			{1,0,1,1,1,1,1,1,1,0},
			{1,0,1,0,0,0,0,0,1,0},
			{1,0,1,1,0,1,1,0,1,0},
			{0,0,0,0,0,1,1,0,0,0}};
	int change_wall_num27[]=new int[]{8,18,21,22,28,29,30,40,38,39,47,48,54,64,58,48};
	
	MapInfo mapinfo27=new MapInfo(map_array27,change_wall_num27,0,99);
	//mapinfo27.add_vector(null);
	ALL_MAP.add(mapinfo27);
	
	
	int map_array28[][]=new int[][]{{0,0,0,1,0,0,1,1,1,1},
			{1,1,0,1,0,0,1,0,0,0},
			{0,0,1,1,1,1,1,0,1,0},
			{0,0,0,0,0,0,0,0,1,0},
			{1,0,1,1,1,1,1,1,0,0},
			{1,0,1,0,0,0,0,0,0,0},
			{1,0,1,1,1,1,1,1,1,0},
			{1,0,1,0,0,0,0,0,1,0},
			{1,0,1,1,0,1,1,0,1,0},
			{0,0,0,0,0,1,1,0,0,0}};
	int change_wall_num28[]=new int[]{8,18,21,22,28,29,30,40,38,39,47,48,54,64,58,48};
	
	MapInfo mapinfo28=new MapInfo(map_array28,change_wall_num28,0,99);
	//mapinfo28.add_vector(null);
	ALL_MAP.add(mapinfo28);
	
	int map_array29[][]=new int[][]{{0,0,0,1,0,0,1,1,1,1},
			{1,1,0,1,0,0,1,0,0,0},
			{0,0,1,1,1,1,1,0,1,0},
			{0,0,0,0,0,0,0,0,1,0},
			{1,0,1,1,1,1,1,1,0,0},
			{1,0,1,0,0,0,0,0,0,0},
			{1,0,1,1,1,1,1,1,1,0},
			{1,0,1,0,0,0,0,0,1,0},
			{1,0,1,1,0,1,1,0,1,0},
			{0,0,0,0,0,1,1,0,0,0}};
	int change_wall_num29[]=new int[]{8,18,21,22,28,29,30,40,38,39,47,48,54,64,58,48};
	
	MapInfo mapinfo29=new MapInfo(map_array29,change_wall_num29,0,99);
	//mapinfo29.add_vector(null);
	ALL_MAP.add(mapinfo29);
	
	
	int map_array30[][]=new int[][]{{0,0,0,1,0,0,1,1,1,1},
			{1,1,0,1,0,0,1,0,0,0},
			{0,0,1,1,1,1,1,0,1,0},
			{0,0,0,0,0,0,0,0,1,0},
			{1,0,1,1,1,1,1,1,0,0},
			{1,0,1,0,0,0,0,0,0,0},
			{1,0,1,1,1,1,1,1,1,0},
			{1,0,1,0,0,0,0,0,1,0},
			{1,0,1,1,0,1,1,0,1,0},
			{0,0,0,0,0,1,1,0,0,0}};
	int change_wall_num30[]=new int[]{8,18,21,22,28,29,30,40,38,39,47,48,54,64,58,48};
	
	MapInfo mapinfo30=new MapInfo(map_array30,change_wall_num30,0,99);
	//mapinfo30.add_vector(null);
	ALL_MAP.add(mapinfo30);
	}
	
	

}
