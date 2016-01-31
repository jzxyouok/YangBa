package yangba.test.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import yangba.test.user.R;
import yangba.test.user.fragment.MainTab01;
import yangba.test.user.fragment.MainTab02;
import yangba.test.user.fragment.MainTab03;
import yangba.test.user.fragment.MainTab04;

public class MainActivity extends FragmentActivity implements OnClickListener {

	private ViewPager mViewPager;
	private FragmentPagerAdapter mAdapter;
	private List<Fragment> mFragments = new ArrayList<Fragment>();

	/**
	 * �ײ��ĸ���ť
	 */
	private LinearLayout mTabBtnWeixin;
	private LinearLayout mTabBtnFrd;
	private LinearLayout mTabBtnAddress;
	private LinearLayout mTabBtnSettings;
	private long exitTime = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);


		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

		initView();

		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

			@Override
			public int getCount() {
				return mFragments.size();
			}

			@Override
			public Fragment getItem(int arg0) {
				return mFragments.get(arg0);
			}
		};

		mViewPager.setAdapter(mAdapter);
		if (LoginActivity.hasregister == true) {
			mViewPager.setCurrentItem(3);
			resetTabBtn();
			((ImageButton) mTabBtnSettings
					.findViewById(R.id.btn_tab_bottom_setting))
					.setImageResource(R.mipmap.tab_settings_pressed);


		}
		if (AddPlanActivity.addplan == true) {
			mViewPager.setCurrentItem(2);
			resetTabBtn();
			((ImageButton) mTabBtnAddress
					.findViewById(R.id.btn_tab_bottom_contact))
					.setImageResource(R.mipmap.tab_address_pressed);


		}



		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			private int currentIndex;

			@Override
			public void onPageSelected(int position) {
				resetTabBtn();
				switch (position) {
					case 0:
						((ImageButton) mTabBtnWeixin
								.findViewById(R.id.btn_tab_bottom_weixin))
								.setImageResource(R.mipmap.add_at_selected);
						break;
					case 1:
						((ImageButton) mTabBtnFrd
								.findViewById(R.id.btn_tab_bottom_friend))
								.setImageResource(R.mipmap.tab_find_frd_pressed);
						break;
					case 2:
						((ImageButton) mTabBtnAddress
								.findViewById(R.id.btn_tab_bottom_contact))
								.setImageResource(R.mipmap.tab_address_pressed);
						break;
					case 3:
						((ImageButton) mTabBtnSettings
								.findViewById(R.id.btn_tab_bottom_setting))
								.setImageResource(R.mipmap.tab_settings_pressed);
						break;
				}

				currentIndex = position;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

	}

	protected void resetTabBtn() {
		((ImageButton) mTabBtnWeixin.findViewById(R.id.btn_tab_bottom_weixin))
				.setImageResource(R.mipmap.tab_weixin_normal);
		((ImageButton) mTabBtnFrd.findViewById(R.id.btn_tab_bottom_friend))
				.setImageResource(R.mipmap.tab_find_frd_normal);
		((ImageButton) mTabBtnAddress.findViewById(R.id.btn_tab_bottom_contact))
				.setImageResource(R.mipmap.tab_address_normal);
		((ImageButton) mTabBtnSettings
				.findViewById(R.id.btn_tab_bottom_setting))
				.setImageResource(R.mipmap.tab_settings_normal);

	}

	@Override
	public void onClick(View v) {
		resetTabBtn();
		switch (v.getId()) {
		case R.id.id_tab_bottom_weixin:
			mViewPager.setCurrentItem(0);
			break;
		case R.id.id_tab_bottom_friend:
			mViewPager.setCurrentItem(1);
			break;
		case R.id.id_tab_bottom_contact:
			mViewPager.setCurrentItem(2);
			break;
		case R.id.id_tab_bottom_setting:
			mViewPager.setCurrentItem(3);
			break;
		default:
			break;
		}

	}

	private void initView() {

		mTabBtnWeixin = (LinearLayout) findViewById(R.id.id_tab_bottom_weixin);
		mTabBtnFrd = (LinearLayout) findViewById(R.id.id_tab_bottom_friend);
		mTabBtnAddress = (LinearLayout) findViewById(R.id.id_tab_bottom_contact);
		mTabBtnSettings = (LinearLayout) findViewById(R.id.id_tab_bottom_setting);
		mTabBtnWeixin.setOnClickListener(this);
		mTabBtnFrd.setOnClickListener(this);
		mTabBtnAddress.setOnClickListener(this);
		mTabBtnSettings.setOnClickListener(this);
		MainTab01 tab01 = new MainTab01();
		MainTab02 tab02 = new MainTab02();
		MainTab03 tab03 = new MainTab03();
		MainTab04 tab04 = new MainTab04();
		mFragments.add(tab01);
		mFragments.add(tab02);
		mFragments.add(tab03);
		mFragments.add(tab04);


	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

			if ((System.currentTimeMillis() - exitTime) > 2000)  //System.currentTimeMillis()无论何时调用，肯定大于2000
			{
				Toast.makeText(getApplicationContext(), "再按一次返回退出养吧", Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
//				finish();
//				System.exit(0);
//				onDestroy();
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				android.os.Process.killProcess(android.os.Process.myPid());
			}

			return true;
		}
		return super.onKeyDown(keyCode, event);
	}



}




