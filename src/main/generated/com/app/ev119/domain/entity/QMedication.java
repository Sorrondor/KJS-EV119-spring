package com.app.ev119.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMedication is a Querydsl query type for Medication
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMedication extends EntityPathBase<Medication> {

    private static final long serialVersionUID = 382151568L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMedication medication = new QMedication("medication");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath medicationName = createString("medicationName");

    public final StringPath medicationTakingtime = createString("medicationTakingtime");

    public final StringPath medicationUsage = createString("medicationUsage");

    public final QMember member;

    public QMedication(String variable) {
        this(Medication.class, forVariable(variable), INITS);
    }

    public QMedication(Path<? extends Medication> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMedication(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMedication(PathMetadata metadata, PathInits inits) {
        this(Medication.class, metadata, inits);
    }

    public QMedication(Class<? extends Medication> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

