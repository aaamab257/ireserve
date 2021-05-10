package com.aaamab.bonappetit.ui.filter;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.AreaFilters;
import com.aaamab.bonappetit.data.FilterData;
import com.aaamab.bonappetit.data.FilterObj;
import com.aaamab.bonappetit.data.RestArray;
import com.aaamab.bonappetit.databinding.ActivityFilterScreenBinding;
import com.aaamab.bonappetit.ui.adapter.OtherFilterAdapter;
import com.aaamab.bonappetit.ui.filterOther.FilterOtherInterface;
import com.aaamab.bonappetit.ui.filterOther.FilterOtherPresenter;
import com.aaamab.bonappetit.ui.filterOther.FilterSearch;
import com.aaamab.bonappetit.ui.government.GovFilterInterface;
import com.aaamab.bonappetit.ui.government.GoverFilterPresenter;
import com.aaamab.bonappetit.ui.government.GovernmentFilter;
import com.aaamab.bonappetit.ui.government.Selectedinter;
import com.aaamab.bonappetit.ui.searchResult.SearchResult;
import com.aaamab.bonappetit.utils.IntentUtilies;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.ToastUtil;
import com.aaamab.bonappetit.utils.dialogs.DialogUtil;
import com.aaamab.bonappetit.utils.dialogs.DialogUtilResponse;
import com.aaamab.bonappetit.utils.network.ConnectionListener;
import com.aaamab.bonappetit.utils.network.ConnectionResponse;
import com.aaamab.bonappetit.utils.network.MainApi;
import com.aaamab.bonappetit.utils.network.MainApiBody;

import java.util.ArrayList;

import okhttp3.RequestBody;

public class FilterScreen extends AppCompatActivity implements DialogUtilResponse, GovFilterInterface, FilterOtherInterface {
    ActivityFilterScreenBinding binding;
    FilterHandler handler;
    DialogUtil dialogUtil;
    GoverFilterPresenter presenter;
    ArrayList<String> namesGove, namesAreas, namesMalls, namesCuis;
    ArrayList<Integer> idsGov, idsAreas, idsMalls, idsCuis;
    FilterOtherPresenter filterOtherPresenter;
    String sortBy = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_filter_screen);
        handler = new FilterHandler(this);
        binding.setHandler(handler);
        dialogUtil = new DialogUtil(this);
        presenter = new GoverFilterPresenter(this);
        presenter.onGovernment(this);
        filterOtherPresenter = new FilterOtherPresenter(this);
        namesGove = new ArrayList<>();
        namesAreas = new ArrayList<>();
        namesMalls = new ArrayList<>();
        namesCuis = new ArrayList<>();
        idsAreas = new ArrayList<>();
        idsGov = new ArrayList<>();
        idsMalls = new ArrayList<>();
        idsCuis = new ArrayList<>();

    }

    @Override
    public void selectedValueSingleChoice(int position) {

    }

    @Override
    public void selectedValueSingleChoice(int position, String arrayType) {

    }

    @Override
    public void selectedMultiChoicelang(ArrayList<String> choices, ArrayList<String> postions, String arrayType) {
        if (arrayType.equals("city")) {
            StaticMethods.GovernmentIds = postions;
            binding.txtGovSelected.setText(postions.size() + "");
            filterOtherPresenter.onArea(FilterScreen.this, postions);
        } else if (arrayType.equals("area")) {
            StaticMethods.ZoneIds = postions;
            binding.txtZoneText.setText(postions.size() + "");
            filterOtherPresenter.onMall(FilterScreen.this, postions);
            Log.e("TAG", "selectedMultiChoicelang:2 " + postions);
        } else if (arrayType.equals("mall")) {
            StaticMethods.MallIds = postions;
            binding.txtMallText.setText(postions.size() + "");
            Log.e("TAG", "selectedMultiChoicelang:3 " + postions);
        } else if (arrayType.equals("cuisines")) {
            StaticMethods.CuisIds = postions;
            binding.txtCuisText.setText(postions.size() + "");
            Log.e("TAG", "selectedMultiChoicelang:4 " + postions);
        }
    }

    @Override
    public void onSuccess(FilterData<FilterObj> data) {
        StaticMethods.Cuisines = data.getData().getCuisines();
        StaticMethods.Zones = data.getData().getZone();
        StaticMethods.Mall = data.getData().getMallss();
        StaticMethods.data = data;
        for (int i = 0; i < data.data.getCity().size(); i++) {
            namesGove.add(data.data.getCity().get(i).getName_en());
            idsGov.add(data.data.getCity().get(i).getId());
        }
    }

    @Override
    public void onArea(AreaFilters data) {
        StaticMethods.areaMallData = data;
        /*for (int i = 0; i < data.data.getZone().size(); i++) {
            namesAreas.add(data.data.getZone().get(i).getName_en());
            idsAreas.add(data.data.getZone().get(i).getId());
        }*/
    }

    @Override
    public void onMall(AreaFilters data) {
        StaticMethods.areaMallData = null ;
        StaticMethods.areaMallData = data;
       /* for (int i = 0; i < data.data.getMallss().size(); i++) {
            namesMalls.add(data.data.getMallss().get(i).getName_en());
            idsMalls.add(data.data.getMallss().get(i).getId());
        }*/
    }

    @Override
    public void onCuis(FilterData<FilterObj> data) {
        for (int i = 0; i < data.data.getCuisines().size(); i++) {
            namesCuis.add(data.data.getCuisines().get(i).getName_en());
            idsCuis.add(data.data.getCuisines().get(i).getId());
        }
    }

    @Override
    public void onFail(String error) {
        ToastUtil.showErrorToast(this, error);
    }

    @Override
    public void onConnection(boolean isConnected) {
        ToastUtil.showErrorToast(this, R.string.noConnection);
    }


    public class FilterHandler {
        Context context;

        public FilterHandler(Context context) {
            this.context = context;
        }

        public void onBack(View view) {
            onBackPressed();
        }

        public void onGovernment(View v) {
            Intent government = new Intent(FilterScreen.this, GovernmentFilter.class);
            startActivityForResult(government, 1000);
            //dialogUtil.showDialogWithMultiChoiceskills(FilterScreen.this, R.string.government, R.string.ok, namesGove, "city", idsGov);
            //IntentUtilies.openActivity(FilterScreen.this, GovernmentFilter.class);
        }
        @RequiresApi(api = Build.VERSION_CODES.M)
        public void rating(View v){
            sortBy = "Rating" ;
            binding.txtN.setTextColor(FilterScreen.this.getColor(R.color.normal));
            binding.txtRate.setTextColor(FilterScreen.this.getColor(R.color.colorActive));
        }
        @RequiresApi(api = Build.VERSION_CODES.M)
        public void name(View v){
            sortBy = "Name" ;
            binding.txtN.setTextColor(FilterScreen.this.getColor(R.color.colorActive));
            binding.txtRate.setTextColor(FilterScreen.this.getColor(R.color.normal));
        }
        public void onArea(View v) {

            Intent area = new Intent(FilterScreen.this, FilterSearch.class);
            area.putExtra("type","area");
            startActivityForResult(area, 2000);


            //bundle.putString("type","mall");
            /*if (namesAreas.size() == 0) {
                for (int i = 0; i < StaticMethods.Zones.size(); i++) {
                    namesAreas.add(StaticMethods.Zones.get(i).getName_en());
                    idsAreas.add(StaticMethods.Zones.get(i).getId());
                }
                dialogUtil.showDialogWithMultiChoiceskills(FilterScreen.this, R.string.area, R.string.ok, namesAreas, "area", idsAreas);
            } else {
                dialogUtil.showDialogWithMultiChoiceskills(FilterScreen.this, R.string.area, R.string.ok, namesAreas, "area", idsAreas);
            }*/
        }


    public void onMall(View v) {
        Intent area = new Intent(FilterScreen.this, FilterSearch.class);
        area.putExtra("type","mall");
        startActivityForResult(area, 3000);
            /*if (namesMalls.size() == 0) {
                for (int i = 0; i < StaticMethods.Mall.size(); i++) {
                    namesMalls.add(StaticMethods.Mall.get(i).getName_en());
                    idsMalls.add(StaticMethods.Mall.get(i).getId());
                }
                dialogUtil.showDialogWithMultiChoiceskills(FilterScreen.this, R.string.mall, R.string.ok, namesMalls, "mall", idsMalls);
            } else {
                dialogUtil.showDialogWithMultiChoiceskills(FilterScreen.this, R.string.mall, R.string.ok, namesMalls, "mall", idsMalls);
            }*/
            /*if(StaticMethods.GovernmentIds != null){
                Bundle bundle = new Bundle();
                bundle.putString("type","mall");
                IntentUtilies.openActivityWithBundle(FilterScreen.this, FilterSearch.class,bundle);
            }else {
                ToastUtil.showErrorToast(FilterScreen.this , "Please Select Government first");
            }*/

    }

    public void onCuisines(View v) {
        Intent area = new Intent(FilterScreen.this, FilterSearch.class);
        area.putExtra("type","cuis");
        startActivityForResult(area, 4000);
            /*if (namesCuis.size() == 0) {
                for (int i = 0; i < StaticMethods.Cuisines.size(); i++) {
                    namesCuis.add(StaticMethods.Cuisines.get(i).getName_en());
                    idsCuis.add(StaticMethods.Cuisines.get(i).getId());
                }
                dialogUtil.showDialogWithMultiChoiceskills(FilterScreen.this, R.string.cuisines, R.string.ok, namesCuis, "cuisines", idsCuis);
            } else {
                dialogUtil.showDialogWithMultiChoiceskills(FilterScreen.this, R.string.cuisines, R.string.ok, namesCuis, "cuisines", idsCuis);
            }*/
            /*if(StaticMethods.GovernmentIds != null){
                Bundle bundle = new Bundle();
                bundle.putString("type","cuis");
                IntentUtilies.openActivityWithBundle(FilterScreen.this, FilterSearch.class,bundle);
            }else {
                ToastUtil.showErrorToast(FilterScreen.this , "Please Select Government first");
            }*/

    }

    public void onReset(View v) {
            /*StaticMethods.GovernmentIds = null ;
            StaticMethods.CuisIds = null ;
            StaticMethods.ZoneIds = null ;
            StaticMethods.MallIds = null;
            IntentUtilies.openActivityInNewStack(FilterScreen.this , FilterScreen.class);*/
        recreate();
    }

    public void onApply(View v) {
        filterOtherPresenter.onFilter(FilterScreen.this , sortBy);
    }


}
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.onAttach(base));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1000:
                //government
                if (resultCode == RESULT_OK) {

                    if (StaticMethods.GovernmentIds != null) {
                        if (StaticMethods.GovernmentIds.size() > 0) {
                            binding.txtGovSelected.setText(StaticMethods.GovernmentIds.size() +"");
                            filterOtherPresenter.onArea(FilterScreen.this, StaticMethods.GovernmentIds);
                        }else {
                            StaticMethods.GovernmentIds.add("0");
                        }
                    }else {
                        StaticMethods.GovernmentIds = new ArrayList<>();
                        StaticMethods.GovernmentIds.add("0");
                    }

                }
                break;
            case 2000:
                //area
                if (resultCode == RESULT_OK) {
                    if (StaticMethods.ZoneIds != null) {
                        if (StaticMethods.ZoneIds.size() > 0) {
                            binding.txtZoneText.setText(StaticMethods.ZoneIds.size() + "");
                            filterOtherPresenter.onMall(FilterScreen.this, StaticMethods.ZoneIds);
                        }
                    }else {
                        StaticMethods.ZoneIds.add("0");
                    }
                }else {
                    StaticMethods.ZoneIds = new ArrayList<>();
                    StaticMethods.ZoneIds.add("0");
                }
                break;
            case 3000:
                //malls
                if (resultCode == RESULT_OK) {
                    if (StaticMethods.MallIds != null) {
                        if (StaticMethods.MallIds.size() > 0) {
                            binding.txtMallText.setText(StaticMethods.MallIds.size() + "");
                            //filterOtherPresenter.o(FilterScreen.this, StaticMethods.ZoneIds);
                        } else {
                            StaticMethods.MallIds.add("0");
                        }
                    } else {
                        StaticMethods.MallIds = new ArrayList<>();
                        StaticMethods.MallIds.add("0");
                    }
                }
                break;
            case 4000:
                //cusi
                if (resultCode == RESULT_OK) {
                    if (StaticMethods.CuisIds != null) {
                        if (StaticMethods.CuisIds.size() > 0) {
                            binding.txtCuisText.setText(StaticMethods.CuisIds.size() + "");
                            //filterOtherPresenter.o(FilterScreen.this, StaticMethods.ZoneIds);
                        }else {
                            StaticMethods.CuisIds.add("0");
                        }
                    } else {
                        StaticMethods.CuisIds = new ArrayList<>();
                        StaticMethods.CuisIds.add("0");
                    }
                }
                break;
        }
    }
}