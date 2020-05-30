package com.senlainc.javacourses.petushokvaliantsin.service.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementComment;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementCommentService;
import org.springframework.stereotype.Service;

import javax.persistence.metamodel.SingularAttribute;
import java.util.List;

@Service
public class AdvertisementCommentService implements IAdvertisementCommentService {

    @Override
    public boolean create(AdvertisementComment object) {
        return false;
    }

    @Override
    public boolean remove(Long index) {
        return false;
    }

    @Override
    public boolean update(AdvertisementComment object) {
        return false;
    }

    @Override
    public AdvertisementComment read(Long index) {
        return null;
    }

    @Override
    public List<AdvertisementComment> readAll(int firstElement, int maxResult) {
        return null;
    }

    @Override
    public <C> List<AdvertisementComment> readAll(int firstElement, int maxResult, SingularAttribute<AdvertisementComment, C> sortField) {
        return null;
    }
}
