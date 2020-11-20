package EntityLayer.Fidanlik;

import AnnotationLayer.Column;
import AnnotationLayer.Table;
import EntityLayer.Ortak.BaseEntity;

/**
 * Created by isahin on 06.08.2018
 */

@Table(name = "FID_AJAX_TUR")
public class FidanAjaxTur extends BaseEntity {


    @Column(name = "ID", insertable = false, updatable = false)
    private Long				id;

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Column(name = "ADI", insertable = false, updatable = false)
    private String adi;

    public String getAdi() {
        return this.adi;
    }

    public void setAdi(final String adi) {
        this.adi = adi;
    }

    @Column(name = "LATINCE_ADI_TUR", insertable = false, updatable = false)
    private String latinceAdiTur;

    public String getLatinceAdiTur() {
        return this.latinceAdiTur;
    }

    public void setLatinceAdiTur(final String latinceAdiTur) {
        this.latinceAdiTur = latinceAdiTur;
    }

    @Column(name = "STOK_ID", insertable = false, updatable = false)
    private Long stokId;

    public Long getStokId() {
        return this.stokId;
    }

    public void setStokId(final Long stokId) {
        this.stokId = stokId;
    }

    @Column(name = "DIGERCINSID", insertable = false, updatable = false)
    private Long digercinsid;

    public Long getDigercinsid() {
        return this.digercinsid;
    }

    public void setDigercinsid(final Long digercinsid) {
        this.digercinsid = digercinsid;
    }

    @Override
    public String toString()
    {
        return adi;
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

    public FidanAjaxTur() {
    }


}
