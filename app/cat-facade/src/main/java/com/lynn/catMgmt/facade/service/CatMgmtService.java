package com.lynn.catMgmt.facade.service;

import com.lynn.catMgmt.facade.model.AddCatParam;
import com.lynn.catMgmt.facade.model.CatDto;
import com.lynn.catMgmt.facade.model.UpdateCatParam;

public interface CatMgmtService {

    CatDto queryCatByName(String name);

    void addCat(AddCatParam cat);

    void deleteCat(String name);

    void updateCat(String name, UpdateCatParam param);

}
