package yangba.test.user.activity;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import yangba.test.user.R;


public class MainActivity extends Activity implements
		View.OnClickListener {

	private ViewPager mViewPager;
	private PagerAdapter mPagerAdapter;
	private List<View> mViews = new ArrayList<View>();
	// �ĸ�Tab��ÿ��Tab����һ����ť
	private LinearLayout mTabWeiXin;
	private LinearLayout mTabAddress;
	private LinearLayout mTabFrd;
	private LinearLayout mTabSetting;
	// �ĸ���ť
	private ImageButton mWeiXinImg;
	private ImageButton mAddressImg;
	private ImageButton mFrdImg;
	private ImageButton mSettingImg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initView();
		initViewPage();
		initEvent();
	}

	private void initEvent() {
		mTabWeiXin.setOnClickListener(this);
		mTabAddress.setOnClickListener(this);
		mTabFrd.setOnClickListener(this);
		mTabSetting.setOnClickListener(this);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
            /**
            *ViewPage���һ���ʱ
            */
			@Override
			public void onPageSelected(int arg0) {
				int currentItem = mViewPager.getCurrentItem();
				switch (currentItem) {
				case 0:
					 resetImg();
					mWeiXinImg.setImageResource(R.mipmap.tab_weixin_pressed);
					break;
				case 1:
					 resetImg();
					mAddressImg.setImageResource(R.mipmap.tab_address_pressed);
					break;
				case 2:
					 resetImg();
					mFrdImg.setImageResource(R.mipmap.tab_find_frd_pressed);
					break;
				case 3:
					 resetImg();
					mSettingImg.setImageResource(R.mipmap.tab_settings_pressed);
					break;
				default:
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}

	private void initView() {
		mViewPager = (ViewPager) findViewById(R.id.id_viewpage);

		mTabWeiXin = (LinearLayout) findViewById(R.id.id_tab_weixin);
		mTabAddress = (LinearLayout) findViewById(R.id.id_tab_address);
		mTabFrd = (LinearLayout) findViewById(R.id.id_tab_frd);
		mTabSetting = (LinearLayout) findViewById(R.id.id_tab_settings);
		// ��ʼ���ĸ���ť
		mWeiXinImg = (ImageButton) findViewById(R.id.id_tab_weixin_img);
		mAddressImg = (ImageButton) findViewById(R.id.id_tab_address_img);
		mFrdImg = (ImageButton) findViewById(R.id.id_tab_frd_img);
		mSettingImg = (ImageButton) findViewById(R.id.id_tab_settings_img);
	}


	private void initViewPage() {


		LayoutInflater mLayoutInflater = LayoutInflater.from(this);
		View tab01 = mLayoutInflater.inflate(R.layout.tab01, null);
		View tab02 = mLayoutInflater.inflate(R.layout.tab02, null);
		View tab03 = mLayoutInflater.inflate(R.layout.tab03, null);
		View tab04 = mLayoutInflater.inflate(R.layout.tab04, null);

		mViews.add(tab01);
		mViews.add(tab02);
		mViews.add(tab03);
		mViews.add(tab04);

		// ��������ʼ��������
		mPagerAdapter = new PagerAdapter() {

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView(mViews.get(position));

			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				View view = mViews.get(position);
				container.addView(view);
				return view;
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {

				return arg0 == arg1;
			}

			@Override
			public int getCount() {

				return mViews.size();
			}
		};
		mViewPager.setAdapter(mPagerAdapter);
	}

	/**
	 * �ж��ĸ�Ҫ��ʾ�������ð�ťͼƬ
	 */
	@Override
	public void onClick(View arg0) {

		switch (arg0.getId()) {
		case R.id.id_tab_weixin:
			mViewPager.setCurrentItem(0);
			resetImg();
			mWeiXinImg.setImageResource(R.mipmap.tab_weixin_pressed);
			break;
		case R.id.id_tab_address:
			mViewPager.setCurrentItem(1);
			resetImg();
			mAddressImg.setImageResource(R.mipmap.tab_address_pressed);
			break;
		case R.id.id_tab_frd:
			mViewPager.setCurrentItem(2);
			resetImg();
			mFrdImg.setImageResource(R.mipmap.tab_find_frd_pressed);
			break;
		case R.id.id_tab_settings:
			mViewPager.setCurrentItem(3);
			resetImg();
			mSettingImg.setImageResource(R.mipmap.tab_settings_pressed);
			break;
		default:
			break;
		}
	}

	/**
	 * ������ͼƬ�䰵
	 */
	private void resetImg() {
		mWeiXinImg.setImageResource(R.mipmap.tab_weixin_normal);
		mAddressImg.setImageResource(R.mipmap.tab_address_normal);
		mFrdImg.setImageResource(R.mipmap.tab_find_frd_normal);
		mSettingImg.setImageResource(R.mipmap.tab_settings_normal);
	}

}
