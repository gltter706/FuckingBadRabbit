package com.example.map2;


public class EnemyInfo {
	public int wall;//如果是横着走，wall是最左边的墙号，是竖着走，wall是最上边的墙号
	public int enemy_run_num;
	public int type;//1是竖着走，2是横着走
	public EnemyInfo(int e, int run_num, int theType)
	{
		wall=e;
		enemy_run_num=run_num;
	    type=theType;
	}
}
