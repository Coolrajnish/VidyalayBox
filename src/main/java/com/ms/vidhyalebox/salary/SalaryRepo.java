package com.ms.vidhyalebox.salary;

import org.springframework.stereotype.Repository;

import com.ms.vidhyalebox.util.repo.GenericRepo;

@Repository
public interface SalaryRepo extends GenericRepo<SalaryEntity, Long> {
}
