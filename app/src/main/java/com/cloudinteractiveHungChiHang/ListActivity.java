package com.cloudinteractiveHungChiHang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.cloudinteractiveHungChiHang.Interface.Callback_WebApi;
import com.cloudinteractiveHungChiHang.common.common;
import com.cloudinteractiveHungChiHang.img.DpUtil;
import com.cloudinteractiveHungChiHang.img.ImageCache;
import com.cloudinteractiveHungChiHang.img.ImageFetcher;
import com.cloudinteractiveHungChiHang.model.Item_api;
import com.cloudinteractiveHungChiHang.presenter.ListPresenter;
import com.cloudinteractiveHungChiHang.presenter.MainPresenter;
import com.cloudinteractiveHungChiHang.webapi.Api_pub;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;


public class ListActivity extends AppCompatActivity implements Callback_WebApi, ListPresenter.View {
    private ListPresenter presenter;
    private RecyclerView list;
    private ImageFetcher mImageFetcher;
    private static final String IMAGE_CACHE_DIR = "thumbs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        presenter = new ListPresenter(this);
        ImageCache.ImageCacheParams cacheParams = new   ImageCache.ImageCacheParams(this, IMAGE_CACHE_DIR);
        cacheParams.setMemCacheSizePercent(0.25f);// Set memory cache to 25% of app memory
        // The ImageFetcher takes care of loading images into our ImageView children asynchronously
        mImageFetcher = new ImageFetcher(this, (int) DpUtil.dp2px(this, 100));
        mImageFetcher.setLoadingImage(R.mipmap.ic_launcher);
        mImageFetcher.addImageCache(cacheParams);
        setFindViewById();
        list.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
        presenter.RequestApi();
    }

    private void setFindViewById() {
        list = findViewById(R.id.list);
    }

    @Override
    public void success(JSONArray para, String ApiName) throws JSONException {
        final JSONObject data = (JSONObject) para.get(0);
        if(ApiName.equals("webapiCall")){
            itemAdapter adapter = new itemAdapter(para,getApplicationContext());
            list.setHasFixedSize(true);
            list.setItemViewCacheSize(100);
            list.setDrawingCacheEnabled(true);
            list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_AUTO);
            list.setAdapter(adapter);
        }
    }

    @Override
    public void fail(JSONObject para, String ApiName) {

    }
    @Override
    public void onBackPressed() {
        finish();

    }

    @Override
    public void webapi_call() {
        Api_pub api = new Api_pub(this);
        Map<String, String> data = new HashMap<>();
        api.webapiCall(common.targetURLBase, data);
    }

    public class itemAdapter extends RecyclerView.Adapter<itemAdapter.Holder> {
        private JSONArray listdata;
        private Context context;



        public itemAdapter(JSONArray listdata, Context context) {
            this.listdata = listdata;
            this.context = context;

        }

        @Override
        public itemAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new itemAdapter.Holder(LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false));
        }

        @Override
        public void onBindViewHolder(itemAdapter.Holder holder, final int position) {
            Gson gson = new Gson();
            Item_api item_api = new Item_api();
            try {
                item_api = gson.fromJson(listdata.getJSONObject(position).toString(), Item_api.class);
                holder.idText.setText(item_api.getId());
                holder.titleText.setText(item_api.getTitle());
                mImageFetcher.loadImage(item_api.getThumbnailUrl(), holder.background);
                Item_api finalItem_api = item_api;
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, ItemActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        common.currentItem_api = finalItem_api;
                        startActivity(intent);
                    }
                });


            } catch (JSONException e) {
                e.printStackTrace();
            }




        }

        @Override
        public int getItemCount() {
            return listdata.length();
        }

        class Holder extends RecyclerView.ViewHolder {
            private TextView idText, titleText;
            private ImageView background;

            public Holder(View itemView) {
                super(itemView);
                idText = itemView.findViewById(R.id.idText);
                titleText = itemView.findViewById(R.id.titleText);
                background = itemView.findViewById(R.id.background);
            }
        }

    }
}
