package com.senlainc.javacourses.petushokvaliantsin.service.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.dto.StateDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumState;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement_;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User_;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement.IAdvertisementDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.IStateService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.user.IUserService;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityNotAvailableException;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.annotation.SingularClass;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.annotation.SingularModel;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.IFilterParameter;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.IPageParameter;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.IStateParameter;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.implementation.FilterParameter;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.implementation.PageParameter;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.implementation.StateParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@SingularClass
public class AdvertisementService extends AbstractService<Advertisement, Long> implements IAdvertisementService {

    private static final String SORT_FIELD = "advertisement-";
    private final IAdvertisementDao advertisementDao;
    private final IStateService stateService;
    private final IUserService userService;

    @Autowired
    public AdvertisementService(IAdvertisementDao advertisementDao, IStateService stateService, IUserService userService) {
        this.advertisementDao = advertisementDao;
        this.stateService = stateService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public boolean create(AdvertisementDto object) {
        final Advertisement advertisement = dtoMapper.map(object, Advertisement.class);
        advertisement.setDate(LocalDate.now());
        advertisement.setState(stateService.read(EnumState.MODERATION.name()));
        advertisementDao.create(advertisement);
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Long index) {
        final Advertisement advertisement = advertisementDao.read(index);
        advertisement.setState(stateService.read(EnumState.DISABLED.name()));
        advertisementDao.update(advertisement);
        return true;
    }

    @Override
    @Transactional
    public boolean updateByUser(AdvertisementDto object) {
        final Advertisement advertisement = dtoMapper.map(object, Advertisement.class);
        advertisementDao.read(object.getId());
        advertisement.setDate(LocalDate.now());
        advertisement.setState(stateService.read(EnumState.MODERATION.name()));
        advertisementDao.update(advertisement);
        return true;
    }

    @Override
    @Transactional
    public boolean updateByModerator(Long advertisementIndex, StateDto state) {
        final Advertisement advertisement = advertisementDao.read(advertisementIndex);
        advertisement.setState(stateService.read(state.getDescription()));
        advertisementDao.update(advertisement);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Advertisement read(Long index) {
        return advertisementDao.read(index);
    }

    @Override
    @Transactional(readOnly = true)
    public AdvertisementDto getAdvertisementByUser(Long index) {
        final Advertisement advertisement = advertisementDao.read(index);
        if (!advertisement.getState().equals(stateService.read(EnumState.ACTIVE.name()))) {
            throw new EntityNotAvailableException("You don't have permission");
        }
        return dtoMapper.map(advertisement, AdvertisementDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public AdvertisementDto getAdvertisementByModerator(Long index) {
        return dtoMapper.map(advertisementDao.read(index), AdvertisementDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AdvertisementDto> getUserAdvertisements(Long index, int page, int numberElements, String state) {
        return dtoMapper.mapAll(advertisementDao.readAllWithState(PageParameter.of(page, numberElements)
                , userService.read(index), stateService.read(state.toUpperCase()))
                , AdvertisementDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    @SingularModel(metamodels = {Advertisement_.class, User_.class})
    public List<AdvertisementDto> getAdvertisements(int page, int numberElements, String direction, String sortField, String search,
                                                    String category, double minPrice, double maxPrice, String advertisementState) {
        final IPageParameter pageParameter = sortField.contains("-") ?
                PageParameter.of(page, numberElements, direction, singularMapper.getAttribute(SORT_FIELD + sortField.split("-")[0]),
                        singularMapper.getAttribute(sortField)) :
                PageParameter.of(page, numberElements, direction, singularMapper.getAttribute(SORT_FIELD + sortField));
        final IFilterParameter filterParameter = FilterParameter.of(search, category, minPrice, maxPrice);
        final IStateParameter stateParameter = StateParameter.of(stateService.read(advertisementState.toUpperCase()), stateService.read(EnumState.APPROVED.name()));
        return dtoMapper.mapAll(advertisementDao.readAllWithFilter(pageParameter, filterParameter, stateParameter),
                AdvertisementDto.class);
    }
}
