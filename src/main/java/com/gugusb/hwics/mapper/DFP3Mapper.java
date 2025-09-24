package com.gugusb.hwics.mapper;

import com.gugusb.hwics.pojo.DFP1;
import com.gugusb.hwics.pojo.DFP2;
import com.gugusb.hwics.pojo.DFP3;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


@Repository
public interface DFP3Mapper extends CrudRepository<DFP3, Integer> {
    Optional<DFP3> findFirstByOrderByDataIdDesc();
    List<DFP3> findByUpdateTimeBetween(Timestamp startTime, Timestamp endTime);
}
