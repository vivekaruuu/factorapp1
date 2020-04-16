package com.example.factorgame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button option1;
    Button option2;
    Button option3;
    int index;
    int number;
    TextView textView;
    public void onClickGenerate(View view){
        try {
            textView=findViewById(R.id.textView);
            option1.setBackgroundColor(Color.WHITE);
            option2.setBackgroundColor(Color.WHITE);
            option3.setBackgroundColor(Color.WHITE);
            textView.setText("Select correct factor");
            EditText editText = findViewById(R.id.editText);

            int value = Integer.parseInt(editText.getText().toString());
            ArrayList<Integer> intArr = new ArrayList<Integer>();
            for (int k =1 ; k <= value / 2; k++) {
                if (value % k == 0) {
                    intArr.add(k);

                }
            }

            intArr.add(value);
            Log.i("arr", String.valueOf(intArr));
            Random random = new Random();
            index = random.nextInt(3);
             number = intArr.get(random.nextInt(intArr.size()));
            ArrayList<Integer> arrayFinal = new ArrayList<Integer>();
            for (int k = 0; k < 3; k++) {
                if (k == index)
                    arrayFinal.add(number);
                else {
                    if(k==1){
                        int temp=getRandomWithExclusion(random, 1, value, intArr);
                        while (temp==arrayFinal.get(0)) {
                            temp = getRandomWithExclusion(random, 1, value, intArr);
                        }
                        arrayFinal.add(temp);
                    }
                    else {

                        if (k == 2) {
                            int temp = getRandomWithExclusion(random, 1, value, intArr);
                            while (temp == arrayFinal.get(0) || temp == arrayFinal.get(1)) {
                                temp = getRandomWithExclusion(random, 1, value, intArr);
                            }
                            arrayFinal.add(temp);
                        }
                        else {
                            arrayFinal.add(getRandomWithExclusion(random, 1, value, intArr));
                        }
                    }
                }
            }
            option1.setText((arrayFinal.get(0)).toString());
            option2.setText((arrayFinal.get(1)).toString());
            option3.setText((arrayFinal.get(2)).toString());
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this,"please enter number first",Toast.LENGTH_SHORT).show();
        }
    }

    public void onClick(View view){
        Button button=findViewById(view.getId());
         textView=findViewById(R.id.textView);
        if((view.getTag().toString()).equals(String.valueOf(index))) {
            textView.setText("Correct!");
            button.setBackgroundColor(Color.GREEN);

        }
        else {
            textView.setText("Incorrect,Answer is "+String.valueOf(number));
            button.setBackgroundColor(Color.RED);
        }
    }
    public int getRandomWithExclusion(Random rnd, int start, int end, ArrayList<Integer> exclude) {
        int random = start + rnd.nextInt(end - start + 1 - exclude.size());
        for (int ex : exclude) {
            if (random < ex) {
                break;
            }
            random++;
        }
        return random;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        option1=(Button)findViewById(R.id.option1);
        option2=(Button)findViewById(R.id.option2);
        option3=(Button)findViewById(R.id.option3);



    }
}
