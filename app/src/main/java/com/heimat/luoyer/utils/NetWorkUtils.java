package com.heimat.luoyer.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by code5 on 2017/4/14.
 */
public class NetWorkUtils {
    private static final String TAG = "net";
    private static final int LOW_SPEED_UPLOAD_BUF_SIZE = 1024;
    private static final int HIGH_SPEED_UPLOAD_BUF_SIZE = 10240;
    private static final int MAX_SPEED_UPLOAD_BUF_SIZE = 102400;
    private static final int LOW_SPEED_DOWNLOAD_BUF_SIZE = 2024;
    private static final int HIGH_SPEED_DOWNLOAD_BUF_SIZE = 30720;
    private static final int MAX_SPEED_DOWNLOAD_BUF_SIZE = 102400;

    public NetWorkUtils() {
    }

    public static boolean hasNetwork(Context context) {
        if(context != null) {
            ConnectivityManager var1 = (ConnectivityManager)context.getSystemService("connectivity");
            NetworkInfo var2 = var1.getActiveNetworkInfo();
            return var2 != null?var2.isAvailable():false;
        } else {
            return false;
        }
    }

    @TargetApi(13)
    public static boolean hasDataConnection(Context context) {
        try {
            ConnectivityManager var1 = (ConnectivityManager)context.getSystemService("connectivity");
            NetworkInfo var2 = var1.getNetworkInfo(1);
            if(var2 != null && var2.isAvailable() && var2.isConnected()) {
                LogUtil.d("net", "has wifi connection");
                return true;
            } else {
                var2 = var1.getNetworkInfo(0);
                if(var2 != null && var2.isAvailable() && var2.isConnected()) {
                    LogUtil.d("net", "has mobile connection");
                    return true;
                } else {
                    if(Build.VERSION.SDK_INT >= 13) {
                        var2 = var1.getNetworkInfo(9);
                        if(var2 != null && var2.isAvailable() && var2.isConnected()) {
                            LogUtil.d("net", "has ethernet connection");
                            return true;
                        }
                    }

                    LogUtil.d("net", "no data connection");
                    return false;
                }
            }
        } catch (Exception var3) {
            return false;
        }
    }

    @Deprecated
    public static boolean isWifiConnection(Context context) {
        return isWifiConnected(context);
    }

    public static boolean isWifiConnected(Context context) {
        ConnectivityManager var1 = (ConnectivityManager)context.getSystemService("connectivity");
        NetworkInfo var2 = var1.getNetworkInfo(1);
        if(var2 != null && var2.isAvailable() && var2.isConnected()) {
            LogUtil.d("net", "wifi is connected");
            return true;
        } else {
            return false;
        }
    }

    @Deprecated
    public static boolean isMobileConnection(Context context) {
        return isMobileConnected(context);
    }

    public static boolean isMobileConnected(Context context) {
        ConnectivityManager var1 = (ConnectivityManager)context.getSystemService("connectivity");
        NetworkInfo var2 = var1.getNetworkInfo(0);
        if(var2 != null && var2.isAvailable() && var2.isConnected()) {
            LogUtil.d("net", "mobile is connected");
            return true;
        } else {
            return false;
        }
    }

    @Deprecated
    public static boolean isEthernetConnection(Context context) {
        return isEthernetConnected(context);
    }

    public static boolean isEthernetConnected(Context context) {
        if(Build.VERSION.SDK_INT >= 13) {
            ConnectivityManager var1 = (ConnectivityManager)context.getSystemService("connectivity");
            NetworkInfo var2 = var1.getNetworkInfo(9);
            if(var2 != null && var2.isAvailable() && var2.isConnected()) {
                LogUtil.d("net", "ethernet is connected");
                return true;
            }
        }

        return false;
    }

    public static String getWiFiSSID(Context context) {
        ConnectivityManager var1 = (ConnectivityManager)context.getSystemService("connectivity");
        NetworkInfo var2 = var1.getNetworkInfo(1);
        WifiManager var3 = (WifiManager)context.getSystemService("wifi");
        WifiInfo var4 = var3.getConnectionInfo();
        return var4.getSSID();
    }

    public static int getUploadBufSize(Context context) {
        ConnectivityManager var1 = (ConnectivityManager)context.getSystemService("connectivity");
        NetworkInfo var2 = var1.getActiveNetworkInfo();
        return var2 != null && var2.getType() == 1?102400:(Build.VERSION.SDK_INT >= 13 && var2 != null && var2.getType() == 9?102400:(var2 == null && isConnectionFast(var2.getType(), var2.getSubtype())?10240:1024));
    }

    public static int getDownloadBufSize(Context context) {
        ConnectivityManager var1 = (ConnectivityManager)context.getSystemService("connectivity");
        NetworkInfo var2 = var1.getActiveNetworkInfo();
        return var2 != null && var2.getType() == 1?102400:(Build.VERSION.SDK_INT >= 13 && var2 != null && var2.getType() == 9?102400:(var2 == null && isConnectionFast(var2.getType(), var2.getSubtype())?30720:2024));
    }

    private static boolean isConnectionFast(int context, int var1) {
        if(context == 1) {
            return true;
        } else if(Build.VERSION.SDK_INT >= 13 && context == 9) {
            return true;
        } else {
            if(context == 0) {
                switch(var1) {
                    case 1:
                        return false;
                    case 2:
                        return false;
                    case 3:
                        return true;
                    case 4:
                        return false;
                    case 5:
                        return true;
                    case 6:
                        return true;
                    case 7:
                        return false;
                    case 8:
                        return true;
                    case 9:
                        return true;
                    case 10:
                        return true;
                    default:
                        if(Build.VERSION.SDK_INT >= 11 && (var1 == 14 || var1 == 13)) {
                            return true;
                        }

                        if(Build.VERSION.SDK_INT >= 9 && var1 == 12) {
                            return true;
                        }

                        if(Build.VERSION.SDK_INT >= 8 && var1 == 11) {
                            return false;
                        }
                }
            }

            return false;
        }
    }

    public static String getNetworkType(Context context) {
        ConnectivityManager var1 = (ConnectivityManager)context.getSystemService("connectivity");
        NetworkInfo var2 = var1.getActiveNetworkInfo();
        if(var2 != null && var2.isAvailable()) {
            int var3 = var2.getType();
            if(Build.VERSION.SDK_INT >= 13 && var3 == 9) {
                return "ETHERNET";
            } else if(var3 == 1) {
                return "WIFI";
            } else {
                TelephonyManager var4 = (TelephonyManager)context.getSystemService("phone");
                switch(var4.getNetworkType()) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        return "2G";
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        return "3G";
                    case 13:
                        return "4G";
                    default:
                        return "unkonw network";
                }
            }
        } else {
            return "no network";
        }
    }
    /**
     * 检查网络是否可用
     *
     * @param paramContext
     * @return
     */
    public static boolean isNetConnected(Context paramContext) {
        boolean i = false;
        NetworkInfo localNetworkInfo = ((ConnectivityManager) paramContext
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if ((localNetworkInfo != null) && (localNetworkInfo.isAvailable()))
            return true;
        return false;
    }
    /**

    /**
     * 检测3G是否连接
     */
    public static boolean is3gConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断网址是否有效
     */
    public static boolean isLinkAvailable(String link) {
        Pattern pattern = Pattern.compile("^(http://|https://)?((?:[A-Za-z0-9]+-[A-Za-z0-9]+|[A-Za-z0-9]+)\\.)+([A-Za-z]+)[/\\?\\:]?.*$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(link);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
}
