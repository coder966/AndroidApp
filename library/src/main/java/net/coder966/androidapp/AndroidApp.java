/*
 * Copyright 2016 Khalid H. Alharisi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.coder966.androidapp;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;

/**
 * A class that inherits {@link Application} to feature:
 * {@link #onCreate(int, String)}
 * {@link #onUpgradeBeforeOnCreate(int, int)}
 * {@link #onUpgradeAfterOnCreate(int, int)}
 *
 * @author Khalid H. Alharisi
 * @link coder966.net
 */
public abstract class AndroidApp extends Application {
    private final String PREF_FILE_NAME = "VERSION_CONTROLLER";
    private final String PREF_NAME = "INSTALLED_VERSION";

    /**
     * Use {@link #onCreate(int, String)} instead.
     */
    @Override
    public final void onCreate() {
        super.onCreate();

        // load data file
        SharedPreferences pref = getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);

        // get info
        int previousVersionCode = pref.getInt(PREF_NAME, 0);
        int currentVersionCode;
        String currentVersionName;
        try{
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            currentVersionCode = packageInfo.versionCode;
            currentVersionName = packageInfo.versionName;

            // handle fresh installation
            long firstInstallTime = packageInfo.firstInstallTime;
            long lastUpdateTime = packageInfo.lastUpdateTime;
            if(firstInstallTime == lastUpdateTime){
                onCreate(currentVersionCode, currentVersionName);
            }
        }catch (Exception e){
            e.printStackTrace();
            currentVersionCode = 0;
            currentVersionName = "0";
        }

        if(previousVersionCode < currentVersionCode){
            // save current installed version code
            pref.edit().putInt(PREF_NAME, currentVersionCode).apply();

            onUpgradeBeforeOnCreate(previousVersionCode, currentVersionCode);
            onCreate(currentVersionCode, currentVersionName);
            onUpgradeAfterOnCreate(previousVersionCode, currentVersionCode);
        }
    }

    /**
     * Use this method instead of {@link #onCreate()}.
     *
     * @param currentVersionCode Currently installed version code.
     * @param currentVersionName Currently installed version name.
     */
    public void onCreate(int currentVersionCode, String currentVersionName){

    }

    /**
     * When the application gets updated, this method will be called right before your {@link #onCreate(int, String)}.
     *
     * @param oldVersionCode Previously installed version code.
     * @param newVersionCode Currently installed version code.
     */
    public void onUpgradeBeforeOnCreate(int oldVersionCode, int newVersionCode){

    }

    /**
     * When the application gets updated, this method will be called right after your {@link #onCreate(int, String)}.
     *
     * @param oldVersionCode Previously installed version code.
     * @param newVersionCode Currently installed version code.
     */
    public void onUpgradeAfterOnCreate(int oldVersionCode, int newVersionCode){

    }

}
