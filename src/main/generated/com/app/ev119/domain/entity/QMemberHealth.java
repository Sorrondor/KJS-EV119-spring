package com.app.ev119.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberHealth is a Querydsl query type for MemberHealth
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberHealth extends EntityPathBase<MemberHealth> {

    private static final long serialVersionUID = 303616535L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberHealth memberHealth = new QMemberHealth("memberHealth");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final NumberPath<Integer> MemberHealthCode = createNumber("MemberHealthCode", Integer.class);

    public QMemberHealth(String variable) {
        this(MemberHealth.class, forVariable(variable), INITS);
    }

    public QMemberHealth(Path<? extends MemberHealth> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberHealth(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberHealth(PathMetadata metadata, PathInits inits) {
        this(MemberHealth.class, metadata, inits);
    }

    public QMemberHealth(Class<? extends MemberHealth> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

