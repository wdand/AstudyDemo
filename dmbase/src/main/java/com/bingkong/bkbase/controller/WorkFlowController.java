package com.bingkong.bkbase.controller;

import android.os.Parcel;
import android.os.Parcelable;

import com.blankj.utilcode.util.StringUtils;
import com.bingkong.bkbase.constant.Constants;
import com.bingkong.bkbase.model.DesignAutoSaveInfo;
import com.bingkong.bkbase.model.DesignModel;
import com.bingkong.bkbase.model.FaceBookEvent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*****
 * This file is used to remember the work flow status and define the method for
 *  step change.
 *  bingsen xie  2018.12.17
 *
 *****/
public class WorkFlowController {

    public static final String STEP_MAINMEDIA_SELECTING = "step1";
    public static final String STEP_DARFT_DESIGN = "step2";
    public static final String STEP_FINISH_DESIGN = "step3";
    public static final String STEP_MAINDesignModified = "step4";
    public static final String STEP_MAINDOCONLY_CREATE = "step5";
    public static final String STEP_ADJUSTDOC_CHECK = "step6";
    public static final String STEP_ALLDOC_READY = "step7";

    String workmodetype;//work flow type
    String curstep;//current stepname in work flow.
    DesignIdsCondition designIds;
    String mDesignIdForDraftSave = null;   //The draft designId used during design process.

    public int getNumberOfProducts() {
        if (mAdjustList == null) {
            return 0;
        } else {
            return mAdjustList.size();
        }
    }

    public FaceBookEvent.DesignMode getDesignMode() {
        if (Constants.WORKMODE_SIMPLE.equals(workmodetype)) {
            return FaceBookEvent.DesignMode.newDesign;
        } else if (Constants.WORKMODE_CUSTOMIZE_NEW.equals(workmodetype)) {
            return FaceBookEvent.DesignMode.customize;
        } else {
            return FaceBookEvent.DesignMode.Edit;
        }
    }

    public Date getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public Date getFinishDatetime() {
        return finishDatetime;
    }

    public void setFinishDatetime(Date finishDatetime) {
        this.finishDatetime = finishDatetime;
    }

    Date startDatetime;
    Date finishDatetime;
    boolean mIsUserSetPrivacy = false;

    public Boolean getIsDesignPreviewCreated() {
        return mIsDesignPreviewCreated;
    }

    public Boolean getIsUserSetPrivacy() {
        return mIsUserSetPrivacy;
    }

    public void setUserSetPrivacy(boolean isUserSetPrivacy) {
        mIsUserSetPrivacy = isUserSetPrivacy;
    }

    public void setDesignPreviewImageCreated(Boolean created) {
        this.mIsDesignPreviewCreated = created;
    }

    Boolean mIsDesignPreviewCreated = false;//To remember is current designed had been editted.

    List<String> mAdjustList = null;

    public String getCurstep() {
        return curstep;
    }

    public String getmDesignIdForDraftSave() {
        return mDesignIdForDraftSave;
    }

    public void setmDesignIdForDraftSave(String mDesignIdForDraftSave) {
        this.mDesignIdForDraftSave = mDesignIdForDraftSave;
    }

    public String getWorkmodetype() {
        return workmodetype;
    }

    public void setCurstep(String curstep) {
        this.curstep = curstep;
    }

    public void setWorkmodetype(String workmodetype) {
        this.workmodetype = workmodetype;
    }

    public void setDesignIds(DesignIdsCondition designIds) {
        this.designIds = designIds;
    }


    public DesignIdsCondition getDesignIds() {
        return designIds;
    }

    public String getPrimaryMediaId() {
        if (designIds == null) {
            return null;
        } else {
            return designIds.getPrimaryMediaId();
        }
    }

    //The information of ids which can be used to build context.
    public static class DesignIdsCondition implements Parcelable {
        String designid;                //for new design, the id is null.
        String lastDesignversionId;     //for new design, the id is null.
        String primaryMediaId;          //
        String productId;               //for customerDesign,the id is the startup product Id.

        public String getProductId() {
            return this.productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getPrimaryMediaId() {
            return primaryMediaId;
        }

        public String getDesignid() {
            return designid;
        }

        public String getLastDesignversionId() {
            return lastDesignversionId;
        }

        public void setDesignid(String designid) {
            this.designid = designid;
        }

        public void setPrimaryMediaId(String primaryMediaId) {
            this.primaryMediaId = primaryMediaId;
        }

        public void setLastDesignversionId(String lastDesignversionId) {
            this.lastDesignversionId = lastDesignversionId;
        }

        public DesignIdsCondition() {
        }

        public DesignIdsCondition(Parcel in) {
            this.designid = in.readString();
            this.primaryMediaId = in.readString();
            this.lastDesignversionId = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.designid);
            dest.writeString(this.primaryMediaId);
            dest.writeString(this.lastDesignversionId);
        }

        @SuppressWarnings("unused")
        public static final Parcelable.Creator<DesignIdsCondition> CREATOR
                = new Parcelable.Creator<DesignIdsCondition>() {
            @Override
            public DesignIdsCondition createFromParcel(Parcel in) {
                return new DesignIdsCondition(in);
            }

            @Override
            public DesignIdsCondition[] newArray(int size) {
                return new DesignIdsCondition[size];
            }
        };
    }

    private DesignAutoSaveInfo.SaveReq mSaveReq;

    public synchronized void setAutoSaveData(DesignAutoSaveInfo.SaveReq saveReq) {
        mSaveReq = saveReq;
    }

    public synchronized DesignAutoSaveInfo.SaveReq getAutoSaveReq() {
        return mSaveReq;
    }

    public List<String> getAdjustSelectList() {
        return mAdjustList;
    }

    public void addToAdjustSelectList(ArrayList<String> list) {
        if (mAdjustList == null) {
            mAdjustList = list;
        } else {
            if (list != null) {
                for (String bean : list) {
                    if (!hasInSelectAdjustList(bean)) {
                        mAdjustList.add(bean);
                    } else {
                        removeAdjustListById(bean);
                        mAdjustList.add(bean);
                    }
                }
            }
        }
    }

    public void removeAdjustSelectList(ArrayList<String> listBeans) {
        if (listBeans != null) {
            ArrayList<String> idsInAdjustList = new ArrayList<>();
            for (String bean : listBeans) {
                if (hasInSelectAdjustList(bean)) {
                    idsInAdjustList.add(bean);
                }
            }
            if (idsInAdjustList.size() > 0) {
                for (String id : idsInAdjustList) {
                    mAdjustList.remove(id);
                }
            }
        }
    }

    public void removeAdjustListById(String id) {
        if (mAdjustList != null && id != null) {
            for (String bean : mAdjustList) {
                if (id.equals(bean)) {
                    mAdjustList.remove(bean);
                    return;
                }
            }
        }
    }

    public boolean hasInSelectAdjustList(String id) {
        if (mAdjustList != null && id != null) {
            for (String bean : mAdjustList) {
                if (bean.equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void clear() {
        if (designIds != null) {
            designIds.setPrimaryMediaId(null);
            designIds.setLastDesignversionId(null);
            designIds.setDesignid(null);
        }
        mDesignIdForDraftSave = null;
        if (mAdjustList != null) mAdjustList.clear();
    }

    //save the primary media information before go to save.
    public void setPrimaryMediaId(String id) {
        //let system remember the main primary mediaIds.
        WorkFlowController.DesignIdsCondition ids = WorkFlowController.getInstance().getDesignIds();
        if (ids == null) {
            ids = new WorkFlowController.DesignIdsCondition();
        }
        String oldId = getPrimaryMediaId();
        if (!StringUtils.isEmpty(oldId)) {
            if (oldId.equals(id)) {
                return;
            }
        }
        ids.setPrimaryMediaId(id);
        removeAdjustListById(oldId);
        WorkFlowController.getInstance().setDesignIds(ids);
        if (mAdjustList == null) {
            mAdjustList = new ArrayList<>();
        }
        if (!hasInSelectAdjustList(id)) {
            mAdjustList.add(id);
        }
    }

    static WorkFlowController mWorkFlowController = null;

    public static WorkFlowController getInstance() {
        if (mWorkFlowController == null) {
            synchronized (WorkFlowController.class) {
                if (mWorkFlowController == null) {
                    mWorkFlowController = new WorkFlowController();
                }
            }
        }
        return mWorkFlowController;
    }

    /**
     * Init WorkFlow's adjust product list.
     ***/
    public void initWorkFlowAdjustProdctList(DesignModel.DesignDetail dd) {
        if ((dd == null) ||
                dd.getDetail() == null ||
                dd.getDetail().getProducts() == null) {
            return;
        }
        ArrayList<String> adjustList = new ArrayList<>();
        List<DesignModel.ProductBean> products = dd.getDetail().getProducts();
        for (DesignModel.ProductBean pb : products) {
            adjustList.add(pb.getBaseMediaId());
        }
        String mainMediaId = dd.getDetail().getPrimaryMediaId();
        WorkFlowController.getInstance().setPrimaryMediaId(mainMediaId);
        /*first to set mainid's datetime*/
        if (adjustList.size() > 0) {
            WorkFlowController.getInstance().addToAdjustSelectList(adjustList);
        }
    }
}
