package com.gugusb.hwics.smartmonitor2.mapper;

import com.gugusb.hwics.smartmonitor2.entity.GasLiftTimerPojo;
import com.gugusb.hwics.smartmonitor2.entity.processstate.FoamProgressState;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GasLiftTimerMapper extends CrudRepository<GasLiftTimerPojo, Integer> {
    Optional<GasLiftTimerPojo> findFirstByOrderByDataIdDesc();
}
