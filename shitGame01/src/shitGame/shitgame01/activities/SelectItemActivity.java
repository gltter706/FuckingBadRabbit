package shitGame.shitgame01.activities;

import java.util.ArrayList;
import java.util.HashMap;

import shitGame.shitgame01.R;
import android.app.Activity;
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
	public int[] drawable = new int[]{
	R.drawable.bg_selectmissionactivity_1,
	R.drawable.bg_selectmissionactivity_2,
	R.drawable.bg_selectmissionactivity_3,
	R.drawable.btn_selectmissionactivity_pic_lock,
	R.drawable.btn_selectmissionactivity_pic1_1,
	R.drawable.btn_selectmissionactivity_pic1_2,
	R.drawable.btn_selectmissionactivity_pic1_3,
	R.drawable.btn_selectmissionactivity_pic1_4,
	R.drawable.btn_selectmissionactivity_pic1_5};
	public int[] personDrawable = new int[]{
			R.drawable.btn_selectmissionactivity_pic1_1,
			R.drawable.btn_selectmissionactivity_pic1_2,
			R.drawable.btn_selectmissionactivity_pic1_3,
			R.drawable.btn_selectmissionactivity_pic1_4
	};
	public String[] desc = new String[]{"描述1","描述2","描述3",
			"描述4","描述5","描述6","描述7","描述8","描述9"};
	public String[] personDesc = new String[]{"角色描述1",
			"角色描述2","角色描述3","角色描述4"};
	public String[] boughtString = new String[]{"已购买",
			"未购买","未购买","已购买"};
	public String[] amount = new String[]{"数量:1","数量:2",
			"数量:3","数量:4","数量:5","数量:6","数量:7","数量:8",
			"数量:9"};
	private ImageView iv_current = null;
	private ImageView iv_selectitem0 = null;
	private ImageView iv_selectitem1 = null;
	private ImageView iv_selectitem2 = null;
	private ArrayList<HashMap<String,Object>> data = new ArrayList<HashMap<String,Object>>();
	private boolean personSelected = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selectitem);
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
				} else {
				iv_current.setImageResource(personDrawable[arg2]);
				}
			}
			
		});
		
		createAndShowListView(drawable, desc, amount);
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

