package com.hoangtien2k3qx1.favouriteservice.repository;

import com.hoangtien2k3qx1.favouriteservice.domain.Favourite;
import com.hoangtien2k3qx1.favouriteservice.domain.id.FavouriteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteRepository extends JpaRepository<Favourite, FavouriteId> {

}
