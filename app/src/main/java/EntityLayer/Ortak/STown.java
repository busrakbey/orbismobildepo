package EntityLayer.Ortak;

import AnnotationLayer.Column;
import AnnotationLayer.Table;

/**
 * Created by isahin on 27.3.2017.
 */

@Table(name = "S_ILCE")
public class STown extends BaseEntity{


    @Override
    public String toString()
    {
        return gorunum;
    }

    @Column(name="id",nullable= false)
    private Long id = null;

    public Long getId(){
        return this.id;
    }

    public void setId(final Long id){
        this.id = id;
    }

    @Column(name="il_id",nullable= false)
    private Long ilId = null;

    public Long getIlId(){
        return this.ilId;
    }

    public void setIlId(final Long ilId){
        this.ilId = ilId;
    }

    @Column(name="ilceId",nullable= false)
    private Long ilceId = null;


    public Long getIlceId() {
        return ilceId;
    }

    public void setIlceId(Long ilceId) {
        this.ilceId = ilceId;
    }

    @Column(name="adi",nullable= false)
    private String adi = null;

    public String getAdi(){
        return this.adi;
    }

    public void setAdi(final String adi){
        this.adi = adi;
    }


    @Column(name="gorunum",nullable= false)
    private String gorunum = null;

    public String getGorunum(){
        return this.gorunum;
    }

    public void setGorunum(final String gorunum){
        this.gorunum = gorunum;
    }

}
