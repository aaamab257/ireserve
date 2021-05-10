package com.aaamab.bonappetit.ui.about;

import com.aaamab.bonappetit.data.AboutArray;
import com.aaamab.bonappetit.data.TermsArray;

public interface AboutInter {
    void onSuccess(AboutArray aboutArray );
    void onFail(String error);
    void onConnection(boolean isConnected);
    void onTerms(TermsArray array );
}
