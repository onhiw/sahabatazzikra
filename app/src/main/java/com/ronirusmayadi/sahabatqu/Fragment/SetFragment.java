package com.ronirusmayadi.sahabatqu.Fragment;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.ronirusmayadi.sahabatqu.Broadcast.AlarmDhuha;
import com.ronirusmayadi.sahabatqu.R;

public class SetFragment extends PreferenceFragment {

    private AlarmDhuha alarmDhuha = new AlarmDhuha();
    private SwitchPreference switchPreference;

    @Override
    public void onCreate( @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference);
        switchPreference = (SwitchPreference) findPreference(getString(R.string.key_reminder_daily));

        switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                String keydaily = preference.getKey();
                boolean isOn = (boolean) newValue;
                if (keydaily.equals(getString(R.string.key_reminder_daily))) {
                    if (isOn) {
                        alarmDhuha.setRepeatingAlarm(getActivity(), alarmDhuha.TYPE_REPEATING, "07:00", getString(R.string.label_daily_reminder));
                    } else {
                        alarmDhuha.cancelAlarm(getActivity(), alarmDhuha.TYPE_REPEATING);
                    }

                    Toast.makeText(getActivity(), getString(R.string.notifications_daily) + " " + (isOn ? getString(R.string.notifications_activated) : getString(R.string.notifications_deactivated)), Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
    }
}
