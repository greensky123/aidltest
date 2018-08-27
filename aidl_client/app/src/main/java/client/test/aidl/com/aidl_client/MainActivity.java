package client.test.aidl.com.aidl_client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import server.test.aidl.com.aidltest_server.IAddService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText num1;
    private EditText num2;
    private Button add;
    private ServiceConnection conn;
    private TextView show;
    String strNum1;
    String strNum2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d("TAG", "service is connected");
                IAddService addService = IAddService.Stub.asInterface(service);
                try {
                    int result = addService.addMethod(Integer.parseInt(strNum1), Integer.parseInt(strNum2));
                    show.setText(result+"");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d("TAG", "service is disconncted");
            }
        };
    }

    private void initView() {
        num1 = findViewById(R.id.edt_num1);
        num2 = findViewById(R.id.edt_num2);
        add = findViewById(R.id.btn_add);
        show = findViewById(R.id.result_show);
        add.setOnClickListener(this);
    }
    private void addNumber() {
        strNum1 = num1.getEditableText().toString();
        strNum2 = num2.getEditableText().toString();
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("server.test.aidl.com.aidltest_server", "server.test.aidl.com.aidltest_server.RemoteService");
        intent.setComponent(componentName);
        bindService(intent, conn, BIND_AUTO_CREATE);

    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_add) {
            addNumber();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (conn != null) {
            unbindService(conn);
        }
    }
}
