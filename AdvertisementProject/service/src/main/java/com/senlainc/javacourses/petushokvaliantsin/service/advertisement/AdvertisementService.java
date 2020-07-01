package com.senlainc.javacourses.petushokvaliantsin.service.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.dto.StateDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumException;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumState;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement_;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User_;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.IStateDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement.IAdvertisementDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user.IUserDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementService;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityNotAvailableException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.PermissionDeniedException;
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
    private static final String SORT_PARAMETER_SEPARATOR = "-";
    private final IAdvertisementDao advertisementDao;
    private final IStateDao stateDao;
    private final IUserDao userDao;

    @Autowired
    public AdvertisementService(IAdvertisementDao advertisementDao, IStateDao stateDao, IUserDao userDao) {
        this.advertisementDao = advertisementDao;
        this.stateDao = stateDao;
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public boolean create(String username, AdvertisementDto advertisementDto) {
        final Advertisement advertisement = dtoMapper.map(advertisementDto, Advertisement.class);
        advertisement.setUser(userDao.readByUserCred(username));
        advertisement.setDate(LocalDate.now());
        advertisement.setState(stateDao.read(EnumState.MODERATION.name()));
        advertisementDao.create(advertisement);
        return true;
    }

    @Override
    @Transactional
    public boolean delete(String username, Long advertisementIndex) {
        final Advertisement advertisement = advertisementDao.read(advertisementIndex);
        if (!advertisement.getUser().getId().equals(userDao.readByUserCred(username).getId())) {
            throw new PermissionDeniedException(EnumException.PERMISSION_EXCEPTION.getMessage());
        }
        advertisement.setState(stateDao.read(EnumState.DISABLED.name()));
        advertisementDao.update(advertisement);
        return true;
    }

    @Override
    @Transactional
    public boolean updateByUser(String username, AdvertisementDto advertisementDto) {
        if (!advertisementDao.read(advertisementDto.getId()).getUser().getId().equals(userDao.readByUserCred(username).getId())) {
            throw new PermissionDeniedException(EnumException.PERMISSION_EXCEPTION.getMessage());
        }
        final Advertisement advertisement = dtoMapper.map(advertisementDto, Advertisement.class);
        advertisement.setDate(LocalDate.now());
        advertisement.setState(stateDao.read(EnumState.MODERATION.name()));
        advertisementDao.update(advertisement);
        return true;
    }

    @Override
    @Transactional
    public boolean updateStateByModerator(Long advertisementIndex, StateDto state) {
        final Advertisement advertisement = advertisementDao.read(advertisementIndex);
        advertisement.setState(stateDao.read(state.getDescription()));
        advertisementDao.update(advertisement);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public AdvertisementDto getAdvertisementByUser(Long index) {
        final Advertisement advertisement = advertisementDao.read(index);
        if (!advertisement.getState().getId().equals(stateDao.read(EnumState.ACTIVE.name()).getId())) {
            throw new EntityNotAvailableException(EnumException.PERMISSION_EXCEPTION.getMessage());
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
    public List<AdvertisementDto> getUserAdvertisements(Long index, int page, int numberElements, EnumState state) {
        return dtoMapper.mapAll(advertisementDao.readAllWithUser(PageParameter.of(page, numberElements)
                , userDao.read(index), stateDao.read(state.name()))
                , AdvertisementDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    @SingularModel(metamodels = {Advertisement_.class, User_.class})
    public List<AdvertisementDto> getAdvertisements(int page, int numberElements, String direction, String sortField, String search,
                                                    String category, double minPrice, double maxPrice, EnumState advertisementState) {
        final IPageParameter pageParameter = sortField.contains(SORT_PARAMETER_SEPARATOR) ?
                PageParameter.of(page, numberElements, direction, singularMapper.getAttribute(SORT_FIELD + sortField.split(SORT_PARAMETER_SEPARATOR)[0]),
                        singularMapper.getAttribute(sortField)) :
                PageParameter.of(page, numberElements, direction, singularMapper.getAttribute(SORT_FIELD + sortField));
        final IFilterParameter filterParameter = FilterParameter.of(search, category, minPrice, maxPrice);
        final IStateParameter stateParameter = StateParameter.of(stateDao.read(advertisementState.name()), stateDao.read(EnumState.APPROVED.name()));
        return dtoMapper.mapAll(advertisementDao.readAllWithFilter(pageParameter, filterParameter, stateParameter),
                AdvertisementDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getSize(EnumState state) {
        return advertisementDao.readCountWithState(stateDao.read(state.name()));
    }
}
