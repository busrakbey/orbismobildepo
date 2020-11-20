package EntityLayer.Ortak;

import AnnotationLayer.Column;
import AnnotationLayer.Table;

/**
 * Created by isahin on 17.09.2018.
 */


public class ozetGrafikGerceklesme{


    @Column(name = "ID", insertable = false, updatable = false)
    private Long				id;

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Column(name = "YIL", insertable = false, updatable = false)
    private Integer yil;

    public Integer getYil() {
        return this.yil;
    }

    public void setYil(final Integer yil) {
        this.yil = yil;
    }

    @Column(name = "HESAP_NO", insertable = false, updatable = false)
    private String hesapNo;

    public String getHesapNo() {
        return this.hesapNo;
    }

    public void setHesapNo(final String hesapNo) {
        this.hesapNo = hesapNo;
    }

    @Column(name = "TUTAR", insertable = false, updatable = false)
    private Long tutar;

    public Long getTutar() {
        return this.tutar;
    }

    public void setTutar(final Long tutar) {
        this.tutar = tutar;
    }

    @Column(name = "MAX_YIL", insertable = false, updatable = false)
    private Integer maxYil;

    public Integer getMaxYil() {
        return this.maxYil;
    }

    public void setMaxYil(final Integer maxYil) {
        this.maxYil = maxYil;
    }

    @Column(name = "MIN_YIL", insertable = false, updatable = false)
    private Integer minYil;

    public Integer getMinYil() {
        return this.minYil;
    }

    public void setMinYil(final Integer minYil) {
        this.minYil = minYil;
    }

}
