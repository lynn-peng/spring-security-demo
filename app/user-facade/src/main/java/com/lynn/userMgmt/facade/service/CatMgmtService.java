package com.lynn.userMgmt.facade.service;

import com.lynn.userMgmt.facade.model.AddCatParam;
import com.lynn.userMgmt.facade.model.CatDto;
import com.lynn.userMgmt.facade.model.UpdateCatParam;

public interface CatMgmtService {

    CatDto queryCatByName(String name);

    void addCat(AddCatParam cat);

    void deleteCat(String name);

    void updateCat(String name, UpdateCatParam param);

}
