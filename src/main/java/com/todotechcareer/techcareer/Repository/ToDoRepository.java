package com.todotechcareer.techcareer.Repository;

import com.todotechcareer.techcareer.Models.ToDo;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ToDoRepository extends JpaRepository<ToDo, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM ToDo WHERE status = ?1")
    void deleteAllByStatus(boolean b);

    @Query("from ToDo where status = ?1")
    List<ToDo> findAllByStatus(boolean b);
}
