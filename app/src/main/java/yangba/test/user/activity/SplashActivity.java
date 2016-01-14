package yangba.test.user.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import yangba.test.user.R;

/**
 * Created by user on 2016/1/14.
 */
public class SplashActivity extends Activity{
    protected boolean _active = true;
    protected int _splashTime = 5000;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while(_active && (waited < _splashTime)) {
                        sleep(100);
                        Log.d("_active", String.valueOf(_active));
                        if(_active) {
                            waited += 100;
                        }
                        Log.d("waited", String.valueOf(waited));
                    }
                } catch(InterruptedException e) {
                    // do nothing
                } finally {
                    finish();
                    // 启动主应用
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    stop();
                }
            }
        };
        splashTread.start();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            _active = false;
        }
        return true;
    }


}
