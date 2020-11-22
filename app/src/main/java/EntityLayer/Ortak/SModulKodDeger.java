package EntityLayer.Ortak;

        import AnnotationLayer.Column;
        import AnnotationLayer.Table;

/**
 * Created by isahin on 20.10.2016.
 */

@Table(name="S_MODUL_KOD_DEGER")
public class SModulKodDeger extends BaseEntity{

    @Column(name="id",nullable= false)
    private Long id=null;

    @Column(name="deger",nullable= false)
    private String deger=null;

    @Column(name="grup",nullable= false)
    private String grup=null;

    @Column(name="modulId",nullable= false)
    private String modulId=null;


    public String getModulId() {
        return modulId;
    }

    public void setModulId(String modulId) {
        this.modulId = modulId;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getDeger() {
        return deger;
    }

    public void setDeger(String deger) {
        this.deger = deger;
    }

    public String getGrup() {
        return grup;
    }

    public void setGrup(String grup) {
        this.grup = grup;
    }
}
