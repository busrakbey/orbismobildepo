package EntityLayer.Ortak;

import AnnotationLayer.Column;
import AnnotationLayer.Table;

/**
 * Created by isahin on 17.1.2017.
 */

@Table(name = "INIK_YOL_ITINERI")
public class SanatYapisi extends BaseEntity{


    @Column(name = "id", nullable = false)
    private Long id = null;

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Column(name = "Y", nullable = true)
    private String y = null;

    public String getY() {
        return this.y;
    }

    public void setY(final String y) {
        this.y = y;
    }

    @Column(name = "X", nullable = true)
    private String x = null;

    public String getX() {
        return this.x;
    }

    public void setX(final String x) {
        this.x = x;
    }





    @Column(name = "SANATCINS", nullable = true)
    private String sanatcins = null;

    public String getSanatcins() {
        return this.sanatcins;
    }

    public void setSanatcins(final String sanatcins) {
        this.sanatcins = sanatcins;
    }

    @Column(name = "SANATCINSKOD", nullable = true)
    private String sanatcinskod = null;

    public String getSanatcinskod() {
        return this.sanatcinskod;
    }

    public void setSanatcinskod(final String sanatcinskod) {
        this.sanatcinskod = sanatcinskod;
    }

    @Column(name = "DARGENIS", nullable = true)
    private String dargenis = null;

    public String getDargenis() {
        return this.dargenis;
    }

    public void setDargenis(final String dargenis) {
        this.dargenis = dargenis;
    }

    @Column(name = "YARICAP", nullable = true)
    private String yaricap = null;

    public String getYaricap() {
        return this.yaricap;
    }

    public void setYaricap(final String yaricap) {
        this.yaricap = yaricap;
    }

    @Column(name = "ACIKLIK", nullable = true)
    private String aciklik = null;

    public String getAciklik() {
        return this.aciklik;
    }

    public void setAciklik(final String aciklik) {
        this.aciklik = aciklik;
    }

    @Column(name = "EN", nullable = true)
    private String en = null;

    public String getEn() {
        return this.en;
    }

    public void setEn(final String en) {
        this.en = en;
    }

    @Column(name = "BOY", nullable = true)
    private String boy = null;

    public String getBoy() {
        return this.boy;
    }

    public void setBoy(final String boy) {
        this.boy = boy;
    }

    @Column(name = "YUKSEKLIK", nullable = true)
    private String yukseklik = null;

    public String getYukseklik() {
        return this.yukseklik;
    }

    public void setYukseklik(final String yukseklik) {
        this.yukseklik = yukseklik;
    }

    @Column(name = "CAP", nullable = true)
    private String cap = null;

    public String getCap() {
        return this.cap;
    }

    public void setCap(final String cap) {
        this.cap = cap;
    }

    @Column(name = "YOL_ID", nullable = true)
    private Long yolId = null;

    public Long getYolId() {
        return this.yolId;
    }

    public void setYolId(final Long yolId) {
        this.yolId = yolId;
    }

    @Column(name = "YOL_ADI", nullable = true)
    private String yolAdi = null;

    @Column(name = "YOL_KODU", nullable = true)
    private String yolKodu = null;

    @Column(name = "YOL_MID", nullable = true)
    private Long yolMid = null;


    public Long getYolMid() {
        return yolMid;
    }

    public void setYolMid(Long yolMid) {
        this.yolMid = yolMid;
    }

    @Column(name = "mid")
    private Long mid;

    @Column(name = "mustid")
    private Long mustid;



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


    public String getYolAdi() {
        return yolAdi;
    }

    public void setYolAdi(String yolAdi) {
        this.yolAdi = yolAdi;
    }

    public String getYolKodu() {
        return yolKodu;
    }

    public void setYolKodu(String yolKodu) {
        this.yolKodu = yolKodu;
    }
}
