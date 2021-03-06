package admin.ats.com.focaloidproject.Fragments;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

import admin.ats.com.focaloidproject.Adapter.ProductAdapter;
import admin.ats.com.focaloidproject.Model.JSONResponse;
import admin.ats.com.focaloidproject.Model.Products;
import admin.ats.com.focaloidproject.ProductAPI;
import admin.ats.com.focaloidproject.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 3/29/2017.
 */

public class Menu_Home extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Products> pdt;
    private ProductAdapter adapter;

    public static Menu_Home newInstance()
    {
        Menu_Home menu_home=new Menu_Home();
        return menu_home;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_home,null);

        recyclerView=(RecyclerView)rootView.findViewById(R.id.rv);
        final FragmentActivity c = getActivity();
        GridLayoutManager layoutManager = new GridLayoutManager(c,2);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        loadJSON();
        return rootView;
    }

    private void loadJSON() {
        final ProgressDialog loading = ProgressDialog.show(getContext(),"Fetching Data","Please wait...",false,false);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://focalloid.com/Distributor_app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ProductAPI request = retrofit.create(ProductAPI.class);
        Call<JSONResponse> call = request.getJSON();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                    JSONResponse jsonrespnse=response.body();
                pdt=new ArrayList<>(Arrays.asList(jsonrespnse.getData()));
                adapter=new ProductAdapter(pdt);
                recyclerView.setAdapter(adapter);
                loading.dismiss();
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("JASONERROR",t.getMessage());

            }
        });
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Home");
    }



    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    @Override
    public String toString() {
        return "Menu_Home" ;
    }
}
