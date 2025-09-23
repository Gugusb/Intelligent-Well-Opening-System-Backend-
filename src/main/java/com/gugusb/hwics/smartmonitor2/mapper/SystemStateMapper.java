package com.gugusb.hwics.smartmonitor2.mapper;

import com.gugusb.hwics.smartmonitor2.entity.SystemState;
import com.gugusb.hwics.smartmonitor2.entity.processstate.FoamProgressState;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SystemStateMapper extends CrudRepository<SystemState, Integer> {
    Optional<SystemState> findFirstByOrderByDataIdDesc();
}
