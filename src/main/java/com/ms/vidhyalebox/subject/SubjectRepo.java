package com.ms.vidhyalebox.subject;

import org.springframework.stereotype.Repository;

import com.ms.vidhyalebox.util.repo.GenericRepo;

@Repository
public interface SubjectRepo extends GenericRepo<SubjectEntity, Long> {
}
