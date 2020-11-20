package EntityLayer.IsletmePazarlama;

import AnnotationLayer.Column;
import AnnotationLayer.Table;
import EntityLayer.Ortak.BaseEntity;

/**
 * Created by isahin on 2.11.2016.
 */

@Table(name = "MUH_STOK_LOKASYON")
public class MuhStokLokasyon extends BaseEntity {

    @Column(name = "id", nullable = false)
    private Long	id	= null;

    @Column(name = "adi", nullable = false)
    private String	adi	= null;

    @Column(name = "ustId", nullable = false)
    private Long	ustId	= null;

    @Column(name = "birimId", nullable = false)
    private Long	birimId	= null;


    @Column(name = "parselNo", nullable = false)
    private String	parselNo	= null;

    @Column(name = "lokasyonTipi", nullable = false)
    private String	lokasyonTipi	= null;


    public String getParselNo() {
        return parselNo;
    }

    public void setParselNo(String parselNo) {
        this.parselNo = parselNo;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public Long getUstId() {
        return ustId;
    }

    public void setUstId(Long ustId) {
        this.ustId = ustId;
    }

    public Long getBirimId() {
        return birimId;
    }

    public void setBirimId(Long birimId) {
        this.birimId = birimId;
    }

    public String getLokasyonTipi() {
        return lokasyonTipi;
    }

    public void setLokasyonTipi(String lokasyonTipi) {
        this.lokasyonTipi = lokasyonTipi;
    }

    @Override
    public String toStringName() {
        String parsel_no = "";
        if(getParselNo() != null)
            parsel_no = "-"+getParselNo();
        return getAdi() + parsel_no;
    }

    @Override
    public String toString() {
        String parsel_no = "";
        if(getParselNo() != null)
            parsel_no = "-"+getParselNo();
        return getAdi() + parsel_no;
    }
}
