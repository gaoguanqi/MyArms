package com.maple.demo.myarms.app.config;

/**
 * author: gaogq
 * time: 2018/12/14 17:21
 * description:
 */
public interface AppContent {
    /**
     * 需要以键值对持久化到SharePerference文件中的Key常量值
     */
    public static class SaveInfoKey {
        public static final String HASLOGIN = "hasLogin";
        public static final String ACCOUNT_ID = "account_id";

    }

    /**
     * Bundle中需要作为Key传递的常量EXTRA开头
     */
    public static class BundleKey {
        /**
         * 进入AccountActivity界面必须要传入的参数
         */
        public static final String EXTRA_EMAIL = "email";
        public static final String EXTRA_TYPE = "type";
        public static final String EXTRA_ID = "id";




    }

    public static class GlobalValue {

        //解锁钱包成功后告诉上一个界面
        public static final int TAG_UNPDAE = 1;
        public static final int TAG_RETURN = 2;

        //修改密码
        public static final int TAG_RESET = 3;

    }

    /**
     * 存放参与API请求的常量
     */
    public static class ApiParams {

        //中文
        public static String LANGUAGE_CN = "cn";
        //英文
        public static String LANGUAGE_EN = "en";

        //获取验证码类型 1注册 2修改密码
        public static String TYPE_VERIFY_CODE_REGISTER = "1";
        public static String TYPE_VERIFY_CODE_RETRIEVE_PASSWORD = "2";

    }

    public static class WebPageUrl {
        private static final String LOAN_PREFIX = "";


        //立即借款
        public static final String LOAN_BORROW = LOAN_PREFIX + "/#/Borrow";
        //借款记录
        public static final String LOAN_RECORD = LOAN_PREFIX + "/#/Record";
        //我要补仓
        public static final String LOAN_RECORD_REPLENISH = LOAN_PREFIX + "/#/Record?selected=3";
        //常见问题
        public static final String LOAN_FAQ = LOAN_PREFIX + "/#/FAQ";
        //联系我们
        public static final String LOAN_CONTACT_US = LOAN_PREFIX + "/#/ContactUs";
        //消息详情
        public static final String LOAN_MESSAGE = LOAN_PREFIX + "/#/Message?id=";

        //注册协议
        public static final String LOAN_REGISTER_AGREEMENT = LOAN_PREFIX + "/#/agreement";

        //解锁钱包页面的服务及协议
        public static final String LOAN_AGREEMENT = LOAN_PREFIX + "/#/MF";

        public static final String LOAN_DES_MNEMONICWORD= LOAN_PREFIX + "/#/Mnemonic";
        public static final String LOAN_DES_KEYSTORE= LOAN_PREFIX + "/#/Keystore";
        public static final String LOAN_DES_PRIVATEKEY= LOAN_PREFIX + "/#/Privatekey";


    }
}
