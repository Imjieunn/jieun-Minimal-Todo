package com.example.avjindersinghsekhon.minimaltodo.AddToDo;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.example.avjindersinghsekhon.minimaltodo.AppDefault.AppDefaultActivity;
import com.example.avjindersinghsekhon.minimaltodo.R;

public class AddToDoActivity extends AppDefaultActivity {
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int contentViewLayoutRes() {
        return R.layout.activity_add_to_do;
    }

    @NonNull
    @Override
    protected Fragment createInitialFragment() {
        return AddToDoFragment.newInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e("IMJE", "requestCode: "+ requestCode);
        Log.e("IMJE", "resultCode: "+ resultCode);

        if (resultCode == RESULT_OK) {
            Uri photoUri = data.getData();
            Log.e("photoUri", photoUri.toString());

            Cursor cursor = getContentResolver().query(photoUri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                String mediaPath = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));
                cursor.close();
                Log.e("경로 확인 >> ", photoUri.toString() + " + " + mediaPath);

                AddToDoFragment addToDoFragment = (AddToDoFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
//                if (addToDoFragment != null) {
//                    addToDoFragment.setImageWithMediaPath(mediaPath);
//                }
            }
        } else {
            Toast.makeText(this, "사진 업로드 실패", Toast.LENGTH_LONG).show();
        }
    }

}

