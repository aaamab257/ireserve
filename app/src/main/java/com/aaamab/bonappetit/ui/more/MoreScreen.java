package com.aaamab.bonappetit.ui.more;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.SocialMediaData;
import com.aaamab.bonappetit.databinding.FragmentMoreScreenBinding;
import com.aaamab.bonappetit.helpers.prefs.PrefUtils;
import com.aaamab.bonappetit.ui.login.LoginScreen;
import com.aaamab.bonappetit.ui.main.MainScreen;
import com.aaamab.bonappetit.ui.profile.Profile;
import com.aaamab.bonappetit.utils.IntentUtilies;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.ToastUtil;
import com.aaamab.bonappetit.utils.dialogs.DialogUtil;
import com.aaamab.bonappetit.utils.dialogs.DialogUtilResponse;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class MoreScreen extends Fragment implements MoreInterface , DialogUtilResponse {


    MoreHandler handler;
    FragmentMoreScreenBinding binding;
    View v;
    MorePresenter presenter ;
    SocialMediaData data;
    DialogUtil dialogUtil;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_more_screen, container, false);
        v = binding.getRoot();
        handler = new MoreHandler(getActivity());
        binding.setHandler(handler);
        presenter = new MorePresenter(this);
        presenter.getSocial(getActivity());
        String nameUpperCase = StaticMethods.userData.getName().toUpperCase();
        binding.txtHello.setText(getActivity().getString(R.string.hello_more)+ nameUpperCase);
        dialogUtil = new DialogUtil(this);
        return v;
    }

    @Override
    public void onSocial(SocialMediaData socialMediaData) {
        this.data = socialMediaData;
    }

    @Override
    public void onConnection(boolean isConnected) {
        ToastUtil.showErrorToast(getActivity(), R.string.noConnection);
    }

    @Override
    public void onFail(String error) {
        ToastUtil.showErrorToast(getActivity(), error);
    }

    @Override
    public void selectedValueSingleChoice(int position) {
        Log.e(TAG, "selectedValueSingleChoice: "+position);
        if (position==0){
            lang("ar");
        }else {
            lang("en");
        }
    }

    @Override
    public void selectedValueSingleChoice(int position, String arrayType) {

    }

    @Override
    public void selectedMultiChoicelang(ArrayList<String> choices, ArrayList<String> postions, String arrayType) {

    }

    public class MoreHandler {
        Context context;

        public MoreHandler(Context context) {
            this.context = context;
        }

        public void profile(View v) {

            ((MainScreen)getActivity()).profile();
        }

        public void about(View v) {
            ((MainScreen)getActivity()).about();
        }

        public void contactUs(View v) {
            ((MainScreen)getActivity()).contactUs();
        }

        public void notifications(View v) {
            ((MainScreen)getActivity()).notifications();
        }

        public void terms(View v) {
            ((MainScreen)getActivity()).terms();
        }

        public void support(View v) {
            ((MainScreen)getActivity()).support();
        }
        public void booking(View v){
            ((MainScreen)getActivity()).bookings();
        }

        public void signOut(View v){
            ((MainScreen)getActivity()).onSignOut();
        }
        public void myOrders(View v){
            ((MainScreen)getActivity()).onMyOrders();
        }
        public void myPay(View v){
            ((MainScreen)getActivity()).myPay();
        }
        public void face(View v){
            ((MainScreen)getActivity()).social(data.data.get(0).facebook);
        }
        public void youtube(View v){
            ((MainScreen)getActivity()).social(data.data.get(0).youtube);
        }
        public void instagram(View v){
            ((MainScreen)getActivity()).social(data.data.get(0).insta);
        }
        public void whats(View v){
            ((MainScreen)getActivity()).social(data.data.get(0).watsapp);
        }
        public void twitter(View v){
            ((MainScreen)getActivity()).social(data.data.get(0).twiter);
        }

        public void changeLang(View v){
            ArrayList<String> lang = new ArrayList<>();
            lang.add(getActivity().getResources().getString(R.string.arabic));
            lang.add(getActivity().getResources().getString(R.string.english));
            dialogUtil.showDialogSingleChooice(getActivity(),R.string.language,R.string.ok,lang);
        }
    }

    public void lang(String lang){
        LocaleManager.setLocale(getActivity(),lang);
        ((MainScreen)getActivity()).openSplash();
    }

}