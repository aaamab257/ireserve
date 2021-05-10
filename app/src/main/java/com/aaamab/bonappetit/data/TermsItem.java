package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class TermsItem {
    @SerializedName("id")
    public int id ;
    @SerializedName("policy_term_ar")
    public String policy_term_ar ;
    @SerializedName("policy_term")
    public String policy_term_en ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPolicy_term_ar() {
        return policy_term_ar;
    }

    public void setPolicy_term_ar(String policy_term_ar) {
        this.policy_term_ar = policy_term_ar;
    }

    public String getPolicy_term_en() {
        return policy_term_en;
    }

    public void setPolicy_term_en(String policy_term_en) {
        this.policy_term_en = policy_term_en;
    }
}
