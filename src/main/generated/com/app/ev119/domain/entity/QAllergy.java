package com.app.ev119.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAllergy is a Querydsl query type for Allergy
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAllergy extends EntityPathBase<Allergy> {

    private static final long serialVersionUID = -373513633L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAllergy allergy = new QAllergy("allergy");

    public final StringPath allergyName = createString("allergyName");

    public final EnumPath<com.app.ev119.domain.type.AllergyType> allergyType = createEnum("allergyType", com.app.ev119.domain.type.AllergyType.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public QAllergy(String variable) {
        this(Allergy.class, forVariable(variable), INITS);
    }

    public QAllergy(Path<? extends Allergy> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAllergy(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAllergy(PathMetadata metadata, PathInits inits) {
        this(Allergy.class, metadata, inits);
    }

    public QAllergy(Class<? extends Allergy> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

