package com.example.echo.dulforum;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.echo.dao.BaseDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends AppCompatActivity {

    //@SuppressLint("HandlerLeak")
//    private Handler handler  = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//
//        }
//    };

    private Handler handler  = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Intent intent = new Intent(MainActivity.this, Register.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //提供以下两种方式进行初始化操作：

        //第一：默认初始化
        //Bmob.initialize(this, "66e8c74b116fc2d63372ce97419f48bd");
        // 注:自v3.5.2开始，数据sdk内部缝合了统计sdk，开发者无需额外集成，传渠道参数即可，不传默认没开启数据统计功能
        //Bmob.initialize(this, "Your Application ID","bmob");

        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        //BmobConfig config =new BmobConfig.Builder(this)
        ////设置appkey
        //.setApplicationId("Your Application ID")
        ////请求超时时间（单位为秒）：默认15s
        //.setConnectTimeout(30)
        ////文件分片上传时每片的大小（单位字节），默认512*1024
        //.setUploadBlockSize(1024*1024)
        ////文件的过期时间(单位为秒)：默认1800s
        //.setFileExpiration(2500)
        //.build();
        //Bmob.initialize(config);

        //testBmob();


//        final Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //testSql();
//                try {
//                    testBase();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        thread.start();

        //睡1S
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    Message message =new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    private void testBase() throws SQLException, ClassNotFoundException {
        Connection connection = new BaseDao().getConn();
    }


    private void testSql() {
                PreparedStatement ps = null;
                Connection ct = null;
                ResultSet rs = null;

                String url = "jdbc:jtds:sqlserver://10.6.35.142:1433;databaseName = test";// 连接字符串换成这个
                //String url = "jdbc:sqlserver://10.6.35.142:1433;databaseName = test";
                //"jdbc:sqlserver://localhost:1433;databaseName = SPJ_TEST"是microsoft提供的java-sqlserver数据库连接驱动来访问sqlserver时的url
                //localhost是指你的数据库服务器地址，1433为你的sqlserver端口号！
                //“SPJ_TEST”是所要连接的数据库的名称
                String user = "sa";
                String password = "sqlserver_2017";
                try {
                    //1.加载驱动
                    //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    Class.forName("net.sourceforge.jtds.jdbc.Driver");//加载驱动换成这个
                    //Toast.makeText(MainActivity.this, "加载驱动成功", Toast.LENGTH_SHORT).show();
                    Log.d("SQLtest", "加载驱动成功");
                    System.out.println("加载驱动成功！");
                }catch(Exception e) {
                    e.printStackTrace();
                    //Toast.makeText(MainActivity.this, "加载驱动失败", Toast.LENGTH_SHORT).show();
                    Log.d("SQLtest", "加载驱动失败");
                    System.out.println("加载驱动失败！");
                }
                try {
                    //2.连接
                    ct = DriverManager.getConnection(url,user,password);
                    //Toast.makeText(MainActivity.this, "sql连接成功", Toast.LENGTH_SHORT).show();
                    Log.d("SQLtest", "sql连接成功");
                    System.out.println("连接数据库成功！");
                }catch(Exception e) {
                    e.printStackTrace();
                    //Toast.makeText(MainActivity.this, "sql连接失败", Toast.LENGTH_SHORT).show();
                    Log.d("SQLtest", "sql连接失败");
                    System.out.println("连接数据库失败！");
                }


    }



    void testBmob() {
//        UserInfo info = new UserInfo();
//        info.setName("Echo");
//        info.setPassword("yezhou");
//        info.save(new SaveListener<String>() {
//            @Override
//            public void done(String s, BmobException e) {
//                if(e==null){
//                    Toast.makeText(MainActivity.this, "上传成功！", Toast.LENGTH_SHORT).show();
//                }else{
//                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
//                }
//            }
//        });
    }
}
