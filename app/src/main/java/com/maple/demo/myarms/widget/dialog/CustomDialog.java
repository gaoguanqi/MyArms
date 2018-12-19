package com.maple.demo.myarms.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.jess.arms.utils.ArmsUtils;
import com.maple.demo.myarms.R;

/**
 * author: gaogq
 * time: 2018/11/7 14:42
 * description:
 */
public class CustomDialog extends Dialog {

    private Context context;             //上下文
    private int height, width;           //宽高
    private boolean isCancel;      //是否点击dialog外面背景取消dialog
    private View view;                   //自定义dialog布局View
    private int anim;                 //进出动画
    private int gravity;            //显示位置

    private CustomDialog(Builder builder) {
        super(builder.context);
        context = builder.context;
        height = builder.height;
        width = builder.width;
        anim = builder.anim;
        gravity = builder.gravity;
        isCancel = builder.isCancel;
        view = builder.view;
    }


    private CustomDialog(Builder builder, int style) {
        super(builder.context, style);
        context = builder.context;
        height = builder.height;
        width = builder.width;
        anim = builder.anim;
        gravity = builder.gravity;
        isCancel = builder.isCancel;
        view = builder.view;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(view);
        setCanceledOnTouchOutside(isCancel);

        if (anim ==-1){
            anim = R.style.dialog_center_anim;
        }

        Window win = getWindow();
        if(win != null){
            win.setWindowAnimations(anim);
            WindowManager.LayoutParams lp = win.getAttributes();
            lp.gravity = gravity;
            if (width==-1){
                DisplayMetrics dm = context.getResources().getDisplayMetrics();
                width = (int)(dm.widthPixels * 0.7);//默认宽度设置为屏幕的0.7
                height = WindowManager.LayoutParams.WRAP_CONTENT;//默认高度为包裹内容
            }
            lp.height = height;
            lp.width = width;
            win.setAttributes(lp);
        }
    }

    public static class Builder {
        private Context context;
        private int height, width = -1;
        private boolean isCancel = false;
        private View view;
        private int style = -1;
        private int anim = -1;
        private int gravity = Gravity.CENTER;//默认居中

        public Builder(Activity context) {
            this.context = context;
        }
        public Builder view(int resView) {
            view = LayoutInflater.from(context).inflate(resView, null);
            return this;
        }

        public Builder setHeightPX(int val) {
            height = val;
            return this;
        }

        public Builder setWidthPX(int val) {
            width = val;
            return this;
        }

        public Builder setHeightDP(int val) {
            height = ArmsUtils.dip2px(context, val);
            return this;
        }

        public Builder setWidthDP(int val) {
            width = ArmsUtils.dip2px(context, val);
            return this;
        }

        public Builder setHeightDimenRes(int dimenRes) {
            height = context.getResources().getDimensionPixelOffset(dimenRes);
            return this;
        }

        public Builder setWidthDimenRes(int dimenRes) {
            width = context.getResources().getDimensionPixelOffset(dimenRes);
            return this;
        }


        public Builder setStyle(int style) {
            this.style = style;
            return this;
        }

        public Builder setAnim(int anim) {
            this.anim = anim;
            return this;
        }

        public Builder setGravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public Builder setIsCancel(boolean isCancel) {
            isCancel = isCancel;
            return this;
        }

        public Builder addViewOnclick(int viewRes,View.OnClickListener listener){
            view.findViewById(viewRes).setOnClickListener(listener);
            return this;
        }

        public Builder setTextView(int viewRes,String s){
            ((TextView)view.findViewById(viewRes)).setText(s);
            return this;
        }

        public Builder setTextView(int viewRes,SpannableString s){
            ((TextView)view.findViewById(viewRes)).setText(s);
            return this;
        }

        public CustomDialog build() {
            if (style != -1) {
                return new CustomDialog(this, style);
            } else {
                return new CustomDialog(this,R.style.BaseDialog);
            }
        }
    }


    public View getDialogView(){
        return view;
    }

    /**拿到子View*/
    public <E extends View> E findView(int viewId) {
        if (view != null) {
            E v = (E) view.findViewById(viewId);
            return v;
        }
        return null;
    }
}
