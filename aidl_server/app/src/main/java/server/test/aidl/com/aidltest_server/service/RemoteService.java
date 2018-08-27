package server.test.aidl.com.aidltest_server.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import server.test.aidl.com.aidltest_server.IAddService;

public class RemoteService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private IAddService.Stub mBinder = new IAddService.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int addMethod(int num1, int num2) throws RemoteException {
            return num1 + num2;
        }
    };
}
