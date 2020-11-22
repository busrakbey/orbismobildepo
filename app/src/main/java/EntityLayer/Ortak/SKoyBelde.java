package EntityLayer.Ortak;

import AnnotationLayer.Column;

/**
 * Created by isahin on 24.5.2017.
 */
public class SKoyBelde {



    @Override
    public String toString()
    {
        return koyAdi+"-"+mulkiYerAdi+"-"+ilAdi;
    }

    @Column(name = "id", nullable = false)
    private Long	id	= null;

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Column(name = "koy_adi", nullable = false)
    private String	koyAdi	= null;

    public String getKoyAdi() {
        return this.koyAdi;
    }

    public void setKoyAdi(final String koyAdi) {
        this.koyAdi = koyAdi;
    }

    @Column(name = "mulki_yer_id", nullable = false)
    private Long	mulkiYerId	= null;

    public Long getMulkiYerId() {
        return this.mulkiYerId;
    }

    public void setMulkiYerId(final Long mulkiYerId) {
        this.mulkiYerId = mulkiYerId;
    }


    @Column(name = "ilgili_birim_id", nullable = true)
    private Long	ilgiliBirimId	= null;

    public Long getIlgiliBirimId() {
        return this.ilgiliBirimId;
    }

    public void setIlgiliBirimId(final Long ilgiliBirimId) {
        this.ilgiliBirimId = ilgiliBirimId;
    }


    @Column(name = "il_id", nullable = true)
    private Long	ilId	= null;

    public Long getIlId() {
        return this.ilId;
    }

    public void setIlId(final Long ilId) {
        this.ilId = ilId;
    }


    @Column(name = "IL_ADI", insertable = false, updatable = false)
    private String	ilAdi;

    public String getIlAdi() {
        return this.ilAdi;
    }

    public void setIlAdi(final String ilAdi) {
        this.ilAdi = ilAdi;
    }

    @Column(name = "BIRIM_ADI", insertable = false, updatable = false)
    private String	birimAdi;

    public String getBirimAdi() {
        return this.birimAdi;
    }

    public void setBirimAdi(final String birimAdi) {
        this.birimAdi = birimAdi;
    }


    @Column(name = "MULKI_YER_ADI", insertable = false, updatable = false)
    private String	mulkiYerAdi;

    public String getMulkiYerAdi() {
        return this.mulkiYerAdi;
    }

    public void setMulkiYerAdi(final String mulkiYerAdi) {
        this.mulkiYerAdi = mulkiYerAdi;
    }
}
