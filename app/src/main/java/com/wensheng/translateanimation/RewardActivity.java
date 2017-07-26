package com.wensheng.translateanimation;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
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
    private LinearLayout rl_prompt_box;
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
        rl_prompt_box = (LinearLayout) findViewById(R.id.rl_prompt_box);

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
            public void onTextChanged(final CharSequence s, int start, int before, int count) {

                if (TextUtils.isEmpty(s)) {
                    tv_modify.setVisibility(View.VISIBLE);
                    tv_money.setText("0.00");
                    tv_reward.setEnabled(false);
                    return;
                } else {
                    tv_modify.setVisibility(View.GONE);
                }
                if (s.toString().trim().startsWith(".")) {
                    et_input.setText("0" + s.toString().trim());
                    tv_money.setText("0" + s.toString().trim());
                } else if (s.toString().trim().startsWith("0") && s.toString().trim().length() > 1 && !s.toString().trim().contains(".")) {
                    et_input.setText(s.toString().substring(1, s.toString().trim().length()));
                    tv_money.setText(s.toString().substring(1, s.toString().trim().length()));
                } else {
                    if (Float.parseFloat(s.toString().trim()) > 2000) {
                        tv_money.setText(s.toString().trim());
                        tv_reward.setEnabled(false);
                        showOrHindeAnimation(true);
                    } else if (Float.parseFloat(s.toString().trim()) == 2000) {

                    } else {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv_money.setText(s.toString());
                                    }
                                });
                            }
                        }, 100);
                        showOrHindeAnimation(false);
                        tv_reward.setEnabled(true);
                    }

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
     * 提示框的动画
     *
     * @param showOrHinde 显示与隐藏
     */
    public void showOrHindeAnimation(boolean showOrHinde) {
        if (showOrHinde) {
            if (rl_prompt_box.getVisibility() == View.VISIBLE) {
                return;
            } else {
                rl_prompt_box.setVisibility(View.VISIBLE);
            }
        } else {
            if (rl_prompt_box.getVisibility() != View.VISIBLE) {
                return;
            }
        }
        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, showOrHinde ? 0.0f : 1.0f, Animation.RELATIVE_TO_SELF, showOrHinde ? 1.0f : 0.0f
        );
        animation.setDuration(500);
        animation.setFillAfter(true);
        rl_prompt_box.startAnimation(animation);

        if (!showOrHinde) {
            rl_prompt_box.setVisibility(View.GONE);
        }
    }

}
