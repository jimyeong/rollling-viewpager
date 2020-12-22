package greenuts.com.vp;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class RollingViewPager extends FrameLayout implements ViewPager.OnPageChangeListener {

    private int DURATION = 0;
    private int PAGE_NUMBER = 0;
    private LayoutInflater lif;
    private Context context;
    private ViewPager vp;
    private RollingPagerAdapter rvp;
    private Timer timer;
    private Handler handler;
    private View button;

    public RollingViewPager(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        lif = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 레이아웃에 적용한 속성들 받아온다.
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.RollingViewPager, 0, 0);
        try {
            // 레이아웃에 적용한 속성들 받아온다.
            this.DURATION = a.getInteger(R.styleable.RollingViewPager_duration, 3000);
            this.PAGE_NUMBER = a.getInteger(R.styleable.RollingViewPager_pageNumber, 0);
        } finally {
            a.recycle();
        }
    }
    @Override
    protected void onFinishInflate() {
        // 뷰그리기
        View view = lif.inflate(R.layout.layout_viewpager, this, false);
        button = lif.inflate(R.layout.slide_page_numbering_button, this, false);

        // button 페이지 노출
        ((Button)button).setText(RollingViewPager.this.PAGE_NUMBER +" 개의 페이지 모두보기");
        vp = view.findViewById(R.id.vp);

        // 어댑터 생성 및 적용
        rvp = new RollingPagerAdapter(this.context, createData(), this.PAGE_NUMBER);
        vp.setAdapter(rvp);
        // 리스터 연결
        vp.addOnPageChangeListener(this);

        // 레이아웃들 추가
        this.addView(view);
        this.addView(button);
        this.startSlidePage();
        super.onFinishInflate();
    }
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    // 슬라읻드를 동작시켜주는 메서드
    public void startSlidePage(){
        this.handler = new android.os.Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                RollingViewPager.this.PAGE_NUMBER++;
                if(RollingViewPager.this.PAGE_NUMBER == Integer.MAX_VALUE){
                    RollingViewPager.this.PAGE_NUMBER = 0;
                }
                vp.setCurrentItem(RollingViewPager.this.PAGE_NUMBER, true);
                ((Button)button).setText(RollingViewPager.this.PAGE_NUMBER +" 개의 페이지 모두보기");
            }
        };
        this.timer = new Timer();
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                RollingViewPager.this.handler.post(runnable);
            }
        }, 0, 3000);

    }
    // 데이터 생성을 위한 메서드
    public List<Color> createData(){
        List<Color> list= new ArrayList<>();
        for(int i=0; i < 5; i++){
            list.add(new Color(i));
        }
        return list;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        // 스크롤이 발생할 떄, 현재 가르키는 페이지와 스크롤 되서 넘어간 페이지의 페이지수를 맞춰준다.
        RollingViewPager.this.PAGE_NUMBER = position;
        ((Button)button).setText(RollingViewPager.this.PAGE_NUMBER +" 개의 페이지 모두보기");
    }

    @Override
    public void onPageSelected(int position) {


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    // 어댑터
    public class RollingPagerAdapter extends PagerAdapter {

        // 결합도를 낮추기위해서, 변수설정은 여기에서 한다.
        private Context context;
        private List<Color> list = new ArrayList<>();
        private int MAX_PAGE = Integer.MAX_VALUE;
        private LayoutInflater llf;
        private int pageNumber = 0;
        public RollingPagerAdapter(Context context, List<Color> list, int pageNumber) {
            this.llf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.context = context;
            this.list = list;
            this.pageNumber = pageNumber;
        }
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            // 뷰와 관련된 설정 셋팅
            // 넘어가는 페이지가 인티저 최대 범위를 넘어가면 0으로 초기화
            if(position == MAX_PAGE){
                position = 0;
            }
            // 페이지 숫자로 현재 페이지수를 나눈다. 그럼 0< x < 5 의 범위가 나오는데,
            // 이걸로 현재 보여줄 페이지수를 컨트롤 한다.
            Color item = this.list.get(position%this.pageNumber);
            View view = this.llf.inflate(R.layout.pager_item, container, false);
            view.findViewById(R.id.imageview)
                    .setBackgroundColor(android.graphics.Color.parseColor(item.getColorHex()));
            container.addView(view);
            return view;
        }

        // 페이지가 끝에 도달했을 때, 되감기 동작을 보여주지 않기 위해서, 인티저최고 범위로 숫잦를 설정한다.
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        // 무슨 메서드지.. 찾아봐야겠다.
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == (View) object;
        }

        // page가 destory 될때 호출 된다.
        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}
