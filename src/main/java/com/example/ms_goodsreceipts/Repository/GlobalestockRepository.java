package com.example.ms_goodsreceipts.Repository;

import com.example.ms_goodsreceipts.Entity.Globalestock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GlobalestockRepository extends JpaRepository<Globalestock, Long> {


@Query("select st from Globalestock st where st.article.id =:idarticle")
    List<Globalestock> findlistById(@Param("idarticle") Long idarticle);

    @Query("select st from Globalestock st where st.locationAreaStock.Area =:Location")
    List<Globalestock> findlistStockByLocationArea(String Location);



  /*  @Query("select st from Globalestock st where st.locationAreaStock.locationBinStocks =:Bin")
    List<Globalestock> findlistStockByLocationBin(String Bin);

    @Query("select st from Globalestock st where st.locationAreaStock.locationBinStocks. =:Place")
    List<Globalestock> findlistStockByLocationPlace(String Place);*/
}
