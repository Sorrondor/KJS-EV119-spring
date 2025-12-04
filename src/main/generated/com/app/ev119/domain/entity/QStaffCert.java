package com.app.ev119.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStaffCert is a Querydsl query type for StaffCert
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStaffCert extends EntityPathBase<StaffCert> {

    private static final long serialVersionUID = -737323965L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStaffCert staffCert = new QStaffCert("staffCert");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMemberStaff memberStaff;

    public final StringPath staffCertContent = createString("staffCertContent");

    public final DateTimePath<java.util.Date> staffCertDate = createDateTime("staffCertDate", java.util.Date.class);

    public final EnumPath<com.app.ev119.domain.type.StaffCertType> staffCertType = createEnum("staffCertType", com.app.ev119.domain.type.StaffCertType.class);

    public QStaffCert(String variable) {
        this(StaffCert.class, forVariable(variable), INITS);
    }

    public QStaffCert(Path<? extends StaffCert> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStaffCert(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStaffCert(PathMetadata metadata, PathInits inits) {
        this(StaffCert.class, metadata, inits);
    }

    public QStaffCert(Class<? extends StaffCert> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberStaff = inits.isInitialized("memberStaff") ? new QMemberStaff(forProperty("memberStaff"), inits.get("memberStaff")) : null;
    }

}

