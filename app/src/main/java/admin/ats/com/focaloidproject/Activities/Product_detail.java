package admin.ats.com.focaloidproject.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import admin.ats.com.focaloidproject.R;

public class Product_detail extends AppCompatActivity {
    String name,price,desc,Url,img;
    TextView txt_name,txt_price,txt_desc;
    ImageView imageView;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        imageView=(ImageView)findViewById(R.id.pdtdesc_img);
        txt_name=(TextView)findViewById(R.id.pdtdesc_name);
        txt_price=(TextView)findViewById(R.id.pdtdesc_price);
        txt_desc=(TextView)findViewById(R.id.pdtdescrptn);


        Intent i=getIntent();
        Bundle bnd_prd_details=i.getExtras();
        name=bnd_prd_details.getString("Name");
        price=bnd_prd_details.getString("Price");
        desc=bnd_prd_details.getString("Desc");
        Url=bnd_prd_details.getString("Url");
        img=bnd_prd_details.getString("Image");
        Log.d("Url",Url);
        txt_name.setText(name);
        txt_price.setText(price);
        txt_desc.setText(Html.fromHtml(desc));
        Glide.with(getApplicationContext()).load(Url).into(imageView);

    }
}
