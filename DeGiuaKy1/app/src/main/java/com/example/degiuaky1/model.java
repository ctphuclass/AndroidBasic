package com.example.degiuaky1;

import java.io.Serializable;

public class model implements Serializable {
    public model(){

    }
    public model(String mTenMonHoc, String mMaMonHoc, String mTinChi,String mTen, String mMssv){
        this.TenMonHoc = mTenMonHoc;
        this.MaMonHoc = mMaMonHoc;
        this.TinChi = mTinChi;
        this.Ten = mTen;
        this.Mssv = mMssv;
    }

    private String TenMonHoc;
    private String MaMonHoc;
    private String TinChi;
    private String Ten;
    private String Mssv;

    public String getTenMonHoc() {
        return TenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        TenMonHoc = tenMonHoc;
    }

    public String getMaMonHoc() {
        return MaMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        MaMonHoc = maMonHoc;
    }

    public String getTinChi() {
        return TinChi;
    }

    public void setTinChi(String tinChi) {
        TinChi = tinChi;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getMssv() {
        return Mssv;
    }

    public void setMssv(String mssv) {
        Mssv = mssv;
    }


}
