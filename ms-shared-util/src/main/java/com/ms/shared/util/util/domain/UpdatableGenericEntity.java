// package com.til.shared.util.util.domain;

// import java.time.OffsetDateTime;

// import javax.persistence.Column;
// import javax.persistence.MappedSuperclass;

// import org.hibernate.annotations.CreationTimestamp;
// import org.hibernate.annotations.UpdateTimestamp;

// import lombok.Data;

// @MappedSuperclass
// @Data
// public  class UpdatableGenericEntity extends  GenericEntity {

//     @Column(name = "created_by")
//     private OffsetDateTime createdBy;

//     @Column(name = "updated_by")
//     private String updatedBy;
	
//     @CreationTimestamp
//     @Column(name = "created_at")
//     private OffsetDateTime createdAt;

//     @UpdateTimestamp
//     @Column(name = "updated_at")
//     private OffsetDateTime updatedAt;
    
// }
