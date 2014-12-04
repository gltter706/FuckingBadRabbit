package shitGame.shitgame01.activities;

import java.util.ArrayList;
import java.util.HashMap;

import shitGame.shitgame01.R;
import shitGame.shitgame01.utils.Bag;
import shitGame.shitgame01.utils.Item;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SelectItemActivity extends Activity{
	private ListView lv_selectitem = null;
	private Button btn_selectitem_confirm = null;
	private Button btn_selectitem_cancel = null;
	public Item[] items = new Item[4];
	public Item[] personItems = new Item[3];
	public int[] drawable = new int[4];
	public int[] personDrawable = new int[3];
	public String[] desc = new String[4];
	public String[] personDesc = new String[3];
	public String[] boughtString = new String[]{"已购买",
			"未购买","未购买"};
	public String[] amount = new String[]{
			"数量:1","数量:2","数量:3","数量:4"
	};
	public int idResource[] = {
			shitGame.shitgame01.R.string.speed_up,
			shitGame.shitgame01.R.string.speed_down,
			shitGame.shitgame01.R.string.shield,
			shitGame.shitgame01.R.string.angel,
			shitGame.shitgame01.R.string.round,
			shitGame.shitgame01.R.string.star,
			shitGame.shitgame01.R.string.hexagon,
	};
	private ImageView iv_current = null;
	private ImageView iv_selectitem0 = null;
	private ImageView iv_selectitem1 = null;
	private ImageView iv_selectitem2 = null;
	private ArrayList<HashMap<String,Object>> data = new ArrayList<HashMap<String,Object>>();
	private boolean personSelected = false;
	private int cur_selected_mission = 0;
	private Bag bag = null;
	private Item item_1 = null,item_2 = null,role_item = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selectitem);
		cur_selected_mission = getIntent().getIntExtra("cur_selected_mission", 0);
		initItems();
		bag = new Bag(this);
		bag.setMission(cur_selected_mission);
		lv_selectitem = (ListView)findViewById(R.id.lv_selectitem);
		iv_selectitem0 = (ImageView)findViewById(R.id.iv_selectitem0);
		iv_selectitem1 = (ImageView)findViewById(R.id.iv_selectitem1);
		iv_selectitem2 = (ImageView)findViewById(R.id.iv_selectitem2);
		btn_selectitem_confirm = (Button)findViewById(R.id.btn_selectitem_confirm);
		btn_selectitem_cancel = (Button)findViewById(R.id.btn_selectitem_cancel);
		iv_current = iv_selectitem0;
		iv_selectitem0.setOnClickListener(new ImageViewListener());
		iv_selectitem1.setOnClickListener(new ImageViewListener());
		iv_selectitem2.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				iv_current = (ImageView)v;
				personSelected = true;
				createAndShowListView(personDrawable, personDesc, boughtString);
			}
		});
		lv_selectitem.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if( false == personSelected){
				iv_current.setImageResource(drawable[arg2]);
					if(iv_current == iv_selectitem0){
					   item_1 = items[arg2];
					}else {
					   item_2 = items[arg2];
					}
				}
				else {
				iv_current.setImageResource(personDrawable[arg2]);
				role_item = personItems[arg2];
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
		createAndShowListView(drawable, desc, amount);
	}
	
	
	
	public void initItems(){
		for(int i = 0; i < 4; i++){
			String ItemID = SelectItemActivity.this.
			getResources().getString(idResource[i]);
			Item item = new Item(SelectItemActivity.this,ItemID);
			items[i] = item;
			drawable[i] = item.getDrawableId();
			desc[i] = item.getItemShort();
		}
		for(int i = 4; i < 7; i++){
			String personItemID = SelectItemActivity.this.
			getResources().getString(idResource[i]);
			Item personItem = new Item(SelectItemActivity.this,
			personItemID);
			personItems[i-4] = personItem;
			personDrawable[i-4] = personItem.getDrawableId();
			personDesc[i-4] = personItem.getItemShort();
		}
		
	}
	
	
	public void createAndShowListView(int[] drawable,String[] desc,String[] extra){
		data.clear();
		for(int i =0; i<desc.length; i++){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("img", drawable[i]);
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
			createAndShowListView(drawable, desc, amount);
		}
		
	}
	
}

