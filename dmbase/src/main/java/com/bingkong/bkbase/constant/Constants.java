package com.bingkong.bkbase.constant;

/**
 * Created by niejiahuan on 18/1/25.
 */

public class Constants {
    public final static String CODE = "NETWORK_CODE";
    public final static String MSG = "NETWORK_MSG";
    public final static String DATA = "NETWORK_DATA";
    public final static String OBJECT = "NETWORK_OBJECT";
    public final static String REQTOKEN = "NETWORK_TOKEN";

    /*Work mode for design, please not change the NewDisign or Edit of the workmode defination.*/
    //NewDesign or Edit     AdjustOrCreateNewDesign   PriMedialOrNototherMedial
    public final static String WORKMODE_SIMPLE = "NewDesignPriMedial";
    public final static String WORKMODE_ADJUST_OLD = "OldEditAdjustOtherMedial";
    public final static String WORKMODE_ADJUST_NEW_PRIMARYMEDIAL = "NewDesignAdjustPriMedial";
    public final static String WORKMODE_ADJUST_NEW_OHTER = "NewDesignAdjustOtherMedial";
    public final static String WORKMODE_CUSTOMIZE_NEW = "NewDesignPriMedialCustomize";
    public final static String WORKMODE_AUTOSAVE_NEW = "NewDesignPriMedialAutosave";
    public static final int WINDOW_DURATION = 3000;

    //Whether to display the temporary global variable of shop
    public static String wShop = "";
    //    public static String NEWRELIC_PRODUCR = "AAfe36ad578710a6509186682c4236efc67fd8193d";
//    public static String NEWRELIC_DEBUG = "AA3022116bebb196ae76857d2e44a6a0dcca87330b";
    public static int isGuestLogin = 10;

    public final static String MAIN_ACT = "/mainactivity/mainActivity";
    public final static String MY_ACCOUNT_FMT = "/MyAccountFragment/MyAccountFragment";
    public final static String VIP_ACT = "/vipActivity/vipActivity";

    public final static String MAIN_FMT = "/main/mainfmt";
    public final static String MAIN_VIDEO_DOWNLOAD_TIPS_ACT = "/main/VideoDownloadTipsAct";
    public final static String MAIN_VIDEO_PLAY_ACT = "/main/VideoPlayAct";

    public final static String HOME_FMT = "/home/HomeFmt";
    public final static String GOODSDIYDETAILS_ACT_FMT = "/home/GoodsDiyDetailsAct";
    public final static String GOODSDIYDETAILS_DIY_ACT_FMT = "/home/GoodsDiyDetailsAct";
    public final static String SHOPPING_CART_ACT = "/shopingCart/ShoppingCartAct";
    public final static String NEW_SHOPPING_CART_ACT = "/newShopingCart/NewShoppingCartAct";
    public final static String SIZE_DETAIL_INFO = "/sizeDetailInfo/SizeDetailInfo";
    public final static String SHOPPING_DELIVERYACT = "/shopingCart/DeliveryAct";
    public final static String SHOPPING_PAYMENTACT = "/shopingCart/PaymentAct";
    public final static String SHOPPING_SELECT_SHIPPING_METHOD_ACT = "/shopingCart/SelectShippingMethodAct";
    public final static String SHOPPING_ORDER_CONFIRMATION_ACT = "/shopingCart/OrderConfirmationAct";

    public final static String ACCOUNT_SET_ACT = "/mine/AccountSettingActivity";
    public final static String LOGINSELECTION_ACT = "/mine/loginselection";
    public final static String TERM_CONFIRM_ACT = "/mine/TermConfirmActivity";
    public final static String IM_SYSTEM_MENU = "/mine/SystemMenu";//迁移到mine模块，Im只是消息，这个类放im不合适
    public final static String FASTLOGIN_ACT = "/app/fastlogin";
    public final static String LOGIN_ACT = "/mine/login";
    public final static String REGISTER_ACT = "/mine/Register";
    public final static String FORGOT_PASSWORD_ACT = "/mine/ForgotPassword";
    public final static String MINE_FMT = "/mine/MineFmt";
    public final static String INSPIRED_PROFILE_ACT = "/inspired/ProfileAct";
    public final static String IM_CHAT_LIST_FMT = "/im/ChatListFmt";
    public final static String IM_CHAT_LISTunlogin_FMT = "/imunlogin/ChatListFmt";
    public final static String MyHubFmt = "/MyHubFmt/MyHubFmt";

    //    public final static String IM_SYSTEM_MENU = "/im/SystemMenu";
    public final static String IM_GROUP_DETAIL = "/im/GroupDetail";
    public final static String IM_SESSION_ACT = "/im/SessionAct";
    public final static String IM_CHAT_ACT = "/applozic/ChatAct";
    public final static String CONTACT_DETAIL_ACT = "/contact/ContactDetailAct";
    public final static String CONTACT_BLOCK_ACT = "/contact/ContactBlockAct";
    public final static String CONTACT_MAIN_ACT = "/contact/ContactMainAct";
    public final static String CONTACT_MAIN_FMT = "/contact/ContactMainFmt";
    public final static String CONTACT_BLOCK_LIST_ACT = "/contact/ContactBlockListAct";
    public final static String CONTACT_BLOCK_LIST_FMT = "/contact/ContactBlockListFmt";
    public final static String CONTACT_SEARCH_FMT = "/contact/ContactSearchFmt";
    public final static String CONTACT_SEARCH_LIST_FMT = "/contact/ContactSearchListFmt";
    public final static String CONTACT_LIST_FMT = "/contact/ContactListFmt";
    public final static String CONTACT_FRIEND_LIST_FMT = "/contact/ContactFriendListFmt";
    public final static String CONTACT_FOLLOWERS_LIST_FMT = "/contact/ContactFollowersListFmt";
    public final static String CONTACT_FOLLOWING_LIST_FMT = "/contact/ContactFollowingListFmt";
    public final static String CONTACT_REPORT_ACT = "/contact/ContactReportAct";


    public final static String MARKETPLACE_ACT = "/markettest/Marketplace";
    public final static String MARKETPLACE_FMT = "/market/Marketplace";
    public final static String MARKETPLACEunlogin_FMT = "/marketunlogin/Marketplace";

    public final static String NewDesign_ACT = "/new/NewDesign";

    public final static String MyDesigns_FMT = "/mydesigns/mydesignhome";
    public final static String NewDesignunlogin_FMT = "/newunlogin/NewDesign";
    public final static String NEW_DESIGN_ACT = "/newdesignACTIVITY/NewDesignActivity";
    public final static String NewDesigns_FMT = "/newdesigns/newdesignhome";


    public final static String SWEETCAMERA_FMT = "/swc/NewDesignCamera";
    public final static String SWEETCAMERA_TAKE_PIC = "/swc/TakePic";
    public final static String SWEETCAMERA_SELECT_PIC_FROM_ALBUM = "/swc/SelectPicFromAlbum";
    public final static String SWEETCAMERA_LOAD_MATERIAL = "/swc/LoadMaterialForDesign";
    public final static String SWEETCAMERA_DESIGN_ON_MESH_ACT = "/swc/DesignOnMeshActivity";
    public final static String SWEETCAMERA_PIECE_DESIGN_ACT = "/swc/PieceDesignActivity";
    public final static String SWEETCAMERA_CUSTOMIZEDESIGN_ACT = "/swc/ComplexEditDesignActivity";
    public final static String SWEETCAMERA_MMSA = "/swc/MultiMedialSelectActivity";
    public final static String VRPREVIEW_ACTIVITY_MMSA = "/swc/VRPreviewActivity";
    public final static String SWEETCAMERA_PRICE_DETAIL_ACT = "/swc/DesignPriceDetailActivity";
    public final static String SWEETCAMERA_PRODUCTS_PRICE_INFO_ACT = "/swc/ProductsPriceInfoActivity";


    public final static String CHECKOUT_ACT = "/checkout/CheckoutActivity";
    public final static String PROMOTION_CODE_ACT = "/promotion/PromotionCode";
    public final static String SHIPPING_ACT = "/shipping/ShippingActivity";
    public final static String GET_LOCATION_ACT = "/shipping/GetLocationActivity";
    public final static String PAY_MENT_ACT = "/checkoutpay/PayMentActivity";
    public final static String ORDER_ACT = "/order/OrderActivity";
    public final static String MYDESIGN_SALE_ACT = "/mydesignsale/MyDesignSaleActivity";
    public final static String MYDESIGN_VIEW_ACT = "/mydesignciew/MyDesignViewActivity";
    public final static String MY_DESIGN_EDIT_ACT = "/mydesignedit/MyDesignEditActivity";
    public final static String EDIT_PRIVACY_ACT = "/mydesignedit/EditPrivacyActivity";
    public final static String EDIT_TITLE_ACT = "/edittitle/EditTieleActivity";
    public final static String EDIT_TAGS_ACT = "/edittags/EditTagsActivity";
    public final static String CHOOSE_STAND_ACT = "/chooseavtivity/chooseActivity";
    public final static String SET_PAYMENT = "/SettingPaymentAct/SettingPaymentAct";
    public final static String PAYMENTADDRESS = "/PaymentAddress/PaymentAddress";
    public final static String PAYMENT_SHIPPING = "/PaymentShippingAct/PaymentShippingAct";
    public final static String PAYMENT_CONFIRMATIONACT = "/PaymentConfirmationAct/PaymentConfirmationAct";
    public final static String PAYMENT_SUCCESS = "/PaymentSuccessAct/PaymentSuccessAct";

    public final static String FOLLOWERS_PROFILE = "/profile/Followers";
    public final static String PROFILE_FOLLOWING_ACT = "/profile/ProfileFollowingAct";
    public final static String PREILE_POST_FMT = "/profile/Post";
    public final static String PREILE_DESIGN_FMT = "/profile/Design";
    public final static String INSPIRED_FMT = "/insp/Inspirehome";
    public final static String INSPIRED_MapDepotActivityACT = "/insp/MapDepotActivity";

    public final static String INSPIREDunlogin_FMT = "/inspunlogin/Inspirehome";
    public final static String MESSAGE_FMT = "/message/Msghome";
    public final static String BROWSER_FMT = "/shop/browser";
    public final static String OLD_BROWSER_FMT = "/shop/browserOld";
    public final static String COMMENTS_ACT = "/comments/commentsactivity";
    public final static String PRODUCTDETAIL_FMT = "/comments/productdetailactivity";
    public final static String INQUIRY_REPORT_ACT = "/comments/reportandinquiryact";
    public final static String ADVANCE_PRIVACY_ACT = "/comments/advanced_privacy";
    public final static String PRIVACY_POLICY_ACT = "/comments/privacy_policy";
    public final static String NoLoginInspire_FMT = "/nologininspire/inspire";
    public final static String NoLogin_Act = "/nologinhome/NotLoggedInActivity";
    public final static String NewPost_Act = "/newpost/newpostActivity";
    public final static String DESIGN_DETAILS_ACT = "/designdetails/DesignDetailsActivity";


    public class NOT_LOGINED {
    }

    public final static String POSTDETAIL_ACT = "/postdetail/postdetailactivity";
    public final static String SETTING_ACT = "/setting/settingact";
    public final static String EMAILVERIFY_ACT = "/emailVerify/EmailVerify";
    public final static String SET_PROFILR_ACT = "/setprofileact/setProfileAct";
    public final static String CREDIT_ACT = "/creditactivity/creditActivity";
    public final static String COMMISSION_ACT = "/commentissionact/commentissionact";
    public final static String GOODS_IN_PROGRESS_ACT = "/goodsinpregressactivity/goodsInPregressActivity";
    public final static String GOODS_COMPLETED_ACT = "/goodscompletedactivity/goodsComPletedActivity";
    public final static String CHANGE_PASSWORD_ACT = "/changepasswordactivity/ChangePasswordActivity";
    public final static String ORDER_COMPLETED_ACT = "/ordercompleted/ordercompleted";
    public final static String IM_WATCHLIST_ACT = "/im/watchlist";
    public final static String IM_ORDERS_ACT = "/im/orders";
    public final static String IM_COMMISSIONS_ACT = "/im/commissions";
    public final static String ISSUES_WITH_OTHER = "/issueswithorder/IssuesWithOrder";
}
