package top.colayy.pojo;

public class Manager {
    private String mAcc;
    private String mPwd;
    private String mPhone;
    private String mEmail;

    public Manager() {
    }

    public Manager(String mAcc, String mPwd, String mPhone, String mEmail) {
        this.mAcc = mAcc;
        this.mPwd = mPwd;
        this.mPhone = mPhone;
        this.mEmail = mEmail;
    }

    public String getmAcc() {
        return mAcc;
    }

    public void setmAcc(String mAcc) {
        this.mAcc = mAcc;
    }

    public String getmPwd() {
        return mPwd;
    }

    public void setmPwd(String mPwd) {
        this.mPwd = mPwd;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "mAcc='" + mAcc + '\'' +
                ", mPwd='" + mPwd + '\'' +
                ", mPhone='" + mPhone + '\'' +
                ", mEmail='" + mEmail + '\'' +
                '}';
    }
}
