/**
 * Copyright 2011 ChinaSoft International Ltd. All rights reserved.
 */

package yangba.test.user.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;

/**
 * <p>
 * Title: LogoActivity
 * </p>
 * <p>
 * Description: Ӧ�ó�������ʱ��ʾ��LOGO����
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: ChinaSoft International Ltd.
 * </p>
 *
 * @author etc
 * @version 1.0
 */

public class LYWelcomeActivity extends Activity {
    /**
     * ������ʾLogo���Զ�����ͼ
     * */
    LogoView lv;

    @Override
    /**
     * ����Activityʱ�Զ��ص�
     * */
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // ʵ�����Զ�����ͼ
        lv = new LogoView(this);
        // ���Զ�����ͼ��ʾ�ڱ�����
        setContentView(lv);
        // �����޸�Logo͸���ȵ��첽����
        LogoTask task = new LogoTask();
        // ִ���첽����
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
            Intent intent = new Intent(LYWelcomeActivity.this, MainActivity.class);
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
