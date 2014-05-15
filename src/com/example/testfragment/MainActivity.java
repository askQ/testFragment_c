package com.example.testfragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint({ "ShowToast", "NewApi" })
public class MainActivity extends Activity implements Comunicator {
	private int index = 0; 
	private TableRow tupian;
	private TableRow shanchu; 
	private TableRow word; 
	ImageView imageView;
	TextView textView1;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageView = (ImageView) findViewById(R.id.imageView1);
		tv = (TextView) findViewById(R.id.textView1);

		
		Button btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(getApplicationContext(), "fuck", 1).show();
				dialogFragment dialogFragment = new dialogFragment();
				dialogFragment.show(getFragmentManager(), "hello :)) ");
				tv = (TextView) findViewById(R.id.textView1);

				tupian = (TableRow) findViewById(R.id.tupian); 
				word=(TableRow)findViewById(R.id.word); 
				shanchu = (TableRow) findViewById(R.id.shanchu); 

				//給tablerow view
				tupian.addView(AddImageView(), 300 , 300);
				word.addView(ShowWord(),300,300);
				shanchu.addView(AddButton(), 300 , 50); 
				
				
			}
		});
	}

	protected View AddImageView() {
		
		index++; 
		ImageView btn = new ImageView(this); 
		btn.setId(index);
		btn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		return btn; 
		}

	protected View ShowWord() {

		// TODO Auto-generated method stub
		TextView btn=new TextView(this);
		btn.setId(index);
		btn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//		btn.setText("text not sent here!!!!! "); //這一行是可以顯示文字的
		return btn;
	}

protected View AddButton() { 
	// index1++; 
	Button btn = new Button(this);
	btn.setId(index);
	btn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)); 
	btn.setOnClickListener(new Button.OnClickListener(){ 
		@Override public void onClick(View v) { 
			int i = v.getId() ; 
			shanchu.removeView(v) ;
			tupian.removeView(tupian.findViewById(i)) ; } });
	btn.setText("删除");
	return btn; 
	} 
	@Override
	public void doSomeThing(String text, Uri uri) {
		
		if (uri !=null) {
			imageView.setImageURI(uri);
			//將照片傳進去
			ImageView imageView1 = (ImageView) findViewById(index);
			
			imageView1.setImageURI(uri);
		}
	//	TextView textView1 = (TextView) findViewById(index);
//		textView1.setText(text);
			tv.setText(text);

	}

	
}
