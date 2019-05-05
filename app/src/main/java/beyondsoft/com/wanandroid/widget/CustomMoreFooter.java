package beyondsoft.com.wanandroid.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhouyou.recyclerview.refresh.BaseMoreFooter;

import beyondsoft.com.wanandroid.R;

/**
 * 自定义有动画效果的加载更多
 */
public class CustomMoreFooter extends BaseMoreFooter {

    private LinearLayout allLayout;
    private TextView mTextView;
    private ImageView mImageView;
    private AnimationDrawable mAnimationDra;

    public CustomMoreFooter(Context context) {
        super(context);
    }

    public CustomMoreFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView() {
        super.initView();//有居中显示功能，如果不需要就去掉super.initView();
        allLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.load_more, null);
        mTextView = (TextView) allLayout.findViewById(R.id.tv_hint);
        mImageView = allLayout.findViewById(R.id.iv_load_more);
        mImageView.setImageResource(R.drawable.clife_refresh_loading);
        mAnimationDra = (AnimationDrawable) mImageView.getDrawable();
        addView(allLayout);
    }

    @Override
    public void setState(int state) {
        super.setState(state);
        //以下是我自定义动画需要用到的状态判断，你可以根据自己需求选择。
        //选择自定义需要处理的状态：STATE_LOADING、STATE_COMPLETE、STATE_NOMORE、STATE_NOMORE
        switch (state) {
            case STATE_LOADING:
                mAnimationDra.start();
                mTextView.setText("努力加载中...");
                this.setVisibility(View.VISIBLE);
                break;
            case STATE_COMPLETE:
                mAnimationDra.stop();
                mTextView.setText("加载完成");
                this.setVisibility(View.GONE);
                break;
            case STATE_NOMORE:
                mTextView.setText("没有更多");
                this.setVisibility(View.GONE);
                break;
        }
    }
}
