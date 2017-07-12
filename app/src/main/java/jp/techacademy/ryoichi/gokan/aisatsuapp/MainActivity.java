package jp.techacademy.ryoichi.gokan.aisatsuapp;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button) findViewById(R.id.button1);
        // イベントリスナーの設定　引数にはonClickメソッドを実装したクリックリスナークラスを設定する
        button1.setOnClickListener(this);
        mTextView = (TextView) findViewById(R.id.textView);

    }

    @Override
    public void onClick(View v) {
        Log.d("UI_PARTS", "ボタンをタップしました。");

        // ボタンを押されたら、TimePickerDoialogを表示する
        if (v.getId() == R.id.button1) {
            showTimePickerDialog();
        }
    }

    private void showTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Log.d("Aisatsu", String.valueOf(hourOfDay) + ":" + String.valueOf(minute));

                        /* 2:00 - 9:59でおはよう
                           10:00 - 17:59でこんにちは
                           18:00 - 1:59でこんばんは */
                        if ((hourOfDay >= 2 && minute >= 0) && (hourOfDay <= 9 && minute <= 59)) {
                            Log.d("Aisatsu", "おはよう");
                            mTextView.setText("おはよう");
                        } else if ((hourOfDay >= 10 && minute >= 0) && (hourOfDay <= 17 && minute <= 59)) {
                            Log.d("Aisatsu", "こんにちは");
                            mTextView.setText("こんにちは");
                        } else if ((hourOfDay >= 18 && minute >= 0) && (hourOfDay <= 23 && minute <= 59)) {
                            Log.d("Aisatsu", "こんばんは");
                            mTextView.setText("こんばんは");
                        } else if ((hourOfDay >= 0 && minute >= 0) && (hourOfDay <= 1 && minute <= 59)) {
                            Log.d("Aisatsu", "こんばんは");
                            mTextView.setText("こんばんは");
                        }
                    }
                },
                13, // 初期値(時間)
                0,  // 初期値(分)
                true); // true 24時間表記, false 午前, 午後を選択する形
        timePickerDialog.show(); // TimePickerDialogを表示
    }
}
