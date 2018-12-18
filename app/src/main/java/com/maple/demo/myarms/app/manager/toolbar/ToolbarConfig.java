package com.maple.demo.myarms.app.manager.toolbar;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.maple.demo.myarms.R;

/**
 * author: gaogq
 * time: 2018/12/17 17:06
 * description: 全局title的配置，一般情况下 APP 的title 应该是统一的。
 */
public class ToolbarConfig {

    /**
     * 是否显示标题
     */
    public boolean hasTitle;
    /**
     * 标题
     */
    public String title;
    /**
     * 是否显示左侧的TextView
     */
    public boolean hasBackText;
    /**
     * 是否显示左侧的按钮
     */
    public boolean hasBack;
    /**
     * 是否显示右侧的TextView
     */
    public boolean hasSettingText;
    /**
     * 是否显示右侧的按钮
     */
    public boolean hasSetting;

    /**
     * 右侧的TextView文字
     */
    public String backText;
    /**
     * 左侧的TextView文字
     */
    public String settingText;

    /**
     * 左右侧的监听，如果开启了titleBar，必须设置该监听
     */
    public OnToolbarLitener litener;

    public static Buidler builder() {
        return new Buidler();
    }

    public static final class Buidler {

        private boolean hasTitle = true;
        private String title;
        private boolean hasBackText;
        private boolean hasBack = true;
        private String backText;

        private boolean hasSettingText;
        private boolean hasSetting;
        private String settingText;

        private OnToolbarLitener litener;


        private Buidler() {
        }

        public Buidler setTitle(String title) {
            this.title = title;
            return this;
        }

        public Buidler setHasTitle(boolean hasTitle) {
            this.hasTitle = hasTitle;
            return this;
        }

        public Buidler setHasBackText(boolean hasBackText) {
            this.hasBackText = hasBackText;
            return this;
        }

        public Buidler setHasBack(boolean hasBack) {
            this.hasBack = hasBack;
            return this;
        }

        public Buidler setBackText(String backText) {
            this.backText = backText;
            return this;
        }


        public Buidler setHasSettingText(boolean hasSettingText) {
            this.hasSettingText = hasSettingText;
            return this;
        }

        public Buidler setHasSetting(boolean hasSetting) {
            this.hasSetting = hasSetting;
            return this;
        }

        public Buidler setSettingText(String settingText) {
            this.settingText = settingText;
            return this;
        }

        public Buidler setToolbarLitener(OnToolbarLitener litener) {
            this.litener = litener;
            return this;
        }

        public ToolbarConfig build() {
            if (litener == null) {
                throw new IllegalStateException("toobar litener is null,（如果开启了titleBar，就必须设置监听）");
            }
            return new ToolbarConfig(this);
        }
    }

    private ToolbarConfig(Buidler builder) {
        this.hasTitle = builder.hasTitle;
        this.title = builder.title;
        this.hasBackText = builder.hasBackText;
        this.hasBack = builder.hasBack;
        this.backText = builder.backText;
        this.hasSettingText = builder.hasSettingText;
        this.hasSetting = builder.hasSetting;
        this.settingText = builder.settingText;
        this.litener = builder.litener;
    }


    public interface OnToolbarLitener {
        void onToolbarClick(int id);
    }
}
