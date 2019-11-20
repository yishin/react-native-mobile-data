package io.appzen.MobileData;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import java.lang.Boolean;
import java.lang.reflect.*;
import android.net.ConnectivityManager;
import android.util.Log;

public class MobileDataModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public MobileDataModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "MobileData";
    }

    // @ReactMethod
    // public void sampleMethod(String stringArgument, int numberArgument, Callback callback) {
    //     // TODO: Implement some actually useful functionality
    //     callback.invoke("Received numberArgument: " + numberArgument + " stringArgument: " + stringArgument);
    // }

    @ReactMethod
    public void setMobileDataEnabled(boolean enabled, Callback callback) {
        try {
            Log.v("MobileData", "Entering setMobileDataEnabled()");

            final ConnectivityManager conman = (ConnectivityManager) this.reactContext.getSystemService(this.reactContext.getApplicationContext().CONNECTIVITY_SERVICE);
            final Class conmanClass = Class.forName(conman.getClass().getName());
            final Field iConnectivityManagerField = conmanClass.getDeclaredField("mService");
            iConnectivityManagerField.setAccessible(true);
            final Object iConnectivityManager = iConnectivityManagerField.get(conman);
            final Class iConnectivityManagerClass = Class.forName(iConnectivityManager.getClass().getName());
            final Method setMobileDataEnabledMethod = iConnectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
            setMobileDataEnabledMethod.setAccessible(true);

            setMobileDataEnabledMethod.invoke(iConnectivityManager, enabled);

            callback.invoke(true);

            Log.v("MobileData", "Terminating setMobileDataEnabled()");
        }
        catch (Exception e) {
            e.printStackTrace();

            callback.invoke(false);
        }
    }

    // public void setMobileDataState(boolean mobileDataEnabled)
    // {
    //     try
    //     {
    //         TelephonyManager telephonyService = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

    //         Method setMobileDataEnabledMethod = telephonyService.getClass().getDeclaredMethod("setDataEnabled", boolean.class);

    //         if (null != setMobileDataEnabledMethod)
    //         {
    //             setMobileDataEnabledMethod.invoke(telephonyService, mobileDataEnabled);
    //         }
    //     }
    //     catch (Exception ex)
    //     {
    //         Log.e(TAG, "Error setting mobile data state", ex);
    //     }
    // }

    // public boolean getMobileDataState()
    // {
    //     try
    //     {
    //         TelephonyManager telephonyService = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

    //         Method getMobileDataEnabledMethod = telephonyService.getClass().getDeclaredMethod("getDataEnabled");

    //         if (null != getMobileDataEnabledMethod)
    //         {
    //             boolean mobileDataEnabled = (Boolean) getMobileDataEnabledMethod.invoke(telephonyService);

    //             return mobileDataEnabled;
    //         }
    //     }
    //     catch (Exception ex)
    //     {
    //         Log.e(TAG, "Error getting mobile data state", ex);
    //     }

    //     return false;
    // }
}
