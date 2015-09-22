/*
* Copyright (c) 2012, Code Aurora Forum. All rights reserved.
*
Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are
met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above
      copyright notice, this list of conditions and the following
      disclaimer in the documentation and/or other materials provided
      with the distribution.
    * Neither the name of Code Aurora Forum, Inc. nor the names of its
      contributors may be used to endorse or promote products derived
      from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESS OR IMPLIED
WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT
ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS
BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN
IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package com.android.qrd.configurationprompt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import com.android.qrd.configurationprompt.R;
import android.view.WindowManager;

import android.content.ActivityNotFoundException;
import android.util.Log;

public class CardChangedActivity extends Activity implements View.OnClickListener {
    static final String LOG_TAG = "CardChangedActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "on create activity");
        requestWindowFeature(Window.FEATURE_LEFT_ICON);

        setContentView(R.layout.card_changed_dialog);

        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        Button resetBtn = (Button)findViewById(R.id.btn_reset);
        Button cancelBtin = (Button)findViewById(R.id.btn_cancel);
        resetBtn.setOnClickListener(this);
        cancelBtin.setOnClickListener(this);
    }

    public void onClick(View v) {
        if ((v != null) && (v.getId() == R.id.btn_reset)) {
            try {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setClassName("com.android.settings", "com.android.settings.multisimsettings.MultiSimSettings");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } catch(ActivityNotFoundException e) {
                Log.w(LOG_TAG, "can not found multi sim settings activity");
            }
        }
        finish();
    }

    @Override
    public void onResume() {
        super.onResume();

        //set tip icon at the left of activity title
        setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, android.R.drawable.ic_dialog_info);
    }

}
