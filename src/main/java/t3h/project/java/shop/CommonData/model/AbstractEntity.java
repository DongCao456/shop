package t3h.project.java.shop.CommonData.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import t3h.project.java.shop.Utils.DateUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    protected Long id;

    @CreatedDate
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @Column(name = "created_at" , nullable = false,updatable = false)
    protected LocalDateTime createdAt;

    @LastModifiedDate
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @Column(name="updated_at",nullable = false)
    protected LocalDateTime updatedAt;

    @CreatedBy
    protected String createdBy;

    @LastModifiedBy
    protected  String updatedBy;



}
