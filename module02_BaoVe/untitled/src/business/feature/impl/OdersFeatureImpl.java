package business.feature.impl;

import business.entity.Oders;
import business.entity.User;
import business.feature.IOdersFeature;
import business.untils.IOFile;

import java.util.List;

public class OdersFeatureImpl implements IOdersFeature {
    public static List<Oders> odersList = IOFile.readObjectFromFile(IOFile.PATH_ODERS);
    @Override
    public void addOrUpdate(Oders oders) {
        int index =findIndexByID(oders.getOderId());
        if(index != -1) {
            odersList.set(index, oders);
        }else  {
            odersList.add(oders);
        }
        IOFile.writeObjectToFile(odersList, IOFile.PATH_ODERS);
    }

    @Override
    public void delete(Integer id) {
        odersList.remove(findIndexByID(id));
        IOFile.writeObjectToFile(odersList, IOFile.PATH_ODERS);
    }

    @Override
    public int findIndexByID(Integer id) {

        return odersList.stream()
                .map(Oders::getOderId).toList().indexOf(id);

    }
}
