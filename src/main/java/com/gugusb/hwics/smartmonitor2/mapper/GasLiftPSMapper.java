package com.gugusb.hwics.smartmonitor2.mapper;

import com.gugusb.hwics.smartmonitor2.entity.processstate.GasLiftProgressState;
import com.gugusb.hwics.smartmonitor2.entity.processstate.PumpProgressState;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GasLiftPSMapper extends CrudRepository<GasLiftProgressState, Integer> {
    Optional<GasLiftProgressState> findFirstByOrderByDataIdDesc();
}
