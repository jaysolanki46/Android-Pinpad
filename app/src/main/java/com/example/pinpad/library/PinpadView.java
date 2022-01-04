package com.example.pinpad.library;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.pinpad.R;

import java.util.ArrayList;
import java.util.List;

public class PinpadView extends LinearLayout {

    private List<TextView> numberKeys;
    private ImageView buttonBackspace;
    private TextView buttonConfirm;
    private PinpadViewListener listener = null;

    public PinpadView(@NonNull Context context) {this(context, null);}

    public PinpadView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(getContext(), R.layout.layout_pinpad_view, this);
        init();
    }

    public void setListener(PinpadViewListener listener) {
        this.listener = listener;
    }

    private void init() {

        numberKeys = new ArrayList<TextView>();
        numberKeys.add(findViewById(R.id.number1));
        numberKeys.add(findViewById(R.id.number2));
        numberKeys.add(findViewById(R.id.number3));
        numberKeys.add(findViewById(R.id.number4));
        numberKeys.add(findViewById(R.id.number5));
        numberKeys.add(findViewById(R.id.number6));
        numberKeys.add(findViewById(R.id.number7));
        numberKeys.add(findViewById(R.id.number8));
        numberKeys.add(findViewById(R.id.number9));
        numberKeys.add(findViewById(R.id.number0));

        buttonBackspace = findViewById(R.id.numberBackspace);
        buttonConfirm = findViewById(R.id.buttonConfirm);

        setUpListeners();
    }

    private void setUpListeners() {

        for (TextView num: numberKeys) {
            num.setOnClickListener(i-> {listener.onClickedNumber(Integer.valueOf(i.getTag().toString()));});
        }

        buttonBackspace.setOnClickListener(i-> {listener.onClickedBackspace();});
        buttonConfirm.setOnClickListener(i-> {listener.onClickedConfirm();});
    }
}
