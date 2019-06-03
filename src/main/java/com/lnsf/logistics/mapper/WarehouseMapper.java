package com.lnsf.logistics.mapper;

import com.lnsf.logistics.entity.Warehouse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface WarehouseMapper {

    @Select("SELECT * FROM warehouse limit #{offset},8")
    List<Warehouse> selectAll(Integer offset);

    @Select("SELECT * FROM warehouse limit #{offset},8")
    List<Warehouse> selectAllWarehouse();

    @Select("SELECT * FROM warehouse WHERE area = #{area} limit #{offset},8")
    List<Warehouse> selectByArea(String area,Integer offset);

    @Select("SELECT * FROM warehouse WHERE level = #{level} limit #{offset},8")
    List<Warehouse> selectByLevel(Integer level,Integer offset);

    @Select("SELECT * FROM warehouse WHERE status = #{status} limit #{offset},8")
    List<Warehouse> selectByStatus(Integer status,Integer offset);

    @Select("SELECT * FROM warehouse WHERE warehouse_id = #{id}")
    Warehouse selectById(Integer id);

    @Select("SELECT * FROM warehouse WHERE user_id = #{id}")
    Warehouse selectByUserId(Integer id);

    @Select("SELECT * FROM warehouse WHERE  area = #{area} AND level = #{level} ")
    Warehouse selectByAreaAndLevel(String area, Integer level);

    @Select("SELECT * FROM warehouse WHERE name = #{name}")
    Warehouse selectByName(String name);

    @Insert("INSERT warehouse VALUES(#{warehouseId},#{name},#{address},#{userId},#{area},#{level},#{maxWeight},#{residueWeight},#{status})")
    Boolean insert(Warehouse warehouse);

    @Update("UPDATE warehouse SET name =#{name},address = #{address},user_id = #{userId},area = #{area},level = #{level},max_weight = #{maxWeight},residue_weight = #{residueWeight},status = #{status} WHERE warehouse_id = #{warehouseId}")
    Boolean update(Warehouse warehouse);



}