package com.wensheng.translateanimation;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 打赏导游 其他金额
 */
public class RewardActivity extends BaseActivity {

    private EditText et_input;
    private TextView tv_reward;
    private TextView tv_money;

    private TextView tv_modify;
    private LinearLayout rl_call_message;
    private LinearLayout ll_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toutist_other_reward);

        setCusTitleBar("发红包", 0, "", 0, null);

        initeView();
        initeListener();
    }

    /**
     * 初始化控件
     */
    protected void initeView() {

        et_input = (EditText) findViewById(R.id.et_input1);
        tv_money = (TextView) findViewById(R.id.tv_money);

        tv_modify = (TextView) findViewById(R.id.tv_modify);
        rl_call_message = (LinearLayout) findViewById(R.id.rl_call_message);

        InputFilter[] filters = {new CashierInputFilter()};
        et_input.setFilters(filters);

        tv_reward = (TextView) findViewById(R.id.tv_reward);

        ll_main = (LinearLayout) findViewById(R.id.ll_main);
        tv_reward.setClickable(true);

    }


    /**
     * View监听
     */
    public void initeListener() {

        et_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (TextUtils.isEmpty(s)) {
                    tv_modify.setVisibility(View.VISIBLE);
                    tv_money.setText("0.00");
                    tv_reward.setEnabled(false);
                    return;
                } else {
                    tv_modify.setVisibility(View.GONE);
                }
                if (StringUtils.isBlank(s.toString().trim())) {
                    tv_reward.setEnabled(false);
                    tv_money.setText("0.00");
                } else {
                    if (Float.parseFloat(s.toString().trim()) > 2000) {
                        tv_money.setText("2000");
                        showOrHindeAnimation(true);
                    } else {
                        tv_money.setText(s.toString());
                        showOrHindeAnimation(false);
                    }
                    tv_reward.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ll_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_input.clearFocus();
                InputMethodManager imm = (InputMethodManager) RewardActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(et_input.getWindowToken(), 0);
            }
        });
    }

    /**
     * 提示单次打赏的动画
     * @param showOrHinde
     */
    public void showOrHindeAnimation(boolean showOrHinde) {

        if (showOrHinde) {
            if (rl_call_message.getVisibility() == View.VISIBLE) {
                return;
            } else {
                rl_call_message.setVisibility(View.VISIBLE);
            }
        } else {
            if (rl_call_message.getVisibility() != View.VISIBLE) {
                return;
            }
        }

        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, showOrHinde ? 0.0f : 1.0f, Animation.RELATIVE_TO_SELF, showOrHinde ? 1.0f : 0.0f
        );
        animation.setDuration(500);
        animation.setFillAfter(true);
        rl_call_message.startAnimation(animation);

        if (!showOrHinde) {
            rl_call_message.setVisibility(View.GONE);
        }
    }


}
