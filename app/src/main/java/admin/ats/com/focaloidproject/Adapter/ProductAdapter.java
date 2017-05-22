package admin.ats.com.focaloidproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;

import android.support.test.espresso.core.deps.guava.base.Splitter;
import android.support.test.espresso.core.deps.guava.collect.Lists;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import admin.ats.com.focaloidproject.Activities.Product_detail;
import admin.ats.com.focaloidproject.Model.Products;
import admin.ats.com.focaloidproject.R;

/**
 * Created by Anju on 25-04-2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private ArrayList<Products>pdts;
    private Context c;


    public ProductAdapter(ArrayList<Products> pdts) {

        this.pdts = pdts;
    }


    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        c=parent.getContext();
        View rootView=LayoutInflater.from(c).inflate(R.layout.cardview,parent,false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder holder, int position) {
        String img_URL="http://focalloid.com/Distributor_app/images/product_images_L/";
        final String name=pdts.get(position).getPro_name();
        final String price=Double.toString(pdts.get(position).getPro_price());
        final String desc=pdts.get(position).getPro_desc();
        final String imag=pdts.get(position).getProduct_images();
        String URL="";

        Log.d("name",name);

        holder.title.setText(name);
        holder.cost.setText(" Price :" +price);
        if (pdts.get(position).getProduct_images()!=null){
                 String images=pdts.get(position).getProduct_images();
                 List<String> list = Lists.newArrayList(Splitter.on(",").splitToList(images));

                 String img=list.get(0);

            // loading album cover using Glide library
                 Glide.with(c).load(img_URL+img).into(holder.image);

        }
        final String finalURL=URL;
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c,name,Toast.LENGTH_LONG).show();
                Bundle product_details=new Bundle();
                product_details.putString("Name",name);
                product_details.putString("Price",price);
                product_details.putString("Desc",desc);
                product_details.putString("Image",imag);
                product_details.putString("Url", finalURL);

                Intent prd_intent=new Intent(c,Product_detail.class);
                prd_intent.putExtras(product_details);
                c.startActivity(prd_intent);

            }
        });

    }

    @Override
    public int getItemCount() {

        return pdts.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView cost,title;
        private ImageView image;
        private CardView cardview;
        public ViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.pdtname);
            cost=(TextView)itemView.findViewById(R.id.pdtprice);
            image=(ImageView)itemView.findViewById(R.id.pdtimage);
            cardview=(CardView)itemView.findViewById(R.id.cv);
        }
    }


}
