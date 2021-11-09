package com.senlainc.javacourses.petushokvaliantsin.dao.api.customquery;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.IFilterParameter;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.IStateParameter;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.implementation.PageParameter;

import java.util.List;

public interface AdvertisementDaoChild {

    List<Advertisement> readAllWithFilter(PageParameter pageParameter, IFilterParameter filterParameter, IStateParameter stateParameter);

    Long readCountWitFilter(IFilterParameter filterParameter, IStateParameter stateParameter);
}
