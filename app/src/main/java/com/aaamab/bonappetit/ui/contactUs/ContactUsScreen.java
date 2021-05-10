package com.aaamab.bonappetit.ui.contactUs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.ContactUsData;
import com.aaamab.bonappetit.data.ContactUsObj;
import com.aaamab.bonappetit.databinding.ActivityContuctUsScreenBinding;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.ToastUtil;
import com.aaamab.bonappetit.utils.network.MainApiBody;

import okhttp3.RequestBody;

public class ContactUsScreen extends AppCompatActivity implements ContactUsIter {
    ContactUsHandler handler;
    ActivityContuctUsScreenBinding binding;
    ContactUsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contuct_us_screen);
        handler = new ContactUsHandler(this);
        binding.setHandler(handler);
        presenter = new ContactUsPresenter(this);
        binding.edMessage.addTextChangedListener(mTextEditorWatcher);
    }

    private final TextWatcher mTextEditorWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //This sets a textview to the current length
            binding.txtCounter.setText(String.valueOf(s.length()) + "/ 250");
        }

        public void afterTextChanged(Editable s) {
        }
    };

    @Override
    public void onSuccess(ContactUsData<ContactUsObj> data) {
        ToastUtil.showSuccessToast(ContactUsScreen.this, R.string.message_sent);
    }

    @Override
    public void onFail(String error) {
        ToastUtil.showErrorToast(ContactUsScreen.this, error);
    }

    @Override
    public void onConnection(boolean isConnected) {
        ToastUtil.showErrorToast(ContactUsScreen.this, R.string.noConnection);
    }

    public class ContactUsHandler {
        Context context;

        public ContactUsHandler(Context context) {
            this.context = context;
        }

        public void onSend(View v) {

            String msg = binding.edMessage.getText().toString();
            if (msg.isEmpty()) {
                binding.edMessage.setText("");
                ToastUtil.showErrorToast(ContactUsScreen.this, R.string.enter_message);
            } else {
                binding.edMessage.setText("");
                RequestBody b = null;
                try {
                    b = MainApiBody.contactUS(msg);
                } catch (Exception e) {

                }
                presenter.sendContact(ContactUsScreen.this, b);

            }

        }

        public void onBack(View v) {
            onBackPressed();
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.onAttach(base));
    }
}