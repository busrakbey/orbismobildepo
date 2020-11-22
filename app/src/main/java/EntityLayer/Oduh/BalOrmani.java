package EntityLayer.Oduh;
import com.google.gson.annotations.SerializedName;


public class BalOrmani {

    private Long id;

    @SerializedName("bolgeId")
    private Long bolgeId;
    private String bolgeAdi;
    private Long isletmeId;
    private String isletmeAdi;
    private Long seflikId;
    private String seflikAdi;
    private Long yil;
    private Long balOrmaniSayisi;
    private  Double toplamAlan;
    private  Double balToplamKapasite;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBolgeId() {
        return bolgeId;
    }

    public void setBolgeId(Long bolgeId) {
        this.bolgeId = bolgeId;
    }

    public String getBolgeAdi() {
        return bolgeAdi;
    }

    public void setBolgeAdi(String bolgeAdi) {
        this.bolgeAdi = bolgeAdi;
    }

    public Long getIsletmeId() {
        return isletmeId;
    }

    public void setIsletmeId(Long isletmeId) {
        this.isletmeId = isletmeId;
    }

    public String getIsletmeAdi() {
        return isletmeAdi;
    }

    public void setIsletmeAdi(String isletmeAdi) {
        this.isletmeAdi = isletmeAdi;
    }

    public Long getSeflikId() {
        return seflikId;
    }

    public void setSeflikId(Long seflikId) {
        this.seflikId = seflikId;
    }

    public String getSeflikAdi() {
        return seflikAdi;
    }

    public void setSeflikAdi(String seflikAdi) {
        this.seflikAdi = seflikAdi;
    }

    public Long getBalOrmaniSayisi() {
        return balOrmaniSayisi;
    }

    public void setBalOrmaniSayisi(Long balOrmaniSayisi) {
        this.balOrmaniSayisi = balOrmaniSayisi;
    }

    public Double getToplamAlan() {
        return toplamAlan;
    }

    public void setToplamAlan(Double toplamAlan) {
        this.toplamAlan = toplamAlan;
    }

    public Double getBalToplamKapasite() {
        return balToplamKapasite;
    }

    public void setBalToplamKapasite(Double balToplamKapasite) {
        this.balToplamKapasite = balToplamKapasite;
    }

    public Long getYil() {
        return yil;
    }

    public void setYil(Long yil) {
        this.yil = yil;
    }
}
