package com.ms.vidhyalebox.stream;

import org.springframework.stereotype.Repository;

import com.ms.vidhyalebox.util.repo.GenericRepo;

@Repository
public interface StreamRepo extends GenericRepo<StreamEntity, Long> {
}
