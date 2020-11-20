package EntityLayer.Ortak;

import AnnotationLayer.Column;
import AnnotationLayer.Table;

/**
 * Created by isahin on 24.10.2016.
 */

@Table(name="GP_BOLME")
public class GpBolme extends BaseEntity {

    @Column(name = "id", nullable = false)
    private Long id = null;

    @Column(name = "bolmeNo", nullable = false)
    private String bolmeNo = null;

    @Column(name = "seflikBirimId", nullable = false)
    private Long seflikBirimId = null;


    @Column(name = "ozmKorumaUygulamaId", nullable = false)
    private Long ozmKorumaUygulamaId = null;



    public Long getOzmKorumaUygulamaId() {
        return ozmKorumaUygulamaId;
    }

    public void setOzmKorumaUygulamaId(Long ozmKorumaUygulamaId) {
        this.ozmKorumaUygulamaId = ozmKorumaUygulamaId;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getBolmeNo() {
        return bolmeNo;
    }

    public void setBolmeNo(String bolmeNo) {
        this.bolmeNo = bolmeNo;
    }

    public Long getSeflikBirimId() {
        return seflikBirimId;
    }

    public void setSeflikBirimId(Long seflikBirimId) {
        this.seflikBirimId = seflikBirimId;
    }


    @Override
    public String toStringName() {
        return getBolmeNo();
    }


    public String toString() {
        return getBolmeNo();
    }
}
