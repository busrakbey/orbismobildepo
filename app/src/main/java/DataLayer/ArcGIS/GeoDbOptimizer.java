package DataLayer.ArcGIS;

import android.app.Activity;
import android.content.Context;

import com.esri.android.map.FeatureLayer;
import com.esri.core.geodatabase.Geodatabase;
import com.esri.core.geodatabase.GeodatabaseFeatureTable;
import com.esri.core.map.CallbackListener;
import com.esri.core.map.Feature;
import com.esri.core.map.FeatureResult;
import com.esri.core.table.FeatureTable;
import com.esri.core.tasks.query.QueryParameters;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


import ToolLayer.OrbisDefaultException;

/**
 * Created by Test on 29.9.2015.
 */
public class GeoDbOptimizer
{

    Geodatabase localGdb = null;
    GeodatabaseFeatureTable gft_bolge = null;
    GeodatabaseFeatureTable gft_bolme = null;
    GeodatabaseFeatureTable gft_isletme_md = null;
    public GeodatabaseFeatureTable gft_ornek_alan = null;
    public GeodatabaseFeatureTable gft_bolmecik = null;
    GeodatabaseFeatureTable gft_plan = null;
    GeodatabaseFeatureTable gft_seflik = null;
    public GeodatabaseFeatureTable gft_EN_KAR_AGAC = null;
    public GeoDbOptimizer(String featureLayerPath)
    {
        try
        {
            localGdb = new Geodatabase(featureLayerPath);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        if (localGdb != null)
        {
            for (GeodatabaseFeatureTable gdbFeatureTable : localGdb.getGeodatabaseTables())
            {


                    String tblName2 =  gdbFeatureTable.getTableName();
                    switch (tblName2)
                    {
                        case "PLAN":
                            gft_plan = gdbFeatureTable;

                            break;
                        case "ORNEK_ALAN":
                            gft_ornek_alan = gdbFeatureTable ;

                            break;
                        case "BOLGE":
                            gft_bolge = gdbFeatureTable;
                            break;
                        case "ISLETME_MD":
                            gft_isletme_md = gdbFeatureTable;
                            break;
                        case "SEFLIK":
                            gft_seflik = gdbFeatureTable;
                            break;
                        case "BOLME":
                            gft_bolme = gdbFeatureTable;
                            break;
                        case "BOLMECIK":
                            gft_bolmecik= gdbFeatureTable;
                            break;
                        case "CBSORBIS.EN_KAR_AGAC":
                            gft_EN_KAR_AGAC= gdbFeatureTable;
                            break;
                        default:
                            break;
                    }
            }
        }


    }

}
