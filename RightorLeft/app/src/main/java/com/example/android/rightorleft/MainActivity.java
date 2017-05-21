package com.example.android.rightorleft;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import ioio.lib.api.AnalogInput;
import ioio.lib.api.DigitalOutput;
import ioio.lib.api.IOIO;
import ioio.lib.api.PwmOutput;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.BaseIOIOLooper;
import ioio.lib.util.IOIOLooper;
import ioio.lib.util.android.IOIOActivity;


import static android.R.id.input;
import static android.R.id.inputArea;

public class MainActivity extends IOIOActivity {

        private TextView textView_;
        private SeekBar seekBar_;
        private ToggleButton toggleButton_;
        private TextView textViewL_;


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            textView_ = (TextView) findViewById(R.id.TextView);
            textViewL_ = (TextView) findViewById(R.id.TextViewL);
            seekBar_ = (SeekBar) findViewById(R.id.SeekBar);
            toggleButton_ = (ToggleButton) findViewById(R.id.ToggleButton);

            enableUi(false);
        }

        class Looper extends BaseIOIOLooper {
            private AnalogInput input_;
            private AnalogInput input2_;
            private PwmOutput pwmOutput_;
            private DigitalOutput led_;
            private float IRsensorR;
            private float IRsensorL;



            @Override
            public void setup() throws ConnectionLostException {
                led_ = ioio_.openDigitalOutput(IOIO.LED_PIN, true);
                input_ = ioio_.openAnalogInput(40);
                input2_ = ioio_.openAnalogInput(39);
                pwmOutput_ = ioio_.openPwmOutput(12, 100);
                enableUi(true);
            }

            @Override
            public void loop() throws ConnectionLostException, InterruptedException {
                IRsensorR = input_.read(); // get the right ir sensor value
                IRsensorL = input2_.read(); // get the left ir sensor value
                setNumber(IRsensorR);
                setNumber2(IRsensorL);
                led_.write(!toggleButton_.isChecked());
                if(IRsensorR > 0.4){
                    pwmOutput_.setPulseWidth(2500);
                }
                else if(IRsensorL > 0.4) {
                    pwmOutput_.setPulseWidth(500);
                }
                else {
                    pwmOutput_.setPulseWidth(500 + seekBar_.getProgress() * 2);
                }
                Thread.sleep(10);
            }

            @Override
            public void disconnected() {
                enableUi(false);
            }
        }

        @Override
        protected IOIOLooper createIOIOLooper() {
            return new Looper();
        }

        private void enableUi(final boolean enable) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    seekBar_.setEnabled(enable);
                    toggleButton_.setEnabled(enable);
                }
            });
        }

        private void setNumber(float f) {
            final String str = String.format("%.2f", f);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView_.setText(str);
                }
            });
        }
        private void setNumber2(float f) {
            final String str = String.format("%.2f", f);
            runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textViewL_.setText(str);
            }
        });

        }


    }
