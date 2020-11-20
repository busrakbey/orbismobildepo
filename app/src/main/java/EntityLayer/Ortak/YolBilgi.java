package EntityLayer.Ortak;

import java.math.BigDecimal;
import java.util.List;

import AnnotationLayer.Column;
import AnnotationLayer.Table;
import AnnotationLayer.Transient;

/**
 * Created by konumsalpc5 on 19.11.2018.
 */

@Table(name = "YOL_BILGI")
public class YolBilgi extends BaseEntity{

    @Column(name = "id", nullable = true)
    private Long id = null;

    @Column(name = "yolId", nullable = true)
    private Long yolId = null;

    @Column(name = "TUL", nullable = true)
    private  String TUL = null;

    @Column(name = "SIRA_NO", nullable = true)
    private  String SIRA_NO = null;

    @Column(name = "YOL_BASLANGIC_X", nullable = true)
    private  String YOL_BASLANGIC_X= null;

    @Column(name = "YOL_BASLANGIC_Y", nullable = true)
    private  String YOL_BASLANGIC_Y= null;

    @Column(name = "YOL_BITIS_X", nullable = true)
    private  String YOL_BITIS_X= null;

    @Column(name = "YOL_BITIS_Y", nullable = true)
    private  String YOL_BITIS_Y= null;

    @Column(name = "YOL_BASLANGIC_UTM_X", nullable = true)
    private  String YOL_BASLANGIC_UTM_X= null;

    @Column(name = "YOL_BASLANGIC_UTM_Y", nullable = true)
    private  String YOL_BASLANGIC_UTM_Y= null;

    @Column(name = "YOL_BITIS_UTM_X", nullable = true)
    private  String YOL_BITIS_UTM_X= null;

    @Column(name = "YOL_BITIS_UTM_Y", nullable = true)
    private  String YOL_BITIS_UTM_Y= null;

    @Column(name = "YOL_TIPI", nullable = true)
    private  String YOL_TIPI= null;

    @Column(name = "islemDurumu", nullable = true)
    private String islemDurumu = null;

    @Column(name = "baslangici", nullable = true)
   private BigDecimal baslangici = null;

    @Column(name = "bitisi", nullable = true)
    private BigDecimal bitisi = null;

    @Column(name = "offset", nullable = true)
    private Long offset = null;


    @Column(name = "aciklama", nullable = true)
    private String aciklama = null;

    @Column(name = "tarih", nullable = true)
    private String tarih = null;

    @Column(name = "yolBirimKodu", nullable = true)
    private String yolBirimKodu = null;

    @Column(name = "eklenecekTul", nullable = true)
    private BigDecimal eklenecekTul = null;

    @Column(name = "offsetbaslangic", nullable = true)
    private BigDecimal offsetbaslangic = null;

    @Column(name = "offsetbitis", nullable = true)
    private BigDecimal offsetbitis = null;



    @Column(name = "yapimSekli", nullable = true)
    private Integer yapimSekli = null;


    @Column(name = "durum", nullable = true)
    private Integer durum = null;


    @Column(name = "mid")
    private Long mid;

    @Column(name = "mustid")
    private Long mustid;

    @Column(name = "orgId")
    private Long orgId;


    @Column(name = "gonderildi")
    private Integer gonderildi=0;

    @Transient
    private List<MobilGuzergah> guzerhList = null;


    @Override
    public Long getMid() {
        return mid;
    }

    @Override
    public void setMid(Long mid) {
        this.mid = mid;
    }

    @Override
    public Long getMustid() {
        return mustid;
    }

    @Override
    public void setMustid(Long mustid) {
        this.mustid = mustid;
    }

    @Override
    public Long getOrgId() {
        return orgId;
    }

    @Override
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    @Override
    public Integer getGonderildi() {
        return gonderildi;
    }

    @Override
    public void setGonderildi(Integer gonderildi) {
        this.gonderildi = gonderildi;
    }

    public String getYOL_TIPI() {
        return YOL_TIPI;
    }

    public void setYOL_TIPI(String YOL_TIPI) {
        this.YOL_TIPI = YOL_TIPI;
    }

    public String getSIRA_NO() {
        return SIRA_NO;
    }

    public void setSIRA_NO(String SIRA_NO) {
        this.SIRA_NO = SIRA_NO;
    }

    public String getYOL_BASLANGIC_X() {
        return YOL_BASLANGIC_X;
    }

    public void setYOL_BASLANGIC_X(String YOL_BASLANGIC_X) {
        this.YOL_BASLANGIC_X = YOL_BASLANGIC_X;
    }

    public String getYOL_BASLANGIC_Y() {
        return YOL_BASLANGIC_Y;
    }

    public void setYOL_BASLANGIC_Y(String YOL_BASLANGIC_Y) {
        this.YOL_BASLANGIC_Y = YOL_BASLANGIC_Y;
    }

    public String getYOL_BITIS_X() {
        return YOL_BITIS_X;
    }

    public void setYOL_BITIS_X(String YOL_BITIS_X) {
        this.YOL_BITIS_X = YOL_BITIS_X;
    }

    public String getYOL_BITIS_Y() {
        return YOL_BITIS_Y;
    }

    public void setYOL_BITIS_Y(String YOL_BITIS_Y) {
        this.YOL_BITIS_Y = YOL_BITIS_Y;
    }

    public String getYOL_BASLANGIC_UTM_X() {
        return YOL_BASLANGIC_UTM_X;
    }

    public void setYOL_BASLANGIC_UTM_X(String YOL_BASLANGIC_UTM_X) {
        this.YOL_BASLANGIC_UTM_X = YOL_BASLANGIC_UTM_X;
    }

    public String getYOL_BASLANGIC_UTM_Y() {
        return YOL_BASLANGIC_UTM_Y;
    }

    public void setYOL_BASLANGIC_UTM_Y(String YOL_BASLANGIC_UTM_Y) {
        this.YOL_BASLANGIC_UTM_Y = YOL_BASLANGIC_UTM_Y;
    }

    public String getYOL_BITIS_UTM_X() {
        return YOL_BITIS_UTM_X;
    }

    public void setYOL_BITIS_UTM_X(String YOL_BITIS_UTM_X) {
        this.YOL_BITIS_UTM_X = YOL_BITIS_UTM_X;
    }

    public String getYOL_BITIS_UTM_Y() {
        return YOL_BITIS_UTM_Y;
    }

    public void setYOL_BITIS_UTM_Y(String YOL_BITIS_UTM_Y) {
        this.YOL_BITIS_UTM_Y = YOL_BITIS_UTM_Y;
    }


    public String getTUL() {
        return TUL;
    }

    public void setTUL(String TUL) {
        this.TUL = TUL;
    }



    public List<MobilGuzergah> getGuzerhList() {
        return guzerhList;
    }

    public void setGuzerhList(List<MobilGuzergah> guzerhList) {
        this.guzerhList = guzerhList;
    }



    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }




    public Long getYolId() {
        return this.yolId;
    }

    public void setYolId(final Long yolId) {
        this.yolId = yolId;
    }




    public String getIslemDurumu() {
        return this.islemDurumu;
    }

    public void setIslemDurumu(final String islemDurumu) {
        this.islemDurumu = islemDurumu;
    }




    public BigDecimal getBaslangici() {
        return this.baslangici;
    }

    public void setBaslangici(final BigDecimal baslangici) {
        this.baslangici = baslangici;
    }




    public BigDecimal getBitisi() {
        return this.bitisi;
    }

    public void setBitisi(final BigDecimal bitisi) {
        this.bitisi = bitisi;
    }




    public Long getOffset() {
        return this.offset;
    }

    public void setOffset(final Long offset) {
        this.offset = offset;
    }





    public String getAciklama() {
        return this.aciklama;
    }

    public void setAciklama(final String aciklama) {
        this.aciklama = aciklama;
    }



    public String getTarih() {
        return this.tarih;
    }

    public void setTarih(final String tarih) {
        this.tarih = tarih;
    }



    public String getYolBirimKodu() {
        return this.yolBirimKodu;
    }

    public void setYolBirimKodu(final String yolBirimKodu) {
        this.yolBirimKodu = yolBirimKodu;
    }


    public BigDecimal getEklenecekTul() {
        return this.eklenecekTul;
    }

    public void setEklenecekTul(final BigDecimal eklenecekTul) {
        this.eklenecekTul = eklenecekTul;
    }



    public BigDecimal getOffsetbaslangic() {
        return this.offsetbaslangic;
    }

    public void setOffsetbaslangic(final BigDecimal offsetbaslangic) {
        this.offsetbaslangic = offsetbaslangic;
    }


    public BigDecimal getOffsetbitis() {
        return this.offsetbitis;
    }

    public void setOffsetbitis(final BigDecimal offsetbitis) {
        this.offsetbitis = offsetbitis;
    }








    public Integer getYapimSekli() {
        return this.yapimSekli;
    }

    public void setYapimSekli(final Integer yapimSekli) {
        this.yapimSekli = yapimSekli;
    }





    public Integer getDurum() {
        return this.durum;
    }

    public void setDurum(final Integer durum) {
        this.durum = durum;
    }


}
