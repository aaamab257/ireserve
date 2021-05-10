package com.aaamab.bonappetit.ui.more;

import com.aaamab.bonappetit.data.SocialMediaData;

public interface MoreInterface {
    void onSocial(SocialMediaData socialMediaData );
    void onConnection(boolean isConnected);
    void onFail(String error);
}
