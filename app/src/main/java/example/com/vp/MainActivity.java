package example.com.vp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import example.com.vp.adaptor.RVPadaptor;
import example.com.vp.base.BaseActivity;
import example.com.vp.model.Color;

// 재미잇을 정도로 말고 지겨울 정도로,,

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    // 변수들 가져와서 그냥쓰면 안되고, 여기서 한번 다 새로 변수에 담아줘야 한다.
    // loose coupling 을 위해서
    private ViewPager vp;
    private List<Color> data = new ArrayList<>();
    private RVPadaptor rvPadaptor;
    private int PAGE_NUMBER = Color.getNumberOfColor();
    private int DELAYED_TIME = 3000;
    private int DURATION = 1000;
    private Handler handler;
    private Timer timer;

    private int CURRENT_PAGE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp= findViewById(R.id.vp);

        rvPadaptor = new RVPadaptor(this, createData());
    }
    @Override
    protected void onStart() {
        super.onStart();
        vp.setAdapter(rvPadaptor);
        createSlide();
    }
    private List<Color> createData(){
        for (int i=0; i < PAGE_NUMBER; i++){
            data.add( new Color(i));
        }
        return this.data;
    }
    private void createSlide(){
        handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //
                MainActivity.this.CURRENT_PAGE++;
                if(MainActivity.this.CURRENT_PAGE == Integer.MAX_VALUE){
                    MainActivity.this.CURRENT_PAGE = 0;
                }
                vp.setCurrentItem(MainActivity.this.CURRENT_PAGE, true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, DELAYED_TIME, DURATION);

    }
    public void initSlide(){
        if(handler != null){
            handler.removeMessages(0);
            timer.cancel();
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        this.CURRENT_PAGE = position;
        initSlide();

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}