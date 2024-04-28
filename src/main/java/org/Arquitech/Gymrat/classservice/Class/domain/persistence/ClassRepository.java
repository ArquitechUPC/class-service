package org.Arquitech.Gymrat.classservice.Class.domain.persistence;

import org.Arquitech.Gymrat.classservice.Class.domain.model.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {
}
