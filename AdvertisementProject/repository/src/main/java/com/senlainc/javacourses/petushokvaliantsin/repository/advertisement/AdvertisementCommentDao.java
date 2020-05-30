package com.senlainc.javacourses.petushokvaliantsin.repository.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementComment;
import com.senlainc.javacourses.petushokvaliantsin.repository.AbstractDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement.IAdvertisementCommentDao;
import org.springframework.stereotype.Repository;

@Repository
public class AdvertisementCommentDao extends AbstractDao<AdvertisementComment, Long> implements IAdvertisementCommentDao {
}
