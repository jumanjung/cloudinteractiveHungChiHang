package com.cloudinteractiveHungChiHang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cloudinteractiveHungChiHang.common.common;
import com.cloudinteractiveHungChiHang.img.DpUtil;
import com.cloudinteractiveHungChiHang.img.ImageCache;
import com.cloudinteractiveHungChiHang.img.ImageFetcher;
import com.cloudinteractiveHungChiHang.presenter.ItemPresenter;


public class ItemActivity extends AppCompatActivity implements ItemPresenter.View {
    ItemPresenter presenter;
    private TextView idText, titleText;
    private ImageView background;
    private ImageFetcher mImageFetcher;
    private static final String IMAGE_CACHE_DIR = "thumbs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        presenter = new ItemPresenter(this);
        ImageCache.ImageCacheParams cacheParams = new   ImageCache.ImageCacheParams(this, IMAGE_CACHE_DIR);
        cacheParams.setMemCacheSizePercent(0.25f);// Set memory cache to 25% of app memory

        // The ImageFetcher takes care of loading images into our ImageView children asynchronously
        mImageFetcher = new ImageFetcher(this, (int) DpUtil.dp2px(this, 100));
        mImageFetcher.setLoadingImage(R.mipmap.ic_launcher);
        mImageFetcher.addImageCache(cacheParams);
        setFindViewById();
        presenter.setData();
    }

    private void setFindViewById() {
            idText = findViewById(R.id.idText);
            titleText = findViewById(R.id.titleText);
            background = findViewById(R.id.background);

    }


    @Override
    public void setdetail() {
        idText.setText(common.currentItem_api.getId());
        titleText.setText(common.currentItem_api.getTitle());
        mImageFetcher.loadImage(common.currentItem_api.getThumbnailUrl(), background);
    }
}