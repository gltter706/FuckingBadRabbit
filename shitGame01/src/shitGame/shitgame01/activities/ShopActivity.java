package shitGame.shitgame01.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shitGame.shitgame01.R;
import shitGame.shitgame01.utils.Bag;
import shitGame.shitgame01.utils.Item;

import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.SyncStateContract.Constants;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ShopActivity extends Activity{
	final String bag_exist = new String("背包里还剩:");
	final String tipString = new String("没钱请到别处去晃悠行吗");
	private final int item_num = 4;
	private final int role_num = 3;
	private String speedupString;
	private String speeddownString;
    private String shieldString ;
	private String angelString;
	private String roundString;
	private String starString;
	private String hexagonString;
	private String coinString;
	private Bag bag;
	private ImageButton btn_roleBlock;
	private ImageButton btn_itemBlock;
	
	private ArrayList<HashMap<String,Object>> item_data = new ArrayList<HashMap<String,Object>>();
	private ArrayList<HashMap<String, Object>> role_data = new ArrayList<HashMap<String,Object>>();
	private ListView lv_shopitem;
	private ImageView iv_desc;
	private TextView tv_desc;
	private TextView tv_bagstore;
	private TextView tv_bagcoin;
	private String[] itemIdList;
	private Item[] item = new Item[item_num];
	private Item[] role = new Item[role_num];
	private ShopAdapter item_shopAdapter;
	private ShopAdapter role_shopAdapter;
	private OnItemClickListener itemClickListener = new  OnItemClickListener() {

		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			iv_desc.setImageResource(item[position].getDrawableId());
			
			tv_desc.setText(item[position].getItemLong());
		}
		
	};
	private OnItemClickListener roleClickListener = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			iv_desc.setImageResource(role[position].getDrawableId());
			
			tv_desc.setText(role[position].getItemLong());
		}
		
	};
	MyHandler handler;
	
	private class MyHandler extends Handler{

		/* (non-Javadoc)
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(0x110 == msg.what){
				lv_shopitem.setAdapter(role_shopAdapter);
				lv_shopitem.setOnItemClickListener(roleClickListener);
			}
			if(0x111 == msg.what){
				lv_shopitem.setAdapter(item_shopAdapter);
				lv_shopitem.setOnItemClickListener(itemClickListener);
			}
			if(0x112 == msg.what){
				tv_bagcoin.setText(""+bag.getCoinInt());
			}
		}
		
	}
	private class ShopAdapter extends SimpleAdapter{

		/* (non-Javadoc)
		 * @see android.widget.SimpleAdapter#getView(int, android.view.View, android.view.ViewGroup)
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			final int pos = position;
			
			View v = super.getView(position, convertView, parent);
			ImageButton lv_btnBuy = (ImageButton)v.findViewById(R.id.btn_buyitem);
			final TextView lv_bagStore= (TextView)v.findViewById(R.id.tv_bagstore);
			lv_btnBuy.setOnClickListener(new Button.OnClickListener(){

				@Override
				public void onClick(View v) {		
				// TODO Auto-generated method stub
					if(false == bag.buyItem(ShopActivity.this, item[pos], 1)){
						Toast.makeText(ShopActivity.this, tipString, Toast.LENGTH_SHORT).show();
					}
					lv_bagStore.setText(bag_exist + bag.getItem_to_num().get(item[pos].getItem_id()));
					handler.sendEmptyMessage(0x112);
				}
				
			});

			return v;
		}
		
		public ShopAdapter(Context context,
				List<? extends Map<String, ?>> data, int resource,
				String[] from, int[] to) {
			super(context, data, resource, from, to);
			// TODO Auto-generated constructor stub
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop);
		handler = new MyHandler();
		bag  = new Bag(ShopActivity.this);
		
		speedupString = ShopActivity.this.getResources().getString(shitGame.shitgame01.R.string.speed_up);
		speeddownString = ShopActivity.this.getResources().getString(shitGame.shitgame01.R.string.speed_down);
	    shieldString = ShopActivity.this.getResources().getString(shitGame.shitgame01.R.string.shield);
		angelString = ShopActivity.this.getResources().getString(shitGame.shitgame01.R.string.angel);
		roundString = ShopActivity.this.getResources().getString(shitGame.shitgame01.R.string.round);
		starString = ShopActivity.this.getResources().getString(shitGame.shitgame01.R.string.star);
		hexagonString =ShopActivity.this.getResources().getString(shitGame.shitgame01.R.string.hexagon);
		coinString = ShopActivity.this.getResources().getString(shitGame.shitgame01.R.string.coin);
		lv_shopitem = (ListView)findViewById(R.id.lv_shop);
		
		iv_desc = (ImageView)findViewById(R.id.iv_imagedesc);
		tv_desc = (TextView)findViewById(R.id.tv_longdesc);
		tv_bagcoin = (TextView)findViewById(R.id.tv_bagcoin);
		tv_bagcoin.setText(""+bag.getCoinInt());
		btn_itemBlock = (ImageButton)findViewById(R.id.btn_itemblock);
		btn_roleBlock = (ImageButton)findViewById(R.id.btn_roleblock);
		
		lv_shopitem.setOnItemClickListener(itemClickListener);
		btn_itemBlock.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				btn_itemBlock.setEnabled(false);
				btn_roleBlock.setEnabled(true);
				handler.sendEmptyMessage(0x111);
				return false;
			}
		});
		
		btn_roleBlock.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				btn_roleBlock.setEnabled(false);
				btn_itemBlock.setEnabled(true);
				handler.sendEmptyMessage(0x110);
				return false;
			}
		});
		CreateThread createThread = new CreateThread();
		createThread.start();
		
	}
	private class CreateThread extends Thread{

		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			createAndShowListView();
		}
	
		
	}
	public void createAndShowListView(){
		
		Context context = ShopActivity.this;
		item_data.clear();
		 String[] itemIdList = new String[]{
					speedupString,
					speeddownString,
					shieldString,
					angelString,
					
				};
		 String[] roleIdList = new String[]{
				roundString,
				starString,
				hexagonString
		 };
		for(int i = 0;i < item_num;i ++){
			item[i] = new Item(context,itemIdList[i]);
		}

		for(int i =0; i< item_num; i++){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("img", item[i].getDrawableId());
			map.put("desc", "\t\t"+item[i].getItemShort());
			map.put("exist_num",bag_exist+bag.getItem_to_num().get(item[i].getItem_id()) );
			map.put("price", ""+item[i].getCoin());
			item_data.add(map);
		}
		for(int i = 0;i < role_num;i ++){
			role[i] = new Item(context,roleIdList[i]);
		}
		for(int i = 0;i < role_num;i ++){
			HashMap<String, Object> map = new HashMap<String,Object>();
			map.put("img", role[i].getDrawableId());
			map.put("desc", "\t\t"+role[i].getItemShort());
			map.put("exist_num",bag_exist+bag.getItem_to_num().get(role[i].getItem_id()) );
			map.put("price", ""+role[i].getCoin());
			role_data.add(map);
		}
		//SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.listitem_shop, new String[]{"img","desc"}, new int[]{R.id.iv_listshop,R.id.tv_desc_listshop});
		item_shopAdapter = new ShopAdapter(this, item_data, R.layout.layout_itemlist, new String[]{"img","desc","exist_num","price"}, new int[]{R.id.iv_imagedesc,R.id.tv_shortdesc,R.id.tv_bagstore,R.id.tv_price});
		role_shopAdapter = new ShopAdapter(this, role_data, R.layout.layout_itemlist, new String[]{"img","desc","exist_num","price"}, new int[]{R.id.iv_imagedesc,R.id.tv_shortdesc,R.id.tv_bagstore,R.id.tv_price});
		handler.sendEmptyMessage(0x111);
	}

	
}
