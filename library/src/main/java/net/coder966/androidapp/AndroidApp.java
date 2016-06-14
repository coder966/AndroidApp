/*
 * Copyright 2016 Khalid Alharisi
 *
 * Licensed under the GNU General Public License v3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.gnu.org/licenses/gpl-3.0.txt
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
 * A class that inherits `Application` class to feature `onUpgrade` method
 *
 * @author Khalid H. Alharisi
 */
public abstract class AndroidApp extends Application {
    private final String PREF_FILE_NAME = "VERSION_CONTROLLER";
    private final String PREF_NAME = "INSTALLED_VERSION";

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferences pref = getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);
        int previousVersion = pref.getInt(PREF_NAME, 0);

        int thisVersion;
        try{
            PackageInfo packageInfo = getPackageManager().getPackageInfo(this.getPackageName(), 0);

            long firstInstallTime = packageInfo.firstInstallTime;
            long lastUpdateTime = packageInfo.lastUpdateTime;
            if(firstInstallTime == lastUpdateTime){
                return; // handle fresh installation
            }

            thisVersion = packageInfo.versionCode;
        }catch (Exception e){
            thisVersion = 0;
        }

        if(previousVersion != thisVersion){
            onUpgrade(previousVersion, thisVersion);
            pref.edit().putInt(PREF_NAME, thisVersion).commit();
        }
    }

    /**
     * This method will be called only if the application has been updated
     *
     * @param oldVersionCode previously installed version
     * @param newVersionCode current installed version
     */
    public abstract void onUpgrade(int oldVersionCode, int newVersionCode);
}
