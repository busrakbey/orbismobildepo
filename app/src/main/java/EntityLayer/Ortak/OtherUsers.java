package EntityLayer.Ortak;

import AnnotationLayer.Column;
import AnnotationLayer.Table;

/**
 * Created by isahin on 20.10.2016.
 */

@Table(name="PER_SICIL")
public class OtherUsers extends BaseEntity{

    @Override
    public String toString()
    {
        return ad +" "+soyad;
    }


    @Column(name="superId",nullable= false)
    private Long superId=null;

    @Column(name="id",nullable= false)
    private Long id=null;

    @Column(name="ad",nullable= false)
    private String ad=null;

    @Column(name="soyad",nullable= false)
    private String soyad=null;

    @Column(name="gorevBirimId",nullable= false)
    private Long gorevBirimId=null;

    @Column(name="gorevUnvanId",nullable= false)
    private Long gorevUnvanId=null;




    @Column(name="tcKimlikNo",nullable= false)
    private String tcKimlikNo=null;



    public String getTcKimlikNo() {
        return tcKimlikNo;
    }

    public void setTcKimlikNo(String tcKimlikNo) {
        this.tcKimlikNo = tcKimlikNo;
    }


    public Long getSuperId() {
        return superId;
    }

    public void setSuperId(Long superId) {
        this.superId = superId;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public Long getGorevBirimId() {
        return gorevBirimId;
    }

    public void setGorevBirimId(Long gorevBirimId) {
        this.gorevBirimId = gorevBirimId;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public Long getGorevUnvanId() {
        return gorevUnvanId;
    }

    public void setGorevUnvanId(Long gorevUnvanId) {
        this.gorevUnvanId = gorevUnvanId;
    }
}
