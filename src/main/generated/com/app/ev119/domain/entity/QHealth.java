package com.app.ev119.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHealth is a Querydsl query type for Health
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHealth extends EntityPathBase<Health> {

    private static final long serialVersionUID = 1012853661L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHealth health = new QHealth("health");

    public final ListPath<Disease, QDisease> diseases = this.<Disease, QDisease>createList("diseases", Disease.class, QDisease.class, PathInits.DIRECT2);

    public final EnumPath<com.app.ev119.domain.type.BloodAbo> healthBloodAbo = createEnum("healthBloodAbo", com.app.ev119.domain.type.BloodAbo.class);

    public final EnumPath<com.app.ev119.domain.type.BloodRh> healthBloodRh = createEnum("healthBloodRh", com.app.ev119.domain.type.BloodRh.class);

    public final EnumPath<com.app.ev119.domain.type.GenderType> healthGender = createEnum("healthGender", com.app.ev119.domain.type.GenderType.class);

    public final NumberPath<Double> healthHeight = createNumber("healthHeight", Double.class);

    public final NumberPath<Double> healthWeight = createNumber("healthWeight", Double.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public QHealth(String variable) {
        this(Health.class, forVariable(variable), INITS);
    }

    public QHealth(Path<? extends Health> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHealth(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHealth(PathMetadata metadata, PathInits inits) {
        this(Health.class, metadata, inits);
    }

    public QHealth(Class<? extends Health> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

