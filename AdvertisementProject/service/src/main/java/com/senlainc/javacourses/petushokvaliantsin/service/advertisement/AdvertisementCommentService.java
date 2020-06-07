package com.senlainc.javacourses.petushokvaliantsin.service.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementComment;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementComment_;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement.IAdvertisementCommentDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementCommentService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.user.IUserService;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.annotation.SingularClass;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.annotation.SingularModel;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.implementation.PageParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@SingularClass
@Transactional
public class AdvertisementCommentService extends AbstractService<AdvertisementComment, Long> implements IAdvertisementCommentService {

    private final IAdvertisementCommentDao advertisementCommentDao;
    private final IAdvertisementService advertisementService;
    private final IUserService userService;

    @Autowired
    public AdvertisementCommentService(IAdvertisementCommentDao advertisementCommentDao, IUserService userService,
                                       IAdvertisementService advertisementService) {
        this.advertisementCommentDao = advertisementCommentDao;
        this.userService = userService;
        this.advertisementService = advertisementService;
    }

    @Override
    public boolean create(Long advertisementIndex, AdvertisementComment object) {
        object.setAdvertisement(advertisementService.read(advertisementIndex));
        object.setUser(userService.read(object.getUser().getId()));
        object.setDateTime(LocalDateTime.now());
        advertisementCommentDao.create(object);
        return true;
    }

    @Override
    public boolean delete(Long index) {
        advertisementCommentDao.delete(advertisementCommentDao.read(index));
        return true;
    }

    @Override
    public boolean update(AdvertisementComment object) {
        advertisementCommentDao.update(object);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    @SingularModel(metamodels = AdvertisementComment_.class)
    public List<AdvertisementComment> readAll(Long index, int page, int numberElements, String direction, String sortField) {
        if (sortField.equals("default")) {
            return advertisementCommentDao.readAll(PageParameter.of(page, numberElements, direction),
                    AdvertisementComment_.advertisement, advertisementService.read(index));
        }
        return advertisementCommentDao.readAll(PageParameter.of(page, numberElements, direction, singularMapper.getSingularAttribute(sortField)),
                AdvertisementComment_.advertisement, advertisementService.read(index));
    }
}
