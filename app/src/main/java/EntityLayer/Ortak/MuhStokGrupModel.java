package EntityLayer.Ortak;

/**
 * Created by isahin on 30.3.2017.
 */
public class MuhStokGrupModel {

    @Override
    public String toString()
    {
        return  getModelAdi();
    }

    private Long	id	= null;

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }


    private Long	stokGrupId	= null;

    public Long getStokGrupId() {
        return this.stokGrupId;
    }

    public void setStokGrupId(final Long stokGrupId) {
        this.stokGrupId = stokGrupId;
    }





    private String	modelAdi	= null;

    public String getModelAdi() {
        return this.modelAdi;
    }

    public void setModelAdi(final String modelAdi) {
        this.modelAdi = modelAdi;
    }


    private Long	modelId	= null;

    public Long getModelId() {
        return this.modelId;
    }

    public void setModelId(final Long modelId) {
        this.modelId = modelId;
    }
}
