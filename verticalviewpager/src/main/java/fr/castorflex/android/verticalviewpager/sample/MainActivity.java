package fr.castorflex.android.verticalviewpager.sample;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final float MIN_SCALE = 0.75f;
    private static final float MIN_ALPHA = 0.75f;
    private VerticalViewPager mVerticalViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVerticalViewPager = (VerticalViewPager) findViewById(R.id.verticalviewpager);

//        verticalViewPager.setAdapter(new DummyAdapter(getFragmentManager()));
//        verticalViewPager.setAdapter(new DummyAdapter(getSupportFragmentManager()));
        mVerticalViewPager.setAdapter(new DummyAdapter2());
        mVerticalViewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.pagemargin));
        mVerticalViewPager.setPageMarginDrawable(new ColorDrawable(getResources().getColor(android.R.color.holo_green_dark)));

        mVerticalViewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View view, float position) {
                int pageWidth = view.getWidth();
                int pageHeight = view.getHeight();

                if (position < -1) { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    view.setAlpha(0);

                } else if (position <= 1) { // [-1,1]
                    // Modify the default slide transition to shrink the page as well
                    float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                    float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                    float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                    if (position < 0) {
                        view.setTranslationY(vertMargin - horzMargin / 2);
                    } else {
                        view.setTranslationY(-vertMargin + horzMargin / 2);
                    }

                    // Scale the page down (between MIN_SCALE and 1)
                    view.setScaleX(scaleFactor);
                    view.setScaleY(scaleFactor);

                    // Fade the page relative to its size.
                    view.setAlpha(MIN_ALPHA +
                            (scaleFactor - MIN_SCALE) /
                                    (1 - MIN_SCALE) * (1 - MIN_ALPHA));

                } else { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    view.setAlpha(0);
                }
            }
        });
    }

    public class DummyAdapter extends FragmentPagerAdapter {

        public DummyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return "PAGE 1";
                case 1:
                    return "PAGE 2";
                case 2:
                    return "PAGE 3";
            }
            return null;
        }

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_layout, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.textview);
            textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }


    }



    //---------------------
    public class DummyAdapter2 extends PagerAdapter {

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3 + 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            //------------------------------------
            /**
             *    这个position是当前页面的下一个页面的缓存，若是想做滑动，position=当前item-1反之则加一
             *    举个例子这个viewpager总共包含了imagesize+2=7个页面
             *    positio=5时 position % ImageSize=0，切换到了第一个position
             */
//
//            position %= ImageSize;
//            View view = mInflater.inflate(R.layout.item, container, false);
//            ImageView imageView = (ImageView) view.findViewById(R.id.meizi);
//            TextView textView = (TextView) view.findViewById(R.id.name);
//            imageView.setImageResource(mImagesSrc[position]);
//            textView.setText(GRILS[position]);
//            final int pos = position;
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(BannerActivity.this, "点击item :" + pos, Toast.LENGTH_SHORT).show();
//                }
//            });
//
//            container.addView(view);
//            return view;
            //------------------------------------

            position %= 3;
            View rootView = LayoutInflater.from(MainActivity.this).inflate(R.layout.fragment_layout, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.textview);
            textView.setText(Integer.toString(position));

            container.addView(rootView);
            return rootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView(container.findViewById(position));
//            container.removeView(mAnchorList.get(position % mAnchorList.size()));
//            container.removeView(mViewPageres.get(position % mAnchorList.size()));

            container.removeView((View) object);
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            int position = mVerticalViewPager.getCurrentItem();
            /**
             *  第五这里获得当前的positon然后对其setCurrentItem进行变换
             *  这里设置当position=0时把position设置为图片列表的最大值
             *  是为了position=0时左滑显示最后一张，我举个例子这里ImageSize是5
             *  当position==0时设置为5，左滑就是position=4，也就是第五张图片，
             *
             *  if (position == (ImageSize+2) - 1)
             *  这个判断 (ImageSize+2)这个是给viewpager设置的页面数，这里是7
             *  当position==7-1=6时，这时viewpager就滑到头了，所以把currentItem设置为1
             *  这里设置为1还是为了能够左滑，这时左滑position=0又执行了第一个判断又设置为5，
             *  这样就实现了无限轮播的效果
             *  setCurrentItem(position,false);
             *  这里第二个参数false是消除viewpager设置item时的滑动动画，不理解的去掉它运行下就知道啥意思了
             *
             */
            Log.i("DDD", "finishUpdate: "+position);
            if (position == 0) {
                position = 3;
                mVerticalViewPager.setCurrentItem(position,false);
            } else if (position == (3+2) - 1) {
                position = 1;
                mVerticalViewPager.setCurrentItem(position,false);
            }
        }

    }
    //---------------------------------


}
