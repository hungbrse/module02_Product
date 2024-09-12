package business.feature.impl;

import business.entity.OderDetail;
import business.entity.Oders;
import business.feature.IOderDetailFeature;
import business.untils.IOFile;

import java.util.List;

public class OderDetailFeatureImpl  implements IOderDetailFeature {

    public static List<OderDetail> oderDetailList = IOFile.readObjectFromFile(IOFile.PATH_ODERDETAIL);


    @Override
    public void addOrUpdate(OderDetail oderDetail) {
//        int index =findIndexByID(oderDetail.getOrderId());
//        if(index != -1) {
//            oderDetailList.set(index, oderDetail);
//        }else  {
            oderDetailList.add(oderDetail);
//        }
        IOFile.writeObjectToFile(oderDetailList, IOFile.PATH_ODERDETAIL);
    }

    @Override
    public void delete(Integer id) {
        oderDetailList.remove(findIndexByID(id));
        IOFile.writeObjectToFile(oderDetailList, IOFile.PATH_ODERDETAIL);
    }

    @Override
    public int findIndexByID(Integer id) {

        return oderDetailList.stream()
                .map(OderDetail::getOrderId).toList().indexOf(id);
    }
}
