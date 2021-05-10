package com.aaamab.bonappetit.ui.favorites;

import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.FavoritesData;
import com.aaamab.bonappetit.databinding.FragmentFavoriteScreenBinding;
import com.aaamab.bonappetit.ui.adapter.FavoriteAdapter;
import com.aaamab.bonappetit.ui.adapter.RecyclerItemTouchHelper;
import com.aaamab.bonappetit.utils.CustomDialog;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.google.android.material.snackbar.Snackbar;

import static com.facebook.FacebookSdk.getApplicationContext;


public class FavoriteScreen extends Fragment implements FavInter , RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    FragmentFavoriteScreenBinding binding;
    View v;
    FavoritesHandler handler;
    FavoriteAdapter adapter;
    CustomDialog dialog;
    FavPresenter presenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite_screen, container, false);
        v = binding.getRoot();
        handler = new FavoritesHandler(getActivity());
        binding.setHandler(handler);
        presenter = new FavPresenter(this);

        dialog = new CustomDialog(getActivity());
        //dialog.showDialog();
        presenter.getFavorites(StaticMethods.userData.api_token, getActivity());
        return v;
    }

    @Override
    public void onSuccess(FavoritesData data) {
        dialog.dismissDialog();

        if (data.getFa().size() > 0) {
            adapter = new FavoriteAdapter(getActivity(), data);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            binding.recFavorite.setLayoutManager(mLayoutManager);
            binding.recFavorite.setItemAnimator(new DefaultItemAnimator());
            binding.recFavorite.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
            binding.recFavorite.setAdapter(adapter);
            ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
            new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(binding.recFavorite);
        } else {
            binding.txtNoData.setText(R.string.no_fav);
        }

    }

    @Override
    public void onFail(String error) {
        dialog.dismissDialog();
        binding.txtNoData.setText(error);
    }

    @Override
    public void onConnection(boolean isConnected) {
        dialog.dismissDialog();
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof FavoriteAdapter.MyViewHolder) {
            // get the removed item name to display it in snack bar
            adapter.removeItem(viewHolder.getAdapterPosition());

        }
    }

    public class FavoritesHandler {
        Context context;

        public FavoritesHandler(Context context) {
            this.context = context;
        }
    }
}