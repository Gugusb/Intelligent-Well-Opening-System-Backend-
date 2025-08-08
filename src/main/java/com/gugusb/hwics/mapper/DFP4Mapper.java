package com.gugusb.hwics.mapper;

import com.gugusb.hwics.pojo.DFP3;
import com.gugusb.hwics.pojo.DFP4;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DFP4Mapper extends CrudRepository<DFP4, Integer> {
    Optional<DFP4> findFirstByOrderByDataIdDesc();
}
