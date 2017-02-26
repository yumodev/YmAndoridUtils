package com.yumo.common.android;

import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.TextUtils;

/**
 * SharePreferences操作的单例类
 * yumodev
 * 2014-12-3
 */
public class YmPrefManager {
	private final static String LOG_TAG = "YmPrefManager";

	private Context mContext = null;

	private static YmPrefManager mInstance = null;

	public void initialize(Context context) {
		mContext = context;
	}

	public static YmPrefManager getInstance() {
		if (mInstance == null){
			mInstance = new YmPrefManager();
		}

		return mInstance;
	}

	public SharedPreferences getPreferences() {
		SharedPreferences shared = null;
		if (mContext != null) {
			shared = PreferenceManager.getDefaultSharedPreferences(mContext);
		}
		return shared;
	}

	private Editor _getEditor() {
		Editor editor = null;
		SharedPreferences pref = getPreferences();
		if (pref != null) {
			editor = pref.edit();
		}
		return editor;
	}

	public Map<String, ?> getAllData() {
		SharedPreferences pref = getPreferences();
		if (pref != null) {
			return pref.getAll();
		}
		return null;
	}

	public boolean removeData(String key) {
		if (TextUtils.isEmpty(key)){
			return false;
		}

		boolean result = false;
		Editor editor = _getEditor();
		if (editor != null) {
			editor.remove(key);
			result = editor.commit();
		}

		return result;
	}

	public String getStringData(String key) {
		if (TextUtils.isEmpty(key)){
			return null;
		}

		String result = "";
		SharedPreferences pref = getPreferences();
		if (pref != null && pref.contains(key)) {
			result = pref.getString(key, "");
		}

		return result;
	}

	public boolean setStringData(String key, String data) {
		if (TextUtils.isEmpty(key)){
			return false;
		}

		boolean result = false;
		Editor editor = _getEditor();
		if (editor != null) {
			editor.putString(key, data);
			result = editor.commit();
		}

		return result;
	}

	public boolean getBooleanData(String key, boolean defaultValue) {
		if (TextUtils.isEmpty(key)){
			return defaultValue;
		}

		boolean result = defaultValue;
		SharedPreferences pref = getPreferences();
		if (pref != null && pref.contains(key)) {
			result = pref.getBoolean(key, defaultValue);
		}

		return result;
	}

	public boolean setBooleanData(String key, boolean data) {
		if (TextUtils.isEmpty(key)){
			return false;
		}

		boolean result = false;
		Editor editor = _getEditor();
		if (editor != null) {
			editor.putBoolean(key, data);
			result = editor.commit();
		}

		return result;
	}

	public int getIntData(String key, int defaultValue) {
		if (TextUtils.isEmpty(key)){
			return defaultValue;
		}

		int result = defaultValue;
		SharedPreferences pref = getPreferences();
		if (pref != null && pref.contains(key)) {
			result = pref.getInt(key, defaultValue);
		}

		return result;
	}

	public boolean setIntData(String key, int data) {
		if (TextUtils.isEmpty(key)){
			return false;
		}

		boolean result = false;
		Editor editor = _getEditor();
		if (editor != null) {
			editor.putInt(key, data);
			result = editor.commit();
		}

		return result;
	}

	public long getLongData(String key, long defaultValue) {
		if (TextUtils.isEmpty(key)){
			return defaultValue;
		}

		long result = defaultValue;
		SharedPreferences pref = getPreferences();
		if (pref != null && pref.contains(key)) {
			result = pref.getLong(key, defaultValue);
		}
		return result;
	}

	public boolean setLongData(String key, long data) {
		if (TextUtils.isEmpty(key)){
			return false;
		}

		boolean result = false;
		Editor editor = _getEditor();
		if (editor != null) {
			editor.putLong(key, data);
			result = editor.commit();
		}

		return result;
	}

}
