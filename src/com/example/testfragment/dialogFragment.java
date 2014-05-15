package com.example.testfragment;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class dialogFragment extends DialogFragment implements OnClickListener {

	Button btnBrowse;
	Button btnLoad;
	EditText editText;
	ImageView imageView;
	Comunicator comunicator;
	String selectedImagePath;
	Uri selectedImageUri;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		/*
		 * con th穫 c籀 th廙�g獺n cho cha �廙θ tuy nhi礙n con th穫 kh繫ng :v
		 */
		comunicator = (Comunicator) activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.dialog, null);
		btnBrowse = (Button) view.findViewById(R.id.btnLoadImage);
		btnLoad = (Button) view.findViewById(R.id.btnloadToListView);
		btnBrowse.setOnClickListener(this);
		btnLoad.setOnClickListener(this);
		editText = (EditText) view.findViewById(R.id.editText1);
		imageView = (ImageView) view.findViewById(R.id.imageView1);

		return view;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnLoadImage:
			Intent intent = new Intent();
			intent.setType("image/*");
			intent.setAction(Intent.ACTION_GET_CONTENT);
			startActivityForResult(
					Intent.createChooser(intent, "Select Picture"), 1111);
			break;

		case R.id.btnloadToListView:
			comunicator.doSomeThing(editText.getText().toString(),
					selectedImageUri);
			dismiss();
			break;
		default:
			break;
		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == Activity.RESULT_OK) {
			if (requestCode == 1111) {
				selectedImageUri = data.getData();
				selectedImagePath = getPath(selectedImageUri);
				Toast.makeText(getActivity(), selectedImagePath, 1).show();
				imageView.setImageURI(selectedImageUri);

			}
		}
	}

	public String getPath(Uri uri) {
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor = getActivity().managedQuery(uri, projection, null, null,
				null);
		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}

}
