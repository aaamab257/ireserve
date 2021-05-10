package com.aaamab.bonappetit.ui.filterOther;

import android.content.Context;
import android.util.Log;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.AreaFilters;
import com.aaamab.bonappetit.data.Cuisines;
import com.aaamab.bonappetit.data.FilterData;
import com.aaamab.bonappetit.data.FilterObj;
import com.aaamab.bonappetit.data.RestArray;
import com.aaamab.bonappetit.ui.filter.FilterScreen;
import com.aaamab.bonappetit.ui.searchResult.SearchResult;
import com.aaamab.bonappetit.utils.IntentUtilies;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.ToastUtil;
import com.aaamab.bonappetit.utils.network.ConnectionListener;
import com.aaamab.bonappetit.utils.network.ConnectionResponse;
import com.aaamab.bonappetit.utils.network.MainApi;
import com.aaamab.bonappetit.utils.network.MainApiBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

import static android.content.ContentValues.TAG;

public class FilterOtherPresenter {
    FilterOtherInterface filter;

    public FilterOtherPresenter(FilterOtherInterface filter) {
        this.filter = filter;
    }

    public void onArea(Context context, ArrayList<String> ids) {
        boolean net = StaticMethods.isConnectingToInternet(context);
        if (net) {
            Map<String, String> params = new HashMap<String, String>();

            for (int i = 0; i < ids.size(); i++) {
                params.put("city_id[" + i + "]", ids.get(i));

            }
            MainApi.getAreaMallS(StaticMethods.userData.getApi_token(), LocaleManager.getLanguage(context), params, new ConnectionListener<AreaFilters>() {
                @Override
                public void onSuccess(ConnectionResponse<AreaFilters> connectionResponse) {
                    if (connectionResponse.data.code == 200) {
                        filter.onArea(connectionResponse.data);
                    }
                    StaticMethods.printJson("data", connectionResponse.data);
                }

                @Override
                public void onFail(Throwable throwable) {
                    filter.onFail(throwable.getMessage());
                    Log.e(TAG, "onFail: " + throwable.getMessage());
                }
            });
        } else {

        }
    }

    public void onMall(Context context, ArrayList<String> ids) {
        boolean net = StaticMethods.isConnectingToInternet(context);
        if (net) {
            Map<String, String> params = new HashMap<String, String>();

            for (int i = 0; i < ids.size(); i++) {
                params.put("zone_id[" + i + "]", ids.get(i));

            }

            MainApi.getMall(StaticMethods.userData.getApi_token(), LocaleManager.getLanguage(context), params, new ConnectionListener<AreaFilters>() {
                @Override
                public void onSuccess(ConnectionResponse<AreaFilters> connectionResponse) {
                    if (connectionResponse.data.code == 200) {
                        filter.onMall(connectionResponse.data);
                        Log.e(TAG, "onMall: " + "params");
                    }

                    /*StaticMethods.printJson("data" , connectionResponse.data);*/
                }

                @Override
                public void onFail(Throwable throwable) {
                    filter.onFail(throwable.getMessage());
                    Log.e(TAG, "onFail: " + throwable.getMessage());
                }
            });
        } else {

        }
    }

    public void onFilter(final Context context , String sortBy) {
        boolean net = StaticMethods.isConnectingToInternet(context);
        if (net) {
            Map<String, String> params = new HashMap<String, String>();
            if (StaticMethods.GovernmentIds == null) {
                StaticMethods.GovernmentIds = new ArrayList<>();
                StaticMethods.GovernmentIds.add("0");
                /*for (int i = 0; i < StaticMethods.GovernmentIds.size(); i++) {
                    params.put("city_id["+i+"]",  StaticMethods.GovernmentIds.get(i));

                }*/
            }
            if (StaticMethods.CuisIds == null) {
                StaticMethods.CuisIds = new ArrayList<>();
                StaticMethods.CuisIds.add("0");
                /*for (int i = 0; i < StaticMethods.CuisIds.size(); i++) {
                    params.put("cuisines_id["+i+"]", StaticMethods.CuisIds.get(i));

                }*/
            }
            if (StaticMethods.ZoneIds == null) {
                StaticMethods.ZoneIds = new ArrayList<>();
                StaticMethods.ZoneIds.add("0");
            }
            if (StaticMethods.MallIds == null) {
                StaticMethods.MallIds = new ArrayList<>();
                StaticMethods.MallIds.add("0");
            }


            if (StaticMethods.GovernmentIds != null) {
                for (int i = 0; i < StaticMethods.GovernmentIds.size(); i++) {
                    params.put("city_id[" + i + "]", StaticMethods.GovernmentIds.get(i));

                }
            }
            if (StaticMethods.CuisIds != null) {
                for (int i = 0; i < StaticMethods.CuisIds.size(); i++) {
                    params.put("cuisines_id[" + i + "]", StaticMethods.CuisIds.get(i));

                }
            }
            if (StaticMethods.ZoneIds != null) {
                for (int i = 0; i < StaticMethods.ZoneIds.size(); i++) {
                    params.put("zone_id[" + i + "]", StaticMethods.ZoneIds.get(i));

                }
            }
            if (StaticMethods.MallIds != null) {
                for (int i = 0; i < StaticMethods.MallIds.size(); i++) {
                    params.put("mall_id[" + i + "]", StaticMethods.MallIds.get(i));
                }
            }
            //here
            if (!sortBy.isEmpty()){
                params.put("Sort_by", sortBy);
            }


            MainApi.postFilter(StaticMethods.userData.getApi_token(), LocaleManager.getLanguage(context), params, new ConnectionListener<RestArray>() {
                @Override
                public void onSuccess(ConnectionResponse<RestArray> connectionResponse) {
                    if (connectionResponse.data.code == 200) {
                        StaticMethods.restArray = connectionResponse.data;
                        IntentUtilies.openActivity(context, SearchResult.class);
                    }

                }

                @Override
                public void onFail(Throwable throwable) {
                    Log.e("TAG", "onFailTh" + throwable.getMessage());
                    ToastUtil.showErrorToast(context, context.getString(R.string.error));
                }
            });
        } else {
            ToastUtil.showErrorToast(context, "No internet connection");
        }
    }
}
