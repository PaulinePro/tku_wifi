package com.devandroid.tkuautowifi;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Utils {

	private static final String TAG = "Utils";

	public static String stackTraceToString(Exception e) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		e.printStackTrace(ps);
		String ret = os.toString();
		ps.close();
		return ret;
	}

	public static String getVersionName(Context context) {
		try {
			return context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			return "Unknown";
		}
	}

	public static int getVersionCode(Context context) {
		try {
			PackageInfo pInfo = context.getPackageManager().getPackageInfo(
					context.getPackageName(), PackageManager.GET_META_DATA);
			return pInfo.versionCode;
		} catch (NameNotFoundException e) {
			return 0;
		}
	}

	// public static void loadLocale(Context context) {
	// String lang = PreferenceManager.getDefaultSharedPreferences(context)
	// .getString(Preferences.KEY_LANGUAGE,
	// Preferences.LANGUAGE_DEFAULT);
	// Configuration config = new Configuration();
	// if (!lang.equals(Preferences.LANGUAGE_DEFAULT)) {
	// config.locale = new Locale(lang);
	// } else {
	// config.locale = Locale.getDefault();
	// }
	// context.getResources().updateConfiguration(config, null);
	// }

	public static void setEnableBroadcastReceiver(Context context,
			boolean enabled) {
		Log.v(TAG, "Setting BroadcastReceiver status to: " + enabled);

		ComponentName receiver = new ComponentName(context,
				NetworkStateChanged.class);
		int state = enabled ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED
				: PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
		context.getPackageManager().setComponentEnabledSetting(receiver, state,
				PackageManager.DONT_KILL_APP);
	}

	public static boolean isWifiAvailable(Context context) {
		boolean isAvailable = false;
		try {
			WifiManager wifi = (WifiManager) context
					.getSystemService(Context.WIFI_SERVICE);
			if (wifi == null) {
				isAvailable = false;
			} else if (wifi != null) {
				isAvailable = wifi.isWifiEnabled();
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return isAvailable;
	}

	public static boolean isConnectToSSID(Context context, String ssid) {
		boolean isConnect = false;
		try {
			WifiManager wifi = (WifiManager) context
					.getSystemService(Context.WIFI_SERVICE);
			WifiInfo info = wifi.getConnectionInfo();
			if (wifi != null && info != null && info.getSSID() != null
					&& wifi.isWifiEnabled() && info.getSSID().equals(ssid)) {
				isConnect = true;
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return isConnect;
	}

	// public static String getWifiIpAddress(Context context) {
	// WifiManager wifi = (WifiManager) context
	// .getSystemService(Context.WIFI_SERVICE);
	// WifiInfo info = wifi.getConnectionInfo();
	// int ip = info.getIpAddress();
	//
	// String result = String.format(Locale.US, "%1$d.%2$d.%3$d.%4$d",
	// (ip & 0xff), (ip >> 8 & 0xff), (ip >> 16 & 0xff),
	// (ip >> 24 & 0xff));
	//
	// return result;
	// }
	//
	// public static String getWifiIpAddressElement(Context context, int
	// element) {
	// WifiManager wifi = (WifiManager) context
	// .getSystemService(Context.WIFI_SERVICE);
	// WifiInfo info = wifi.getConnectionInfo();
	// int ip = info.getIpAddress();
	//
	// String result = String.format(Locale.US, "%1$d.%2$d.%3$d.%4$d",
	// (ip & 0xff), (ip >> 8 & 0xff), (ip >> 16 & 0xff),
	// (ip >> 24 & 0xff));
	// String[] ip_address = new String[4];
	// ip_address = result.split("\\.");
	//
	// return ip_address[element];
	// }

	public static String getDebugInfo(Context context) {
		StringBuilder sb = new StringBuilder();
		sb.append("Debug Info:\n");
		sb.append("API: " + android.os.Build.VERSION.SDK_INT + "\n");
		sb.append("Model: " + android.os.Build.MODEL + "\n");
		sb.append("Version: " + Utils.getVersionName(context) + "\n\n");

		return sb.toString();
	}

	public static void toggleSoftKeyboard(Context c, boolean isShow, View v) {
		if (isShow) {
			((InputMethodManager) c
					.getSystemService(Context.INPUT_METHOD_SERVICE))
					.toggleSoftInput(InputMethodManager.SHOW_FORCED,
							InputMethodManager.HIDE_IMPLICIT_ONLY);
		} else {
			((InputMethodManager) c
					.getSystemService(Context.INPUT_METHOD_SERVICE))
					.hideSoftInputFromWindow(v.getWindowToken(),
							InputMethodManager.RESULT_UNCHANGED_SHOWN);
		}
	}

	public static boolean isScreenPortrait(Context context) {
		return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ? true
				: false;
	}
}
