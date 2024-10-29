package com.demo2.dao;

import com.demo2.entity.ItemA;
import com.demo2.entity.ItemB;
import com.demo2.entity.ItemC;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DataDAO {
    @Insert("insert into FormA values (#{A},#{B},#{C},#{D},#{E},#{F},#{aa},#{bb},#{cc},#{dd},#{ee})")
    int insertA(ItemA itemA);

    @Update("update FormA set A=#{A},B=#{B},C=#{C},D=#{D},E=#{E},F=#{F},aa=#{aa},bb=#{bb},cc=#{cc},dd=#{dd},ee=#{ee} where A=#{A}")
    int updateA(ItemA itemA);

    @Select("select * from FormA where A like CONCAT(CONCAT('%', #{A}), '%')")
    List<ItemA> selectA(ItemA itemA);

    @Select("select * from FormA")
    List<ItemA> selectAllA();

    @Select("select * from FormA where A=#{A}")
    ItemA selectOneA(String A);

    @Delete("delete from FormA where A=#{A}")
    int deleteA  (ItemA itemA);

    @Select("select * from FormA")
    List<ItemA> selectALlA();

    @Insert("insert into FormB values (#{A},#{B},#{C},#{D},#{E},#{F},#{aa},#{correct_aa},#{bb},#{correct_bb},#{cc},#{correct_cc},#{dd},#{correct_dd},#{ee},#{correct_ee})")
    int insertB(ItemB itemB);

    @Update("update FormB set A=#{A},B=#{B},C=#{C},D=#{D},E=#{E},F=#{F},aa=#{aa},correct_aa=#{correct_aa},bb=#{bb},correct_bb=#{correct_bb},cc=#{cc},correct_cc=#{correct_cc},dd=#{dd},correct_dd=#{correct_dd},ee=#{ee},correct_ee=#{correct_ee} where A=#{A} and C=#{C}")
    int updateB(ItemB itemB);

    @Select("select * from FormB where A = #{A} and C=#{C}")
    ItemB selectOneB(ItemB itemB);
    @Select("select * from FormB where A = #{A} and C is null")
    ItemB selectOneBCIsNull(ItemB itemB);

    @Select("select * from FormB where C = #{C}")
    List<ItemB> selectBByC(ItemB itemB);

    @Select("select * from FormB")
    List<ItemB> selectALlB();

    @Delete("delete from FormB where C like  CONCAT(CONCAT('%', #{C}), '%')")
    int deleteBByC  (ItemB itemB);

    @Delete("delete from FormB where (A =#{A} and C is null)")
    int deleteOneBCIsNull  (String A);

    @Delete("delete from FormB where (A=#{A} and C=#{C})")
    int deleteOneB  (String A, String C);

    @Select("select * from FormC where B like CONCAT(CONCAT('%', #{B}), '%')")
    List<ItemC> selectC (ItemC itemC);

    @Insert("INSERT INTO FormC (B, aaS, bbS, ccS, aaA, bbA, ccA, aaSS, aaC, ddS) VALUES (#{B}, #{aaS}, #{bbS}, #{ccS}, #{aaA}, #{bbA}, #{ccA}, #{aaSS}, #{aaC}, #{ddS})")
    int insertC(ItemC itemC);

    @Select("select * from FormC")
    List<ItemC> selectAllC();

    @Insert("update FormC set B=#{B},aaS=#{aaS},bbS=#{bbS},ccS=#{ccS},aaA=#{aaA},bbA=#{bbA},ccA=#{ccA},aaSS=#{aaSS},aaC=#{aaC},ddS=#{ddS} where B=#{B}")
    int updateC(ItemC itemC);


}
