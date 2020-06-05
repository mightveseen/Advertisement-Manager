package com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementCategory;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.IGenericDao;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.IPageParameter;

import javax.persistence.metamodel.SingularAttribute;
import java.util.List;

public interface IAdvertisementDao extends IGenericDao<Advertisement, Long> {

    <F> List<Advertisement> readAllWithSearch(IPageParameter pageParameter, SingularAttribute<Advertisement, F> field, F value);

    <F> List<Advertisement> readAllWithCategory(IPageParameter pageParameter, SingularAttribute<AdvertisementCategory, F> field, F value);
}
