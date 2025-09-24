package com.gugusb.hwics.mapper;

import com.gugusb.hwics.pojo.DFP1;
import com.gugusb.hwics.pojo.DFP4;
import com.gugusb.hwics.pojo.DFP5;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


@Repository
public interface DFP5Mapper extends CrudRepository<DFP5, Integer> {
    Optional<DFP5> findFirstByOrderByDataIdDesc();
    List<DFP5> findByUpdateTimeBetween(Timestamp startTime, Timestamp endTime);
}
