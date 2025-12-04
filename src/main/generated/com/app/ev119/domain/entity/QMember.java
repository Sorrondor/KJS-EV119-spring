package com.app.ev119.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 1156346843L;

    public static final QMember member = new QMember("member1");

    public final ListPath<Address, QAddress> addresses = this.<Address, QAddress>createList("addresses", Address.class, QAddress.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.app.ev119.domain.type.BloodAbo> MemberBloodAbo = createEnum("MemberBloodAbo", com.app.ev119.domain.type.BloodAbo.class);

    public final EnumPath<com.app.ev119.domain.type.BloodRh> MemberBloodRh = createEnum("MemberBloodRh", com.app.ev119.domain.type.BloodRh.class);

    public final StringPath MemberEmail = createString("MemberEmail");

    public final EnumPath<com.app.ev119.domain.type.GenderType> MemberGender = createEnum("MemberGender", com.app.ev119.domain.type.GenderType.class);

    public final ListPath<MemberHealth, QMemberHealth> memberHealths = this.<MemberHealth, QMemberHealth>createList("memberHealths", MemberHealth.class, QMemberHealth.class, PathInits.DIRECT2);

    public final StringPath MemberName = createString("MemberName");

    public final StringPath MemberPassword = createString("MemberPassword");

    public final ListPath<MemberStaff, QMemberStaff> memberStaffs = this.<MemberStaff, QMemberStaff>createList("memberStaffs", MemberStaff.class, QMemberStaff.class, PathInits.DIRECT2);

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

