package com.gugusb.hwics.mapper;

import com.gugusb.hwics.pojo.DFP1;
import com.gugusb.hwics.pojo.DFP2;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


@Repository
public interface DFP2Mapper  extends CrudRepository<DFP2, Integer> {
    Optional<DFP2> findFirstByOrderByDataIdDesc();
    List<DFP2> findByUpdateTimeBetween(Timestamp startTime, Timestamp endTime);
}
