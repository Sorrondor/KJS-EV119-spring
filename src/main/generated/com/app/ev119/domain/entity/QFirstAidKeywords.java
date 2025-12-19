package com.app.ev119.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFirstAidKeywords is a Querydsl query type for FirstAidKeywords
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFirstAidKeywords extends EntityPathBase<FirstAidKeywords> {

    private static final long serialVersionUID = -639839177L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFirstAidKeywords firstAidKeywords = new QFirstAidKeywords("firstAidKeywords");

    public final QFirstAid firstAid;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath keyword = createString("keyword");

    public QFirstAidKeywords(String variable) {
        this(FirstAidKeywords.class, forVariable(variable), INITS);
    }

    public QFirstAidKeywords(Path<? extends FirstAidKeywords> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFirstAidKeywords(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFirstAidKeywords(PathMetadata metadata, PathInits inits) {
        this(FirstAidKeywords.class, metadata, inits);
    }

    public QFirstAidKeywords(Class<? extends FirstAidKeywords> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.firstAid = inits.isInitialized("firstAid") ? new QFirstAid(forProperty("firstAid")) : null;
    }

}

