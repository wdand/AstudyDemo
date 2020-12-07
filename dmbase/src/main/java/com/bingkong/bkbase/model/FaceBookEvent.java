package com.bingkong.bkbase.model;

import android.os.Bundle;

import com.bingkong.bkbase.event.ModuleMessage;
import com.google.gson.Gson;

import com.bingkong.bkbase.utils.CommentTime;

import org.greenrobot.eventbus.EventBus;

import java.util.Date;
import java.util.List;

public class FaceBookEvent {
    private static final String TAG = "FaceBookEvent";
    public static final String EN_StartRegistration = "StartRegistration";
    public static final String EN_CompleteRegistration = "CompleteRegistration";
    public static final String EN_InitiateNewPost = "InitiateNewPost";
    public static final String EN_AttachPostImage = "AttachPostImage";
    public static final String EN_SubmitNewPost = "SubmitNewPost";
    public static final String EN_SubmitReply = "SubmitReply";
    public static final String EN_InitiateNewDesign = "InitiateNewDesign";
    public static final String EN_CancelDesign = "CancelDesign";
    public static final String EN_FinishDesign = "FinishDesign";
    public static final String EN_DEUploadBackgroundImage = "DEUploadBackgroundImage";
    public static final String EN_DECaptureCameraImage = "DECaptureCameraImage";
    public static final String EN_DESwitchPiece = "DESwitchPiece";
    public static final String EN_DEPreviewDesign = "DEPreviewDesign";
    public static final String EN_DESwitchView = "DESwitchView";
    public static final String EN_DEBackgroundColorToolActive = "DEBackgroundColorToolActive";
    public static final String EN_DEBrushToolActive = "DEBrushToolActive";
    public static final String EN_DEBrushDraw = "DEBrushDraw";
    public static final String EN_DEBrushErase = "DEBrushErase";
    public static final String EN_DEBrushUndo = "DEBrushUndo";
    public static final String EN_DEEmbroideryToolActive = "DEEmbroideryToolActive";
    public static final String EN_DEEmbroideryDraw = "DEEmbroideryDraw";
    public static final String EN_DEEmbroideryErase = "DEEmbroideryErase";
    public static final String EN_DEEmbroideryUndo = "DEEmbroideryUndo";
    public static final String EN_DETextToolActive = "DETextToolActive";
    public static final String EN_DETextAdd = "DETextAdd";
    public static final String EN_DETextSetEmbossed = "DETextSetEmbossed";
    public static final String EN_DETextUnsetEmbossed = "DETextUnsetEmbossed";
    public static final String EN_DETextChangeFont = "DETextChangeFont";
    public static final String EN_DETextEdit = "DETextEdit";
    public static final String EN_DETextRemove = "DETextRemove";
    public static final String EN_DETextUndo = "DETextUndo";
    public static final String EN_DEGemToolActive = "DEGemToolActive";
    public static final String EN_DEGemAdd = "DEGemAdd";
    public static final String EN_DEGemRemove = "DEGemRemove";
    public static final String EN_DEGemUndo = "DEGemUndo";
    public static final String EN_InitiateAdjustProducts = "InitiateAdjustProducts";
    public static final String EN_AdjustProductAddProduct = "AdjustProductAddProduct";
    public static final String EN_AdjustProductRemoveProduct = "AdjustProductRemoveProduct";
    public static final String EN_AdjustProductEditProduct = "AdjustProductEditProduct";
    public static final String EN_AddToCart = "AddToCart";
    public static final String EN_EnterDesignList = "EnterDesignList";
    public static final String EN_AddToWishlist = "AddToWishlist";
    public static final String EN_Search = "Search";
    public static final String EN_CustomizeProduct = "CustomizeProduct";
    public static final String EN_LikeProduct = "LikeProduct";
    public static final String EN_EnterShopList = "EnterShopList";
    public static final String EN_InitiateCheckout = "InitiateCheckout";
    public static final String EN_AddPaymentInfo = "AddPaymentInfo";
    public static final String EN_Purchase = "Purchase";
    public static final String EN_AddShippingInfo = "AddShippingInfo";
    public static final String EN_SelectShippingMethod = "SelectShippingMethod";
    public static final String EN_CartSubmit = "CartSubmit";
    public static final String EN_EnterApp = "EnterApp";
    public static final String EN_LeaveApp = "LeaveApp";
    public static final String EN_QuickLogin = "QuickLogin";
    public static final String EN_UpdateRegister = "UpdateRegister";
    public static final String EN_DEWarningDesignNotFinish = "DEWarningDesignNotFinish";
    public static final String EN_DEGenerateDesign = "DEGenerateDesign";
    public static final String EN_DELeaveDesignBoard = "DELeaveDesignBoard";


    public static void logSimpleEvent(String eventName) {
        ModuleMessage mm = new ModuleMessage(ModuleMessage.EVENT_STATICS_REPORT,
                eventName);
        EventBus.getDefault().post(mm);
    }

    public static void logCompleteRegisterEvent(String registrationMethod) {
        ModuleMessage mm = new ModuleMessage(ModuleMessage.EVENT_STATICS_REPORT,
                EN_CompleteRegistration);
        Bundle bd = new Bundle();
        bd.putString("registrationMethod", registrationMethod);
        mm.setBundleData(bd);
        EventBus.getDefault().post(mm);
    }

    public static void logAttachPostImage(Boolean from_new_post) {
        ModuleMessage mm = new ModuleMessage(ModuleMessage.EVENT_STATICS_REPORT,
                EN_AttachPostImage);
        Bundle bd = new Bundle();
        bd.putBoolean("from_new_post", from_new_post);
        mm.setBundleData(bd);
        EventBus.getDefault().post(mm);
    }

    public static void logSubmitNewPost(Boolean design_related,
                                        String product_version_id,
                                        String designer_id,
                                        Integer num_images_attached
    ) {
        ModuleMessage mm = new ModuleMessage(ModuleMessage.EVENT_STATICS_REPORT,
                EN_SubmitNewPost);
        Bundle bd = new Bundle();
        bd.putBoolean("design_related", design_related);
        bd.putString("product_version_id", product_version_id);
        bd.putString("designer_id", designer_id);
        bd.putInt("num_images_attached", num_images_attached);
        mm.setBundleData(bd);
        EventBus.getDefault().post(mm);
    }

    public static void logSubmitReply(Boolean with_repost) {
        ModuleMessage mm = new ModuleMessage(ModuleMessage.EVENT_STATICS_REPORT,
                EN_SubmitReply);
        Bundle bd = new Bundle();
        bd.putBoolean("with_repost", with_repost);
        mm.setBundleData(bd);
        EventBus.getDefault().post(mm);
    }

    public static void logInitiateNewDesign(Date start_datetime,
                                            DesignMode designMode) {
        ModuleMessage mm = new ModuleMessage(ModuleMessage.EVENT_STATICS_REPORT,
                EN_InitiateNewDesign);
        Bundle bd = new Bundle();
        bd.putString("start_datetime", getStrForDate(start_datetime));
        bd.putString("designMode", getStrOfDesignMode(designMode));
        mm.setBundleData(bd);
        EventBus.getDefault().post(mm);
    }

    public enum DesignMode {
        newDesign,
        customize,
        Edit
    }

    public static String getStrForDate(Date datetime) {
        return CommentTime.dateToString(datetime, "MEDIUM");
    }

    /**
     * (newDesign/customize/edit)
     ***/
    public static void logCancelDesign(
            Date start_datetime,
            Date cancel_datetime,
            DesignMode designMode,
            Boolean isAdjust) {
        ModuleMessage mm = new ModuleMessage(ModuleMessage.EVENT_STATICS_REPORT,
                EN_CancelDesign);
        Bundle bd = new Bundle();

        bd.putString("start_datetime", getStrForDate(start_datetime));
        bd.putString("cancel_datetime", getStrForDate(cancel_datetime));
        Integer seconds_spend = (int) CommentTime.getDuration(start_datetime,
                cancel_datetime);
        bd.putInt("seconds_spent", seconds_spend);
        String strDesignMode = getStrOfDesignMode(designMode);
        bd.putString("designMode", strDesignMode);
        bd.putBoolean("isAdjust", isAdjust);
        mm.setBundleData(bd);
        EventBus.getDefault().post(mm);
    }

    private static String getStrOfDesignMode(DesignMode designMode) {
        String strDesignMode = "";
        switch (designMode) {
            case customize:
                strDesignMode = "customize";
                break;
            case newDesign:
                strDesignMode = "newDesign";
                break;
            case Edit:
                strDesignMode = "edit";
                break;
        }
        return strDesignMode;
    }

    public static void logFinishDesign(Date start_datetime,
                                       Date end_datetime,
                                       DesignMode designMode,
                                       Integer num_products) {
        ModuleMessage mm = new ModuleMessage(ModuleMessage.EVENT_STATICS_REPORT,
                EN_FinishDesign);
        Bundle bd = new Bundle();
        bd.putString("start_datetime", getStrForDate(start_datetime));
        bd.putString("end_datetime", getStrForDate(end_datetime));
        Integer seconds_spend = (int) CommentTime.getDuration(start_datetime, end_datetime);
        bd.putInt("seconds_spent", seconds_spend);
        bd.putString("designMode", getStrOfDesignMode(designMode));
        bd.putInt("num_products", num_products);
        mm.setBundleData(bd);
        EventBus.getDefault().post(mm);
    }

    public static class ImageExtentsion {
        public int image_width;
        public int image_height;
    }

    private static String getImageExtentsion(ImageExtentsion imageExtentsion) {
        Gson gson = new Gson();
        return gson.toJson(imageExtentsion, ImageExtentsion.class);
    }

    public static void logDEUploadBackgroundImage(String piece_name,
                                                  int image_bytes,
                                                  ImageExtentsion imageExtentsion) {
        ModuleMessage mm = new ModuleMessage(ModuleMessage.EVENT_STATICS_REPORT,
                EN_DEUploadBackgroundImage);
        Bundle bd = new Bundle();
        bd.putString("piece_name", piece_name);
        bd.putInt("image_bytes", image_bytes);
        bd.putString("ImageExtentsion", getImageExtentsion(imageExtentsion));
        mm.setBundleData(bd);
        EventBus.getDefault().post(mm);
    }

    public static void logDECaptureCameraImage(String piece_name,
                                               ImageExtentsion imageExtentsion) {
        ModuleMessage mm = new ModuleMessage(ModuleMessage.EVENT_STATICS_REPORT,
                EN_DECaptureCameraImage);
        Bundle bd = new Bundle();
        bd.putString("piece_name", piece_name);
        bd.putString("ImageExtentsion", getImageExtentsion(imageExtentsion));
        mm.setBundleData(bd);
        EventBus.getDefault().post(mm);
    }

    public static void logDETextChangeFont(String new_font_name) {
        ModuleMessage mm = new ModuleMessage(ModuleMessage.EVENT_STATICS_REPORT,
                EN_DETextChangeFont);
        Bundle bd = new Bundle();
        bd.putString("new_font_name", new_font_name);
        mm.setBundleData(bd);
        EventBus.getDefault().post(mm);
    }

    public static void logDEGemAdd(int size_code,
                                   String color_code,
                                   int num) {
        ModuleMessage mm = new ModuleMessage(ModuleMessage.EVENT_STATICS_REPORT,
                EN_DEGemAdd);
        Bundle bd = new Bundle();
        bd.putString("size_code", String.format("%d", size_code));
        bd.putString("color_code", color_code);
        bd.putInt("num", num);
        mm.setBundleData(bd);
        EventBus.getDefault().post(mm);
    }

    public static void logAddToCart(String productName,
                                    String productId,
                                    String mediaId,
                                    double price,
                                    String pageName) {
        ModuleMessage mm = new ModuleMessage(ModuleMessage.EVENT_STATICS_REPORT,
                EN_AddToCart);
        Bundle bd = new Bundle();
        bd.putString("productName", productName);
        bd.putString("productId", productId);
        bd.putString("mediaId", mediaId);
        bd.putDouble("price", price);
        bd.putString("pageName", pageName);
        mm.setBundleData(bd);
        EventBus.getDefault().post(mm);
    }

    public static void logAddToWishlist(String productId) {
        ModuleMessage mm = new ModuleMessage(ModuleMessage.EVENT_STATICS_REPORT,
                EN_AddToWishlist);
        Bundle bd = new Bundle();
        bd.putString("productId", productId);
        mm.setBundleData(bd);
        EventBus.getDefault().post(mm);
    }

    public static void logSearch(String keyword) {
        ModuleMessage mm = new ModuleMessage(ModuleMessage.EVENT_STATICS_REPORT,
                EN_Search);
        Bundle bd = new Bundle();
        bd.putString("keyword", keyword);
        mm.setBundleData(bd);
        EventBus.getDefault().post(mm);
    }

    public static void logCustomizeProduct(String productId) {
        ModuleMessage mm = new ModuleMessage(ModuleMessage.EVENT_STATICS_REPORT,
                EN_CustomizeProduct);
        Bundle bd = new Bundle();
        bd.putString("productId", productId);
        mm.setBundleData(bd);
        EventBus.getDefault().post(mm);
    }

    public static void logInitiateCheckout(int numItems,
                                           double totalPrice) {
        ModuleMessage mm = new ModuleMessage(ModuleMessage.EVENT_STATICS_REPORT,
                EN_InitiateCheckout);
        Bundle bd = new Bundle();
        bd.putInt("numItems", numItems);
        bd.putDouble("totalPrice", totalPrice);
        mm.setBundleData(bd);
        EventBus.getDefault().post(mm);
    }

    public static void logPurchase(double purchaseAmount) {
        ModuleMessage mm = new ModuleMessage(ModuleMessage.EVENT_STATICS_REPORT,
                EN_Purchase);
        Bundle bd = new Bundle();
        bd.putDouble("purchaseAmount", purchaseAmount);
        mm.setBundleData(bd);
        EventBus.getDefault().post(mm);
    }

    public static void logAddPaymentInfo(Boolean AddResult) {
        ModuleMessage mm = new ModuleMessage(ModuleMessage.EVENT_STATICS_REPORT,
                EN_AddPaymentInfo);
        Bundle bd = new Bundle();
        bd.putBoolean("AddResult", AddResult);
        mm.setBundleData(bd);
        EventBus.getDefault().post(mm);
    }

    public static void logAddShippingInfo(Boolean AddResult) {
        ModuleMessage mm = new ModuleMessage(ModuleMessage.EVENT_STATICS_REPORT,
                EN_AddShippingInfo);
        Bundle bd = new Bundle();
        bd.putBoolean("AddResult", AddResult);
        mm.setBundleData(bd);
        EventBus.getDefault().post(mm);
    }

    public static class SizeAndNum {
        public String size;
        public int quantity;
    }

    public static void logCartSubmit(String productId,
                                     String productColor,
                                     List<SizeAndNum> contentOfSizeAndNum) {

        if (contentOfSizeAndNum == null) {
            return;
        }
        ModuleMessage mm = new ModuleMessage(ModuleMessage.EVENT_STATICS_REPORT, EN_CartSubmit);
        Bundle bd = new Bundle();
        bd.putString("productId", productId);
        bd.putString("productColor", productColor);

        Gson gson = new Gson();
        String strJson = gson.toJson(contentOfSizeAndNum, List.class);
        bd.putString("contentOfSizeAndNum", strJson);

        mm.setBundleData(bd);
        EventBus.getDefault().post(mm);
    }

    public static void logEnterApp(Date focus_datetime) {
        ModuleMessage mm = new ModuleMessage(ModuleMessage.EVENT_STATICS_REPORT,
                EN_EnterApp);
        Bundle bd = new Bundle();
        bd.putString("focus_datetime", getStrForDate(focus_datetime));
        mm.setBundleData(bd);
        EventBus.getDefault().post(mm);
    }

    public static void logLeaveApp(Date focus_datetime,
                                   Date blur_datetime) {
        ModuleMessage mm = new ModuleMessage(ModuleMessage.EVENT_STATICS_REPORT,
                EN_LeaveApp);
        Bundle bd = new Bundle();
        bd.putString("focus_datetime", getStrForDate(focus_datetime));
        bd.putString("blur_datetime", getStrForDate(blur_datetime));
        Integer seconds_spend = (int) CommentTime.getDuration(focus_datetime,
                blur_datetime);
        bd.putInt("seconds_spent", seconds_spend);
        mm.setBundleData(bd);
        EventBus.getDefault().post(mm);
    }
}
