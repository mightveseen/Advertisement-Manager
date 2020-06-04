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
import com.senlainc.javacourses.petushokvaliantsin.utility.sort.implementation.PageParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@SingularClass
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
    @Transactional
    public boolean create(Long advertisementIndex, AdvertisementComment object) {
        object.setAdvertisement(advertisementService.read(advertisementIndex));
        object.setUser(userService.read(object.getUser().getIndex()));
        object.setDateTime(LocalDateTime.now());
        advertisementCommentDao.create(object);
        return true;
    }

    @Override
    public boolean create(AdvertisementComment object) {
        return false;
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
    public AdvertisementComment read(Long index) {
        return advertisementCommentDao.read(index);
    }

    @Override
    public List<AdvertisementComment> readAll(int firstElement, int maxResult) {
        return advertisementCommentDao.readAll(PageParameter.of(firstElement, maxResult));
    }

    @Override
    @Transactional(readOnly = true)
    @SingularModel(metamodels = AdvertisementComment_.class)
    public List<AdvertisementComment> readAllComments(Long index, int firstElement, int maxResult, String direction, String sortField) {
        if (sortField.equals("default")) {
            return advertisementCommentDao.readAll(PageParameter.of(firstElement, maxResult, direction),
                    AdvertisementComment_.advertisement, advertisementService.read(index));
        }
        return advertisementCommentDao.readAll(PageParameter.of(firstElement, maxResult, direction, singularMapper.getSingularAttribute(sortField)),
                AdvertisementComment_.advertisement, advertisementService.read(index));
    }
}
