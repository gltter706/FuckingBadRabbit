package shitGame.shitgame01.planeboss;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import shitGame.shitgame01.R;
import shitGame.shitgame01.R.string;
import shitGame.shitgame01.activities.LoseActivity;
import shitGame.shitgame01.activities.StartActivity;
import shitGame.shitgame01.activities.WinActivity;
import shitGame.shitgame01.utils.Bag;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class PlaneSurfaceView extends SurfaceView implements Callback {
	private final float ROLEH = 20;
	private final float ROLEW = 20;
	private ArrayList<UFO> al_ufo = new ArrayList<UFO>();
	private Background background;
	private Matrix backgroundMatrix;
	private float launcherW;
	private float launcherH;
	private float planeW;
	private float planeH;
	private float roleW;
	private float roleH;
	private MyThread thread;
	private Canvas canvas;
	private SurfaceHolder sfhHolder;
	private Paint paint;
	private Context context;
	private int height;
	private int width;
	private Coordinate beginTouch;
	private Coordinate endTouch;
	private double angle;
	private double power;
	private Protagonist protagonist;
	private boolean flag;
	private boolean release = false;
	private final int stamp = 50;
	private int PRO_INIT_X = 0;
	private int PRO_INIT_Y;
	private Bitmap proBitmap;
	private Matrix proMatrix;
	private Bitmap boomBitmap;
	private Matrix boomMatrix;
	private Bitmap launcherBitmap;
	private Matrix launcherMatrix;
	private long TIMEOUT = 60000;
	private int TARGET = 30;
	private int bonus;
	
	private void drawLauncher(Canvas canvas,Paint paint){
		canvas.drawBitmap(launcherBitmap, 0, height - launcherBitmap.getHeight(), paint);
	}
	//show the current record and target record
	private void drawRecord(Canvas canvas,Paint paint){
		int txtWidth = (int) (width*0.8);
		int txtHeight = (int) (height * 0.1);
		paint.setTypeface(Typeface.MONOSPACE);
		paint.setTextSize(25);
		if(bonus < TARGET){
			paint.setColor(Color.rgb(200, 0, 0));
			canvas.drawText(bonus+" : "+TARGET, txtWidth,txtHeight , paint);
		}
		else{
			paint.setColor(Color.rgb(0, 140, 0));
			canvas.drawText(bonus+" : "+TARGET, txtWidth,txtHeight , paint);
		}
		paint.reset();
	}
	private void drawTime(Canvas canvas,Paint paint,long timeOut){
		int second;
		int mil_second;
		Calendar date;
		int txtWidth = (int) (width*0.9);
		int txtHeight = (int) (height*0.15 );
		
		date = Calendar.getInstance();
		date.setTimeInMillis(timeOut);
		
		second = date.get(Calendar.SECOND);
	    mil_second = date.get(Calendar.MILLISECOND);
	    mil_second *= 0.01;
		canvas.drawText(second+" : "+mil_second, txtWidth, txtHeight, paint);
	}
	//draw something who could fly
	private void drawFlyAbs(FlyAbs flyAbs, Canvas canvas, float w, float h,Paint paint) {
		float locx = flyAbs.getLoc_x();
		float locy = flyAbs.getLoc_y();
		canvas.clipRect(new RectF(locx, locy, locx + w, locy + h));
		canvas.drawBitmap(flyAbs.getFigure(), locx, locy, null);
		canvas.restore();
	}
	//draw the aiming line
	private void drawLine(Canvas canvas, Paint paint) {
		paint.setColor(Color.BLACK);
		paint.setStyle(Paint.Style.STROKE);
		PathEffect effects = new DashPathEffect(new float[] { 5, 5, 5, 5 }, 1);
		paint.setPathEffect(effects);
		canvas.drawLine(beginTouch.x, beginTouch.y, endTouch.x, endTouch.y,
				paint);
		paint.reset();
	}

	public PlaneSurfaceView(Context context) {
		super(context);
		this.context = context;
		angle = Math.PI / 2;
		power = 600;
		beginTouch = new Coordinate(0, 0);
		sfhHolder = this.getHolder();
		sfhHolder.addCallback(this);
		paint = new Paint();
		paint.setColor(Color.RED);
		bonus = 0;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		this.width = getWidth();
		this.height = getHeight();

		planeH = height / 20;
		planeW = width / 20;
		launcherW = height /10;
		launcherH = width/10;

		roleH = ROLEH;
		roleW = ROLEW;

		//init all the bitmap
		boomBitmap = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.ic_ufo_boom);
		boomMatrix = new Matrix();
		boomMatrix.postScale(planeW / boomBitmap.getWidth(),
				planeH / boomBitmap.getHeight());
		genUfo();

		//init background
		background = new Background(BitmapFactory.decodeResource(
				context.getResources(), R.drawable.gaming_bg1_01), 0, 0);
		backgroundMatrix = background.getMatrix();
		backgroundMatrix.postScale(width / background.getWidth(), height
				/ background.getHeight());
		background.setBackgrpBitmap(Bitmap.createBitmap(
				background.getBackgrpBitmap(), 0, 0,
				(int) background.getWidth(), (int) background.getHeight(),
				backgroundMatrix, true));
		launcherBitmap = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.lic_auncher);
		launcherMatrix = new Matrix();
		launcherMatrix.postScale((float)0.2,(float)0.2);
	
		launcherBitmap = Bitmap.createBitmap(launcherBitmap, 0, 0, 
				launcherBitmap.getWidth(),launcherBitmap.getHeight(),launcherMatrix,true);
		
		proBitmap = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.square);
		proMatrix = new Matrix();
		proMatrix.postScale(roleW / proBitmap.getWidth(),
				roleH / proBitmap.getHeight());
		proBitmap = Bitmap.createBitmap(proBitmap, 0, 0, proBitmap.getWidth(),
				proBitmap.getHeight(), proMatrix, true);
		PRO_INIT_X = (int) ( launcherBitmap.getWidth()/2 + proBitmap.getWidth()/2);
		PRO_INIT_Y = (int) (height - launcherBitmap.getHeight()/2 - launcherBitmap.getHeight()/2);
		endTouch = new Coordinate(PRO_INIT_X + proBitmap.getWidth()/2,PRO_INIT_Y + proBitmap.getHeight()/2 );
		protagonist = new Protagonist(new ProFlyBehaviour(), PRO_INIT_X,
				PRO_INIT_Y, proBitmap);
		//start game
		flag = true;
		thread = new MyThread();
		thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		flag = false;
	}

	//generate ufo
	private void genUfo() {
		FlyBehaviour flyBehaviour;

		int locx = (int) (Math.random() * 2);
		if (1 == locx) {
			locx = 0;
			flyBehaviour = new LeftUFOFlyBehaviour();
		} else {
			locx = width;
			flyBehaviour = new RightUFOFlyBehaviour();
		}

		int locy = (int) (Math.random() * height);
		if (height / 4 < locy) {
			locy = locy - height / 4;
		}
		Bitmap ufoBitmap = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.ic_ufo);
		Matrix ufoMatrix = new Matrix();
		ufoMatrix.postScale(planeW / ufoBitmap.getWidth(),
				planeH / ufoBitmap.getHeight());
		ufoBitmap = Bitmap.createBitmap(ufoBitmap, 0, 0, ufoBitmap.getWidth(),
				ufoBitmap.getHeight(), ufoMatrix, true);
		al_ufo.add( new UFO(flyBehaviour, locx, locy, ufoBitmap));
	}

	private void logic() {
		//reset protagonist position
		if (protagonist.getLoc_x() > width * 1.2 || protagonist.getLoc_y() < -height * 0.1) {
			release = false;
			protagonist.setLoc_x(PRO_INIT_X);
			protagonist.setLoc_y(PRO_INIT_Y);
		}
		
		//ufo flys
		for(int i = 0;i != al_ufo.size();i ++){
			al_ufo.get(i).fly();
		}
		
		//shoot the protagonist
		if (release) {
			if (protagonist.getLoc_x() == PRO_INIT_X
					&& PRO_INIT_Y == protagonist.getLoc_y()) {
				protagonist.setSpeed((int) power / 5);
			}
			protagonist.fly(angle, height);
		}
		for (int i = 0; i != al_ufo.size(); i++) {
			UFO tmp = al_ufo.get(i);
			if (tmp.getLoc_x() > width * 1.2 || tmp.getLoc_x() < -width * 0.2) {
				al_ufo.remove(i);
				genUfo();
			}
		}

		//do the bumping check
		for (int i = 0; i != al_ufo.size(); i++) {
			UFO tmp = al_ufo.get(i);
			if (true == bumpWithRect(
					new RectF(tmp.getLoc_x(), tmp.getLoc_y(), tmp.getLoc_x()
							+ tmp.getWidth(), tmp.getLoc_y() + tmp.getHeight()),
					new RectF(protagonist.getLoc_x(), protagonist.getLoc_y(),
							protagonist.getLoc_x() + protagonist.getWidth(),
							protagonist.getLoc_y() + protagonist.getHeight()))) {
				release = false;
				protagonist.setLoc_x(PRO_INIT_X);
				protagonist.setLoc_y(PRO_INIT_Y);
				Bitmap tmp_Bitmap = Bitmap.createBitmap(boomBitmap, 0, 0,boomBitmap.getWidth(),
						boomBitmap.getHeight(),boomMatrix,true);
				al_ufo.get(i).Boom(tmp_Bitmap);
				bonus += tmp.getBonus();
				genUfo();
			}
		}
	}

	private void myDraw(long timeOut) {
		canvas = sfhHolder.lockCanvas();
		try {
			if (canvas != null) {
				canvas.drawColor(Color.WHITE);
				canvas.drawBitmap(background.getBackgrpBitmap(), 0, 0, paint);
				drawLauncher(canvas,paint);
				drawTime(canvas, paint, timeOut);
				drawRecord(canvas, paint);
				drawLine(canvas, paint);
				for (int i = 0; i != al_ufo.size(); i++) {
					UFO tmp = al_ufo.get(i);
					if(!tmp.getIsBoom()){
						drawFlyAbs(tmp, canvas, planeW, planeH,paint);
					}
					else{
						drawFlyAbs(tmp, canvas, planeW, planeH,paint);
						if(tmp.Crack())
							al_ufo.remove(i);
					}
				}
				drawFlyAbs(protagonist, canvas, roleW, roleH,paint);
			}
		} catch (Exception e) {
		} finally {
			if (canvas != null) {
				sfhHolder.unlockCanvasAndPost(canvas);
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (PRO_INIT_X == protagonist.getLoc_x()
				&& PRO_INIT_Y == protagonist.getLoc_y()) {
			if (MotionEvent.ACTION_MOVE == event.getAction()) {
				beginTouch.x = event.getX();
				beginTouch.y = event.getY();
			}
			if (MotionEvent.ACTION_UP == event.getAction()) {

				float disX = beginTouch.x - endTouch.x;
				float disY = beginTouch.y - endTouch.y;
				float dis = (float) (Math.pow((disX), 2) + Math.pow(disY, 2));
				power = Math.sqrt(dis);
				angle = Math.acos(disX / power);
				release = true;
			}
		}
		return true;
	}

	public boolean bumpWithRect(RectF rect1, RectF rect2) {
		float x1 = rect1.left, y1 = rect1.top, w1 = rect1.width(), h1 = rect1
				.height();
		float x2 = rect2.left, y2 = rect2.top, w2 = rect2.width(), h2 = rect2
				.height();
		if (x1 >= x2 && x1 >= x2 + w2)
			return false;
		else if (x1 <= x2 && x1 + w1 <= x2)
			return false;
		else if (y1 >= y2 && y1 >= y2 + h2)
			return false;
		else if (y1 <= y2 && y2 >= y1 + h1)
			return false;
		return true;
	}

	private class MyThread extends Thread {
		long start = System.currentTimeMillis();
		long kill = System.currentTimeMillis();
		public void run() {
			while (flag) {
				try {
					long begin = System.currentTimeMillis();
					myDraw(TIMEOUT - (kill-start));
					logic();
					long end = System.currentTimeMillis();
					kill = end;
					if(kill - start > TIMEOUT){
						flag = false;
						Intent data = ((Activity) context).getIntent();
						Bag t_bag = (Bag)data.getSerializableExtra("bag");
						if(bonus >= TARGET){
							Intent intent = new Intent(context,WinActivity.class);
							intent.putExtra("bag", t_bag);
							intent.putExtra("superBonus", bonus);
							context.startActivity(intent);
						}
						else{
							Intent intent = new Intent(context,LoseActivity.class);
							intent.putExtra("bag", t_bag);
							intent.putExtra("superBonus", bonus);
							context.startActivity(intent);
						}
					}
					if (end - begin < stamp) {
						Thread.sleep(stamp - (end - begin));
					}
				} catch (Exception e) {
				}
			}
		}
	}
}
