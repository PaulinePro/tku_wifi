package com.devandroid.tkuautowifi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class Memory {
	private static SharedPreferences appSharedPrefs;
	private static Editor prefsEditor;

	@SuppressLint("CommitPrefEdits")
	public static void init(Context context) {
		appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
		prefsEditor = appSharedPrefs.edit();
	}

	public static int getInt(Context context, String key, int defValue) {
		init(context);

		return appSharedPrefs.getInt(key, defValue);
	}

	public static void setInt(Context context, String key, int value) {
		init(context);

		prefsEditor.putInt(key, value);
		prefsEditor.commit();
	}

	public static String getString(Context context, String key, String defValue) {
		init(context);

		return appSharedPrefs.getString(key, defValue);
	}

	public static void setString(Context context, String key, String data) {
		init(context);

		prefsEditor.putString(key, data);
		prefsEditor.commit();
	}

	public static boolean getBoolean(Context context, String key,
			boolean defValue) {
		init(context);

		return appSharedPrefs.getBoolean(key, defValue);
	}

	public static void setBoolean(Context context, String key, boolean data) {
		init(context);

		prefsEditor.putBoolean(key, data);
		prefsEditor.commit();
	}

	public static long getLong(Context context, String key, long defValue) {
		init(context);

		return appSharedPrefs.getLong(key, defValue);
	}

	public static void setLong(Context context, String key, long value) {
		init(context);

		prefsEditor.putLong(key, value);
		prefsEditor.commit();
	}
}