package shitGame.shitgame01.activities;

import java.util.ArrayList;
import java.util.HashMap;

import shitGame.shitgame01.R;
import shitGame.shitgame01.utils.Bag;
import shitGame.shitgame01.utils.Item;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class SelectItemActivity extends Activity{
	private ListView lv_selectitem = null;
	private ImageButton btn_selectitem_confirm = null;
	private ImageButton btn_selectitem_cancel = null;
	public Item[] items = new Item[3];
	public Item[] personItems = new Item[3];
	//4个道具
	public int[] drawable = new int[4];
	//带指纹的4个道具
	public int[] drawableWithFingerPrints = new int[]{
			R.drawable.ic_originalitems_withfingerprint,
			R.drawable.ic_up_withfingerprint,
			R.drawable.ic_down_withfingerprint,
			R.drawable.ic_shield_withfingerprint,
	};
	//3个角色
	public int[] personDrawable = new int[3];
	//4个道具描述
	public String[] desc = new String[4];
	//3个角色描述
	public String[] personDesc = new String[3];
	public String[] boughtString = new String[]{"已购买",
			"未购买","未购买"};
	public String[] amount = new String[]{
			"","数量:","数量:","数量:"
	};
	public String[] itemAmounts = new String[4];
	public String[] needCoin = new String[7];
	public int coin_draw_id;
	public int idResource[] = {
			shitGame.shitgame01.R.string.speed_up,
			shitGame.shitgame01.R.string.speed_down,
			shitGame.shitgame01.R.string.shield,
			
	};
	private ImageView iv_current = null;
	private ImageView iv_selectitem0 = null;
	private ImageView iv_selectitem1 = null;
	private ImageView iv_selectitem2 = null;
	private ArrayList<HashMap<String,Object>> data = new ArrayList<HashMap<String,Object>>();
	private boolean personSelected = false;
	private boolean zero = true;
	private int cur_selected_mission = 0;
	//第一个道具框选中的道具编号
	private int cur_selected_item0 = -1;
	//第二个道具框选中的道具编号
	private int cur_selected_item1 = -1;
	private int cur_item_number = 0;
	private Bag bag = null;
	private Item item_1 = null,item_2 = null,role_item = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selectitem);
		coin_draw_id = R.drawable.ic_coin;
		cur_selected_mission = getIntent().getIntExtra("cur_selected_mission", 0);
		bag = new Bag(this);
		bag.setMission(cur_selected_mission);
		
		initItems();
		lv_selectitem = (ListView)findViewById(R.id.lv_selectitem);
		iv_selectitem0 = (ImageView)findViewById(R.id.iv_selectitem0);
		iv_selectitem1 = (ImageView)findViewById(R.id.iv_selectitem1);
		iv_selectitem2 = (ImageView)findViewById(R.id.iv_selectitem2);
		iv_selectitem2.setImageResource(R.drawable.ic_role);
		btn_selectitem_confirm = (ImageButton)findViewById(R.id.btn_selectitem_confirm);
		btn_selectitem_cancel = (ImageButton)findViewById(R.id.btn_selectitem_cancel);
		
		iv_current = iv_selectitem0;
		iv_selectitem0.setOnClickListener(new ImageViewListener());
		iv_selectitem1.setOnClickListener(new ImageViewListener());
		iv_selectitem2.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				iv_current = (ImageView)v;
				personSelected = true;
				//createAndShowListView(personDrawable, needCoin,personDesc, boughtString);
			}
		});
		
		lv_selectitem.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				if(arg2 != 0 && 0 == Integer.valueOf(itemAmounts[arg2])){
					zero = true;
					Toast.makeText(SelectItemActivity.this, "此道具数目为0!", Toast.LENGTH_LONG).show();
				} else{
					zero = false;
				}
				if(arg2 != 0 && 1 == Integer.valueOf(itemAmounts[arg2]) && (arg2 == cur_selected_item0 || arg2 == cur_selected_item1)){
					Toast.makeText(SelectItemActivity.this, "此道具数目为0!", Toast.LENGTH_LONG).show();
				}else{
					
				if( false == personSelected){
					if(0 == arg2 && iv_current == iv_selectitem0){
						iv_current.setImageResource(R.drawable.ic_originalitems);
						cur_selected_item0 = -1;
					}else if(0 == arg2 && iv_current == iv_selectitem1){
						iv_current.setImageResource(R.drawable.ic_originalitems);
						cur_selected_item1 = -1;
					}else if(!zero){
				iv_current.setImageResource(drawable[arg2]);
					if(iv_current == iv_selectitem0){
					   item_1 = items[arg2-1];
					   cur_selected_item0 = arg2;
					}else {
					   item_2 = items[arg2-1];
					   cur_selected_item1 = arg2;
					}
				  }
				}
				else {
					
				iv_current.setImageResource(personDrawable[arg2]);
				role_item = personItems[arg2];
				 
				}
			  }
		   }
			
		});
		
		btn_selectitem_confirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SelectItemActivity.this,ChallengeActivity.class);
				bag.select(item_1, item_2, role_item);
				intent.putExtra("bag", bag);
				startActivity(intent);
				SelectItemActivity.this.finish();
			}
		});
		
		btn_selectitem_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SelectItemActivity.this,SelectMissionActivity.class);
				startActivity(intent);
				SelectItemActivity.this.finish();
			}
		});
		createAndShowListView(drawable,desc, amount);
	}
	
	
	
	public void initItems(){
			drawable[0] = R.drawable.ic_nullcancel;
			desc[0] = "取消选择";
			needCoin[0] = "";
			itemAmounts[0] = "";
			amount[0] += itemAmounts[0];
		for(int i = 0; i < 3; i++){
			String ItemID = SelectItemActivity.this.
			getResources().getString(idResource[i]);
			Item item = new Item(SelectItemActivity.this,ItemID);
			items[i] = item;
			drawable[i+1] = item.getDrawableId();
			desc[i+1] = item.getItemShort();
			needCoin[i+1] = String.valueOf(item.getCoin());
			itemAmounts[i+1] = String.valueOf(bag.getItem_to_num().get(items[i].getItem_id()));
			amount[i+1] += itemAmounts[i+1];
		}
		/*
		for(int i = 4; i < 6; i++){
			String personItemID = SelectItemActivity.this.
			getResources().getString(idResource[i]);
			Item personItem = new Item(SelectItemActivity.this,
			personItemID);
			personItems[i-4] = personItem;
			personDrawable[i-4] = personItem.getDrawableId();
			personDesc[i-4] = personItem.getItemShort();
			needCoin[i] = String.valueOf(personItem.getCoin());
		}
		*/
	}
	
	
	public void createAndShowListView(int[] drawable,String[] desc,String[] extra){
		data.clear();
		for(int i =0; i<desc.length; i++){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("img", drawable[i]);
			//map.put("coin",coin_draw_id);
			map.put("desc", desc[i]);
			map.put("extra", extra[i]);
			data.add(map);
		}
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.listitem_selectitem, new String[]{"img","desc","extra"}, new int[]{R.id.iv_listitem,R.id.tv_desc_listitem,R.id.tv_extra_listitem});
		lv_selectitem.setAdapter(simpleAdapter);
	}
	
	class ImageViewListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			iv_current = (ImageView)v;
			personSelected = false;
			createAndShowListView(drawable,  desc, amount);
			//如果当前点击的是第一个道具框
				if(iv_current == iv_selectitem0){
					//第一个道具框未选过道具而第二个也未选过
					if(-1 == cur_selected_item0 && -1 == cur_selected_item1){
					iv_selectitem1.setImageResource(R.drawable.ic_originalitems);
					iv_selectitem0.setImageResource(R.drawable.ic_originalitems_withfingerprint);
					}
					//第一个道具框未选过而第二个选过
				else if(-1 == cur_selected_item0 && -1 != cur_selected_item1 && !zero){
					iv_selectitem0.setImageResource(R.drawable.ic_originalitems_withfingerprint);
					iv_selectitem1.setImageResource(drawable[cur_selected_item1]);
					//第一个道具框选过而第二个未选过
			   }else if(-1 != cur_selected_item0 && -1 == cur_selected_item1 && !zero){
				   iv_selectitem0.setImageResource(drawableWithFingerPrints[cur_selected_item0]);
				   iv_selectitem1.setImageResource(R.drawable.ic_originalitems);
				   //第一个道具框选过而第二个道具框也选过
			 }else if(-1 != cur_selected_item0 && -1 != cur_selected_item1 && !zero){
				   iv_selectitem0.setImageResource(drawableWithFingerPrints[cur_selected_item0]);
				   iv_selectitem1.setImageResource(drawable[cur_selected_item1]);
				 }
					//当前点击的是第二个道具框,同上
			}else if(iv_current == iv_selectitem1){
				if(-1 == cur_selected_item1 && -1 == cur_selected_item0){
					iv_selectitem0.setImageResource(R.drawable.ic_originalitems);
					iv_selectitem1.setImageResource(R.drawable.ic_originalitems_withfingerprint);
					}
				else if(-1 == cur_selected_item1 && -1 != cur_selected_item0 && !zero){
					iv_selectitem1.setImageResource(R.drawable.ic_originalitems_withfingerprint);
					iv_selectitem0.setImageResource(drawable[cur_selected_item0]);
			   }else if(-1 != cur_selected_item1 && -1 == cur_selected_item0 && !zero){
				   iv_selectitem1.setImageResource(drawableWithFingerPrints[cur_selected_item1]);
				   iv_selectitem0.setImageResource(R.drawable.ic_originalitems);
			 }else if(-1 != cur_selected_item1 && -1 != cur_selected_item0 && !zero){
				   iv_selectitem1.setImageResource(drawableWithFingerPrints[cur_selected_item1]);
				   iv_selectitem0.setImageResource(drawable[cur_selected_item0]);
				 }
			}
		
		}
	
	}
}
