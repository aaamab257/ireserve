package com.aaamab.bonappetit.ui.review;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.DataObj;
import com.aaamab.bonappetit.data.MakeReview;
import com.aaamab.bonappetit.data.RestruantByID;
import com.aaamab.bonappetit.databinding.FragmentReviewScreendBinding;
import com.aaamab.bonappetit.ui.adapter.ReviewsAdapter;
import com.aaamab.bonappetit.ui.resDetails.RestaurantDetails;
import com.aaamab.bonappetit.utils.CustomDialog;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.ToastUtil;


public class ReviewScreen extends Fragment implements ReviewsInter {

    ReviewHandler handler ;
    FragmentReviewScreendBinding binding ;
    View v ;
    ReviewsAdapter adapter ;
    ReviewsPresenter presenter;
    CustomDialog dialog ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_review_screend, container, false);
        v = binding.getRoot();
        handler = new ReviewHandler(getActivity());
        binding.setHandler(handler);
        presenter = new ReviewsPresenter(this);
        dialog = new CustomDialog(getActivity());
        //dialog.showDialog();
        ((RestaurantDetails)getActivity()).goneButtons(true);
        presenter.getDetails(getActivity(), StaticMethods.resID);
        binding.editTextTextPersonName.addTextChangedListener(mTextEditorWatcher);
        return v;
    }
    private final TextWatcher mTextEditorWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //This sets a textview to the current length
            binding.txtCounter.setText(String.valueOf(s.length())+"/ 250");
        }

        public void afterTextChanged(Editable s) {
        }
    };
    @Override
    public void onSuccess(RestruantByID restruant) {
        dialog.dismissDialog();
        adapter = new ReviewsAdapter(getActivity() , restruant.getRes().get(0).getReviews());
        binding.recReviews.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recReviews.setAdapter(adapter);
    }

    @Override
    public void onFail(String error) {
        dialog.dismissDialog();
        ToastUtil.showErrorToast(getActivity() ,error);
    }

    @Override
    public void onConnection(boolean isConnected) {
        dialog.dismissDialog();
        ToastUtil.showErrorToast(getActivity() ,R.string.noConnection);
    }

    @Override
    public void onPostReview(DataObj<MakeReview> dataObj) {
        dialog.dismissDialog();
        binding.editTextTextPersonName.setText("");
        binding.ratingBar2.setRating(0);
        ToastUtil.showSuccessToast(getActivity() , dataObj.getMsg());
    }

    public class ReviewHandler{
        Context context ;

        public ReviewHandler(Context context) {
            this.context = context;
        }
        public void onPost(View v){
            String review = binding.editTextTextPersonName.getText().toString();
            if(review.isEmpty()){
                ToastUtil.showErrorToast(getActivity(), R.string.comment);
            }else {
                //dialog.showDialog();
                float rate = binding.ratingBar2.getRating();
                if (rate == 0){
                    ToastUtil.showErrorToast(getActivity() , R.string.your_rate);
                }else {
                    presenter.postReview(getActivity() ,review , StaticMethods.resID, rate);
                }

            }
        }
    }
}