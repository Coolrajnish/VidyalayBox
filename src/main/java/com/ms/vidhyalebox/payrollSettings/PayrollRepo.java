package com.ms.vidhyalebox.payrollSettings;

import org.springframework.stereotype.Repository;

import com.ms.vidhyalebox.util.repo.GenericRepo;

@Repository
public interface PayrollRepo extends GenericRepo<PayrollEntity, Long> {
}
