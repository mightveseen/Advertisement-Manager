package com.senlainc.javacourses.petushokvaliantsin.repositoryapi.chat;

import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.IGenericDao;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.IPageParameter;

import javax.persistence.metamodel.SetAttribute;
import java.util.List;

public interface IChatDao extends IGenericDao<Chat, Long> {

    <F> List<Chat> readAll(IPageParameter pageParameter, SetAttribute<Chat, F> field, F value);
}
