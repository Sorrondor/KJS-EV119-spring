package com.app.ev119.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFirstAidProcedures is a Querydsl query type for FirstAidProcedures
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFirstAidProcedures extends EntityPathBase<FirstAidProcedures> {

    private static final long serialVersionUID = -531948211L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFirstAidProcedures firstAidProcedures = new QFirstAidProcedures("firstAidProcedures");

    public final QFirstAid firstAid;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath proceduresContent = createString("proceduresContent");

    public QFirstAidProcedures(String variable) {
        this(FirstAidProcedures.class, forVariable(variable), INITS);
    }

    public QFirstAidProcedures(Path<? extends FirstAidProcedures> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFirstAidProcedures(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFirstAidProcedures(PathMetadata metadata, PathInits inits) {
        this(FirstAidProcedures.class, metadata, inits);
    }

    public QFirstAidProcedures(Class<? extends FirstAidProcedures> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.firstAid = inits.isInitialized("firstAid") ? new QFirstAid(forProperty("firstAid")) : null;
    }

}

