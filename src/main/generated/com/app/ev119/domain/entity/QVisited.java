package com.app.ev119.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVisited is a Querydsl query type for Visited
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVisited extends EntityPathBase<Visited> {

    private static final long serialVersionUID = 1004892681L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVisited visited = new QVisited("visited");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final DateTimePath<java.util.Date> visitedDate = createDateTime("visitedDate", java.util.Date.class);

    public final StringPath visitedDepartment = createString("visitedDepartment");

    public final StringPath visitedDiagnosis = createString("visitedDiagnosis");

    public final StringPath visitedDoctor = createString("visitedDoctor");

    public final StringPath visitedName = createString("visitedName");

    public final StringPath visitedReason = createString("visitedReason");

    public final StringPath visitedTreatmentContent = createString("visitedTreatmentContent");

    public final EnumPath<com.app.ev119.domain.type.VisitedType> visitedType = createEnum("visitedType", com.app.ev119.domain.type.VisitedType.class);

    public QVisited(String variable) {
        this(Visited.class, forVariable(variable), INITS);
    }

    public QVisited(Path<? extends Visited> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVisited(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVisited(PathMetadata metadata, PathInits inits) {
        this(Visited.class, metadata, inits);
    }

    public QVisited(Class<? extends Visited> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

