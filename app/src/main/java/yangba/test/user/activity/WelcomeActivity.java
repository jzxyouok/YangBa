/**
 * Copyright 2011 ChinaSoft International Ltd. All rights reserved.
 */

package yangba.test.user.activity;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;


public class WelcomeActivity extends Activity {
    /**
     * ������ʾLogo���Զ�����ͼ
     * */
    LogoView lv;
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;

    public static boolean hasLoginInfo = false;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        lv = new LogoView(this);
        setContentView(lv);
        sharedPreferences = getSharedPreferences("userinfo", Activity.MODE_PRIVATE);
        String user_name = sharedPreferences.getString("user_name", "");
        String user_password = sharedPreferences.getString("user_password", "");
        if (!user_name.equals("") && !user_password.equals("")) {
            hasLoginInfo = true;
        }
        LogoTask task = new LogoTask();
        task.execute();

    }

    /**
     * �����޸�Logo����ͼƬ͸���ȵ��첽����
     * */
    private class LogoTask extends AsyncTask<Object, Integer, String> {

        // ��ǰ��͸����ȡֵ
        int alpha = 0;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            //System.out.println("a"+System.currentTimeMillis());
        }

        /**
         * �첽����ִ�гɹ����Զ���ת��Ӧ�ó���������
         * */
        @Override
        protected void onPostExecute(String result) {
            //System.out.println("d"+System.currentTimeMillis());
            // TODO Auto-generated method stub
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        /**
         * �޸�Logo�Զ�����ͼͼƬ͸���ȣ����ػ�
         * */
        @Override
        protected void onProgressUpdate(Integer... values) {
            //System.out.println("c"+System.currentTimeMillis());
            // TODO Auto-generated method stub
            int temp = values[0].intValue();
            lv.repaint(temp);
        }

        /**
         * �첽����ѭ���ı�͸����ȡֵ
         * */
        @Override
        protected String doInBackground(Object... arg0) {
            // TODO Auto-generated method stub
            // System.out.println("b"+System.currentTimeMillis());
            while (alpha < 255) {

                try {
                    Thread.sleep(100);
                    publishProgress(new Integer(alpha));
                    alpha += 5;
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            return null;
        }

    }

}
