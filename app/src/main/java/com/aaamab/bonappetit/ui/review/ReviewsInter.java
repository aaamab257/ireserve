package com.aaamab.bonappetit.ui.review;

import com.aaamab.bonappetit.data.DataObj;
import com.aaamab.bonappetit.data.MakeReview;
import com.aaamab.bonappetit.data.ResReviews;
import com.aaamab.bonappetit.data.RestruantByID;
import com.aaamab.bonappetit.data.UserReviews;

import java.util.ArrayList;

public interface ReviewsInter {
    void onSuccess( RestruantByID restruant);
    void onFail(String error);
    void onConnection(boolean isConnected);
    void onPostReview(DataObj<MakeReview> dataObj );
}
