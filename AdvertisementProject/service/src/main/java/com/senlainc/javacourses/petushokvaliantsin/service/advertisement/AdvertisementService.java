package com.senlainc.javacourses.petushokvaliantsin.service.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.dto.StateDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumException;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumLogger;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumState;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.GraphProperty;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement_;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User_;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.IStateDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement.IAdvertisementDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user.IUserDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementService;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityNotAvailableException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityNotExistException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.PermissionDeniedException;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.annotation.SingularClass;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.annotation.SingularModel;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.IFilterParameter;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.IPageParameter;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.IStateParameter;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.implementation.FilterParameter;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.implementation.PageParameter;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.implementation.StateParameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@SingularClass
public class AdvertisementService extends AbstractService implements IAdvertisementService {

    private static final Logger LOGGER = LogManager.getLogger(AdvertisementService.class);
    private static final String SORT_FIELD = "advertisement-";
    private static final String[] CLASSES = {"Advertisement", "State", "User"};
    private static final String[] CLASS_FIELDS = {"id", "description", "username"};
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
        advertisement.setUser(userDao.readByUserCred(username, GraphProperty.User.DEFAULT).orElseThrow(() ->
                new EntityNotExistException(String.format(EnumException.ENTITY_WITH_FIELD_NOT_EXIST.getMessage(), CLASSES[2], CLASS_FIELDS[2], username))));
        advertisement.setDate(LocalDate.now());
        advertisement.setState(stateDao.readByDescription(EnumState.MODERATION.name()).orElseThrow(() ->
                new EntityNotExistException(String.format(EnumException.ENTITY_WITH_FIELD_NOT_EXIST.getMessage(), CLASSES[1], CLASS_FIELDS[1], EnumState.MODERATION.name()))));
        advertisementDao.create(advertisement);
        LOGGER.info(EnumLogger.SUCCESSFUL_CREATE.getText());
        return true;
    }

    @Override
    @Transactional
    public boolean delete(String username, Long index) {
        final Advertisement advertisement = advertisementDao.read(index, GraphProperty.Advertisement.DEFAULT).orElseThrow(() ->
                new EntityNotExistException(String.format(EnumException.ENTITY_WITH_FIELD_NOT_EXIST.getMessage(), CLASSES[0], CLASS_FIELDS[0], index)));
        checkPermission(advertisement, username);
        advertisement.setState(stateDao.readByDescription(EnumState.DISABLED.name()).orElseThrow(() ->
                new EntityNotExistException(String.format(EnumException.ENTITY_WITH_FIELD_NOT_EXIST.getMessage(), CLASSES[1], CLASS_FIELDS[1], EnumState.DISABLED.name()))));
        advertisementDao.update(advertisement);
        LOGGER.info(EnumLogger.SUCCESSFUL_DELETE.getText());
        return true;
    }

    @Override
    @Transactional
    public boolean updateByUser(String username, AdvertisementDto advertisementDto) {
        checkPermission(advertisementDao.read(advertisementDto.getId(), GraphProperty.Advertisement.DEFAULT).orElseThrow(() ->
                new EntityNotExistException(String.format(EnumException.ENTITY_WITH_FIELD_NOT_EXIST.getMessage(), CLASSES[0], CLASS_FIELDS[0], advertisementDto.getId()))), username);
        final Advertisement advertisement = dtoMapper.map(advertisementDto, Advertisement.class);
        advertisement.setDate(LocalDate.now());
        advertisement.setState(stateDao.readByDescription(EnumState.MODERATION.name()).orElseThrow(() ->
                new EntityNotExistException(String.format(EnumException.ENTITY_WITH_FIELD_NOT_EXIST.getMessage(), CLASSES[1], CLASS_FIELDS[1], EnumState.MODERATION.name()))));
        advertisementDao.update(advertisement);
        LOGGER.info(EnumLogger.SUCCESSFUL_UPDATE.getText());
        return true;
    }

    @Override
    @Transactional
    public boolean updateStateByModerator(Long index, StateDto state) {
        final Advertisement advertisement = advertisementDao.read(index, GraphProperty.Advertisement.DEFAULT).orElseThrow();
        advertisement.setState(stateDao.readByDescription(state.getDescription()).orElseThrow(() ->
                new EntityNotExistException(String.format(EnumException.ENTITY_WITH_FIELD_NOT_EXIST.getMessage(), CLASSES[1], CLASS_FIELDS[1], state.getDescription()))));
        advertisementDao.update(advertisement);
        LOGGER.info(EnumLogger.SUCCESSFUL_UPDATE.getText());
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public AdvertisementDto readByUser(Long index) {
        final Advertisement advertisement = advertisementDao.read(index, GraphProperty.Advertisement.DEFAULT).orElseThrow();
        if (!advertisement.getState().getId().equals(stateDao.readByDescription(EnumState.ACTIVE.name()).orElseThrow(() ->
                new EntityNotExistException(String.format(EnumException.ENTITY_WITH_FIELD_NOT_EXIST.getMessage(), CLASSES[1], CLASS_FIELDS[1], EnumState.ACTIVE.name()))).getId())) {
            throw new EntityNotAvailableException(EnumException.PERMISSION.getMessage());
        }
        final AdvertisementDto result = dtoMapper.map(advertisement, AdvertisementDto.class);
        LOGGER.info(EnumLogger.SUCCESSFUL_READ.getText());
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public AdvertisementDto readByModerator(Long index) {
        final AdvertisementDto result = dtoMapper.map(advertisementDao.read(index, GraphProperty.Advertisement.DEFAULT).orElseThrow(() ->
                new EntityNotExistException(String.format(EnumException.ENTITY_WITH_FIELD_NOT_EXIST.getMessage(), CLASSES[0], CLASS_FIELDS[0], index))), AdvertisementDto.class);
        LOGGER.info(EnumLogger.SUCCESSFUL_READ.getText());
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AdvertisementDto> readAllWithUser(Long userIndex, int page, int numberElements, EnumState state) {
        final List<AdvertisementDto> result = dtoMapper.mapAll(advertisementDao.readAllWithUser(PageParameter.of(page, numberElements),
                userDao.read(userIndex, GraphProperty.User.DEFAULT).orElseThrow(() ->
                        new EntityNotExistException(String.format(EnumException.ENTITY_WITH_FIELD_NOT_EXIST.getMessage(), CLASSES[2], CLASS_FIELDS[0], userIndex))),
                stateDao.readByDescription(state.name()).orElseThrow(() ->
                        new EntityNotExistException(String.format(EnumException.ENTITY_WITH_FIELD_NOT_EXIST.getMessage(), CLASSES[1], CLASS_FIELDS[1], EnumState.ACTIVE.name())))), AdvertisementDto.class);
        LOGGER.info(EnumLogger.SUCCESSFUL_READ.getText());
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    @SingularModel(metamodels = {Advertisement_.class, User_.class})
    public List<AdvertisementDto> readAll(int page, int numberElements, String direction, String sortField, String search,
                                          String category, double minPrice, double maxPrice, EnumState advertisementState) {
        final IPageParameter pageParameter = splitSortFiled(page, numberElements, direction, sortField);
        final IFilterParameter filterParameter = FilterParameter.of(search, category, minPrice, maxPrice);
        final IStateParameter stateParameter = StateParameter.of(stateDao.readByDescription(advertisementState.name()).orElse(null),
                stateDao.readByDescription(EnumState.APPROVED.name()).orElseThrow(() ->
                        new EntityNotExistException(String.format(EnumException.ENTITY_WITH_FIELD_NOT_EXIST.getMessage(), CLASSES[1], CLASS_FIELDS[1], EnumState.APPROVED.name()))));
        final List<AdvertisementDto> result = dtoMapper.mapAll(advertisementDao.readAllWithFilter(pageParameter, filterParameter, stateParameter),
                AdvertisementDto.class);
        LOGGER.info(EnumLogger.SUCCESSFUL_READ.getText());
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Long readSize() {
        return advertisementDao.readCount();
    }

    @Override
    @Transactional(readOnly = true)
    public Long readSize(String search, String category, double minPrice, double maxPrice, EnumState advertisementState) {
        final IFilterParameter filterParameter = FilterParameter.of(search, category, minPrice, maxPrice);
        final IStateParameter stateParameter = StateParameter.of(stateDao.readByDescription(advertisementState.name()).orElse(null),
                stateDao.readByDescription(EnumState.APPROVED.name()).orElseThrow(() ->
                        new EntityNotExistException(String.format(EnumException.ENTITY_WITH_FIELD_NOT_EXIST.getMessage(), CLASSES[1], CLASS_FIELDS[1], EnumState.APPROVED.name()))));
        return advertisementDao.readCountWitFilter(filterParameter, stateParameter);
    }

    private IPageParameter splitSortFiled(int page, int numberElements, String direction, String sortField) {
        if (sortField.contains(SORT_PARAMETER_SEPARATOR)) {
            return PageParameter.of(page, numberElements, direction,
                    singularMapper.getAttribute(SORT_FIELD + sortField.split(SORT_PARAMETER_SEPARATOR)[0]),
                    singularMapper.getAttribute(sortField));
        }
        return PageParameter.of(page, numberElements, direction, singularMapper.getAttribute(SORT_FIELD + sortField));
    }

    private void checkPermission(Advertisement advertisement, String username) {
        if (!advertisement.getUser().getId().equals(userDao.readByUserCred(username, GraphProperty.User.DEFAULT).orElseThrow(() ->
                new EntityNotExistException(String.format(EnumException.ENTITY_WITH_FIELD_NOT_EXIST.getMessage(), CLASSES[2], CLASS_FIELDS[2], username))).getId())) {
            throw new PermissionDeniedException(EnumException.PERMISSION.getMessage());
        }
    }
}
