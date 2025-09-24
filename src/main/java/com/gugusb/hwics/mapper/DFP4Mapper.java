package com.gugusb.hwics.mapper;

import com.gugusb.hwics.pojo.DFP1;
import com.gugusb.hwics.pojo.DFP3;
import com.gugusb.hwics.pojo.DFP4;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


@Repository
public interface DFP4Mapper extends CrudRepository<DFP4, Integer> {
    Optional<DFP4> findFirstByOrderByDataIdDesc();
    List<DFP4> findByUpdateTimeBetween(Timestamp startTime, Timestamp endTime);
}
