package com.lnsf.logistics.service.impl;

import com.lnsf.logistics.entity.Car;
import com.lnsf.logistics.entity.Warehouse;
import com.lnsf.logistics.mapper.CarMapper;
import com.lnsf.logistics.mapper.UserMapper;
import com.lnsf.logistics.mapper.WarehouseMapper;
import com.lnsf.logistics.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.lnsf.logistics.Enum.CarStatus.GIFT;
import static com.lnsf.logistics.Enum.CarStatus.IS_BUSY;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private CarMapper carMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WarehouseMapper warehouseMapper;


    @Override
    public List<Car> selectAll() {
        return carMapper.selectAll();
    }

    @Override
    public List<Car> selectByWarehouseId(Integer keyword, Integer id, Integer level, Integer status, Integer offset) {
        String sql = "SELECT * FROM car WHERE warehouse_id = " + id + " ";
        if (level != null) {
            sql += "AND level = " + level + " ";
        }
        if (status != null) {
            sql += "AND status = " + status + " ";
        } else sql += "AND status != 5 ";
        if (keyword != null) {
            sql += "AND car_id = " + keyword + " ";
        }
        if (offset != null) {
            sql += "LIMIT " + offset + ",8";
        }
        return carMapper.selectByWarehouseId(sql);
    }

    @Override
    public Integer countByWarehouseId(Integer keyword, Integer id, Integer level, Integer status) {
        String sql = "SELECT count(car_id) FROM car WHERE warehouse_id = " + id + " ";
        if (level != null) {
            sql += "AND level = " + level + " ";
        }
        if (status != null) {
            sql += "AND status = " + status + " ";
        } else sql += "AND status != 5 ";
        if (keyword != null) {
            sql += "AND car_id = " + keyword + " ";
        }
        return carMapper.countByWarehouseId(sql);
    }


    @Override
    public Car selectByUserId(Integer id) {
        return carMapper.selectByUserId(id);
    }

    @Override
    public Car selectById(Integer id) {
        return carMapper.selectById(id);
    }

    @Override
    public String insert(Car car) {
        if (car.getMaxWeight() == null) {
            return "车辆最大重量不能为空！";
        } else if (carMapper.insert(car)) {
            return "插入成功";
        } else return "插入失败";

    }

    @Override
    public String update(Car car) {
//            if (!carMapper.selectById(car.getCarId()).getStatus().equals(GIFT.getCode())) {
//            } else if (carMapper.selectById(car.getCarId()).getStatus().equals(IS_BUSY.getCode())) {
//                return "请确保该车辆不在工作状态再进行更新！";
//            } else if (car.getMaxWeight() == null) {
//                return "车辆最大重量不能为空！";
//            } else if (car.getResidueWeight() > car.getMaxWeight()) {
//                return "车辆载量不能超过最大载量！";
        //  } else
        if (carMapper.update(car)) {
            return "更新成功";
        } else return "更新失败";
//        } else return "该车辆已经废弃！";
//        return "找不到该车辆！";

    }

    @Override
    public String delete(Integer id) {
        if (carMapper.selectById(id) != null) {
            if (carMapper.selectById(id).getStatus().equals(GIFT.getCode())) {
                return "该车辆已经删除！请勿重复操作";
            } else if (carMapper.selectById(id).getStatus().equals(IS_BUSY.getCode())) {
                return "请确保该车辆不在工作状态再删除！";
            } else if (carMapper.delete(id)) {
                return "删除成功";
            } else return "删除失败";
        } else return "找不到该车辆！";
    }
}
