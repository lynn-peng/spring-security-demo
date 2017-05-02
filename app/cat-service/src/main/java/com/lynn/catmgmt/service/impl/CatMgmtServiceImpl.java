package com.lynn.catmgmt.service.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lynn.catMgmt.facade.exception.CatAlreadyExistsException;
import com.lynn.catMgmt.facade.exception.CatNotFoundException;
import com.lynn.catMgmt.facade.model.AddCatParam;
import com.lynn.catMgmt.facade.model.CatDto;
import com.lynn.catMgmt.facade.model.UpdateCatParam;
import com.lynn.catMgmt.facade.service.CatMgmtService;
import com.lynn.catmgmt.service.model.CatsPo;

public class CatMgmtServiceImpl implements CatMgmtService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CatMgmtServiceImpl.class);

    private static final Map<String, CatsPo> CATS = new ConcurrentHashMap();

    static {
        populateCat();
    }

    @Override
    public CatDto queryCatByName(String name) {
        CatsPo catPo = CATS.get(name);
        if (catPo == null) {
            LOGGER.error("Cat {} not found.", name);
            throw new CatNotFoundException(String.format("Cat %s not found.", name));
        }

        return toUserDto(catPo);
    }

    @Override
    public void addCat(AddCatParam cat) {
        CatsPo po = toUserPo(cat);
        boolean isExist = CATS.containsKey(po.getFormalName());
        if (isExist) {
            LOGGER.error("Cat {} already exists.", cat.getFormalName());
            throw new CatAlreadyExistsException(
                    String.format("Cat %s already exists.", cat.getFormalName()));
        }

        CATS.put(cat.getFormalName(), po);
    }

    @Override
    public void deleteCat(String name) {
        CATS.remove(name);
    }

    @Override
    public void updateCat(String name, UpdateCatParam param) {
        if (CATS.containsKey(name)) {
            CatsPo po = CATS.get(name);
            if (param.getNickname() != null)
                po.setNickname(param.getNickname());
            if (param.getAge() != null)
                po.setAge(param.getAge());
            if (param.getIsMale() != null)
                po.setMale(param.getIsMale());
        } else {
            throw new CatNotFoundException(String.format("%s not found.", name));
        }
    }

    private static void populateCat() {
        CatsPo po1 = new CatsPo();
        po1.setFormalName("lynn001");
        po1.setNickname("xigua");
        po1.setAge(2);
        po1.setMale(false);
        CATS.put(po1.getFormalName(), po1);
        CatsPo po2 = new CatsPo();
        po2.setFormalName("lynn002");
        po2.setNickname("bai");
        po2.setAge(2);
        po2.setMale(false);
        CATS.put(po2.getFormalName(), po2);
    }

    private CatDto toUserDto(CatsPo po) {
        CatDto dto = new CatDto();
        dto.setFormalName(po.getFormalName());
        dto.setNickname(po.getNickname());
        dto.setAge(po.getAge());
        dto.setIsMale(po.isMale());

        return dto;
    }

    private CatsPo toUserPo(AddCatParam param) {
        CatsPo po = new CatsPo();
        po.setFormalName(param.getFormalName());
        po.setNickname(param.getNickname());
        po.setAge(param.getAge() == null ? 0 : param.getAge());

        return po;
    }

}
