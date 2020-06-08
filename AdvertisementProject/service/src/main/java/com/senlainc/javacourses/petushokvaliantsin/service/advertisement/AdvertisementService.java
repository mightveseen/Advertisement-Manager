package com.senlainc.javacourses.petushokvaliantsin.service.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementDto;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementCategory_;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement_;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement.IAdvertisementDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.IStateService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementService;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.annotation.SingularClass;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.annotation.SingularModel;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.implementation.PageParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@SingularClass
public class AdvertisementService extends AbstractService<Advertisement, Long> implements IAdvertisementService {

    private final IAdvertisementDao advertisementDao;
    private final IStateService stateService;

    @Autowired
    public AdvertisementService(IAdvertisementDao advertisementDao, IStateService stateService) {
        this.advertisementDao = advertisementDao;
        this.stateService = stateService;
    }

    @Override
    @Transactional
    public boolean create(Advertisement object) {
        advertisementDao.create(object);
        return true;
    }

    @Override
    @Transactional
    public boolean create(AdvertisementDto object) {
        final Advertisement advertisement = dtoMapper.map(object, Advertisement.class);
        advertisementDao.create(advertisement);
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Long index) {
        final Advertisement advertisement = advertisementDao.read(index);
        advertisement.setState(stateService.read("DISABLED"));
        advertisementDao.update(advertisement);
        return true;
    }

    @Override
    @Transactional
    public boolean update(Advertisement object) {
        advertisementDao.update(object);
        return true;
    }

    @Override
    @Transactional
    public boolean update(AdvertisementDto object) {
        advertisementDao.update(dtoMapper.map(object, Advertisement.class));
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Advertisement read(Long index) {
        return advertisementDao.read(index);
    }

    @Override
    @Transactional(readOnly = true)
    public AdvertisementDto getAdvertisement(Long index) {
        return dtoMapper.map(advertisementDao.read(index), AdvertisementDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    @SingularModel(metamodels = Advertisement_.class)
    public List<AdvertisementDto> getAdvertisements(int page, int numberElements, String direction, String sortField, String category, String search) {
        if (!search.equals("none")) {
            return dtoMapper.mapAll(advertisementDao.readAllWithSearch(PageParameter.of(page, numberElements, direction,
                    singularMapper.getSingularAttribute("Advertisement-" + sortField.toLowerCase())), Advertisement_.header, search),
                    AdvertisementDto.class);
        }
        if (!category.equals("none")) {
            return dtoMapper.mapAll(advertisementDao.readAllWithCategory(PageParameter.of(page, numberElements, direction,
                    singularMapper.getSingularAttribute("Advertisement-" + sortField.toLowerCase())), AdvertisementCategory_.description, category),
                    AdvertisementDto.class);
        }
        return dtoMapper.mapAll(advertisementDao.readAll(PageParameter.of(page, numberElements, direction,
                singularMapper.getSingularAttribute("Advertisement-" + sortField.toLowerCase()))),
                AdvertisementDto.class);
    }
}
