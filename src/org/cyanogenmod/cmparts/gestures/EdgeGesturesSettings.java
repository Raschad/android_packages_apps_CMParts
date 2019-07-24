/*
 * Copyright (C) 2017 The Nitrogen Project
 * Copyright (C) 2017 The Liquid Remix Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cyanogenmod.cmparts.gestures;

import android.content.Context;
import android.os.Bundle;
import android.os.UserHandle;
import android.provider.Settings;
import android.support.v14.preference.SwitchPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.Preference.OnPreferenceChangeListener;

import com.android.internal.util.cm.ActionUtils;
import org.cyanogenmod.cmparts.R;
import org.cyanogenmod.cmparts.SettingsPreferenceFragment;

public class EdgeGesturesSettings extends SettingsPreferenceFragment implements
        OnPreferenceChangeListener {

    public static final String EDGE_GESTURES_ENABLED = "edge_gestures_enabled";
    public static final String EDGE_GESTURES_SCREEN_PERCENT = "edge_gestures_back_screen_percent";

    private String previousTitle;

    private SwitchPreference enabledPreference;

    @Override
    protected int getPreferenceResource() {
        return R.xml.edge_gestures;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        enabledPreference = (SwitchPreference) findPreference(EDGE_GESTURES_ENABLED);
        enabledPreference.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference == enabledPreference) {
            boolean enabled = ((Boolean)newValue);
            CMSettings.Global.getInt(getActivity().getContentResolver(),
                    CMSettings.Global.DEV_FORCE_SHOW_NAVBAR,
                    enabled ? 0 : (ActionUtils.hasNavbarByDefault(
                                      getActivity().getApplicationContext()) ? 1 : 0));
            return true;
        }
        return false;
    }
}
