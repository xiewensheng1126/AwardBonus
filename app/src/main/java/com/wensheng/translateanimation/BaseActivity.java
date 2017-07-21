package com.wensheng.translateanimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by xiewensheng on 2017/7/21.
 */

public class BaseActivity extends Activity {

    public RelativeLayout rl_title_back;
    public TextView tv_title_center;
    public TextView tv_title_right;
    public ImageView iv_title_right;

    public RelativeLayout rl_title_right;
    public LinearLayout ll_heard_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     * 设置默认的  tilte  如果要自定义头布局调用setBaseHeadView（）；
     *
     * @param titleCenter        标题的名字
     * @param isRightPicOrTvOrNo 右侧显示的是文字图片还是没有 0：代表没有 1.文字 2.图片
     * @param titleTvRight       右侧的文字
     * @param titleRightIvRes    右侧的图片
     * @param rightClick         右侧的点击
     */
    public void setCusTitleBar(String titleCenter, int isRightPicOrTvOrNo, String titleTvRight, int titleRightIvRes, View.OnClickListener rightClick) {
        rl_title_back = (RelativeLayout) findViewById(R.id.rl_title_back);
        tv_title_center = (TextView) findViewById(R.id.tv_title_center);
        tv_title_right = (TextView) findViewById(R.id.tv_title_right);
        iv_title_right = (ImageView) findViewById(R.id.iv_title_right);
        rl_title_right = (RelativeLayout) findViewById(R.id.rl_title_right);
        ll_heard_title = (LinearLayout) findViewById(R.id.ll_heard_title);

        if (!StringUtils.isBlank(titleCenter)) {
            tv_title_center.setText(titleCenter);
        }

        if (isRightPicOrTvOrNo == 0) {
            tv_title_right.setVisibility(View.GONE);
            iv_title_right.setVisibility(View.GONE);
        } else if (isRightPicOrTvOrNo == 1) {
            tv_title_right.setVisibility(View.VISIBLE);
            iv_title_right.setVisibility(View.GONE);
            if (!StringUtils.isBlank(titleTvRight)) {
                tv_title_right.setText(titleTvRight);
            }
            rl_title_right.setOnClickListener(rightClick);
        } else if (isRightPicOrTvOrNo == 2) {
            tv_title_right.setVisibility(View.GONE);
            iv_title_right.setVisibility(View.VISIBLE);
            rl_title_right.setOnClickListener(rightClick);
            iv_title_right.setBackgroundResource(titleRightIvRes);
        }
        rl_title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
