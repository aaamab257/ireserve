package com.aaamab.bonappetit.ui.search;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.RestArray;
import com.aaamab.bonappetit.data.RestruantByID;
import com.aaamab.bonappetit.data.SeatingData;
import com.aaamab.bonappetit.databinding.FragmentSearchScreenBinding;
import com.aaamab.bonappetit.ui.adapter.TableSeatCountAdapter;
import com.aaamab.bonappetit.ui.dineinOrder.DineInOrderScreen;
import com.aaamab.bonappetit.ui.dineinOrder.onCountSelected;
import com.aaamab.bonappetit.ui.main.MainScreen;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.ToastUtil;
import com.aaamab.bonappetit.utils.dialogs.DialogUtil;
import com.aaamab.bonappetit.utils.dialogs.DialogUtilResponse;
import com.aaamab.bonappetit.utils.network.MainApi;
import com.aaamab.bonappetit.utils.network.MainApiBody;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.RequestBody;

public class SearchScreen extends Fragment implements onCountSelected ,SearchInter , DialogUtilResponse {



    FragmentSearchScreenBinding binding ;
    View v;
    String count = "1" ;
    TableSeatCountAdapter adapter ;
    SearchHandler handler ;
    SearchPresenter presenter ;
    RestArray data ;
    ArrayList<String> name ;
    DialogUtil dialogUtil;
    int steat_type = 0 ;
    ArrayList<Integer> ids;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_screen, container, false);
        v = binding.getRoot();
        handler = new SearchHandler(getActivity());
        binding.setHandler(handler);
        adapter = new TableSeatCountAdapter(getActivity() , this);
        final LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        binding.recSeats.setLayoutManager(layoutManager);
        binding.recSeats.setAdapter(adapter);
        presenter = new SearchPresenter(this);
        presenter.getSeating(getActivity() , 0);
        dialogUtil = new DialogUtil(this);
        binding.txtHello.setText(StaticMethods.userData.getName().toUpperCase());
        /*if (StaticMethods.userData.getGender().equals("female")){
            binding.imageView.setImageResource(R.drawable.female);
        }*/
        Picasso.with(getActivity()).load(StaticMethods.userData.getImage()).into(binding.imageView);
        return v;
    }

    @Override
    public void onSelected(String count) {
        this.count = count ;
    }

    @Override
    public void onSuccess(RestArray data) {
        this.data = data ;
        StaticMethods.restArray = data ;
        ((MainScreen)getActivity()).openResultScreen();
    }

    @Override
    public void onFail(String error) {
        ToastUtil.showErrorToast(getActivity() , error);
    }

    @Override
    public void onConnection(boolean isConnected) {
        ToastUtil.showErrorToast(getActivity() , R.string.noConnection);
    }

    @Override
    public void onSeatingType(SeatingData data) {
        try {
            name = new ArrayList<>();
            ids = new ArrayList<>();
            for (int i = 0 ; i < data.data.size() ;i++){
                name.add(data.data.get(i).getName());
                ids.add(data.data.get(i).getId());
            }
        }catch (Exception e){
            ToastUtil.showErrorToast(getActivity(),R.string.error);
        }

    }

    @Override
    public void selectedValueSingleChoice(int position) {

    }

    @Override
    public void selectedValueSingleChoice(int position, String arrayType) {
        if (arrayType.equals("seating")){
            binding.spinner.setText(name.get(position));
            steat_type = ids.get(position);
        }
    }

    @Override
    public void selectedMultiChoicelang(ArrayList<String> choices, ArrayList<String> postions, String arrayType) {

    }

    public class SearchHandler{
        Context context ;

        public SearchHandler(Context context) {
            this.context = context;
        }
        public void profile(View v ){
            ((MainScreen)getActivity()).profile();
        }
        public void onFilter(View v){
            ((MainScreen)getActivity()).filter();
        }
        public void onSearch(View v){
            Date date = binding.singleDayPicker.getDate();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String strDate = dateFormat.format(date);
            DateFormat timeFormat = new SimpleDateFormat("hh:mm");
            String timeForm = timeFormat.format(date);

            if(count.equals("0")){
                ToastUtil.showErrorToast(getActivity() , R.string.select_count);
            }else {
                RequestBody body = null ;
                try {
                    body = MainApiBody.filterBooking(count , strDate ,timeForm , steat_type);
                }catch (Exception e){

                }
                presenter.onSearch(getActivity() , body);
            }
        }
        public void onCancel(View v){
            ((MainScreen)getActivity()).onCance();
        }

        public void seating(View v){
            try {
                if (name.size() > 0){
                    dialogUtil.showDialogSingleChooice(getActivity(), getString(R.string.seat_type), R.string.ok,name,"seating" );
                }else {
                    ToastUtil.showErrorToast(getActivity() , R.string.no_data);
                }
            }catch (Exception e){
                ToastUtil.showErrorToast(getActivity() , R.string.error);
            }


        }
        /**/
    }
}