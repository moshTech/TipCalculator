package com.mosh.tipcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mosh.tipcalculator.databinding.ActivityMainBinding;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {
//    ActivityMainBinding binding;

    // Butterknife for the variables
    @BindView(R.id.etBillAmount)
    EditText etBillAmount;
    @BindView(R.id.tvTipPercent)
    TextView tvTipPercent;
    @BindView(R.id.tvTipAmount)
    TextView tvTipAmount;
    @BindView(R.id.tvBillTotalAmount)
    TextView tvBillTotalAmount;

    // Default variables
    float percentage = 0;
    float tipTotal = 0;
    float finalBilAmt = 0;

    float totalBillAmt = 0;

    // Default values fo each ImageButton
    float regTipPercentage = 10;
    float goodTipPercentage = 15;
    float excelTipPercentage = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//       binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setContentView(R.layout.activity_main);

        // Butterknife injection
        ButterKnife.bind(this);

        setTipValues();
    }

    // Setting values
    private void setTipValues() {
        tvTipPercent.setText(getString(R.string.msg_tipPercent, percentage));
        tvTipAmount.setText(getString(R.string.msg_tipTotal, tipTotal));
        tvBillTotalAmount.setText(getString(R.string.msg_billTotalResult, finalBilAmt));
    }

    // Butterknife onClick data binding
    @OnClick({R.id.ibRegularService, R.id.ibGoodService, R.id.ibExcellentService})
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.ibRegularService:
                percentage = regTipPercentage;
                break;
            case R.id.ibGoodService:
                percentage = goodTipPercentage;
                break;
            case R.id.ibExcellentService:
                percentage = excelTipPercentage;
                break;
        }
        calculateFinalBill();
        setTipValues();

    }

    // Butterknife for EditText on textChanged
    @OnTextChanged(R.id.etBillAmount)
    public void onTextChanged() {
        calculateFinalBill();
        setTipValues();
    }

    // Calculation handled
    private void calculateFinalBill() {
        if (percentage == 0)
            percentage = goodTipPercentage;

        // For the input not to be empty and not "."
        if (!etBillAmount.getText().toString().equals("")  && !etBillAmount.getText().toString().equals("."))
            totalBillAmt = Float.valueOf(etBillAmount.getText().toString());
        else
            totalBillAmt = 0;

        tipTotal = (totalBillAmt * percentage) / 100;
        finalBilAmt = totalBillAmt + tipTotal;
    }
}
