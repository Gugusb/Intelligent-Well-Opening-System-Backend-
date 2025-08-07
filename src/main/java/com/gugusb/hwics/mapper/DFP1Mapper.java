package com.gugusb.hwics.mapper;

import com.gugusb.hwics.pojo.DFP1;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DFP1Mapper extends CrudRepository<DFP1, Integer> {
    Optional<DFP1> findFirstByOrderByDataIdDesc();
}
