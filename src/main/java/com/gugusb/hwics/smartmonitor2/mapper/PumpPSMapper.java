package com.gugusb.hwics.smartmonitor2.mapper;

import com.gugusb.hwics.smartmonitor2.entity.processstate.FoamProgressState;
import com.gugusb.hwics.smartmonitor2.entity.processstate.PumpProgressState;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PumpPSMapper extends CrudRepository<PumpProgressState, Integer> {
    Optional<PumpProgressState> findFirstByOrderByDataIdDesc();
}
