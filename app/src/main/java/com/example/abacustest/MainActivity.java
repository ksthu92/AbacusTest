package com.example.abacustest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import android.view.View;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.submitButton)
    Button saveButton;
    private ColorAdapter colorAdapter;
    private List<ColorItem> items = new ArrayList<>();
    private FirebaseFirestore db;
    private String color;
    private String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        db = FirebaseFirestore.getInstance();
        recyclerView.setHasFixedSize(true);
        colorAdapter = new ColorAdapter(items);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(colorAdapter);
        addMenuItemsfromJSON();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < items.size(); i++) {
                    Map<String, Object> colorData = new HashMap<>();
                    colorData.put("color", items.get(i).getColorName());
                    colorData.put("value", items.get(i).getColorCode());
                    db.collection("AbacusAndroidTest").document("Entry").collection("Test1").document(items.get(i).getColorName()).set(colorData).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getBaseContext(), "The data is saved", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener(){
                                @Override
                       public void onFailure(@NonNull Exception e){
                            Toast.makeText(getBaseContext(), "Unable to save the data", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void addMenuItemsfromJSON() {
        try {
            String jsonDataString = readJsonDatafromFile();
            JSONArray menuItemsfromJsonArray = new JSONArray(jsonDataString);
            for (int i = 0; i < menuItemsfromJsonArray.length(); ++i) {
                JSONObject menuItemObejct = menuItemsfromJsonArray.getJSONObject(i);
                color = menuItemObejct.getString("color");
                value = menuItemObejct.getString("value");
                ColorItem colorItems = new ColorItem(color, value);
                items.add(colorItems);
            }
        } catch (IOException | JSONException exception) {
            Log.e(MainActivity.class.getName(), "Unable to parse JSON file.", exception);
        }
    }

    private String readJsonDatafromFile() throws IOException {
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();
        try {
            String jsonDataString = null;
            inputStream = getResources().openRawResource(R.raw.jsondata);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((jsonDataString = bufferedReader.readLine()) != null) {
                builder.append(jsonDataString);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return new String(builder);
    }
}

