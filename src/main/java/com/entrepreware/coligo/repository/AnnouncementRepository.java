package com.entrepreware.coligo.repository;

import com.entrepreware.coligo.entity.Announcement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends CrudRepository<Announcement,Integer> {
}
