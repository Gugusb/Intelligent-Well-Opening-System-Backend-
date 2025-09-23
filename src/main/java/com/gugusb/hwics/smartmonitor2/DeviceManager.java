package com.gugusb.hwics.smartmonitor2;

import org.hibernate.collection.spi.PersistentBag;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class DeviceManager {

    public double checkAllTags(){
        Random random = new Random();
        return 0.95 + random.nextDouble();
    }

    public boolean turnOnGasLift(){
        System.out.println("气举设备启动成功！");
        return true;
    }

    public boolean turnOnPump(){
        System.out.println("抽汲设备启动成功！");
        return true;
    }

    public boolean turnOnFoam(){
        System.out.println("泡排设备启动成功！");
        return true;
    }

    public List<Boolean> getProcessState() {
        List<Boolean> list = new ArrayList<>();
        list.add(true);
        list.add(true);
        list.add(true);
        return list;
    }
}
