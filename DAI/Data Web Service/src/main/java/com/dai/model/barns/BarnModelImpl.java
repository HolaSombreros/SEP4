package com.dai.model.barns;

import com.dai.Helper;
import com.dai.dao.barn.BarnDao;
import com.dai.shared.Barn;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BarnModelImpl implements BarnModel{

    private BarnDao barnDao;

    public BarnModelImpl(BarnDao barnDao) {
        this.barnDao = barnDao;
    }

    @Override
    public List<Barn> getAll() throws Exception {
        return Helper.await(barnDao.getAll());
    }

    @Override
    public Barn create(Barn barn) throws Exception {
        return Helper.await(barnDao.create(barn));
    }
}
