package com.teamx.bottomnav;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions;
import com.google.firebase.ml.common.modeldownload.FirebaseModelManager;
import com.google.firebase.ml.custom.FirebaseCustomLocalModel;
import com.google.firebase.ml.custom.FirebaseCustomRemoteModel;
import com.google.firebase.ml.custom.FirebaseModelDataType;
import com.google.firebase.ml.custom.FirebaseModelInputOutputOptions;
import com.google.firebase.ml.custom.FirebaseModelInputs;
import com.google.firebase.ml.custom.FirebaseModelInterpreter;
import com.google.firebase.ml.custom.FirebaseModelInterpreterOptions;
import com.google.firebase.ml.custom.FirebaseModelOutputs;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CropDisease extends AppCompatActivity {
    // declarations
    ImageDatabase database;
    private Bitmap imageToStore;
    private static final int PICK_IMAGE_REQUEST = 100;
    private Uri imageFilePath;
    private ImageView imageView;
    Button uploadImage, checkDisease;
    TextView Disease, sol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_disease);
        imageView = (ImageView) findViewById(R.id.imageView7);
        uploadImage = (Button) findViewById(R.id.button2);
        checkDisease = (Button) findViewById(R.id.button);
        Disease = (TextView) findViewById(R.id.diseaseName);
        sol = (TextView) findViewById(R.id.textView27);
        database = new ImageDatabase(this);

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent objectIntent = new Intent();
                    objectIntent.setType("image/*");

                    objectIntent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(objectIntent, PICK_IMAGE_REQUEST);
                } catch (Exception e) {
                    Toast.makeText(CropDisease.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        checkDisease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadModel();
            }
        });
    }
    public void loadModel(){
        // local model
        final FirebaseCustomLocalModel localModel = new FirebaseCustomLocalModel.Builder()
                .setAssetFilePath("converted_model.tflite")
                .build();
        Toast.makeText(CropDisease.this,"load model", Toast.LENGTH_SHORT).show();
        // create interpreter
        FirebaseModelInterpreter interpreter = null;
        try {
            FirebaseModelInterpreterOptions options =
                    new FirebaseModelInterpreterOptions.Builder(localModel).build();
            interpreter = FirebaseModelInterpreter.getInstance(options);
        } catch (FirebaseMLException e) {
            // ...
        }
        // specify input and output

        FirebaseModelInputOutputOptions inputOutputOptions =
                null;
        try {
            inputOutputOptions = new FirebaseModelInputOutputOptions.Builder()
                    .setInputFormat(0, FirebaseModelDataType.FLOAT32, new int[]{1, 224, 224,   3})
                    .setOutputFormat(0, FirebaseModelDataType.FLOAT32, new int[]{1, 38})
                    .build();
            Toast.makeText(CropDisease.this,"Build Model", Toast.LENGTH_SHORT).show();
        } catch (FirebaseMLException e) {
            e.printStackTrace();
        }
        //perform inference on output data
        Bitmap bitmap = getYourInputImage();
        bitmap = Bitmap.createScaledBitmap(bitmap, 224, 224, true);

        int batchNum = 0;
        float[][][][] input = new float[1][224][224][3];
        for (int x = 0; x < 224; x++) {
            for (int y = 0; y < 224; y++) {
                int pixel = bitmap.getPixel(x, y);
                // Normalize channel values to [-1.0, 1.0]. This requirement varies by
                // model. For example, some models might require values to be normalized
                // to the range [0.0, 1.0] instead.
                input[batchNum][x][y][0] = (Color.red(pixel) - 127) / 128.0f;
                input[batchNum][x][y][1] = (Color.green(pixel) - 127) / 128.0f;
                input[batchNum][x][y][2] = (Color.blue(pixel) - 127) / 128.0f;
            }
        }
        //Then, create a FirebaseModelInputs object with your input data, and pass it and the model's input and output specification to the model interpreter's run method:

        FirebaseModelInputs inputs = null;
        try {
            inputs = new FirebaseModelInputs.Builder()
                    .add(input)  // add() as many input arrays as your model requires
                    .build();
            Toast.makeText(CropDisease.this, "Input Added", Toast.LENGTH_SHORT).show();
        } catch (FirebaseMLException e) {
            e.printStackTrace();
        }
        interpreter.run(inputs, inputOutputOptions)
                .addOnSuccessListener(
                        new OnSuccessListener<FirebaseModelOutputs>() {
                            @Override
                            public void onSuccess(FirebaseModelOutputs result) {
                                // ...
                                float[][] output = result.getOutput(0);
                                float[] probabilities = output[0];
                                float[] ans = output[0];

                                try{
                                    JSONObject obj = new JSONObject(loadJSONFromAsset());
                                    float max = probabilities[0];
                                    for (int i = 0; i <= probabilities.length; i++) {
                                        try{
                                            if (probabilities[i] >= max) {
                                                max = probabilities[i];

                                            }
                                        } catch (Exception e) {
                                            break;
                                        }

                                    }
                                    Log.i("Max is", String.valueOf(max));
                                    for (int i = 0; i <= probabilities.length; i++) {
                                        try {
                                            if (ans[i] == max){
                                                if (max > 0.7){
                                                    String dis = obj.getString(String.valueOf(i));
                                                    Log.i("MLKit", String.format("%s: %1.4f", dis, max));
                                                    Disease.setText("Disease Type " + dis + " with accuracy of " + max);
                                                    sol.setText("Good To Go "+ "\n"+"Here's your solution");
                                                }
                                                else{
                                                    String dis = obj.getString(String.valueOf(i));
                                                    Log.i("MLKit", String.format("%s: %1.4f", dis, max));
                                                    Disease.setText("Disease Type " + dis + " with accuracy of " + max);
                                                    sol.setText("Its advisable to ask an expert on this ");
                                                }

                                            }
                                        } catch (Exception e) {
                                            break;
                                        }




                                      //  Arrays.sort(probabilities);
                                       // if (ans[i] == probabilities[probabilities.length-1]) {


                                      //      Log.i("MLKit", String.format("%s: %1.4f", dis, ans[i]));
                                     //   }
                                     //   else{
                                     //       continue;
                                     //   }
                                       // String dis = obj.getString(String.valueOf(probabilities[probabilities.length-1]));
                                       // Log.i("MLKit", String.format("%s: %1.4f", dis, probabilities[probabilities.length - 1]));
                                        //Disease.setText("The name Of The Disease is " + dis + " probabilities " + probabilities[i]);

                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                           //     int ans = result.getOutput(0);
                               /* BufferedReader reader = null;
                                try {
                                    reader = new BufferedReader(
                                            new InputStreamReader(getAssets().open("class_indices.json")));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                for (int i = 0; i < probabilities.length; i++) {
                                    String label = null;
                                    StringBuilder stringBuilder = new StringBuilder();
                                    try {
                                        String line = reader.readLine();
                                        while (line != null){
                                            stringBuilder.append(line).append("\n");
                                            line = reader.readLine();
                                        }
                                        reader.close();
                                        // This responce will have Json Format String
                                        String responce = stringBuilder.toString();
                                        try {
                                            JSONObject jsonObject  = new JSONObject(responce);
                                            String dis = jsonObject.getString(String.valueOf(probabilities));
                                            Disease.setText("The name Of The Disease is " + dis + " probabilities " + probabilities[i]);


                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    Log.i("MLKit", String.format("%s: %1.4f", label, probabilities[i]));
                                }*/
                                    //Toast.makeText(CropDisease.this, "Gotten the String Hopes It works Output", Toast.LENGTH_SHORT).show();


                            }

                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Task failed with an exception
                                // ...
                            }
                        });



    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("class_indices.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public Bitmap getYourInputImage() {
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = (Cursor) db.rawQuery(" SELECT * FROM " + ImageDatabase.TABLE_NAME, null, null);
        if (cursor.moveToLast()) {
            byte[] imgByte = cursor.getBlob(cursor.getColumnIndex(ImageDatabase.KEY_IMG_URL));
            cursor.close();
            return BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            imageFilePath = data.getData();
            //imageToStore = (Bitmap) data.getExtras().get("data"); data.getData();
            try {
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(), imageFilePath);
            } catch (IOException e) {
                Toast.makeText(CropDisease.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageToStore.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            SQLiteDatabase db = database.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(ImageDatabase.KEY_IMG_URL, byteArray);
            db.insert(ImageDatabase.TABLE_NAME, null, values);
            db.close();
            Bitmap b = getYourInputImage();
            imageView.setImageBitmap(b);

        }
    }
}