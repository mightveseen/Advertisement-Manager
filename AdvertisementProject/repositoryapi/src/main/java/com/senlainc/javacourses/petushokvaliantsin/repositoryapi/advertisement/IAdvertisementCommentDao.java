package com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.graph.GraphName;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementComment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IAdvertisementCommentDao extends CrudRepository<AdvertisementComment, Long> {

    @EntityGraph(value = GraphName.AdvertisementComment.USER)
    List<AdvertisementComment> readAllByAdvertisement(Pageable pageable, Advertisement advertisement);

    Long countAdvertisementCommentByAdvertisement(Advertisement advertisement);
}
