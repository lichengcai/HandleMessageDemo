package cc.handlemessagedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ThreadLocalActivity extends AppCompatActivity {
    private static final String TAG = "ThreadLocalActivity";
    /**定义个Integer类型全局变量mIntegerThreadLocal*/
    private ThreadLocal<Integer> mIntegerThreadLocal = new ThreadLocal<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIntegerThreadLocal.set(0);/**主线程设置为0*/
        /**主线程打印mIntegerThreadLocal值*/
        Log.d(TAG,Thread.currentThread().getName() + " mIntegerThreadLocal = " + mIntegerThreadLocal.get());
        new Thread("Thread#1") {
            @Override
            public void run() {
                /**线程Thread#1中打印mIntegerThreadLocal值*/
                Log.d(TAG,Thread.currentThread().getName() + " mIntegerThreadLocal = " + mIntegerThreadLocal.get());
            }
        }.start();

        new Thread("Thread#2") {
            @Override
            public void run() {
                /**线程Thread#2中设置为2并打印mIntegerThreadLocal值*/
                mIntegerThreadLocal.set(2);
                Log.d(TAG,Thread.currentThread().getName() + " mIntegerThreadLocal = " + mIntegerThreadLocal.get());
            }
        }.start();
    }

}
