package com.wikimedia.repository;

import com.wikimedia.entity.WikimediaData;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WikimediaDataRepository extends JpaRepository<WikimediaData,Long> {

}
