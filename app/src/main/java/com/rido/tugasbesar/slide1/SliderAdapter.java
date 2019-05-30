package com.rido.tugasbesar.slide1;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rido.tugasbesar.R;

public class SliderAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;

//    private TextView slideHeading, slideDescription;
//    private ImageView slide_imageView;


    public SliderAdapter(Context context) {

        this.context = context;
    }

    // img Array
    public int[] image_slide ={
            R.mipmap.ic_book,
            R.mipmap.ic_catalogue,
            R.mipmap.ic_checklist,
            R.mipmap.ic_search,
            R.mipmap.ic_rating,
            R.mipmap.ic_student

    };

    // heading Array
    public String[] heading_slide ={
            "BUKU",
            "KATEGORI",
            "SINOPSIS",
            "PENCARIAN",
            "REVIEW",
            "PENGGUNA"
    };

    // description Array
    public String[] description_slide ={
            "Terdapat berbagai macam buku pada aplikasi ini",
            "Dan tersedia banyak kategori-kategori buku yang ter-update",
            "Untuk mengetahui gambaran umum dari suatu buku disuguhkan oleh adanya sinopsis",
            "Anda bisa mencari buku yang Anda inginkan",
            "Tentunya review sangat dibutuhkan sebagai pertimbangan Anda",
            "Anda harus terdaftar pada aplikasi terlebih dahulu"
    };




    @Override
    public int getCount() {

        return heading_slide.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container,false);
        container.addView(view);

        ImageView slide_imageView = view.findViewById(R.id.imageView1);
        TextView slideHeading = view.findViewById(R.id.tvHeading);
        TextView slideDescription = view.findViewById(R.id.tvDescription);

        slide_imageView.setImageResource(image_slide[position]);
        slideHeading.setText(heading_slide[position]);
        slideDescription.setText(description_slide[position]);

        return view;
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }

//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        View view = (View) object;
//        container.removeView(view);
//    }

}


