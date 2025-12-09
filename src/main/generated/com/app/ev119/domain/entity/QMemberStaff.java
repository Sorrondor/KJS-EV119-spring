package com.app.ev119.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberStaff is a Querydsl query type for MemberStaff
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberStaff extends EntityPathBase<MemberStaff> {

    private static final long serialVersionUID = -1503621179L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberStaff memberStaff = new QMemberStaff("memberStaff");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final StringPath memberStaffIndustry = createString("memberStaffIndustry");

    public final ListPath<StaffCert, QStaffCert> staffCerts = this.<StaffCert, QStaffCert>createList("staffCerts", StaffCert.class, QStaffCert.class, PathInits.DIRECT2);

    public QMemberStaff(String variable) {
        this(MemberStaff.class, forVariable(variable), INITS);
    }

    public QMemberStaff(Path<? extends MemberStaff> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberStaff(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberStaff(PathMetadata metadata, PathInits inits) {
        this(MemberStaff.class, metadata, inits);
    }

    public QMemberStaff(Class<? extends MemberStaff> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

