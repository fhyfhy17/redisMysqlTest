package com.cart.dao.repo;

import com.cart.entry.PlayerEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntry, Long>
{

}