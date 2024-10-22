package com.task.xml_processor.repository;

import com.task.xml_processor.entity.Epaper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * EpaperRepository.
 *
 * @author Ravi.Gautam
 * @see Epaper
 * @since 22-October-2024
 * @version 1.0
 *
 */

@Repository
public interface EpaperRepository extends JpaRepository<Epaper, Long> {

    @Query("SELECT e FROM Epaper e WHERE (e.newspaperName LIKE %:search% OR e.filename LIKE %:search%) "
            + " AND (e.uploadedAt BETWEEN :fromDate AND :toDate)")
    List<Epaper> getAllEpaperList(@Param("search") String search, @Param("fromDate") Date fromDate,@Param("toDate") Date toDate, Pageable page) throws Exception;


}
