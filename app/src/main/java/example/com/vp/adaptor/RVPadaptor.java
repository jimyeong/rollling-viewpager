package example.com.vp.adaptor;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

import example.com.vp.MainActivity;
import example.com.vp.R;
import example.com.vp.model.Color;

public class RVPadaptor extends PagerAdapter {
    private Context context;
    private List<? extends Color> viewData = new ArrayList();
    private LayoutInflater llf;
    private int CURRENT_POSITION = 0;
    private int PAGE_NUMBER = Integer.MAX_VALUE;


    public RVPadaptor(Context context, List<? extends Color> list) {
        llf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.viewData = list;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // slide page 랑 연관이 되있음
        if (this.CURRENT_POSITION == PAGE_NUMBER) {
            this.CURRENT_POSITION = 0;
        };
        this.CURRENT_POSITION++;
        int slideNumber = this.CURRENT_POSITION%4;

        Color color = this.viewData.get(slideNumber);
        View itemView = llf.inflate(R.layout.view_pager_item, container, false);
        // to use color hex value
        itemView.setBackgroundColor(android.graphics.Color.parseColor(color.getColorHex()));
        container.addView(itemView);

        return itemView;
    }

    // 무한번으로 돌리고 싶으면 여기에 INTEGER_MAX_VALUE를 넣으면 된다.
    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (View) object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
