/*
 * Copyright 2012 Jay Weisskopf
 *
 * Licensed under the MIT License (see LICENSE.txt)
 */

package no.wtw.android.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import no.wtw.android.preference.R;

public class SliderPreference extends DialogPreference {

    protected int minValue, maxValue, resolution;
    protected int value;
    protected int seekBarValue;
    private int unitResource;

    public SliderPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(context, attrs);
    }

    public SliderPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setup(context, attrs);
    }

    private void setup(Context context, AttributeSet attrs) {
        setDialogLayoutResource(R.layout.slider_preference_dialog);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SliderPreference);
        try {
            minValue = a.getInt(R.styleable.SliderPreference_minValue, 0);
            maxValue = a.getInt(R.styleable.SliderPreference_maxValue, 100);
            resolution = maxValue - minValue;
            unitResource = a.getResourceId(R.styleable.SliderPreference_unitStringReference, 0);
        } catch (Exception e) {
            // Do nothing
        }
        a.recycle();
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return a.getFloat(index, 0);
    }

    @Override
    protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
        setValue(restoreValue ? getPersistedInt(value) : (Integer) defaultValue);
    }

    @Override
    public CharSequence getSummary() {
        return getSummaryByValue(value);
    }

    private CharSequence getSummaryByValue(int value) {
        if (unitResource != 0)
            return getContext().getResources().getQuantityString(unitResource, value, value);
        else
            return String.valueOf(value);
    }

    public void setValue(int value) {
        value = getClampedValue(value);
        if (shouldPersist()) {
            persistInt(value);
        }
        if (value != this.value) {
            this.value = value;
            notifyChanged();
        }
    }

    public int getClampedValue(int value) {
        return Math.max(minValue, Math.min(value, maxValue));
    }

    @Override
    protected View onCreateDialogView() {
        seekBarValue = value - minValue;
        View view = super.onCreateDialogView();
        final TextView valueText = (TextView) view.findViewById(R.id.value);
        valueText.setText(getSummaryByValue(getClampedValue(value)));

        SeekBar seekbar = (SeekBar) view.findViewById(R.id.slider_preference_seekbar);
        seekbar.setMax(maxValue - minValue);
        seekbar.setProgress(seekBarValue);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    SliderPreference.this.seekBarValue = progress;
                    valueText.setText(getSummaryByValue(getClampedValue(seekBarValue + minValue)));
                }
            }
        });
        return view;
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        final int newValue = seekBarValue + minValue;
        if (positiveResult && callChangeListener(newValue)) {
            setValue(newValue);
        }
        super.onDialogClosed(positiveResult);
    }

}
