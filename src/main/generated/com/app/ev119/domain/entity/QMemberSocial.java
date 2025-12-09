package com.app.ev119.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberSocial is a Querydsl query type for MemberSocial
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberSocial extends EntityPathBase<MemberSocial> {

    private static final long serialVersionUID = 627828520L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberSocial memberSocial = new QMemberSocial("memberSocial");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final EnumPath<com.app.ev119.domain.type.SocialType> memberSocialProvider = createEnum("memberSocialProvider", com.app.ev119.domain.type.SocialType.class);

    public final StringPath memberSocialProviderId = createString("memberSocialProviderId");

    public QMemberSocial(String variable) {
        this(MemberSocial.class, forVariable(variable), INITS);
    }

    public QMemberSocial(Path<? extends MemberSocial> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberSocial(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberSocial(PathMetadata metadata, PathInits inits) {
        this(MemberSocial.class, metadata, inits);
    }

    public QMemberSocial(Class<? extends MemberSocial> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

